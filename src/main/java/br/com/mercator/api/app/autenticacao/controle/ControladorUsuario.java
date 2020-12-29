package br.com.mercator.api.app.autenticacao.controle;

import br.com.mercator.api.app.autenticacao.controle.to.ToUsuario;
import br.com.mercator.api.app.autenticacao.modelo.EmpresaAutenticacao;
import br.com.mercator.api.app.autenticacao.modelo.Perfil;
import br.com.mercator.api.app.autenticacao.modelo.Usuario;
import br.com.mercator.api.app.autenticacao.repositorio.RepositorioEmpresaAutenticacao;
import br.com.mercator.api.app.autenticacao.repositorioMercator.RepositorioMercatorEmpresaAutenticacao;
import br.com.mercator.api.app.autenticacao.repositorioMercator.RepositorioMercatorUsuario;
import br.com.mercator.api.arquitetura.controle.ControleExecucaoException;
import br.com.mercator.api.arquitetura.controle.ProcessadorRetornoRest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("usuarios")
public class ControladorUsuario {

    @Autowired
    ProcessadorRetornoRest<ToUsuario> processadorRetornoRest;
    @Autowired
    RepositorioMercatorUsuario repositorioMercatorUsuario;
    @Autowired
    RepositorioMercatorEmpresaAutenticacao repositorioMercatorEmpresaAutenticacao;
    @Autowired
    RepositorioEmpresaAutenticacao repositorioEmpresaAutenticacao;


    @PostMapping("novo")
    @Transactional("transactionManager")
    public ResponseEntity post(@RequestBody @Valid ToUsuario corpoRequisicao, HttpServletRequest request) {
        return processadorRetornoRest.processaPost(request, () -> {
            String cnpj = corpoRequisicao.getEmpresaAutenticacao().getCnpj();
            Optional<EmpresaAutenticacao> byCnpj = repositorioEmpresaAutenticacao.findByCnpj(cnpj);
            if (byCnpj.isPresent()) {
                throw new ControleExecucaoException(HttpStatus.UNAUTHORIZED);
            }

            Usuario usuario = corpoRequisicao.transfereValoresCamposPara(new Usuario(), true);
            usuario.setPerfil(Perfil.ROLE_GERENTE);

            EmpresaAutenticacao empresa = repositorioMercatorEmpresaAutenticacao.persiste(usuario.getEmpresaAutenticacao());
            usuario.setEmpresaAutenticacao(empresa);
            usuario = repositorioMercatorUsuario.persiste(usuario);
            corpoRequisicao.setCodigo(usuario.getCodigo());
            return corpoRequisicao;
        });
    }

}

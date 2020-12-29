package br.com.mercator.api.app.representacao.controle;

import br.com.mercator.api.app.representacao.controle.to.ToCliente;
import br.com.mercator.api.app.representacao.modelo.Cliente;
import br.com.mercator.api.app.representacao.repositorio.RepositorioCliente;
import br.com.mercator.api.app.representacao.repositorioMercator.RepositorioMercatorCliente;
import br.com.mercator.api.arquitetura.controle.ProcessadorRetornoRest;
import br.com.mercator.api.arquitetura.controle.ProcessoRetornoObjeto;
import br.com.mercator.api.arquitetura.repositorio.PesquisaPorCodigo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ControladorClientes {

    @Autowired
    ProcessadorRetornoRest<ToCliente> processadorRetornoRest;

    @Autowired
    RepositorioMercatorCliente repositorioMercatorCliente;

    @Autowired
    RepositorioCliente repositorioCliente;

//    @PreAuthorize("hasRole('ADMIN') or hasHole('OPERADOR')")
//    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity getAll() {
        return processadorRetornoRest.processaGet(() -> {
            List<Cliente> clientes = repositorioCliente.findAll();
            List<ToCliente> resultados = new ArrayList<>();
            for (Cliente cliente : clientes) {
                ToCliente to = new ToCliente();
                to.recebeValoresCamposDe(cliente);
                resultados.add(to);
            }
            return resultados;
        });
    }

    @PostMapping
    @Transactional("transactionManagerRepresentacao")
    public ResponseEntity post(@RequestBody @Valid ToCliente corpoRequisicao, HttpServletRequest request) {
        return processadorRetornoRest.processaPost(request, () -> {
            Cliente cliente = corpoRequisicao.transfereValoresCamposPara(new Cliente());
            cliente = repositorioMercatorCliente.persiste(cliente);
            corpoRequisicao.setCodigo(cliente.getCodigo());
            return corpoRequisicao;
        });
    }

    @PutMapping(path = "{codigo}")
    @Transactional("transactionManagerRepresentacao")
    public ResponseEntity put(@RequestBody @Valid ToCliente corpoRequisicao, @PathVariable String codigo) {
        return processadorRetornoRest.processaPut(() -> {
            Cliente cliente = repositorioMercatorCliente.pesquisaUm(new PesquisaPorCodigo(codigo, Cliente.class));
            corpoRequisicao.transfereValoresCamposPara(cliente, true);
            repositorioMercatorCliente.persiste(cliente);
            return corpoRequisicao;
        });
    }

}

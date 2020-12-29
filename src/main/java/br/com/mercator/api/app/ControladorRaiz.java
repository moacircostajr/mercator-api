package br.com.mercator.api.app;

import br.com.mercator.api.app.autenticacao.modelo.Usuario;
import br.com.mercator.api.app.autenticacao.repositorio.RepositorioUsuario;
import br.com.mercator.api.app.autenticacao.repositorioMercator.RepositorioMercatorUsuario;
import br.com.mercator.api.arquitetura.controle.ProcessadorRetornoRest;
import br.com.mercator.api.arquitetura.modelo.Modelo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/")
public class ControladorRaiz {

    @Autowired
    ProcessadorRetornoRest processadorRetornoRest;
    @Autowired
    RepositorioUsuario repositorioUsuario;
    @Autowired
    RepositorioMercatorUsuario repositorioMercatorUsuario;
    @Autowired
    Environment environment;

    @GetMapping
    public ResponseEntity get() {
        return processadorRetornoRest.processaGet(() -> {
            return environment.getProperty("app.mensagem");
        });

    }

    @Transactional
    @GetMapping("ping")
    public ResponseEntity ping() {
        return processadorRetornoRest.processaGet(() -> {
            /*
            Usuario novo = new Usuario();
            novo.setEmail("teste@teste.com");
            novo.setSenha("senha");
            novo.setNome("nome");
            novo.setSobrenome("sobrenome");
            repositorioMercatorUsuario.persiste(novo);

             */
            Modelo byPrimaryKey = repositorioMercatorUsuario.pesquisaPorId(1L);
            List<Usuario> all = repositorioUsuario.findAll();
            System.out.println("repositorio configurado");
            return new Date();
        });
    }
}

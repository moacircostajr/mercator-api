package br.com.mercator.api.app.autenticacao.controle.to;

import br.com.mercator.api.arquitetura.controle.To;
import br.com.mercator.api.arquitetura.seguranca.controle.to.TokenTo;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class ToUsuario extends To {

    @NotEmpty
    private String email;

    @NotEmpty
    private String senha;

    @NotEmpty
    private String nome;

    @NotEmpty
    private String sobrenome;

    private TokenTo autenticacao;

    private ToEmpresa empresaAutenticacao;
}

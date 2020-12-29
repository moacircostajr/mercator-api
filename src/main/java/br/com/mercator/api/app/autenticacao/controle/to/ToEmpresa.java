package br.com.mercator.api.app.autenticacao.controle.to;

import br.com.mercator.api.arquitetura.controle.To;
import br.com.mercator.api.arquitetura.seguranca.controle.to.TokenTo;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class ToEmpresa extends To {

    @NotEmpty
    private String cnpj;

    @NotEmpty
    private String nomeFantasia;

}

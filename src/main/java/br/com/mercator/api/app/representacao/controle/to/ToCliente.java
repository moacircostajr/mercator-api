package br.com.mercator.api.app.representacao.controle.to;

import br.com.mercator.api.arquitetura.controle.To;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@ToString
public class ToCliente extends To {

    @NotEmpty(message = "Por favor, especifique o nome do cliente")
    private String nome;

    private String cnpj;

    private String cpf;

    @NotEmpty(message = "Por favor, especifique o endereco do cliente")
    private String endereco;

    @NotEmpty(message = "Por favor, especifique pelo menos um telefone para o cliente")
    private String telefones;

    @Email(message = "O campo email esta com o formato invalido")
    @NotEmpty(message = "Por favor, especifique o email do cliente")
    private String email;


}

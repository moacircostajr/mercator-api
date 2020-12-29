package br.com.mercator.api.app.representacao.modelo;

import br.com.mercator.api.app.autenticacao.modelo.EmpresaAutenticacao;
import br.com.mercator.api.app.autenticacao.modelo.Usuario;
import br.com.mercator.api.arquitetura.modelo.ModeloGenerico;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;


@ToString
@Getter
@Setter
@Entity
@Table(name = "cliente")
public class Cliente extends ModeloGenerico {

    @ManyToOne
    @JoinColumn(name = "id_empresa", nullable = false)
    private EmpresaRepresentacao empresa;

    /*TODO: alterar no flyway*/
    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private UsuarioRepresentacao usuarioCriador;

    @Column(nullable = false)
    private String nome;

    @CNPJ
    @Column(length = 20, unique = true)
    private String cnpj;

    @CPF
    @Column(length = 20, unique = true)
    private String cpf;

    @Column(length = 256)
    private String endereco;

    @Column(length = 48)
    private String telefones;

    @Column(length = 64)
    private String email;

}

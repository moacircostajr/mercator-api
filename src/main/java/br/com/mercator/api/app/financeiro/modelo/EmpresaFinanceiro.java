package br.com.mercator.api.app.financeiro.modelo;

import br.com.mercator.api.arquitetura.modelo.ModeloGenerico;
import br.com.mercator.api.arquitetura.modelo.Sistema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@ToString
@Getter
@Setter
@Entity
@Table(name = "empresa")
public class EmpresaFinanceiro extends ModeloGenerico {

    public EmpresaFinanceiro() {    }

    @Column(name = "nome_fantasia", nullable = false)
    private String nomeFantasia;

    @Column(name = "razao_social")
    private String razaoSocial;

    @Column(name = "cnpj")
    private String cnpj;

    @Column(name = "endereco")
    private String endereco;

    @Column(name = "telefone")
    private String telefone;

}

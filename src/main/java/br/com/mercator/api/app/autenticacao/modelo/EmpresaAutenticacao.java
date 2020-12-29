package br.com.mercator.api.app.autenticacao.modelo;

import br.com.mercator.api.arquitetura.modelo.ModeloGenerico;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@ToString
@Getter
@Setter
@Entity
@Table(name = "empresa")
public class EmpresaAutenticacao extends ModeloGenerico {

    public EmpresaAutenticacao() {
    }

    @Column(name = "nome_fantasia", nullable = false)
    private String nomeFantasia;

    @Column(name = "razao_social")
    private String razaoSocial;

    @Column(name = "cnpj")
    private String cnpj;

}

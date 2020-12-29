package br.com.mercator.api.app.autenticacao.modelo;


import br.com.mercator.api.arquitetura.modelo.ModeloGenerico;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@ToString
@Getter
@Setter
@Entity
@Table(name = "usuario")
public class Usuario extends ModeloGenerico {

    @ManyToOne
    @JoinColumn(name = "id_empresa", nullable = false)
    private EmpresaAutenticacao empresaAutenticacao;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String senha;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String sobrenome;

    @Enumerated(EnumType.STRING)
    @Column (nullable = false)
    private Perfil perfil;

}

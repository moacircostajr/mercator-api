package br.com.mercator.api.app.representacao.modelo;

import br.com.mercator.api.app.autenticacao.modelo.Usuario;
import br.com.mercator.api.arquitetura.modelo.ModeloGenerico;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@ToString
@Getter
@Setter
@Entity
@Table(name = "pedido")
public class Pedido extends ModeloGenerico {

    @ManyToOne
    @JoinColumn(name = "id_empresa", nullable = false)
    private EmpresaRepresentacao empresa;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private UsuarioRepresentacao usuarioCriador;

    @ManyToOne
    @JoinColumn(name = "id_cliente", nullable = false, updatable = false)
    private Cliente cliente;

    @Column(updatable = false, nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    Date momento;

    @Column(name = "valor_total")
    Double valorTotal;

    @Column(nullable = false)
    Boolean entregue = false;
}

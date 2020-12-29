package br.com.mercator.api.app.representacao.modelo;

import br.com.mercator.api.arquitetura.modelo.ModeloGenerico;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@ToString
@Getter
@Setter
@Entity
@Table(name = "item_pedidp")
public class ItemPedido extends ModeloGenerico {

    @ManyToOne
    @JoinColumn(name = "id_pedido", nullable = false)
    private Pedido pedido;

    @Column(length = 255, nullable = false)
    private String discriminacao;

    @Column(nullable = false)
    private Float quantidade;

    @Enumerated(EnumType.STRING)
    @Column(length = 60, nullable = false)
    private TipoUnidade unidade;

    @Column(nullable = false)
    private Double valor;
}

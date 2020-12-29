package br.com.mercator.api.arquitetura.modelo;

import javax.persistence.*;

@MappedSuperclass
@SuppressWarnings("serial")
public abstract class ModeloGenerico extends ModeloGenericoComMetadados implements Modelo {

    @Id
    @Column(nullable = false, unique = true, updatable = false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    protected Long id;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Object id) {
        this.id = (Long) id;
    }


}
package br.com.mercator.api.arquitetura.repositorio;


import br.com.mercator.api.arquitetura.modelo.Modelo;

import javax.persistence.Query;

public class PesquisaPorCodigo<T extends Modelo> extends PesquisaJPQLGenerica<T> {

    private String code;
    private Class<T> clazz;

    public PesquisaPorCodigo(String code, Class<T> clazz) {
        this.code = code;
        this.clazz = clazz;
    }

    @Override
    protected String selectCount() {
        return " SELECT COUNT(entity) ";
    }

    @Override
    protected String select() {
        return " SELECT entity ";
    }

    @Override
    protected String from() {
        return " FROM " + clazz.getSimpleName() + " entity ";
    }

    @Override
    protected String where() {
        return " WHERE entity.codigo = :codigo ";
    }

    @Override
    protected void setQueryParameters(Query query) {
        query.setParameter("codigo", code);
    }

}
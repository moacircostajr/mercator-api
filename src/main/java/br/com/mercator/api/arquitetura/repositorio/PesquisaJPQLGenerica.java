package br.com.mercator.api.arquitetura.repositorio;

import br.com.mercator.api.arquitetura.modelo.Modelo;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public abstract class PesquisaJPQLGenerica<T extends Modelo> extends PesquisaRepositorio<T, Query> {

    @Override
    protected Query mountQuery(Boolean isCount, EntityManager entityManager, StringBuilder hql) {
        Query query = entityManager.createQuery(hql.toString());
        setQueryParameters(query);
        return query;
    }

    @Override
    protected Object getResultList(Query query) {
        configurePagination(query);
        return query.getResultList();
    }

    @Override
    protected Object getSingleResult(Query query) {
        return query.getSingleResult();
    }

    private void configurePagination(Query query) {
        if (maxResults > 0) {
            query.setFirstResult(firstResult);
            query.setMaxResults(maxResults);
        }
    }
}
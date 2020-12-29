package br.com.mercator.api.arquitetura.repositorio;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionException;
import org.hibernate.transform.Transformers;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

public abstract class PesquisaSQLGenerica<T> extends PesquisaRepositorio<T, Query> {

    @Override
    protected Query mountQuery(Boolean isCount, final EntityManager entityManager, final StringBuilder hql) {
        try {
            final Session session = (Session) entityManager.getDelegate();
            final Query query = session.createSQLQuery(hql.toString());
            setQueryParameters(query);
            if (!isCount) {
                query.setResultTransformer(Transformers.aliasToBean(getEntityClass()));
            }
            return query;
        } catch (final SessionException e) {
            throw new RuntimeException("Anote o servico com [" + Transactional.class + "] para abrir a sessao do Hibernate", e);
        }
    }

    @Override
    protected Object getResultList(final Query query) {
        setQueryPage(query);
        return query.list();
    }

    @Override
    protected Object getSingleResult(final Query query) {
        return query.uniqueResult();
    }

    private void setQueryPage(final Query query) {
        if (maxResults > 0) {
            query.setFirstResult(firstResult);
            query.setMaxResults(maxResults);
        }
    }
}
package br.com.mercator.api.arquitetura.repositorio;

import javax.persistence.EntityManager;
import java.lang.reflect.ParameterizedType;
import java.util.List;

abstract class PesquisaRepositorio<T, Q> implements Pesquisa<T> {

    protected int firstResult = 0;
    protected int maxResults = 0;

    List<T> find(EntityManager entityManager) {
        return executeFind(entityManager);
    }

    @SuppressWarnings("unchecked")
    protected final List<T> executeFind(EntityManager entityManager) {
        return (List<T>) execute(entityManager, false);
    }

    Long count(EntityManager entityManager) {
        return executeCount(entityManager);
    }

    protected final Long executeCount(EntityManager entityManager) {
        return ((Number) execute(entityManager, true)).longValue();
    }

    protected Object execute(EntityManager entityManager, boolean isCount) {
        StringBuilder script = new StringBuilder();
        script.append(select(isCount));
        script.append(" ");
        script.append(from());
        script.append(" ");
        script.append(where(isCount) == null ? where() : where(isCount));
        script.append(" ");
        if (!isCount) {
            script.append(orderBy());
        }
        return executeQuery(isCount, mountQuery(isCount, entityManager, script));
    }

    protected abstract Q mountQuery(Boolean isCount, EntityManager entityManager, StringBuilder hql);

    protected Object executeQuery(Boolean isCount, Q query) {
        if (!isCount) {
            return getResultList(query);
        }
        return getSingleResult(query);
    }

    protected abstract Object getSingleResult(Q query);

    protected abstract Object getResultList(Q query);

    protected final String select(boolean isCount) {
        if (isCount) {
            return selectCount();
        }
        return select();
    }

    protected abstract String selectCount();

    protected abstract String select();

    protected abstract String from();

    protected abstract String where();

    protected String where(boolean isCount) {
        return null;
    }

    protected String orderBy() {
        return "";
    }

    protected abstract void setQueryParameters(Q query);

    @Override
    public Pesquisa<T> setPage(Integer firstResult, Integer maxResults) {
        if (firstResult != null && maxResults != null) {
            this.firstResult = firstResult;
            this.maxResults = maxResults;
        }
        return this;
    }

    protected final String getDefaultSelectedEntity() {
        return getEntityClass().getSimpleName();
    }

    @SuppressWarnings("unchecked")
    protected final Class<T> getEntityClass() {
        return ((Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
    }

    // static helpers

    protected static boolean isNotNull(Object isNull) {
        return isNull != null;
    }

    protected static boolean isNotEmpty(List<?> isEmpty) {
        return isEmpty != null && !isEmpty.isEmpty();
    }

}
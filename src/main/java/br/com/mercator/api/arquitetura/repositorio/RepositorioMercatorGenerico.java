package br.com.mercator.api.arquitetura.repositorio;

import br.com.mercator.api.arquitetura.UtilitarioReflexao;
import br.com.mercator.api.arquitetura.modelo.Modelo;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.lang.reflect.ParameterizedType;
import java.util.Date;
import java.util.List;

public abstract class RepositorioMercatorGenerico<T extends Modelo, ID> implements RepositorioMercator<T, ID> {

    public abstract  EntityManager getEntityManager();

    @Override
    @SuppressWarnings("unchecked")
    public List<T> pesquisaLista(final Pesquisa<T> pesquisa) {
        return ((PesquisaRepositorio<T, ?>) pesquisa).find(getEntityManager());
    }

    @Override
    @SuppressWarnings("unchecked")
    public T pesquisaUm(final Pesquisa<T> pesquisa) {
        final List<T> find = ((PesquisaRepositorio<T, ?>) pesquisa).find(getEntityManager());
        if (find.size() == 0) {
            return null;
        } else if (find.size() == 1) {
            return find.get(0);
        }
        throw new RuntimeException("A pesquisaUm trouxe mais de um resultado. Corrija a pesquisaUm para que ela traga 0 ou 1 resultados ou utilize o metodo findManyModels.");
    }

    @Override
    @SuppressWarnings("unchecked")
    public Long pesquisaContagem(final Pesquisa<T> pesquisa) {
        return ((PesquisaRepositorio<T, ?>) pesquisa).count(getEntityManager());
    }


    @Override
    public T pesquisaPorId(final ID primaryKey) {
        return getEntityManager().find(getEntityClass(), primaryKey);
    }

    private final Class<T> getEntityClass() {
        return (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public List<T> persiste(final List<T> list) {
        for (T item : list){
            persiste(item);
        }
        return list;
    }

    @Override
    public T persiste(final T entity) {
        if (entity.getId() == null) {
            Date lastChange = new Date();
            entity.setUltimaAtualizacao(lastChange);
            entity.aplicaCodigo();
            setDpendenciesCode(entity, lastChange);
            getEntityManager().persist(entity);
            return entity;
        } else {
            if (entity.getId() == null) {
                entity.setUltimaAtualizacao(new Date());
                entity.aplicaCodigo();
            }
            return getEntityManager().merge(entity);
        }
    }

    private void setDpendenciesCode(Modelo object, Date lastChange) {
        try {
            UtilitarioReflexao utilitarioReflexao = new UtilitarioReflexao(object);
            List<String> listFieldNames = utilitarioReflexao.listaCamposRepresentaveisPor(Modelo.class);
            for (String fieldName : listFieldNames) {
                Modelo objectField = (Modelo) utilitarioReflexao.acessaValorCampo(fieldName);
                if (objectField == null) continue;

                objectField.setUltimaAtualizacao(lastChange);
                if (objectField.getCodigo() == null) {
                    objectField.aplicaCodigo();
                    setDpendenciesCode(objectField, lastChange);
                }
            }
        } catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void atualiza(final T entity) {
        getEntityManager().refresh(entity);
    }

    @Override
    public void remove(final T entity) {
        if (entity == null) return;
        T find = pesquisaPorId((ID) entity.getId());
        getEntityManager().remove(find);
    }

}
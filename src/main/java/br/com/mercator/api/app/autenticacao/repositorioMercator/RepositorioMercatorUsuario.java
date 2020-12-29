package br.com.mercator.api.app.autenticacao.repositorioMercator;

import br.com.mercator.api.app.autenticacao.modelo.Usuario;
import br.com.mercator.api.arquitetura.repositorio.RepositorioMercatorGenerico;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
@Transactional(value = "transactionManager", propagation = Propagation.MANDATORY)
public class RepositorioMercatorUsuario extends RepositorioMercatorGenerico<Usuario, Long> {

    @PersistenceContext(unitName = "MERCATOR_AUTENTICACAO")
    EntityManager entityManager;

    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }
}

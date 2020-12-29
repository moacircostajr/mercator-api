package br.com.mercator.api.app.representacao.repositorioMercator;

import br.com.mercator.api.app.representacao.modelo.Cliente;
import br.com.mercator.api.arquitetura.repositorio.RepositorioMercatorGenerico;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
@Transactional(value = "transactionManagerRepresentacao", propagation = Propagation.MANDATORY)
public class RepositorioMercatorCliente extends RepositorioMercatorGenerico<Cliente, Long> {

    @PersistenceContext(unitName = "MERCATOR_REPRESENTACAO")
    EntityManager entityManager;

    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }
}

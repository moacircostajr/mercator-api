package br.com.mercator.api.app.representacao.repositorio;

import br.com.mercator.api.app.representacao.modelo.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(value = "transactionManagerRepresentacao", propagation = Propagation.MANDATORY)
public interface RepositorioCliente extends JpaRepository<Cliente, Long> {

}

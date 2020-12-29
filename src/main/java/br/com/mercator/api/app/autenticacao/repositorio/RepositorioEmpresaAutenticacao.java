package br.com.mercator.api.app.autenticacao.repositorio;

import br.com.mercator.api.app.autenticacao.modelo.EmpresaAutenticacao;
import br.com.mercator.api.app.autenticacao.modelo.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface RepositorioEmpresaAutenticacao extends JpaRepository<EmpresaAutenticacao, Long> {
    Optional<EmpresaAutenticacao> findByCnpj(String cnpj);
}

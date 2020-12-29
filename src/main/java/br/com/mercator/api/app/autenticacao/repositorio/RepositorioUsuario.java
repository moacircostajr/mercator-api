package br.com.mercator.api.app.autenticacao.repositorio;

import br.com.mercator.api.app.autenticacao.modelo.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface RepositorioUsuario extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmail(String email);
}

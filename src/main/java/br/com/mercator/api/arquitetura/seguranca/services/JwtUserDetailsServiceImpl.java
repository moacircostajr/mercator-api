package br.com.mercator.api.arquitetura.seguranca.services;

import br.com.mercator.api.app.autenticacao.modelo.Usuario;
import br.com.mercator.api.app.autenticacao.repositorio.RepositorioUsuario;
import br.com.mercator.api.arquitetura.seguranca.JwtUserFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private RepositorioUsuario repositorioUsuario;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<Usuario> usuario = repositorioUsuario.findByEmail(username);
        if (usuario.isPresent()) {
            return JwtUserFactory.create(usuario.get());
        }

        throw new UsernameNotFoundException("Email " + username + " não encontrado.");
    }

}

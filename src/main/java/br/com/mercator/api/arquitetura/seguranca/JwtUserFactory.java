package br.com.mercator.api.arquitetura.seguranca;

import java.util.ArrayList;
import java.util.List;

import br.com.mercator.api.app.autenticacao.modelo.Perfil;
import br.com.mercator.api.app.autenticacao.modelo.Usuario;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;


public class JwtUserFactory {

	private JwtUserFactory() {
	}

	/**
	 * Converte e gera um JwtUser com base nos dados de um aluno ou de um colaborador.
	 * 
	 * @param usuario (com os atributos id, email, senha e perfil(enum))
	 * @return JwtUser
	 */
	public static JwtUser create(Usuario usuario) {
		JwtUser jwtUser =  new JwtUser(usuario.getId(), usuario.getEmail(), usuario.getSenha(),
				mapToGrantedAuthorities(usuario.getPerfil()));
		return jwtUser;
	}

	/*
	public static JwtUser create(Object object) {
			JwtUser jwtUser = null;
			if (object instanceof Aluno) {
				jwtUser =  new JwtUser(((Aluno) object).getId(), ((Aluno) object).getEmail(), ((Aluno) object).getSenha(),
						mapToGrantedAuthorities(((Aluno) object).getPerfil()));
			} else if (object instanceof Usuario) {
				jwtUser = new JwtUser(((Usuario) object).getId(), ((Usuario) object).getEmail(), ((Usuario) object).getSenha(),
						mapToGrantedAuthorities(((Usuario) object).getPerfil()));
			}
			return jwtUser;
		}
	*/

	/**
	 * Converte o perfil do usuário para o formato utilizado pelo Spring Security.
	 * 
	 * @param perfil
	 * @return List<GrantedAuthority>
	 */
	private static List<GrantedAuthority> mapToGrantedAuthorities(Perfil perfil) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority(perfil.toString()));
		return authorities;
	}

}

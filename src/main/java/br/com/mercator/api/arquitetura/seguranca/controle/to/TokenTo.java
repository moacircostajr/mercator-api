package br.com.mercator.api.arquitetura.seguranca.controle.to;

public class TokenTo {

	private String token; 
	
	public TokenTo() {
	}
	
	public TokenTo(String token) {
		this.token = token;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}

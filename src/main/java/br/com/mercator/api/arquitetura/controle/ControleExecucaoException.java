package br.com.mercator.api.arquitetura.controle;

import org.springframework.http.HttpStatus;

public class ControleExecucaoException extends RuntimeException {

    private String mensagem;
    private HttpStatus statusHttp;

    public ControleExecucaoException(HttpStatus statusHttp) {
        this.mensagem = mensagem;
        this.statusHttp = statusHttp;
    }

    public ControleExecucaoException(HttpStatus statusHttp, String mensagem) {
        this.mensagem = mensagem;
        this.statusHttp = statusHttp;
    }

    public String getMensagem() {
        return mensagem;
    }

    public HttpStatus getStatusHttp() {
        return statusHttp;
    }
}

package br.com.mercator.api.arquitetura.controle;

import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;

public interface ProcessadorRetornoRest<T extends To> {

    ResponseEntity processaGet(ProcessoRetornoLista<T> processo);

    ResponseEntity processaGet(ProcessoRetornoObjeto<T> processoRetornoObjeto);

    ResponseEntity processaPost(HttpServletRequest httpRequest, ProcessoRetornoObjeto<T> processoRetornoObjeto);

    ResponseEntity processaPut(ProcessoRetornoObjeto<T> processoRetornoObjeto);

    ResponseEntity processaDelete(ProcessoSemRetorno<T> processo);
}
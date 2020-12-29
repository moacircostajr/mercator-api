package br.com.mercator.api.arquitetura.controle;

import br.com.mercator.api.arquitetura.UtilitarioReflexao;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.net.URI;

@Service
public class ProcessadorRetornoRestImpl<T extends To> implements ProcessadorRetornoRest<T> {

    @Override
    public ResponseEntity processaGet(ProcessoRetornoLista<T> processo) {
        try {
            return ResponseEntity.ok(processo.executa());
        } catch (Exception e) {
            return trataErro(e);
        }
    }

    @Override
    public ResponseEntity processaGet(ProcessoRetornoObjeto<T> processoRetornoObjeto) {
        try {
            T obj = processoRetornoObjeto.executa();
            if (obj == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            } else {
                return ResponseEntity.status(HttpStatus.OK).body(obj);
            }
        } catch (Exception e) {
            return trataErro(e);
        }
    }

    @Override
    public ResponseEntity processaPost(HttpServletRequest httpRequest, ProcessoRetornoObjeto<T> processo) {
        try {
            T obj = processo.executa();
            UtilitarioReflexao utilitarioReflexao = new UtilitarioReflexao(obj);
            String code = (String) utilitarioReflexao.acessaValorCampo("codigo");
            String uriPath = httpRequest.getServerName() + ":" + httpRequest.getServerPort() + httpRequest.getContextPath() + httpRequest.getServletPath() + "/" + code;
            uriPath = uriPath.replaceAll("//","/");
            URI create = URI.create(uriPath); // url para acessar o recurso
            return ResponseEntity.status(HttpStatus.CREATED).location(create).body(obj);
        } catch (Exception e) {
            return trataErro(e);
        }

    }

    @Override
    public ResponseEntity processaPut(ProcessoRetornoObjeto<T> processo) {
        try {
            T obj = processo.executa();
            if (obj == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception e) {
            return trataErro(e);
        }

    }

    @Override
    public ResponseEntity processaDelete(ProcessoSemRetorno<T> processo) {
        try {
            processo.executa();
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception e) {
            return trataErro(e);
        }

    }

    private ResponseEntity trataErro(Exception e) {

        if(e instanceof ControleExecucaoException){
            ControleExecucaoException controleExecucaoException = (ControleExecucaoException) e;
            return ResponseEntity.status(controleExecucaoException.getStatusHttp()).body(controleExecucaoException.getMensagem());
        }

        e.printStackTrace();
        Message message = new Message();
        message.message = e.getMessage();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
    }

}

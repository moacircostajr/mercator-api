package br.com.mercator.api.arquitetura.controle;

import java.util.List;

public interface ProcessoRetornoLista<T> {
    public List<T> executa();
}

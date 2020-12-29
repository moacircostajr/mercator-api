package br.com.mercator.api.arquitetura.repositorio;

public interface Pesquisa<T> {

    Pesquisa<T> setPage(Integer firstResult, Integer maxResults);

}
package br.com.mercator.api.arquitetura.repositorio;

import br.com.mercator.api.arquitetura.modelo.Modelo;

import java.util.List;

public interface RepositorioMercator<T extends Modelo, ID> {

    /**
     * Executa pesquisaUm por uma lista de resultados
     *
     * @param pesquisa
     * @return
     */
    List<T> pesquisaLista(Pesquisa<T> pesquisa);

    /**
     * Executa pesquisaUm de um unico resultado
     *
     * @param pesquisa
     * @return
     */
    T pesquisaUm(Pesquisa<T> pesquisa);

    /**
     * Executa pesquisaUm count
     *
     * @param pesquisa
     * @return
     */
    Long pesquisaContagem(Pesquisa<T> pesquisa);

    /**
     * Faz a busca da entidade pelo id
     * @param primaryKey
     */
    T pesquisaPorId(ID primaryKey);

    // metodos de persistencia

    /**
     * Persiste uma lista de entidades ao mesmo tempo.
     * @param list
     * @return
     */
    List<T> persiste(final List<T> list);
    /**
     * Torna a entidade gerenciada e persiste as alteracoes.
     * Retorna uma entidade gerenciada a partir do id de uma entidade transiente
     * (nao gerenciada), caso nao encontre no contexo uma entidade com o mesmo
     * id no contexto, a entidade passada como parametro sera persistida no
     * commit.
     */
    T persiste(T entity);


    /**
     * Atualiza a instÃ¢ncia a apartir das informacoes do banco de dados,
     * substituindo os valores na entidade pelos valores que estao no banco
     * (como se fosse um reset).
     */
    void atualiza(T entity);

    /**
     * Remove a entidade do contexto e da arquitetura de dados
     */
    void remove(T entity);

}
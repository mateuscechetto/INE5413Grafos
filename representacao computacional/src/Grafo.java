import java.util.HashMap;

public interface Grafo {
	/**
	 * Insere um vertice no grafo
	 * @param vertice inteiro que se refere ao vertice
	 */
	public void insereVertice(int vertice);
	
	/**
	 * Insere uma aresta no grafo
	 * @param vertice1 Um vertice da relacao
	 * @param vertice2 Outro vertice da relacao
	 * @return true se a aresta foi adicionada;
	 * 	false se a aresta ja fizesse parte do grafo
	 * 	ou os vertices nao sejam parte do grafo2q
	 */
	public boolean insereAresta(int vertice1, int vertice2);
	
	/**
	 * Verifica se dois vertices sao adjacentes
	 * @param vertice1 Vertice a ser verificado
	 * @param vertice2 Vertice a ser verificado
	 * @return true se vertice1 for adjacente a vertice2;
	 * 	false caso contrario
	 */
	public boolean ehAdjacente(int vertice1, int vertice2);
	
	/**
	 * Calcula o valor do grau de um vertice
	 * @param vertice Vertice do qual o grau será calculado
	 * @return valor do grau do vertice;
	 *  -1 caso o vertice nao faca parte do grafo
	 */
	public int getGrau(int vertice);
	
	/**
	 * Imprime no console os vertices vizinhos de um vertice
	 * @param vertice vertice cujos vizinhos serão impressos
	 */
	public void imprimeVizinhosDe(int vertice);
	
	/**
	 * Remove vertice do grafo
	 * @param vertice vertice a ser removido
	 * @return true se o vertice foi removido;
	 * 	false se o vertice nao fizesse parte do grafo 
	 */
	public boolean removeVertice(int vertice);
	
	/**
	 * Remove aresta do grafo
	 * @param vertice1 Vertice da relacao
	 * @param vertice2 Outro vertice da relacao
	 * @return true caso a aresta tenha sido removida; falso caso
	 * 	a aresta nao faca parte do grafo
	 */

	public boolean removeAresta(int vertice1, int vertice2);
	
	/**
	 * Imprime o grafo no console
	 */
	public void print();
	
	/**
	 * Realiza busca em largura a partir de um vertice
	 * @param vertice pelo qual começara a busca
	 * @return HashMap o qual as chaves sao os vertices,
	 *  e os valores eh um array o qual 
	 *  [0] sao as distancias,
	 *  [1] sao os antecessores
	 *  [2] sao os verificadores se o vertice foi atingido
	 */
	public HashMap<Integer, Integer[]> buscaLargura(int vertice);
	
}

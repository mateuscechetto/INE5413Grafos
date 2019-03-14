
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
	 */
	public void insereAresta(int vertice1, int vertice2);
	
	/**
	 * Verifica se dois vertices sao adjacentes
	 * @param vertice1 Vertice a ser verificado
	 * @param vertice2 Vertice a ser verificado
	 * @return true se vertice1 for adjacente a vertice2; false caso contrario
	 */
	public boolean ehAdjacente(int vertice1, int vertice2);
	
	/**
	 * Calcula o valor do grau de um vertice
	 * @param vertice Vertice do qual o grau será calculado
	 * @return valor do grau do vertice
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
	 */
	public void removeVertice(int vertice);
	
	/**
	 * Remove aresta do grafo
	 * @param vertice1 Vertice da relacao
	 * @param vertice2 Outro vertice da relacao
	 */
	public void removeAresta(int vertice1, int vertice2);
	
	/**
	 * Imprime o grafo no console
	 */
	public void print();
}

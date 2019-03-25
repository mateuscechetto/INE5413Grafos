
public interface IGrafo {
	
	/**
	 * 
	 * @return a quantidade de vertices;
	 */
	int qtdVertices();
	
	/**
	 * 
	 * @return a quantidade de arestas;
	 */
	int qtdArestas();
	
	/**
	 * 
	 * @param inteiro referente ao vertice;
	 * @return o grau do vertice; 
	 * -1 caso o vertice nao faca parte do grafo
	 */
	int grau(int vertice);
	
	/**
	 * 
	 * @param inteiro referente ao vertice;
	 * @return retorna o rotulo do vertice;
	 */
	String rotulo(int vertice);
	
	/**
	 * 
	 * @param vertice
	 * @return  os vizinhos do vertice;
	 */
	Integer[] vizinhos(int vertice);
	
	/**
	 * 
	 * @param u um dos vertices da aresta
	 * @param v o outro vertice da aresta
	 * @return  se {u, v} ∈ E, retorna verdadeiro; 
	 * se nao existir, retorna falso;
	 */
	boolean haAresta(int u, int v);
	
	/**
	 * 
	 * @param u um dos vertices da aresta
	 * @param v o outro vertice da aresta
	 * @return : se {u, v} ∈ E, retorna o peso da aresta {u, v};
	 *  se nao existir, retorna um valor infinito positivo;

	 */
	double peso(int u, int v);
	
	
}

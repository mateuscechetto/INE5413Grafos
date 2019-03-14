import java.util.HashMap;

//ignora caso repetido, entrada null, etc...
public class GrafoHashMap implements Grafo {

	private HashMap<Integer, HashMap<Integer,Integer>> grafo;
	
	public GrafoHashMap() {
		this.grafo = new HashMap<Integer, HashMap<Integer,Integer>>();
	}

	public GrafoHashMap(int[] vertices) {
		this.grafo = new HashMap<Integer, HashMap<Integer,Integer>>();
		for(int vertice: vertices) {
			this.grafo.put(vertice, new HashMap<Integer, Integer>());

		}
	}
	
	public GrafoHashMap(int[] vertices,int[][] arestas) {
		this.grafo = new HashMap<Integer, HashMap<Integer,Integer>>();
		for(int vertice: vertices) {
			this.grafo.put(vertice, new HashMap<Integer, Integer>());

		}
		for(int i = 0; i < arestas.length; i++) {
			this.grafo.get(arestas[i][0]).put(arestas[i][1], 1);
			this.grafo.get(arestas[i][1]).put(arestas[i][0], 1);
		}
	}
	
	
	@Override
	public void insereVertice(int vertice) {
		this.grafo.put(vertice, new HashMap<Integer, Integer>());
	}
	
	@Override
	public void insereAresta(int vertice1, int vertice2) {
		this.grafo.get(vertice1).put(vertice2, 1);
		this.grafo.get(vertice2).put(vertice1, 1);
	}
	
	@Override
	public boolean ehAdjacente(int vertice1, int vertice2) {
		int valor = this.grafo.get(vertice1).getOrDefault(vertice2, 0);
		return valor == 1;
	}
	
	@Override
	public int getGrau(int vertice) {
		return this.grafo.get(vertice).size();
	}
	
	@Override
	public void imprimeVizinhosDe(int vertice) {
		HashMap<Integer, Integer> auxiliar = this.grafo.get(vertice);
		for(Integer i: auxiliar.keySet()) {
			System.out.println(i);
		}
	}
	
	@Override
	public void removeVertice(int vertice) {
		HashMap<Integer, Integer> removido = this.grafo.remove(vertice);
		for(Integer i: removido.keySet()) {
			this.grafo.get(i).remove(vertice);
		}
	}
	
	@Override
	public void removeAresta(int vertice1, int vertice2) {
		this.grafo.get(vertice1).remove(vertice2);
		this.grafo.get(vertice2).remove(vertice1);
	}
	
	@Override
	public void print() {
		for(Integer vertice: this.grafo.keySet()) {
			System.out.println(vertice);
		}
		System.out.println("<-------------------------->");
		for(HashMap<Integer, Integer> vertice: this.grafo.values()) {
			for(Integer vizinho: vertice.keySet()) {
				System.out.print(vizinho + ", ");
			}
			System.out.println();
		}
	}
	
	
}

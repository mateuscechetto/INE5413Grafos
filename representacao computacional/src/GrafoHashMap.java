import java.util.HashMap;
import java.util.LinkedList;
import java.util.Stack;

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
	public boolean insereAresta(int vertice1, int vertice2) {
		if(!this.grafo.containsKey(vertice1) ||
				!this.grafo.containsKey(vertice2)) return false;
		this.grafo.get(vertice1).put(vertice2, 1);
		return this.grafo.get(vertice2).put(vertice1, 1) == null;
	}
	
	@Override
	public boolean ehAdjacente(int vertice1, int vertice2) {
		if(!this.grafo.containsKey(vertice1)) return false;
		int valor = this.grafo.get(vertice1).getOrDefault(vertice2, 0);
		return valor == 1;
	}
	
	@Override
	public int getGrau(int vertice) {
		if(!this.grafo.containsKey(vertice)) return -1;
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
	public boolean removeVertice(int vertice) {
		HashMap<Integer, Integer> removido = this.grafo.remove(vertice);
		if(removido == null) return false;
		for(Integer i: removido.keySet()) {
			this.grafo.get(i).remove(vertice);
		}
		return true;
	}
	
	@Override
	public boolean removeAresta(int vertice1, int vertice2) {
		if(!this.grafo.containsKey(vertice1) ||
			!this.grafo.containsKey(vertice2)) return false;
		this.grafo.get(vertice1).remove(vertice2);
		this.grafo.get(vertice2).remove(vertice1);
		return true;
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

	@Override
	public HashMap<Integer, Integer[]> buscaLargura(int vertice) {
		HashMap<Integer, Integer[]> tabela = new HashMap<>();
		for(int v: grafo.keySet()) {
			Integer[] setup = {Integer.MAX_VALUE, -1, -1};
			tabela.put(v,setup);
		}
		
		tabela.get(vertice)[0] = 0;
		tabela.get(vertice)[2] = 1;

		
		LinkedList<Integer> fila = new LinkedList<Integer>();
		fila.add(vertice);
		
		while(!fila.isEmpty()) {
			int antecessor = fila.remove();
			for(int i: grafo.get(antecessor).keySet()) {
				if(tabela.get(i)[2] < 0) {
					tabela.get(i)[2] = 1;
					tabela.get(i)[0] = tabela.get(antecessor)[0] + 1;
					tabela.get(i)[1] = antecessor;
					fila.add(i);
				}
			}
		}
		return tabela;
	}

	@Override
	public HashMap<Integer, Integer[]> buscaProfundidade(int vertice) {
		HashMap<Integer, Integer[]> tabela = new HashMap<>();
		for(int v: grafo.keySet()) {
			Integer[] setup = {Integer.MAX_VALUE, -1, -1};
			tabela.put(v, setup);
		}
		
		tabela.get(vertice)[0] = 0;
		tabela.get(vertice)[2] = 1;
		int tempo = 0;
		
		Stack<Integer> pilha = new Stack<>();
		pilha.push(vertice);
		
		while(!pilha.empty()) {
			tempo++;
			int antecessor = pilha.pop();
			tabela.get(antecessor)[0] = tempo;
			for(int i: grafo.get(antecessor).keySet()) {
				if(tabela.get(i)[2] < 0) {
					tabela.get(i)[2] = 1;
					tabela.get(i)[1] = antecessor;
					pilha.push(i);
				}
			}
		}
		
		return tabela;
	}
	
	
}

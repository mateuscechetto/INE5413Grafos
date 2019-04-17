import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Stack;

public class Grafo implements IGrafo {
	
	private HashMap<Integer, HashMap<Integer, Double>> grafo;
	private String[] rotulos;
	private int qtdArestas;
	
	public Grafo(String nomeArquivo) {
		
		try {		
			FileReader arq = new FileReader(nomeArquivo);
			BufferedReader lerArq = new BufferedReader(arq);
			
			String linha = lerArq.readLine();
			String[] auxiliar = linha.split(" ");
			int nVertices = Integer.parseInt(auxiliar[auxiliar.length - 1]);
			int[] vertices = new int[nVertices];
			String[] rotulos = new String[nVertices];
			for(int i = 0; i < nVertices; i++) {
				linha = lerArq.readLine();
				auxiliar = linha.split(" ");
				vertices[i] = Integer.parseInt(auxiliar[0]);
				rotulos[i] = auxiliar[1];
				for(int j = 2; j < auxiliar.length; j++) {
					rotulos[i] += " " + auxiliar[j];
				}
			}
			linha = lerArq.readLine();
			ArrayList<Integer[]> arestas = new ArrayList<>();
			ArrayList<Double> pesos = new ArrayList<>(); 
			
			linha = lerArq.readLine();
			while(linha != null) {
				auxiliar = linha.split(" ");
				Integer[] aresta = new Integer[2];
				aresta[0] = Integer.parseInt(auxiliar[0]);
				aresta[1] = Integer.parseInt(auxiliar[1]);
				arestas.add(aresta);
				pesos.add(Double.parseDouble(auxiliar[2]));
				linha = lerArq.readLine();
			}
			arq.close();
			
			this.grafo = new HashMap<>();
			for(int vertice: vertices) {
				this.grafo.put(vertice, new HashMap<Integer, Double>());
			}
			for(int i = 0; i < arestas.size(); i++) {
				this.grafo.get(arestas.get(i)[0]).put(arestas.get(i)[1], pesos.get(i));
				this.grafo.get(arestas.get(i)[1]).put(arestas.get(i)[0], pesos.get(i));
			}
			this.rotulos = rotulos;
			this.qtdArestas = arestas.size();
			
		} catch(IOException e ) {
			System.out.println("Erro na abertura do arquivo, verifique se o caminho estah correto");
			e.getMessage();
		}
		

	}

	@Override
	public int qtdVertices() {
		return this.grafo.size();
	}

	@Override
	public int qtdArestas() {
		return qtdArestas;
	}

	@Override
	public int grau(int vertice) {
		if(!this.grafo.containsKey(vertice)) return -1;
		return this.grafo.get(vertice).size();
	}

	@Override
	public String rotulo(int vertice) {
		return rotulos[vertice -1];
	}

	@Override
	public Integer[] vizinhos(int vertice) {
		return this.grafo.get(vertice).keySet().toArray(new Integer[0]);
	}

	@Override
	public boolean haAresta(int u, int v) {
		if(!this.grafo.containsKey(u)) return false;
		return this.grafo.get(u).containsKey(v);
	}

	@Override
	public double peso(int u, int v) {
		if(haAresta(u, v)) {
			return this.grafo.get(u).get(v);
		} else {
			return Double.MAX_VALUE;
		}
	}
	
	public void buscaLargura(int vertice) {
		HashMap<Integer, Integer[]> tabela = new HashMap<>();
		for(int v: grafo.keySet()) {
			Integer[] setup = {Integer.MAX_VALUE, -1, -1};
			tabela.put(v,setup);
		}
		
		tabela.get(vertice)[0] = 0;
		tabela.get(vertice)[2] = 1;
		int distancia = -1;

		
		LinkedList<Integer> fila = new LinkedList<Integer>();
		fila.add(vertice);
		
		while(!fila.isEmpty()) {
			int antecessor = fila.remove();
			if(distancia != tabela.get(antecessor)[0]) {
				distancia = tabela.get(antecessor)[0];
				System.out.println();
				System.out.print(distancia + ": " + antecessor);
			} else {
				System.out.print(", " + antecessor);
			}
			for(int i: grafo.get(antecessor).keySet()) {
				if(tabela.get(i)[2] < 0) {
					tabela.get(i)[2] = 1;
					tabela.get(i)[0] = tabela.get(antecessor)[0] + 1;
					tabela.get(i)[1] = antecessor;
					fila.add(i);
				}
			}
		}
		System.out.println();
	}
	
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
	
	public boolean ehConexo() {
		int vertice = 1;
		HashMap<Integer, Integer[]> tabela = new HashMap<>();
		ArrayList<Integer> restantes = new ArrayList<>();
		for(int v: grafo.keySet()) {
			Integer[] setup = {Integer.MAX_VALUE, -1, -1};
			tabela.put(v, setup);
			restantes.add(v);
		}
		
		tabela.get(vertice)[0] = 0;
		tabela.get(vertice)[2] = 1;
		int tempo = 0;
		
		Stack<Integer> pilha = new Stack<>();
		pilha.push(vertice);
		
		while(!pilha.empty()) {
			tempo++;
			int antecessor = pilha.pop();
			Integer ant = (Integer) antecessor;
			restantes.remove(ant);
			tabela.get(antecessor)[0] = tempo;
			for(int i: grafo.get(antecessor).keySet()) {
				if(tabela.get(i)[2] < 0) {
					tabela.get(i)[2] = 1;
					tabela.get(i)[1] = antecessor;
					pilha.push(i);
				}
			}
		}
		return restantes.isEmpty();
	}
	
	public int euleriano() {
		for(Integer vertice: grafo.keySet()) {
			if(grafo.get(vertice).size() % 2 == 1) {
				System.out.println(0);
				return 0;
			}
		}
		if(!ehConexo()) {
			System.out.println(0);
			return 0;
		}
		
		System.out.println(1);
		HashMap<Integer, ArrayList<Integer>> auxiliar = new HashMap<>();
		for(Integer vertice: grafo.keySet()) {
			auxiliar.put(vertice, new ArrayList<>());
			for(Integer vizinho: grafo.get(vertice).keySet()) {
				auxiliar.get(vertice).add(vizinho);
			}
		}
		Stack<Integer> pilha = new Stack<>();
		ArrayList<Integer> circuito = new ArrayList<>();
		int verticeAtual = 1;
		pilha.push(verticeAtual);
		
		while(!pilha.isEmpty()) {
			
			if(!auxiliar.get(verticeAtual).isEmpty()) {
				pilha.add(verticeAtual);
				int proximoVertice = auxiliar.get(verticeAtual).remove(0);
				Integer vAtual = (Integer) verticeAtual;
				auxiliar.get(proximoVertice).remove(vAtual);
				verticeAtual = proximoVertice;
			} else {
				circuito.add(verticeAtual);
				verticeAtual = pilha.pop();
			}
		}

		for(int i = 0; i < circuito.size() -1; i++) {
			System.out.print(circuito.get(i) + ",");
		}
		System.out.println(circuito.get(circuito.size() - 1));
		
		return 1;
	}
	
	public void dijkstra(int vertice) {
		HashMap<Integer, Data> tabela = new HashMap<>();
		for(int v: grafo.keySet()) {
			Data setup = new Data(Double.MAX_VALUE, -1);
			tabela.put(v, setup);
		}
		PriorityQueue<Data> pq = new PriorityQueue<>(new Comparator<Data>() {
			@Override
			public int compare(Data arg0, Data arg1) {
				return (int) (arg0.getDistancia() - arg1.getDistancia());
			}
		});
		ArrayList<Integer> jaAdicionados = new ArrayList<>();
		tabela.get(vertice).setDistancia(0);
		
		Data added = new Data(tabela.get(vertice).getDistancia(), vertice);
		pq.add(added);
		jaAdicionados.add(vertice);
		
		while(!pq.isEmpty()) {
			Data u = pq.poll();
			for(Integer v: grafo.get(u.getVertice()).keySet()) {
				if(jaAdicionados.contains(v)) continue;
				double value = u.getDistancia() + grafo.get(u.getVertice()).get(v);
				if(tabela.get(v).getDistancia() > value) {
					tabela.get(v).setDistancia(value);
					tabela.get(v).setVertice(u.getVertice());
					pq.add(new Data(tabela.get(v).getDistancia(), v));
				}
			}
		}
		
		for(int key: tabela.keySet()) {
			System.out.print(rotulos[key - 1] + ": ");
			System.out.println(tabela.get(key).getVertice() + " d=" + tabela.get(key).getDistancia());
		}

	}
	
	
	public void floydWarshall() {
		double[][] matriz = new double[grafo.size()][grafo.size()];
		for(int k: grafo.keySet()) {
			for(int i = 1; i <= matriz.length; i++) {
				if(grafo.get(k).keySet().contains(i)) {
					matriz[k - 1][i - 1] = grafo.get(k).get(i);
				} else {
					matriz[k - 1][i - 1] = Double.MAX_VALUE;
				}
			}
		}
		//imprimeMatriz(matriz);
		for(int k: grafo.keySet()) {
			for(int u: grafo.keySet()) {
				for(int v: grafo.keySet()) {
					if(u == v) {
						matriz[u - 1][v - 1] = 0;
					} else {
						matriz[u - 1][v - 1] = Math.min(matriz[u - 1][v - 1], matriz[u - 1][k - 1] + matriz[k - 1][v - 1]);
					}
				}
			}
		}
		for (int i = 0; i < matriz.length; i++) {
			System.out.print(rotulo(i + 1) + ": ");
			for (int j = 0; j < matriz[i].length; j++) {
				System.out.print(matriz[i][j] + ",");
			}
	    System.out.println();
		}
		//imprimeMatriz(matriz);
	}
	
	private void imprimeMatriz(double[][] matriz) {
		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz[i].length; j++) {
				System.out.print(matriz[i][j] + " ");
			}
	    System.out.println();
		}
	}
	
	private class Data {
		double distancia;
		int vertice;		
		public Data(double distancia, int vertice) {
			this.distancia = distancia;
			this.vertice = vertice;
		}
		public double getDistancia() {
			return distancia;
		}
		public void setDistancia(double distancia) {
			this.distancia = distancia;
		}
		public int getVertice() {
			return vertice;
		}
		public void setVertice(int vertice) {
			this.vertice = vertice;
		}
		
	}
	
	
	
	

	
}

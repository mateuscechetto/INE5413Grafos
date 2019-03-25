import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

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

	
}
import java.util.Arrays;
import java.util.HashMap;

public class GrafoMatrizAdjacencia implements Grafo {

	private int[][] matriz;
	private int[] vertices;
	private int nVertices;
	
	public GrafoMatrizAdjacencia(int[] vertices) {
		//poderia ser criado ja com espaco reservado para novos vertices mas nao acredito que haja demanda
		this.vertices = vertices;
		this.nVertices = vertices.length;
		this.matriz = new int[nVertices][nVertices + 1]; 
	}
	
	public GrafoMatrizAdjacencia(int[] vertices, int[][] arestas) {
		this.vertices = vertices;
		this.nVertices = vertices.length;
		this.matriz = new int[nVertices][nVertices + 1]; 
		for(int i = 0; i < arestas.length; i++) {
			this.matriz[arestas[i][0]][arestas[i][1]] = 1;
			this.matriz[arestas[i][0]][nVertices]++;
			this.matriz[arestas[i][1]][arestas[i][0]] = 1;
			this.matriz[arestas[i][1]][nVertices]++;
		}
	}
	
	@Override
	public void insereVertice(int vertice) {
		if(vertices.length == nVertices) {
			this.vertices = Arrays.copyOf(this.vertices, this.vertices.length * 2);
			this.nVertices++;
			int[][] novaMatriz = new int[matriz.length * 2][matriz.length * 2 + 1];
			for(int l = 0; l < matriz.length; l++) {
				for(int c = 0; c < matriz.length; c++) {
					novaMatriz[l][c] = matriz[l][c];
				}
				novaMatriz[l][novaMatriz.length] = matriz[l][matriz[l].length - 1];
			}
			this.matriz = novaMatriz;
			for(int x = 0; x < novaMatriz.length; x++) {
				for(int y = 0; y < novaMatriz[x].length; y++) {
					System.out.print(novaMatriz[x][y] + " ");
				}
				System.out.println();
			}
		} else {
			this.vertices[nVertices] = vertice;
			this.nVertices++;
		}
	}

	@Override
	public boolean insereAresta(int vertice1, int vertice2) {
		this.matriz[vertice1][vertice2] = 1;
		this.matriz[vertice1][this.matriz[0].length - 1]++;
		this.matriz[vertice2][vertice1] = 1;
		this.matriz[vertice2][this.matriz[0].length - 1]++;
		return true;
	}

	@Override
	public boolean ehAdjacente(int vertice1, int vertice2) {
		return this.matriz[vertice1][vertice2] == 1;
	}

	@Override
	public int getGrau(int vertice) {
		if(vertice >= nVertices) return -1;
		return this.matriz[vertice][this.matriz[0].length - 1];
	}

	@Override
	public void imprimeVizinhosDe(int vertice) {
		int vizinhosRestantes = this.matriz[vertice][this.matriz[0].length - 1];
		for(int i = 0; i < this.matriz[vertice].length - 1; i++) {
			if(vizinhosRestantes < 1) break;
			if(this.matriz[vertice][i] == 1) {
				System.out.println(vertices[i]);
				vizinhosRestantes--;
			}
		}
		
	}

	@Override
	public boolean removeVertice(int vertice) {
		return false;
	}

	@Override
	public boolean removeAresta(int vertice1, int vertice2) {
		if(vertice1 >= nVertices || vertice2 > nVertices) return false;
		this.matriz[vertice1][vertice2] = 0;
		this.matriz[vertice1][this.matriz[0].length - 1]--;
		this.matriz[vertice2][vertice1] = 0;
		this.matriz[vertice2][this.matriz[0].length - 1]--;
		return true;
	}

	@Override
	public void print() {
		for(int l = 0; l < this.nVertices; l++) {
			for(int c = 0; c < this.nVertices; c++) {
				System.out.print(matriz[l][c] + " ");
			}
			System.out.println();
		}
	}

	@Override
	public HashMap<Integer, Integer[]> buscaLargura(int vertice) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HashMap<Integer, Integer[]> buscaProfundidade(int vertice) {
		// TODO Auto-generated method stub
		return null;
	}
}

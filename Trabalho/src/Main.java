import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) {
//		System.out.println("digite o caminho do arquivo");
//		String arquivo = new Scanner(System.in).nextLine();
		Grafo g = new Grafo("C:\\\\Users\\\\mateu\\\\Downloads\\fln_pequena.net");
		//Grafo g = new Grafo("/home/100000000851731/Downloads/football.net");
		//Grafo g = new Grafo("/home/100000000851731/Downloads/football.net");
//		Grafo g = new Grafo(arquivo);
//		Grafo g = new Grafo("/home/100000000851731/euleriano.txt");
		
//		System.out.println(g.qtdArestas());
//		System.out.println(g.qtdVertices());
//		System.out.println(g.grau(32));
//		System.out.println(g.haAresta(1, 15));
//		System.out.println(g.haAresta(11, 1));
//		System.out.println(g.rotulo(5));
//		Integer[] vizinhos = g.vizinhos(3);
//		for(int i: vizinhos) {
//			System.out.println(i);
//		}
//		g.buscaLargura(2);
//		g.euleriano();
		//g.floydWarshall();
		g.dijkstra(2);
	}
}

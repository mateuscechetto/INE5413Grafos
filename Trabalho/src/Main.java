
public class Main {
	
	public static void main(String[] args) {
		Grafo g = new Grafo("C:\\\\Users\\\\mateu\\\\Downloads\\karate.net");
		System.out.println(g.qtdArestas());
		System.out.println(g.qtdVertices());
		System.out.println(g.grau(32));
		System.out.println(g.haAresta(1, 15));
		System.out.println(g.haAresta(11, 1));
		System.out.println(g.rotulo(5));
		Integer[] vizinhos = g.vizinhos(32);
		for(int i: vizinhos) {
			System.out.println(i);
		}
	}
}

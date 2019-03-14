
public class Test {

	public static void main(String[] args) {
		int[] vertices = {
				1,
				2,
				3,
		};
		int[][] arestas = {
				{1,2},
				{2,3},
				};
		GrafoHashMap g = new GrafoHashMap(vertices, arestas);
		g.print();
		
		g.insereVertice(4);
		System.out.println("insere vertice 4");
		g.print();
		g.insereAresta(1, 4);
		System.out.println("insere aresta {1,4}");
		g.print();
		System.out.print("1 adjacente a 2?  ");
		System.out.println(g.ehAdjacente(1, 2));
		System.out.print("2 adjacente a 1?  ");
		System.out.println(g.ehAdjacente(2, 1));
		System.out.print("4 adjacente a 3?  ");
		System.out.println(g.ehAdjacente(4, 3));
		System.out.println("vizinhos do 2");
		g.imprimeVizinhosDe(2);
		for(int vertice: vertices) {
			System.out.println("grau do vertice " + vertice + ": " + g.getGrau(vertice));
		}
		System.out.println("grau do vertice " + 4 + ": " + g.getGrau(4));

		
		g.removeVertice(2);
		System.out.println("remove vertice 2");
		g.print();
		
		g.removeAresta(1, 4);
		System.out.println("remove aresta {1,4}");
		g.print();
		
	}
}

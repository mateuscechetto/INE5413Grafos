import java.util.HashMap;

public class Test {

	public static void main(String[] args) {
		int[] vertices = {
				0,
				1,
				2,
				3,
		};
		int[][] arestas = {
				{0,1},
				{1,2},
				{0,2},
				{2,3}
				};
		Grafo g = new GrafoHashMap(vertices, arestas);
//		g.print();
//		
//		g.insereVertice(3);
//		System.out.println("insere vertice 3");
//		g.print();
//		g.insereAresta(0, 3);
//		System.out.println("insere aresta {0,3}");
//		g.print();
//		System.out.print("0 adjacente a 1?  ");
//		System.out.println(g.ehAdjacente(0, 1));
//		System.out.print("1 adjacente a 0?  ");
//		System.out.println(g.ehAdjacente(1, 0));
//		System.out.print("3 adjacente a 2?  ");
//		System.out.println(g.ehAdjacente(3, 2));
//		System.out.println("vizinhos do 1");
//		g.imprimeVizinhosDe(1);
//		for(int vertice: vertices) {
//			System.out.println("grau do vertice " + vertice + ": " + g.getGrau(vertice));
//		}
//		System.out.println("grau do vertice " + 3 + ": " + g.getGrau(3));
//
//		
//		g.removeVertice(1);
//		System.out.println("remove vertice 1");
//		g.print();
//		
//		g.removeAresta(0, 3);
//		System.out.println("remove aresta {0,3}");
//		g.print();
		
		HashMap<Integer, Integer[]> tabela = g.buscaLargura(0);
		
		for(int v: tabela.keySet()) {
			System.out.print(v);
			System.out.println(": " + tabela.get(v)[0] + " " + tabela.get(v)[1] + " " + tabela.get(v)[2]);
		}
		
		System.out.println("<----------------------------->");
		
		tabela = g.buscaProfundidade(0);
		
		for(int v: tabela.keySet()) {
			System.out.print(v);
			System.out.println(": " + tabela.get(v)[0] + " " + tabela.get(v)[1] + " " + tabela.get(v)[2]);
		}
	}
}

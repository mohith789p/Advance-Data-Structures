import java.util.LinkedList;
import java.util.Queue;

class BFTM {
    private int[][] adjMatrix;
    private int vertices;

    public BFTM(int size) {
        vertices = size;
        adjMatrix = new int[size][size];
    }

    public void addEdge(int u, int v) {
        adjMatrix[u][v] = 1;
        adjMatrix[v][u] = 1;
    }

    public void printBFT(int start) {
        boolean[] visited = new boolean[vertices];
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        visited[start] = true;
        System.out.print("BFT: ");
        while (!queue.isEmpty()) {
            int vertex = queue.poll();
            System.out.print(vertex + " ");
            for (int i = 0; i < vertices; i++) {
                if (adjMatrix[vertex][i] == 1 && !visited[i]) {
                    queue.add(i);
                    visited[i] = true;
                }
            }
        }
        System.out.println();
    }
    public static void main(String[] args) {
        BFTM graph = new BFTM(5);
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 2);
        graph.addEdge(1, 3);
        graph.addEdge(2, 4);
        graph.printBFT(0);
    }
}
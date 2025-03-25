import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;
import java.util.Queue;

class BFTL {
    private List<List<Integer>> adjList;
    private int vertices;

    public BFTL(int size) {
        vertices = size;
        adjList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            adjList.add(new LinkedList<>());
        }
    }

    public void addEdge(int u, int v) {
        adjList.get(u).add(v);
        adjList.get(v).add(u);
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
            for (int neighbor : adjList.get(vertex)) {
                if (!visited[neighbor]) {
                    queue.add(neighbor);
                    visited[neighbor] = true;
                }
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        BFTL graph = new BFTL(5);
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 2);
        graph.addEdge(1, 3);
        graph.addEdge(2, 4);
        graph.printBFT(0); // Start BFT from vertex 0
    }
}
import java.util.Stack;

class DFTM {
    private int[][] adjMatrix;
    private int vertices;

    public DFTM(int size) {
        vertices = size;
        adjMatrix = new int[size][size];
    }

    public void addEdge(int u, int v) {
        adjMatrix[u][v] = 1;
        adjMatrix[v][u] = 1; // For undirected graph
    }

    public void printDFT(int start) {
        boolean[] visited = new boolean[vertices];
        Stack<Integer> stack = new Stack<>();
        stack.push(start);
        System.out.print("DFT: ");
        while (!stack.isEmpty()) {
            int vertex = stack.pop();
            if (!visited[vertex]) {
                System.out.print(vertex + " ");
                visited[vertex] = true;
                for (int i = 0; i < vertices; i++) {
                    if (adjMatrix[vertex][i] == 1 && !visited[i]) {
                        stack.push(i);
                    }
                }
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        DFTM graph = new DFTM(5);
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 2);
        graph.addEdge(1, 3);
        graph.addEdge(2, 4);
        graph.printDFT(0);
    }
}
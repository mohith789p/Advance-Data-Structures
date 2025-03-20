import java.util.*;

public class DFS {
    public static void main(String[] args) {
        int v = 4;
        List<List<Integer>> adj = new ArrayList<>(v);
        boolean[] visited = new boolean[v];

        for (int i = 0; i < 4; i++) {
            adj.add(new ArrayList<>());
        }

        addEdge(adj, 0, 1);
        addEdge(adj, 0, 2);
        addEdge(adj, 1, 3);
        addEdge(adj, 2, 3);

        System.out.print("DFS starts from 0: ");
        dfs(adj, visited, 0);
        System.out.println();
    }

    static void addEdge(List<List<Integer>> adj, int u, int v) {
        adj.get(u).add(v);
        adj.get(v).add(u);
    }

    static void dfs(List<List<Integer>> adj, boolean[] visited, int start) {
        visited[start] = true;
        System.out.print(start + " ");
        for (int a : adj.get(start)) {
            if (!visited[a]) {
                visited[a] = true;
                dfs(adj, visited, a);

            }
        }
    }

}

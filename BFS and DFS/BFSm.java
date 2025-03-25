import java.util.Queue;
import java.util.LinkedList;

public class BFSm {
    public static void main(String[] args) {
        int[][] adj = { { 0, 1, 1, 0 }, { 1, 0, 0, 1 }, { 1, 0, 0, 1 }, { 0, 1, 1, 0 } };
        System.out.print("BFS Starts with 1: ");
        bfs(adj, 1);
        System.out.println();
    }

    static void bfs(int[][] adj, int start) {
        Queue<Integer> q = new LinkedList<>();
        boolean[] visited = new boolean[adj.length];
        q.add(start);
        visited[start] = true;

        while (!q.isEmpty()) {
            int v = q.poll();
            System.out.print(v + " ");
            for (int a = 0; a < adj.length; a++) {
                if (adj[v][a] == 1 && !visited[a]) {
                    q.add(a);
                    visited[a] = true;
                }
            }
        }
    }
}

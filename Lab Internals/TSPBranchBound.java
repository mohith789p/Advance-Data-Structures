import java.util.*;

public class TSPBranchBound {
    static final int N = 4;

    static int tsp(int[][] graph, boolean[] visited, int pos, int count, int cost, int ans) {
        if (count == N && graph[pos][0] > 0) {
            ans = Math.min(ans, cost + graph[pos][0]);
            return ans;
        }

        for (int i = 0; i < N; i++) {
            if (!visited[i] && graph[pos][i] > 0) {
                visited[i] = true;
                ans = tsp(graph, visited, i, count + 1, cost + graph[pos][i], ans);
                visited[i] = false;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[][] graph = {
                { 0, 10, 15, 20 },
                { 10, 0, 35, 25 },
                { 15, 35, 0, 30 },
                { 20, 25, 30, 0 }
        };
        boolean[] visited = new boolean[N];
        visited[0] = true;
        int ans = tsp(graph, visited, 0, 1, 0, Integer.MAX_VALUE);
        System.out.println("Minimum cost: " + ans);
    }
}

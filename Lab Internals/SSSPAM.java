import java.util.Arrays;

public class SSSPAM {
    private static final int INF = Integer.MAX_VALUE;

    public static void dijkstra(int[][] graph, int start) {
        int n = graph.length;
        int[] dist = new int[n];
        boolean[] visited = new boolean[n];

        Arrays.fill(dist, INF);
        dist[start] = 0;

        for (int count = 0; count < n; count++) {
            int u = minDistance(dist, visited);
            if (u == -1)
                break; // No reachable vertex left
            visited[u] = true;

            for (int v = 0; v < n; v++) {
                if (!visited[v] && graph[u][v] != 0 && dist[u] != INF) {
                    int newDist = dist[u] + graph[u][v];
                    if (newDist >= 0 && newDist < dist[v]) {
                        dist[v] = newDist;
                    }
                }
            }
        }

        System.out.println("\nVertex Distance from Source:");
        for (int i = 0; i < n; i++)
            System.out.println(i + " \t " + (dist[i] == INF ? "INF" : dist[i]));
    }

    private static int minDistance(int[] dist, boolean[] visited) {
        int minIndex = -1, min = INF;
        for (int i = 0; i < dist.length; i++)
            if (!visited[i] && dist[i] < min)
                min = dist[minIndex = i];
        return minIndex;
    }

    public static void main(String[] args) {
        int[][] graph = {
                { 0, 3, INF, 7, INF },
                { 3, 0, 2, INF, INF },
                { INF, 2, 0, 5, 1 },
                { 7, INF, 5, 0, 4 },
                { INF, INF, 1, 4, 0 }
        };

        long startTime = System.nanoTime();
        dijkstra(graph, 0);
        System.out.println("\nExecution Time: " + (System.nanoTime() - startTime) + " ns");
    }
}
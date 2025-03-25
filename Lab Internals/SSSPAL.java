import java.util.*;

public class SSSPAL {
    static class Edge {
        int target, weight;

        Edge(int target, int weight) {
            this.target = target;
            this.weight = weight;
        }
    }

    public static void dijkstra(List<List<Edge>> graph, int start) {
        int n = graph.size();
        int INF = Integer.MAX_VALUE;
        int[] dist = new int[n];
        boolean[] visited = new boolean[n];

        Arrays.fill(dist, INF);
        dist[start] = 0;

        PriorityQueue<Edge> queue = new PriorityQueue<>(Comparator.comparingInt(e -> e.weight));
        queue.offer(new Edge(start, 0));

        while (!queue.isEmpty()) {
            Edge current = queue.poll();
            int u = current.target;

            if (visited[u])
                continue;
            visited[u] = true;

            for (Edge edge : graph.get(u)) {
                int v = edge.target, weight = edge.weight;

                if (!visited[v] && dist[u] != INF) {
                    int newDist = dist[u] + weight;
                    if (newDist >= 0 && newDist < dist[v]) { // Prevent integer overflow
                        dist[v] = newDist;
                        queue.offer(new Edge(v, newDist));
                    }
                }
            }
        }

        printSolution(dist);
    }

    private static void printSolution(int[] dist) {
        System.out.println("Vertex Distance from Source:");
        for (int i = 0; i < dist.length; i++)
            System.out.println(i + " \t " + (dist[i] == Integer.MAX_VALUE ? "INF" : dist[i]));
    }

    public static void main(String[] args) {
        int size = 5;
        List<List<Edge>> graph = new ArrayList<>();

        for (int i = 0; i < size; i++)
            graph.add(new ArrayList<>());

        int[][] edges = {
                { 0, 1, 3 }, { 0, 3, 7 }, { 1, 2, 2 }, { 2, 3, 5 }, { 2, 4, 1 }, { 3, 4, 4 }
        };

        for (int[] edge : edges)
            graph.get(edge[0]).add(new Edge(edge[1], edge[2]));

        long startTime = System.nanoTime();
        dijkstra(graph, 0);
        System.out.println("\nExecution Time: " + (System.nanoTime() - startTime) + " ns");
    }
}

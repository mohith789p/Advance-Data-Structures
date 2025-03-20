import java.util.*;

class BFS{
    public static void main(String[] args) {
        int v = 4;
        List<List<Integer>> adj = new ArrayList<>(v);
        
        for(int i = 0; i < v; i++){
            adj.add(new ArrayList<>());
        }

        addEdge(adj, 0, 1);
        addEdge(adj, 0, 2);
        addEdge(adj, 1, 3);
        addEdge(adj, 2, 3);

        System.out.println("BFS starts from 3:");
        bfs(adj,1);
        System.out.println();

    }

    static void addEdge(List<List<Integer>> adj, int u, int v){
        adj.get(u).add(v);
        adj.get(v).add(u);
    }

    static void bfs(List<List<Integer>> adj, int start){
        List<Integer> q = new ArrayList<>();
        boolean[] visited = new boolean[adj.size()];
        visited[start] = true;
        q.add(start);
        while(!q.isEmpty()){
            int v = q.get(0);
            System.out.print(v + " ");
            q.remove(0);
            for(int i : adj.get(v)){
                if (!visited[i]) {
                    q.add(i);
                    visited[i] = true;                
                }
            }
        }
    }
}

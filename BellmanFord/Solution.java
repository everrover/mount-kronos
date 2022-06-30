package BellmanFord;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Solution {

    int vertexCount;
    int edgeCount;
    List<Edge> edges;
    List<To>[] adj;
    private static class Edge{
        int from, to, weight;
        public Edge(int from, int to, int weight){
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }
    private static class To{
        int to, weight;
        public To(int to, int weight){
            this.to = to;
            this.weight = weight;
        }
    }
    private boolean bellmanFordImpl(int start, int []SPT){

        Arrays.fill(SPT, Integer.MAX_VALUE);
        SPT[start] = 0;

        // build SPT
        for(int i=1; i<vertexCount; i++){
            for(Edge edge: edges){
                int from = edge.from, to = edge.to, weight = edge.weight;
                if(SPT[from] != Integer.MAX_VALUE && SPT[to] > SPT[from] + weight){
                    SPT[to] = SPT[from] + weight;
                }
            }
        }
        // -ve cycle check
        for(Edge edge: edges){
            int from = edge.from, to = edge.to, weight = edge.weight;
            if(SPT[from] != Integer.MAX_VALUE && SPT[to] > SPT[from] + weight){
                return false;
            }
        }
        return true;
    }

    private int bellmanFord(int start, int end){
        int []SPT = new int[vertexCount];
        if(bellmanFordImpl(start, SPT)){
            System.out.println("Shortest path from " + start + " to " + end + " is " + SPT[end]);
            return SPT[end];
        }else{
            System.out.println("No path from " + start + " to " + end + ". There is a negative cycle.");
            return -1;
        }
    }

    public Solution(int vertexCount, int edgeCount, List<To> []adj){
        this.vertexCount = vertexCount;
        this.edgeCount = edgeCount;
        this.edges = new LinkedList<>();
        this.adj = adj;
        for(int i=0; i<vertexCount; i++){
            for(To to: adj[i]){
                edges.add(new Edge(i, to.to, to.weight));
            }
        }
    }

    public static void main(String[] args) {
        List<To>[] adj = new List[10];
        for(int i=0; i<10; i++){
            adj[i] = new LinkedList<>();
        }
        adj[0].add(new To(1, -1));
        adj[0].add(new To(2, 4));
        adj[1].add(new To(2, 3));
        adj[2].add(new To(3, 2));
        adj[2].add(new To(4, 2));
        adj[2].add(new To(5, -3));
        adj[3].add(new To(4, -1));
        adj[4].add(new To(5, -2));
        adj[5].add(new To(6, 2));
        adj[5].add(new To(7, -1));
        adj[6].add(new To(7, 3));
        adj[7].add(new To(8, 2));
        adj[8].add(new To(9, 2));
        adj[9].add(new To(8, -1));
        Solution s = new Solution(10, 10, adj);
        int res = s.bellmanFord(0,9);
        int res1 = s.bellmanFord(3,7);
        System.out.println(res);
        System.out.println(res1);
    }
}

package PathWithMaxProbability;

import java.util.*;

public class Solution {
    private static class C{
        double cost;
        int vertex;
        public C(int vertex, double cost){
            this.vertex = vertex;
            this.cost = cost;
        }
    }
    public double maxProbability(int n, int[][] edges, double[] succProb, int start, int end) {
        double minCost[] = new double[n];
        Arrays.fill(minCost, 0.0);
        boolean isVisited[] = new boolean[n];

        List<C>[] adj = new List[n];
        for(int i=0; i<n; i++){
            adj[i] = new LinkedList<>();
        }
        for(int i=0; i<edges.length; i++){
            adj[edges[i][0]].add(new C(edges[i][1], succProb[i]));
            adj[edges[i][1]].add(new C(edges[i][0], succProb[i]));
        }

        Queue<C> pq = new PriorityQueue<>((a,b)->Double.compare(b.cost,a.cost));
        pq.add(new C(start, 1.0));
        while(!pq.isEmpty()){
            C curr = pq.poll();
            if(minCost[curr.vertex] > curr.cost || adj[curr.vertex].isEmpty()) continue;
            isVisited[curr.vertex] = true;
            minCost[curr.vertex] = curr.cost;
            for(C c: adj[curr.vertex]){
                double newCost = curr.cost*c.cost;
                if(!isVisited[c.vertex] && minCost[c.vertex] < newCost){
                    minCost[c.vertex] = newCost;
                    pq.offer(new C(c.vertex, newCost));
                }
            }
        }
        return minCost[end];
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.maxProbability(3, new int[][]{{0,1},{1,2},{0,2}}, new double[]{0.5,0.5,0.2}, 0, 2));
    }
}
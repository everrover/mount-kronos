package SixtyFour;

import java.util.*;

/**
 * Link: https://leetcode.com/problems/network-delay-time/
 * Simple variant of Dijkstra's algorithm with a provided source
 */

public class NetworkDelayTime {
    private static class T{
        public int from, to, cost;
        public T(int from, int to, int cost){
            this.from = from;
            this.to = to;
            this.cost = cost;
        }
    }
    public int networkDelayTime(int[][] times, int n, int k) {
        int [][]edges = times;
        List<T>[]adj = new List[n+1];
        for(int i=0; i<=n; i++){
            adj[i] = new LinkedList<T>();
        }
        for(int []edge: edges){
            adj[edge[0]].add(new T(edge[0], edge[1], edge[2]));
        }
        // boolean[] visited = new boolean[n];
        int[] minCost = new int[n+1];
        Arrays.fill(minCost, Integer.MAX_VALUE/2);
        Queue<T> pq = new PriorityQueue<>((a, b)->a.cost-b.cost);
        minCost[0] = 0;
        pq.add(new T(0, k, 0));
        while(!pq.isEmpty()){
            T t = pq.poll();
            if(minCost[t.to] < (minCost[t.from]+t.cost)) continue;
            minCost[t.to] = t.cost+minCost[t.from];
            for(T y: adj[t.to]){
                if(minCost[y.to] > (minCost[t.to]+y.cost)){
                    minCost[y.to] = minCost[t.to]+y.cost;
                    pq.add(new T(y.from, y.to, y.cost));
                }
            }
        }
        int res = 0;
        for(int i=1; i<minCost.length; i++){
            if(i == k) continue;
            res = Math.max(res, minCost[i]);
        }
        return res==(Integer.MAX_VALUE/2)?-1:res;
    }
}

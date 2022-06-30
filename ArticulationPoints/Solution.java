package ArticulationPoints;

import java.util.LinkedList;
import java.util.List;

public class Solution {
    private static class Edge{
        int from;
        int to;
        public Edge(int from, int to){
            this.from = from;
            this.to = to;
        }
    }
    private int visitTime, nodes;
    private boolean []isAP;
    private List<Edge> bridges;
    private int [][]edges;
    private int []low, discovery; // the earliest discovery time for all the descendents of this node, earliest discovery time of vertex
    private boolean []isVisited;
    private void findAP(int vertex, int parent){
        isVisited[vertex] = true;
        discovery[vertex] = low[vertex] = visitTime++;
        int childCount = 0;
        for(int child: edges[vertex]){
            if(child == parent) continue;
            if(!isVisited[child]){
                childCount++;
                findAP(child, vertex);
                low[vertex] = Math.min(low[vertex], low[child]);
                if(low[child] >= discovery[vertex] && parent != -1){
                    isAP[vertex] = true;
                }
                if(low[child] > discovery[vertex]){
                    bridges.add(new Edge(vertex, child));
                }
            }else{
                low[vertex] = Math.min(low[vertex], discovery[child]);
            }
        }
        if(parent == -1 && childCount > 1){
            isAP[vertex] = true;
        }
    }
    public int findAP(int start){
        this.nodes = 10;
        this.visitTime = 0;
        this.isVisited = new boolean[this.nodes];
        this.low = new int[this.nodes];
        this.discovery = new int[this.nodes];
        this.isAP = new boolean[this.nodes];
        this.bridges = new LinkedList<>();
        this.findAP(start, -1);
        return bridges.size();
    }

    public static void main(String[] args) {
        Solution s = new Solution();

        s.edges = new int[][]{ // for directed graph
                {1,2},
                {0,2},
                {0,1,3},
                {2,4,5,9},
                {3,5,7},
                {3,4,6},
                {5},
                {4,8,9},
                {7,9},
                {3,7,8}
        };

        System.out.println(s.findAP(0));
        System.out.println(s.findAP(1));
        System.out.println(s.findAP(2));
        System.out.println(s.findAP(3));
        System.out.println(s.findAP(4));
        System.out.println(s.findAP(5));
        System.out.println(s.findAP(6));
        System.out.println(s.findAP(7));
        System.out.println(s.findAP(8));
        System.out.println(s.findAP(9));
    }

}

package ArticulationPoints;

import java.util.LinkedList;
import java.util.List;

public class CCInNetwork {
    private int visitTime, nodes;
    private List<List<Integer>> bridges;
    private List<Integer> []edges;
    private int []low, discovery;
    private boolean []isVisited;
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        edges = new List[n];
        for(int i=0; i<n; i++){
            edges[i] = new LinkedList<>();
        }
        for(List<Integer> lst: connections){
            edges[lst.get(0)].add(lst.get(1));
            edges[lst.get(1)].add(lst.get(0));
        }
        bridges = new LinkedList<>();
        low = new int[n];
        discovery = new int[n];
        isVisited = new boolean[n];
        nodes = n;
        visitTime = 1;
        for(int i=0; i<n; i++){
            if(!isVisited[i]) findAP(i, -1);
        }
        return bridges;
    }
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
                if(low[child] > discovery[vertex]){
                    List<Integer> res = new LinkedList<>();
                    res.add(vertex);
                    res.add(child);
                    bridges.add(res);
                }
            }else{
                low[vertex] = Math.min(low[vertex], discovery[child]);
            }
        }
    }
}

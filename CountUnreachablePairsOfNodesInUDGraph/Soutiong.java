package CountUnreachablePairsOfNodesInUDGraph;

import java.util.HashSet;
import java.util.Set;

public class Soutiong {
    private static class SCC{
        public int curr/*, rank*/;
        public long count;
        public SCC parent;
        public SCC(int curr){
            this.curr = curr;
//            this.rank = 1;
            this.parent = this;
            this.count = 1L;
        }
    }
    private void union(SCC a, SCC b){
        SCC ap = find(a);
        SCC bp = find(b);
        if(ap == bp) return;
        else{
//            if(ap.rank > bp.rank) {
            if(ap.count > bp.count) { // using root count as rank
                bp.parent = ap;
//                ap.rank++;
                ap.count += bp.count;
            }else{
                ap.parent = bp;
//                bp.rank++;
                bp.count += ap.count;
            }
        }
    }
    private SCC find(SCC a){
        SCC root = a;
        while(root.parent != root){
            root = root.parent;
        }
        a.parent = root;
        return root;
    }
    public long countPairs(int n, int[][] edges) {
        long ans = 0;
        SCC[] sccs = new SCC[n];
        for(int i=0; i<n; i++) sccs[i] = new SCC(i);
        for(int edge[]: edges){ // In DFS we'll build adjList here
            union(sccs[edge[0]], sccs[edge[1]]);
        }
        Set<Integer> parents = new HashSet<>();
        long remainingNodes = n;
        for(int i=0; i<n; i++){
            // System.out.println(sccs[i].curr+":"+sccs[i].parent.curr);
            SCC a = find(sccs[i]);
            parents.add(a.curr);
            // DFS/BFS logic
//            if(visited[i]) continue; // need to keep track of visited nodes
//            int count = dfs(i); // DFS returns count of nodes found in traversal
//            remainingNodes -= count;
//            ans += (count*remainingNodes);
        }
        // in case of DFS or BFS, this iteration isn't required.
        for(int parent: parents){
            remainingNodes -= sccs[parent].count;
            ans += (sccs[parent].count*remainingNodes);
        }
        return ans;
    }
}

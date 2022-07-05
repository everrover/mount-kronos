package MinScoreAfterRemovalsOnTree;

import java.util.HashSet;
import java.util.Set;

public class DFSSol {
    private static class N{
        public int idx, num, xor;
        public Set<N> nodes;
        public N(int idx, int num){
            this.idx = idx;
            this.num = num;
            this.xor = num;
            this.nodes = new HashSet<>();
        }
        @Override
        public String toString(){ return "::"+idx; }
    }
    private int dfs(N[] ns, boolean visited[], int curr, int parent){
        if(visited[curr]) return ns[curr].xor;
        visited[curr] = true;
        Set<N> set = new HashSet<>();
        for(N n: ns[curr].nodes){
            if(n.idx == parent) continue;
            ns[curr].xor ^= dfs(ns, visited, n.idx, curr);
            set.addAll(ns[n.idx].nodes);
        }
        ns[curr].nodes.addAll(set);
        if(parent>=0) ns[curr].nodes.remove(ns[parent]);
        return ns[curr].xor;
    }
    public int minimumScore(int[] nums, int[][] edges) {
        N[] ns = new N[nums.length];
        for(int i=0; i<nums.length; i++){
            ns[i] = new N(i, nums[i]);
        }
        boolean []visited = new boolean[nums.length];
        for(int[] edge: edges){
            ns[edge[0]].nodes.add(ns[edge[1]]);
            ns[edge[1]].nodes.add(ns[edge[0]]);
        }
        int totalXor = dfs(ns, visited, 0, -1);
        int res = Integer.MAX_VALUE;
        for(int i=0; i<edges.length; i++){
            for(int j=i+1; j<edges.length; j++){
                int edge1[] = edges[i];
                N a = ns[edge1[0]], b = ns[edge1[1]];
                if(a.nodes.contains(b)){
                    N tmp = a;
                    a = b;
                    b = tmp;
                }
                int edge2[] = edges[j];
                N d = ns[edge2[0]], c = ns[edge2[1]];
                if(c.nodes.contains(d)){
                    N tmp = d;
                    d = c;
                    c = tmp;
                }

                if(a.nodes.contains(c)){
                    res = Math.min(res, Math.max(Math.max(a.xor^c.xor, c.xor), totalXor^a.xor)- Math.min(Math.min(a.xor^c.xor, c.xor), totalXor^a.xor));
                }else if(c.nodes.contains(a)){
                    res = Math.min(res, Math.max(Math.max(c.xor^a.xor, a.xor), totalXor^c.xor)- Math.min(Math.min(c.xor^a.xor, a.xor), totalXor^c.xor));
                }else{
                    res = Math.min(res, Math.max(Math.max(c.xor, a.xor), totalXor^c.xor^a.xor)- Math.min(Math.min(c.xor, a.xor), totalXor^c.xor^a.xor));
                }
            }
        }
        return res;
    }
    public static void main(String[] args) {
        DFSSol solution = new DFSSol();
        System.out.println(solution.minimumScore(new int[]{1,5,5,4,11,2}, new int[][]{{5,2},{5,3},{5,4},{0,4},{1,4}}));
//        System.out.println(solution.minimumScore(new int[]{26,20,1,18,5,29,13,17}, new int[][]{{5,7},{5,6},{1,7},{6,4},{7,0},{3,0},{7,2}}));
    }
}

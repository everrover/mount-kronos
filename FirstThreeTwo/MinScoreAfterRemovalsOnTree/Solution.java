package FirstThreeTwo.MinScoreAfterRemovalsOnTree;

import java.util.*;

class Solution {
    private static class T{
        public int idx, num, indegree;
        public List<T> nodes;
        public T(int idx, int num){
            this.idx = idx;
            this.num = num;
            this.indegree = 0;
            this.nodes = new LinkedList<>();
        }

        @Override
        public String toString(){
//            return idx+":"+num+":"+indegree+":"+nodes.size();
            return ":::"+idx;
        }
    }
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
        public String toString(){
//            return idx+":"+num+":"+xor+":"+nodes.size();
            return "::"+idx;
        }
    }
    public int minimumScore(int[] nums, int[][] edges) {
        T[] ts = new T[nums.length];
        int totalXor = 0;
        boolean []visited = new boolean[nums.length];
        Deque<T> dq = new LinkedList<>();
        for(int i=0; i<nums.length; i++){
            totalXor ^= nums[i];
            ts[i] = new T(i, nums[i]);
        }
        for(int[] edge: edges){
            ts[edge[0]].indegree++;
            ts[edge[0]].nodes.add(ts[edge[1]]);
            ts[edge[1]].indegree++;
            ts[edge[1]].nodes.add(ts[edge[0]]);
        }
        for(T t: ts){
            if(t.indegree == 1){
                dq.addLast(t);
            }
        }
        N[] ns = new N[nums.length];
        for(int i=0; i<nums.length; i++){
            ns[i] = new N(i, nums[i]);
        }
        while(!dq.isEmpty()){
            T curr = dq.removeFirst();
            for(T t: curr.nodes){
                if(!visited[t.idx]){
                    ns[t.idx].nodes.add(ns[curr.idx]);
                    ns[t.idx].nodes.addAll(ns[curr.idx].nodes);
                    ns[t.idx].xor ^= ns[curr.idx].xor;
                    t.indegree--;
                    if(t.indegree == 1){
                        dq.addLast(t);
                    }
                }
            }
            visited[curr.idx] = true;
        }
        int res = Integer.MAX_VALUE;
        for(int i=0; i<(edges.length-1); i++){
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
        Solution solution = new Solution();
//        System.out.println(solution.minimumScore(new int[]{1,5,5,4,11,2}, new int[][]{{5,2},{5,3},{5,4},{0,4},{1,4}}));
        System.out.println(solution.minimumScore(new int[]{26,20,1,18,5,29,13,17}, new int[][]{{5,7},{5,6},{1,7},{6,4},{7,0},{3,0},{7,2}}));
    }
}
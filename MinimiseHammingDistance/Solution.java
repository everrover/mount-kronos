package MinimiseHammingDistance;

import java.util.HashMap;
import java.util.Map;

/**
 * Link: https://leetcode.com/problems/minimize-hamming-distance-after-swap-operations/
 * Trick similar to: ../SmallestStringWithSwaps/sol.md
 * Could have applied DFS as in `SmallestStringWithSwaps`
 */

public class Solution {
    private static class N{
        public int val;
        public int rank;
        public N parent;
        private N(int val){
            this.val = val;
            this.rank = 0;
            this.parent = this;
        }
        @Override
        public String toString(){ return "::"+val; }
    }
    private N findRep(N curr){
        N node = curr;
        while(node.parent != node){
            node = node.parent;
        }
        curr.parent = node; // path compression
        return node;
    }

    private void unionSet(N set1, N set2){
        set1 = findRep(set1);
        set2 = findRep(set2);
        if(set1 == set2 || set1.val == set2.val){ return; }
        if(set1.rank > set2.rank){
            set2.parent = set1;
        }else{
            if(set1.rank == set2.rank){
                set1.rank++;
            }
            set1.parent = set2;
        }
    }

    public int minimumHammingDistance(int[] source, int[] target, int[][] allowedSwaps) {
        N []set = new N[target.length];
        for(int i=0; i<set.length; i++){ set[i] = new N(i); }
        for(int[] allowedSwap: allowedSwaps){ unionSet(set[allowedSwap[0]], set[allowedSwap[1]]); }
        boolean[] visited = new boolean[target.length];
        Map<Integer, Map<Integer, Integer>> map = new HashMap<>();
        for(int i=0; i<source.length; i++){
            N rep = findRep(set[i]);
            if(!map.containsKey(rep.val)){
                map.put(rep.val, new HashMap<>());
            }
            Map<Integer, Integer> cnts = map.get(rep.val);
            if(!cnts.containsKey(source[set[i].val])){
                cnts.put(source[set[i].val], 0);
            }
            cnts.put(source[set[i].val], cnts.get(source[set[i].val])+1);
        }
        int res = 0;
        for(int i=0; i<target.length; i++){
            N rep = findRep(set[i]);
            if(map.get(rep.val).containsKey(target[i])){
                map.get(rep.val).put(target[i], map.get(rep.val).get(target[i])-1);
                if(map.get(rep.val).get(target[i]) == 0){
                    map.get(rep.val).remove(target[i]);
                }
            }else{
                res++;
            }
        }
        return res;
    }
}
package FirstThreeTwo.MinimumCostToCutStick;

import java.util.Arrays;

public class Solution {
    public int minCost(int n, int[] cuts) {
        Arrays.sort(cuts);
        int [][]dp  = new int[cuts.length][cuts.length];
        for(int []d: dp){
            Arrays.fill(d, -1);
        }
        return recurse(cuts, dp, 0, n, 0, cuts.length-1);
    }
    private int recurse(int[] cuts, int[][] dp, int start, int end, int lIdx, int rIdx){
        if(dp[lIdx][rIdx] != -1) return dp[lIdx][rIdx];
        int res = Integer.MAX_VALUE;
        int rodSize = end-start;
        for(int i=lIdx; i<=rIdx; i++){
            int leftCut = i==lIdx?0:recurse(cuts, dp, start, cuts[i], lIdx, i-1);
            int rightCut = i==rIdx?0:recurse(cuts, dp, cuts[i], end, i+1, rIdx);
            res = Math.min(res, rodSize+leftCut+rightCut);
        }
        dp[lIdx][rIdx] = res;
        return res;
    }
}
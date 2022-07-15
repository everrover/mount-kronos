package SixtyFour.LastStoneWeight;

import java.util.Arrays;

public class Solution {
    public int lastStoneWeightII(int[] stones) {
        final int n = stones.length;
        int [][]dp = new int[n][n];
        for(int []d: dp) Arrays.fill(d, -1);
        findSol(stones, dp, 0, n-1);
        return dp[0][n-1];
    }

    private int findSol(int []stones, int [][]dp, int i, int j){
        if(i>j) return 0;
        if(dp[i][j] != -1) return dp[i][j];
        if(i == j) {
            dp[i][j] = stones[i];
            return stones[i];
        }
        int res = Integer.MAX_VALUE;
        for(int split = i; split<j; split++){
            int left = findSol(stones, dp, i, split);
            int right = findSol(stones, dp, split+1, j);
            res = Math.min(res, Math.abs(left-right));
        }
        // res = Math.min(res, Math.abs(stones[j]-findSol(stones, dp, i, j-1)));
        dp[i][j] = res;
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().lastStoneWeightII(new int[]{6,6,3,6,3,2,5,1}));
    }
}
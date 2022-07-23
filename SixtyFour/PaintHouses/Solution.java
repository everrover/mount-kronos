package SixtyFour.PaintHouses;

import java.util.Arrays;

public class Solution {
    public int minCost(int[][] costs) {
        int n = costs.length;
        int [][]dp = new int[n][n];
        for(int []d: dp) Arrays.fill(d, -1);
        return findSol(costs, dp, 0, n-1);
    }

    private int findSol(int [][]costs, int [][]dp, int i, int j){
        if(i>j) return 0;
        if(dp[i][j] != -1) return dp[i][j];
        if(i == j) {
            dp[i][j] = costs[i][0]+costs[i][1];
            return costs[i][0]+costs[i][1];
        }
        int res = Integer.MAX_VALUE;
        for(int split = i; split<j; split++){
            int left = findSol(costs, dp, i, split);
            int right = findSol(costs, dp, split+1, j);
            res = Math.min(res, left+right);
        }
        dp[i][j] = res;
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().minCost(new int[][]{{1,1,1},{1,1,1},{1,1,1}}));
    }
}

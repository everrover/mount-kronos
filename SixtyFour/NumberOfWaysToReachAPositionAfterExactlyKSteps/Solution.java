package SixtyFour.NumberOfWaysToReachAPositionAfterExactlyKSteps;

import java.util.Arrays;

public class Solution {
    // https://leetcode.com/problems/number-of-ways-to-reach-a-position-after-exactly-k-steps/
    private final int MOD = (int)1e9+7;
    private int util(int d, int k, int dp[][]){
        if(dp[d][k] != -1) return dp[d][k];
        if(k <= d){
            int res = d==k?1:0;
            if(k == d) dp[d][k]=res;
            return res;
        }
        long res = (util(d+1, k-1, dp)+util(Math.abs(d-1), k-1, dp))%MOD;
        return dp[d][k]=(int)res;
    }
    public int numberOfWays(int startPos, int endPos, int k) {
        int [][]dp = new int[1001][1001];
        for(int []d: dp) Arrays.fill(d, -1);
        return util(Math.abs(endPos-startPos), k, dp);
    }
}

package NumberOfDistinctRollSeq;

import java.util.Arrays;

class Solution {
    static long dp[][][];
    static int mappings[][] = new int[][]{{},{2,3,4,5,6},{1,3,5},{1,2,4,5},{1,3,5},{1,2,3,4,6},{1,5}};
    public int distinctSequences(int n) {
        long ans = 0;
        dp = new long[n+1][7][7];
        for (long[][] d : dp) {
            for (long[] dp : d) {
                Arrays.fill(dp, -1);
            }
        }
        for(long []d: dp[0]){ Arrays.fill(d, 0); }
        for(long []d: dp[1]){ Arrays.fill(d, 1); }
        for(int i=1; i<=6; i++){
            long count = bt(n, i, 0);
            // dp[n][i][0] = count;
            ans+=count;
            ans%=1000000007;
        }
        return (int)ans;
    }

    public long bt(int n, int number, int prev){
        if(dp[n][number][prev] != -1) return dp[n][number][prev];
        else if(n == 0) return 0;
        else if(n == 1) return 1;
        int res = 0;
        for(int to: mappings[number]){
            if(number == to || prev == to) continue;
            res += bt(n-1, to, number);
            res %= 1000000007;
        }
        dp[n][number][prev] = res;
        return res;
    }
}

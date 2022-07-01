package CountNumberOfHouses;

import java.util.Arrays;

public class Solution {
    public int countHousePlacements(int n) {
        long [][]dp = new long[n+1][2];
        dp[0][0] = 0; dp[0][1] = 0; dp[1][0] = 1; dp[1][1] = 2;
        recurse(n, 1, dp);
        return (int)((dp[n][1] * dp[n][1])%1000000007);
    }
    private long recurse(int curr, int canBuild, long[][] dp){
        if(curr <= 0) return 0;
        if(dp[curr][canBuild] != 0) return dp[curr][canBuild];
        long res = 0;
        if(canBuild == 1){
            res = recurse(curr-1, 1, dp)+recurse(curr-1, 0, dp);
        }else{
            res = recurse(curr-1, 1, dp);
        }
        dp[curr][canBuild] = res%1000000007;
        return dp[curr][canBuild];
    }

    public int countHousePlacementsBrute(int n) {
        long [][]dp = new long[n+1][4];
        // 0 - none placed in the same column
        // 1 - second not placed in the same column
        // 2 - first not placed, second placed in the same column
        // 3 - both placed in the same column
        Arrays.fill(dp[0], 0);
        Arrays.fill(dp[1], 1);
        recurse(false, false, n, dp);
        recurse(true, false, n, dp);
        recurse(false, true, n, dp);
        recurse(true, true, n, dp);
        return (int)((dp[n][0]+dp[n][1]+dp[n][2]+dp[n][3])%1000000007);
    }

    private long recurse(boolean one, boolean two, int curr, long[][] dp) { // for column at `curr` position and can build one or two houses
        if (curr == 0) return 0;
        int o = ((one ? 2 : 0) + (two ? 1 : 0));
        if (dp[curr][o] != 0) return dp[curr][o];
        long res = 0;
        if (one && two) {
            res = recurse(false, false, curr - 1, dp); // 11 -> 00
        } else if (one && !two) {
            res = recurse(false, true, curr - 1, dp) + recurse(false, false, curr - 1, dp); // 10 -> 01, 00
        } else if (!one && two) {
            res = recurse(true, false, curr - 1, dp) + recurse(false, false, curr - 1, dp); // 01 -> 10, 00
        } else {
            res = recurse(true, false, curr - 1, dp) + recurse(false, true, curr - 1, dp) // 00 -> 11, 01, 10, 00
                    + recurse(true, true, curr - 1, dp)
                    + recurse(false, false, curr - 1, dp);
        }
        dp[curr][o] = res % 1000000007;
        return dp[curr][o];
    }
}

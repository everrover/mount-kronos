package CountingcCoins_II;

import java.util.Arrays;

class SolutionTD {
    public int change(int amount, int[] coins) {
        // Arrays.sort(coins);
        int dp[][] = new int[coins.length][amount+1];
        for(int d[]: dp){
            Arrays.fill(d, -1);
            d[0] = 1;
        }
        return func(coins, coins.length-1, amount, dp);
    }

    private int func(int []coins, int idx, int amount, int[][]dp){
        if(amount < 0 || idx < 0) return 0;
        if(dp[idx][amount] != -1) return dp[idx][amount];
        int res;
        if(amount == 0){
            res = 1;
        }else{
            res = func(coins, idx, amount-coins[idx], dp) + func(coins, idx-1, amount, dp);
        }
        dp[idx][amount] = res;
        return res;
    }
}


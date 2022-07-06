package FirstThreeTwo.CountingcCoins_II;

import java.util.Arrays;

class Solution2 {
    public int change(int amount, int[] coins) {
        Arrays.sort(coins);
        int dp[] = new int[amount+1];
        dp[0] = 1;
        for(int j=1; j<=amount; j++){
            dp[j] = (j>=coins[0]?dp[j-coins[0]]:0);
        }
        for(int i=1; i<coins.length; i++){
            for(int j=1; j<=amount; j++){
                dp[j] = dp[j]+(j>=coins[i]?dp[j-coins[i]]:0);
            }
        }
        return dp[amount];
    }
}

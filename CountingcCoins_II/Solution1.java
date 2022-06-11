package CountingcCoins_II;

import java.util.Arrays;

class Solution1 {
    public int change(int amount, int[] coins) {
        // Arrays.sort(coins);
        int dp[] = new int[amount + 1];
        dp[0] = 1;
        for (int coin : coins) {
            for (int j = coin; j <= amount; j++) {
                dp[j] += dp[j - coin];
            }
        }
        return dp[amount];
    }
}

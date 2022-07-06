# [518. Counting Coins II](https://leetcode.com/problems/coin-change-2)

**#dynamic-programming**
**#unbounded-knapsack**

For each available coin we have two possibilities.

Either we include a coin or we don't, all while utilizing the complete capacity of our 
**knapsack**. Earlier I thought that sorted array was needed, but it wasn't. Essentially it's a 
variant of unbounded(we can put in as many elements as we require in our knapsack) knapsack 
algorithm.

Recursive solution for same was,
```
func(amount, coin_idx) = func(amount-coin[coin_idx], coin_idx)+func(amount, coin_idx-1), amound>0
                         = 1, amount = 0
                         = 0, amount < 0
```

### Top-down approach

```java
class Solution {
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
```

TC: `O(coins.length*amount)`

MC: `O(coins.length*amount)`

### Bottom-up approach

```java
class Solution {
    public int change(int amount, int[] coins) {
        Arrays.sort(coins);
        int dp[][] = new int[coins.length][amount+1];
        for(int d[]: dp){
            d[0] = 1;
        }
        for(int j=1; j<=amount; j++){
            dp[0][j] = (j>=coins[0]?dp[0][j-coins[0]]:0);
        }
        for(int i=1; i<coins.length; i++){
            for(int j=1; j<=amount; j++){
                dp[i][j] = dp[i-1][j]+(j>=coins[i]?dp[i][j-coins[i]]:0);
            }
        }
        return dp[coins.length-1][amount];
    }
}
```

TC: `O(coins.length*amount)`

MC: `O(coins.length*amount)`

Since, for a previous layer, we are only using value at current index... our job can be done in 
one single layer of `dp` _array_. 

```java
class Solution {
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
```

Even better coding in terms of optimization...
```java
class Solution {
    public int change(int amount, int[] coins) {
        // Arrays.sort(coins);
        int dp[] = new int[amount+1];
        dp[0] = 1;
        for(int coin: coins){
            for(int j=coin; j<=amount; j++){
                dp[j] += dp[j-coin];
            }
        }
        return dp[amount];
    }
}
```

Here MC is `O(amount)`.



Cheers guyz.

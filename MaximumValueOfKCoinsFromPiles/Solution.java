package MaximumValueOfKCoinsFromPiles;

import java.util.Arrays;
import java.util.List;

class Solution {
    public int maxValueOfCoins(List<List<Integer>> piles, int k) {
        int [][]dp = new int[piles.size()][k+1]; int i=0;
        for(int d[]: dp){
            Arrays.fill(d, -1);
        }
        return recurse(piles, piles.size()-1, k, dp);
    }

    private int recurse(List<List<Integer>> pileSum, int cp, int k, int [][]dp){
        if(cp<0 || k<0) return 0;
        else if(dp[cp][k] != -1) return dp[cp][k];

        List<Integer> currPile = pileSum.get(cp);
        final int recTill = Math.min(k, currPile.size());
        int res = 0, sum = 0;
        res = recurse(pileSum, cp-1, k, dp);
        for(int i=1; i<=recTill; i++){
            sum += currPile.get(i-1);
            res = Math.max(
                    recurse(pileSum, cp-1, k-i, dp)+sum,
                    res
            );
        }

        dp[cp][k] = res;
        return res;
    }
}

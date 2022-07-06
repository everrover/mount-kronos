package FirstThreeTwo.MaximumValueOfKCoinsFromPiles;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PrefixSummedDP {
    public int maxValueOfCoins(List<List<Integer>> piles, int k) {
        int [][]dp = new int[piles.size()][k+1]; int i=0;
        List<List<Integer>> pileSum = new ArrayList<>();
        for(List<Integer> pile: piles){
            Arrays.fill(dp[i++], -1);
            int sum=0;
            List<Integer> ps = new ArrayList<>();
            ps.add(sum);
            for(int p: pile){
                sum += p;
                ps.add(sum);
            }
            pileSum.add(ps);
        }

        return recurse(pileSum, piles.size()-1, k, dp);
    }

    private int recurse(List<List<Integer>> pileSum, int cp, int k, int [][]dp){
        if(cp<0 || k<0) return 0;
        else if(dp[cp][k] != -1) return dp[cp][k];

        List<Integer> currPile = pileSum.get(cp);
        final int size = currPile.size();
        final int recTill = Math.min(k, size-1);
        int res = Integer.MIN_VALUE;

        for(int i=0; i<=recTill; i++){
            res = Math.max(
                    recurse(pileSum, cp-1, k-i, dp)+currPile.get(i),
                    res
            );
        }

        dp[cp][k] = res;
        return res;
    }
}
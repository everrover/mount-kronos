package FirstThreeTwo.ChampagneTower;

import java.util.Arrays;

class Solution {
    public double champagneTower(int poured, int queryRow, int queryGlass) {
        double []dp = new double[queryRow+2];
        Arrays.fill(dp, 0);
        dp[0] = poured;
        for(int i=0; i<queryRow; i++){
            for(int j=i; j>=0; j--){
                // System.out.print(dp[j]+",");
                if(dp[j]<1) {
                    dp[j] = 0;
                }else{
                    dp[j+1] += (dp[j]-1)/2;
                    dp[j] = (dp[j]-1)/2;
                }
            }
            // System.out.println();
        }
        return dp[queryGlass]>=1?1.0:dp[queryGlass];
    }
}
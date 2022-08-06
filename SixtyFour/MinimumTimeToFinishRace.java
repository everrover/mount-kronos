package SixtyFour;

import java.util.Arrays;

public class MinimumTimeToFinishRace {

    /*
     * Link: https://leetcode.com/problems/minimum-time-to-finish-the-race/
     *
     */

    private int minimumFinishTimeBrute(int[][] tires, int changeTime, int numLaps) {
        // Brute DP - works in O(N^3) and also the `minAtIth[i]` always exceeds int capacity
        //
        int minAtIth[] = new int[numLaps];
        int t[] = new int[tires.length];
        Arrays.fill(minAtIth, Integer.MAX_VALUE);
        for(int i=0; i<numLaps; i++){
            for(int j=0; j<tires.length; j++){
                minAtIth[i] = Math.min(minAtIth[i], t[j]=(t[j]+tires[j][0]*(int)Math.pow(tires[j][1], i)));
            }
        }
        int dp[][] = new int[numLaps-1][numLaps];
        for(int i=0; i<numLaps; i++){
            dp[0][i] = minAtIth[i];
        }
        for(int i=1; i<(numLaps-1); i++){
            for(int j=i; j<numLaps; j++){
                int put = Integer.MAX_VALUE;
                for(int k=i-1; k<j; k++){
                    put = Math.min(put, dp[i-1][k]+changeTime+minAtIth[j-k-1]);
                }
                dp[i][j] = put;
            }
        }

        int res = Integer.MAX_VALUE;
        for(int i=1; i<numLaps; i++){
            res = Math.min(dp[i-1][numLaps-1], res);
        }
        return res;
    }

    //
    private int minimumFinishTime(int[][] tires, int changeTime, int numLaps){
        int minAtIth[] = new int[numLaps];
        Arrays.fill(minAtIth, Integer.MAX_VALUE);
        for(int j=0; j<tires.length; j++){
            int sum = 0;
            for(int i=0; i<numLaps; i++){
                int currlapspend = tires[j][0]*(int)Math.pow(tires[j][1], i);
                if(currlapspend > tires[j][0]+changeTime) break;
                sum+=currlapspend;
                minAtIth[i] = Math.min(minAtIth[i], sum);
            }
        }

        long []dp = new long[numLaps];
        dp[0] = minAtIth[0];
        for(int i=1; i<numLaps; i++){
            dp[i] = minAtIth[i];
            for(int j=0; j<i; j++){
                dp[i] = Math.min(dp[i], dp[j]+changeTime+dp[i-j-1]);
            }
        }
        return (int)dp[numLaps-1];
    }

    public static void main(String[] args) {
        MinimumTimeToFinishRace minimumTimeToFinishRace = new MinimumTimeToFinishRace();
        System.out.println(minimumTimeToFinishRace.minimumFinishTime(new int[][]{{1,10},{2,2},{3,4}}, 6, 5));
    }
}

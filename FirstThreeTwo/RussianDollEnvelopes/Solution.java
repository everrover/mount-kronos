package FirstThreeTwo.RussianDollEnvelopes;

import java.util.Arrays;

// https://leetcode.com/problems/russian-doll-envelopes/
// Have used LIS over here...

public class Solution {
    private int binarySearch(int [][]dp, int start, int end, int seek[]){
        int l = start, r = end-1, mid;
        while(l<=r){
            mid = (l+r)/2;
            if((dp[mid][0]<=seek[0] && dp[mid][1]<=seek[1]) && (seek[0]<dp[mid+1][0] && seek[1]<dp[mid+1][1])){
                return mid;
            }else if(seek[0]<dp[mid][0] || (seek[0] == dp[mid][0] && seek[1]<dp[mid][1])){
                r = mid-1;
            }else{
                l = mid+1;
            }
        }
        return end;
    }
    private int LIS(int [][]envelopes){
        int size = 0;
        int [][]dp = new int[envelopes.length][];
        for(int i=0; i<dp.length; i++){
            dp[i] = new int[2];
            dp[i][0] = dp[i][1] = Integer.MAX_VALUE;
        }
        for(int j=0; j<envelopes.length; j++){
            int idx = binarySearch(dp, 0, size, envelopes[j]);
            dp[idx] = envelopes[j];
            if(idx == size) size++;
            System.out.print(idx+"::");
            for(int []d: dp){
                System.out.print(d[0]+":"+d[1]+"::");
            }
            System.out.println();
        }
        return size;
    }
    public int maxEnvelopes(int[][] envelopes) {
        Arrays.sort(envelopes, (a, b)->(a[0]==b[0]?(a[1]-b[1]):(a[0]-b[0])));
        return LIS(envelopes);
    }
}

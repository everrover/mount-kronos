package SixtyFour.CountIdealArrays;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class Solution {
    static private final int MOD = (int)1e9+7;
    public int idealArraysDp(int n, int maxValue) { // throws of a big ass TLE #1
        long res=0;
        long [][]dp = new long[maxValue][n];
        for(int i=1; i<=maxValue; i++){
            res += idealArraysRec(i, n-1, dp, maxValue)%MOD;
            res %= MOD;
        }
        return (int)res;
    }

    private long idealArraysRec(int curr, int n, long [][]dp, final int MAXVAL){
        if(dp[curr-1][n] != 0)
            return dp[curr-1][n];
        else if(n == 1) {
            // System.out.println(curr+":"+n+":"+MAXVAL/curr);
            dp[curr-1][1] = MAXVAL/curr;
            return dp[curr-1][1];
        }
        long res = 0;
        for(int l=curr; l<=MAXVAL; l+=curr){
            res += (idealArraysRec(l, n-1, dp, MAXVAL)%MOD);
            res %= MOD;
        }
        // for(long []d: dp){
        //     for(long l: d){
        //         System.out.print(l+",");
        //     }
        //     System.out.println();
        // }
        dp[curr-1][n] = res;
        return res;
    }

    private static long[][] pascal;

    public int idealArraysWithQ(int n, int maxValue){// #2
        if(pascal == null) buildPascals(10015,15);
        long res = 0;
        Queue<Integer> q = new LinkedList<>();
        for(int i=1; i<=maxValue; i++){
            q.add(i);
        }
        int arrSize = 1;
        while (q.size() > 0 && arrSize <= n) {
            int cnt = q.size();
            res += cnt*nCk(n-1, arrSize-1);
            res %= MOD;
            while(cnt-->0){
                int curr = q.poll();
                for(int l=curr*2; l<=maxValue; l+=curr){
                    q.add(l);
                }
            }
            arrSize++;
        }
        return (int)res;
    }

    private long nCk(int m, int n) {
        return pascal[m][n];
    }

    private void buildPascals(int n, int k){ // for nCk
        pascal = new long[n+1][k+1];
        for(int i=0; i<=n; i++){
            pascal[i][0] = 1;
            int MIN = Math.min(i, k);
            for(int j=1; j<=MIN; j++){
                pascal[i][j] = (pascal[i-1][j-1]+pascal[i-1][j])%MOD;
            }
        }
    }


    public int idealArraysWQ(int n, int maxValue){// #3 - MLE
        if(pascal == null) buildPascals(10015,15);
        long res = 0;
        Queue<Integer> q = new LinkedList<>();
//        Map<Integer, Integer> map = new HashMap<>();
        int []map = new int[maxValue+1];
        for(int i=1; i<=maxValue; i++){
            q.add(i);
//            map.put(i, 1);
            map[i] = 1;
        }
        int arrSize = 1;
        while (q.size() > 0 && arrSize <= n) {
            int qsize = q.size();
            int cnt = 0;
            int []currs = new int[qsize];
            while((--qsize)>=0){
                currs[qsize] = q.poll();
//                cnt += map.get(currs[qsize]);
                cnt += map[currs[qsize]];
//                map.put(currs[qsize], 0);
                map[currs[qsize]] = 0;
            }
            for(int curr: currs){
                for(int l=curr*2; l<=maxValue; l+=curr){
                    q.add(l);
                    map[l]++;
                }
            }
            res += cnt*nCk(n-1, arrSize-1);
            res %= MOD;
            arrSize++;
        }
        return (int)res;
    }

    private int N, MAXVAL;
    public int idealArrays(int n, int maxValue) { // throws of a big ass TLE #1
        if(pascal == null) buildPascals(10000,15);
        long res=0;
        N = n; MAXVAL = maxValue;
        long [][]dp = new long[maxValue+1][16];
        for(int i=1; i<=maxValue; i++){
            res += idealArraysRec(i, 0, dp)%MOD;
            res %= MOD;
        }
        return (int)res;
    }

    private long idealArraysRec(int curr, int n, long [][]dp){
        if(dp[curr][n] != 0) return dp[curr][n];
        long res = 0;
        for(int l=2*curr; l<=MAXVAL; l+=curr){
            res += (idealArraysRec(l, n+1, dp)%MOD);
            res %= MOD;
        }
        res += nCk(N-1, n);
        res %= MOD;
        dp[curr][n] = res;
        return res;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
//        System.out.println(s.idealArrays(2, 5));
        System.out.println(s.idealArrays(5, 5));
        System.out.println(s.idealArrays(184, 389));
    }

}

import java.util.Arrays;

public class Lo{
    private int N3;
    private int[] A;
    private long[][][] dp;
    private long[] post;
    private long rec(int idx, int c, int c1, int c2, long sum1, long sum2){
        if(idx >= A.length) return 0;
        if(dp[idx][c1][c2] != -1) return dp[idx][c1][c2];
        long res = 0;
        if(c == N3){
            res = rec(idx+1, c, c1==N3?c1:(c1+1), c1==N3?(c2+1):c2, c1==N3?sum1:(sum1+A[idx]), c1==N3?(sum2+A[idx]):sum2);
        }else if((c1+c2) < 2*N3){
            res = Math.max(
                    rec(idx+1, c, c1==N3?c1:(c1+1), c1==N3?(c2+1):c2, c1==N3?sum1:(sum1+A[idx]), c1==N3?(sum2+A[idx]):sum2), // curr not restricted
                    rec(idx+1, c+1, c1, c2, sum1, sum2)
            );
        }else{
            // res = sum1 - (sum2 + post[idx]);
            res = sum1-sum2;
        }
        dp[idx][c1][c2] = res;
        return res;
    }
    long colosseum(int N,int A[]) {
        N *= 3;
        dp = new long[N][(N/3)+1][(N/3)+1];
        post = new long[N+1];
        this.A = A;
        this.N3 = N/3;
        for(long [][]d: dp){
            for(long []dd: d){
                Arrays.fill(dd, -1);
            }
        }
        // for(int []dd: dp[0]){
        //     Arrays.fill(d, -1);
        // }
        // post[N] = 0;
        // for(int i=N-1; i>=0; i--){
        //     post[i] = post[i+1]+A[i];
        // }
        return rec(0, 0, 0, 0, 0L, 0L);
    }

    public static void main(String[] args) {
        Lo lo = new Lo();
        System.out.println(lo.colosseum(2, new int[]{1, 3, 5, 2, 1, 1}));
    }
}
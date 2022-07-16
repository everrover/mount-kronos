package SixtyFour;

import java.util.Arrays;

/**
 * 1735. Count Ways to Make Array With Product
 * Link: https://leetcode.com/problems/count-ways-to-make-array-with-product/
 * Have used some math to solve this problem.
 * ```
 * For filling `n` identical elements in `m` different buckets:
 * = (n+(m-1))! / (n! * (m-1)!) = C(n+m-1, m-1)
 * ```
 * Steps:
 * - Find prime factors of the number `n` in the query
 * - For each prime factor, find the number of ways to fill the buckets with that prime factor
 *   For `m` count of a prime factor & `k` positions, apply `C(k+m-1, m-1)` and multiply to result
 *
 * For the given constraints I've found the pascal's triangle till depth of 10015.
 * The max number of prime factor count is 14 since 2^14 > 20000. Take 15 just for ease.
 * And since C(10015, 10000) = C(10015, 15), our work can be done with pre-computing all possible values for until C(10015, 15)
 * Lastly, primes have been pre-computed till 10000, using Sieve of Eratosthenes.(Our constraints are less than 10^6)
 */

public class CountWaysToMakeArrayWithProduct {
    private static boolean []isPrime = null;
    private static int []primes;
    private final long MOD = (int)1e9+7;
    private static long [][]pascal;

    private static void createPrimes(){
        int count = 0;
        isPrime = new boolean[10001];
        Arrays.fill(isPrime, true);
        for(int i=2; i<=10001; i++){
            if(!isPrime[i]) continue;
            count++;
            for(int j=i*i; j<=10001; j+=i){ // init? values for sequence i*2, i*3, i*4 ... i*(i-1) will already be marked by previous computations
                isPrime[j] = false;
            }
        }
        primes = new int[count];
        for(int i=2, j=0; i<=10001; i++){
            if(isPrime[i]) primes[j++] = i;
        }
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

    private int findQResult(final int N, final int K){
        int num = K;
        long res = 1L;
        for(int prime: primes){
            if(prime > num) break;
            int factors = 0;
            while(num % prime == 0){
                num /= prime;
                factors++;
            }
            res = (res*pascal[factors+N-1][factors])%MOD;
        }
        return (int)res;
    }

    public int[] waysToFillArray(int[][] queries) {
        if(isPrime == null || primes == null){
            createPrimes();
            buildPascals(10015,15);
        }
        int []res = new int[queries.length];
        for(int i=0; i<queries.length; i++){
            res[i] = findQResult(queries[i][0], queries[i][1]);
        }
        return res;
    }

    public static void main(String[] args) {
        CountWaysToMakeArrayWithProduct c = new CountWaysToMakeArrayWithProduct();
        int [][]queries = {{2,3},{2,5},{2,4},{2,2},{2,1},{2,0}};
        int []res = c.waysToFillArray(queries);
        for(int i: res) System.out.println(i);
    }
}

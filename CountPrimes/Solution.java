package CountPrimes;

/**
 * Link: https://leetcode.com/problems/count-primes
 * Simply have used a brute approach. Runs in `O(n)` TC and MC.
 */


public class Solution {
    public int countPrimes(int n) {
        if(n<3) return 0;
        boolean primes[] = new boolean[n+1];
        int ans = n/2;
        for (int i = 3; i * i < n; i += 2) {
            if (primes[i])
                continue;

            for (int j = i * i; j < n; j += 2 * i) {
                if (!primes[j]) {
                    --ans;
                    primes[j] = true;
                }
            }
        }
        return ans;
    }
}

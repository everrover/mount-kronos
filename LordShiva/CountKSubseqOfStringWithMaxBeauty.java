package LordShiva;

import java.util.Arrays;

public class CountKSubseqOfStringWithMaxBeauty {
  /**
   * https://leetcode.com/problems/count-k-subsequences-of-a-string-with-maximum-beauty/description/
   * 
   * Just the inferred word `math` in problem gave it away. Constraints and `count` were enough.
   * 
   * Basically if more of higher freq characters are used, then beauty will be more. So we need to find the highest freq character and use it as much as possible.
   * This is basically a selection operation. So we can use greedy approach.
   * 
   * Now, all characters in subseq are unique. So we need to find the highest freq character and use it as much as possible. Then we need to find the next highest freq character and use it as much as possible. And so on.
   * If we are limited to `k` characters, we'll iterate in sorted freq array from 0 to k-1.
   * For each character freq there are two possibilities:
   * - if freq is greater than lastUsed, we'll multiply it with res. Since number of ways to select it is freq[i]
   * - if freq is equal to lastUsed, then there are several possibilities of picking the char(s). If we have `lastCnt` number of chars with `lastUsed` freq,
   * then we select C(lastCnt, k[remaining]) number of ways and for those we have `freq[i]` number of char options to select from.
   * 
   * TC: O(n) SC: O(1)
   * #greedy #math
   */

  final static long MOD = (int)(1e9+7);

  public int countKSubsequencesWithMaxBeauty(String s, int k) {
    char []str = s.toCharArray();
    int[] freq = new int[26];
    Arrays.fill(freq, 0);
    // for(int i=0; i<freq.length; i++) freq[i][0] = i;
    for(char c: str) { freq[(char)(c-'a')]++; }
    Arrays.sort(freq); // don't know why Lambdas weren't working
    for (int i = 0; i < freq.length / 2; i++) {
      int temp = freq[i];
      freq[i] = freq[freq.length - i - 1];
      freq[freq.length - i - 1] = temp;
    }
    if(k > 26 || freq[k-1] == 0) return 0;
    long res = 1, comb = 1;
    int lastUsed = freq[k-1], lastCnt = 0;
    for(int i=0; i<26; i++){
      if(freq[i] > lastUsed){
        k--;
        res = (res * freq[i]) % MOD;
      }else if(freq[i] == lastUsed){
        lastCnt++;
      }else{
        break;
      }
    }
    for(int i=0; i<k; i++){ // nCr calculation
      comb = comb * (lastCnt - i) / (i+1);
      res = res * lastUsed % MOD; // Tried using Math.pow but MOD wasn't getting applied due to big results
    }
    return (int)(res*comb%MOD);
  }
}
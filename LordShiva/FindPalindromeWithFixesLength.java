package LordShiva;

public class FindPalindromeWithFixesLength {

  /**
   * https://leetcode.com/problems/find-palindrome-with-fixed-length/description/
   * 
   * Enumerated on the basis of first half of palindrome. If palindrome length is 6, 999 is used as an
   * `adder` to seq number for finding the palindrome's first half. Limit for palindrome's first half is `adder+1`
   * And then the second half is same number but in reverse.
   * If length is odd, we need to remove the middle digit from the palindrome's right half.
   * 
   * TC: O(queries.length) SC: O(queries.length)
   * 
   * #math #array
   */


  public long[] kthPalindrome(int[] queries, final int intLength) {
    long []res = new long[queries.length];
    long count = intLength/2, tmpc;
    long adder = 0;
    tmpc = count+(intLength%2==0?0:1);
    while(--tmpc>0) adder = adder*10+9;
    final long LIMIT = adder*10+10;
    for(int i=0; i<queries.length; i++){
      if(queries[i]+adder>=LIMIT) res[i] = -1;
      else {
        tmpc = count;
        long r = res[i] = queries[i]+adder;
        if(intLength%2!=0) r/=10; // remove the middle digit if length is odd
        while(tmpc-->0) {
          res[i] = (long)((res[i]*10)+(r%10));
          r/=10;
        }
      }
    }
    return res;
  }
}

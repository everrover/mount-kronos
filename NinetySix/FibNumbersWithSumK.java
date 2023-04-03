package NinetySix;

import java.util.TreeSet;

public class FibNumbersWithSumK {
  /**
   * https://leetcode.com/problems/find-the-minimum-number-of-fibonacci-numbers-whose-sum-is-k/
   * Hint:: A larger fib number is formed with smaller ones.
   * Since we are given that `k` always forms with a sum of fib numbers(although it would always be a case since all fib numbers are composed of f0's and f1's), we can pick the largest fib number smaller than remaining sum and use it to form sum.
   * F5 = F4+F3 = f3+f2+f3 = 2f3+f1+f0 = 2f2+2f1+f1+f0 = 2f1+2f0+3f1+f0 = 5f1+3f0
   * since f(large) is always composed of smaller ones(f1's and f0's), largest possible fib number will chip away largest numbers of f1's and f0's and will leave remaining sum(formed with f0's and f1's) for remaining largest fib number
   * p.s. f0 = 0, f1 = 1
   */

  private static TreeSet<Integer> fib;
  public int findMinFibonacciNumbers(int k) {
    if(fib == null){
      fib = new TreeSet<>();
      int a = 0, b = 1;
      fib.add(a); fib.add(b);
      while(b < (1e9+1)){
        int tmp = b+a;
        fib.add(tmp);
        a = b;
        b = tmp;
      }
    }
    int ans = 0;
    while(k>0){
      k -= fib.floor(k); // not handled null since, we are given that k forms from sum of fib numbers
      ans++;
    }
    return k==0?ans:-1;
  }

}

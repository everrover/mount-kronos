package LordShiva;

import java.util.Arrays;

public class StrangePrinter {
  /**
   * https://leetcode.com/problems/strange-printer/
   * 
   * Needed some help fopr this one: https://leetcode.com/problems/strange-printer/solutions/3836705/easy-recursive-solution-dp-java-beginner-s-friendly/
   * Stick to the basics please. Assuming only one char, res=1.
   * Assuming two chars, if they are same, res=1, else res=2.
   * Similarly, for three chars, if they are same, res=1, else if any two are same, res=2, else res=3.
   * ...
   * If we have unique chars, res=n. That's the base case.
   * 
   * If some equal element is present for range[idx...jdx], at `i`, we can ignore it 
   * and find cheapest way to print range[idx...i-1] and range[i+1...jdx]. 
   * Ultimately it'll be down to printing only unique elements in base case for any possible range.
   * 
   * TC: O(n^3) SC: O(n^2)
   * 
   * #dynamic-programming #array #tricky #important
   */

  int [][]dp;
  int n;
  char []chs;
  public int strangePrinter(String s) {
    chs = s.toCharArray();
    n = chs.length;
    dp = new int[n][n];
    for(int []d: dp) Arrays.fill(d, -1);
    return dfs(0,n-1);
  }

  private int dfs(int idx, int jdx){
    if(idx==jdx) return 1;
    else if(idx > jdx) return 0;
    else if(dp[idx][jdx]>=0) return dp[idx][jdx];
    int res = 1+dfs(idx+1,jdx); // finds ways to print range[idx+1...jdx] while printing chs[idx] separately
    for(int i=idx+1; i<=jdx; i++){
      if(chs[i] == chs[idx]){ // equals ... means we can print char[i] in 1 less step
        res = Math.min(res, 
          dfs(idx, i-1)+ // find ways to print range[idx ... i-1]
          dfs(i+1, jdx) // find ways to print range[i+1 ... jdx]
        );
      }
    }
    return dp[idx][jdx]=res;
  }
}

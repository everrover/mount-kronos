package SuperMario;

import java.util.Arrays;

public class AllocateMailboxes {

  /**
   * https://leetcode.com/problems/allocate-mailboxes/
   * 
   * Needed to do the math here. Basically, attempt is made to split the houses into `k` groups.
   * Then, for each group the ideal mailbox position is found.
   * 
   * Spoiler:: Ideal mailbox position is at the median house.
   * 
   * For example for subsecion arr :  ... 1 4 8 10 20 ...
   * Let's say mailbox is at pos `x`, then dist = |x-1| + |x-4| + |x-8| + |x-10| + |x-20|
   * If mailbox is placed before house 1 : dist = (1-x)+(4-x)+(8-x)+(10-x)+(20-x) = 43-5x
   * If mailbox is placed between houses 1 & 4: dist = (x-1)+(4-x)+(8-x)+(10-x)+(20-x) = 41-3x
   * If mailbox is placed between houses 4 & 8: dist = (x-1)+(x-4)+(8-x)+(10-x)+(20-x) = -x+33
   * If mailbox is placed between houses 8 & 10: dist = (x-1)+(x-4)+(x-8)+(10-x)+(20-x) = x+17
   * If mailbox is placed between houses 10 & 20: dist = (x-1)+(x-4)+(x-8)+(x-10)+(20-x) = 3x-3
   * If mailbox is placed after house 20: dist = (x-1)+(x-4)+(x-8)+(x-10)+(x-20) = 5x-43
   * 
   * So the slope of equation is -5, -3, -1, 1, 3, 5 and 
   * it is clear that the minimum value is at the median since it's the minima of function.
   * Any of the lines (-x+33) or (x+17) can give the minima at the median `8`. So, since x = 8 
   * => dist = (x-1)+(x-4)+(will always be 0)+(10-x)+(20-x) = 25
   * Since all x's cancel out, diff is always 25 which is the difference between sum of all left points and sum of all right points.
   * 
   * 
   * In case of even number of houses, the median is the average of middle two houses. 
   * For example, for arr : ... 1 4 8 10 20 21 ...
   * Slope is -6, -4, -2, 0, 2, 4, 6 an the minima is the line with slope 0. So any point between 8 & 10 is the ideal mailbox position.
   * 
   * Since sum of ranges is required, I used prefix sum to calculate the sum of ranges in O(1) time.
   * 
   * TC: O(n*n+n*k) SC: O(n*n+n*k)
   * 
   * #dynamic-programming #math #tricky-optimization
   */

   int []prefix;
   int []houses;
   int [][]dp;
   int [][]costs;
 
   public int minDistance(int[] houses, int k) {
     Arrays.sort(houses);
     this.houses = houses;
     int res = Integer.MAX_VALUE;
     prefix = new int[houses.length+1];
     costs  = new int[houses.length][houses.length];
     for(int i=1; i<=houses.length; i++) prefix[i] = prefix[i-1] + houses[i-1];
     for(int i=0; i<houses.length; i++){
       for(int j=i; j<houses.length; j++){
         int mid = (i+j)/2, count = j-i+1;
         if(count%2 == 0){
           costs[i][j] = (prefix[j+1] - prefix[i+count/2]) - (prefix[i+count/2] - prefix[i]);
         }else{
           costs[i][j] = (prefix[j+1] - prefix[i+1+count/2]) - (prefix[i+count/2] - prefix[i]);
         }
       }
     }
     dp = new int[houses.length][k+1];
     for(int i=0; i<houses.length; i++) Arrays.fill(dp[i], -1);
     res = Math.min(res, dfs(0, k));
     return res;
   }
 
   private int dfs(int idx, int k) {
     if (k == 0 || idx>=houses.length) return 0;
     if (dp[idx][k] != -1) return dp[idx][k];
     int res = Integer.MAX_VALUE;
     if(k == 1){
       return dp[idx][k] = costs[idx][houses.length-1];
     }
     for (int i = idx; i<houses.length; i++) {
       res = Math.min(res, costs[idx][i] + dfs(i+1, k-1));
     }
     return dp[idx][k]=res;
   }

}

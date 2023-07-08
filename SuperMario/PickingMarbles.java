package SuperMario;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class PickingMarbles {
  /**
   * https://leetcode.com/problems/put-marbles-in-bags/
   * Initial thoughts: Using nCk formula, we can find the number of ways to pick k marbles from n marbles. 
   * Pick the maximum and minimum of the marble selection costs and find the difference.
   * 
   * TC: O(C(n,k)) which is huge for the provided constraints
   * 
   * Next thoughts: Can we use Coin Change logic to find max and min costs
   * TC: O(n*n) SC: O(n*n), again huge for the provided constraints
   * 
   * Next thoughts: We need to split the marbles into `k` groups, equivalent to putting `k-1` dividers between the marbles.
   * There's a catch though, we need to find the maximum and minimum of the sum of the marbles in each group. 
   * And any split used would incur a cost of arr[i]+arr[i+1]
   * for k = 1, max-cost = arr[0]+arr[n-1]
   * for k = 2, max-cost = (arr[0]+arr[i])+(arr[i+1]+arr[n-1]) = arr[0]+(arr[i]+arr[i+1])+arr[n-2], for split at `i`
   * for k = 3, max-cost = (arr[0]+arr[i])+(arr[i+1]+arr[j])+(arr[j+1]+arr[n-1]) 
   *                     = arr[0]+(arr[i]+arr[i+1])+(arr[j]+arr[j+1])+arr[n-3], for splits at `i` and `j`
   * ...
   * Now these splits can be sorted according to the max and min values to find max and min costs
   * Used PQ for sorting
   * 
   * TC: O(nlogn) SC: O(n)
   * 
   * #greedy #priority-queue #sorting #math #optimization
   * 
   * Can use sorting as well...
   */
  public long putMarbles(int[] weights, int k) {
    Queue<Long> min = new PriorityQueue<>();
    Queue<Long> max = new PriorityQueue<>(Comparator.reverseOrder());
    for(int i=1; i<weights.length; i++){
      min.offer((long)weights[i]+(long)weights[i-1]);
      max.offer((long)weights[i]+(long)weights[i-1]);
    }
    // System.out.println(max);
    long i = 1, maxSum = 0, minSum = 0;
    while(i++<k){
      maxSum += max.poll();
      minSum += min.poll();
    }
    return (maxSum-minSum);
  }

  public long putMarblesWithSort(int[] weights, int k) {
    // all partition points
    int[] costs = new int[weights.length - 1];
    for (int i = 0; i < weights.length - 1; i++) {
      costs[i] = weights[i] + weights[i+1];
    }
    Arrays.sort(costs);
    long min = 0, max = 0;
    for (int i = 0; i < k - 1; i++) {
      min += costs[i];
      max += costs[costs.length - 1 - i];
    }
    return max - min;
  }
}

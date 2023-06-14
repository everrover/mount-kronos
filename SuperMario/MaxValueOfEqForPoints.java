package SuperMario;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.PriorityQueue;
import java.util.Queue;

public class MaxValueOfEqForPoints {
  /**
   * https://leetcode.com/problems/max-value-of-equation/
   * Brute force: Compute for all pairs and find the max. TC: O(n^2) SC: O(1)
   * Slighlty optimized:
   * - Use a MAX-HEAP to store the values of already seen values to keep track of maximum
   * - For each `i` remove all values from the heap which are not in the range of `k` from `i`
   * - TC: O(n*log(n)) SC: O(n)
   * Optimized:
   * - Use a monotonically decreasing queue to store the values of already seen values
   * - For each `i` remove all values from the DQ which are not in the range of `k` from `i`. Do for `left`(first) end
   * - Maintaining monotonic decreasing property:: For each `i` remove all values from the DQ which are less than the current value. Do for `right`(last) end
   * - TC: O(n) SC: O(n)
   *
   */

  // O(n*log(n) & O(n) solution TC and SC
  public int findMaxValueOfEquationHeaped(int[][] points, int k) {
    if(points.length < 2) return -1;
    long res = Long.MIN_VALUE;
    Queue<int[]> pq = new PriorityQueue<>((a, b)->b[0]-a[0]);
    pq.offer(new int[]{points[0][1]-points[0][0], points[0][0]});
    for(int i=1; i<points.length; i++){
      while(!pq.isEmpty() && Math.abs(points[i][0]-pq.peek()[1])>k) pq.poll();
      if(!pq.isEmpty()) res = Math.max(res, points[i][0]+points[i][1]+pq.peek()[0]);
      pq.offer(new int[]{points[i][1]-points[i][0], points[i][0]});
    }
    return (int)res;
  }
  // O(n) solution TC and SC : Using monotonically decreasing queue
  public int findMaxValueOfEquation(int[][] points, int k) {
    if(points.length < 2) return -1;
    long res = Long.MIN_VALUE;
    Deque<int[]> pq = new ArrayDeque<>();
    for(int[] point: points){
      while(!pq.isEmpty() && Math.abs(point[0]-pq.peekFirst()[1])>k) pq.pollFirst();
      if(!pq.isEmpty()) res = Math.max(res, point[0]+point[1]+pq.peekFirst()[0]);
      while(!pq.isEmpty() && point[1]-point[0]>pq.peekLast()[0]) pq.pollLast();
      pq.offerLast(new int[]{point[1]-point[0], point[0]});
    }
    return (int)res;
  }
}

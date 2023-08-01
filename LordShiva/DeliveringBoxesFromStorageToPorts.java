package LordShiva;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.PriorityQueue;
import java.util.Queue;

public class DeliveringBoxesFromStorageToPorts {
  /**
   * https://leetcode.com/problems/delivering-boxes-from-storage-to-ports/
   * 
   * For top-down approach, we can use prefix sum to find the number of different cities in a range
   * recurse(idx) = min(2+prefix[idx+1]-prefix[i+1]+recurse(i+1)) for all i in [idx, boxes.length)
   *              = 0 if idx >= boxes.length
   * Same can be implemented in bottom up approach.
   * TC: O(n^2) SC: O(n)
   * 
   * For bottom up approach, we know we need to find the minimum cost of delivering boxes from 0 to idx-1 and then
   * add the cost of delivering boxes from idx to boxes.length-1. We can optimize the minimum finding step by using
   * segment tree or priority-queue. -- boxDeliveringOptimizedO_n_logn
   * TC: O(nlogn) SC: O(n)
   * 
   * For the same optimization step, we can use a mono-queue as well. Why? Due to greedy nature of the problem.
   * The smallest element is the leftmost one. We remove the elements from the left which are not useful.
   * i.e. for wt>maxWeight or cnt > maxBoxes. And also the elements which are smaller than `dp[i]-diffCity[i+1]` for maintaining
   * the monotonicity of the queue.
   * P.S. Same can be done with a sliding window as well. But we need to maintain the monotonicity of the window.
   * -- boxDeliveringOptimizedO_n
   * TC: O(n) SC: O(n)
   * 
   * #dynamic-programming #sliding-window #mono-deque #prefix-sum #segment-tree #optimization #sliding-window
   */

  private int[][] boxes;
  private int[] dp;
  private int[] prefix;
  private int maxBoxes; private int maxWeight;
  public int boxDeliveringApproach1(int[][] boxes, int portsCount, int maxBoxes, int maxWeight) {
    this.maxBoxes = maxBoxes; 
    this.maxWeight = maxWeight;
    this.boxes = boxes;
    dp = new int[boxes.length];
    prefix = new int[boxes.length+1];
    for(int i=1; i<=boxes.length; i++){
      dp[i-1] = -1;
      prefix[i] = prefix[i-1];
      if(i>1 && boxes[i-1][0]!=boxes[i-2][0]){
        prefix[i]++;
      }
    }
    // for(int p: prefix) System.out.print(p+",");
    // System.out.println();
    // return recApproach1(0);
    return recBottomUp();
  }

  private int recApproach1(int idx){// finding the minimum cost of current trip starting from idx
    if(idx >= boxes.length) return 0;
    else if(dp[idx] >= 0) return dp[idx];
    int res = Integer.MAX_VALUE, wt = 0, cnt = 0, i=idx;
    while(i<boxes.length){ // can be optimized to work with segment tree - need bottom up approach for it
      wt+=boxes[i][1]; cnt++;
      if(wt>maxWeight || cnt > maxBoxes) break;
      res = Math.min(res, 2+prefix[i+1]-prefix[idx+1]+recApproach1(i+1));
      i++;
    }
    return dp[idx] = res;
  }

  private int recBottomUp(){
    int[] dp = new int[boxes.length+1];
    dp[0] = 0;
    for(int i=1; i<=boxes.length; i++){
      int res = Integer.MAX_VALUE, wt = 0, cnt = 0, j=i-1;
      while(j>=0){ // can be optimized to work with segment tree - need bottom up approach for it
        wt+=boxes[j][1]; cnt++;
        if(wt>maxWeight || cnt > maxBoxes) break;
        res = Math.min(res, 2+prefix[i]-prefix[j]+dp[j]);
        j--;
      }
      dp[i] = res;
    }
    return dp[boxes.length];
  }

  public int boxDeliveringOptimizedO_n_logn(int[][] boxes, int portsCount, int maxBoxes, int maxWeight) {
    int []dp = new int[boxes.length+1];
    int[] prefix = new int[boxes.length+1], diffCity = new int[boxes.length+1];
    for(int i=1; i<=boxes.length; i++){
      prefix[i] = prefix[i-1]+boxes[i-1][1];
      diffCity[i] = diffCity[i-1]+((i>1 && boxes[i-1][0]!=boxes[i-2][0])?1:0);
    }
    Arrays.fill(dp, Integer.MAX_VALUE);
    dp[0] = 0;
    Queue<int[]> pq = new PriorityQueue<>((a,b)->a[1]-b[1]);
    pq.offer(new int[]{0,0});
    for(int i=1; i<=boxes.length; i++){
      while(!pq.isEmpty() && (i-pq.peek()[0]>maxBoxes || prefix[i]-prefix[pq.peek()[0]]>maxWeight)){
        pq.poll();
      }

      dp[i] = pq.peek()[1]+2+diffCity[i];
      if(i!=boxes.length){
        pq.offer(new int[]{i, dp[i]-diffCity[i+1]});
      }
    }
    return dp[boxes.length];
  }

  public int boxDeliveringOptimizedO_n(int[][] boxes, int portsCount, int maxBoxes, int maxWeight) {
    int []dp = new int[boxes.length+1];
    int[] prefix = new int[boxes.length+1], diffCity = new int[boxes.length+1];
    for(int i=1; i<=boxes.length; i++){
      prefix[i] = prefix[i-1]+boxes[i-1][1];
      diffCity[i] = diffCity[i-1]+((i>1 && boxes[i-1][0]!=boxes[i-2][0])?1:0);
    }
    Arrays.fill(dp, Integer.MAX_VALUE);
    dp[0] = 0;
    Deque<int[]> dq = new ArrayDeque<>();
    dq.offer(new int[]{0,0});
    for(int i=1; i<=boxes.length; i++){
      while(!dq.isEmpty() && (i-dq.peek()[0]>maxBoxes || prefix[i]-prefix[dq.peek()[0]]>maxWeight)){
        dq.poll();
      }
      dp[i] = dq.peek()[1]+2+diffCity[i];
      if(i!=boxes.length){
        while(dq.peekLast()[1]>=dp[i]-diffCity[i+1]) dq.pollLast();
        dq.offer(new int[]{i, dp[i]-diffCity[i+1]});
      }
    }
    return dp[boxes.length];
  }
}
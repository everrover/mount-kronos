package SuperMario;

import java.util.PriorityQueue;
import java.util.Queue;

public class IPO {
  /**
   * https://leetcode.com/problems/ipo/description/
   * Had it been `revenue` and not `profit`, we would have computed it beforehand and then used it.
   * TC: O(n*log(n)+k*log(n)) SC: O(n)
   * #greedy #heap #priority-queue #trivial-optimization
   */
  public int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
    final int psiz = profits.length;
    Queue<int[]> pqCap = new PriorityQueue<>((a, b)->a[0]-b[0]);
    Queue<Integer> pqProfit = new PriorityQueue<>((a, b)->b-a);
    for(int i=0; i<psiz; i++){
      pqCap.offer(new int[]{capital[i], profits[i]});
    }
    while(k-->0){
      while(!pqCap.isEmpty() && pqCap.peek()[0]<=w){
        pqProfit.offer(pqCap.poll()[1]);
      }
      if(pqProfit.isEmpty()) break;
      w += pqProfit.poll();
    }
    return w;
  }
  // This was optimized as per the dataset and works best in `avg_case`, not `worst_case`
  public int findMaximizedCapitalOpt(int k, int w, int[] profits, int[] capital) {
    boolean speedUp = true;
    for(int c : capital) if (w < c) { speedUp = false; break; }

    if(speedUp){
      PriorityQueue<Integer> pq = new PriorityQueue<>(); // min heap
      for(int p : profits){
        pq.offer(p);
        if(pq.size() > k) pq.poll(); // remove the smallest profit holders
      }
      while(!pq.isEmpty()) w += pq.poll();
      return w;
    }

    final int psiz = profits.length;
    Queue<int[]> pqCap = new PriorityQueue<>((a, b)->a[0]-b[0]);
    Queue<Integer> pqProfit = new PriorityQueue<>((a, b)->b-a);
    for(int i=0; i<psiz; i++){
      pqCap.offer(new int[]{capital[i], profits[i]});
    }
    while(k-->0){
      while(!pqCap.isEmpty() && pqCap.peek()[0]<=w) pqProfit.offer(pqCap.poll()[1]);
      if(pqProfit.isEmpty()) break;
      w += pqProfit.poll();
    }
    return w;
  }
}

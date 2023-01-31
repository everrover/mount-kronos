package NinetySix;

import java.util.Arrays;

/**\
 * https://leetcode.com/problems/most-profit-assigning-work/
 * For each worker, the most profitable task is picked from tasks which it can do.
 * - Sort aggregated data block acc to profit and difficulty. Also sort workers list.
 * - Using bin search I found the index of highest difficulty task that a worker can do. The maximum profit work is chosen from
 *   profits[0...idx], idx=index of task with highest difficulty for a worker.
 * - Find `idxNew` via bin search for new worker. Since workers list is already sorted, idxNew>=idxOld. Hence, we will not
 *   re-iterate over profits array from [0...idxNew]. We'll search in [`idxOld`, ... ,`idxNew`] to find next max profitable
 *   task(if present).
 * - Repeat
 *
 * Optimization for worker iteration.
 * Since workers list is already sorted, if task is found at `i` for any worker at `j`, new set of tasks would
 * always be picked from tasks[`i`...n] in tasks list for all workers[j+1...m].
 */

public class MaxProfitAssignment {

  public int maxProfitAssignment(int[] difficulty, int[] profit, int[] worker) {
    if(profit.length == 0 || worker.length == 0) return 0;
    int [][]prodif = new int[difficulty.length][2];
    for(int i=0; i<difficulty.length; i++){
      prodif[i][0] = profit[i];
      prodif[i][1] = difficulty[i];
    }
    Arrays.sort(prodif, (a,b)->a[1]==b[1]?(a[0]-b[0]):(a[1]-b[1]));
    Arrays.sort(worker);
    int res = 0, idx=0, maxP = Integer.MIN_VALUE, j=0;
    for(int w: worker){
      while(idx<prodif.length && prodif[idx][1] <= w) {
        maxP = Math.max(maxP, prodif[idx++][0]);
      }
      if(idx == 0) continue;
      res += maxP;
    }
    return res;
  }

  public int maxProfitAssignmentBinarySearch(int[] difficulty, int[] profit, int[] worker) {
    if(profit.length == 0 || worker.length == 0) return 0;
    int [][]prodif = new int[difficulty.length][2];
    for(int i=0; i<difficulty.length; i++){
      prodif[i][0] = profit[i];
      prodif[i][1] = difficulty[i];
    }
    Arrays.sort(prodif, (a, b)->a[1]==b[1]?(a[0]-b[0]):(a[1]-b[1]));
    Arrays.sort(worker);
    int res = 0, idx=0, maxP = Integer.MIN_VALUE, j=0;
    for(int w: worker){
      idx = findIdx(prodif, w, idx);
      if(idx == -1) continue;
      while(j<=idx) maxP = Math.max(maxP, prodif[j++][0]);
      res += maxP;
    }
    return res;
  }
  private int findIdx(int [][]prodif, int worker, int prev){
    if(prodif.length>0 && worker < prodif[0][1]) return -1;
    int l=prev, r=prodif.length-1, idx=-1;
    int m = (l+r)/2;
    while(l<=r){
      m = (l+r)/2;
      if(prodif[m][1]<=worker){
        idx = m;
        l = m+1;
      }else{
        r = m-1;
      }
    }
    // System.out.println(worker+"::"+idx);
    return idx;
  }
}

package LordShiva;

import java.util.Arrays;

public class RemoveBoxes {

  /**
   * https://leetcode.com/problems/remove-boxes/
   * 
   * Earlier thought of dynamic programming, but with a bit-mask. But not a scalable solution. Since it involved backtracking.
   * 
   * TC:: O(n!) SC: O(n!) // For traversing and storage through n! states
   * 
   * Then found a ingenious way to represent the recursion. 
   * For each box as a starting point, we can have a streak of boxes with same color. 
   * We can either have them as a continuous streak of elements or we can have something in between those elements generating a streak.
   * If we assume streak is terminated at `idx`, then we can have a new streak starting at `idx+1` and ending at `jdx`.
   * If we assume streak is continued with `idx` for element for some `i` in `idx+1 ... jdx` where `boxes[i] == boxes[idx]`
   * , we can have a new streak starting at `i` in range ending at `jdx`. 
   * However, we can't have a streak starting at `idx+1` and ending at `i-1`, since we assume streak is continued with `i`th element.
   * Hence, a fresh streak is started for boxes[idx+1...i].
   * 
   * TC: O(n^4) SC: O(n^3)
   * 
   * #dynamic-programming #array #tricky #important 
   */

  private int [][][]dp;
  private int []boxes;
  private int n;

  public int removeBoxes(int[] boxes) {
    this.boxes = boxes;
    n = boxes.length;
    dp = new int[n][n][n+1];
    for(int [][]dd: dp) for(int []d: dd) Arrays.fill(d, -1);
    return recurse(0, n-1, 0); // with no existing streak, find max number of points fetched via 
  }

  private int recurse(int idx, int jdx, int streak){
    if(idx>jdx) return 0;
    else if(dp[idx][jdx][streak] != -1) return dp[idx][jdx][streak];
    int ai = idx, astreak = streak;
    while(idx+1<jdx && boxes[idx+1] == boxes[idx]){ idx++; streak++; }
    int res = (streak+1)*(streak+1) + recurse(idx+1, jdx, 0); // Streak terminated at `idx` here, hence started a new streak for new element at `idx+1`
    for(int i=idx+1; i<=jdx; i++){
      if(boxes[i] == boxes[idx]){ // if equals
        res = Math.max(
          res,
          recurse(idx+1, i-1, 0) // we don't want to include boxes[idx+1 ... i-1] to be part of existing streak
          +recurse(i, jdx, streak+1) // it means we want `idx`->`i`->{???} to be the next streak(set of boxes to be removed). since `i` is part of `streak` increased `streak` by `1`
        );
      }
    }
    return dp[ai][jdx][astreak] = res;
  }
}

package LordShiva;
public class MinimumNumberOperationsToMoveAllBallsToEachBox {
  /**
   * https://leetcode.com/problems/minimum-number-of-operations-to-move-all-balls-to-each-box/
   * 
   * Used prefix sum concept to find the number of operations required to move all balls to each box. 
   * Moved from left to right and then whenever encountered a new ball, got know that `count-of-balls` 
   * number of balls would be moved to the right. 
   * Otherwise, the number of balls to move would be same as the number of balls moved to the previous box.
   * Same done for right to left.
   * 
   * TC: O(n) SC: O(n)
   * 
   * #prefix-sum-concept #array #easy
   */

   public int[] minOperations(String boxes) {
    int []l2r = new int[boxes.length()];
    int []r2l = new int[boxes.length()];
    int ball = boxes.charAt(0)=='1'?1:0;
    for(int i=1; i<boxes.length(); i++){
      l2r[i] = l2r[i-1]+ball;
      ball += boxes.charAt(i)=='1'?1:0;
    }
    ball = boxes.charAt(boxes.length()-1)=='1'?1:0;
    for(int i=boxes.length()-2; i>=0; i--){
      r2l[i] = r2l[i+1]+ball;
      ball += boxes.charAt(i)=='1'?1:0;
    }
    for(int i=0; i<boxes.length(); i++){
      // System.out.println(l2r[i]+":"+r2l[i]);
      l2r[i] = l2r[i]+r2l[i];
    }
    return l2r;
  }
}

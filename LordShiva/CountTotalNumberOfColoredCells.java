package LordShiva;

public class CountTotalNumberOfColoredCells {
  /**
   * https://leetcode.com/problems/count-total-number-of-colored-cells/description/
   * Center sq is always in middle, and there are four parts of square who have (n-1), (n-2), ... 1 cells, and so on. So, total cells = (n-1)*n/2*4+1
   * TC = O(1) SC = O(1)
   * #easy #math
   */
  public long coloredCells(int n) {
    long t = n;
    return (t-1)*t*2+1;
  }

  /**
   * https://leetcode.com/problems/minimum-cuts-to-divide-a-circle/
   * Read the logic please. That's the proof.
   * TC = O(1) SC = O(1)
   * #easy #math
   */
  public int numberOfCuts(int n) {
    if(n==1) return 0;
    return n%2==0?(n/2):n;
  }

}

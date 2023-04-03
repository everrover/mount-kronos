package NinetySix;

public class AddMinNumberOfRungs{
  /**
   * https://leetcode.com/problems/add-minimum-number-of-rungs/
   * We place rungs just enough to reach from `i`th till (i+1)th
   */
  public int addRungs(int[] rungs, int dist) {
    int ans = 0;
    if(rungs[0] > dist) ans+=(rungs[0]-1)/dist;
    for(int i=1; i<rungs.length; i++) {
      int diff = rungs[i]-rungs[i-1];
      if(diff>dist) ans+=(diff-1)/dist;
    }
    return ans;
  }
}
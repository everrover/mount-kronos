package NinetySix.BulbSwitcher;

/**
 * https://leetcode.com/problems/bulb-switcher/
 */
public class BulbSwitcher {
  public int bulbSwitch(int n) {
    int res = 0, j=1;
    while(n>=0) {
      res++;
      n-=j;
      j+=2;
    }
    return res-1;
  }
}

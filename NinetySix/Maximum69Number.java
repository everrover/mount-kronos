package NinetySix;

/**
 * https://leetcode.com/problems/maximum-69-number/
 * Flipped leftmost 6
 */
public class Maximum69Number {
  public int maximum69Number (int num) {
    // find location of first 6 in num, moving from L->R
    StringBuilder n = new StringBuilder(""+num);
    int idx = n.indexOf("6");
    if(idx != -1) n.setCharAt(idx, '9');
    return Integer.parseInt(n.toString());
  }
}

package NinetySix;

/**
 * https://leetcode.com/problems/bulb-switcher-ii/
 * Doing the same operation even number of times result's in no changes out there in results. ... (1)
 * I simulated for n = 1(max states = 2), n=2(max states = 4), n>=3(max possible states = 2^n)
 * Since moves(and due to ..(1)) are limited it causes the bulbs to never have any more than 8 states for n>=3
 */
public class BulbSwitcherII {
  public int flipLights(int n, int presses) {
    if(n==0) return 1;
    if(n == 1){
      return presses==0?1:2;
    }
    else if(n == 2){
      if(presses == 0) return 1;
      else if(presses == 1) return 3; // Move (4) is equivalent to (3) due to n=2
      else return 4;
    }else{
      if(presses == 0) return 1;
      else if(presses == 1) return 4;
      else if(presses == 2) return 7;
      else return 8;
    }
  }
}

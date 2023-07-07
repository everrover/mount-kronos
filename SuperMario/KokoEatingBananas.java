package SuperMario;

import java.util.Arrays;

public class KokoEatingBananas {
  /**
   * https://leetcode.com/problems/koko-eating-bananas/description/
   * Applied binary search over sol space. 
   */
  private int []piles;
  private int h;
  public int minEatingSpeed(int[] piles, int h) {
    this.piles = piles;
    this.h = h;
    int sum = Arrays.stream(piles).sum();
    int l = (int)Math.ceil((double)sum/h), r = 0, mid, res=0;
    for(int pile: piles) r = Math.max(r, pile+1);
    while(l<r){
      mid = (l+r)/2;
      if(ableToEat(mid)){
        res = mid;
        r = mid;
      }else{
        l = mid+1;
      }
    }
    return res;
  }
  private boolean ableToEat(int m){
    int tmp = h;
    // System.out.println(h+","+m+":::");
    for(int pile: piles){
      tmp -= (int)Math.ceil((double)pile/m);
      // System.out.println(pile+":"+tmp+",");
      if(tmp<0) return false;
    }
    return true;
  }
}

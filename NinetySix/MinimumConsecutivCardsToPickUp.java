package NinetySix;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/minimum-consecutive-cards-to-pick-up/
 * Used hashes for storing indexes
 */
public class MinimumConsecutivCardsToPickUp {
  public int minimumCardPickup(int[] cards) {
    int res = Integer.MAX_VALUE;
    Map<Integer, Integer> m = new HashMap<>();
    for(int i=0; i<cards.length; i++){
      if(m.containsKey(cards[i])) res = Integer.min(res, i-m.get(cards[i])+1);
      m.put(cards[i], i);
    }
    return res==Integer.MAX_VALUE?-1:res;
  }
}

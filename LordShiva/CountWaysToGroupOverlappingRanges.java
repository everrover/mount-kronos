package LordShiva;

import java.util.Arrays;

public class CountWaysToGroupOverlappingRanges {

  /**
   * https://leetcode.com/problems/count-ways-to-group-overlapping-ranges/
   * 
   * Simply grouped all the overlapping since they'll always be together and they become a disjoint set. Each of these either can join group1 or group2. So, total ways = 2^cnt
   * 
   * #easy #greedy #sorting #math #combintorics
   * TC: O(n*log(n)) SC: O(1)
   * 
   */

  final static long MOD = (long)1e9+7;
  public int countWays(int[][] ranges) {
    Arrays.sort(ranges, (a, b)->(a[0]!=b[0])?(a[0]-b[0]):(a[1]-b[1]));
    int cnt = 0, max = -1;
    for(int []range: ranges){
      if(max < range[0]) cnt++;
      max = Math.max(max, range[1]);
    }
    return (int)powwithmod(2, (long)cnt);
  }
  private long powwithmod(long x, long y){
    if(y == 0) return 1;
    else if(y%2 == 1) return (x*powwithmod(x, y-1))%MOD;
    else {
      long t = powwithmod(x, y/2);
      return (t*t)%MOD;
    }
  }
}

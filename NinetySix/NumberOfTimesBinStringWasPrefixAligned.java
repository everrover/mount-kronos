package NinetySix;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/number-of-times-binary-string-is-prefix-aligned/
 * Just kept track of prefix alignment with a `j` index and while loop. If j>=i, incremented count by 1
 */
public class NumberOfTimesBinStringWasPrefixAligned {
  public int numTimesAllBlue(int[] flips) {
    int[] chs = new int[flips.length+1];
    Arrays.fill(chs, 0);
    chs[0] = 1;
    int res = 0, i=0;
    for(int j=0; j<flips.length; j++){
      chs[flips[j]] = 1;
      while(i<flips.length && chs[i+1] == 1) i++; // notes a range of [0...j] where all elements are 1
      if(i >= (j+1)) res++;
    }
    return res;
  }
}

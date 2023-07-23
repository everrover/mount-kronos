package LordShiva;

public class ThreeEqualParts {
  /**
   * https://leetcode.com/problems/three-equal-parts/
   * 
   * Commented code
   * 
   * TC: O(n) SC: O(1)
   * #math #array #tricky #math:divisibility-rule
   */

  
  public int[] threeEqualParts(int[] arr) {
    final int n = arr.length;
    int sum = 0;
    for(int a: arr) sum+=a;
    if(sum%3 != 0) return new int[]{-1,-1}; // if sum is not divisible by 3, then no solution
    else if(sum == 0) return new int[]{0,n-1}; // if sum is 0, then all parts are equal
    int i=-1, j, ones=0, div3 = sum/3, left, right, lenr = 0, cnum=0, lnum=0, rnum=0;
    while(i<n && ones<=div3) if(1 == arr[++i]) ones++;
    left = i-1; // find left part init point with as many preceding zeros as possible
    i=n; ones = 0;
    while(i>=0 && ones<=div3) if(1 == arr[--i]) ones++;
    right = i+1; // find right part init point with as many preceding zeros as possible
    i = right-1; j = -1;
    while(i<n && arr[++i] == 0);
    lenr = n-i; // length of right part with preceding zeros ignored
    while(j<n && arr[++j] == 0); // find first part init point with preceding zeros ignored
    right = left+lenr+1; // right part init point
    left = j+lenr-1; // left part init point
    // find cnum : O(n)
    i = 0;
    while(i<=left) lnum = (lnum<<1)|arr[i++];
    i = left+1;
    while(i<right) cnum = (cnum<<1)|arr[i++];
    i = right;
    while(i<n) rnum = (rnum<<1)|arr[i++];
    if(lnum == rnum && rnum == cnum) return new int[]{left, right};
    return new int[]{-1,-1};

  }
}

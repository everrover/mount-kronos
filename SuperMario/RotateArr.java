package SuperMario;

public class RotateArr {
  /**
   * https://leetcode.com/problems/rotate-array
   * Simple solution, but not optimal. Used tmp array to store the rotated array.
   * TC: O(n) SC: O(n)
   * For SC: O(1) solution, we can use reverse algorithm.viz. reverse the whole array, then reverse the first k elements, then reverse the rest.
   *
   * #array #rotate #no-effort
   */
  public void rotate(int[] nums, int k) {
    final int siz = nums.length;
    if(siz <= 1) return;
    k = k%siz;
    if( k == 0 )return;
    int tmp[] = new int[siz];
    int i;
    for(i=0; i<k; i++){
      tmp[i] = nums[siz-k+i];
    }
    for(; i<(siz); i++){
      tmp[i] = nums[i-k];
    }
    for(i=0; i<siz; i++){
      nums[i] = tmp[i];
    }
  }
}

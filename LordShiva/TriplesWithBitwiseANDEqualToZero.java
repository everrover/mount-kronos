package LordShiva;

public class TriplesWithBitwiseANDEqualToZero {
  /**
   * https://leetcode.com/problems/triples-with-bitwise-and-equal-to-zero/
   * 
   * Could've used trie as well: https://leetcode.com/problems/triples-with-bitwise-and-equal-to-zero/solutions/988061/java-5-approaches-with-explanation-and-clean-codes/
   * By traversing node 0 for bit 1 and node 1 and node 0 for bit 0. 
   * Since 0&0 = 1&0 = 0(for 0), and we want count of all such nodes at leaf.
   * And for 1, we can only traverse node 0 for bit 0 as result.
   * 
   * TC: O(n^2) SC: O(n)
   * 
   * #bitmasking #array #optimization #hashing
   */

  public int countTriplets(int[] nums) {
    int[] count = new int[1 << 16];
    for(int a: nums) for(int b: nums) count[a & b]++;
    int res = 0;
    for(int a: nums) for(int i = 0; i < count.length; i++) {
      if((a & i) == 0) res += count[i];
      else i += (a&i)-1; // xxxx10000 -> xxxx11111 can be skipped if at any bit position we have `1`
    }
    return res;
  } 
}

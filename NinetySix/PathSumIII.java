package NinetySix;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/path-sum-iii/
 * Memoization can be done along the path sum since no other branches are used.
 * We store `currentPathSum` and number of instances where it was encountered along the path. We'll call `(currentPathSum - targetSum)` as `x`.
 * If at any node, `x` is in the map, we'll know that `currentPathSum-previousPathSum == targetSum`. And count of nodes with mapped `x`(nothing but `previousPathSum`) value will result in `targetSum`
 * Hence, we increment result by instances where `x` is in the DP map.
 */

public class PathSumIII {
  public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
      this.val = val;
      this.left = left;
      this.right = right;
    }
  }
  private static int res = 0, ts;
  private static Map<Long, Integer> mem;
  public int pathSum(TreeNode root, int targetSum) {
    mem = new HashMap<>(); mem.put(0L, 1);
    res = 0; ts = targetSum;
    preorder(root, 0L);
    return res;
  }

  private void preorder(TreeNode root, long pathSum){
    if(root == null) return;
    long curPathSum = pathSum+root.val;
    long prevPathSum = curPathSum-ts;
    res += mem.getOrDefault(prevPathSum, 0);
    mem.putIfAbsent(curPathSum, 0);
    mem.put(curPathSum, mem.get(curPathSum)+1);
    preorder(root.left, curPathSum);
    preorder(root.right, curPathSum);
    mem.put(curPathSum, mem.get(curPathSum)-1);
  }

  // brute method
  public int pathSumV2Brute(TreeNode root, int targetSum) {
    res = 0; ts = targetSum;
    preorder(root);
    return res;
  }

  private void preorder(TreeNode root){
    if(root == null) return;
    preorderUtil(root, 0);
    preorder(root.left);
    preorder(root.right);
  }

  private void preorderUtil(TreeNode root, long sum){
    if(root == null) return;
    if(ts == sum+root.val) res++;
    preorderUtil(root.left, sum+root.val);
    preorderUtil(root.right, sum+root.val);
  }
}

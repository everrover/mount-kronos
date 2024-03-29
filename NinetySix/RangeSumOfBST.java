package NinetySix;

/**
 * [938. Range Sum of BST](https://leetcode.com/problems/range-sum-of-bst/)
 */

public class RangeSumOfBST {

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
  public int rangeSumBST(TreeNode root, int low, int high) {
    if(root == null) return 0;
    return rangeSumBST(root.left, low, high)+rangeSumBST(root.right, low, high)+((root.val>=low&&root.val<=high)?root.val:0);
  }
}

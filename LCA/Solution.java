package LCA;

// https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/
// https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree-ii/
// https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/

public class Solution {
    public static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int x) { val = x; }
    }
    private static TreeNode res = null;
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        res = null;
        lca(root, p, q);
        return res;
    }

    public boolean lca(TreeNode root, TreeNode p, TreeNode q){
        if(root == null) return false;
        boolean left = lca(root.left, p, q);
        boolean curr = root == p || q == root;
        boolean right = lca(root.right, p, q);
        if((left && right) || (curr && right) || (left && curr)){
            res = root;
        }
        return left || right || curr;
    }

    public TreeNode lowestCommonAncestorTrickSol(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null || root.val == p.val || root.val == q.val) return root;
        TreeNode left = lowestCommonAncestorTrickSol(root.left, p, q);
        TreeNode right = lowestCommonAncestorTrickSol(root.right, p, q);
        if(left==null) return right;
        if(right==null) return left;
        return root;
    }
}
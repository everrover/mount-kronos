package NinetySix;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Queue;

/**
 * https://leetcode.com/problems/find-largest-value-in-each-tree-row/submissions/
 * Applied BFS simply, for level-order traversal. Could have used DFS with passing level-number as well.
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class FindLar {
  public List<Integer> largestValues(TreeNode root) {
    Queue<TreeNode> q = new LinkedList<>();
    List<Integer> res = new LinkedList<>();
    if(Objects.nonNull(root)) q.offer(root);
    while(!q.isEmpty()){
      int sz = q.size();
      int r = Integer.MIN_VALUE;
      while(sz-->0){
        TreeNode tn = q.poll();
        if(Objects.nonNull(tn.left)) q.offer(tn.left);
        if(Objects.nonNull(tn.right)) q.offer(tn.right);
        r = Integer.max(r, tn.val);
      }
      res.add(r);
    }
    return res;
  }
}

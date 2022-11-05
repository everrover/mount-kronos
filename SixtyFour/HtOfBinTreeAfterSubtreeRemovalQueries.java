package SixtyFour;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

// https://leetcode.com/problems/height-of-binary-tree-after-subtree-removal-queries
// ----------------
// https://leetcode.com/problems/height-of-binary-tree-after-subtree-removal-queries/discuss/2757990/Python-3-Explanation-with-pictures-DFS
// https://leetcode.com/problems/height-of-binary-tree-after-subtree-removal-queries/discuss/2758013/Level-%2B-Depth
// ----------------
// MY SOLUTION IS WILDLY DIFFERENT BUT STILL BASED ON SAME IDEA
public class HtOfBinTreeAfterSubtreeRemovalQueries {
  public static class TreeNode {
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

    @Override
    public String toString() {
      return "::"+val+"::";
    }
  }
  public static class SolutionBalancedBTree { // works only for balanced bin tree and not a vine
    private static class Node {
      int height, val;
      Node left, right, parent;
      boolean isLeft;
      public Node(int height, int val, Node left, Node right, Node parent, boolean isLeft){
        this.height = height;
        this.val = val;
        this.left = left;
        this.right = right;
        this.isLeft = isLeft;
        this.parent = parent;
      }

      @Override
      public String toString() {
        return val+"::"+height;
      }
    }

    private Node heightsBuild(TreeNode root, boolean isLeft, Map<Integer, Node> nodeMap){
      if(root == null) return null;

      int height = 0;
      Node left = null, right = null;
      if(root.left != null) {
        left = heightsBuild(root.left, true, nodeMap);
        height = Integer.max(left.height, height);
      }
      if(root.right != null) {
        right = heightsBuild(root.right, false, nodeMap);
        height = Integer.max(right.height, height);
      }
      height++;

      Node node = new Node(height, root.val, left, right, null, isLeft);
      nodeMap.put(root.val, node);
      return node;
    }

    private void parentsBuildUp(Node parent, Node root){
      if(root == null) return;
      root.parent = parent;
      parentsBuildUp(root, root.left);
      parentsBuildUp(root, root.right);
    }

    private int retHt(Node node){
      return node == null? 0: node.height;
    }

    private int upTheTree(int val, Map<Integer, Node> nodeMap){
      int ht = -1;
      Node curr = nodeMap.get(val);
      while(curr.parent != null){
        boolean isL = curr.isLeft;
        curr = curr.parent;
        ht = Integer.max(ht+1, isL?retHt(curr.right):retHt(curr.left));
      }
      return ht;
    }

    public int[] treeQueries(TreeNode root, int[] queries) {
      int []res = new int[queries.length];

      Map<Integer, Node> nodeMap = new HashMap<>();
      Node nodeRoot = heightsBuild(root, true, nodeMap);
      parentsBuildUp(null, nodeRoot);

      for(int i=0; i<queries.length; i++){
        res[i] = upTheTree(queries[i], nodeMap);
      }
      return res;
    }
  }

  public static class Solution {

    public int heightsBuildUp(TreeNode root, int depth, int []maxHt, int []maxHt2nd, int []depths, int []maxNodeAtDepth){
      if(root == null) return -1;
      int height = Integer.max(heightsBuildUp(root.left, depth+1, maxHt, maxHt2nd, depths, maxNodeAtDepth), heightsBuildUp(root.right, depth+1, maxHt, maxHt2nd, depths, maxNodeAtDepth))+1;
      depths[root.val] = depth;
      // store the largest and second largest height at each level
      if(height > maxHt[depth]){
        maxNodeAtDepth[depth] = root.val;
        int tmp = maxHt[depth];
        maxHt[depth] = height;
        maxHt2nd[depth] = tmp;
      }else if(height >= maxHt2nd[depth]){
        maxHt2nd[depth] = height;
      }
      return height;
    }
    public int[] treeQueries(TreeNode root, int []queries){
      int []res = new int[queries.length];
      int []maxHt = new int[11]; // stores max height at depth
      int []maxNodeAtDepth = new int[11]; // stores node with maximum depth
      int []maxHt2nd = new int[11]; // stores 2nd max height at depth
      int []depths = new int[11]; // stores depth of each node
      Arrays.fill(maxHt, -1);
      Arrays.fill(maxHt2nd, -1);
      heightsBuildUp(root, 0, maxHt, maxHt2nd, depths, maxNodeAtDepth);
      for(int i=0; i<queries.length; i++){
        int depth = depths[queries[i]];
        if(maxNodeAtDepth[depth] == queries[i]){ // if best height-ed node is removed => pick 2nd largest height
          res[i] = maxHt2nd[depth];
        }else{ // else pick largest height
          res[i] = maxHt[depth];
        }
        res[i] += depth;
      }
      return res;
    }
  }

  public static void main(String[] args) {
    TreeNode node = new TreeNode(5);
    node.left = new TreeNode(8);
    node.right = new TreeNode(9);
    node.left.left = new TreeNode(2);
    node.left.right = new TreeNode(1);
    node.left.left.left = new TreeNode(4);
//    node.left.left.left.left = new TreeNode(10);
    node.left.left.right = new TreeNode(6);
    node.right.left = new TreeNode(3);
    node.right.right = new TreeNode(7);
    Solution solution = new Solution();
    System.out.println(solution.treeQueries(node, new int[]{3,2,4,8,10}));
    node = new TreeNode(1);
    node.right = new TreeNode(5);
    node.right.left = new TreeNode(3);
    node.right.left.left = new TreeNode(2);
    node.right.left.right = new TreeNode(4);
    System.out.println(solution.treeQueries(node, new int[]{3,5,4,2,4}));
  }
}

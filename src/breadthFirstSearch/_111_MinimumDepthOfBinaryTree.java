package breadthFirstSearch;

import java.util.LinkedList;

/**
 * Given a binary tree, find its minimum depth.
 *
 * The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.
 *
 * Note: A leaf is a node with no children.
 *
 * Example 1:
 * Input: root = [3,9,20,null,null,15,7]
 * Output: 2
 *
 * Example 2:
 * Input: root = [2,null,3,null,4,null,5,null,6]
 * Output: 5
 * <br>
 * <a href="https://leetcode.com/problems/minimum-depth-of-binary-tree/">111. Minimum Depth of Binary Tree</a>
 */
public class _111_MinimumDepthOfBinaryTree {
    public int practice(TreeNode root) {
        if (root == null) {
            return 0;
        }
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.addLast(root);
        int level = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode current = queue.removeFirst();
                if (current.left == null && current.right == null) {
                    return level;
                }
                if (current.left != null) {
                    queue.addLast(current.left);
                }
                if (current.right != null) {
                    queue.addLast(current.right);
                }
            }
            level += 1;
        }
        return level;
    }
    private static class TreeNode {
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
}

package problems.p0501_0600;

import patterns.TreePattern;
import patterns.DepthFirstSearchPattern;
import difficulty.Easy;

/**
 * Given the root of a binary tree, return the length of the diameter of the
 * tree.
 *
 * The diameter of a binary tree is the length of the longest path between any
 * two
 * nodes in a tree. This path may or may not pass through the root.
 *
 * The length of a path between two nodes is represented by the number of edges
 * between them.
 *
 * Example 1:
 * Input: root = [1,2,3,4,5]
 * Output: 3
 * Explanation: 3 is the length of the path [4,2,1,3] or [5,2,1,3].
 *
 * Example 2:
 * Input: root = [1,2]
 * Output: 1
 *
 * <a href="https://leetcode.com/problems/diameter-of-binary-tree/">543.
 * Diameter of Binary Tree</a>
 */
public class _0543_DiameterOfBinaryTree implements TreePattern, DepthFirstSearchPattern, Easy {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    int max = 0;

    /**
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     */
    public int diameterOfBinaryTree(TreeNode root) {
        int diameter = height(root.left) + height(root.right);
        if (max > diameter) {
            return max;
        } else {
            return diameter;
        }
    }

    public int height(TreeNode node) {
        if (node == null)
            return 0;
        int left = height(node.left);
        int right = height(node.right);
        if ((left + right) > max) {
            max = left + right;
        }
        if (left > right) {
            return left + 1;
        } else {
            return right + 1;
        }
    }
}

package problems.p0101_0200;

import patterns.TreePattern;
import patterns.BinaryTreePattern;
import patterns.DepthFirstSearchPattern;
import difficulty.Easy;

/**
 * Given a binary tree, determine if it is height-balanced.
 *
 * A height-balanced binary tree is a binary tree in which the depth of the
 * two subtrees of every node never differs by more than one.
 *
 * Example 1:
 * Input: root = [3,9,20,null,null,15,7]
 * Output: true
 *
 * Example 2:
 * Input: root = [1,2,2,3,3,null,null,4,4]
 * Output: false
 *
 * Example 3:
 * Input: root = []
 * Output: true
 *
 * Constraints:
 * - The number of nodes in the tree is in the range [0, 5000].
 * - -10^4 <= Node.val <= 10^4
 *
 * <a href="https://leetcode.com/problems/balanced-binary-tree/">110. Balanced
 * Binary Tree</a>
 */
public class _0110_BalancedBinaryTree implements BinaryTreePattern, TreePattern, DepthFirstSearchPattern, Easy {

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

    /**
     * Time Complexity: O(N)
     * Space Complexity: O(H) where H is the height of the tree
     */
    public boolean isBalance(TreeNode root) {
        return checkHeight(root) != -1;
    }

    private int checkHeight(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int left = checkHeight(node.left);
        if (left == -1) {
            return -1;
        }
        int right = checkHeight(node.right);
        if (right == -1) {
            return -1;
        }
        if (right - left > 1 || right - left < -1) {
            return -1;
        }
        if (right - left > 0) {
            return right + 1;
        } else {
            return left + 1;
        }
    }

    /**
     * Time Complexity: O(N^2)
     * Space Complexity: O(H) where H is the height of the tree
     */
    public boolean isBalanced_0(TreeNode root) {
        if (root == null) {
            return true;
        }
        int depthDiff = depth(root.left) - depth(root.right);
        if (depthDiff > 1 || depthDiff < -1) {
            return false;
        }
        return isBalanced_0(root.left) && isBalanced_0(root.right);
    }

    private int depth(TreeNode target) {
        if (target == null) {
            return 0;
        }
        if (target.left == null && target.right == null) {
            return 1;
        }
        int left = depth(target.left);
        int right = depth(target.right);
        return left > right ? left + 1 : right + 1;
    }
}

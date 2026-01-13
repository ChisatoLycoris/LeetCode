package problems.p0201_0300;

import patterns.TreePattern;
import patterns.BinaryTreePattern;
import patterns.BinarySearchTreePattern;
import patterns.DepthFirstSearchPattern;
import difficulty.Medium;

/**
 * Given a binary search tree (BST), find the lowest common ancestor (LCA) node
 * of two given nodes in the BST.
 *
 * According to the definition of LCA on Wikipedia: "The lowest common ancestor
 * is defined between two nodes p and q as the lowest node in T that has both p
 * and q as descendants (where we allow a node to be a descendant of itself)."
 *
 * Example 1:
 * Input: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 8
 * Output: 6
 * Explanation: The LCA of nodes 2 and 8 is 6.
 *
 * Example 2:
 * Input: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 4
 * Output: 2
 * Explanation: The LCA of nodes 2 and 4 is 2, since a node can be a descendant
 * of itself according to the LCA definition.
 *
 * Example 3:
 * Input: root = [2,1], p = 2, q = 1
 * Output: 2
 *
 * Constraints:
 * - The number of nodes in the tree is in the range [2, 10^5].
 * - -10^9 <= Node.val <= 10^9
 * - All Node.val are unique.
 * - p != q
 * - p and q will exist in the BST.
 *
 * <a href=
 * "https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/">235.
 * Lowest Common Ancestor of a Binary Search Tree</a>
 */
public class _0235_LowestCommonAncestorOfABinarySearchTree
        implements TreePattern, BinaryTreePattern, BinarySearchTreePattern, DepthFirstSearchPattern, Medium {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    /**
     * Time Complexity: O(logN)
     * Space Complexity: O(1)
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root.val == p.val || root.val == q.val) {
            return root;
        }
        TreeNode nextP;
        if (root.val > p.val) {
            nextP = root.left;
        } else {
            nextP = root.right;
        }
        TreeNode nextQ;
        if (root.val > q.val) {
            nextQ = root.left;
        } else {
            nextQ = root.right;
        }
        if (nextP.val != nextQ.val) {
            return root;
        }
        return lowestCommonAncestor(nextP, p, q);
    }
}

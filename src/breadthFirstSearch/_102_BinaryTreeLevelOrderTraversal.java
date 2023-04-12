package breadthFirstSearch;

import java.util.LinkedList;
import java.util.List;

/**
 * Given the root of a binary tree,
 * return the level order traversal of its nodes' values. (i.e., from left to right, level by level).
 *
 * Example 1:
 * Input: root = [3,9,20,null,null,15,7]
 * Output: [[3],[9,20],[15,7]]
 *
 * Example 2:
 * Input: root = [1]
 * Output: [[1]]
 *
 * Example 3:
 * Input: root = []
 * Output: []
 * <br>
 * <a href="https://leetcode.com/problems/binary-tree-level-order-traversal/">102. Binary Tree Level Order Traversal</a>
 */
public class _102_BinaryTreeLevelOrderTraversal {
    public List<List<Integer>> practice(TreeNode root) {
        LinkedList<TreeNode> currentLevel = new LinkedList<>();
        LinkedList<TreeNode> nextLevel = new LinkedList<>();
        currentLevel.addLast(root);
        List<Integer> levelList = new LinkedList<>();
        List<List<Integer>> result = new LinkedList<>();
        while (!currentLevel.isEmpty()) {
            TreeNode current = currentLevel.removeFirst();
            if (current != null) {
                levelList.add(current.val);
                nextLevel.addLast(current.left);
                nextLevel.addLast(current.right);
            }
            if (currentLevel.isEmpty() && !levelList.isEmpty()) {
                result.add(levelList);
                levelList = new LinkedList<>();
                currentLevel = nextLevel;
                nextLevel = new LinkedList<>();
            }
        }
        return result;
    }

    public List<List<Integer>> BFS(TreeNode root) {
        List<List<Integer>> result = new LinkedList<>();
        if (root == null) {
            return result;
        }
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.addLast(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> levelList = new LinkedList<>();
            for (int i = 0; i < size; i++) {
                TreeNode current = queue.removeFirst();
                levelList.add(current.val);
                if (current.left != null) {
                    queue.addLast(current.left);
                }
                if (current.right != null) {
                    queue.addLast(current.right);
                }
            }
            result.add(levelList);
        }
        return result;
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

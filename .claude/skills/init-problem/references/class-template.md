# Java Class Template

Use this template when creating new LeetCode problem classes.

## Template

```java
package problems.{package};

// TODO: Add pattern imports (e.g., import patterns.TreePattern;)
// TODO: Add difficulty import (e.g., import difficulty.Medium;)

/**
 * TODO: Add problem description
 *
 * <a href="https://leetcode.com/problems/{kebab-case-name}/">{number}. {Problem Name}</a>
 */
public class _{number:04d}_{PascalCaseName} /* TODO: implements PatternInterface, DifficultyInterface */ {

    // TODO: Add solution method(s)
}
```

## Template Variables

| Variable | Description | Example |
|----------|-------------|---------|
| `{package}` | Package directory name | `p0201_0300` |
| `{number}` | Problem number (raw) | `235` |
| `{number:04d}` | Zero-padded problem number | `0235` |
| `{PascalCaseName}` | Problem name in PascalCase | `LowestCommonAncestorOfABinarySearchTree` |
| `{Problem Name}` | Original problem name with spaces | `Lowest Common Ancestor of a Binary Search Tree` |
| `{kebab-case-name}` | Problem name for URL | `lowest-common-ancestor-of-a-binary-search-tree` |

## Naming Conversions

### PascalCase Conversion

1. Split problem name by spaces and special characters
2. Capitalize first letter of each word
3. Remove non-alphanumeric characters
4. Join without separators

Example: `Lowest Common Ancestor of a Binary Search Tree` → `LowestCommonAncestorOfABinarySearchTree`

### Kebab-case Conversion

1. Convert to lowercase
2. Replace spaces with hyphens
3. Remove special characters except hyphens

Example: `Lowest Common Ancestor of a Binary Search Tree` → `lowest-common-ancestor-of-a-binary-search-tree`

## Completed Example

For problem `235. Lowest Common Ancestor of a Binary Search Tree`:

```java
package problems.p0201_0300;

import patterns.TreePattern;
import patterns.BinarySearchPattern;
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
 *
 * Example 2:
 * Input: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 4
 * Output: 2
 *
 * <a href="https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/">235. Lowest Common Ancestor of a Binary Search Tree</a>
 */
public class _0235_LowestCommonAncestorOfABinarySearchTree implements TreePattern, BinarySearchPattern, Medium {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    /**
     * Time Complexity: O(H) where H is the height of the tree
     * Space Complexity: O(1)
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // TODO: Implement solution
        return null;
    }
}
```

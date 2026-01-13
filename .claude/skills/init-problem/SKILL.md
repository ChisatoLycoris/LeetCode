---
name: init-problem
description: This skill should be used when the user asks to "initialize a LeetCode problem", "create a new problem", "init problem", "add problem 235", or provides a problem like "235. Two Sum". Also use when updating problem description or method signature after initialization.
---

# LeetCode Problem Initialization

Initialize, create, and update LeetCode problem classes following the repository's naming conventions and structure.

## Capabilities

1. **Initialize new problem** - Create empty problem class from problem name
2. **Update description** - Add/update problem description in Javadoc
3. **Update method signature** - Add/update solution method placeholder
4. **Set patterns and difficulty** - Configure implemented interfaces

## Problem Initialization Workflow

### Parse Problem Input

Extract problem number and name from input format: `{number}. {Problem Name}`

Examples:
- `235. Lowest Common Ancestor of a Binary Search Tree` → number: 235, name: LowestCommonAncestorOfABinarySearchTree
- `1. Two Sum` → number: 1, name: TwoSum

### Calculate Location

**Package directory formula:**
```
start = ((number - 1) / 100) * 100 + 1
end = start + 99
package = p{start:04d}_{end:04d}
```

Examples:
- Problem 1 → `p0001_0100`
- Problem 235 → `p0201_0300`
- Problem 1234 → `p1201_1300`

**File naming:** `_{number:04d}_{PascalCaseName}.java`

**Full path:** `src/problems/{package}/{filename}`

### Check Existence

Use Glob to check: `src/problems/**/_NNNN_*.java`

**If exists:** Report location and stop. DO NOT overwrite.

```
Problem already exists at: problems.{package}._{NNNN}_{Name}
Path: src/problems/{package}/_{NNNN}_{Name}.java
```

### Create Problem Class

Create package directory if needed, then create Java file using template from `references/class-template.md`.

### Report and Prompt

After creation:
```
Created: src/problems/{package}/_{NNNN}_{Name}.java
Package: problems.{package}
Class: _{NNNN}_{Name}
```

Then prompt user for:
1. Problem description (paste from LeetCode)
2. Method signature
3. Patterns (see `src/patterns/` for available interfaces)
4. Difficulty (Easy, Medium, Hard from `src/difficulty/`)

## Updating Existing Problems

### Update Description

When user provides problem description:

1. Read the existing file
2. Replace the `TODO: Add problem description` section in Javadoc with formatted description
3. Include examples if provided
4. Keep the LeetCode URL link

### Update Method Signature

When user provides method signature like `public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q)`:

1. Read the existing file
2. Replace `// TODO: Add solution method(s)` with the method stub
3. Add complexity documentation placeholders
4. If method uses custom types (TreeNode, ListNode), add inner class definition

### Update Patterns and Difficulty

When user specifies patterns/difficulty:

1. Read the existing file
2. Add appropriate imports from `patterns/` and `difficulty/` packages
3. Update class declaration to implement specified interfaces
4. Remove the `/* TODO: implements ... */` comment

## Reference Files

- **`references/class-template.md`** - Java class template for new problems
- **`references/inner-classes.md`** - Common inner class definitions (TreeNode, ListNode, etc.)

## Quick Examples

**Initialize:**
```
/init-problem 235. Lowest Common Ancestor of a Binary Search Tree
```

**After creation, user might say:**
```
Description: Given a binary search tree (BST), find the lowest common ancestor...

Method: public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q)

Patterns: TreePattern, BinarySearchPattern
Difficulty: Medium
```

The skill then updates the file with all provided information.

---
name: init-problem
description: This skill should be used when the user asks to "initialize a LeetCode problem", "create a new problem", "init problem", "add problem 235", or provides a problem reference like "235", "235. Two Sum", "two-sum", or "Two Sum". Also use when updating problem description or method signature after initialization.
---

# LeetCode Problem Initialization

Initialize LeetCode problem classes following the repository's naming conventions. Problem metadata (description, difficulty, method signature, topic tags) is fetched automatically from LeetCode's GraphQL API — the user only needs to provide a problem reference.

## Capabilities

1. **Initialize new problem** - Create a fully populated problem template from just a number, slug, or name
2. **Auto-fetch metadata** - Description, difficulty, Java method signature, and pattern mapping from LeetCode
3. **Update description / signature / patterns** - Manual updates after initialization

## Problem Initialization Workflow

### Step 1: Fetch Problem Metadata

Run the fetch script with whatever reference the user gave (number, `"N. Name"`, slug, or plain name):

```bash
python3 .claude/skills/init-problem/scripts/fetch_problem.py 200
python3 .claude/skills/init-problem/scripts/fetch_problem.py "200. Number of Islands"
python3 .claude/skills/init-problem/scripts/fetch_problem.py number-of-islands
```

Output JSON fields:

| Field | Use |
|-------|-----|
| `id` | Problem number → package directory and file name |
| `title` | PascalCase conversion → class name |
| `url` | Javadoc link |
| `difficulty` | Difficulty interface (`Easy` / `Medium` / `Hard`) |
| `description` | Javadoc problem description (plain text, includes examples and constraints) |
| `javaSnippet` | Official Java signature → solution method stub |
| `patterns` | Topic tags already mapped to this repo's pattern interfaces |
| `unmappedTags` | Tags with no matching interface — report to the user (see Step 5) |

On `{"error": ...}` output (ambiguous name, not found), relay the message/candidates to the user. If the script fails entirely (offline), fall back to the manual flow: ask the user to paste description, signature, patterns, and difficulty.

### Step 2: Calculate Location

**Package directory formula:**
```
start = ((id - 1) / 100) * 100 + 1
end = start + 99
package = p{start:04d}_{end:04d}
```

**File naming:** `_{id:04d}_{PascalCaseTitle}.java` (see `references/class-template.md` for PascalCase rules)

**Full path:** `src/problems/{package}/{filename}`

### Step 3: Check Existence

Use Glob: `src/problems/**/_NNNN_*.java`

**If exists:** Report location and stop. DO NOT overwrite.

### Step 4: Create Problem Class

Create the package directory if needed, then create the Java file using the template from `references/class-template.md`, fully populated from the fetched metadata:

1. **Javadoc**: `description` text (keep examples and constraints), ending with the `<a href="{url}">{id}. {title}</a>` link
2. **implements**: all interfaces from `patterns` + the difficulty interface
3. **Method stub**: signature from `javaSnippet` (drop the `class Solution` wrapper), with literal `O(?)` complexity placeholders (`Time Complexity: O(?)` / `Space Complexity: O(?)` — never pre-fill the actual complexity, that spoils the exercise) and an empty `// TODO: Implement solution` body — NEVER implement the solution (Practice Mode)
4. **Inner classes**: if the signature uses `ListNode` / `TreeNode` / `Node`, add the definition from `references/inner-classes.md`

### Step 5: Report

```
Created: src/problems/{package}/_{NNNN}_{Name}.java
Difficulty: {difficulty}
Patterns: {patterns}
```

If `unmappedTags` is non-empty, list them and note there is no matching pattern interface. Ask the user whether to create a new pattern interface only when the tag represents a real algorithmic technique (e.g. Trie); ignore catalog-style tags (e.g. Design, Simulation) unless the user asks.

Then compile-check: `make compile`

## Updating Existing Problems

For manual updates after initialization (user provides new text):

- **Description**: Replace the description section in Javadoc, keep the LeetCode URL link
- **Method signature**: Replace/add the method stub with complexity placeholders; add inner classes if needed
- **Patterns/difficulty**: Update imports and the `implements` clause

## Test Harness

When the user reports a failing test case or asks to test against a LeetCode input, use the **debug-problem** skill instead — it builds a `main()` harness with `utils.LeetCodeInput`.

## Reference Files

- **`references/class-template.md`** - Java class template and naming conversion rules
- **`references/inner-classes.md`** - Common inner class definitions (TreeNode, ListNode, etc.)
- **`scripts/fetch_problem.py`** - LeetCode GraphQL fetcher (stdlib-only Python 3)

## Quick Examples

**All equivalent:**
```
/init-problem 200
/init-problem 200. Number of Islands
/init-problem number-of-islands
```

Result: `src/problems/p0101_0200/_0200_NumberOfIslands.java` implementing
`ArrayPattern, BreadthFirstSearchPattern, DepthFirstSearchPattern, MatrixPattern, UnionFindPattern, Medium`,
with the full problem description in Javadoc and a `public int numIslands(char[][] grid)` stub.

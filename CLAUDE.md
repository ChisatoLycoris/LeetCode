# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Important: Practice Mode

**NEVER implement solution code for LeetCode problems.** This is a practice repository - leave all TODO sections empty so the user can solve the problems themselves. Only set up problem templates with method signatures.

This rule applies to problem solutions only. Shared infrastructure under `src/utils/` (test-input parsing, debug helpers) is regular code and may be fully implemented and maintained.

## Project Overview

This is a personal LeetCode practice repository containing Java solutions organized by problem ID with pattern-based tagging using interfaces. Each problem is implemented with detailed comments, complexity analysis, and multiple solution approaches.

## Repository Structure

The repository uses pattern-based tagging with interface implementations for organized LeetCode problem solutions.

**Project Configuration Files:**
- `.classpath` - Eclipse classpath configuration (defines `src` as source folder)
- `.project` - Eclipse project configuration (enables Java nature)
- `.settings/org.eclipse.jdt.core.prefs` - Java compiler settings (Java 21)
- These files are required for jdtls (Java LSP) to recognize the project structure

**Directory Structure:**

```
src/
├── patterns/              # Pattern marker interfaces for categorization
│   ├── ArrayPattern.java
│   ├── BinarySearchPattern.java
│   ├── BreadthFirstSearchPattern.java
│   ├── DepthFirstSearchPattern.java
│   ├── DivideAndConquerPattern.java
│   ├── DynamicProgrammingPattern.java
│   ├── SlidingWindowPattern.java
│   ├── TwoPointersPattern.java
│   ├── BacktrackingPattern.java
│   ├── GreedyPattern.java
│   ├── HashTablePattern.java
│   ├── HeapPattern.java
│   ├── LinkedListPattern.java
│   ├── StackPattern.java
│   ├── QueuePattern.java
│   ├── TreePattern.java
│   ├── GraphPattern.java
│   ├── StringPattern.java
│   ├── SortingPattern.java
│   ├── BitManipulationPattern.java
│   ├── MathPattern.java
│   └── UnionFindPattern.java
│
├── difficulty/            # Difficulty marker interfaces
│   ├── Easy.java
│   ├── Medium.java
│   └── Hard.java
│
├── utils/                 # Shared debug/test infrastructure (fully implemented)
│   └── LeetCodeInput.java # Parses LeetCode test-case notation into Java data
│
└── problems/              # Problems organized by ID ranges (100 per directory)
    ├── p0001_0100/       # Problems 1-100
    ├── p0101_0200/       # Problems 101-200
    └── ...               # One directory per 100-problem range, created as needed
```

Note: the pattern interface and problem-range listings above are illustrative, not exhaustive — derive the current state from `src/patterns/` and `src/problems/` directly.

## File Naming Convention

All problem files follow the pattern: `_NNNN_ProblemName.java` where NNNN is the zero-padded 4-digit problem number.

Examples:
- `_0001_TwoSum.java` (Problem #1)
- `_0023_MergedKSortedLists.java` (Problem #23)
- `_0852_PeakIndexInAMountainArray.java` (Problem #852)

## Pattern Interface System

### Naming Convention

All pattern interfaces use the suffix "Pattern" (e.g., `StackPattern`, `StringPattern`, `ArrayPattern`) to avoid naming conflicts with Java standard library classes. This convention:
- **Prevents ambiguity**: No conflicts with `java.util.Stack`, `java.lang.String`, `java.lang.Math`, etc.
- **Improves clarity**: Makes it immediately clear these are marker interfaces for categorization
- **Enables clean imports**: You can use both `import patterns.StackPattern;` and `import java.util.Stack;` without qualification

### How It Works

Problems implement one or more pattern interfaces to indicate which algorithmic patterns they use. This allows:
- **Multi-pattern support**: A single problem can implement multiple patterns
- **IDE navigation**: Use "Find Implementations" to see all problems using a pattern
- **Type-safe categorization**: No duplication, clear organization

### Example Problem Structure

```java
package problems.p0001_0100;

import patterns.ArrayPattern;
import patterns.HashTablePattern;
import difficulty.Easy;

import java.util.HashMap;
import java.util.Map;

/**
 * Problem description...
 * <a href="https://leetcode.com/problems/two-sum/">1. Two Sum</a>
 */
public class _0001_TwoSum implements ArrayPattern, HashTablePattern, Easy {

    /**
     * Time Complexity: O(N)
     * Space Complexity: O(N)
     */
    public int[] usingHashMap(int[] nums, int target) {
        Map<Integer, Integer> visited = new HashMap<>();
        // implementation...
    }
}
```

### Pattern Interfaces

All pattern interfaces are empty marker interfaces used purely for categorization:

```java
package patterns;

/**
 * Marker interface for problems using dynamic programming.
 */
public interface DynamicProgrammingPattern {
    // Empty - used for categorization only
}
```

## Development Commands

### Using Makefile (Recommended)

The project includes a Makefile for easy compilation and execution:

```bash
# Show all available commands
make help

# Compile all Java source files
make compile
# or
make build

# Clean all compiled class files
make clean

# Run a specific problem with main() method (just use the problem number)
make run PROBLEM=0904
make run PROBLEM=0015
make run PROBLEM=0001

# List all problems that have main() methods
make list-problems

# Clean and recompile
make rebuild
```

### Manual Compilation (Alternative)

```bash
# Source sdkman before using Java (per global CLAUDE.md requirement)
source ~/.sdkman/bin/sdkman-init.sh

# Compile interfaces
javac -d out src/patterns/*.java src/difficulty/*.java

# Compile a single problem
javac -cp out -d out src/problems/p0001_0100/_0001_TwoSum.java

# Compile entire range
javac -cp out -d out src/problems/p0001_0100/*.java

# Compile all migrated code
javac -d out src/patterns/*.java src/difficulty/*.java src/problems/**/*.java
```

### Manual Running (Alternative)

```bash
# Run a problem with main() method
java -cp out problems.p0001_0100._0015_3Sum

# Run with compiled output directory
java -cp out problems.p0901_1000._0904_FruitsIntoBasket
```

### Finding Problems by Pattern

Using IDE (IntelliJ/Eclipse):
1. Navigate to pattern interface (e.g., `patterns/DynamicProgrammingPattern.java`)
2. Right-click → Find Usages / Find Implementations
3. See all problems implementing that pattern

Using command line:
```bash
# Find all Dynamic Programming problems
grep -r "implements.*DynamicProgrammingPattern" src/problems/

# Find all Easy problems using Sliding Window
grep -r "implements.*SlidingWindowPattern.*Easy\|implements.*Easy.*SlidingWindowPattern" src/problems/
```

## Current Problem Collection

Do not maintain a manual problem list here — it goes stale. Derive the current collection from the source tree:

```bash
# List all problems
ls src/problems/*/

# Count problems
ls src/problems/*/_*.java | wc -l

# List by difficulty
grep -rl "implements.*Medium" src/problems/

# List by pattern
grep -rl "implements.*DynamicProgrammingPattern" src/problems/
```

## Debugging Failing Test Cases

`utils.LeetCodeInput` parses LeetCode's test-case notation (JSON-like) directly into Java data structures, so failing test cases can be pasted verbatim into a `main()` method:

```java
import utils.LeetCodeInput;

// 直接貼上 LeetCode 的失敗測資(Java 21 text block 免跳脫引號)
char[][] grid = LeetCodeInput.parseCharMatrix("""
        [["1","1","0"],["0","1","0"]]
        """);
int[] nums = LeetCodeInput.parseIntArray("nums = [2,7,11,15]"); // 允許 "name =" 前綴
int[][] edges = LeetCodeInput.parseIntMatrix("[[1,2],[2,3]]");
Integer[] tree = LeetCodeInput.parseIntegerArray("[6,2,8,null,4]"); // 保留 null,供建樹使用
String[] words = LeetCodeInput.parseStringArray("[\"flower\",\"flow\"]");
List<List<Integer>> lists = LeetCodeInput.parseIntListList("[[1,2],[3]]");

System.out.println(LeetCodeInput.gridToString(grid)); // 格網易讀輸出
```

When the user reports a failing test case, use the **debug-problem** skill (`.claude/skills/debug-problem/`). In short: add a `main()` harness that parses the pasted test case via `LeetCodeInput`, call each solution method with deep-copied inputs, run `make compile && make run PROBLEM=NNNN`, and compare against the expected answer. Writing the harness is allowed; fixing the solution body is not (Practice Mode).

## Code Patterns and Conventions

1. **Multiple solution approaches**: Files typically contain multiple methods showing different algorithmic approaches (brute force, optimized, different patterns)

2. **Complexity documentation**: Each method includes time and space complexity in Javadoc comments

3. **Descriptive method names**: Method names indicate the approach used (e.g., `usingHashMap`, `slidingWindow`, `practice`, `optimal`)

4. **External references**: Some solutions include links to external sources or explanations in comments

5. **Inner classes**: Problems often define helper data structures (ListNode, TreeNode) as inner classes to avoid common package conflicts

## Working with This Codebase

### Adding New Solutions

1. Determine the appropriate directory based on problem number
2. Create file with 4-digit zero-padded naming: `_NNNN_ProblemName.java`
3. Add package declaration matching directory structure
4. Import relevant pattern and difficulty interfaces
5. Implement appropriate interfaces based on solution approach
6. Include problem description, examples, and LeetCode URL in Javadoc
7. Document time/space complexity for each solution method
8. Add a `main()` method for testing if helpful

### Analyzing Existing Solutions

- Check for multiple solution approaches within the same file
- Look for complexity trade-offs between different methods
- Use IDE's "Find Implementations" on pattern interfaces to discover related problems
- Pattern interfaces indicate the primary techniques used, even if problem appears in multiple categories

## Tips

- **Finding patterns**: Right-click on any pattern interface and use "Find Implementations"
- **Discovering similar problems**: Look at what other patterns a problem implements
- **Learning progression**: Filter by difficulty interfaces to practice incrementally
- **No common package**: Data structures like ListNode/TreeNode are defined within each problem class to avoid dependency management
- **Scalable design**: Each directory holds 100 problems, easily supporting up to 10,000 problems

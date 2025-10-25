# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

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
│   └── MathPattern.java
│
├── difficulty/            # Difficulty marker interfaces
│   ├── Easy.java
│   ├── Medium.java
│   └── Hard.java
│
└── problems/              # Problems organized by ID ranges (100 per directory)
    ├── p0001_0100/       # Problems 1-100
    ├── p0101_0200/       # Problems 101-200
    ├── p0201_0300/       # Problems 201-300
    ├── p0601_0700/       # Problems 601-700
    ├── p0801_0900/       # Problems 801-900
    └── p0901_1000/       # Problems 901-1000
```

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

**30 problems fully migrated** across different difficulty levels and patterns:

### By Difficulty
- **Easy**: 1, 13, 14, 20, 21, 111, 637, 643, 852
- **Medium**: 2, 3, 5, 7, 8, 11, 15, 17, 19, 23, 53, 91, 102, 139, 167, 198, 200, 207, 209, 210, 904
- **Hard**: 23

### By Pattern (Examples)
- **Dynamic Programming**: 5, 53, 91, 139, 198
- **Sliding Window**: 3, 209, 643, 904
- **Two Pointers**: 11, 15, 19, 20, 21, 167, 209
- **BFS/DFS**: 102, 111, 200, 207, 210, 637
- **Binary Search**: 167, 852
- **Tree**: 102, 111, 637
- **Graph**: 200, 207, 210

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

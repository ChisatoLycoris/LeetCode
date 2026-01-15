---
name: review-solution
description: This skill should be used when the user asks to "review my solution", "check complexity", "analyze my leetcode solution", "verify time complexity", "verify space complexity", "is my solution optimal", "review problem 141", "suggest optimizations", or wants feedback on a solved LeetCode problem without modifying the code.
---

# LeetCode Solution Review

Analyze and review solved LeetCode problems, verify complexity analysis, suggest optimizations, and identify alternative approaches—all without modifying the implementation.

## Capabilities

1. **Complexity Verification** - Validate time/space complexity in Javadoc is accurate
2. **Alternative Approaches** - Identify other ways to solve the problem
3. **Optimization Suggestions** - Spot potential improvements
4. **Pattern Recognition** - Confirm appropriate patterns are used

## Important Constraint

**DO NOT modify the user's code.** This skill provides analysis and feedback only. All suggestions should be communicated in the session response, not as file edits.

## Review Workflow

### Step 1: Locate the Problem File

Parse problem input (number or file path) and locate the solution file.

**From problem number:**
```
Problem 141 → src/problems/p0101_0200/_0141_*.java
Problem 1 → src/problems/p0001_0100/_0001_*.java
```

**Formula for package:**
```
start = ((number - 1) / 100) * 100 + 1
end = start + 99
package = p{start:04d}_{end:04d}
```

Use Glob: `src/problems/**/_NNNN_*.java`

### Step 2: Read and Analyze the Solution

Read the entire file and extract:
- Problem description from Javadoc
- Implemented patterns (from `implements` clause)
- All solution methods with their complexity annotations
- The actual implementation code

### Step 3: Verify Complexity

For each method with complexity annotations:

1. **Analyze the code structure:**
   - Count nested loops and their bounds
   - Identify recursive calls and their recurrence relation
   - Check data structure operations (HashMap, TreeMap, etc.)
   - Identify any sorting or searching operations

2. **Calculate actual complexity:**
   - Time: Based on iterations, recursion depth, and operation costs
   - Space: Based on additional data structures, recursion stack, output size

3. **Compare with documented complexity:**
   - If correct: Confirm the analysis
   - If incorrect: Explain the discrepancy with reasoning

Consult `references/complexity-patterns.md` for common complexity patterns.

### Step 4: Identify Alternative Approaches

Based on the problem type and patterns, consider:

1. **Different algorithmic approaches:**
   - Could Two Pointers replace HashSet?
   - Would Dynamic Programming be more efficient than recursion?
   - Is there a mathematical/closed-form solution?

2. **Data structure alternatives:**
   - TreeMap vs HashMap trade-offs
   - Array vs LinkedList for the use case
   - Stack vs recursion

3. **Space-time trade-offs:**
   - Can precomputation reduce time complexity?
   - Can streaming reduce space complexity?

### Step 5: Suggest Optimizations

Look for:
- Redundant operations inside loops
- Unnecessary object creation
- Early termination opportunities
- Better data structure choices
- Constant factor improvements

### Step 6: Generate Review Report

Structure the response as:

```
## Solution Review: {Problem Number}. {Problem Name}

### Complexity Analysis

**Method: `{methodName}`**
- Documented: Time O(?), Space O(?)
- Verified: [Correct/Incorrect]
- Analysis: {explanation of why the complexity is what it is}

### Alternative Approaches

1. **{Approach Name}**
   - Time: O(?)
   - Space: O(?)
   - Trade-off: {pros and cons vs current solution}

### Optimization Opportunities

- {specific suggestion 1}
- {specific suggestion 2}

### Pattern Assessment

Current patterns: {list from implements}
Assessment: {whether patterns are appropriate}

### Summary

{brief overall assessment - is the solution good, optimal, or could be improved}
```

## Quick Examples

**User says:** "review problem 141"

1. Glob for `src/problems/**/_0141_*.java`
2. Read the file
3. Analyze the `hasCycle` method
4. Verify O(N) time, O(1) space for Floyd's algorithm
5. Note HashSet alternative with O(N) space trade-off
6. Report findings

**User says:** "check if my Two Sum complexity is correct"

1. Find `_0001_TwoSum.java`
2. Read and analyze each method
3. Verify HashMap approach is O(N) time, O(N) space
4. Verify brute force is O(N²) time, O(1) space
5. Report findings

## Reference Files

For detailed complexity analysis patterns, consult:
- **`references/complexity-patterns.md`** - Common complexity patterns by data structure and algorithm type

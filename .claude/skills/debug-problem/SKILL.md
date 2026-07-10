---
name: debug-problem
description: This skill should be used when the user reports a failing LeetCode test case, asks to "debug problem 200", "reproduce this testcase", "my solution failed on this input", "why does my code return X", or pastes LeetCode test-case notation (e.g. [["1","0"],["1","1"]]) with or without an expected output. Builds a local repro harness without modifying the solution code.
---

# LeetCode Problem Debugging

Reproduce a failing LeetCode test case locally by building a `main()` harness that parses the pasted test input with `utils.LeetCodeInput`, runs every solution method, and compares actual vs expected output.

## Important Constraints

1. **NEVER modify the solution method bodies** (Practice Mode). Only the `main()` harness and any input-building helper code may be written.
2. **Do not reveal the fix.** Report *which* method fails and *what* it returns; describe observed behavior (e.g., "returns 3 on inputs where diagonal-adjacent land exists"). Let the user find the bug. Give hints only if the user explicitly asks for them.

## Debug Workflow

### Step 1: Locate the Problem File

Same formula as init-problem:

```
start = ((number - 1) / 100) * 100 + 1
end = start + 99
package = p{start:04d}_{end:04d}
```

Glob: `src/problems/**/_NNNN_*.java`

### Step 2: Gather Inputs

Required from the user:
- The failing test input (LeetCode notation, pasted verbatim)
- The expected output

If the expected output is missing, ask for it — do NOT compute it by solving the problem (that violates Practice Mode). LeetCode always shows the expected value on a failed submission.

### Step 3: Build the Harness

Add (or extend) `public static void main(String[] args)` in the problem class:

1. Parse each input with `utils.LeetCodeInput` — paste the notation verbatim inside a Java 21 text block (no quote escaping needed). See `references/harness-template.md` for the full template and supported types.
2. Call **every** public solution method in the file, printing each result next to the expected output.
3. **Deep-copy mutable inputs between calls.** Many solutions mutate their input (e.g., flood-fill marking visited cells in a `char[][]`); reusing the same array makes the second method receive corrupted data and report a false failure.
4. For `ListNode`/`TreeNode` inputs, write small builder helpers inside `main()`'s class using the problem's own inner classes (see `references/harness-template.md`).

### Step 4: Run and Compare

```bash
make compile && make run PROBLEM=NNNN
```

### Step 5: Report

Structure the report as:

```
## Debug Report: {Number}. {Name}

Test input: {short description, e.g. 10x10 grid}
Expected: {expected}

| Method | Result | Status |
|--------|--------|--------|
| dfs    | 2      | PASS   |
| bfs    | 3      | FAIL   |

Observations: {which method diverges; observable symptom without revealing the fix}
```

Keep the harness in place after debugging — the repo convention allows `main()` methods for testing, and it serves as a regression check for that test case.

## Reference Files

- **`references/harness-template.md`** - Full `main()` harness template, `LeetCodeInput` API summary, ListNode/TreeNode builder snippets

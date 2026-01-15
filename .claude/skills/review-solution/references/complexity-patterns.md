# Complexity Patterns Reference

Quick reference for analyzing time and space complexity of common patterns.

## Data Structure Operations

### HashMap / HashSet
| Operation | Average | Worst |
|-----------|---------|-------|
| get/put/contains | O(1) | O(N) |
| iteration | O(N) | O(N) |

**Space:** O(N) for N elements

### TreeMap / TreeSet
| Operation | Time |
|-----------|------|
| get/put/contains | O(log N) |
| iteration | O(N) |
| first/last | O(log N) |

**Space:** O(N) for N elements

### Array / ArrayList
| Operation | Time |
|-----------|------|
| access by index | O(1) |
| add at end | O(1) amortized |
| add at index | O(N) |
| contains/indexOf | O(N) |

### LinkedList
| Operation | Time |
|-----------|------|
| add at head/tail | O(1) |
| access by index | O(N) |
| contains | O(N) |

### Stack / Queue (ArrayDeque)
| Operation | Time |
|-----------|------|
| push/pop/peek | O(1) |
| offer/poll/peek | O(1) |

### PriorityQueue (Heap)
| Operation | Time |
|-----------|------|
| offer (insert) | O(log N) |
| poll (extract) | O(log N) |
| peek | O(1) |
| heapify N elements | O(N) |

## Algorithm Patterns

### Two Pointers
- **Time:** O(N) - single pass with two pointers
- **Space:** O(1) - only pointer variables
- **Examples:** Cycle detection, palindrome check, sorted array problems

### Sliding Window
- **Time:** O(N) - each element enters/exits window once
- **Space:** O(K) where K is window size, or O(1) for fixed window
- **Examples:** Maximum sum subarray, longest substring

### Binary Search
- **Time:** O(log N)
- **Space:** O(1) iterative, O(log N) recursive
- **Examples:** Search in sorted array, find boundary

### BFS (Breadth-First Search)
- **Time:** O(V + E) for graphs, O(N) for trees
- **Space:** O(W) where W is maximum width of tree/level
- **Examples:** Level order traversal, shortest path unweighted

### DFS (Depth-First Search)
- **Time:** O(V + E) for graphs, O(N) for trees
- **Space:** O(H) where H is height (recursion stack)
- **Examples:** Path finding, tree traversals, cycle detection

### Dynamic Programming
- **Time:** O(states × transition cost)
- **Space:** O(states), can often optimize to O(previous states needed)
- **Examples:**
  - 1D DP: O(N) time, O(N) or O(1) space
  - 2D DP: O(N×M) time, O(N×M) or O(M) space

### Backtracking
- **Time:** O(K^N) or O(N!) depending on branching factor
- **Space:** O(N) for recursion depth
- **Examples:** Permutations, combinations, N-Queens

### Divide and Conquer
- **Time:** Usually O(N log N)
- **Space:** O(log N) to O(N) depending on implementation
- **Examples:** Merge sort, quick sort, binary search

## Loop Analysis

### Single Loop
```java
for (int i = 0; i < n; i++) { } // O(N)
```

### Nested Loops
```java
for (int i = 0; i < n; i++) {
    for (int j = 0; j < n; j++) { } // O(N²)
}
```

```java
for (int i = 0; i < n; i++) {
    for (int j = 0; j < m; j++) { } // O(N×M)
}
```

```java
for (int i = 0; i < n; i++) {
    for (int j = i; j < n; j++) { } // O(N²) - triangle pattern
}
```

### Logarithmic Loops
```java
for (int i = 1; i < n; i *= 2) { } // O(log N)
for (int i = n; i > 0; i /= 2) { } // O(log N)
```

### Nested with Logarithmic
```java
for (int i = 0; i < n; i++) {
    for (int j = 1; j < n; j *= 2) { } // O(N log N)
}
```

## Recursion Analysis

### Linear Recursion
```java
void f(int n) {
    if (n <= 0) return;
    f(n - 1);  // O(N) time, O(N) space
}
```

### Binary Recursion (Tree-like)
```java
void f(int n) {
    if (n <= 0) return;
    f(n - 1);
    f(n - 1);  // O(2^N) time, O(N) space
}
```

### Divide and Conquer Recursion
```java
void f(int n) {
    if (n <= 1) return;
    f(n / 2);
    f(n / 2);  // O(N log N) if O(N) work per level
}
```

### Master Theorem Quick Reference
For T(n) = aT(n/b) + O(n^d):
- If d < log_b(a): O(n^log_b(a))
- If d = log_b(a): O(n^d log n)
- If d > log_b(a): O(n^d)

## Common Problem Patterns

### Linked List Cycle Detection
- **Floyd's (Fast/Slow):** O(N) time, O(1) space
- **HashSet:** O(N) time, O(N) space

### Two Sum Variants
- **HashMap:** O(N) time, O(N) space
- **Sorted + Two Pointers:** O(N log N) time, O(1) space
- **Brute Force:** O(N²) time, O(1) space

### String Matching
- **Brute Force:** O(N×M)
- **KMP:** O(N+M)
- **Rabin-Karp:** O(N+M) average

### Tree Traversal
- **Any order:** O(N) time
- **Recursive:** O(H) space
- **Iterative with Stack:** O(H) space
- **Morris:** O(1) space

### Graph Traversal
- **BFS/DFS:** O(V+E) time, O(V) space

### Sorting-Based Problems
- **Sort + Process:** O(N log N) time
- **Counting/Bucket Sort:** O(N+K) time, O(K) space

## Space Complexity Checklist

When calculating space complexity, consider:

1. **Explicit data structures:** HashMap, arrays, lists created
2. **Recursion stack:** Maximum depth × frame size
3. **Input modification:** Does it modify input or create copy?
4. **Output size:** Sometimes included, sometimes excluded

**Convention:** Usually exclude input and output space, count only auxiliary space.

## Common Mistakes

### Mistake 1: Ignoring Hidden Loops
```java
// This is O(N²), not O(N)
for (String s : list) {
    if (list.contains(target)) { } // contains is O(N)
}
```

### Mistake 2: String Concatenation in Loop
```java
// This is O(N²) due to string immutability
String result = "";
for (int i = 0; i < n; i++) {
    result += str; // Creates new string each time
}
```

### Mistake 3: Recursion Space
```java
// Space is O(N), not O(1)
int sum(int[] arr, int i) {
    if (i >= arr.length) return 0;
    return arr[i] + sum(arr, i + 1);
}
```

### Mistake 4: Amortized vs Worst Case
- ArrayList.add(): O(1) amortized, O(N) worst case
- HashMap operations: O(1) average, O(N) worst case

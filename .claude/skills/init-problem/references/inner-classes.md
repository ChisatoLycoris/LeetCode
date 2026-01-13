# Common Inner Class Definitions

LeetCode problems often use custom data structures. Define these as inner classes to avoid package conflicts.

## TreeNode

For binary tree problems:

```java
public class TreeNode {
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
```

## ListNode

For linked list problems:

```java
public class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}
```

## Node (N-ary Tree)

For N-ary tree problems:

```java
public class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int val) {
        this.val = val;
    }

    public Node(int val, List<Node> children) {
        this.val = val;
        this.children = children;
    }
}
```

## Node (Graph with neighbors)

For graph problems with neighbor lists:

```java
public class Node {
    public int val;
    public List<Node> neighbors;

    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }

    public Node(int val) {
        this.val = val;
        neighbors = new ArrayList<Node>();
    }

    public Node(int val, ArrayList<Node> neighbors) {
        this.val = val;
        this.neighbors = neighbors;
    }
}
```

## Node (Doubly Linked List)

For problems requiring doubly linked lists:

```java
public class Node {
    public int val;
    public Node prev;
    public Node next;
    public Node child;

    public Node() {}

    public Node(int val) {
        this.val = val;
    }

    public Node(int val, Node prev, Node next, Node child) {
        this.val = val;
        this.prev = prev;
        this.next = next;
        this.child = child;
    }
}
```

## Node (Random Pointer)

For problems with random pointer (e.g., Copy List with Random Pointer):

```java
public class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
```

## Detection by Method Signature

Determine which inner class to add based on method parameter types:

| Parameter Type | Inner Class |
|----------------|-------------|
| `TreeNode` | TreeNode (binary tree) |
| `ListNode` | ListNode (singly linked list) |
| `Node` with `children` | Node (N-ary tree) |
| `Node` with `neighbors` | Node (graph) |
| `Node` with `prev`/`next` | Node (doubly linked) |
| `Node` with `random` | Node (random pointer) |

## Placement

Place inner class definitions after the class declaration, before any solution methods:

```java
public class _0235_Problem implements TreePattern, Medium {

    // Inner class definitions first
    public class TreeNode {
        // ...
    }

    // Then solution methods
    public TreeNode solution(TreeNode root) {
        // ...
    }

    // Main method last
    public static void main(String[] args) {
        // ...
    }
}
```

package problems.p0101_0200;

import patterns.GraphPattern;
import patterns.BreadthFirstSearchPattern;
import patterns.DepthFirstSearchPattern;
import patterns.HashTablePattern;
import difficulty.Medium;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Given a reference of a node in a connected undirected graph.
 *
 * Return a deep copy (clone) of the graph.
 *
 * Each node in the graph contains a value (int) and a list (List[Node]) of its
 * neighbors.
 *
 * Example 1:
 * Input: adjList = [[2,4],[1,3],[2,4],[1,3]]
 * Output: [[2,4],[1,3],[2,4],[1,3]]
 * Explanation: There are 4 nodes in the graph.
 * 1st node (val = 1)'s neighbors are 2nd node (val = 2) and 4th node (val = 4).
 * 2nd node (val = 2)'s neighbors are 1st node (val = 1) and 3rd node (val = 3).
 * 3rd node (val = 3)'s neighbors are 2nd node (val = 2) and 4th node (val = 4).
 * 4th node (val = 4)'s neighbors are 1st node (val = 1) and 3rd node (val = 3).
 *
 * Example 2:
 * Input: adjList = [[]]
 * Output: [[]]
 * Explanation: The graph consists of only one node with val = 1 and it does not
 * have any neighbors.
 *
 * Example 3:
 * Input: adjList = []
 * Output: []
 * Explanation: This is an empty graph, it does not have any nodes.
 *
 * Constraints:
 * - The number of nodes in the graph is in the range [0, 100].
 * - 1 <= Node.val <= 100
 * - Node.val is unique for each node.
 * - There are no repeated edges and no self-loops in the graph.
 * - The Graph is connected and all nodes can be visited starting from the given
 * node.
 *
 * <a href="https://leetcode.com/problems/clone-graph/">133. Clone Graph</a>
 */
public class _0133_CloneGraph
        implements GraphPattern, BreadthFirstSearchPattern, DepthFirstSearchPattern, HashTablePattern, Medium {

    public class Node {
        public int val;
        public List<Node> neighbors;

        public Node() {
            val = 0;
            neighbors = new ArrayList<>();
        }

        public Node(int val) {
            this.val = val;
            neighbors = new ArrayList<>();
        }

        public Node(int val, ArrayList<Node> neighbors) {
            this.val = val;
            this.neighbors = neighbors;
        }
    }

    /**
     * Time Complexity: O(V+E)
     * Space Complexity: O(1) for extra space
     */
    public Node cloneGraph(Node node) {
        if (node == null)
            return null;
        Node[] clonedMap = new Node[101];
        return cloneNode(node, clonedMap);
    }

    private Node cloneNode(Node node, Node[] clonedMap) {
        Node cloned = clonedMap[node.val];
        if (cloned != null)
            return cloned;
        cloned = new Node(node.val);
        clonedMap[node.val] = cloned;
        for (Node neighbor : node.neighbors) {
            cloned.neighbors.add(cloneNode(neighbor, clonedMap));
        }
        return cloned;
    }

    /**
     * Time Complexity: O(V+E)
     * Space Complexity: O(V+E) for extra space
     */
    public Node cloneGraph_BFS(Node node) {
        if (node == null)
            return null;
        Node[] clonedMap = new Node[101];
        clonedMap[node.val] = new Node(node.val);
        Queue<Node> queue = new LinkedList<>();
        queue.offer(node);
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            for (Node neighbor : cur.neighbors) {
                if (clonedMap[neighbor.val] == null) {
                    clonedMap[neighbor.val] = new Node(neighbor.val);
                    queue.offer(neighbor);
                }
                clonedMap[cur.val].neighbors.add(clonedMap[neighbor.val]);
            }
        }
        return clonedMap[node.val];
    }
}

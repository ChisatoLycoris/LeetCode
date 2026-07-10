package problems.p0101_0200;

import patterns.BreadthFirstSearchPattern;
import patterns.DepthFirstSearchPattern;
import patterns.GraphPattern;
import patterns.UnionFindPattern;
import patterns.ArrayPattern;
import difficulty.Medium;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Given an m x n 2D binary grid grid which represents a map of '1's (land) and '0's (water), return the number of islands.
 *
 * An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically.
 * You may assume all four edges of the grid are all surrounded by water.
 *
 * Example 1:
 * Input: grid = [
 *   ["1","1","1","1","0"],
 *   ["1","1","0","1","0"],
 *   ["1","1","0","0","0"],
 *   ["0","0","0","0","0"]
 * ]
 * Output: 1
 *
 *
 * Input: grid = [
 *   ["1","1","0","0","0"],
 *   ["1","1","0","0","0"],
 *   ["0","0","1","0","0"],
 *   ["0","0","0","1","1"]
 * ]
 * Output: 3
 * <br>
 * <a href="https://leetcode.com/problems/number-of-islands/">200. Number of Islands</a>
 */
public class _0200_NumberOfIslands implements BreadthFirstSearchPattern, DepthFirstSearchPattern, GraphPattern, UnionFindPattern, ArrayPattern, Medium {

    /**
     * Time Complexity: O(m * n)
     * Space Complexity: O(m * n)
     */
    public int dfs(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int result = 0;
        boolean[][] checked = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j  = 0; j < n; j++) {
                if (checked[i][j]) {
                    continue;
                }
                if (grid[i][j] == '0') {
                    checked[i][j] =  true;
                } else {
                    result += 1;
                    markIslandDfs(i, j, grid, checked);
                }
            }
        }
        return result;
    }

    private void markIslandDfs(int i, int j, char[][] grid, boolean[][] checked) {
        if (i < 0 || i >= checked.length || j < 0 || j >= checked[0].length) {
            return;
        }
        if (checked[i][j] || grid[i][j] == '0') {
            return;
        }
        checked[i][j] = true;
        markIslandDfs(i + 1, j, grid, checked);
        markIslandDfs(i - 1, j, grid, checked);
        markIslandDfs(i, j + 1, grid, checked);
        markIslandDfs(i, j - 1, grid, checked);
    }

    /**
     * Time Complexity: O(m * n)
     * Space Complexity: O(m * n)
     */
    public int bfs(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int result = 0;
        boolean[][] checked = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j  = 0; j < n; j++) {
                if (checked[i][j]) {
                    continue;
                }
                checked[i][j] =  true;
                if (grid[i][j] == '1') {
                    result += 1;
                    Queue<Integer> queue = new LinkedList<>();
                    queue.add(i * n + j);
                    markIslandBfs(queue, grid, checked);
                }
            }
        }
        return result;
    }

    private void markIslandBfs(Queue<Integer> queue, char[][] grid, boolean[][] checked) {
        while (!queue.isEmpty()) {
            int target = queue.poll();
            int n = grid[0].length;
            int i = target / n;
            int j = target % n;
            if (grid[i][j] == '0') {
                continue;
            }
            if (i > 0 && !checked[i - 1][j]) {
                checked[i - 1][j] = true;
                queue.add(i * n - n + j);
            }
            if (i < grid.length - 1 && !checked[i + 1][j]) {
                checked[i + 1][j]  = true;
                queue.add(i * n + n + j);
            }
            if (j > 0 && !checked[i][j - 1]) {
                checked[i][j - 1] = true;
                queue.add(i * n + j - 1);
            }
            if (j < grid[0].length - 1 && !checked[i][j + 1]) {
                checked[i][j + 1] = true;
                queue.add(i * n + j + 1);
            }
        }
    }

    /**
     * Time Complexity: O(m * n)
     * Space Complexity: O(m * n)
     */

    public int unionFind(char[][] grid) {
        int count = grid.length * grid[0].length;
        int[] parent = new int[count];
        Arrays.fill(parent, count);
        for (int i =  0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '0') {
                    count--;
                    continue;
                }
                int cur = i * grid[0].length + j;
                if (i < grid.length - 1 && grid[i + 1][j] == '1') {
                    int down = (i + 1) * grid[0].length + j;
                    if (union(cur, down, parent)) {
                        count--;
                    }
                }
                if (j < grid[0].length -1 && grid[i][j + 1] == '1') {
                    int right = i * grid[0].length + j + 1;
                    if (union(cur, right, parent)) {
                        count--;
                    }
                }
            }
        }
        return count;
    }

    private int find(int x, int[] parent) {
        if (parent[x] == parent.length) {
            parent[x] = x;
        }
        while(parent[x] != x) {
            parent[x] = parent[parent[x]];
            x = parent[x];
        }
        return x;
    }

    private boolean union(int x, int y, int[] parent)  {
        int rx = find(x, parent);
        int ry = find(y, parent);
        if (rx == ry) {
            return false;
        }
        parent[ry] = parent[rx];
        return true;
    }
}

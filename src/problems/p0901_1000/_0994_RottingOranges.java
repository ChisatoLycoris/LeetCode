package problems.p0901_1000;

import patterns.ArrayPattern;
import patterns.BreadthFirstSearchPattern;
import patterns.MatrixPattern;

import java.util.ArrayDeque;
import java.util.Queue;

import difficulty.Medium;

/**
 * You are given an m x n grid where each cell can have one of three values:
 *
 * - 0 representing an empty cell,
 * - 1 representing a fresh orange, or
 * - 2 representing a rotten orange.
 *
 * Every minute, any fresh orange that is 4-directionally adjacent to a rotten orange becomes rotten.
 *
 * Return the minimum number of minutes that must elapse until no cell has a fresh orange.
 * If this is impossible, return -1.
 *
 * Example 1:
 * Input: grid = [[2,1,1],[1,1,0],[0,1,1]]
 * Output: 4
 *
 * Example 2:
 * Input: grid = [[2,1,1],[0,1,1],[1,0,1]]
 * Output: -1
 * Explanation: The orange in the bottom left corner (row 2, column 0) is never rotten,
 * because rotting only happens 4-directionally.
 *
 * Example 3:
 * Input: grid = [[0,2]]
 * Output: 0
 * Explanation: Since there are already no fresh oranges at minute 0, the answer is just 0.
 *
 * Constraints:
 * - m == grid.length
 * - n == grid[i].length
 * - 1 <= m, n <= 10
 * - grid[i][j] is 0, 1, or 2.
 *
 * <a href="https://leetcode.com/problems/rotting-oranges/">994. Rotting Oranges</a>
 */
public class _0994_RottingOranges implements ArrayPattern, BreadthFirstSearchPattern, MatrixPattern, Medium {

    /**
     * Time Complexity: O(m * n)
     * Space Complexity: O(m * n)
     */
    public int orangesRotting(int[][] grid) {
        int height = grid.length;
        int width = grid[0].length;
        int fresh = 0;
        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < width; j++) {
                if (grid[i][j] == 2) {
                    queue.add(i * width + j);
                }
                if (grid[i][j] == 1) {
                    fresh ++;
                }
            }
        }
        int time = 0;
        while (!queue.isEmpty()) {
            for (int k = queue.size(); k > 0; k--) {
                int n = queue.poll();
                int i = n / width;
                int j = n % width;
                if (i > 0 && grid[i - 1][j] == 1) {
                    grid[i - 1][j] = 2;
                    queue.add(n - width);
                    fresh--;
                }
                if (i < height - 1 && grid[i + 1][j] == 1) {
                    grid[i + 1][j] = 2;
                    queue.add(n + width);
                    fresh--;
                }
                if (j > 0 && grid[i][j - 1] == 1) {
                    grid[i][j - 1] = 2;
                    queue.add(n - 1);
                    fresh--;
                }
                if (j < width - 1 && grid[i][j + 1] == 1) {
                    grid[i][j + 1] = 2;
                    queue.add(n + 1);
                    fresh--;
                }
            }
            if (!queue.isEmpty()) {
                time++;
            }
        }
        return fresh == 0 ? time : -1;
    }
}

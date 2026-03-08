package problems.p0501_0600;

import difficulty.Medium;
import patterns.BreadthFirstSearchPattern;
import patterns.DynamicProgrammingPattern;
import patterns.MatrixPattern;

import java.util.Queue;
import java.util.ArrayDeque;

/**
 * Given an m x n binary matrix mat, return the distance of the nearest 0 for
 * each cell.
 *
 * The distance between two cells sharing a common edge is 1.
 *
 * Example 1:
 * Input: mat = [[0,0,0],[0,1,0],[0,0,0]]
 * Output: [[0,0,0],[0,1,0],[0,0,0]]
 *
 * Example 2:
 * Input: mat = [[0,0,0],[0,1,0],[1,1,1]]
 * Output: [[0,0,0],[0,1,0],[1,2,1]]
 *
 * Constraints:
 * - m == mat.length
 * - n == mat[i].length
 * - 1 <= m, n <= 10^4
 * - 1 <= m * n <= 10^4
 * - mat[i][j] is either 0 or 1.
 * - There is at least one 0 in mat.
 *
 * Note: This question is the same as 1765: Map of Highest Peak
 *
 * <a href="https://leetcode.com/problems/01-matrix/">542. 01 Matrix</a>
 */
public class _0542_01Matrix implements Medium, MatrixPattern, DynamicProgrammingPattern, BreadthFirstSearchPattern {

    /**
     * Time Complexity: O(m x n)
     * Space Complexity: O(m x n)
     * Two-pass Dynamic Programming
     */
    public int[][] updateMatrix(int[][] mat) {
        int rows = mat.length;
        int cols = mat[0].length;
        int[][] result = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (mat[i][j] != 0) {
                    result[i][j] = Integer.MAX_VALUE - 1;
                }
            }
        }
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (i > 0) {
                    result[i][j] = Math.min(result[i][j], result[i - 1][j] + 1);
                }
                if (j > 0) {
                    result[i][j] = Math.min(result[i][j], result[i][j - 1] + 1);
                }
            }
        }
        for (int i = rows - 1; i > -1; i--) {
            for (int j = cols - 1; j > -1; j--) {
                if (i < rows - 1) {
                    result[i][j] = Math.min(result[i][j], result[i + 1][j] + 1);
                }
                if (j < cols - 1) {
                    result[i][j] = Math.min(result[i][j], result[i][j + 1] + 1);
                }
            }
        }
        return result;
    }

    /**
     * Time Complexity: O(m x n)
     * Space Complexity: O(m x n)
     * Multi Source Breadth First Search
     */
    public int[][] updateMatrix_BFS(int[][] mat) {
        Queue<Integer> queue = new ArrayDeque<>();
        int[][] result = new int[mat.length][mat[0].length];
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                if (mat[i][j] == 0) {
                    queue.offer(i * mat[0].length + j);
                } else {
                    result[i][j] = -1;
                }
            }
        }
        int[][] directions = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };
        while (!queue.isEmpty()) {
            int p = queue.poll();
            int pRow = p / mat[0].length;
            int pCol = p % mat[0].length;
            for (int[] d : directions) {
                int row = pRow + d[0];
                int col = pCol + d[1];
                if (row > -1 && row < mat.length && col > -1 && col < mat[0].length
                        && result[row][col] == -1) {
                    result[row][col] = result[pRow][pCol] + 1;
                    queue.offer(row * mat[0].length + col);
                }
            }
        }
        return result;
    }
}

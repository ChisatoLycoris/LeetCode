package problems.p0101_0200;

import patterns.BreadthFirstSearchPattern;
import patterns.DepthFirstSearchPattern;
import patterns.GraphPattern;
import patterns.ArrayPattern;
import difficulty.Medium;

import java.util.LinkedList;

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
public class _0200_NumberOfIslands implements BreadthFirstSearchPattern, DepthFirstSearchPattern, GraphPattern, ArrayPattern, Medium {
    public int practice(char[][] grid) {
        boolean[] cached = new boolean[(grid.length + 2) * (grid[0].length + 2)];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == '1') {
                    int index = (i + 1) * (grid[0].length + 2) + j + 1;
                    cached[index] = true;
                }
            }
        }
        int island = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                int index = (i + 1) * (grid[0].length + 2) + j + 1;
                if (!cached[index]) {
                    continue;
                }
                island += 1;
                LinkedList<Integer> queue = new LinkedList<>();
                queue.add(index);
                while (!queue.isEmpty()) {
                    int current = queue.removeFirst();
                    if (cached[current - grid[0].length - 2]) {
                        queue.addFirst(current - grid[0].length - 2);
                        cached[current - grid[0].length - 2] = false;
                    }
                    if (cached[current - 1]) {
                        queue.addFirst(current - 1);
                        cached[current - 1] = false;
                    }
                    if (cached[current + 1]) {
                        queue.addFirst(current + 1);
                        cached[current + 1] = false;
                    }
                    if (cached[current + grid[0].length + 2]) {
                        queue.addFirst(current + grid[0].length + 2);
                        cached[current + grid[0].length + 2] = false;
                    }
                }
            }
        }
        return island;
    }

    public int practice2(char[][] grid) {
        int island = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == '0') {
                    continue;
                }
                island += 1;
                LinkedList<Integer> queue = new LinkedList<>();
                queue.addLast(i);
                queue.addLast(j);
                while (!queue.isEmpty()) {
                    int indexI = queue.removeFirst();
                    int indexJ = queue.removeFirst();
                    if (indexI > 0 && grid[indexI - 1][indexJ] == '1') {
                        queue.addLast(indexI - 1);
                        queue.addLast(indexJ);
                        grid[indexI - 1][indexJ] = '0';
                    }
                    if (indexJ > 0 && grid[indexI][indexJ - 1] == '1') {
                        queue.addLast(indexI);
                        queue.addLast(indexJ - 1);
                        grid[indexI][indexJ - 1] = '0';
                    }
                    if (indexJ < (grid[0].length - 1) && grid[indexI][indexJ + 1] == '1') {
                        queue.addLast(indexI);
                        queue.addLast(indexJ + 1);
                        grid[indexI][indexJ + 1] = '0';
                    }
                    if (indexI < (grid.length - 1) && grid[indexI + 1][indexJ] == '1') {
                        queue.addLast(indexI + 1);
                        queue.addLast(indexJ);
                        grid[indexI + 1][indexJ] = '0';
                    }
                }
            }
        }
        return island;
    }

    /**
     * <a href="https://leetcode.com/problems/number-of-islands/solutions/56359/very-concise-java-ac-solution/">source</a>
     */
    public int optimal(char[][] grid) {
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    DFSMarking(grid, i, j);
                    count++;
                }
            }
        }
        return count;
    }

    private void DFSMarking(char[][] grid, int i, int j) {
        if (i < 0 || j < 0 || i >= grid.length || j >= grid[i].length || grid[i][j] != '1') return;
        grid[i][j] = '0';
        DFSMarking(grid, i + 1, j);
        DFSMarking(grid, i - 1, j);
        DFSMarking(grid, i, j + 1);
        DFSMarking(grid, i, j - 1);
    }
}

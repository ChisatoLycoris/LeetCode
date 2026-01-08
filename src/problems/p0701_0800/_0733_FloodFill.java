package problems.p0701_0800;

import patterns.ArrayPattern;
import patterns.BreadthFirstSearchPattern;
import patterns.DepthFirstSearchPattern;
import patterns.MatrixPattern;

import java.util.LinkedList;
import java.util.List;

import difficulty.Easy;

/**
 * 733. Flood Fill
 * <a href="https://leetcode.com/problems/flood-fill/">733. Flood Fill</a>
 *
 * You are given an image represented by an m x n grid of integers image,
 * where image[i][j] represents the pixel value of the image. You are also
 * given three integers sr, sc, and color. Your task is to perform a flood
 * fill on the image starting from the pixel image[sr][sc].
 *
 * To perform a flood fill:
 * 1. Begin with the starting pixel and change its color to color.
 * 2. Perform the same process for each pixel that is directly adjacent
 * (pixels that share a side with the original pixel, either horizontally
 * or vertically) and shares the same color as the starting pixel.
 * 3. Keep repeating this process by checking neighboring pixels of the
 * updated pixels and modifying their color if it matches the original
 * color of the starting pixel.
 * 4. The process stops when there are no more adjacent pixels of the
 * original color to update.
 *
 * Return the modified image after performing the flood fill.
 *
 * Example 1:
 * Input: image = [[1,1,1],[1,1,0],[1,0,1]], sr = 1, sc = 1, color = 2
 * Output: [[2,2,2],[2,2,0],[2,0,1]]
 * Explanation: From the center of the image with position (sr, sc) = (1, 1),
 * all pixels connected by a path of the same color as the starting pixel
 * are colored with the new color. Note the bottom corner is not colored 2,
 * because it is not horizontally or vertically connected to the starting pixel.
 *
 * Example 2:
 * Input: image = [[0,0,0],[0,0,0]], sr = 0, sc = 0, color = 0
 * Output: [[0,0,0],[0,0,0]]
 * Explanation: The starting pixel is already colored with 0, which is the
 * same as the target color. Therefore, no changes are made to the image.
 *
 * Constraints:
 * - m == image.length
 * - n == image[i].length
 * - 1 <= m, n <= 50
 * - 0 <= image[i][j], color < 2^16
 * - 0 <= sr < m
 * - 0 <= sc < n
 */
public class _0733_FloodFill implements ArrayPattern, MatrixPattern, BreadthFirstSearchPattern, DepthFirstSearchPattern, Easy {

    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        int rows = image.length;
        int colums = image[0].length;
        boolean[] checked = new boolean[rows * colums];
        int colorFrom = image[sr][sc];
        List<Integer> queue = new LinkedList<>();
        queue.add(sr * colums + sc);
        while (!queue.isEmpty()) {
            floodFill(image, colorFrom, color, checked, queue);
        }
        return image;
    }

    private void floodFill(int[][] image, int colorFrom, int colorTo, boolean[] checked, List<Integer> queue) {
        int target = queue.removeFirst();
        if (checked[target]) {
            return;
        }
        checked[target] = true;
        int row = target / image[0].length;
        int column = target % image[0].length;
        if (image[row][column] == colorFrom) {
            image[row][column] = colorTo;
            if (target >= image[0].length && !checked[target - image[0].length]) {
                queue.add(target - image[0].length);
            }
            if (column != 0 && !checked[target - 1]) {
                queue.add(target - 1);
            }
            if (column != image[0].length - 1 && !checked[target + 1]) {
                queue.add(target + 1);
            }
            if (target < image[0].length * (image.length - 1) && !checked[target + image[0].length]) {
                queue.add(target + image[0].length);
            }
        }
    }

    public int[][] floodFillDfs(int[][] image, int sr, int sc, int color) {
        int target = image[sr][sc];
        if (target == color) {
            return image;
        }
        return image;
    }

    public void dfs(int r, int c, int[][] image, int target, int color) {
        if (r < 0 || r >= image.length || c < 0 || c >= image[0].length) {
            return;
        }
        if (image[r][c] != target) {
            return;
        }
        image[r][c] = color;
        dfs(r - 1, c, image, target, color);
        dfs(r, c - 1, image, target, color);
        dfs(r, c + 1, image, target, color);
        dfs(r + 1, c, image, target, color);
    }
}

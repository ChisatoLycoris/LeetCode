package problems.p0001_0100;

import patterns.ArrayPattern;
import patterns.TwoPointersPattern;
import patterns.GreedyPattern;
import difficulty.Medium;

/**
 * You are given an integer array height of length n.
 * There are n vertical lines drawn such that the two endpoints of the ith line are (i, 0) and (i, height[i]).
 *
 * Find two lines that together with the x-axis form a container, such that the container contains the most water.
 * Return the maximum amount of water a container can store.
 *
 * Notice that you may not slant the container.
 * <br>
 * <a href="https://leetcode.com/problems/container-with-most-water/">11. Container With Most Water</a>
 */
public class _0011_ContainerWithMostWater implements ArrayPattern, TwoPointersPattern, GreedyPattern, Medium {
    public int practice(int[] height) {
        int maxArea = 0;
        for (int i = 0; i < height.length; i++) {
            int left = 0;
            int right = height.length - 1;
            int area = 0;
            while (i != left || i != right) {
                if (right - i > i - left) {
                    if (height[right] >= height[i]) {
                        area = height[i] * (right - i);
                        break;
                    }
                    right -= 1;
                } else if (i - left > right - i) {
                    if (height[left] >= height[i]) {
                        area = height[i] * (i - left);
                        break;
                    }
                    left += 1;
                } else {
                    if (height[right] >= height[i]) {
                        area = height[i] * (right - i);
                        break;
                    }
                    if (height[left] >= height[i]) {
                        area = height[i] * (i - left);
                        break;
                    }
                    right -= 1;
                    left += 1;
                }
            }
            if (area > maxArea) {
                maxArea = area;
            }
        }
        return maxArea;
    }

    /**
     * Time Complexity: O(N)
     * Space Complexity: O(1)
     * <br>
     * <a href="https://leetcode.com/problems/container-with-most-water/solutions/1915172/java-c-easiest-explanations/">source</a>
     */
    public int commonSolution(int[] height) {
        int left = 0;
        int right = height.length - 1;
        int max = 0;
        while(left < right){
            int w = right - left;
            int h = java.lang.Math.min(height[left], height[right]);
            int area = h * w;
            max = java.lang.Math.max(max, area);
            if(height[left] < height[right]) left++;
            else if(height[left] > height[right]) right--;
            else {
                left++;
                right--;
            }
        }
        return max;
    }
}

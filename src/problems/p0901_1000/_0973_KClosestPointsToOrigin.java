package problems.p0901_1000;

import java.util.Arrays;
import java.util.PriorityQueue;

import difficulty.Medium;
import patterns.HeapPattern;
import patterns.SortingPattern;

// TODO: Add pattern imports (e.g., import patterns.HeapPattern;)
// TODO: Add difficulty import (e.g., import difficulty.Medium;)

/**
 * Given an array of points where points[i] = [xi, yi] represents a point on
 * the X-Y plane and an integer k, return the k closest points to the origin
 * (0, 0).
 *
 * The distance between two points on the X-Y plane is the Euclidean distance
 * (i.e., sqrt((x1 - x2)^2 + (y1 - y2)^2)).
 *
 * You may return the answer in any order. The answer is guaranteed to be unique
 * (except for the order that it is in).
 *
 * Example 1:
 * Input: points = [[1,3],[-2,2]], k = 1
 * Output: [[-2,2]]
 * Explanation:
 * The distance between (1, 3) and the origin is sqrt(10).
 * The distance between (-2, 2) and the origin is sqrt(8).
 * Since sqrt(8) < sqrt(10), (-2, 2) is closer to the origin.
 * We only want the closest k = 1 points from the origin, so the answer is
 * just [[-2,2]].
 *
 * Example 2:
 * Input: points = [[3,3],[5,-1],[-2,4]], k = 2
 * Output: [[3,3],[-2,4]]
 * Explanation: The answer [[-2,4],[3,3]] would also be accepted.
 *
 * Constraints:
 * - 1 <= k <= points.length <= 10^4
 * - -10^4 <= xi, yi <= 10^4
 *
 * <a href="https://leetcode.com/problems/k-closest-points-to-origin/">973. K
 * Closest Points to Origin</a>
 */
public class _0973_KClosestPointsToOrigin implements Medium, SortingPattern, HeapPattern {

    /**
     * Time Complexity: O(n logn)
     * Space Complexity: O(1)
     */
    public int[][] kClosest(int[][] points, int k) {
        Arrays.sort(points, (p1, p2) -> (p1[0] * p1[0] + p1[1] * p1[1] - p2[0] * p2[0] - p2[1] * p2[1]));
        return Arrays.copyOfRange(points, 0, k);
    }

    /**
     * Time Complexity: O(n logk)
     * Space Complexity: O(k)
     */
    public int[][] kClosest_MaxHeap(int[][] points, int k) {
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>(
                (a, b) -> (b[0] * b[0] + b[1] * b[1] - a[0] * a[0] - a[1] * a[1]));
        for (int[] point : points) {
            maxHeap.offer(point);
            if (maxHeap.size() > k) {
                maxHeap.poll();
            }
        }
        return maxHeap.toArray(new int[k][]);
    }

    /**
     * Time Complexity: O(n + k logn)
     * Space Complexity: O(n)
     */
    public int[][] kClosest_MinHeap(int[][] points, int k) {
        PriorityQueue<int[]> minHeap = new PriorityQueue<>(
                (a, b) -> (a[0] * a[0] + a[1] * a[1] - b[0] * b[0] - b[1] * b[1]));
        for (int[] point : points) {
            minHeap.offer(point);
        }
        int[][] result = new int[k][];
        for (int i = 0; i < k; i++) {
            result[i] = minHeap.poll();
        }
        return result;
    }

}

package problems.p0001_0100;

import patterns.ArrayPattern;
import difficulty.Medium;

/**
 * You are given an array of non-overlapping intervals intervals where
 * intervals[i] = [starti, endi] represent the start and the end of the ith
 * interval and intervals is sorted in ascending order by starti. You are also
 * given an interval newInterval = [start, end] that represents the start and
 * end of another interval.
 *
 * Insert newInterval into intervals such that intervals is still sorted in
 * ascending order by starti and intervals still does not have any overlapping
 * intervals (merge overlapping intervals if necessary).
 *
 * Return intervals after the insertion.
 *
 * Note that you don't need to modify intervals in-place. You can make a new
 * array and return it.
 *
 * Example 1:
 * Input: intervals = [[1,3],[6,9]], newInterval = [2,5]
 * Output: [[1,5],[6,9]]
 *
 * Example 2:
 * Input: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
 * Output: [[1,2],[3,10],[12,16]]
 * Explanation: Because the new interval [4,8] overlaps with [3,5],[6,7],[8,10].
 *
 * Constraints:
 * - 0 <= intervals.length <= 10^4
 * - intervals[i].length == 2
 * - 0 <= starti <= endi <= 10^5
 * - intervals is sorted by starti in ascending order.
 * - newInterval.length == 2
 * - 0 <= start <= end <= 10^5
 *
 * <a href="https://leetcode.com/problems/insert-interval/">57. Insert
 * Interval</a>
 */
public class _0057_InsertInterval implements ArrayPattern, Medium {

    /**
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     */
    public int[][] insert(int[][] intervals, int[] newInterval) {
        int idx2 = intervals.length;
        for (int i = 0; i < intervals.length; i++) {
            if (intervals[i][1] >= newInterval[0]) {
                idx2 = i;
                break;
            }
        }
        int idx1 = idx2 - 1;
        while (idx2 < intervals.length) {
            if (newInterval[1] >= intervals[idx2][0]) {
                newInterval = merge(newInterval, intervals[idx2]);
                idx2++;
            } else {
                break;
            }
        }

        int[][] result = new int[idx1 + 2 + intervals.length - idx2][2];
        System.arraycopy(intervals, 0, result, 0, idx1 + 1);
        if (intervals.length > idx2) {
            System.arraycopy(intervals, idx2, result, idx1 + 2, intervals.length - idx2);
        }
        result[idx1 + 1] = newInterval;
        return result;
    }

    private int[] merge(int[] a, int[] b) {
        return new int[] { Math.min(a[0], b[1]), Math.max(a[1], b[1]) };
    }
}

package problems.p0201_0300;

import patterns.SlidingWindowPattern;
import patterns.ArrayPattern;
import patterns.TwoPointersPattern;
import difficulty.Medium;

/**
 * Given an array of positive integers nums and a positive integer target, return the minimal length of a
 * subarray whose sum is greater than or equal to target. If there is no such subarray, return 0 instead.
 *
 * Example 1:
 * Input: target = 7, nums = [2,3,1,2,4,3]
 * Output: 2
 * Explanation: The subarray [4,3] has the minimal length under the problem constraint.
 *
 * Example 2:
 * Input: target = 4, nums = [1,4,4]
 * Output: 1
 *
 * Example 3:
 * Input: target = 11, nums = [1,1,1,1,1,1,1,1]
 * Output: 0
 * <br>
 * <a href="https://leetcode.com/problems/minimum-size-subarray-sum/">209. Minimum Size Subarray Sum</a>
 */
public class _0209_MinSizeSubarraySum implements SlidingWindowPattern, ArrayPattern, TwoPointersPattern, Medium {
    public int practice(int target, int[] nums) {
        int start = 0;
        int sum = 0;
        int minLen = Integer.MAX_VALUE;
        for (int end = 0; end < nums.length; end++) {
            sum += nums[end];
            if (sum < target) continue;
            while (sum >= target && start <= end) {
                sum -= nums[start];
                start++;
            }
            if (minLen > end - start + 2) minLen = end - start + 2;
        }
        return minLen == Integer.MAX_VALUE ? 0 : minLen;
    }

    /**
     * <a href="https://www.youtube.com/watch?v=XfSgQvKfcys&list=PL7g1jYj15RUOjoeZAJsWjwV8XUo9r0hwc&index=5">source</a>
     */
    public int slidingWindow(int target, int[] nums) {
        int sum = 0;
        int start = 0;
        int minLen = 0;
        for (int end = 0; end < nums.length; end++) {
            sum += nums[end];
            while (sum >= target) {
                if (minLen == 0) {
                    minLen = end - start + 1;
                }
                minLen = java.lang.Math.min(minLen, end - start + 1);
                sum -= nums[start];
                start++;
            }
        }
        return minLen;
    }

    public static void main(java.lang.String[] args) {
        int[] nums = {2,3,1,2,4,3};
        int target = 7;
        System.out.println(new _0209_MinSizeSubarraySum().practice(target, nums));
    }
}

package problems.p0001_0100;

import patterns.ArrayPattern;
import patterns.DynamicProgrammingPattern;
import patterns.GreedyPattern;
import difficulty.Medium;

/**
 * Given an integer array nums, find the subarray with the largest sum, and return its sum.
 *
 * Example 1:
 * Input: nums = [-2,1,-3,4,-1,2,1,-5,4]
 * Output: 6
 * Explanation: The subarray [4,-1,2,1] has the largest sum 6.
 *
 * Example 2:
 * Input: nums = [1]
 * Output: 1
 * Explanation: The subarray [1] has the largest sum 1.
 *
 * Example 3:
 * Input: nums = [5,4,-1,7,8]
 * Output: 23
 * Explanation: The subarray [5,4,-1,7,8] has the largest sum 23.
 * <br>
 * <a href="https://leetcode.com/problems/maximum-subarray/">53. Maximum Subarray</a>
 */
public class _0053_MaximumSubArray implements ArrayPattern, DynamicProgrammingPattern, GreedyPattern, Medium {
    public int practice(int[] nums) {
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int max = dp[0];
        for (int i = 1; i < nums.length; i++) {
            dp[i] = java.lang.Math.max(dp[i - 1] + nums[i], nums[i]);
            max = java.lang.Math.max(max, dp[i]);
        }
        return max;
    }

    public int bruteForce(int[] nums) {
        int max = Integer.MIN_VALUE;
        int sum = 0;
        for (int i : nums) {
            sum += i;
            max = java.lang.Math.max(sum, max);
            if (sum < 0) sum = 0;
        }
        return max;
    }

    public static void main(java.lang.String[] args) {
        new _0053_MaximumSubArray().practice(new int[] {-2,1,-3,4,-1,2,1,-5,4});
    }
}

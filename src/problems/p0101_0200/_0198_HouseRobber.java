package problems.p0101_0200;

import patterns.DynamicProgrammingPattern;
import patterns.ArrayPattern;
import difficulty.Medium;

/**
 * You are a professional robber planning to rob houses along a street.
 * Each house has a certain amount of money stashed,
 * the only constraint stopping you from robbing each of them is that
 * adjacent houses have security systems connected, and
 * it will automatically contact the police if two adjacent houses were broken into on the same night.
 *
 * Given an integer array nums representing the amount of money of each house,
 * return the maximum amount of money you can rob tonight without alerting the police.
 *
 * Example 1:
 * Input: nums = [1,2,3,1]
 * Output: 4
 * Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
 * Total amount you can rob = 1 + 3 = 4.
 *
 * Example 2:
 * Input: nums = [2,7,9,3,1]
 * Output: 12
 * Explanation: Rob house 1 (money = 2), rob house 3 (money = 9) and rob house 5 (money = 1).
 * Total amount you can rob = 2 + 9 + 1 = 12.
 * <br>
 * <a href="https://leetcode.com/problems/house-robber/">198. House Robber</a>
 */
public class _0198_HouseRobber implements DynamicProgrammingPattern, ArrayPattern, Medium {
    public int practice(int[] nums) {
        int length = nums.length;
        if (length == 1) return nums[0];
        int[] money = new int[nums.length];
        money[0] = nums[0];
        money[1] = java.lang.Math.max(nums[0], nums[1]);
        for (int i = 2; i < nums.length; i++) {
            money[i] = java.lang.Math.max(money[i - 1], (money[i - 2] + nums[i]));
        }
        return money[nums.length - 1];
    }
}

package problems.p0001_0100;

import patterns.ArrayPattern;
import patterns.HashTablePattern;
import difficulty.Easy;

import java.util.HashMap;
import java.util.Map;

/**
 * Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.
 * You may assume that each input would have exactly one solution, and you may not use the same element twice.
 * You can return the answer in any order.
 *
 * Example 1:
 * Input: nums = [2,7,11,15], target = 9
 * Output: [0,1]
 * Explanation: Because nums[0] + nums[1] == 9, we return [0, 1].
 *
 * Example 2:
 * Input: nums = [3,2,4], target = 6
 * Output: [1,2]
 *
 * Example 3:
 * Input: nums = [3,3], target = 6
 * Output: [0,1]
 *
 * <br>
 * <a href ="https://leetcode.com/problems/two-sum/">1. Two Sum</a>
 */
public class _0001_TwoSum implements ArrayPattern, HashTablePattern, Easy {

    /**
     * Time Complexity: O(N^2)
     * Space Complexity: O(N)
     */
    public int[] practice(int[] nums, int target) {
        int[] temp = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < i; j ++) {
                if (temp[j] == nums[i]) {
                    return new int[] {j, i};
                }
            }
            temp[i] = target - nums[i];
        }
        return new int[] {};
    }

    /**
     * Time Complexity: O(N)
     * Space Complexity: O(N)
     */
    public int[] usingHashMap(int[] nums, int target) {
        Map<Integer, Integer> visited = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int n = nums[i];
            int complement = target - n;
            if (visited.containsKey(complement)) {
                return new int[] {visited.get(complement), i};
            }
            visited.put(nums[i], i);
        }
        return new int[] {};
    }
}

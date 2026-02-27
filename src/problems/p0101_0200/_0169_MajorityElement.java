package problems.p0101_0200;

import patterns.ArrayPattern;
import patterns.HashTablePattern;
import difficulty.Easy;
import java.util.Map;
import java.util.HashMap;

/**
 * Given an array nums of size n, return the majority element.
 *
 * The majority element is the element that appears more than ⌊n / 2⌋ times.
 * You may assume that the majority element always exists in the array.
 *
 * Example 1:
 * Input: nums = [3,2,3]
 * Output: 3
 *
 * Example 2:
 * Input: nums = [2,2,1,1,1,2,2]
 * Output: 2
 *
 * Constraints:
 * - n == nums.length
 * - 1 <= n <= 5 * 10^4
 * - -10^9 <= nums[i] <= 10^9
 *
 * Follow-up: Could you solve the problem in linear time and in O(1) space?
 *
 * <a href="https://leetcode.com/problems/majority-element/">169. Majority
 * Element</a>
 */
public class _0169_MajorityElement implements ArrayPattern, HashTablePattern, Easy {

    /**
     * Time Complexity: O(N)
     * Space Complexity: O(N)
     */
    public int majorityElement(int[] nums) {
        Map<Integer, Integer> countMap = new HashMap<>();
        for (int i : nums) {
            int count = countMap.getOrDefault(i, 0) + 1;
            if (count > nums.length / 2) {
                return i;
            }
            countMap.put(i, count);
        }
        return 0;
    }

    /**
     * Boyer-Moore Voting Algorithm.
     *
     * Time Complexity: O(N)
     * Space Complexity: O(1)
     */
    public int boyerMooreVoting(int[] nums) {
        int candidate = nums[0];
        int count = 0;
        for (int num : nums) {
            if (count == 0) {
                candidate = num;
            }
            count += (num == candidate) ? 1 : -1;
        }
        return candidate;
    }
}

package problems.p0201_0300;

import difficulty.Medium;
import patterns.ArrayPattern;

/**
 * Given an integer array nums, return an array answer such that answer[i] is
 * equal to the product of all the elements of nums except nums[i].
 *
 * The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit
 * integer.
 *
 * You must write an algorithm that runs in O(n) time and without using the
 * division operation.
 *
 * Example 1:
 * Input: nums = [1,2,3,4]
 * Output: [24,12,8,6]
 *
 * Example 2:
 * Input: nums = [-1,1,0,-3,3]
 * Output: [0,0,9,0,0]
 *
 * Constraints:
 * - 2 <= nums.length <= 10^5
 * - -30 <= nums[i] <= 30
 * - The input is generated such that answer[i] is guaranteed to fit in a 32-bit
 * integer.
 *
 * Follow up: Can you solve the problem in O(1) extra space complexity? (The
 * output array does not count as extra space for space complexity analysis.)
 *
 * <a href="https://leetcode.com/problems/product-of-array-except-self/">238.
 * Product of Array Except Self</a>
 */
public class _0238_ProductOfArrayExceptSelf implements Medium, ArrayPattern {
    /**
     * Time Complexity: O(n)
     * Space Complexity: O(n) (excluding the output array)
     */
    public int[] productExceptSelf(int[] nums) {
        int[] prefix = new int[nums.length];
        int[] suffix = new int[nums.length];
        prefix[0] = nums[0];
        suffix[nums.length - 1] = nums[nums.length - 1];
        for (int i = 1; i < nums.length; i++) {
            prefix[i] = prefix[i - 1] * nums[i];
        }
        for (int i = nums.length - 2; i > -1; i--) {
            suffix[i] = suffix[i + 1] * nums[i];
        }
        int[] result = new int[nums.length];
        for (int i = 1; i < nums.length - 1; i++) {
            result[i] = prefix[i - 1] * suffix[i + 1];
        }
        result[0] = suffix[1];
        result[nums.length - 1] = prefix[nums.length - 2];
        return result;
    }

    /**
     * Time Complexity: O(n)
     * Space Complexity: O(1) (excluding the output array)
     */
    public int[] productExceptSelf_O1(int[] nums) {
        int[] result = new int[nums.length];
        result[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            result[i] = result[i - 1] * nums[i - 1];
        }
        int suffix = 1;
        for (int i = nums.length - 1; i > -1; i--) {
            result[i] = result[i] * suffix;
            suffix = suffix * nums[i];
        }
        return result;
    }
}

package twoPointers;

/**
 * Given a 1-indexed array of integers numbers that is already sorted in non-decreasing order,
 * find two numbers such that they add up to a specific target number.
 * Let these two numbers be numbers[index1] and numbers[index2] where 1 <= index1 < index2 <= numbers.length.
 *
 * Return the indices of the two numbers, index1 and index2, added by one as an integer array [index1, index2] of length 2.
 *
 * The tests are generated such that there is exactly one solution. You may not use the same element twice.
 *
 * Your solution must use only constant extra space.
 *
 * <a href="https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/">167. Two Sum II - Input Array Is Sorted</a>
 */
public class _167_TwoSumII {
    public int[] practice(int[] numbers, int target) {
        int left = 0;
        int right = numbers.length - 1;
        while (left != right) {
            if (numbers[right] + numbers[left] == target) {
                return new int[] {left + 1, right + 1};
            } else if (numbers[right] + numbers[left] > target) {
                right -= 1;
            } else {
                left += 1;
            }
        }
        return null;
    }
}

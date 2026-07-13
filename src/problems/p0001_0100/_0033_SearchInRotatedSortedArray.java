package problems.p0001_0100;

import patterns.ArrayPattern;
import patterns.BinarySearchPattern;
import difficulty.Medium;

/**
 * There is an integer array nums sorted in ascending order (with distinct values).
 *
 * Prior to being passed to your function, nums is possibly left rotated at an unknown index k
 * (1 <= k < nums.length) such that the resulting array is
 * [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]] (0-indexed).
 * For example, [0,1,2,4,5,6,7] might be left rotated by 3 indices and become [4,5,6,7,0,1,2].
 *
 * Given the array nums after the possible rotation and an integer target, return the index of
 * target if it is in nums, or -1 if it is not in nums.
 *
 * You must write an algorithm with O(log n) runtime complexity.
 *
 * Example 1:
 * Input: nums = [4,5,6,7,0,1,2], target = 0
 * Output: 4
 *
 * Example 2:
 * Input: nums = [4,5,6,7,0,1,2], target = 3
 * Output: -1
 *
 * Example 3:
 * Input: nums = [1], target = 0
 * Output: -1
 *
 * Constraints:
 * - 1 <= nums.length <= 5000
 * - -10^4 <= nums[i] <= 10^4
 * - All values of nums are unique.
 * - nums is an ascending array that is possibly rotated.
 * - -10^4 <= target <= 10^4
 *
 * <br>
 * <a href="https://leetcode.com/problems/search-in-rotated-sorted-array/">33. Search in Rotated Sorted Array</a>
 */
public class _0033_SearchInRotatedSortedArray implements ArrayPattern, BinarySearchPattern, Medium {

    /**
     * Time Complexity: O(log n)
     * Space Complexity: O(log n)
     */
    public int recursion(int[] nums, int target) {
        return recursion(nums, target, 0, nums.length - 1);
    }

    private int recursion(int[] nums, int target, int left, int right) {
        if (nums[left] == target) {
            return left;
        }
        if (nums[right] == target) {
            return right;
        }
        if (right - left < 2) {
            return -1;
        }
        int next = (left + right) / 2;
        if (nums[next] == target) {
            return next;
        }
        if (nums[left] > target) {
            if (nums[next] < target || nums[next] > nums[left]) {
                return recursion(nums, target, next, right);
            } else {
                return recursion(nums, target, left, next);
            }
        } else {
            if (nums[next] > target || nums[next] < nums[left]) {
                return recursion(nums, target, left, next);
            } else {
                return recursion(nums, target, next, right);
            }
        }
    }

    /**
     * Time Complexity: O(log n)
     * Space Complexity: O(1)
     */
    public int iteration(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (right - left > 1) {
            int next = (left + right) / 2;
            if (nums[next] == target) {
                return next;
            }
            if (nums[left] > target) {
                if (nums[next] < target || nums[next] > nums[left]) {
                    left = next;
                } else {
                    right = next;
                }
            } else {
                if (nums[next] > target || nums[next] < nums[left]) {
                    right = next;
                } else {
                    left = next;
                }
            }
        }
        if (nums[left] == target) {
            return left;
        }
        if (nums[right] == target) {
            return right;
        }
        return -1;
    }

    /**
     * Time Complexity: O(log n)
     * Space Complexity: O(1)
     */
    public int twoPass(int[] nums, int target) {
        int pivot = findPivot(nums);
        int left = 0;
        int right = nums.length - 1;
        while (right - left > 1) {
            int mid = (left + right) / 2;
            int real = rotate(mid, pivot, nums.length);
            if (nums[real] > target) {
                right = mid;
            } else if (nums[real] < target) {
                left = mid;
            } else {
                return real;
            }
        }
        if (nums[rotate(left, pivot, nums.length)] == target) {
            return rotate(left, pivot, nums.length);
        }
        if (nums[rotate(right, pivot, nums.length)] == target) {
            return rotate(right, pivot, nums.length);
        }
        return -1;
    }

    private int rotate(int idx, int pivot, int length) {
        return (idx + pivot) % length;
    }

    private int findPivot(int[] nums) {
        if (nums.length == 1) {
            return 0;
        }
        int left = 0;
        int right = nums.length - 1;
        while (nums[left] > nums[right]) {
            int mid = (left + right) / 2;
            if (mid == left) {
                left += 1;
                break;
            }
            if (nums[mid] > nums[left]) {
                left = mid;
            } else {
                right = mid;
            }
        }
        return left;
    }
}

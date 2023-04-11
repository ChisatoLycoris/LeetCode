package slidingWindowPattern;

/**
 * You are given an integer array nums consisting of n elements, and an integer k.
 *
 * Find a contiguous subarray whose length is equal to k that has the maximum average value and return this value.
 * Any answer with a calculation error less than 10^-5 will be accepted.
 *
 * Example 1:
 * Input: nums = [1,12,-5,-6,50,3], k = 4
 * Output: 12.75000
 * Explanation: Maximum average is (12 - 5 - 6 + 50) / 4 = 51 / 4 = 12.75
 *
 * Example 2:
 * Input: nums = [5], k = 1
 * Output: 5.00000
 * <br>
 * <a href="https://leetcode.com/problems/maximum-average-subarray-i/">643. Maximum Average Subarray I</a>
 */
public class _643_MaxAvgSubarrayI {
    public double naive(int[] nums, int k) {
        double max = - Double.MAX_VALUE;
        for (int i = 0; i < nums.length - k + 1; i++) {
            int sum = 0;
            for (int j = 0; j < k; j++) {
                sum += nums[i + j];
            }
            max = Math.max(max, sum / (double) k);
        }
        return max == - Double.MAX_VALUE ? 0 : max;
    }

    /**
     * <a href="https://www.youtube.com/watch?v=XfSgQvKfcys&list=PL7g1jYj15RUOjoeZAJsWjwV8XUo9r0hwc&index=2">source</a>
     */
    public double slidingWindow(int[] nums, int k) {
        int sum = 0;
        for (int i = 0; i < k; i++) {
            sum += nums[i];
        }
        int max = sum;
        for (int i = 0; i < nums.length - k; i++) {
            sum -= nums[i];
            sum += nums[i + k];
            if (sum > max) max = sum;
        }
        return max / (double) k;
    }

}

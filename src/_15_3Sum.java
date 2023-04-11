import java.util.*;

/**
 * Given an integer array nums, return all the triplets [nums[i], nums[j], nums[k]]
 * such that i != j, i != k, and j != k, and nums[i] + nums[j] + nums[k] == 0.
 *
 * Notice that the solution set must not contain duplicate triplets.
 *
 * Example 1:
 * Input: nums = [-1,0,1,2,-1,-4]
 * Output: [[-1,-1,2],[-1,0,1]]
 * Explanation:
 * nums[0] + nums[1] + nums[2] = (-1) + 0 + 1 = 0.
 * nums[1] + nums[2] + nums[4] = 0 + 1 + (-1) = 0.
 * nums[0] + nums[3] + nums[4] = (-1) + 2 + (-1) = 0.
 * The distinct triplets are [-1,0,1] and [-1,-1,2].
 * Notice that the order of the output and the order of the triplets does not matter.
 * <br>
 * <a href="https://leetcode.com/problems/3sum/">15. 3Sum</a>
 */
public class _15_3Sum {
    public List<List<Integer>> practice(int[] nums) {
        Set<List<Integer>> result = new HashSet<>();
        Arrays.sort(nums);
        int start = 0;
        int end = nums.length - 1;
        while (start != end) {
            int mid = start + 1;
            for (; mid < end; mid++) {

            }
        }
        return result.stream().toList();
    }

    public static void main(String[] args) {
        int[] nums = {-1,0,1,2,-1,-4};
        // [-4, -1, -1, 0, 1, 2]
        System.out.println(new _15_3Sum().practice(nums));
        System.out.println("Still in progress...");
    }
}

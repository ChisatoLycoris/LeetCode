package problems.p0801_0900;

import patterns.BinarySearchPattern;
import patterns.ArrayPattern;
import difficulty.Easy;

/**
 * An array arr a mountain if the following properties hold:
 *
 * arr.length >= 3
 * There exists some i with 0 < i < arr.length - 1 such that:
 * arr[0] < arr[1] < ... < arr[i - 1] < arr[i]
 * arr[i] > arr[i + 1] > ... > arr[arr.length - 1]
 * Given a mountain array arr, return the index i such that both equations were true.
 *
 * You must solve it in O(log(arr.length)) time complexity.
 *
 * Example 1:
 * Input: arr = [0,1,0]
 * Output: 1
 *
 * Example 2:
 * Input: arr = [0,2,1,0]
 * Output: 1
 *
 * Example 3:
 * Input: arr = [0,10,5,2]
 * Output: 1
 * <br>
 * <a href="https://leetcode.com/problems/peak-index-in-a-mountain-array/">852. Peak Index in a Mountain Array</a>
 */
public class _0852_PeakIndexInAMountainArray implements BinarySearchPattern, ArrayPattern, Easy {
    public int practice(int[] arr) {
        int start = 0;
        int end = arr.length - 1;
        while (start < end) {
            int mid1 = start + (end - start) / 2;
            int mid2 = mid1 + 1;
            if (arr[mid1] > arr[mid2]) {
                end = mid1;
            } else {
                start = mid2;
            }
        }
        return start;
    }
}

package problems.p0001_0100;

import patterns.DynamicProgrammingPattern;
import difficulty.Easy;

/**
 * You are climbing a staircase. It takes n steps to reach the top.
 *
 * Each time you can either climb 1 or 2 steps. In how many distinct ways can
 * you climb to the top?
 *
 * Example 1:
 * Input: n = 2
 * Output: 2
 * Explanation: There are two ways to climb to the top.
 * 1. 1 step + 1 step
 * 2. 2 steps
 *
 * Example 2:
 * Input: n = 3
 * Output: 3
 * Explanation: There are three ways to climb to the top.
 * 1. 1 step + 1 step + 1 step
 * 2. 1 step + 2 steps
 * 3. 2 steps + 1 step
 *
 * Constraints:
 * - 1 <= n <= 45
 *
 * <a href="https://leetcode.com/problems/climbing-stairs/">70. Climbing
 * Stairs</a>
 */
public class _0070_ClimbingStairs implements DynamicProgrammingPattern, Easy {

    /**
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     */
    public int climbStairs(int n) {
        if (n == 1)
            return 1;
        if (n == 2)
            return 2;
        int[] steps = new int[n];
        steps[0] = 1;
        steps[1] = 2;
        for (int i = 2; i < n; i++) {
            steps[i] = steps[i - 2] + steps[i - 1];
        }
        return steps[n - 1];
    }

    /**
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public int climbStairsOptimized(int n) {
        if (n < 3)
            return n;
        int dp0 = 1;
        int dp1 = 2;
        for (int i = 3; i <= n; i++) {
            int k = dp0 + dp1;
            dp0 = dp1;
            dp1 = k;
        }
        return dp1;
    }
}

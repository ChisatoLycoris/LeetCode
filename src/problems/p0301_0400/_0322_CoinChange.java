package problems.p0301_0400;

import patterns.DynamicProgrammingPattern;

import java.util.Arrays;

import difficulty.Medium;

/**
 * You are given an integer array coins representing coins of different
 * denominations and an integer amount representing a total amount of money.
 *
 * Return the fewest number of coins that you need to make up that amount.
 * If that amount of money cannot be made up by any combination of the coins,
 * return -1.
 *
 * You may assume that you have an infinite number of each kind of coin.
 *
 * Example 1:
 * Input: coins = [1,2,5], amount = 11
 * Output: 3
 * Explanation: 11 = 5 + 5 + 1
 *
 * Example 2:
 * Input: coins = [2], amount = 3
 * Output: -1
 *
 * Example 3:
 * Input: coins = [1], amount = 0
 * Output: 0
 *
 * Constraints:
 * - 1 <= coins.length <= 12
 * - 1 <= coins[i] <= 2^31 - 1
 * - 0 <= amount <= 10^4
 *
 * <a href="https://leetcode.com/problems/coin-change/">322. Coin Change</a>
 */
public class _0322_CoinChange implements DynamicProgrammingPattern, Medium {

    /**
     * Time Complexity: O(n) n for amount, coin qty is limited to constant, it does
     * not matter.
     * Space Complexity: O(n)
     */
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            dp[i] = amount + 1;
        }
        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) {
                if (coin <= i) {
                    dp[i] = Math.min(dp[i], 1 + dp[i - coin]);
                }
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }

    /**
     * Time Complexity: O(n) n for amount, coin qty is limited to constant, it does
     * not matter.
     * Space Complexity: O(n)
     */
    public int coinChange_topDownDP(int[] coins, int amount) {
        int[] memo = new int[amount + 1];
        Arrays.fill(memo, -2);
        return topDownDp(coins, amount, memo);
    }

    private int topDownDp(int[] coins, int amount, int[] memo) {
        if (amount == 0) {
            return 0;
        }
        if (amount < 0) {
            return -1;
        }
        if (memo[amount] != -2) {
            return memo[amount];
        }
        int result = Integer.MAX_VALUE;
        for (int coin : coins) {
            if (coin <= amount) {
                int prev = topDownDp(coins, amount - coin, memo);
                if (prev != -1) {
                    result = Math.min(result, prev + 1);
                }
            }
        }
        if (result == Integer.MAX_VALUE) {
            result = -1;
        }
        memo[amount] = result;
        return result;
    }
}

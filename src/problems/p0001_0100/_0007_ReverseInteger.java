package problems.p0001_0100;

import patterns.MathPattern;
import difficulty.Medium;

/**
 * Given a signed 32-bit integer x, return x with its digits reversed.
 * If reversing x causes the value to go outside the signed 32-bit integer range [-231, 231 - 1], then return 0.
 *
 * Assume the environment does not allow you to store 64-bit integers (signed or unsigned).
 *
 * Example 1:
 * Input: x = 123
 * Output: 321
 *
 * Example 2:
 * Input: x = -123
 * Output: -321
 *
 * Example 3:
 * Input: x = 120
 * Output: 21
 *
 * <br>
 * <a href="https://leetcode.com/problems/reverse-integer/">7. Reverse Integer</a>
 */
public class _0007_ReverseInteger implements MathPattern, Medium {
    public int practice(int x) {
        if (x == 0) {
            return 0;
        }
        boolean positive = x > 0;
        boolean isZero = true;
        int result = 0;
        int current = java.lang.Math.abs(x);
        while (current > 0) {
            int n = current % 10;
            current /= 10;
            if (isZero && n == 0) {
                continue;
            }
            isZero = false;
            if (result > Integer.MAX_VALUE / 10) {
                return 0;
            }
            result *= 10;
            result += n;
        }

        if (!positive) {
            result = - result;
        }
        return result;
    }

    /**
     * Time Complexity: O(log(N)) ~ roughly log10(x) digits in x
     * Space Complexity: O(1)
     */
    public int popAndPush(int x) {
        int rev = 0;
        while (x != 0) {
            int pop = x % 10;
            x /= 10;
            if (rev > Integer.MAX_VALUE/10 || (rev == Integer.MAX_VALUE / 10 && pop > 7)) return 0;
            if (rev < Integer.MIN_VALUE/10 || (rev == Integer.MIN_VALUE / 10 && pop < -8)) return 0;
            rev = rev * 10 + pop;
        }
        return rev;
    }
}

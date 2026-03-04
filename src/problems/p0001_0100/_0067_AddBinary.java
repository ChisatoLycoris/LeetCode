package problems.p0001_0100;

import patterns.StringPattern;
import patterns.BitManipulationPattern;
import patterns.MathPattern;
import difficulty.Easy;

/**
 * Given two binary strings a and b, return their sum as a binary string.
 *
 * Example 1:
 * Input: a = "11", b = "1"
 * Output: "100"
 *
 * Example 2:
 * Input: a = "1010", b = "1011"
 * Output: "10101"
 *
 * Constraints:
 * - 1 <= a.length, b.length <= 10^4
 * - a and b consist only of '0' or '1' characters.
 * - Each string does not contain leading zeros except for the zero itself.
 *
 * <a href="https://leetcode.com/problems/add-binary/">67. Add Binary</a>
 */
public class _0067_AddBinary implements StringPattern, BitManipulationPattern, MathPattern, Easy {

    /**
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     */
    public String addBinary(String a, String b) {
        int len = a.length() > b.length() ? a.length() : b.length();
        char[] cs = new char[len + 1];
        int overflow = 0;
        for (int i = 0; i < len; i++) {
            int aNum = 0;
            int bNum = 0;
            if (i < a.length()) {
                aNum = a.charAt(a.length() - i - 1) - '0';
            }
            if (i < b.length()) {
                bNum = b.charAt(b.length() - i - 1) - '0';
            }
            int result = aNum + bNum + overflow;
            cs[len - i] = result % 2 == 0 ? '0' : '1';
            overflow = result / 2;
        }
        if (overflow == 0) {
            return new String(cs, 1, len);
        } else {
            cs[0] = '1';
            return new String(cs);
        }
    }
}

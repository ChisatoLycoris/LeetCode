package problems.p0101_0200;

import difficulty.Easy;
import patterns.StringPattern;
import patterns.TwoPointersPattern;

/**
 * A phrase is a palindrome if, after converting all uppercase letters into
 * lowercase letters
 * and removing all non-alphanumeric characters, it reads the same forward and
 * backward.
 * Alphanumeric characters include letters and numbers.
 *
 * Given a string s, return true if it is a palindrome, or false otherwise.
 *
 * Example 1:
 * Input: s = "A man, a plan, a canal: Panama"
 * Output: true
 * Explanation: "amanaplanacanalpanama" is a palindrome.
 *
 * Example 2:
 * Input: s = "race a car"
 * Output: false
 * Explanation: "raceacar" is not a palindrome.
 *
 * Example 3:
 * Input: s = " "
 * Output: true
 * <br>
 * <a href="https://leetcode.com/problems/valid-palindrome/">125. Valid
 * Palindrome</a>
 */
public class _0125_ValidPalindrome implements StringPattern, TwoPointersPattern, Easy {
    public boolean isPalindrome(String s) {
        char[] filtered = new char[s.length()];
        int filteredLength = 0;
        for (int i = 0; i < s.length(); i++) {
            char target = s.charAt(i);
            switch (target) {
                case 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T',
                        'U', 'V', 'W', 'X', 'Y', 'Z' -> {
                    target += 32;
                    filtered[filteredLength] = target;
                    filteredLength += 1;
                }
                case '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
                        'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z' -> {
                    filtered[filteredLength] = target;
                    filteredLength += 1;
                }
            }
        }
        if (filteredLength < 2) {
            return true;
        }
        for (int i = 0; i < filteredLength / 2; i++) {
            if (filtered[i] != filtered[filteredLength - 1 - i]) {
                return false;
            }
        }
        return true;
    }

    public boolean isPalindrome2(String s) {
        char[] filtered = new char[s.length()];
        int filteredLength = 0;
        for (int i = 0; i < s.length(); i++) {
            char target = s.charAt(i);
            if (target >= 'A' && target <= 'Z') {
                target += 32;
            }
            if ((target >= '0' && target <= '9') || (target >= 'a' && target <= 'z')) {
                filtered[filteredLength] = target;
                filteredLength += 1;
            }
        }
        if (filteredLength < 2) {
            return true;
        }
        for (int i = 0; i < filteredLength / 2; i++) {
            if (filtered[i] != filtered[filteredLength - 1 - i]) {
                return false;
            }
        }
        return true;
    }
}

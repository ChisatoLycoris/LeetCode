package problems.p0001_0100;

import patterns.DynamicProgrammingPattern;
import patterns.StringPattern;
import difficulty.Medium;

/**
 * Given a string s, return the longest palindromic substring in s.
 * A string is palindromic if it reads the same forward and backward.
 *
 *
 * Example 1:
 * Input: s = "babad"
 * Output: "bab"
 * Explanation: "aba" is also a valid answer.
 *
 * Example 2:
 * Input: s = "cbbd"
 * Output: "bb"
 * <br>
 * <a href="https://leetcode.com/problems/longest-palindromic-substring/">5. Longest Palindromic Substring</a>
 */
public class _0005_PalindromicSubstring implements DynamicProgrammingPattern, StringPattern, Medium {
    /**
     * Time Complexity: O(N^2)
     * Space Complexity: O(1)
     */
    public java.lang.String practice(java.lang.String s) {
        java.lang.String result = s.substring(0, 1);
        for (int i = 0; i < s.length(); i++) {
            int k = 0;
            while (i - k > 0 && i + k < s.length() && s.charAt(i + k) == s.charAt(i - k - 1)) {
                k += 1;
            }
            if (k != 0 && result.length() < 2 * k) {
                result = s.substring(i - k, i + k);
            }
            int j = 0;
            while (i - j > 0 && i + j + 1< s.length() && s.charAt(i + j + 1) == s.charAt(i - j - 1)) {
                j += 1;
            }
            if (j != 0 && result.length() < 2 * j + 1) {
                result = s.substring(i - j, i + j + 1);
            }
        }
        return result;
    }

    /**
     * Time Complexity: O(N^2)
     * Space Complexity: O(1)
     * <br>
     * <a href=""></a>
     */
    public java.lang.String simpler(java.lang.String s) {
        int start = 0;
        int end = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            int left = i - 1;
            int right = i + 1;
            int j = 0;
            while (right < s.length() && s.charAt(right) == c) {
                right++;
                j++;
            }
            while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
                left--;
                right++;
            }
            left = left + 1;
            if (end - start < right - left) {
                end = right;
                start = left;
            }
            i = i + j;
        }
        return s.substring(start, end);
    }

    /**
     * Time Complexity: O(N^2)
     * Space Complexity: O(N^2)
     * <br>
     * <a href=""></a>
     */
    public java.lang.String dynamicPrograming(java.lang.String s) {
        int len = s.length();
        if (len < 2) {
            return s;
        }

        int maxLen = 1;
        int begin = 0;

        // dp[i][j] is true when s[i...j] is palindromic substring
        // dp[i][j] = dp[i + 1][j - 1] && (s[i] == s[j])
        boolean[][] dp = new boolean[len][len];
        for (int i = 0; i < len; i++) {
            dp[i][i] = true;
        }

        char[] charArray = s.toCharArray();

        for (int j = 1; j < len; j++) {
            for (int i = 0; i < j; i++) {
                if (charArray[i] != charArray[j]) {
                    dp[i][j] = false;
                } else {
                    if (j - i < 3) {
                        dp[i][j] = true;
                    } else {
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                }
                if (dp[i][j] && j - i + 1 > maxLen) {
                    maxLen = j - i + 1;
                    begin = i;
                }
            }
        }
        return s.substring(begin, begin + maxLen);
    }

    /**
     * Time Complexity: O(N^2)
     * Space Complexity: O(1)
     * <br>
     * <a href="https://en.wikipedia.org/wiki/Longest_palindromic_substring#Manacher's_algorithm">Manacher Algorithm</a>
     */
    public java.lang.String manacherAlgorithm(java.lang.String s) {

        if (s.length() == 0) {
            throw new IllegalArgumentException("String must not be empty");
        }
        if (s.length() == 1) {
            return s;
        }

        char[] chars = s.toCharArray();
        int currentPalindromeStart = 0;
        int currentPalindromeLength = 1;
        int longestPalindromeStart = 0;
        int longestPalindromeLength = 1;
        int leftDistanceToPivot = 1;
        boolean isSequenceOfSameCharacters = true;

        for (int i = 1, pivot = 0; i < chars.length; i++) {
            if (isSequenceOfSameCharacters && chars[pivot] == chars[i]) {
                currentPalindromeStart = pivot;
                currentPalindromeLength++;
            } else if ((pivot - leftDistanceToPivot) >= 0 && chars[i] == chars[pivot - leftDistanceToPivot]) {
                currentPalindromeStart = pivot - leftDistanceToPivot;
                leftDistanceToPivot++;
                currentPalindromeLength += 2;
                isSequenceOfSameCharacters = false;
            } else {
                pivot++;
                i = pivot;
                leftDistanceToPivot = 1;
                currentPalindromeLength = 1;
                isSequenceOfSameCharacters = true;
            }
            if (currentPalindromeLength > longestPalindromeLength) {
                longestPalindromeLength = currentPalindromeLength;
                longestPalindromeStart = Math.max(currentPalindromeStart, 0);
            }
        }
        return s.substring(longestPalindromeStart, longestPalindromeStart + longestPalindromeLength);
    }
}

import java.util.LinkedList;

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
 */
public class _5_PalindromicSubstring {
    /**
     * Time Complexity: O(N^2)
     * Space Complexity: O(1)
     */
    public String practice(String s) {
        String result = s.substring(0, 1);
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
    public String simpler(String s) {
        int start = 0;
        int end = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            int left = i;
            int right = i;
            while (left >= 0 && s.charAt(left) == c) {
                left--;
            }
            while (right < s.length() && s.charAt(right) == c) {
                right++;
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
        }
        return s.substring(start, end);
    }

    /**
     * Time Complexity: O(N^2)
     * Space Complexity: O(1)
     * <br>
     * <a href="https://leetcode.com/problems/longest-palindromic-substring/submissions/929091516/"></a>
     */
    public String longestPalindrome(String s) {

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

package problems.p0001_0100;

import patterns.HashTablePattern;
import patterns.SlidingWindowPattern;
import patterns.StringPattern;
import difficulty.Medium;

/**
 * Given a string s, find the length of the longest substring without repeating
 * characters.
 *
 * Example 1:
 * Input: s = "abcabcbb"
 * Output: 3
 * Explanation: The answer is "abc", with the length of 3.
 *
 * Example 2:
 * Input: s = "bbbbb"
 * Output: 1
 * Explanation: The answer is "b", with the length of 1.
 *
 * Example 3:
 * Input: s = "pwwkew"
 * Output: 3
 * Explanation: The answer is "wke", with the length of 3.
 * Notice that the answer must be a substring, "pwke" is a subsequence and not a
 * substring.
 *
 * <br>
 * <a href=
 * "https://leetcode.com/problems/longest-substring-without-repeating-characters/">3.
 * Longest Substring Without Repeating Characters</a>
 */
public class _0003_NoRepeatingSubstring implements SlidingWindowPattern, HashTablePattern, StringPattern, Medium {
    /**
     * Time Complexity: O(N)
     * Space Complexity: O(1)
     */
    public int lengthOfLongestSubstring(String s) {
        int result = 0;
        int[] lastSeen = new int[128]; // stores index + 1 (0 means unseen)
        for (int i = 0, start = 0; i < s.length(); i++) {
            start = Math.max(start, lastSeen[s.charAt(i)]);
            lastSeen[s.charAt(i)] = i + 1;
            result = Math.max(result, i - start + 1);
        }
        return result;
    }
}

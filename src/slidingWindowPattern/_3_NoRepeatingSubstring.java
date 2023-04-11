package slidingWindowPattern;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a string s, find the length of the longest substring without repeating characters.
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
 * Notice that the answer must be a substring, "pwke" is a subsequence and not a substring.
 *
 * <br>
 * <a href="https://leetcode.com/problems/longest-substring-without-repeating-characters/">3. Longest Substring Without Repeating Characters</a>
 */
public class _3_NoRepeatingSubstring {
    /**
     * <a href="https://www.youtube.com/watch?v=XfSgQvKfcys&list=PL7g1jYj15RUOjoeZAJsWjwV8XUo9r0hwc&index=4">source</a>
     */
    public int slidingWindow(String s) {
        Map<Character, Boolean> charMap = new HashMap<>();
        int longestSubLen = 0;
        int start = 0;
        for (int end = 0; end < s.length(); end++) {
            char c = s.charAt(end);
            if (!charMap.containsKey(c) || !charMap.get(c)) {
                if (end - start + 1 > longestSubLen) longestSubLen = end - start + 1;
            } else {
                while (charMap.get(c)) {
                    charMap.put(s.charAt(start), false);
                    start++;
                }
            }
            charMap.put(c, true);
        }
        return longestSubLen;
    }
}

package problems.p0101_0200;

import patterns.DynamicProgrammingPattern;
import patterns.StringPattern;
import patterns.HashTablePattern;
import difficulty.Medium;

import java.util.List;

/**
 * Given a string s and a dictionary of strings wordDict,
 * return true if s can be segmented into a space-separated sequence of one or more dictionary words.
 *
 * Note that the same word in the dictionary may be reused multiple times in the segmentation.
 *
 * Example 1:
 * Input: s = "applepenapple", wordDict = ["apple","pen"]
 * Output: true
 * Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".
 * Note that you are allowed to reuse a dictionary word.
 *
 * Example 2:
 * Input: s = "catsandog", wordDict = ["cats","dog","sand","and","cat"]
 * Output: false
 * <a href="https://leetcode.com/problems/word-break/">139. Word Break</a>
 */
public class _0139_WordBreak implements DynamicProgrammingPattern, StringPattern, HashTablePattern, Medium {
    /**
     * Time Limit Exceeded
     */
    public boolean practice(java.lang.String s, List<java.lang.String> wordDict) {
        if (wordDict.contains(s)) return true;
        for (java.lang.String word : wordDict) {
            if (word.length() < s.length() && s.startsWith(word)
                    && practice(s.substring(word.length()), wordDict)) {
                return true;
            }
        }
        return false;
    }

    public boolean practice2(java.lang.String s, List<java.lang.String> wordDict) {
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        int maxLength = 0;
        for (java.lang.String word : wordDict) {
            maxLength = java.lang.Math.max(maxLength, word.length());
        }
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (i - j > maxLength) continue;
                if (dp[j] && wordDict.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }
}

package dyanmicPrograming;

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
public class _139_WordBreak {
    /**
     * Time Limit Exceeded
     */
    public boolean practice(String s, List<String> wordDict) {
        if (wordDict.contains(s)) return true;
        for (String word : wordDict) {
            if (word.length() < s.length() && s.startsWith(word)
                    && practice(s.substring(word.length()), wordDict)) {
                return true;
            }
        }
        return false;
    }

    public boolean practice2(String s, List<String> wordDict) {
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        int maxLength = 0;
        for (String word : wordDict) {
            maxLength = Math.max(maxLength, word.length());
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

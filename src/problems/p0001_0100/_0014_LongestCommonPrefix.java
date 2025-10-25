package problems.p0001_0100;

import patterns.StringPattern;
import difficulty.Easy;

/**
 * Write a function to find the longest common prefix string amongst an array of strings.
 *
 * If there is no common prefix, return an empty string "".
 *
 * Example 1:
 * Input: strs = ["flower","flow","flight"]
 * Output: "fl"
 *
 * Example 2:
 * Input: strs = ["dog","racecar","car"]
 * Output: ""
 * Explanation: There is no common prefix among the input strings.
 * <br>
 * <a href="https://leetcode.com/problems/longest-common-prefix/">14. Longest Common Prefix</a>
 */
public class _0014_LongestCommonPrefix implements StringPattern, Easy {
    public java.lang.String practice(java.lang.String[] strs) {
        char[][] charArrays = new char[strs.length][];
        charArrays[0] = strs[0].toCharArray();
        int length = charArrays[0].length;
        for (int i = 1; i < strs.length; i++) {
            charArrays[i] = strs[i].toCharArray();
            length = java.lang.Math.min(length, charArrays[i].length);
            int commonLength = 0;
            for (int j = 0; j < length; j++) {
                if (charArrays[i][commonLength] != charArrays[i - 1][commonLength]) {
                    break;
                }
                commonLength += 1;
            }
            if (commonLength == 0) {
                return "";
            }
            length = commonLength;
        }
        return strs[0].substring(0, length);
    }

    public java.lang.String anotherAnswer(java.lang.String[] strs) {
        java.lang.String prefix = strs[0];
        for(int i = 1; i < strs.length; i++){
            while(strs[i].indexOf(prefix) != 0)
                prefix = prefix.substring(0, prefix.length() - 1);
            if(prefix.isEmpty())
                break;
        }
        return prefix;
    }
}

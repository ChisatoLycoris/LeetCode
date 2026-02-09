package problems.p0401_0500;

import patterns.HashTablePattern;
import patterns.StringPattern;
import patterns.GreedyPattern;
import difficulty.Easy;

/**
 * Given a string s which consists of lowercase or uppercase letters, return the
 * length of the longest palindrome that can be built with those letters.
 * (Palindrome: A palindrome is a string that reads the same forward and
 * backward.)
 *
 * Letters are case sensitive, for example, "Aa" is not considered a palindrome.
 *
 * Example 1:
 * Input: s = "abccccdd"
 * Output: 7
 * Explanation: One longest palindrome that can be built is "dccaccd", whose
 * length is 7.
 *
 * Example 2:
 * Input: s = "a"
 * Output: 1
 * Explanation: The longest palindrome that can be built is "a", whose length is
 * 1.
 *
 * Constraints:
 * - 1 <= s.length <= 2000
 * - s consists of lowercase and/or uppercase English letters only.
 *
 * <a href="https://leetcode.com/problems/longest-palindrome/">409. Longest
 * Palindrome</a>
 */
public class _0409_LongestPalindrome implements HashTablePattern, StringPattern, GreedyPattern, Easy {

    /**
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public int longestPalindrome(String s) {
        int[] map = new int['z' - 'A' + 1];
        for (char c : s.toCharArray()) {
            int idx = c - 'A';
            map[idx] = map[idx] + 1;
        }
        int length = 0;
        boolean hasOdd = false;
        for (int i = 0; i < map.length; i++) {
            if (map[i] == 0)
                continue;
            if (map[i] % 2 == 0) {
                length += map[i];
            } else {
                length += map[i] - 1;
                hasOdd = true;
            }
        }
        if (hasOdd) {
            length += 1;
        }
        return length;
    }
}

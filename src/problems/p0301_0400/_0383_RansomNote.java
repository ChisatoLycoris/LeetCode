package problems.p0301_0400;

import patterns.HashTablePattern;
import patterns.StringPattern;
import difficulty.Easy;

/**
 * Given two strings ransomNote and magazine, return true if ransomNote can be
 * constructed by using the letters from magazine and false otherwise.
 *
 * Each letter in magazine can only be used once in ransomNote.
 *
 * Example 1:
 * Input: ransomNote = "a", magazine = "b"
 * Output: false
 *
 * Example 2:
 * Input: ransomNote = "aa", magazine = "ab"
 * Output: false
 *
 * Example 3:
 * Input: ransomNote = "aa", magazine = "aab"
 * Output: true
 *
 * Constraints:
 * - 1 <= ransomNote.length, magazine.length <= 10^5
 * - ransomNote and magazine consist of lowercase English letters.
 *
 * <a href="https://leetcode.com/problems/ransom-note/">383. Ransom Note</a>
 */
public class _0383_RansomNote implements HashTablePattern, StringPattern, Easy {

    /**
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public boolean canConstruct(String ransomNote, String magazine) {
        int[] ransomNoteMap = new int[26];
        int[] magazineMap = new int[26];
        int idx0 = 'a';
        for (int i = 0; i < ransomNote.length(); i++) {
            char target = ransomNote.charAt(i);
            int idx = target - idx0;
            ransomNoteMap[idx] = ransomNoteMap[idx] + 1;
        }
        for (int i = 0; i < magazine.length(); i++) {
            char target = magazine.charAt(i);
            int idx = target - idx0;
            magazineMap[idx] = magazineMap[idx] + 1;
        }
        for (int i = 0; i < 26; i++) {
            if (ransomNoteMap[i] > magazineMap[i]) {
                return false;
            }
        }
        return true;
    }

    /**
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public boolean canConstructOptimize(String ransomNote, String magazine) {
        int[] count = new int[26];
        for (char c : magazine.toCharArray()) {
            count[c - 'a']++;
        }
        for (char c : ransomNote.toCharArray()) {
            if (--count[c - 'a'] < 0) {
                return false;
            }
        }
        return true;
    }
}

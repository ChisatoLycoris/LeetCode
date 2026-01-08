package problems.p0201_0300;

import java.util.HashMap;
import java.util.Map;

import patterns.HashTablePattern;
import patterns.SortingPattern;
import patterns.StringPattern;

/**
 * Given two strings s and t, return true if t is an anagram of s, and false
 * otherwise.
 * An anagram is a word or phrase formed by rearranging the letters of a
 * different word or phrase, using all the original letters exactly once.
 *
 * Example 1:
 * Input: s = "anagram", t = "nagaram"
 * Output: true
 *
 * Example 2:
 * Input: s = "rat", t = "car"
 * Output: false
 */
public class _0242_ValidAnagram implements HashTablePattern, StringPattern, SortingPattern {
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        int[] sRecord = new int[26];
        int[] tRecode = new int[26];
        for (int i = 0; i < s.length(); i++) {
            int sIdx = s.charAt(i) - 'a';
            sRecord[sIdx] = sRecord[sIdx] + 1;
            int tIdx = t.charAt(i) - 'a';
            tRecode[tIdx] = tRecode[tIdx] + 1;
        }
        for (int i = 0; i < 26; i++) {
            if (sRecord[i] != tRecode[i]) {
                return false;
            }
        }
        return true;
    }

    public boolean isAnagramMap(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        Map<Character, Integer> sRecord = new HashMap<>();
        Map<Character, Integer> tRecord = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            int sCount = sRecord.getOrDefault(s.charAt(i), 0);
            sRecord.put(s.charAt(i), sCount + 1);
            int tCount = tRecord.getOrDefault(t.charAt(i), 0);
            tRecord.put(t.charAt(i), tCount + 1);
        }
        return sRecord.equals(tRecord);
    }
}

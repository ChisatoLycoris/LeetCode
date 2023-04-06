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
     * Time Complexity: O(N^2)
     * Space Complexity: O(N)
     */
    public int practice(String s) {
        int best = 0;
        char[] arr = s.toCharArray();
        for (int i = 0; i < arr.length; i++) {
            Map<Character, Integer> map = new HashMap<>();
            int length = 0;
            int index = i;
            while (!map.containsKey(arr[index])) {
                map.put(arr[index], index);
                length += 1;
                index += 1;
                if (index >= arr.length) {
                    return Math.max(best, length);
                }
            }
            i = map.get(arr[index]);
            if (length > best) {
                best = length;
            }
        }
        return best;
    }

    /**
     * Time Complexity: O(N)
     * Space Complexity: O(1)
     * <br>
     * <a href="https://leetcode.com/problems/longest-substring-without-repeating-characters/solutions/1729/11-line-simple-java-solution-o-n-with-explanation/?orderBy=most_votes">source</a>
     */
    public int solution(String s) {
        int result = 0;
        int[] cache = new int[128];
        for (int i = 0, j = 0; i < s.length(); i++) {
            j = Math.max(j, cache[s.charAt(i)]);
            cache[s.charAt(i)] = i + 1;
            result = Math.max(result, i - j + 1);
        }
        return result;
    }
}

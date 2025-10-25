package problems.p0001_0100;

import patterns.BacktrackingPattern;
import patterns.StringPattern;
import difficulty.Medium;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Given a string containing digits from 2-9 inclusive,
 * return all possible letter combinations that the number could represent.
 * Return the answer in any order.
 *
 * A mapping of digits to letters (just like on the telephone buttons) is given below.
 * Note that 1 does not map to any letters.
 * 2: abc
 * 3: def
 * 4: ghi
 * 5: jkl
 * 6: mno
 * 7: pqrs
 * 8: tuv
 * 9: wxyz
 *
 * Example 1:
 * Input: digits = "23"
 * Output: ["ad","ae","af","bd","be","bf","cd","ce","cf"]
 * <br>
 * <a href="https://leetcode.com/problems/letter-combinations-of-a-phone-number/
 * ">17. Letter Combinations of a Phone Number</a>
 */
public class _0017_LetterCombinationOfPhoneNumber implements BacktrackingPattern, StringPattern, Medium {
    public List<java.lang.String> practice(java.lang.String digits) {
        List<java.lang.String> result = new LinkedList<>();
        if (digits.length() == 0) {
            return result;
        }
        Set<java.lang.String>[] letter = new Set[8];
        letter[0] = Set.of("a", "b", "c");
        letter[1] = Set.of("d", "e", "f");
        letter[2] = Set.of("g", "h", "i");
        letter[3] = Set.of("j", "k", "l");
        letter[4] = Set.of("m", "n", "o");
        letter[5] = Set.of("p", "q", "r", "s");
        letter[6] = Set.of("t", "u", "v");
        letter[7] = Set.of("w", "x", "y", "z");
        result.add("");
        for (int i = 0; i < digits.length(); i++) {
            List<java.lang.String> temp = new LinkedList<>();
            while (!result.isEmpty()) {
                java.lang.String s = result.remove(0);
                for (java.lang.String l : letter[digits.charAt(i) - 50]) {
                    temp.add(s + l);
                }
            }
            result = temp;
        }
        return result;
    }
}

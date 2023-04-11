/**
 * Roman numerals are represented by seven different symbols: I, V, X, L, C, D and M.
 *
 * Symbol       Value
 * I             1
 * V             5
 * X             10
 * L             50
 * C             100
 * D             500
 * M             1000
 * For example, 2 is written as II in Roman numeral, just two ones added together.
 * 12 is written as XII, which is simply X + II. The number 27 is written as XXVII, which is XX + V + II.
 *
 * Roman numerals are usually written largest to smallest from left to right.
 * However, the numeral for four is not IIII. Instead, the number four is written as IV.
 * Because the one is before the five we subtract it making four.
 * The same principle applies to the number nine, which is written as IX.
 * There are six instances where subtraction is used:
 *
 * I can be placed before V (5) and X (10) to make 4 and 9.
 * X can be placed before L (50) and C (100) to make 40 and 90.
 * C can be placed before D (500) and M (1000) to make 400 and 900.
 * Given a roman numeral, convert it to an integer.
 * <br>
 * <a href="https://leetcode.com/problems/roman-to-integer/">13. Roman to Integer</a>
 */
public class _13_RomanToInteger {
    public int practice(String s) {
        int result = 0;
        int length = s.length();
        for (int i = 0; i < length; i++) {
            char next = 'a';
            if (i + 1 < length) {
                next = s.charAt(i + 1);
            }
            switch (s.charAt(i)) {
                case 'M' -> {
                    result += 1000;
                }
                case 'D' -> {
                    result += 500;
                }
                case 'C' -> {
                    if (next == 'D') {
                        result += 400;
                        i += 1;
                    } else if (next == 'M') {
                        result += 900;
                        i += 1;
                    } else {
                        result += 100;
                    }
                }
                case 'L' -> {
                    result += 50;
                }
                case 'X' -> {
                    if (next == 'L') {
                        result += 40;
                        i += 1;
                    } else if (next == 'C') {
                        result += 90;
                        i += 1;
                    } else {
                        result += 10;
                    }
                }
                case 'V' -> {
                    result += 5;
                }
                case 'I' -> {
                    if (next == 'V') {
                        result += 4;
                        i += 1;
                    } else if (next == 'X') {
                        result += 9;
                        i += 1;
                    } else {
                        result += 1;
                    }
                }

            }
        }
        return result;
    }

    public int optimal(String s) {
        int answer = 0, number = 0, prev = 0;

        for (int j = s.length() - 1; j >= 0; j--) {
            switch (s.charAt(j)) {
                case 'M' -> number = 1000;
                case 'D' -> number = 500;
                case 'C' -> number = 100;
                case 'L' -> number = 50;
                case 'X' -> number = 10;
                case 'V' -> number = 5;
                case 'I' -> number = 1;
            }
            if (number < prev) {
                answer -= number;
            } else {
                answer += number;
            }
            prev = number;
        }
        return answer;
    }
}

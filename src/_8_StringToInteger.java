/**
 * Implement the myAtoi(string s) function, which converts a string to a 32-bit signed integer (similar to C/C++'s atoi function).
 *
 * The algorithm for myAtoi(string s) is as follows:
 *
 * Read in and ignore any leading whitespace.
 * Check if the next character (if not already at the end of the string) is '-' or '+'.
 * Read this character in if it is either. This determines if the final result is negative or positive respectively.
 * Assume the result is positive if neither is present.
 * Read in next the characters until the next non-digit character or the end of the input is reached.
 * The rest of the string is ignored.
 * Convert these digits into an integer (i.e. "123" -> 123, "0032" -> 32).
 * If no digits were read, then the integer is 0. Change the sign as necessary (from step 2).
 * If the integer is out of the 32-bit signed integer range [-2^31, 2^31 - 1],
 * then clamp the integer so that it remains in the range.
 * Specifically, integers less than -2^31 should be clamped to -2^31,
 * and integers greater than 2^31 - 1 should be clamped to 2^31 - 1.
 * Return the integer as the final result.
 *
 * <br>
 * <a href="https://leetcode.com/problems/string-to-integer-atoi/">8. String to Integer (atoi)</a>
 */
public class _8_StringToInteger {
    public int practice(String s) {
        char[] charArray = s.toCharArray();
        int idx = 0;
        while (idx < charArray.length && charArray[idx] == ' ') {
            idx += 1;
        }
        boolean negative = false;
        if (idx >= charArray.length) {
            return 0;
        } else if (charArray[idx] == '-') {
            idx += 1;
            negative = true;
        } else if (charArray[idx] == '+') {
            idx += 1;
        }
        int result = 0;
        while (idx < charArray.length && charArray[idx] > 47 && charArray[idx] < 58) {
            int pop = charArray[idx] - 48;
            if (negative) {
                pop = - pop;
            }
            if (result > Integer.MAX_VALUE / 10 || (result == Integer.MAX_VALUE / 10 && pop > 7)) {
                return Integer.MAX_VALUE;
            }
            if (result < Integer.MIN_VALUE / 10 || (result == Integer.MIN_VALUE / 10 && pop < -8)) {
                return Integer.MIN_VALUE;
            }
            result *= 10;
            result += pop;
            idx += 1;
        }
        return result;
    }

    /**
     * <a href="https://leetcode.com/problems/string-to-integer-atoi/solutions/4643/java-solution-with-4-steps-explanations/">source</a>
     */
    public int optimal(String str) {
        int index = 0, sign = 1, total = 0;
        //1. Empty string
        if(str.length() == 0) return 0;

        //2. Remove Spaces
        while(index < str.length() && str.charAt(index) == ' ')
            index ++;

        //3. Handle signs
        if(str.charAt(index) == '+' || str.charAt(index) == '-'){
            sign = str.charAt(index) == '+' ? 1 : -1;
            index ++;
        }

        //4. Convert number and avoid overflow
        while(index < str.length()){
            int digit = str.charAt(index) - '0';
            if(digit < 0 || digit > 9) break;

            //check if total will be overflow after 10 times and add digit
            if(Integer.MAX_VALUE/10 < total || Integer.MAX_VALUE/10 == total && Integer.MAX_VALUE %10 < digit)
                return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;

            total = 10 * total + digit;
            index ++;
        }
        return total * sign;
    }
}

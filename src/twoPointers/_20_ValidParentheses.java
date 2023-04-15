package twoPointers;

import java.util.Stack;

/**
 * Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
 *
 * An input string is valid if:
 *
 * Open brackets must be closed by the same type of brackets.
 * Open brackets must be closed in the correct order.
 * Every close bracket has a corresponding open bracket of the same type.
 */
public class _20_ValidParentheses {
    public boolean practice(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            switch (c) {
                case ')' -> {
                    if (stack.isEmpty() || !stack.pop().equals('(')) return false;
                }
                case ']' -> {
                    if (stack.isEmpty() || !stack.pop().equals('[')) return false;
                }
                case '}' -> {
                    if (stack.isEmpty() || !stack.pop().equals('{')) return false;
                }
                default -> stack.push(c);
            }
        }
        return stack.isEmpty();
    }

    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
                continue;
            }
            if (stack.isEmpty()) return false;
            char prev =stack.pop();
            if (c == ')' && prev != '(') return false;
            if (c == ']' && prev != '[') return false;
            if (c == '}' && prev != '{') return false;
        }
        return stack.isEmpty();
    }
}

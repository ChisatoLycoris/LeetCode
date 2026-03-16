package problems.p0101_0200;

import patterns.StackPattern;

import difficulty.Medium;

/**
 * You are given an array of strings tokens that represents an arithmetic
 * expression in a Reverse Polish Notation.
 *
 * Evaluate the expression. Return an integer that represents the value of
 * the expression.
 *
 * Note that:
 * - The valid operators are '+', '-', '*', and '/'.
 * - Each operand may be an integer or another expression.
 * - The division between two integers always truncates toward zero.
 * - There will not be any division by zero.
 * - The input represents a valid arithmetic expression in a reverse polish
 * notation.
 * - The answer and all the intermediate calculations can be represented in a
 * 32-bit integer.
 *
 * Example 1:
 * Input: tokens = ["2","1","+","3","*"]
 * Output: 9
 * Explanation: ((2 + 1) * 3) = 9
 *
 * Example 2:
 * Input: tokens = ["4","13","5","/","+"]
 * Output: 6
 * Explanation: (4 + (13 / 5)) = 6
 *
 * Example 3:
 * Input: tokens = ["10","6","9","3","+","-11","*","/","*","17","+","5","+"]
 * Output: 22
 * Explanation: ((10 * (6 / ((9 + 3) * -11))) + 17) + 5
 *
 * Constraints:
 * - 1 <= tokens.length <= 10^4
 * - tokens[i] is either an operator: "+", "-", "*", or "/", or an integer in
 * the range [-200, 200].
 *
 * <a href=
 * "https://leetcode.com/problems/evaluate-reverse-polish-notation/">150.
 * Evaluate Reverse Polish Notation</a>
 */
public class _0150_EvaluateReversePolishNotation implements StackPattern, Medium {

    /**
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     */
    public int evalRPN(String[] tokens) {
        int[] stack = new int[tokens.length];
        int top = 0;
        for (String s : tokens) {
            switch (s) {
                case "+":
                    stack[top - 2] = stack[top - 2] + stack[top - 1];
                    top--;
                    break;
                case "-":
                    stack[top - 2] = stack[top - 2] - stack[top - 1];
                    top--;
                    break;
                case "*":
                    stack[top - 2] = stack[top - 2] * stack[top - 1];
                    top--;
                    break;
                case "/":
                    stack[top - 2] = stack[top - 2] / stack[top - 1];
                    top--;
                    break;
                default:
                    stack[top++] = Integer.parseInt(s);
                    break;
            }
        }
        return stack[0];
    }
}

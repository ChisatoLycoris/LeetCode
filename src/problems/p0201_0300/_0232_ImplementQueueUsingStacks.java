package problems.p0201_0300;

import patterns.StackPattern;
import patterns.QueuePattern;
import difficulty.Easy;

import java.util.Stack;

/**
 * Implement a first in first out (FIFO) queue using only two stacks. The
 * implemented
 * queue should support all the functions of a normal queue (push, peek, pop,
 * and empty).
 *
 * Implement the MyQueue class:
 * - void push(int x) Pushes element x to the back of the queue.
 * - int pop() Removes the element from the front of the queue and returns it.
 * - int peek() Returns the element at the front of the queue.
 * - boolean empty() Returns true if the queue is empty, false otherwise.
 *
 * Notes:
 * - You must use only standard operations of a stack, which means only push to
 * top,
 * peek/pop from top, size, and is empty operations are valid.
 * - Depending on your language, the stack may not be supported natively. You
 * may
 * simulate a stack using a list or deque as long as you use only a stack's
 * standard operations.
 *
 * Example 1:
 * Input: ["MyQueue", "push", "push", "peek", "pop", "empty"]
 * [[], [1], [2], [], [], []]
 * Output: [null, null, null, 1, 1, false]
 * Explanation:
 * MyQueue myQueue = new MyQueue();
 * myQueue.push(1); // queue is: [1]
 * myQueue.push(2); // queue is: [1, 2] (leftmost is front of the queue)
 * myQueue.peek(); // return 1
 * myQueue.pop(); // return 1, queue is [2]
 * myQueue.empty(); // return false
 *
 * Constraints:
 * - 1 <= x <= 9
 * - At most 100 calls will be made to push, pop, peek, and empty.
 * - All the calls to pop and peek are valid.
 *
 * Follow-up: Can you implement the queue such that each operation is amortized
 * O(1)
 * time complexity?
 *
 * <a href="https://leetcode.com/problems/implement-queue-using-stacks/">232.
 * Implement Queue using Stacks</a>
 */
public class _0232_ImplementQueueUsingStacks implements StackPattern, QueuePattern, Easy {

    /**
     * Inner class implementing Queue using two Stacks.
     *
     * Hint: Use two stacks - one for input, one for output.
     * Transfer elements from input to output when output is empty.
     */
    class MyQueue {
        private Stack<Integer> inputStack;
        private Stack<Integer> outputStack;

        public MyQueue() {
            this.inputStack = new Stack<>();
            this.outputStack = new Stack<>();
        }

        /**
         * Pushes element x to the back of the queue.
         * Time Complexity: O(1)
         */
        public void push(int x) {
            inputStack.push(x);
        }

        /**
         * Removes the element from the front of the queue and returns it.
         * Time Complexity: Amortized O(1)
         */
        public int pop() {
            if (outputStack.isEmpty()) {
                while (!inputStack.isEmpty()) {
                    outputStack.push(inputStack.pop());
                }
            }
            return outputStack.pop();
        }

        /**
         * Returns the element at the front of the queue.
         * Time Complexity: Amortized O(1)
         */
        public int peek() {
            if (outputStack.isEmpty()) {
                while (!inputStack.isEmpty()) {
                    outputStack.push(inputStack.pop());
                }
            }
            return outputStack.peek();
        }

        /**
         * Returns true if the queue is empty, false otherwise.
         * Time Complexity: O(1)
         */
        public boolean empty() {
            return inputStack.isEmpty() && outputStack.isEmpty();
        }
    }
}

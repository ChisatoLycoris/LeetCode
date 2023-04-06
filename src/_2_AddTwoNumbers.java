/**
 * You are given two non-empty linked lists representing two non-negative integers.
 * The digits are stored in reverse order, and each of their nodes contains a single digit.
 * Add the two numbers and return the sum as a linked list.
 *
 * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 *
 * Example 1:
 * Input: l1 = [2,4,3], l2 = [5,6,4]
 * Output: [7,0,8]
 * Explanation: 342 + 465 = 807.
 *
 * Example 2:
 * Input: l1 = [0], l2 = [0]
 * Output: [0]
 *
 * Example 3:
 * Input: l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
 * Output: [8,9,9,9,0,0,0,1]
 *
 * <br>
 * <a href="https://leetcode.com/problems/add-two-numbers/">2. Add Two Numbers</a>
 */
public class _2_AddTwoNumbers {
    /**
     * Time Complexity: O(N)
     * Space Complexity: O(N)
     */
    public ListNode practice(ListNode l1, ListNode l2) {
        ListNode result = new ListNode();
        ListNode write = result;
        boolean carryOver = false;
        while (!(l1 == null && l2 == null && !carryOver)) {
            int val1 = (l1 == null) ? 0 : l1.val;
            int val2 = (l2 == null) ? 0 : l2.val;
            int sum = val1 + val2;
            if (carryOver) {
                sum += 1;
                carryOver = false;
            }
            if (sum > 9) {
                sum -= 10;
                carryOver = true;
            }
            write.next = new ListNode(sum);
            write = write.next;
            l1 = (l1 == null) ? null : l1.next;
            l2 = (l2 == null) ? null : l2.next;
        }
        return result.next;
    }

    /**
     * Time Complexity: O(N)
     * Space Complexity: O(N)
     */
    public ListNode optimal(ListNode l1, ListNode l2) {
        ListNode sentinel = new ListNode();
        ListNode write = sentinel;
        int sum = 0;
        while (l1 != null || l2 != null) {
            sum /= 10;
            if (l1 != null) {
                sum += l1.val;
                l1 = l1.next;
            }
            if (l2 != null) {
                sum += l2.val;
                l2 = l2.next;
            }
            write.next = new ListNode(sum % 10);
            write = write.next;
        }
        if (sum / 10 == 1) {
            write.next = new ListNode(1);
        }
        return sentinel.next;
    }
    class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
}


/**
 * Given the head of a linked list, remove the nth node from the end of the list and return its head.
 *
 * Example 1:
 * Input: head = [1,2,3,4,5], n = 2
 * Output: [1,2,3,5]
 *
 * Example 2:
 * Input: head = [1], n = 1
 * Output: []
 * <br>
 * <a href="https://leetcode.com/problems/remove-nth-node-from-end-of-list/">19. Remove Nth Node From End of List</a>
 */
public class _19_RemoveNthNodeFromEndOfList {
    public ListNode practice(ListNode head, int n) {
        int length = 0;
        ListNode pointer = head;
        while (pointer != null) {
            length += 1;
            pointer = pointer.next;
        }
        if (length == n) {
            return head.next;
        }
        pointer = head;
        int count = 1;
        while (count != length - n) {
            pointer = pointer.next;
            count += 1;
        }
        pointer.next = pointer.next.next;
        return head;
    }

    private class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) {this.val = val;}
        ListNode(int val, ListNode next) {this.val = val; this.next = next;}

    }
}

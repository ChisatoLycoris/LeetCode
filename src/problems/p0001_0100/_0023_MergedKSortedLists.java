package problems.p0001_0100;

import patterns.DivideAndConquerPattern;
import patterns.HeapPattern;
import patterns.LinkedListPattern;
import difficulty.Hard;

/**
 * You are given an array of k linked-lists lists, each linked-list is sorted in ascending order.
 *
 * Merge all the linked-lists into one sorted linked-list and return it.
 *
 * Example 1:
 * Input: lists = [[1,4,5],[1,3,4],[2,6]]
 * Output: [1,1,2,3,4,4,5,6]
 * Explanation: The linked-lists are:
 * [
 *   1->4->5,
 *   1->3->4,
 *   2->6
 * ]
 * merging them into one sorted list:
 * 1->1->2->3->4->4->5->6
 *
 * <br>
 * <a href="https://leetcode.com/problems/merge-k-sorted-lists/">23. Merge k Sorted Lists</a>
 */
public class _0023_MergedKSortedLists implements DivideAndConquerPattern, HeapPattern, LinkedListPattern, Hard {
    public ListNode practice(ListNode[] lists) {
        if (lists.length == 0) return null;
        if (lists.length == 1) return lists[0];
        ListNode[] leftList = new ListNode[lists.length / 2];
        System.arraycopy(lists, 0, leftList, 0, lists.length / 2);
        ListNode[] rightList = new ListNode[lists.length - (lists.length / 2)];
        System.arraycopy(lists, lists.length / 2, rightList, 0, lists.length - (lists.length / 2));
        ListNode left = practice(leftList);
        ListNode right = practice(rightList);
        return merge(left, right);
    }

    private ListNode merge(ListNode left, ListNode right) {
        ListNode result = new ListNode();
        ListNode pointer = result;
        while (left != null && right != null) {
            if (left.val > right.val) {
                pointer.next = new ListNode(right.val);
                right = right.next;
            } else {
                pointer.next = new ListNode(left.val);
                left = left.next;
            }
            pointer = pointer.next;
        }
        while (left != null) {
            pointer.next = new ListNode(left.val);
            left = left.next;
            pointer = pointer.next;
        }
        while (right != null) {
            pointer.next = new ListNode(right.val);
            right = right.next;
            pointer = pointer.next;
        }
        return result.next;
    }

    private static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public static void main(java.lang.String[] args) {
        ListNode[] testCase = new ListNode[2];
        testCase[0] = new ListNode(1, new ListNode(2, new ListNode(3)));
        testCase[1] = new ListNode(4, new ListNode(5, new ListNode(6, new ListNode(7))));
        new _0023_MergedKSortedLists().practice(testCase);
    }
}

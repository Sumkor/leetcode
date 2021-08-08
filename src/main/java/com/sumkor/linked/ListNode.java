package com.sumkor.linked;

/**
 * Definition for singly-linked list.
 *
 * @author Sumkor
 * @since 2021/8/5
 */
public class ListNode {

    public int val;
    public ListNode next;

    public ListNode() {
    }

    public ListNode(int val) {
        this.val = val;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    /**
     * 打印链表
     */
    public static String print(ListNode node) {
        if (node == null) {
            return "[]";
        }
        StringBuilder result = new StringBuilder("[" + node.val);
        // 游标节点
        ListNode curr = node.next;
        while (curr != null) {
            result.append(",").append(curr.val);
            curr = curr.next;
        }
        return result + "]";
    }
}

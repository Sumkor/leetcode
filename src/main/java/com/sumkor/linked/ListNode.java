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

    /**
     * 创建链表
     */
    public static ListNode create(Integer... list) {
        ListNode head = new ListNode();
        ListNode curr = head;
        for (Integer integer : list) {
            curr.next = new ListNode(integer);
            curr = curr.next;
        }
        return head.next;
    }

    public static void main(String[] args) {
        ListNode listNode = create(1, 2, 3, 4);
        String print = print(listNode);
        System.out.println("print = " + print);
    }
}

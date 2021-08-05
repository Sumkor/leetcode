package com.sumkor.linked._0019_removefromend;

/**
 * Definition for singly-linked list.
 *
 * @author Sumkor
 * @since 2021/8/5
 */
public class ListNode {

    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
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
        ListNode nextNode = node.next;
        while (nextNode != null) {
            result.append(",").append(nextNode.val);
            nextNode = nextNode.next;
        }
        return result + "]";
    }
}

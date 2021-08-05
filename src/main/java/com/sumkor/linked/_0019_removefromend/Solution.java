package com.sumkor.linked._0019_removefromend;

import org.junit.Test;

/**
 * @author Sumkor
 * @since 2021/8/5
 */
public class Solution {

    /**
     * 第一次遍历找到目标节点的正序位置，第二次遍历移除目标节点
     *
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗：36.3 MB, 在所有 Java 提交中击败了65.92% 的用户
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null) {
            return null;
        }
        // 利用游标节点，计数
        int count = 1;
        ListNode nextNode = head.next;
        while (nextNode != null) {
            nextNode = nextNode.next;
            count++;
        }
        // n 合法性校验
        if (count < n || n < 0) {
            return head;
        }
        // 特殊情况，只有一个节点
        if (count == 1 && n == 1) {
            return null;
        }
        // 特殊情况，移除 head
        if (count == n) {
            return head.next;
        }
        // 找到倒数第 n 个节点的正序下标（从 0 开始）
        int index = count - n;
        // 移除 index 位置的节点
        ListNode prevNode = head;
        nextNode = head.next;
        int i = 1;
        while (nextNode != null) {
            // 当前 i 位置是 nextNode，需要移除 nextNode
            if (i == index) {
                prevNode.next = nextNode.next;
                break;
            }
            prevNode = nextNode;
            nextNode = nextNode.next;
            i++;
        }
        return head;
    }

    /**
     * 输入：head = [1,2,3,4,5], n = 2
     * 输出：[1,2,3,5]
     */
    @Test
    public void test01() {
        ListNode node05 = new ListNode(5);
        ListNode node04 = new ListNode(4, node05);
        ListNode node03 = new ListNode(3, node04);
        ListNode node02 = new ListNode(2, node03);
        ListNode node01 = new ListNode(1, node02);
        ListNode listNode = removeNthFromEnd(node01, 2);
        System.out.println(ListNode.print(listNode));
    }

    /**
     * 输入：head = [1], n = 1
     * 输出：[]
     */
    @Test
    public void test02() {
        ListNode node01 = new ListNode(1);
        ListNode listNode = removeNthFromEnd(node01, 1);
        System.out.println(ListNode.print(listNode));
    }

    /**
     * 输入：head = [1,2], n = 1
     * 输出：[1]
     */
    @Test
    public void test03() {
        ListNode node02 = new ListNode(2);
        ListNode node01 = new ListNode(1, node02);
        ListNode listNode = removeNthFromEnd(node01, 1);
        System.out.println(ListNode.print(listNode));
    }

    /**
     * 输入：[1,2] 2
     * 输出：[2]
     */
    @Test
    public void test04() {
        ListNode node02 = new ListNode(2);
        ListNode node01 = new ListNode(1, node02);
        ListNode listNode = removeNthFromEnd(node01, 2);
        System.out.println(ListNode.print(listNode));
    }
}

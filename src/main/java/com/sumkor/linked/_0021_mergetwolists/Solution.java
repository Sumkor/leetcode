package com.sumkor.linked._0021_mergetwolists;

import com.sumkor.linked.ListNode;
import org.junit.Test;

/**
 * @author Sumkor
 * @since 2021/8/8
 */
public class Solution {

    /**
     * 以 l1 链表作为结果集（优化版）
     * 遍历 l2 中的每个节点，插入 l1 链表
     * 由于 l1 和 l2 链表是排过序的，因此可以同时遍历
     *
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗：38 MB, 在所有 Java 提交中击败了8.51% 的用户
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0, l1);
        // 定义 l1 上的指针
        ListNode prevL1 = dummy;
        ListNode currL1 = dummy.next;
        // 遍历 l2 中的每个节点，插入 l1 链表
        ListNode currL2 = l2;
        while (currL2 != null) {
            ListNode newNode = new ListNode(currL2.val);
            // 遍历 l1 链表，为 currL2 节点找到插入位置
            while (currL1 != null && currL1.val < currL2.val) {
                prevL1 = currL1;
                currL1 = currL1.next;
            }
            // 到了这里，说明 currL2.val <= currL1.val，可以插入 currL2 节点
            newNode.next = currL1;
            prevL1.next = newNode;
            // 由于在 prevL1 和 currL1 之间插入了 currL2 节点，因此需要修正 currL1，用于下次遍历
            currL1 = newNode;
            // 遍历 l2 链表的下一个节点
            currL2 = currL2.next;
        }
        return dummy.next;
    }

    /**
     * 以 l1 链表作为结果集
     * 遍历 l2 中的每个节点，插入 l1 链表
     *
     * 执行用时：1 ms, 在所有 Java 提交中击败了21.34% 的用户
     * 内存消耗：37.7 MB, 在所有 Java 提交中击败了70.90% 的用户
     */
    public ListNode mergeTwoLists0(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0, l1);
        // 遍历 l2 链表
        ListNode currL2 = l2;
        while (currL2 != null) {
            ListNode newNode = new ListNode(currL2.val);
            // 从头开始遍历 l1 链表，为 currL2 节点找到插入位置
            ListNode prevL1 = dummy;
            ListNode currL1 = dummy.next;
            while (currL1 != null && currL1.val < currL2.val) {
                prevL1 = currL1;
                currL1 = currL1.next;
            }
            // 到了这里，说明 currL2.val >= currL1.val，可以插入 currL2 节点
            newNode.next = currL1;
            prevL1.next = newNode;
            // 遍历 l2 链表的下一个节点
            currL2 = currL2.next;
        }
        return dummy.next;
    }

    /**
     * 官方题解
     * https://leetcode-cn.com/problems/merge-two-sorted-lists/solution/he-bing-liang-ge-you-xu-lian-biao-by-leetcode-solu/
     *
     * 定义新的头节点 preHead，同时遍历两个链表来构造 preHead 为起始的链表
     *
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗：37.9 MB, 在所有 Java 提交中击败了40.30% 的用户
     *
     * 时间复杂度：O(n+m)，其中 n 和 m 分别为两个链表的长度。
     * 因为每次循环迭代中，l1 和 l2 只有一个元素会被放进合并链表中，因此 while 循环的次数不会超过两个链表的长度之和。
     * 所有其他操作的时间复杂度都是常数级别的，因此总的时间复杂度为 O(n+m)。
     *
     * 空间复杂度：O(1)。我们只需要常数的空间存放若干变量。
     */
    public ListNode mergeTwoLists1(ListNode l1, ListNode l2) {
        ListNode preHead = new ListNode(-1);

        ListNode prev = preHead;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                prev.next = l1; // prev 链加入 l1
                l1 = l1.next;
            } else {
                prev.next = l2;
                l2 = l2.next;
            }
            prev = prev.next;
        }

        // 合并后 l1 和 l2 最多只有一个还未被合并完，我们直接将链表末尾指向未合并完的链表即可
        prev.next = l1 == null ? l2 : l1;

        return preHead.next;
    }

    @Test
    public void test() {
        ListNode l1 = ListNode.create(1, 2, 4);
        ListNode l2 = ListNode.create(1, 3, 4);
        ListNode listNode = mergeTwoLists(l1, l2);
        ListNode.print(listNode);
    }
}

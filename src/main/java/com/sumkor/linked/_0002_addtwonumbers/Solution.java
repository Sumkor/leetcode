package com.sumkor.linked._0002_addtwonumbers;

import com.sumkor.linked.ListNode;
import org.junit.Test;

/**
 * @author Sumkor
 * @since 2021/8/8
 */
public class Solution {

    /**
     * 1. 先处理 l1 和 l2 的公共部分
     * 2. 再处理 l1 或 l2 的剩余部分
     * 3. 最后处理进位
     *
     * 执行用时：2 ms, 在所有 Java 提交中击败了98.11% 的用户
     * 内存消耗：38.5 MB, 在所有 Java 提交中击败了82.41% 的用户
     *
     * 时间复杂度：O(max(m,n))，其中 m 和 n 分别为两个链表的长度。我们要遍历两个链表的全部位置，而处理每个位置只需要 O(1) 的时间。
     * 空间复杂度：O(1)。注意返回值不计入空间复杂度。
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        // 游标节点
        ListNode curr = dummy;
        // 进位的值
        int addNum = 0;

        // 先处理公共部分
        while (l1 != null && l2 != null) {
            int newVal = l1.val + l2.val + addNum;
            if (newVal >= 10) {
                addNum = 1; // 进位
                newVal = newVal % 10; // 取个位
            } else {
                addNum = 0;
            }
            curr.next = new ListNode(newVal);
            // 遍历下一个
            curr = curr.next;
            l1 = l1.next;
            l2 = l2.next;
        }

        // 遍历完公共部分，处理剩余的
        ListNode remain = l1 == null ? l2 : l1;
        if (addNum == 0) {
            curr.next = remain;
        } else {
            while (remain != null) {
                int newVal = remain.val + addNum;
                if (newVal >= 10) {
                    addNum = 1; // 进位
                    newVal = newVal % 10; // 取个位
                } else {
                    addNum = 0;
                }
                curr.next = new ListNode(newVal);
                // 遍历下一个
                curr = curr.next;
                remain = remain.next;
            }
        }

        // 如果 l1 和 l2 都处理完，还有进位
        if (addNum == 1) {
            curr.next = new ListNode(1);
        }
        return dummy.next;
    }

    /**
     * 官方题解（写法比较简洁，思路一样）
     * https://leetcode-cn.com/problems/add-two-numbers/solution/liang-shu-xiang-jia-by-leetcode-solution/
     */
    public ListNode addTwoNumbers0(ListNode l1, ListNode l2) {
        ListNode head = null, tail = null;
        int carry = 0;
        while (l1 != null || l2 != null) {
            int n1 = l1 != null ? l1.val : 0;
            int n2 = l2 != null ? l2.val : 0;
            int sum = n1 + n2 + carry;
            if (head == null) {
                head = tail = new ListNode(sum % 10);
            } else {
                tail.next = new ListNode(sum % 10);
                tail = tail.next;
            }
            carry = sum / 10;
            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }
        if (carry > 0) {
            tail.next = new ListNode(carry);
        }
        return head;
    }

    /**
     * 342 + 465 = 807
     */
    @Test
    public void test() {
        ListNode l1 = ListNode.create(2, 4, 3);
        ListNode l2 = ListNode.create(5, 6, 4);

        ListNode listNode = addTwoNumbers(l1, l2);
        ListNode.print(listNode);
    }

    @Test
    public void test02() {
        ListNode l1_1 = new ListNode(0);
        ListNode l2_1 = new ListNode(0);

        ListNode listNode = addTwoNumbers(l1_1, l2_1);
        ListNode.print(listNode);
    }

    /**
     * 9999999 + 9999 = 10009998
     */
    @Test
    public void test3() {
        ListNode l1 = ListNode.create(9, 9, 9, 9, 9, 9, 9);
        ListNode l2 = ListNode.create(9, 9, 9, 9, 9);

        ListNode listNode = addTwoNumbers(l1, l2);
        ListNode.print(listNode);
    }
}

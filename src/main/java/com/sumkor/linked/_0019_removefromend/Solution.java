package com.sumkor.linked._0019_removefromend;

import com.sumkor.linked.ListNode;
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
     *
     * 时间复杂度：O(L)，其中 L 是链表的长度。
     * 空间复杂度：O(1)。
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
     * 官方题解：计算链表长度
     *
     * 在对链表进行操作时，一种常用的技巧是添加一个哑节点（dummy node），它的 next 指针指向链表的头节点。
     * 这样一来，我们就不需要对头节点进行特殊的判断了。
     * 例如，在本题中，如果我们要删除节点 y，我们需要知道节点 y 的前驱节点 x，并将 x 的指针指向 y 的后继节点。
     * 但由于头节点不存在前驱节点，因此我们需要在删除头节点时进行特殊判断。
     * 但如果我们添加了哑节点，那么头节点的前驱节点就是哑节点本身，此时我们就只需要考虑通用的情况即可。
     */
    public ListNode removeNthFromEnd0(ListNode head, int n) {
        ListNode dummy = new ListNode(0, head);
        int length = getLength(head);
        ListNode cur = dummy;
        // 当遍历到第 length - n + 1 个节点时，它的下一个节点就是我们需要删除的节点
        for (int i = 1; i < length - n + 1; ++i) {
            cur = cur.next;
        }
        cur.next = cur.next.next;
        return dummy.next;
    }

    public int getLength(ListNode head) {
        int length = 0;
        while (head != null) {
            ++length;
            head = head.next;
        }
        return length;
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

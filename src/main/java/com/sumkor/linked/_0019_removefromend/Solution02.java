package com.sumkor.linked._0019_removefromend;

import com.sumkor.linked.ListNode;
import org.junit.Test;

/**
 * @author Sumkor
 * @since 2021/8/8
 */
public class Solution02 {

    /**
     * 官方题解
     * https://leetcode-cn.com/problems/remove-nth-node-from-end-of-list/solution/shan-chu-lian-biao-de-dao-shu-di-nge-jie-dian-b-61/
     *
     * 快慢指针
     *
     * 通过双指针，可以在不预处理出链表的长度，以及使用常数空间的前提下解决本题。
     * 由于我们需要找到倒数第 n 个节点，因此我们可以使用两个指针 first 和 second 同时对链表进行遍历，并且 first 比 second 超前 n 个节点。
     * 当 first 遍历到链表的末尾时，second 就恰好处于倒数第 n 个节点。
     *
     * 如果我们能够得到的是倒数第 n 个节点的前驱节点而不是倒数第 n 个节点的话，删除操作会更加方便。
     * 因此我们可以考虑在初始时将 second 指向哑节点，其余的操作步骤不变。
     * 这样一来，当 first 遍历到链表的末尾时，second 的下一个节点就是我们需要删除的节点。
     *
     * 时间复杂度：O(L)，其中 L 是链表的长度。
     * 空间复杂度：O(1)。
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0, head);
        ListNode first = head;
        ListNode second = dummy;
        // 让 first 先走 n 个节点
        for (int i = 0; i < n; ++i) {
            first = first.next;
        }
        // 当 first 遍历到链表的末尾时，second 的下一个节点就是我们需要删除的节点
        while (first != null) {
            first = first.next;
            second = second.next;
        }
        second.next = second.next.next;
        return dummy.next;
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
}

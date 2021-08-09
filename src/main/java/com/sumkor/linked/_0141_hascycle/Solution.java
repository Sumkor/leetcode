package com.sumkor.linked._0141_hascycle;

import com.sumkor.linked.ListNode;
import org.junit.Test;

/**
 * @author Sumkor
 * @since 2021/8/8
 */
public class Solution {

    /**
     * 快慢指针
     *
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗：39.2 MB, 在所有 Java 提交中击败了80.45% 的用户
     *
     * 时间复杂度：O(N)，其中 N 是链表中的节点数。
     *     当链表中不存在环时，快指针将先于慢指针到达链表尾部，链表中每个节点至多被访问两次。
     *     当链表中存在环时，每一轮移动后，快慢指针的距离将减小一。而初始距离为环的长度，因此至多移动 N 轮。
     *
     * 空间复杂度：O(1)。我们只使用了两个指针的额外空间。
     */
    public boolean hasCycle(ListNode head) {
        if (head == null) {
            return false;
        }
        // 起点不同，为了满足 while 循环
        ListNode low = head;
        ListNode fast = head.next;
        while (fast != null) {
            if (fast == low) {
                return true;
            }
            // 慢指针，走一步
            low = low.next;
            // 快指针，走两步
            fast = fast.next;
            if (fast != null) {
                fast = fast.next;
            } else {
                return false;
            }
        }
        return false;
    }

    /**
     * 构造环形链表
     * 使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）
     */
    private ListNode createLink(ListNode head, int pos) {
        if (pos == -1 || head == null) {
            return head;
        }
        // 定位到 pos 位置
        int count = 0;
        ListNode posNode = head;
        ListNode tail = head;
        while (tail.next != null) {
            if (count == pos) {
                posNode = tail;
            }
            count++;
            tail = tail.next;
        }
        System.out.println("pos node is: " + posNode.val);
        // 重构链表
        tail.next = posNode;
        return head;
    }

    /**
     * head = [3,2,0,-4], pos = 1
     */
    @Test
    public void createLink() {
        ListNode listNode = ListNode.create(3, 2, 0, -4);
        createLink(listNode, 1);
    }

    @Test
    public void test() {
        ListNode listNode = createLink(ListNode.create(3, 2, 0, -4), -1);
        boolean hasCycle = hasCycle(listNode);
        System.out.println("hasCycle = " + hasCycle);
    }
}

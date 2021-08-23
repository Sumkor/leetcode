package com.sumkor.array._0042_rainwater;

import org.junit.Test;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author Sumkor
 * @since 2021/8/23
 */
public class Solution {

    /**
     * 使用双指针 + 单调队列（优化版，减少对单调队列的重建）
     *
     * 总体思路：
     * 使用双指针划定 i 到 j 范围作为木桶：
     * 1. 如果 i 是木桶短板，可以直接计算得出 i 到 j 范围的水量
     * 2. 如果 i 不是木桶短板，则需要在 i 到 j 范围中，找到比 i 小的第二大值（记为 j'）作为短板，计算得出 i 到 j' 范围的水量
     *
     * 可以看到，如果 i 是短板，不需要使用单调队列。
     * 当 i 不是短板，才需要使用单调队列，以找到 j 的位置。
     *
     * 单调队列的思路来自于 {@link com.sumkor.array._0239_window.Solution02}
     * 队列入队过程中，若入队的元素比队尾的更大，说明队尾的值无法被用到了，需要弹出
     *
     * 执行用时：3 ms, 在所有 Java 提交中击败了22.31% 的用户
     * 内存消耗：38.5 MB, 在所有 Java 提交中击败了5.13% 的用户
     */
    public int trap(int[] height) {
        int n = height.length;
        if (n <= 2) {
            return 0;
        }
        int water = 0;
        boolean createQueue = true;
        Deque<Integer> queue = new LinkedList<>();
        int i = 0;
        while (i < n) {
            int j = i + 1;
            while (createQueue && height[i] > 0 && j < n) {
                // 维护队列：小值直接加入，大值则需要先弹出小值再加入
                if (queue.isEmpty()) {
                    queue.addLast(i);
                }
                while (!queue.isEmpty() && height[j] > height[queue.peekLast()]) {
                    queue.pollLast();
                }
                queue.addLast(j);
                // 假设 i 是木桶的短板
                if (height[i] > height[j]) {
                    j++;
                    continue;
                }
                // 来到这里，说明木桶的短板是 i 成立，可以计算水量
                for (int k = i + 1; k < j; k++) {
                    water += (height[i] - height[k]);
                }
                queue = new LinkedList<>();
                break;
            }
            // 弹出不在范围中的节点
            while (!queue.isEmpty() && queue.peekFirst() <= i) {
                queue.pollFirst();
            }
            // 来到这里，说明木桶的短板不是 i，而是位于 i 的右侧
            if (!queue.isEmpty()) {
                // 从单调队列中，找到 i 到 j 范围第二大的值，作为木桶的短板
                j = queue.peekFirst();
                for (int k = i + 1; k < j; k++) {
                    water += (height[j] - height[k]);
                }
                // 后续 i 不会再作为短板了，因此不用再重建队列
                createQueue = false;
            }
            i = j;
        }
        return water;
    }

    /**
     * 使用双指针 + 单调队列
     *
     * 执行用时：32 ms, 在所有 Java 提交中击败了10.14% 的用户
     * 内存消耗：39.3 MB, 在所有 Java 提交中击败了5.13% 的用户
     */
    public int trap0(int[] height) {
        int n = height.length;
        if (n <= 2) {
            return 0;
        }
        int water = 0;
        Deque<Integer> queue = new LinkedList<>();
        // 定义两个边界 i 和 j
        int i = 0;
        while (i < n) {
            int j = i + 1;
            while (height[i] > 0 && j < n) {
                // 维护队列：小值直接加入，大值则需要先弹出小值再加入
                if (queue.isEmpty()) {
                    queue.addLast(i);
                }
                while (!queue.isEmpty() && height[j] > height[queue.peekLast()]) {
                    queue.pollLast();
                }
                queue.addLast(j);
                // 假设木桶的短板是 i，需要划定 i 到 j 的范围，再计算水量
                if (height[j] < height[i]) {
                    j++;
                    continue;
                }
                for (int k = i + 1; k < j; k++) {
                    water += (height[i] - height[k]);
                }
                queue = new LinkedList<>();
                break;
            }
            // 来到这里，说明木桶的短板不是 i，而是位于 i 的右侧
            if (!queue.isEmpty()) {
                // 划定 i 到 j 的范围，再计算水量
                while (!queue.isEmpty() && queue.peekFirst() <= i) {
                    queue.pollFirst(); // 弹出不在范围中的节点
                }
                j = queue.peekFirst();
                for (int k = i + 1; k < j; k++) {
                    water += (height[j] - height[k]);
                }
                queue = new LinkedList<>();
            }
            i = j;
        }
        return water;
    }

    /**
     * 输入：height = [0,1,0,2,1,0,1,3,2,1,2,1]
     * 输出：6
     *
     * 输入：height = [4,2,0,3,2,5]
     * 输出：9
     *
     * [4,2,3]
     * 1
     *
     * [9,6,8,8,5,6,3]
     * 3
     */
    @Test
    public void test() {
//        int[] height = new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        int[] height = new int[]{4, 2, 0, 3, 2, 5};
//        int[] height = new int[]{4, 2, 3};
//        int[] height = new int[]{9, 6, 8, 8, 5, 6, 3};
        int result = trap(height);
        System.out.println("result = " + result);
    }
}

package com.sumkor.array._0239_window;

import org.junit.Test;

import java.util.*;

/**
 * @author Sumkor
 * @since 2021/8/10
 */
public class Solution02 {

    /**
     * 滑动窗口 + 大顶堆（优化版）TODO
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        int capacity = n - k + 1;
        int[] result = new int[capacity];
        // 大顶堆
        PriorityQueue<Integer> queue = new PriorityQueue<>(n, (a, b) -> b - a);
        // 双指针
        int i = 0;
        int j = i + k - 1;
        while (i < capacity) {
            // 初始化窗口
            if (i == 0) {
                for (int index = 0; index < k; index++) {
                    queue.add(nums[index]);
                }
            }
            // 滑动窗口
            else {
                queue.add(nums[j]);
            }
            int top = queue.peek();
            // 当堆顶的值被移除掉了，才需要重新选堆顶
            if (i != 0 && nums[i - 1] >= top) {
                queue.poll();
                top = queue.peek();
            }
            result[i] = top;
            i++;
            j++;
        }
        return result;
    }

    /**
     * 滑动窗口 + 大顶堆
     *
     * 对于去重的解法，除了用哈希表，还可以用排序。
     * 如果每次都对新的子序列进行排序，很耗时！
     * 这里并不需要全量排序，只需要找出最大值。PriorityQueue 是小顶堆，这里需要改为大顶堆。
     *
     * 但是，这里随着窗口的移动，不停地出队入队，比较耗时！
     *
     * 执行结果：超出时间限制
     */
    public int[] maxSlidingWindow1(int[] nums, int k) {
        int n = nums.length;
        int capacity = n - k + 1;
        int[] result = new int[capacity];
        // 大顶堆
        PriorityQueue<Integer> queue = new PriorityQueue<>(n, (a, b) -> b - a);
        // 双指针
        int i = 0;
        int j = i + k - 1;
        while (i < capacity) {
            // 初始化窗口
            if (i == 0) {
                for (int index = 0; index < k; index++) {
                    queue.add(nums[index]);
                }
            }
            // 滑动窗口
            else {
                if (nums[j] != nums[i - 1]) {
                    queue.add(nums[j]);
                    queue.remove(nums[i - 1]);
                }
            }
            result[i] = queue.peek();
            i++;
            j++;
        }
        return result;
    }

    /**
     * PriorityQueue 默认是小顶堆，改为大顶堆
     */
    @Test
    public void priority() {
        int[] nums = new int[]{1, 3, -1};
        int n = nums.length;
        PriorityQueue<Integer> queue = new PriorityQueue<>(n, (a, b) -> b - a);
        /**
         * a > b return -1 // 返回负数，第一个参数放前面
         * a = b return 0
         * a < b return 1
         */
        for (int i = 0; i < nums.length; i++) {
            queue.add(nums[i]);
        }
        Integer peek = queue.peek();
        System.out.println("peek = " + peek);
    }
}

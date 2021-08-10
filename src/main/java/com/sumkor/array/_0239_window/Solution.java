package com.sumkor.array._0239_window;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Sumkor
 * @since 2021/8/9
 */
public class Solution {

    /**
     * 滑动窗口 + 暴力法 + map 缓存
     *
     * 执行用时：742 ms, 在所有 Java 提交中击败了5.06% 的用户
     * 内存消耗：57.4 MB, 在所有 Java 提交中击败了23.34% 的用户
     *
     * 在本地调试，加入缓存之后，执行速度更慢了，但是 leetcode 反而没有提示超时
     * {@link TestCase#testLong()} 耗时 20 ms
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        int capacity = n - k + 1;
        int[] result = new int[capacity];
        // 缓存：key=数字，value=下标
        Map<Integer, Integer> map = new HashMap<>();
        // 当 i = 0 时，构造窗口
        int max = nums[0];
        for (int j = 1; j < k; j++) {
            max = Math.max(max, nums[j]);
            // 更新缓存
            map.put(nums[j], j);
        }
        result[0] = max;
        // 滑动窗口
        for (int i = 1; i < capacity; i++) {
            // 对比移出和加入窗口的两个数字
            int remove = nums[i - 1];
            int add = nums[i + k - 1];
            // 更新缓存
            map.put(add, i + k - 1);
            // 移除小的 or 加入大的
            if (remove < max || add >= max) {
                max = Math.max(max, add);
            }
            // 移除大的 and 加入小的
            else {
                // 查缓存，看中间是否存在与 max 相等的数字（加入缓存的目的！）
                Integer index = map.get(max);
                // 缓存不存在，需要在中间重新找最大值
                if (index == null || index < i) {
                    max = nums[i];
                    for (int j = i + 1; j < i + k; j++) {
                        max = Math.max(max, nums[j]);
                    }
                }
            }
            result[i] = max;
        }
        return result;
    }

    /**
     * 滑动窗口 + 暴力法
     *
     * 执行结果：超出时间限制
     *
     * {@link TestCase#testLong()} 耗时 3 ms
     */
    public int[] maxSlidingWindow1(int[] nums, int k) {
        int n = nums.length;
        int capacity = n - k + 1;
        int[] result = new int[capacity];
        // 当 i = 0 时，构造窗口
        int max = nums[0];
        for (int j = 1; j < k; j++) {
            max = Math.max(max, nums[j]);
        }
        result[0] = max;
        // 滑动窗口
        for (int i = 1; i < capacity; i++) {
            // 对比移出和加入窗口的两个数字
            int remove = nums[i - 1];
            int add = nums[i + k - 1];
            // 移除小的 or 加入大的
            if (remove < max || add >= max) {
                max = Math.max(max, add);
            }
            // 移除大的 and 加入小的，需要在中间重新找最大值
            else {
                max = nums[i];
                for (int j = i + 1; j < i + k; j++) {
                    max = Math.max(max, nums[j]);
                }
            }
            result[i] = max;
        }
        return result;
    }

    /**
     * 暴力法
     *
     * 1 <= k <= nums.length
     *
     * 执行结果：超出时间限制
     */
    public int[] maxSlidingWindow0(int[] nums, int k) {
        int n = nums.length;
        int capacity = n - k + 1;
        int[] result = new int[capacity];
        for (int i = 0; i < capacity; i++) {
            // 从 i 位置开始的 k 个值中，找到最大值
            int max = nums[i];
            for (int j = i + 1; j < i + k; j++) { // 每次 j 都是从 i+1 开始，耗时！
                max = Math.max(max, nums[j]);
            }
            result[i] = max;
        }
        return result;
    }


}

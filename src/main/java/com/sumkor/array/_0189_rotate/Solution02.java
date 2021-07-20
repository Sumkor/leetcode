package com.sumkor.array._0189_rotate;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author Sumkor
 * @since 2021/7/20
 */
public class Solution02 {

    /**
     * 环状替换
     * https://leetcode-cn.com/problems/rotate-array/solution/189-xuan-zhuan-shu-zu-san-chong-si-lu-xu-1k1i/
     *
     * 在这种情况下，我们只需要定义一个变量暂时存储数据即可。
     *
     * 大致可以分为两种情况：
     *      1. 可以根据环装替换一次性到位
     *      2. 需要多次，也就是存在多个环
     * 其实是这样的，对于一个长度为 n 的数组，整体移动 k 个位置：
     *      当 n 和 k 的最大公约数 等于 1 的时候：1 次遍历就可以完成交换；比如 n=5, k=3
     *      当 n 和 k 的最大公约数 不等于 1 的时候：1 次遍历是无法完成的所有元素归位，需要 m (最大公约数) 次
     * 但是其中存在一个通性，那就是交换的次数就是总元素个数
     *
     * 时间复杂度：O(n)，其中 n 为数组的长度。每个元素只会被遍历一次。
     * 空间复杂度：O(1)。我们只需常数空间存放若干变量。
     */
    public void rotate(int[] nums, int k) {
        int len = nums.length;
        k = k % len;
        // 记录交换位置的次数，n 个数一共需要换 n 次
        int count = 0;
        for (int i = 0; count < len; i++) {
            // 从0开始换
            int cur = i;
            // 目前要换的数
            int pre = nums[cur];
            do {
                // 下一个的位置
                int next = (cur + k) % len;
                // 暂存数据
                int temp = nums[next];
                // 放入下一个位置
                nums[next] = pre;
                // 更新后移
                pre = temp;
                cur = next;
                // 记录交换次数
                count++;
            } while (i != cur);
            // 回到起始位置时，循环结束，起始位置准备后移
        }
    }

    @Test
    public void test() {
        int[] nums = {-1, -100, 3, 99}; int k = 2;
//        int[] nums = {1, 2, 3, 4, 5, 6, 7}; int k = 3;
        rotate(nums, k);
        Arrays.stream(nums).forEach(System.out::print);
    }
}

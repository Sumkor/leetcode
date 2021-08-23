package com.sumkor.array._0042_rainwater;

import org.junit.Test;

/**
 * @author Sumkor
 * @since 2021/8/23
 */
public class Solution02 {

    /**
     * 动态规划（官方题解）
     * https://leetcode-cn.com/problems/trapping-rain-water/solution/jie-yu-shui-by-leetcode/
     * https://www.bilibili.com/video/BV1fi4y1t7BP
     *
     * 在暴力方法中，我们仅仅为了找到最大值每次都要向左和向右扫描一次。但是我们可以提前存储这个值。因此，可以通过动态编程解决。
     *
     * 算法：
     * 找到数组中从下标 i 到最左端最高的条形块高度 left_max。
     * 找到数组中从下标 i 到最右端最高的条形块高度 right_max。
     * 扫描数组 height 并更新答案：累加 Math.min(left_max[i], right_max[i]) - height[i] 到 ans 上
     *
     * 时间复杂度：O(n)。
     *     存储最大高度数组，需要两次遍历，每次 O(n) 。
     *     最终使用存储的数据更新 ans，O(n)。
     *
     * 空间复杂度：O(n) 额外空间。
     *     使用了额外的 O(n) 空间用来放置 left_max 和 right_max 数组。
     *
     * 执行用时：1 ms, 在所有 Java 提交中击败了88.83% 的用户
     * 内存消耗：38.2 MB, 在所有 Java 提交中击败了24.43% 的用户
     */
    public int trap(int[] height) {
        if (height == null || height.length == 0)
            return 0;
        int ans = 0;
        int size = height.length;
        // 提前存储每个位置上：所有左边柱子高度的最大值、所有右边柱子高度的最大值
        int[] left_max = new int[size];
        int[] right_max = new int[size];
        left_max[0] = height[0];
        for (int i = 1; i < size; i++) {
            left_max[i] = Math.max(height[i], left_max[i - 1]);
        }
        right_max[size - 1] = height[size - 1];
        for (int i = size - 2; i >= 0; i--) {
            right_max[i] = Math.max(height[i], right_max[i + 1]);
        }
        // 计算每个位置的存水量
        for (int i = 1; i < size - 1; i++) {
            ans += Math.min(left_max[i], right_max[i]) - height[i];
        }
        return ans;
    }

    /**
     * 暴力
     *
     * 对于数组中的每个元素，我们找出下雨后水能达到的最高位置，等于两边最大高度的较小值减去当前高度的值。
     *
     * 时间复杂度：O(n^2)。数组中的每个元素都需要向左向右扫描。
     * 空间复杂度 O(1) 的额外空间。
     */
    public int trap0(int[] height) {
        int ans = 0;
        int size = height.length;
        for (int i = 1; i < size - 1; i++) {
            int max_left = 0, max_right = 0;
            for (int j = i; j >= 0; j--) { // Search the left part for max bar size
                max_left = Math.max(max_left, height[j]);
            }
            for (int j = i; j < size; j++) { // Search the right part for max bar size
                max_right = Math.max(max_right, height[j]);
            }
            ans += Math.min(max_left, max_right) - height[i];
        }
        return ans;
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

package com.sumkor.dp._0198_robhouse;

import org.junit.Test;

/**
 * @author Sumkor
 * @since 2021/8/24
 */
public class Solution {

    /**
     * 动态规划
     *
     * dp[i] 表示以 i 位置为终点的最大值
     *
     * 状态转移方程：
     * dp[i] = max{dp[i - 2], dp[i - 3]} + nums[i]
     *
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗：35.8 MB, 在所有 Java 提交中击败了51.82% 的用户
     */
    public int rob(int[] nums) {
        int n = nums.length;
        // 特殊情况
        if (n == 1) {
            return nums[0];
        }
        if (n == 2) {
            return Math.max(nums[0], nums[1]);
        }
        int[] dp = new int[n];
        // 初始值
        dp[0] = nums[0];
        dp[1] = nums[1];
        dp[2] = nums[0] + nums[2];
        // 动态规划
        for (int i = 3; i < n; i++) {
            dp[i] = Math.max(dp[i - 2], dp[i - 3]) + nums[i];
        }
        return Math.max(dp[n - 1], dp[n - 2]);
    }

    /**
     * 动态规划（官方题解）
     * https://leetcode-cn.com/problems/house-robber/solution/da-jia-jie-she-by-leetcode-solution/
     *
     * dp[i] 表示前 i 间房屋能偷窃到的最高总金额
     * dp[i] = max(dp[i−2]+nums[i], dp[i−1])
     */
    public int rob0(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int length = nums.length;
        if (length == 1) {
            return nums[0];
        }
        int[] dp = new int[length];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < length; i++) {
            dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1]);
        }
        return dp[length - 1];
    }

    /**
     * 动态规划（官方题解）
     * https://leetcode-cn.com/problems/house-robber/solution/da-jia-jie-she-by-leetcode-solution/
     *
     * 考虑到每间房屋的最高总金额只和该房屋的前两间房屋的最高总金额相关，因此可以使用滚动数组，在每个时刻只需要存储前两间房屋的最高总金额。
     *
     * 时间复杂度：O(n)，其中 n 是数组长度。只需要对数组遍历一次。
     * 空间复杂度：O(1)。使用滚动数组，可以只存储前两间房屋的最高总金额，而不需要存储整个数组的结果，因此空间复杂度是 O(1)。
     */
    public int rob1(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int length = nums.length;
        if (length == 1) {
            return nums[0];
        }
        int first = nums[0], second = Math.max(nums[0], nums[1]);
        for (int i = 2; i < length; i++) {
            int temp = second;
            second = Math.max(first + nums[i], second);
            first = temp;
        }
        return second;
    }

    /**
     * 输入：[1,2,3,1]
     * 输出：4
     *
     * 输入：[2,7,9,3,1]
     * 输出：12
     *
     * 输入：[2,0,9,10,1,2]
     * 输出：14
     */
    @Test
    public void test() {
//        int[] nums = new int[]{1, 2, 3, 1};
//        int[] nums = new int[]{2, 7, 9, 3, 1};
//        int[] nums = new int[]{2, 0, 9, 10, 1, 2};
        int[] nums = new int[]{1, 2, 3};
        int result = rob(nums);
        System.out.println("result = " + result);
    }
}

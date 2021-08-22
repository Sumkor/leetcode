package com.sumkor.dp._0053_maxsubarray;

import org.junit.Test;

/**
 * @author Sumkor
 * @since 2021/8/22
 */
public class Solution {

    /**
     * dp[i] 表示以 nums[i] 结尾的连续子数组的最大和
     *
     * 状态转移方程：dp[i] = max{dp[i], dp[i-1] + nums[i]}
     *
     * 执行用时：1 ms, 在所有 Java 提交中击败了92.52% 的用户
     * 内存消耗：38.3 MB, 在所有 Java 提交中击败了72.41% 的用户
     *
     * 时间复杂度：O(n)，其中 n 为 nums 数组的长度。我们只需要遍历一遍数组即可求得答案。
     * 空间复杂度：O(1)。我们只需要常数空间存放若干变量。
     */
    public int maxSubArray(int[] nums) {
        int max = nums[0];
        int[] dp = nums;
        for (int i = 1; i < nums.length; i++) {
            dp[i] = Math.max(dp[i], dp[i - 1] + nums[i]);
            max = Math.max(max, dp[i]);
        }
        return max;
    }

    /**
     * 输入：nums = [-2,1,-3,4,-1,2,1,-5,4]
     * 输出：6
     * 解释：连续子数组 [4,-1,2,1] 的和最大，为 6
     */
    @Test
    public void test() {
//        int[] nums = new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4};
        int[] nums = new int[]{-1, -2, 5, 1};
        int result = maxSubArray(nums);
        System.out.println("result = " + result);
    }
}

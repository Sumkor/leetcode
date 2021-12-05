package com.sumkor.math._0089_graycode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Sumkor
 * @since 2021/12/5
 */
public class Solution03 {

    /**
     * 回溯算法
     * https://leetcode-cn.com/problems/gray-code/solution/hui-su-javadai-ma-zhu-shi-by-xiao-xiao-l-sz0r/
     *
     * 执行用时：24 ms, 在所有 Java 提交中击败了12.36% 的用户
     * 内存消耗：45.4 MB, 在所有 Java 提交中击败了76.30% 的用户
     */
    public List<Integer> grayCode(int n) {
        List<Integer> ans = new ArrayList<>();
        backtrace(n, new StringBuffer(), new int[]{0, 1}, ans);
        return ans;
    }

    /**
     * n = 3 决策树：
     *
     * 第一位             0 1
     *               /         \
     * 第二位       0 1        1 0
     *           /    |      |    \
     * 第三位  0 1    1 0    0 1  1 0
     *
     * 结果 000 001 011 010 110 111 101 100
     *
     * 可以发现每一位的选择时，会出现 01 和 10 的对称情况
     */
    public void backtrace(int n, StringBuffer sb, int[] nums, List<Integer> ans) {
        //判断条件，是否返回
        if (sb.length() == n) {
            // 二进制转换为十进制
            ans.add(Integer.valueOf(sb.toString(), 2));
            return;
        }
        // 回溯第一个状态
        sb.append(nums[0]);
        System.out.println("递归0前 》 " + sb);
        // 注意数组
        backtrace(n, sb, new int[]{0, 1}, ans);
        sb.deleteCharAt(sb.length() - 1);
        System.out.println("递归0后 》 " + sb);

        // 回溯第二个状态
        sb.append(nums[1]);
        System.out.println("递归1前 》 " + sb);
        // 注意数组
        backtrace(n, sb, new int[]{1, 0}, ans);
        sb.deleteCharAt(sb.length() - 1);
        System.out.println("递归1后 》 " + sb);
    }

    /**
     * 输入：n = 2
     * 输出：[0,1,3,2]
     * [0,1,3,2] 的二进制表示是 [00,01,11,10]
     *
     * 输入：n = 1
     * 输出：[0,1]
     *
     * n = 3
     * [000, 100, 110, 010, 011, 111, 101, 001]
     */
    @Test
    public void test() {
        int n = 3;
        List<Integer> ans = grayCode(n);
        System.out.println("ans = " + ans);
    }
}

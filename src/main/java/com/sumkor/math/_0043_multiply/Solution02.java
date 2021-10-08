package com.sumkor.math._0043_multiply;

/**
 * @author Sumkor
 * @since 2021/10/8
 */
public class Solution02 {

    /**
     * 官方题解
     * https://leetcode-cn.com/problems/multiply-strings/solution/zi-fu-chuan-xiang-cheng-by-leetcode-solution/
     *
     * 多项式展开，计算结果
     * 123 * 456 = (100 + 20 + 3) * (400 + 50 + 6)
     *
     * 先计算乘法，再处理进位
     *
     * 执行用时：2 ms, 在所有 Java 提交中击败了96.08% 的用户
     * 内存消耗：38.2 MB, 在所有 Java 提交中击败了94.55% 的用户
     */
    public String multiply(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")) {
            return "0";
        }
        int m = num1.length(), n = num2.length();
        int[] ansArr = new int[m + n];
        // 计算每一项的乘积
        for (int i = m - 1; i >= 0; i--) {
            int x = num1.charAt(i) - '0';
            for (int j = n - 1; j >= 0; j--) {
                int y = num2.charAt(j) - '0';
                ansArr[i + j + 1] += x * y;
            }
        }
        // 统一处理进位
        for (int i = m + n - 1; i > 0; i--) {
            ansArr[i - 1] += ansArr[i] / 10;
            ansArr[i] %= 10;
        }
        // 数组转换字符串
        int index = ansArr[0] == 0 ? 1 : 0;
        StringBuilder ans = new StringBuilder();
        while (index < m + n) {
            ans.append(ansArr[index]);
            index++;
        }
        return ans.toString();
    }

}

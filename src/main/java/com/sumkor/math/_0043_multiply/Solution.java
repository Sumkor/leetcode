package com.sumkor.math._0043_multiply;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.Arrays;

/**
 * @author Sumkor
 * @since 2021/10/8
 */
public class Solution {

    /**
     * 多项式展开，计算结果
     * 123 * 456 = (100 + 20 + 3) * (400 + 50 + 6)
     *
     * 利用 int 数组记录结果，避免溢出
     * 计算每一项的时候，只对该位上的数值进行相乘，再在 int 数组上找到该乘积所在的位置
     *
     * 执行用时：5 ms, 在所有 Java 提交中击败了49.80% 的用户
     * 内存消耗：38.1 MB, 在所有 Java 提交中击败了96.43% 的用户
     */
    public String multiply(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")) {
            return "0";
        }
        char[] chars1 = num1.toCharArray();
        char[] chars2 = num2.toCharArray();
        int m = num1.length(), n = num2.length();
        // 多项式展开，计算结果
        int[] ansArr = new int[m + n];
        for (int i = m - 1; i >= 0; i--) {
            int a = chars1[i] - '0';
            for (int j = n - 1; j >= 0; j--) {
                int b = chars2[j] - '0';
                int result = a * b;
                // 对应结果集的起始位
                // int index = m + n - ((m - i) + (n - j) -1);
                int index = i + j + 1;
                if (result >= 10) {
                    add(ansArr, index - 1, result / 10);
                }
                add(ansArr, index, result % 10);
            }
        }
        // 数组转换字符串
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < ansArr.length; i++) {
            if (i == 0 && ansArr[i] == 0) {
                continue;
            }
            sb.append(ansArr[i]);
        }
        return sb.toString();
    }

    /**
     * 数组 ansArr 的 index 位加上数字 num
     */
    private void add(int[] ansArr, int index, int num) {
        if (num == 0) {
            return;
        }
        int addOne = 0;
        while (index >= 0) {
            int result = ansArr[index] + num + addOne;
            if (result >= 10) {
                ansArr[index] = result % 10;
                addOne = 1;
                num = 0;
                index--;
            } else {
                ansArr[index] = result;
                break;
            }
        }
    }

    /**
     * 多项式展开，计算结果
     * 123 * 456 = (100 + 20 + 3) * (400 + 50 + 6)
     *
     * 计算过程中，result 会溢出！
     */
    public String multiply0(String num1, String num2) {
        char[] chars1 = num1.toCharArray();
        char[] chars2 = num2.toCharArray();
        int length1 = chars1.length;
        int length2 = chars2.length;
        long result = 0;
        for (int i = 0; i < length1; i++) {
            long a = (chars1[i] - '0') * (long) Math.pow(10, length1 - 1 - i);
            for (int j = 0; j < length2; j++) {
                long b = (chars2[j] - '0') * (long) Math.pow(10, length2 - 1 - j);
                result += a * b;
            }
        }
        return String.valueOf(result);
    }

    /**
     * 输入: num1 = "2", num2 = "3"
     * 输出: "6"
     *
     * 输入: num1 = "123", num2 = "456"
     * 输出: "56088"
     *
     * "123456789"
     * "987654321"
     *
     * "498828660196"
     * "840477629533"
     */
    @Test
    public void test() {
//        String num1 = "498828660196";
//        String num2 = "840477629533";
        String num1 = "123";
        String num2 = "456";
        String result = multiply(num1, num2);
        System.out.println(result);
        System.out.println(new BigDecimal(num1).multiply(new BigDecimal(num2)));
    }

    @Test
    public void add() {
        int[] ansArr = new int[]{2, 9}; // 表示数据 29
        add(ansArr, 0, 7);
        Arrays.stream(ansArr).forEach(System.out::print);
    }
}

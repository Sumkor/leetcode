package com.sumkor.math._0012_roman;

import org.junit.Test;

/**
 * @author Sumkor
 * @since 2021/10/7
 */
public class Solution {

    /**
     * 模拟
     *
     * 执行用时：4 ms, 在所有 Java 提交中击败了99.97% 的用户
     * 内存消耗：37.8 MB, 在所有 Java 提交中击败了69.26% 的用户
     */
    public String intToRoman(int num) {
        StringBuilder ans = new StringBuilder();
        while (num >= 1000) {
            num -= 1000;
            ans.append("M");
        }
        while (num >= 900) {
            num -= 900;
            ans.append("CM");
        }
        while (num >= 500) {
            num -= 500;
            ans.append("D");
        }
        while (num >= 400) {
            num -= 400;
            ans.append("CD");
        }
        while (num >= 100) {
            num -= 100;
            ans.append("C");
        }
        while (num >= 90) {
            num -= 90;
            ans.append("XC");
        }
        while (num >= 50) {
            num -= 50;
            ans.append("L");
        }
        while (num >= 40) {
            num -= 40;
            ans.append("XL");
        }
        while (num >= 10) {
            num -= 10;
            ans.append("X");
        }
        while (num >= 9) {
            num -= 9;
            ans.append("IX");
        }
        while (num >= 5) {
            num -= 5;
            ans.append("V");
        }
        while (num >= 4) {
            num -= 4;
            ans.append("IV");
        }
        while (num >= 1) {
            num -= 1;
            ans.append("I");
        }
        return ans.toString();
    }

    /**
     * 模拟（官方题解，思路一致，写法简洁）
     * https://leetcode-cn.com/problems/integer-to-roman/solution/zheng-shu-zhuan-luo-ma-shu-zi-by-leetcod-75rs/
     *
     * 时间复杂度：O(1)。由于 value symbols 数组长度是固定的，且这 13 字符中的每个字符的出现次数均不会超过 3，因此循环次数有一个确定的上限。对于本题给出的数据范围，循环次数不会超过 15 次。
     * 空间复杂度：O(1)。
     */
    public String intToRoman0(int num) {
        StringBuilder roman = new StringBuilder();
        for (int i = 0; i < values.length; ++i) {
            int value = values[i];
            String symbol = symbols[i];
            while (num >= value) {
                num -= value;
                roman.append(symbol);
            }
            if (num == 0) {
                break;
            }
        }
        return roman.toString();
    }

    int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
    String[] symbols = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

    /**
     * 输入: num = 3
     * 输出: "III"
     *
     * 输入: num = 4
     * 输出: "IV"
     *
     * 输入: num = 9
     * 输出: "IX"
     *
     * 输入: num = 58
     * 输出: "LVIII"
     * 解释: L = 50, V = 5, III = 3.
     *
     * 输入: num = 1994
     * 输出: "MCMXCIV"
     * 解释: M = 1000, CM = 900, XC = 90, IV = 4.
     */
    @Test
    public void test() {
//        int num = 9;
//        int num = 58;
        int num = 1994;
        String roman = intToRoman(num);
        System.out.println("roman = " + roman);
    }
}

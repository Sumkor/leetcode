package com.sumkor.math._0013_roman;

import org.junit.Test;

import java.util.Map;

/**
 * @author Sumkor
 * @since 2021/10/7
 */
public class Solution {

    /**
     * 查哈希表，每次读一个或两个字符
     *
     * 执行用时：16 ms, 在所有 Java 提交中击败了5.84% 的用户
     * 内存消耗：38.9 MB, 在所有 Java 提交中击败了35.40% 的用户
     */
    public int romanToInt0(String s) {
        int ans = 0;
        int i = 0;
        char[] chars = s.toCharArray();
        int length = chars.length;
        while (i < length) {
            Integer num = null;
            if (i < length - 1) {
                num = map.get(String.valueOf(chars[i]) + chars[i + 1]);
                if (num == null) {
                    num = map.get(String.valueOf(chars[i]));
                    i++;
                } else {
                    i += 2;
                }
            } else {
                num = map.get(String.valueOf(chars[i]));
                i++;
            }
            // 只需要累加
            ans += num;
        }
        return ans;
    }

    private Map<String, Integer> map = Map.ofEntries(
            Map.entry("I", 1),
            Map.entry("IV", 4),
            Map.entry("V", 5),
            Map.entry("IX", 9),
            Map.entry("X", 10),
            Map.entry("XL", 40),
            Map.entry("L", 50),
            Map.entry("XC", 90),
            Map.entry("C", 100),
            Map.entry("CD", 400),
            Map.entry("D", 500),
            Map.entry("CM", 900),
            Map.entry("M", 1000)
    );

    /**
     * 查哈希表，每次读一个字符
     *
     * 执行用时：7 ms, 在所有 Java 提交中击败了35.61% 的用户
     * 内存消耗：39 MB, 在所有 Java 提交中击败了21.32% 的用户
     */
    public int romanToInt1(String s) {
        int ans = 0;
        int i = 0;
        char[] chars = s.toCharArray();
        int length = chars.length;
        int lastNum = 0;
        while (i < length) {
            Integer num = charMap.get(chars[i]);
            // 如果当前字符大于上一个字符，说明是特例
            if (num > lastNum) {
                ans = ans - lastNum + (num - lastNum);
            } else {
                ans += num;
            }
            lastNum = num;
            i++;
        }
        return ans;
    }

    private Map<Character, Integer> charMap = Map.ofEntries(
            Map.entry('I', 1),
            Map.entry('V', 5),
            Map.entry('X', 10),
            Map.entry('L', 50),
            Map.entry('C', 100),
            Map.entry('D', 500),
            Map.entry('M', 1000)
    );

    /**
     * 查哈希表，每次读一个字符（官方题解，思路类似，计算更为简单）
     * https://leetcode-cn.com/problems/roman-to-integer/solution/luo-ma-shu-zi-zhuan-zheng-shu-by-leetcod-w55p/
     *
     * 时间复杂度：O(n)，其中 n 是字符串 s 的长度。
     * 空间复杂度：O(1)。
     *
     * 执行用时：6 ms, 在所有 Java 提交中击败了63.80% 的用户
     * 内存消耗：39 MB, 在所有 Java 提交中击败了17.83% 的用户
     */
    public int romanToInt(String s) {
        int ans = 0;
        int n = s.length();
        for (int i = 0; i < n; ++i) {
            int value = charMap.get(s.charAt(i));
            // 如果当前字符小于下一个字符，说明是特例
            if (i < n - 1 && value < charMap.get(s.charAt(i + 1))) {
                ans -= value;
            } else {
                ans += value;
            }
        }
        return ans;
    }


    /**
     * 输入: "III"
     * 输出: 3
     *
     * 输入: "IV"
     * 输出: 4
     *
     * 输入: "IX"
     * 输出: 9
     *
     * 输入: "LVIII"
     * 输出: 58
     * 解释: L = 50, V= 5, III = 3.
     *
     * 输入: "MCMXCIV"
     * 输出: 1994
     * 解释: M = 1000, CM = 900, XC = 90, IV = 4.
     */
    @Test
    public void test() {
//        String s = "III";
//        String s = "IV";
//        String s = "LVIII";
        String s = "MCMXCIV";
        int ans = romanToInt1(s);
        System.out.println("ans = " + ans);
    }
}

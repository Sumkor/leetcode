package com.sumkor.string._0006_zconvert;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author Sumkor
 * @since 2021/7/26
 */
public class Solution02 {

    /**
     * 周期法
     * https://www.geekxh.com/1.0.%E6%95%B0%E7%BB%84%E7%B3%BB%E5%88%97/009.html#_02%E3%80%81%E9%A2%98%E7%9B%AE%E5%88%86%E6%9E%90
     *
     * 观察可知，遍历打印过程中，每 2n-2 即为一个周期，n 为入参 numRows。
     *
     * 时间复杂度：O(n)，其中 n==len(s)
     * 空间复杂度：O(n)
     *
     * 执行用时：13 ms, 在所有 Java 提交中击败了27.91% 的用户
     * 内存消耗：39 MB, 在所有 Java 提交中击败了46.22% 的用户
     */
    public String convert(String s, int numRows) {
        if (numRows == 1) return s;
        // 存放结果的一维数组
        String[] arr = new String[numRows];
        Arrays.fill(arr, "");
        char[] chars = s.toCharArray();
        int len = chars.length;
        int period = numRows * 2 - 2;
        for (int i = 0; i < len; i++) {
            int mod = i % period;
            // 每一次周期打印都分为两部分
            if (mod < numRows) {
                arr[mod] += chars[i];
            } else {
                arr[period - mod] += String.valueOf(chars[i]);
            }
        }
        // 按照新的顺序输出
        StringBuilder res = new StringBuilder();
        for (String ch : arr) {
            res.append(ch);
        }
        return res.toString();
    }

    /**
     * 周期法 按行访问
     * https://leetcode-cn.com/problems/zigzag-conversion/solution/z-zi-xing-bian-huan-by-leetcode/
     *
     * 首先访问 行 0 中的所有字符，接着访问 行 1，然后 行 2，依此类推...
     *
     * 对于所有整数 k：
     * 行 0 中的字符位于索引 k  (2⋅numRows−2)k 处;
     * 行 numRows−1 中的字符位于索引 k  (2⋅numRows−2)+numRows−1 处;
     * 内部的 行 i 中的字符位于索引 k  (2⋅numRows−2)+i 以及 (k+1)(2⋅numRows−2)−i 处;
     */
    public String convert0(String s, int numRows) {
        if (numRows == 1) return s;
        StringBuilder ret = new StringBuilder();
        int n = s.length();
        int cycleLen = 2 * numRows - 2;
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j + i < n; j += cycleLen) {
                ret.append(s.charAt(j + i)); // 表示在 i 位置之后，直接拼接下个周期 j 位置的字符
                if (i != 0 && i != numRows - 1 && j + cycleLen - i < n) // i 不在首尾时，需要额外拼接字符
                    ret.append(s.charAt(j + cycleLen - i));
            }
        }
        return ret.toString();
    }

    @Test
    public void test() {
        String s = "PAYPALISHIRING";
        int numRows = 4;
        String convert = convert0(s, numRows);
        System.out.println("convert = " + convert);
    }
}

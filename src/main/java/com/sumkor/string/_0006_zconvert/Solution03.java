package com.sumkor.string._0006_zconvert;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author Sumkor
 * @since 2021/7/26
 */
public class Solution03 {

    /**
     * 标志位法：通过使用一个标志位，来使轨迹回移。
     *
     * 执行用时：17 ms, 在所有 Java 提交中击败了21.89% 的用户
     * 内存消耗：39.5 MB, 在所有 Java 提交中击败了10.79% 的用户
     */
    public String convert(String s, int numRows) {
        if (numRows == 1) return s;
        String[] arr = new String[numRows];
        Arrays.fill(arr, "");
        int i = 0, flag = -1;
        for (char c : s.toCharArray()) {
            arr[i] += c;
            // 初始时，flag 为 1，此时 i 递增。在 i 递增即将达到 numRows 之后，开始递减
            if (i == 0 || i == numRows - 1) {
                flag = -flag;
            }
            i += flag;
        }
        StringBuilder res = new StringBuilder();
        for (String ch : arr) {
            res.append(ch);
        }
        return res.toString();
    }

    @Test
    public void test() {
        String s = "PAYPALISHIRING";
        int numRows = 4;
        String convert = convert(s, numRows);
        System.out.println("convert = " + convert);
    }
}

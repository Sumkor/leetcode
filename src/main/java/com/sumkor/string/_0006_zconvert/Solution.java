package com.sumkor.string._0006_zconvert;

import org.junit.Test;

/**
 * @author Sumkor
 * @since 2021/7/25
 */
public class Solution {

    /**
     * 二维数组
     * <p>
     * 输入：s = "PAYPALISHIRING", numRows = 3
     * 输出："PAHNAPLSIIGYIR"
     * 解释：
     * P   A   H   N
     * A P L S I I G
     * Y   I   R
     * <p>
     * 输入：s = "PAYPALISHIRING", numRows = 4
     * 输出："PINALSIGYAHRPI"
     * 解释：
     * P     I    N
     * A   L S  I G
     * Y A   H R
     * P     I
     * 优化：
     * P   I   N
     * A L S I G
     * Y A H R
     * P   I
     * <p>
     * 执行用时：25 ms, 在所有 Java 提交中击败了13.95% 的用户
     * 内存消耗：41 MB, 在所有 Java 提交中击败了5.38% 的用户
     */
    public String convert(String s, int numRows) {
        char[][] result = new char[numRows][s.length()];
        boolean isBlank = false; // 当前列是否留空
        int xIndex = 0; // 横
        int yIndex = 0; // 纵
        for (int i = 0; i < s.length(); i++) {
            char curr = s.charAt(i);
            if (yIndex < numRows && yIndex >= 0) {
                // 当前列不包含首尾空格，此时 yIndex 是递增打印
                if (!isBlank) {
                    result[yIndex][xIndex] = curr;
                    yIndex++;
                }
                // 当前列包含首尾空格，此时 yIndex 是递减打印
                else {
                    // 补空格
                    if (numRows != 1 && (yIndex == 0 || yIndex == numRows - 1)) {
                        result[yIndex][xIndex] = ' ';
                        i--;
                    }
                    // 填字母
                    else {
                        result[yIndex][xIndex] = curr;
                    }
                    yIndex--;
                }
            } else {
                // 换列咯。如果当前列包含空格，则下一次遍历，yIndex 从 0 开始递增；否则从 numRows 开始递减
                if (isBlank) {
                    yIndex = 0;
                } else {
                    yIndex = numRows - 1;
                }
                xIndex++;
                isBlank = !isBlank;
                i--;
            }
        }
        // 打印二维数组
        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[i].length; j++) {
                if (result[i][j] != '\u0000')
                    System.out.print(result[i][j]);
            }
            System.out.println();
        }
        char[] ans = new char[s.length()];
        int index = 0;
        // 按照新的顺序输出
        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[i].length; j++) {
                if (result[i][j] != '\u0000' && result[i][j] != ' ') {
                    ans[index] = result[i][j];
                    index++;
                }
            }
        }
        return new String(ans);
    }

    @Test
    public void test0() {
        char[][] result = new char[4][9];
        result[0][1] = 'A';
        result[0][2] = 'B';
        result[0][3] = 'C';

        result[1][1] = 'C';
        result[1][2] = 'D';
        result[1][3] = 'E';

        System.out.println("result.length = " + result.length);
        for (int i = 0; i < result.length; i++) {
            System.out.println("result = " + result[i]);
        }
    }

    @Test
    public void test() {
        String s = "PAYPALISHIRING";
        int numRows = 4;
//        String s = "ABCDEF";
//        int numRows = 2;
        String convert = convert(s, numRows);
        System.out.println("convert = " + convert);
    }
}

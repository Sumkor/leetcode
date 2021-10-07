package com.sumkor.math._0013_roman;

/**
 * @author Sumkor
 * @since 2021/10/7
 */
public class Solution02 {

    /**
     * 耗时最优解
     *
     * 执行用时：4 ms, 在所有 Java 提交中击败了99.44% 的用户
     * 内存消耗：38.5 MB, 在所有 Java 提交中击败了76.81% 的用户
     */
    public int romanToInt(String s) {
        int sum = 0;
        char pre = '?';
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            switch (c) {
                case 'I':
                    sum += 1;
                    break;
                case 'V':
                    if (pre == 'I') {
                        sum += 3;
                    } else {
                        sum += 5;
                    }
                    break;
                case 'X':
                    if (pre == 'I') {
                        sum += 8;
                    } else {
                        sum += 10;
                    }
                    break;
                case 'L':
                    if (pre == 'X') {
                        sum += 30;
                    } else {
                        sum += 50;
                    }
                    break;
                case 'C':
                    if (pre == 'X') {
                        sum += 80;
                    } else {
                        sum += 100;
                    }
                    break;
                case 'D':
                    if (pre == 'C') {
                        sum += 300;
                    } else {
                        sum += 500;
                    }
                    break;
                case 'M':
                    if (pre == 'C') {
                        sum += 800;
                    } else {
                        sum += 1000;
                    }
                    break;
                default:
                    break;
            }
            pre = c;
        }
        return sum;
    }

}

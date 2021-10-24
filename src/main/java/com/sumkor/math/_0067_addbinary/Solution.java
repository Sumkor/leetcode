package com.sumkor.math._0067_addbinary;

import org.junit.Test;

import java.math.BigInteger;

/**
 * @author Sumkor
 * @since 2021/10/24
 */
public class Solution {

    /**
     * 执行用时：6 ms, 在所有 Java 提交中击败了11.87% 的用户
     * 内存消耗：38.4 MB, 在所有 Java 提交中击败了63.86% 的用户
     */
    public String addBinary1(String a, String b) {
        return new BigInteger(a, 2).add(new BigInteger(b, 2)).toString(2);
    }

    /**
     * 执行用时：6 ms, 在所有 Java 提交中击败了11.87% 的用户
     * 内存消耗：38.3 MB, 在所有 Java 提交中击败了75.71% 的用户
     */
    public String addBinary0(String a, String b) {
        String ans = "";
        int m = a.length();
        int n = b.length();
        char[] A = a.toCharArray();
        char[] B = b.toCharArray();
        int i = m - 1;
        int j = n - 1;
        int addOne = 0;
        while (i >= 0 || j >= 0) {
            int curr = 0;
            if (i < 0) {
                curr = B[j] - '0' + addOne;
            }
            else if (j < 0) {
                curr = A[i] - '0' + addOne;
            }
            else {
                curr = (A[i] - '0') + (B[j] - '0') + addOne;
            }
            if (curr == 2) {
                curr = 0;
                addOne = 1;
            } else if  (curr == 3) {
                curr = 1;
                addOne = 1;
            } else {
                addOne = 0;
            }
            ans = curr + ans;
            i--;
            j--;
        }
        if (addOne == 1) {
            ans = 1 + ans;
        }
        return ans;
    }

    /**
     * 将字符串拼接转换成 StringBuilder#insert 可以大幅减少耗时
     * 使用 String#toCharArray 与 String#charAt 之间没有多大差异
     *
     * 执行用时：2 ms, 在所有 Java 提交中击败了93.20% 的用户
     * 内存消耗：37.7 MB, 在所有 Java 提交中击败了99.59% 的用户
     */
    public String addBinary(String a, String b) {
        StringBuilder ans = new StringBuilder();
        int i = a.length() - 1;
        int j = b.length() - 1;
        int addOne = 0;
        while (i >= 0 || j >= 0) {
            int curr;
            if (i < 0) {
                curr = b.charAt(j) - '0' + addOne;
            }
            else if (j < 0) {
                curr = a.charAt(i) - '0' + addOne;
            }
            else {
                curr = (a.charAt(i) - '0') + (b.charAt(j) - '0') + addOne;
            }
            if (curr == 2) {
                curr = 0;
                addOne = 1;
            } else if  (curr == 3) {
                curr = 1;
                addOne = 1;
            } else {
                addOne = 0;
            }
            ans.insert(0, curr);
            i--;
            j--;
        }
        if (addOne == 1) {
            ans.insert(0, 1);
        }
        return ans.toString();
    }


    /**
     * 输入: a = "11", b = "1"
     * 输出: "100"
     *
     * 输入: a = "1010", b = "1011"
     * 输出: "10101"
     */
    @Test
    public void test() {
//        String a = "11", b = "1";
        String a = "1010", b = "1011";
        String s = addBinary(a, b);
        System.out.println("s = " + s);
    }
}

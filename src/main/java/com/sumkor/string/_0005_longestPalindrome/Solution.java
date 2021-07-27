package com.sumkor.string._0005_longestPalindrome;

import org.junit.Test;

/**
 * @author Sumkor
 * @since 2021/7/25
 */
public class Solution {

    /**
     * 先指定一个区间，再用双指针来对比，判断该区间是否是回文子串
     *
     * 执行用时：318 ms, 在所有 Java 提交中击败了20.24% 的用户
     * 内存消耗：38.4 MB, 在所有 Java 提交中击败了86.59% 的用户
     */
    public String longestPalindrome(String s) {
        int n = s.length();
        String longestPalindrome = "";
        // 先指定一个区间，再用双指针来对比
        for (int i = 0; i < n; i++) {
            for (int j = n - 1; j >= i; j--) {
                // 剩余区间，无法构成更长的回文子串，直接退出
                if (longestPalindrome.length() >= j - i + 1) {
                    break;
                }
                int left = i;
                int right = j;
                boolean isError = false;
                while (left <= right) {
                    if (s.charAt(left) == s.charAt(right)) {
                        right--;
                        left++;
                    } else {
                        isError = true;
                        break;
                    }
                }
                // 如果没有出现错误，说明 i 到 j 区间是一个回文子串
                if (!isError) {
                    if (longestPalindrome.length() < j - i + 1) {
                        longestPalindrome = s.substring(i, j + 1);
                    }
                }
            }
        }
        return longestPalindrome;
    }

    @Test
    public void test() {
//        String s = "ac";
        String s = "babad"; // "bab"
//        String s = "bacabab"; // "bacab"
//        String s = "xaabacxcabaaxcabaax"; // "xaabacxcabaax"
        String longestPalindrome = longestPalindrome(s);
        System.out.println("longestPalindrome = " + longestPalindrome);
    }
}

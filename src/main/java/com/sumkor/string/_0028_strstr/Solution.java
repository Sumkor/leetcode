package com.sumkor.string._0028_strstr;

import org.junit.Test;

/**
 * @author 黄泽滨 【huangzebin@i72.com】
 * @since 2021/7/28
 */
public class Solution {

    /**
     * 先用 substring 再用 equal，对比子串
     *
     * 执行用时：377 ms, 在所有 Java 提交中击败了11.57% 的用户
     * 内存消耗：38.9 MB, 在所有 Java 提交中击败了5.06% 的用户
     */
    public int strStr(String haystack, String needle) {
        if (needle.equals("")) {
            return 0;
        }
        int aLength = haystack.length();
        int bLength = needle.length();
        if (bLength > aLength) {
            return -1;
        }
        for (int i = 0; i < aLength; i++) {
            // 子串比 needle 还短，直接退出
            if (aLength - i < bLength) {
                break;
            }
            // 这一步可以提速约 30 ms
            if (haystack.charAt(i) != needle.charAt(0)) {
                continue;
            }
            // 对比子串是否相同
            if (needle.equals(haystack.substring(i, i + bLength))) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 先用 substring 再用 equal，类似的写法。
     *
     * 执行用时：433 ms, 在所有 Java 提交中击败了8.60% 的用户
     * 内存消耗：38.6 MB, 在所有 Java 提交中击败了10.62% 的用户
     */
    public int strStr2(String haystack, String needle) {
        if (needle.equals("")) {
            return 0;
        }
        int aLength = haystack.length();
        int bLength = needle.length();
        if (bLength > aLength) {
            return -1;
        }
        char[] aArray = haystack.toCharArray();
        char[] bArray = needle.toCharArray();
        for (int i = 0; i < aLength; i++) {
            if (aLength - i < bLength) {
                break;
            }
            if (aArray[i] != bArray[0]) {
                continue;
            }
            // 对比子串是否相等
            boolean isEqual = true;
            int index = 0;
            int n = needle.length();
            while (n-- != 0) {
                if (aArray[i + index] != bArray[index]) {
                    isEqual = false;
                    break;
                }
                index++;
            }
            if (isEqual) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 双指针，对比子串。很耗时
     *
     * 执行用时：1499 ms, 在所有 Java 提交中击败了5.01% 的用户
     * 内存消耗：38.3 MB, 在所有 Java 提交中击败了40.86% 的用户
     */
    public int strStr1(String haystack, String needle) {
        if (needle.equals("")) {
            return 0;
        }
        int aLength = haystack.length();
        int bLength = needle.length();
        if (bLength > aLength) {
            return -1;
        }
        for (int i = 0; i < aLength; i++) {
            if (aLength - i < bLength) {
                break;
            }
            if (haystack.charAt(i) != needle.charAt(0)) {
                continue;
            }
            // 双指针，对比从 i 位置开始的字串是否相同
            int aIndex = i + 1;
            int bIndex = 1;
            boolean isEqual = true;
            while (aIndex < aLength && bIndex < bLength) {
                if (haystack.charAt(aIndex) == needle.charAt(bIndex)) {
                    aIndex++;
                    bIndex++;
                } else {
                    isEqual = false;
                    break;
                }
            }
            if (isEqual) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Java 原生方法
     *
     * 执行用时：443 ms, 在所有 Java 提交中击败了8.32% 的用户
     * 内存消耗：38.5 MB, 在所有 Java 提交中击败了20.97% 的用户
     */
    public int strStr0(String haystack, String needle) {
        return haystack.indexOf(needle);
    }

    @Test
    public void test() {
        String haystack = ""; String needle = "";
//        String haystack = ""; String needle = "a";
//        String haystack = "a"; String needle = "";

        int result = strStr(haystack, needle);
        System.out.println("result = " + result);

        int test = haystack.indexOf(needle);
        System.out.println("test = " + test);
    }
}

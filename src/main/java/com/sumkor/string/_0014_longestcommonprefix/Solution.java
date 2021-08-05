package com.sumkor.string._0014_longestcommonprefix;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author Sumkor
 * @since 2021/7/17
 */
public class Solution {

    /**
     * 横向扫描法
     *
     * 执行用时：1 ms, 在所有 Java 提交中击败了83.00% 的用户
     * 内存消耗：36.4 MB, 在所有 Java 提交中击败了74.83% 的用户
     *
     * 时间复杂度：O(mn)，其中 m 是字符串数组中的字符串的平均长度，n 是字符串的数量。最坏情况下，字符串数组中的每个字符串的每个字符都会被比较一次。
     * 空间复杂度：O(1)。使用的额外空间复杂度为常数。
     */
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        // 先取第一个，作为最终的最长公共前缀
        String longestCommonPrefix = strs[0];
        // 从数组中的第二个元素开始对比
        for (int i = 0; i < strs.length; i++) {
            if (i == 0) {
                continue;
            }
            String currStr = strs[i];
            // 如果数组中包含空串，则直接跳出
            int minLength = Math.min(longestCommonPrefix.length(), currStr.length());
            if (minLength == 0) {
                return "";
            }
            int index = 0;
            // 遍历字符串中的每一个字符，计算当前的最长公共前缀
            for (int j = 0; j < minLength; j++) {
                if (longestCommonPrefix.charAt(j) == currStr.charAt(j)) {
                    index++;
                } else {
                    break;
                }
            }
            // 如果当前字符串不存在公共前缀，则直接跳出
            if (index == 0) {
                return "";
            }
            longestCommonPrefix = longestCommonPrefix.substring(0, index);
        }
        return longestCommonPrefix;
    }

    /**
     * 横向扫描法 未优化版
     *
     * 执行用时：1 ms, 在所有 Java 提交中击败了83.00% 的用户
     * 内存消耗：36.6 MB, 在所有 Java 提交中击败了32.38% 的用户
     */
    public String longestCommonPrefix0(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        char[] longestCommonPrefix = strs[0].toCharArray(); // 最终的最长公共前缀
        char[] currLongestCommonPrefix = new char[longestCommonPrefix.length]; // 当前的最长公共前缀
        for (int i = 0; i < strs.length; i++) {
            if (i == 0) {
                continue;
            }
            String currStr = strs[i];
            int minLength = Math.min(longestCommonPrefix.length, currStr.length());
            if (minLength == 0) {
                return "";
            }
            int index = 0;
            for (int j = 0; j < minLength; j++) {
                if (longestCommonPrefix[j] == currStr.charAt(j)) {
                    currLongestCommonPrefix[index++] = longestCommonPrefix[j];
                } else {
                    break;
                }
            }
            if (index == 0) {
                return "";
            }
            longestCommonPrefix = Arrays.copyOfRange(currLongestCommonPrefix, 0, index);
        }
        return new String(longestCommonPrefix);
    }

    @Test
    public void test() {
//        String[] strs = {"flower", "flow", "flight"};
        String[] strs = {"flower"};
        String result = longestCommonPrefix(strs);
        System.out.println("result = " + result);
    }
}

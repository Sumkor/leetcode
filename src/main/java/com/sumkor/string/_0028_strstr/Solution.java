package com.sumkor.string._0028_strstr;

import org.junit.Test;

/**
 * 个人解答汇总
 * 思路：遍历 haystack 字符串的每一个字符，对比从 i 位置开始的子串是否与 needle 相等。
 *
 *   strStr0 是 Java 原生的方法 indexOf()。性能不佳，表示惊讶。
 *   strStr1 是最初的尝试，利用双指针，对比从 i 位置开始的子串是否相同。性能拉胯，表示不解。
 * ☆ strStr  改变了对比子串的方式，先使用 subString() 截取，再使用 equals() 对比子串，性能接近直接使用 indexOf()。
 *   strStr3 是对 strStr 的实现，将字符串的 subString()、equals() 转换为数组实现，性能与 strStr 一致。
 *   strStr2 是对 strStr1 的改写，参照 strStr3 的写法，将双指针改写为单指针，但是性能较 strStr1 并没有提升。
 *
 * 疑问，strStr2 的性能为什么比 strStr3 差那么多？
 * 网上的说法是 charAt() 涉及对栈帧的操作，不如数组直接取下标高效。
 *
 * @author Sumkor
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
    public int strStr3(String haystack, String needle) {
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
     * 单指针，对比子串。很耗时
     *
     * 执行用时：1533 ms, 在所有 Java 提交中击败了5.01% 的用户
     * 内存消耗：38.4 MB, 在所有 Java 提交中击败了36.09% 的用户
     *
     * 时间复杂度：O(n×m)，其中 n 是字符串 haystack 的长度，m 是字符串 needle 的长度。最坏情况下我们需要将字符串 needle 与字符串 haystack 的所有长度为 m 的子串均匹配一次。
     * 空间复杂度：O(1)。我们只需要常数的空间保存若干变量。
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
        for (int i = 0; i < aLength; i++) {
            if (aLength - i < bLength) {
                break;
            }
            if (haystack.charAt(i) != needle.charAt(0)) {
                continue;
            }
            // 对比子串是否相等
            boolean isEqual = true;
            int bIndex = 0;
            int n = bLength;
            while (n-- != 0) {
                if (haystack.charAt(i + bIndex) != needle.charAt(bIndex)) {
                    isEqual = false;
                    break;
                }
                bIndex++;
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
     *
     * 时间复杂度：O(n×m)，其中 n 是字符串 haystack 的长度，m 是字符串 needle 的长度。最坏情况下我们需要将字符串 needle 与字符串 haystack 的所有长度为 m 的子串均匹配一次。
     * 空间复杂度：O(1)。我们只需要常数的空间保存若干变量。
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
            int aIndex = i;
            int bIndex = 0;
            boolean isEqual = true;
            while (bIndex < bLength && aIndex < aLength) {
                if (haystack.charAt(aIndex) != needle.charAt(bIndex)) {
                    isEqual = false;
                    break;
                } else {
                    aIndex++;
                    bIndex++;
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
//        String haystack = ""; String needle = "";
//        String haystack = ""; String needle = "a";
//        String haystack = "a"; String needle = "";
        String haystack = "hello"; String needle = "ll";
        int result = strStr(haystack, needle);
        System.out.println("result = " + result);

        int test = haystack.indexOf(needle);
        System.out.println("test = " + test);
    }
}

package com.sumkor.string._0028_strstr;

import org.junit.Test;

/**
 * @author Sumkor
 * @since 2021/7/29
 */
public class Solution02 {

    /**
     * Sunday 算法
     * https://www.geekxh.com/1.3.%E5%AD%97%E7%AC%A6%E4%B8%B2%E7%B3%BB%E5%88%97/303.html
     *
     * 基本概念：
     *     串：串是字符串的简称
     *     空串：长度为零的串称为空串
     *     主串：包含子串的串相应地称为主串
     *     子串：串中任意个连续字符组成的子序列称为该串的子串
     *     模式串：子串的定位运算又称为串的模式匹配，是一种求子串第一个字符在主串中序号的运算。被匹配的主串称为目标串，子串称为模式串。
     *
     * 假若我们的目标串为：Here is a little Hao
     * 模式串为：little
     *
     * 一般来讲，字符串匹配算法第一步，都是把目标串和模式串对齐。不管是 KMP，BM，SUNDAY 都是这样。
     * 而对于 SUNDAY 算法，我们从头部开始比较，一旦发现不匹配，直接找到主串中位于模式串后面的第一个字符。（本例中为字符 “s”）
     *     Here is a little Hao
     *     little
     *
     * 这里说明一下，为什么是找模式串后面的第一个字符。在把模式串和目标串对齐后，如果发现不匹配，那肯定需要移动模式串。问题是需要移动多少步。
     * 各字符串匹配算法之间的差别也来自于这个地方，对于 KMP，是建立部分匹配表来计算。BM，是反向比较计算移动量。对于 SUNDAY，就是找到模式串后的第一个字符。
     * 因为，无论模式串移动多少步，模式串后的第一个字符都要参与下一次比较。
     *
     * 找到了模式串后的第一个字符 “s”，接下来该怎么做？我们需要查看模式串中是否包含这个元素，如果不包含那就可以跳过一大片，从该字符的下一个字符开始比较。
     *     Here is a little Hao
     *            little
     *
     * 因为仍然不匹配（空格和 l），我们继续重复上面的过程。找到模式串的下一个元素：t
     * 现在有意思了，我们发现 t 被包含于模式串中，并且 t 出现在模式串倒数第3个。所以我们把模式串向前移动3个单位：
     *     Here is a little Hao
     *               little
     * 匹配完成。
     *
     *
     * 时间复杂度分析： 最坏情况：O(nm) 平均情况：O(n)
     *
     * 执行用时：698 ms, 在所有 Java 提交中击败了7.24% 的用户
     * 内存消耗：38.3 MB, 在所有 Java 提交中击败了41.80% 的用户
     */
    public int strStr(String haystack, String needle) {
        if (haystack == null || needle == null) {
            return 0;
        }
        int aLength = haystack.length();
        int bLength = needle.length();
        if (aLength < bLength) {
            return -1;
        }
        int aIndex = 0; // 目标串匹配索引
        int bIndex = 0; // 模式串匹配索引
        // 成功匹配完终止条件：所有模式串均成功匹配
        while (bIndex < bLength) {
            // 针对目标串匹配完，但模式串未匹配完情况处理 如 mississippi sippia
            if (aIndex > aLength - 1) {
                return -1;
            }
            if (haystack.charAt(aIndex) == needle.charAt(bIndex)) {
                // 匹配则 index 均加1
                aIndex++;
                bIndex++;
            } else {
                // 不匹配，则找到目标串中位于模式串后面的第一个字符
                int nextCharIndex = aIndex - bIndex + bLength;
                // 判断目标串中 nextCharIndex 位置的字符是否存在
                if (nextCharIndex < aLength) {
                    // 判断目标字符在模式串中是否存在，返回最后一个匹配的下标
                    int step = needle.lastIndexOf(haystack.charAt(nextCharIndex));
                    if (step == -1) {
                        // 不存在的话，设置到目标字符的下一个元素
                        aIndex = nextCharIndex + 1;
                    } else {
                        // 存在的话，移动对应的数字
                        aIndex = nextCharIndex - step;
                    }
                    // 模式串总是从第一个开始匹配
                    bIndex = 0;
                } else {
                    return -1;
                }
            }
        }
        return aIndex - bIndex;
    }

    /**
     * Sunday 算法，将 charAt 改为数组法
     *
     * 执行用时：392 ms, 在所有 Java 提交中击败了10.36% 的用户
     * 内存消耗：38.3 MB, 在所有 Java 提交中击败了43.07% 的用户
     */
    public int strStr1(String haystack, String needle) {
        if (haystack == null || needle == null) {
            return 0;
        }
        int aLength = haystack.length();
        int bLength = needle.length();
        if (aLength < bLength) {
            return -1;
        }
        char[] aArray = haystack.toCharArray();
        char[] bArray = needle.toCharArray();
        int aIndex = 0; // 目标串匹配索引
        int bIndex = 0; // 模式串匹配索引
        while (bIndex < bLength) {
            if (aIndex > aLength - 1) {
                return -1;
            }
            // 匹配
            if (aArray[aIndex] == bArray[bIndex]) {
                aIndex++;
                bIndex++;
            }
            // 不匹配
            else {
                int nextCharIndex = aIndex - bIndex + bLength;
                if (nextCharIndex < aLength) {
                    int step = needle.lastIndexOf(aArray[nextCharIndex]);
                    if (step == -1) {
                        // 不存在的话，设置到目标字符的下一个元素
                        aIndex = nextCharIndex + 1;
                    } else {
                        // 存在的话，移动对应的数字
                        aIndex = nextCharIndex - step;
                    }
                    bIndex = 0;
                } else {
                    return -1;
                }
            }
        }
        return aIndex - bIndex;
    }

    @Test
    public void test() {
        String haystack = "hello"; String needle = "ll";
        int result = strStr(haystack, needle);
        System.out.println("result = " + result);

        int test = haystack.indexOf(needle);
        System.out.println("test = " + test);
    }
}

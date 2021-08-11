package com.sumkor.string._0438_findanagrams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 对子串的字符进行排序，再进行对比
 *
 * @author Sumkor
 * @since 2021/8/10
 */
public class Solution02 {

    /**
     * 滑动窗口 + 排序 (优化版)
     *
     * 通过记录上一次遍历的对比结果（ s 子串是否与 p 串相等），以此减少对子串的排序
     *
     * 执行用时：1006 ms, 在所有 Java 提交中击败了7.59% 的用户
     * 内存消耗：39.6 MB, 在所有 Java 提交中击败了34.83% 的用户
     *
     * {@link TestCase#testLong()} 耗时 100 ms
     */
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> result = new ArrayList<>();
        int pLength = p.length();
        int sLength = s.length();
        if (pLength > sLength) {
            return result;
        }
        // 排序
        char[] pArray = p.toCharArray();
        Arrays.sort(pArray);
        // 遍历字符串 s，寻找字母异位子串
        char[] sArray = s.toCharArray();
        // 双指针
        int i = 0;
        int j = i + pLength - 1;
        // 上一次遍历 s 子串是否和 p 串相等
        boolean lastEqual = false;
        // 滑动窗口
        while (i < sLength - pLength + 1) {
            char[] subArray = Arrays.copyOfRange(sArray, i, j + 1);
            if (!lastEqual) {
                // 遍历，对比 subArray 与 pArray 是否相等
                boolean isEqual = true;
                Arrays.sort(subArray);
                int index = 0;
                while (index < pLength) {
                    if (subArray[index] != pArray[index]) {
                        isEqual = false;
                        break;
                    }
                    index++;
                }
                if (isEqual) {
                    lastEqual = true;
                    result.add(i);
                }
            } else {
                // 移除的和加入的相等，则不用全量对比子串
                if (sArray[i - 1] == sArray[j]) {
                    result.add(i);
                } else {
                    lastEqual = false;
                }
            }
            i++;
            j++;
        }
        return result;
    }

    /**
     * 滑动窗口 + 排序
     *
     * 执行用时：1952 ms, 在所有 Java 提交中击败了5.03% 的用户
     * 内存消耗：39.2 MB, 在所有 Java 提交中击败了88.13% 的用户
     *
     * {@link TestCase#testLong()} 耗时 1000 ms
     */
    public List<Integer> findAnagrams1(String s, String p) {
        List<Integer> result = new ArrayList<>();
        int pLength = p.length();
        int sLength = s.length();
        if (pLength > sLength) {
            return result;
        }
        // 排序
        char[] pArray = p.toCharArray();
        Arrays.sort(pArray);
        // 遍历字符串 s
        char[] sArray = s.toCharArray();
        // 双指针
        int i = 0;
        int j = i + pLength - 1;
        // 滑动窗口
        while (i < sLength - pLength + 1) {
            char[] subArray = Arrays.copyOfRange(sArray, i, j + 1);
            Arrays.sort(subArray);
            // 对比 subArray 与 pArray 是否相等
            boolean isEqual = true;
            int index = 0;
            while (index < pLength) {
                if (subArray[index] != pArray[index]) {
                    isEqual = false;
                    break;
                }
                index++;
            }
            if (isEqual) {
                result.add(i);
            }
            i++;
            j++;
        }
        return result;
    }
}

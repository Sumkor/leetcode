package com.sumkor.string._0438_findanagrams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * 对子串的字符进行计数，再进行对比
 *
 * @author Sumkor
 * @since 2021/8/9
 */
public class Solution {

    /**
     * 滑动窗口 + 数组
     *
     * 前两种解法中，最大的问题在于每次移动窗口，都需要重新构造子串的哈希表。
     * 实际上，只需要构造一次，后续移动的时候，移除窗口的左边界，加入窗口的右边界，即可！
     *
     * 时间复杂度：O(N)
     * 空间复杂度：O(1)
     *
     * 执行用时：3 ms, 在所有 Java 提交中击败了99.99% 的用户
     * 内存消耗：39.6 MB, 在所有 Java 提交中击败了36.17% 的用户
     *
     * {@link TestCase#testLong()} 耗时 3 ms
     */
    public List<Integer> findAnagrams(String s, String p) {
        if (s == null || s.length() <= 0 || p == null || p.length() <= 0 || s.length() < p.length()) {
            return new ArrayList<>();
        }
        int sLength = s.length(), pLength = p.length();
        List<Integer> result = new ArrayList<>(sLength);
        char[] pArray = p.toCharArray();
        char[] sArray = s.toCharArray();
        int[] sMap = new int[26];
        int[] pMap = new int[26];
        // 初始化窗口
        for (int i = 0; i < pLength; i++) {
            pMap[pArray[i] - 'a']++;
            sMap[sArray[i] - 'a']++;
        }
        // 滑动窗口
        for (int i = 0; i < sLength - pLength + 1; i++) {
            if (i != 0) {
                // 移除窗口的左边界，加入窗口的右边界
                sMap[sArray[i - 1] - 'a']--;
                sMap[sArray[i + pLength - 1] - 'a']++;
            }
            boolean success = true;
            // 对比
            for (int j = 0; j < 26; j++) {
                if (sMap[j] != pMap[j]) {
                    success = false;
                    break;
                }
            }
            if (success) {
                result.add(i);
            }
        }
        return result;
    }

    /**
     * 滑动窗口 + 数组（使用数组代替哈希表）
     *
     * 执行用时：320 ms, 在所有 Java 提交中击败了13.88% 的用户
     * 内存消耗：39.9 MB, 在所有 Java 提交中击败了10.18% 的用户
     *
     * {@link TestCase#testLong()} 耗时 200 ms
     */
    public List<Integer> findAnagrams2(String s, String p) {
        List<Integer> result = new ArrayList<>();
        int pLength = p.length();
        int sLength = s.length();
        if (pLength > sLength) {
            return result;
        }
        // 由字符串 p 构造数组，index=字符所在ASCII编码，value=次数
        int[] pMap = new int[26];
        char[] pArray = p.toCharArray();
        for (int i = 0; i < pLength; i++) {
            pMap[pArray[i] - 'a'] += 1;
        }
        // 遍历字符串 s
        char[] sArray = s.toCharArray();
        for (int i = 0; i < sLength - pLength + 1; i++) {
            boolean success = true;
            char[] subArray = Arrays.copyOfRange(sArray, i, i + pLength);
            // 由子串构造数组，index=字符所在ASCII编码，value=次数
            int[] subMap = new int[26];
            for (int j = 0; j < pLength; j++) {
                if (pMap[subArray[j] - 'a'] == 0) {
                    success = false;
                    break;
                } else {
                    subMap[subArray[j] - 'a'] += 1;
                }
            }
            // 对比两个数组是否相同
            if (success) {
                for (int j = 0; j < pMap.length; j++) {
                    if (pMap[j] != subMap[j]) {
                        success = false;
                        break;
                    }
                }
            }
            if (success) {
                result.add(i);
            }
        }
        return result;
    }

    /**
     * 滑动窗口 + 哈希表
     *
     * 执行结果：超出时间限制
     *
     * {@link TestCase#testLong()} 耗时 3000 ms
     */
    public List<Integer> findAnagrams1(String s, String p) {
        List<Integer> result = new ArrayList<>();
        int pLength = p.length();
        int sLength = s.length();
        if (pLength > sLength) {
            return result;
        }
        // 由字符串 p 构造哈希表，key=字符，value=次数
        HashMap<Character, Integer> pMap = new HashMap<>();
        char[] pArray = p.toCharArray();
        for (int i = 0; i < pLength; i++) {
            pMap.put(pArray[i], pMap.getOrDefault(pArray[i], 0) + 1);
        }
        // 遍历字符串 s，寻找字母异位子串
        char[] sArray = s.toCharArray();
        for (int i = 0; i < sLength - pLength + 1; i++) {
            boolean success = true;
            char[] subArray = Arrays.copyOfRange(sArray, i, i + pLength);
            // 由子串构造哈希表
            HashMap<Character, Integer> subMap = new HashMap<>();
            for (int j = 0; j < pLength; j++) {
                if (!pMap.containsKey(subArray[j])) {
                    success = false;
                    break;
                } else {
                    subMap.put(subArray[j], subMap.getOrDefault(subArray[j], 0) + 1);
                }
            }
            // 对比两个哈希表是否相同
            if (success) {
                // 每一个字母的数量必须与 p 相同
                if (subMap.size() != pMap.size()) {
                    success = false;
                } else {
                    for (int j = 0; j < pLength; j++) {
                        if (!pMap.get(pArray[j]).equals(subMap.get(pArray[j]))) {
                            success = false;
                            break;
                        }
                    }
                }
            }
            if (success) {
                result.add(i);
            }
        }
        return result;
    }

}

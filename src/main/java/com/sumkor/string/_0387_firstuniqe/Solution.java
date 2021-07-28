package com.sumkor.string._0387_firstuniqe;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Sumkor
 * @since 2021/7/28
 */
public class Solution {

    /**
     * 使用 HashMap 记录每个字符出现的次数，返回第一个只出现一次的
     *
     * 执行用时：37 ms, 在所有 Java 提交中击败了22.91% 的用户
     * 内存消耗：38.8 MB, 在所有 Java 提交中击败了72.73% 的用户
     *
     * 时间复杂度：O(n)，其中 n 是字符串 s 的长度。我们需要进行两次遍历。
     * 空间复杂度：O(∣Σ∣)，其中 Σ 是字符集，在本题中 s 只包含小写字母，因此 ∣Σ∣≤26。我们需要 O(∣Σ∣) 的空间存储哈希映射。
     */
    public int firstUniqChar(String s) {
        // 记录出现次数
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char curr = s.charAt(i);
            if (map.containsKey(curr)) {
                map.put(curr, map.get(curr) + 1);
            } else {
                map.put(curr, 1);
            }
        }
        // 找到第一个不重复
        for (int i = 0; i < s.length(); i++) {
            if (map.get(s.charAt(i)) == 1) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 使用 数组 记录每个字符出现的次数
     * https://leetcode-cn.com/problems/first-unique-character-in-a-string/solution/zi-fu-chuan-zhong-de-di-yi-ge-wei-yi-zi-x9rok/
     *
     * 执行用时：5 ms, 在所有 Java 提交中击败了93.03% 的用户
     * 内存消耗：38.9 MB, 在所有 Java 提交中击败了61.45% 的用户
     */
    public int firstUniqChar0(String s) {
        int[] arr = new int[26];
        int n = s.length();
        for (int i = 0; i < n; i++) {
            arr[s.charAt(i) - 'a']++;
        }
        for (int i = 0; i < n; i++) {
            if (arr[s.charAt(i) - 'a'] == 1) {
                return i;
            }
        }
        return -1;
    }


    @Test
    public void test() {
        String s = "leetcode";
//        String s = "loveleetcode";
//        String s = "aabb";
//        String s = "aacbb";
//        String s = "aadadaad";
        int i = firstUniqChar(s);
        System.out.println("i = " + i);
    }
}

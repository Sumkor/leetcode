package com.sumkor.string._0003_lengthoflongestsubstring;

import org.junit.Test;

import java.util.HashSet;

/**
 * @author Sumkor
 * @since 2021/8/3
 */
public class Solution {

    /**
     * 暴力 + 哈希表去重
     *
     * 执行用时：92 ms, 在所有 Java 提交中击败了12.22% 的用户
     * 内存消耗：39.1 MB, 在所有 Java 提交中击败了12.31% 的用户
     */
    public int lengthOfLongestSubstring(String s) {
        int maxLength = 0;
        int length = s.length();
        char[] array = s.toCharArray();
        // 从 i 位置开始，列举不重复的后续字符，记录最大长度
        for (int i = 0; i < length; i++) {
            HashSet<Character> set = new HashSet<>();
            set.add(array[i]);
            int j = i + 1;
            while (j < length && !set.contains(array[j])) {
                set.add(array[j]);
                j++;
            }
            maxLength = Math.max(maxLength, set.size());
        }
        return maxLength;
    }

    @Test
    public void test() {
//        String s = "abcabcbb";
//        String s = "pwwkew";
//        String s = "bbbbb";
        String s = "";
        int length = lengthOfLongestSubstring(s);
        System.out.println("length = " + length);
    }
}

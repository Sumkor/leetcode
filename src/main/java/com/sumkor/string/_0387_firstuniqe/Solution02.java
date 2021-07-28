package com.sumkor.string._0387_firstuniqe;

import org.junit.Test;

import java.util.HashSet;

/**
 * @author Sumkor
 * @since 2021/7/28
 */
public class Solution02 {

    /**
     * 暴力 + 缓存
     *
     * 使用 hashMap 做缓存
     * 执行用时：11 ms, 在所有 Java 提交中击败了71.91% 的用户
     * 内存消耗：39 MB, 在所有 Java 提交中击败了50.97% 的用户
     *
     * 使用 hashSet 做缓存
     * 执行用时：10 ms, 在所有 Java 提交中击败了72.68% 的用户
     * 内存消耗：39 MB, 在所有 Java 提交中击败了39.09% 的用户
     */
    public int firstUniqChar(String s) {
        HashSet<Object> set = new HashSet<>();// 缓存
        for (int i = 0; i < s.length(); i++) {
            if (set.contains(s.charAt(i))) { // 命中缓存
                continue;
            }
            boolean isUnique = true;
            for (int j = 0; j < s.length(); j++) {
                if (i != j && s.charAt(i) == s.charAt(j)) {
                    isUnique = false;
                    break;
                }
            }
            if (isUnique) {
                return i;
            }
            set.add(s.charAt(i)); // 更新缓存
        }
        return -1;
    }

    /**
     * 暴力
     *
     * 执行用时：41 ms, 在所有 Java 提交中击败了14.71% 的用户
     * 内存消耗：38.9 MB, 在所有 Java 提交中击败了61.45% 的用户
     */
    public int firstUniqChar0(String s) {
        for (int i = 0; i < s.length(); i++) {
            boolean isUnique = true;
            for (int j = 0; j < s.length(); j++) {
                if (i != j && s.charAt(i) == s.charAt(j)) {
                    isUnique = false;
                    break;
                }
            }
            if (isUnique) {
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

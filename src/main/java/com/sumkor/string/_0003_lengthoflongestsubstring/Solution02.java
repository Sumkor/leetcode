package com.sumkor.string._0003_lengthoflongestsubstring;

import org.junit.Test;

import java.util.*;

/**
 * @author Sumkor
 * @since 2021/8/4
 */
public class Solution02 {

    /**
     * 滑动窗口
     * https://www.geekxh.com/1.5.%E6%BB%91%E5%8A%A8%E7%AA%97%E5%8F%A3%E7%B3%BB%E5%88%97/502.html
     *
     * 解题思路，是去维护一个可变长度的滑动窗口。
     * 1. 当下一个元素在窗口没有出现过时，我们扩大窗口；
     * 2. 当下一个元素在窗口中出现过时，我们缩小窗口，将出现过的元素以及其左边的元素统统移出。
     * 3. 在整个过程中，我们记录下窗口出现过的最大值即可。而我们唯一要做的，只需要尽可能扩大窗口。
     *
     * 这里使用【双指针】作为滑动窗口的边界，使用【哈希表】来作为滑动窗口的存储，用于去重。
     *
     * 执行用时：6 ms, 在所有 Java 提交中击败了81.84% 的用户
     * 内存消耗：39.1 MB, 在所有 Java 提交中击败了11.60% 的用户
     *
     * 通过观察，我们能看出来。如果是最坏情况的话，我们每一个字符都可能会访问两次，left一次，right一次，时间复杂度达到了O(2N)，这是不可饶恕的。
     */
    public int lengthOfLongestSubstring(String s) {
        int n = s.length();
        // 转换为字符数组，避免字符串 charAt() 操作的性能损耗
        char[] array = s.toCharArray();
        Set<Character> set = new HashSet<>();
        int maxLength = 0, i = 0, j = 0;
        while (i < n && j < n) {
            // 当 j 位置字符不存在时，扩张右边界（很好理解）
            if (!set.contains(array[j])) {
                set.add(array[j]);
                j++;
                maxLength = Math.max(maxLength, j - i);
            }
            // 当 j 位置字符已存在时，收缩左边界（目的是为了在左边 把与 j 位置相同的字符 一步步地排除掉！注意这里效率较低）
            else {
                set.remove(array[i]);
                i++;
            }
        }
        return maxLength;
    }

    /**
     * 滑动窗口 优化版（实际上提升有限）
     *
     * 定义字符到索引的映射，当我们找到重复的字符时，我们可以立即跳过该窗口，而不需要对之前的元素进行再次访问。
     *
     * 时间复杂度：O(N)，其中 N 是字符串的长度。左指针和右指针分别会遍历整个字符串一次。
     * 空间复杂度：O(∣Σ∣)，其中 Σ 表示字符集（即字符串中可以出现的字符），∣Σ∣ 表示字符集的大小。
     *
     * 执行用时：5 ms, 在所有 Java 提交中击败了85.60% 的用户
     * 内存消耗：38.4 MB, 在所有 Java 提交中击败了76.36% 的用户
     */
    public int lengthOfLongestSubstring1(String s) {
        int n = s.length();
        int maxLength = 0;
        char[] array = s.toCharArray();
        // map: key=字符, value=字符所在下标
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0, j = 0; j < n; j++) {
            // 当 map 中存在 j 位置字符时，则将 i 收缩到该字符首次出现的位置的下一位（收缩左边界，一次性排除到位）
            if (map.containsKey(array[j])) {
                i = Math.max(map.get(array[j]) + 1, i); // 若 i 比 map.get(array[j]) + 1 还小则不收缩
            }
            // 记录最大长度
            maxLength = Math.max(maxLength, j - i + 1);
            // 更新字符所在的最新位置
            map.put(array[j], j);
        }
        return maxLength;
    }

    /**
     * 滑动窗口 进一步优化版（使用数组替换哈希表）
     *
     * 在本题中没有明确说明字符集，因此可以默认为所有 ASCII 码在 [0,128) 内的字符
     * 因为 ASCII 码表里的字符总共有 128 个。ASCII 码的长度是一个字节，8位，理论上可以表示 256 个字符，但是许多时候只谈 128 个。
     *
     * 执行用时：2 ms, 在所有 Java 提交中击败了99.70% 的用户
     * 内存消耗：38.6 MB, 在所有 Java 提交中击败了47.19% 的用户
     *
     * 另外一个可以使用数组替换哈希表的题目
     * @see com.sumkor.string._0387_firstuniqe.Solution
     */
    public int lengthOfLongestSubstring2(String s) {
        int n = s.length();
        int maxLength = 0;
        char[] array = s.toCharArray();
        // 索引数组，该数组中的每一位，可以表示字符（ASCII 码形式），以及该字符在字符串中的索引！
        int[] charIndex = new int[256];
        Arrays.fill(charIndex, -1);
        for (int i = 0, j = 0; j < n; j++) {
            char currChar = array[j];
            // 查数组，若已存在，则收缩左边界
            /*if (charIndex[currChar] != -1) */
            i = Math.max(charIndex[currChar] + 1, i);
            // 记录最大长度
            maxLength = Math.max(maxLength, j - i + 1);
            // 在索引数组中，记录当前字符在字符串中的最新索引
            charIndex[currChar] = j;
        }
        return maxLength;
    }

    @Test
    public void test() {
//        String s = "abcabcbb";
//        String s = "pwwkew";
//        String s = "bbbbb";
//        String s = "tmmzuxt";
        String s = " ";
        int length = lengthOfLongestSubstring2(s);
        System.out.println("length = " + length);
    }
}

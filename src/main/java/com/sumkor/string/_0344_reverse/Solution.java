package com.sumkor.string._0344_reverse;

import org.junit.Test;

import java.util.stream.Stream;

/**
 * @author Sumkor
 * @since 2021/7/28
 */
public class Solution {

    /**
     * 左右指针从中间开始，依次交互元素
     *
     * 执行用时：1 ms, 在所有 Java 提交中击败了98.43% 的用户
     * 内存消耗：45 MB, 在所有 Java 提交中击败了51.81% 的用户
     *
     * 时间复杂度：O(N)，其中 N 为字符数组的长度。一共执行了 N/2 次的交换。
     * 空间复杂度：O(1)。只使用了常数空间来存放若干变量。
     */
    public void reverseString(char[] s) {
        if (s.length < 2) {
            return;
        }
        int left = 0;
        int right = 0;
        // 左右指针从中间开始
        if (s.length % 2 == 0) {
            // 偶数
            left = s.length / 2 - 1;
            right = left + 1;
        } else {
            // 奇数
            left = s.length / 2;
            right = left;
        }
        while (left >= 0 && right < s.length) {
            if (s[left] != s[right]) {
                char tmp = s[left];
                s[left] = s[right];
                s[right] = tmp;
            }
            left--;
            right++;
        }
    }

    @Test
    public void test() {
        char[] s = {'h', 'e', 'l', 'l', 'o'};
        reverseString(s);
        Stream.of(s).forEach(System.out::print);
    }
}

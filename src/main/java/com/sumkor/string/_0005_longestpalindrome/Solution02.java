package com.sumkor.string._0005_longestPalindrome;

import org.junit.Test;

/**
 * @author Sumkor
 * @since 2021/7/28
 */
public class Solution02 {

    /**
     * 中心扩展算法
     * https://labuladong.gitee.io/algo/5/40/
     *
     * 寻找回文串的问题核心思想是：从中间开始向两边扩散来判断回文串。
     * 但是呢，回文串的长度可能是奇数也可能是偶数，如果是 abba 这种情况，没有一个中心字符，因此需要分两种情况来处理。
     *
     * 时间复杂度 O(N^2)，空间复杂度 O(1)
     *
     * 执行用时：31 ms, 在所有 Java 提交中击败了83.73% 的用户
     * 内存消耗：38.8 MB, 在所有 Java 提交中击败了59.47% 的用户
     */
    public String longestPalindrome(String s) {
        if (null == s || s.length() < 2) return s;
        String ans = "";
        for (int i = 0; i < s.length(); i++) {
            String s1 = findPalindrome(s, i, i); // 这个是为了解决回文是奇数的情况
            String s2 = findPalindrome(s, i, i + 1); // 这个是为了解决回文是偶数的情况
            ans = ans.length() > s1.length() ? ans : s1;
            ans = ans.length() > s2.length() ? ans : s2;
        }
        return ans;
    }

    public String findPalindrome(String target, int l, int r) {
        while (l >= 0 && r < target.length() && target.charAt(l) == target.charAt(r)) {
            l--;
            r++;
        }
        return target.substring(l + 1, r);
    }

    @Test
    public void test() {
//        String s = "ac";
//        String s = "babad"; // "bab"
//        String s = "bacabab"; // "bacab"
        String s = "xaabacxcabaaxcabaax"; // "xaabacxcabaax"
        String longestPalindrome = longestPalindrome(s);
        System.out.println("longestPalindrome = " + longestPalindrome);
    }
}

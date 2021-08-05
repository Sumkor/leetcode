package com.sumkor.string._0005_longestPalindrome;

import org.junit.Test;

/**
 * @author Sumkor
 * @since 2021/7/25
 */
public class Solution03 {

    /**
     * 动态规划
     * https://leetcode-cn.com/problems/longest-palindromic-substring/solution/zui-chang-hui-wen-zi-chuan-by-leetcode-solution/
     *
     * 对于一个子串而言，如果它是回文串，并且长度大于 2，那么将它首尾的两个字母去除之后，它仍然是个回文串。
     * 例如对于字符串 "ababa"，如果我们已经知道 "bab" 是回文串，那么 "ababa"  一定是回文串，这是因为它的首尾两个字母都是 "a"。
     * 根据这样的思路，我们就可以用动态规划的方法解决本题。
     *
     * 我们用 P(i,j) 表示字符串 s 的第 i 到 j 个字母组成的串（下文表示成 s[i:j]）是否为回文串：
     *     P(i,j) = true
     *     P(i,j) = false
     * 状态转移方程：
     *     P(i,j) = P(i+1,j−1) && (Si​==Sj​)
     * 也就是说，只有 s[i+1:j−1] 是回文串，并且 s 的第 i 和 j 个字母相同时，s[i:j] 才会是回文串。
     *
     * 上文的所有讨论是建立在子串长度大于 2 的前提之上的，我们还需要考虑动态规划中的边界条件，即子串的长度为 1 或 2。
     * 对于长度为 1 的子串，它显然是个回文串；
     * 对于长度为 2 的子串，只要它的两个字母相同，它就是一个回文串。
     * 因此我们就可以写出动态规划的边界条件：
     *     P(i,i) = true
     *     P(i,i+1) = (Si==Si+1)
     *
     * 根据这个思路，我们就可以完成动态规划了，最终的答案即为所有 P(i,j)=true 中 j−i+1（即子串长度）的最大值。
     * 注意：在状态转移方程中，我们是从长度较短的字符串向长度较长的字符串进行转移的，因此一定要注意动态规划的循环顺序。
     *
     * 时间复杂度：O(n^2)，其中 n 是字符串的长度。动态规划的状态总数为 O(n^2)，对于每个状态，我们需要转移的时间为 O(1)。
     * 空间复杂度：O(n^2)，即存储动态规划状态需要的空间。
     *
     * 这道题是少有的动态规划非最优解法的问题。
     *
     * 执行用时：173 ms, 在所有 Java 提交中击败了50.15% 的用户
     * 内存消耗：43 MB, 在所有 Java 提交中击败了13.49% 的用户
     */
    public String longestPalindrome(String s) {
        int len = s.length();
        if (len < 2) {
            return s;
        }

        int maxLen = 1;
        int begin = 0;
        // dp[i][j] 表示 s[i..j] 是否是回文串
        boolean[][] dp = new boolean[len][len];
        // 初始化：所有长度为 1 的子串都是回文串
        for (int i = 0; i < len; i++) {
            dp[i][i] = true;
        }

        char[] charArray = s.toCharArray();
        // 递推开始
        // 先枚举子串长度
        for (int L = 2; L <= len; L++) {
            // 枚举左边界，左边界的上限设置可以宽松一些
            for (int i = 0; i < len; i++) {
                // 由 L 和 i 可以确定右边界，即 j - i + 1 = L 得
                int j = L + i - 1;
                // 如果右边界越界，就可以退出当前循环
                if (j >= len) {
                    break;
                }

                if (charArray[i] != charArray[j]) {
                    dp[i][j] = false;
                } else {
                    // 相等的情况下
                    // 考虑头尾去掉以后没有字符剩余，或者剩下一个字符的时候，肯定是回文串
                    if (j - i < 3) {
                        dp[i][j] = true;
                    }
                    // 状态转移
                    else {
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                }

                // 只要 dp[i][L] == true 成立，就表示子串 s[i..L] 是回文，此时记录回文长度和起始位置
                if (dp[i][j] && j - i + 1 > maxLen) {
                    maxLen = j - i + 1;
                    begin = i;
                }
            }
        }
        return s.substring(begin, begin + maxLen);
    }

    @Test
    public void test() {
//        String s = "ac";
        String s = "babad"; // "bab"
//        String s = "bacabab"; // "bacab"
//        String s = "xaabacxcabaaxcabaax"; // "xaabacxcabaax"
        String longestPalindrome = longestPalindrome(s);
        System.out.println("longestPalindrome = " + longestPalindrome);
    }
}

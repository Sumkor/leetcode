package com.sumkor.backtrack._0022_parenthesis;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Sumkor
 * @since 2021/9/17
 */
public class Solution02 {

    /**
     * 回溯算法，官方题解
     * https://leetcode-cn.com/problems/generate-parentheses/solution/gua-hao-sheng-cheng-by-leetcode-solution/
     *
     * 如果利用暴力法，可以生成所有 2^(2n) 个 '(' 和 ')' 字符构成的序列，然后检查每一个是否有效即可。
     * 但是，可以只在序列仍然保持有效时才添加 '(' or ')'，通过跟踪到目前为止放置的左括号和右括号的数目来做到这一点。
     * 如果左括号数量不大于 n，我们可以放一个左括号。如果右括号数量小于左括号的数量，我们可以放一个右括号。
     *
     * 我们的复杂度分析依赖于理解 generateParenthesis(n) 中有多少个元素。这个分析超出了本文的范畴，但事实证明这是第 n 个卡特兰数。
     * 空间复杂度：O(n)，除了答案数组之外，我们所需要的空间取决于递归栈的深度，每一层递归函数需要 O(1) 的空间，最多递归 2n 层，因此空间复杂度为 O(n)。
     *
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗：38.7 MB, 在所有 Java 提交中击败了27.23% 的用户
     */
    public List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList<>();
        backtrack(ans, new StringBuilder(), 0, 0, n);
        return ans;
    }

    public void backtrack(List<String> ans, StringBuilder path, int open, int close, int max) {
        if (path.length() == max * 2) {
            ans.add(path.toString());
            return;
        }
        if (open < max) {
            path.append('(');
            backtrack(ans, path, open + 1, close, max);
            path.deleteCharAt(path.length() - 1);
        }
        if (close < open) {
            path.append(')');
            backtrack(ans, path, open, close + 1, max);
            path.deleteCharAt(path.length() - 1);
        }
    }

    /**
     * 输入：n = 3
     * 输出：["((()))","(()())","(())()","()(())","()()()"]
     *
     * 输入：n = 1
     * 输出：["()"]
     */
    @Test
    public void test() {
        int n = 3;
        List<String> results = generateParenthesis(n);
        System.out.println("results = " + results);
    }
}

package com.sumkor.backtrack._0022_parenthesis;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Sumkor
 * @since 2021/9/17
 */
public class Solution {

    /**
     * 回溯算法
     * n = 3 时，相当于对 "((()))" 进行全排列，并筛选合法的
     *
     * 执行用时：1 ms, 在所有 Java 提交中击败了78.70% 的用户
     * 内存消耗：38.5 MB, 在所有 Java 提交中击败了67.29% 的用户
     */
    public List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList<>();
        StringBuilder path = new StringBuilder();
        boolean[] map = new boolean[n + n];
        dfs(n, 0, 0, map, path, ans);
        return ans;
    }

    private void dfs(int n, int leftCount, int rightCount, boolean[] map, StringBuilder path, List<String> ans) {
        if (leftCount == n && rightCount == n) {
            ans.add(path.toString());
            return;
        }
        if (leftCount > n || rightCount > n) {
            return;
        }
        for (int i = 0; i < n + n; i++) {
            // 剪枝：每一次都是从 0 到 2n 范围取未选的
            if (map[i]) {
                continue;
            }
            // 剪枝：过滤重复元素，上一位没有选择的情况下，当前位与上一位相等，则当前位也不选
            if (i > 0 && !map[i - 1] && i != n) {
                continue;
            }
            // 判断当前是左右括号
            boolean isLeft = i < n;
            if (isLeft) {
                path.append("(");
                map[i] = true;
                // System.out.println("递归前 》" + path);
                dfs(n, leftCount + 1, rightCount, map, path, ans);
            } else {
                // 剪枝：非闭合括号
                if (rightCount + 1 > leftCount) {
                    continue;
                }
                map[i] = true;
                path.append(")");
                // System.out.println("递归前 》" + path);
                dfs(n, leftCount, rightCount + 1, map, path, ans);
            }
            // 撤销选择
            map[i] = false;
            path.delete(leftCount + rightCount, leftCount + rightCount + 1);
            // System.out.println("递归后 》" + path);
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

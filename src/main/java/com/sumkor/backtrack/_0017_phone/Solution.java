package com.sumkor.backtrack._0017_phone;

import org.junit.Test;

import java.util.*;

/**
 * @author Sumkor
 * @since 2021/9/16
 */
public class Solution {

    /**
     * 回溯算法，思路与官方题解一致
     *
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗：37.2 MB, 在所有 Java 提交中击败了48.53% 的用户
     */
    public List<String> letterCombinations(String digits) {
        List<String> ans = new ArrayList<>();
        int n = digits.length();
        if (n == 0) {
            return ans;
        }
        if (n == 1) {
            ans.addAll(Arrays.asList(map.get(digits.charAt(0))));
            return ans;
        }
        dfs(digits.toCharArray(), digits.length(), 0, new StringBuilder(), ans);
        return ans;
    }

    private void dfs(char[] digits, int n, int index, StringBuilder path, List<String> ans) {
        if (index == n) {
            ans.add(path.toString());
            return;
        }
        String[] letters = map.get(digits[index]);
        for (int i = 0; i < letters.length; i++) {
            // 做出选择
            path.append(letters[i]);
            // 进入递归
            dfs(digits, n, index + 1, path, ans);
            // 撤销选择（这一步写法的优化，可以节省1ms）
            // path.delete(path.length() - 1, path.length());
            path.delete(index, index + 1);
        }
    }

    private final Map<Character, String[]> map = new HashMap<>();

    {
        map.put('2', new String[]{"a", "b", "c"});
        map.put('3', new String[]{"d", "e", "f"});
        map.put('4', new String[]{"g", "h", "i"});
        map.put('5', new String[]{"j", "k", "l"});
        map.put('6', new String[]{"m", "n", "o"});
        map.put('7', new String[]{"p", "q", "r", "s"});
        map.put('8', new String[]{"t", "u", "v"});
        map.put('9', new String[]{"w", "x", "y", "z"});
    }

    /**
     * 输入：digits = "23"
     * 输出：["ad","ae","af","bd","be","bf","cd","ce","cf"]
     *
     * 输入：digits = ""
     * 输出：[]
     *
     * 输入：digits = "2"
     * 输出：["a","b","c"]
     */
    @Test
    public void test() {
        String digits = "23";
        List<String> result = letterCombinations(digits);
        System.out.println("result = " + result);
    }
}

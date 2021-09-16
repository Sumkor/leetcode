package com.sumkor.backtrack._0017_phone;

import org.junit.Test;

import java.util.*;

/**
 * @author Sumkor
 * @since 2021/9/16
 */
public class Solution02 {

    /**
     * 官方题解
     * https://leetcode-cn.com/problems/letter-combinations-of-a-phone-number/solution/dian-hua-hao-ma-de-zi-mu-zu-he-by-leetcode-solutio/
     *
     * 时间复杂度：O(3^m×4^n).
     * 其中 m 是输入中对应 3 个字母的数字个数（包括数字 2、3、4、5、6、8），n 是输入中对应 4 个字母的数字个数（包括数字 7、9），m+n 是输入数字的总个数。
     * 当输入包含 m 个对应 3 个字母的数字和 n 个对应 4 个字母的数字时，不同的字母组合一共有 3^m × 4^n 种，需要遍历每一种字母组合。
     *
     * 空间复杂度：O(m+n).
     * 其中 m 是输入中对应 3 个字母的数字个数，n 是输入中对应 4 个字母的数字个数，m+n 是输入数字的总个数。
     * 除了返回值以外，空间复杂度主要取决于哈希表以及回溯过程中的递归调用层数，哈希表的大小与输入无关，可以看成常数，递归调用层数最大为 m+n。
     *
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗：37.1 MB, 在所有 Java 提交中击败了58.87% 的用户
     */
    public List<String> letterCombinations(String digits) {
        if (digits.length() == 0) return result;
        getResult(digits, 0);
        return result;
    }

    StringBuilder path = new StringBuilder();
    List<String> result = new ArrayList<>();
    final Map<Integer, String> map = Map.of(
            2, "abc",
            3, "def",
            4, "ghi",
            5, "jkl",
            6, "mno",
            7, "pqrs",
            8, "tuv",
            9, "wxyz"
    );

    /**
     * digits 用来获取数字，index 用来记录树的深度
     */
    public void getResult(String digits, int deep) {
        if (deep == digits.length()) {
            result.add(path.toString());
            return;
        }
        int index = digits.charAt(deep) - 48;
        String tempString = map.get(index);
        for (int i = 0; i < tempString.length(); i++) {
            path.append(tempString.charAt(i));
            getResult(digits, deep + 1);
            path.delete(deep, deep + 1);
        }
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

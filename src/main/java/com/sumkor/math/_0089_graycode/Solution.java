package com.sumkor.math._0089_graycode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Sumkor
 * @since 2021/12/5
 */
public class Solution {

    /**
     * 套用公式
     *
     * 执行用时：5 ms, 在所有 Java 提交中击败了84.32% 的用户
     * 内存消耗：45.6 MB, 在所有 Java 提交中击败了49.87% 的用户
     */
    public List<Integer> grayCode(int n) {
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < 1 << n; i++) {
            ans.add(gray(i));
        }
        return ans;
    }

    /**
     * 计算第 n 个格雷码
     * 格雷码计算公式：G(n) = n xor (n >> 1)
     */
    public int gray(int n) {
        return n ^ (n >> 1);
    }

    /**
     * 输入：n = 2
     * 输出：[0,1,3,2]
     * [0,1,3,2] 的二进制表示是 [00,01,11,10]
     *
     * 输入：n = 1
     * 输出：[0,1]
     *
     * n = 3
     * [000, 100, 110, 010, 011, 111, 101, 001]
     */
    @Test
    public void test() {
        int n = 3;
        List<Integer> ans = grayCode(n);
        System.out.println("ans = " + ans);
    }
}

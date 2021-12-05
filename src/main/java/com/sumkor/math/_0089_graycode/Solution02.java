package com.sumkor.math._0089_graycode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Sumkor
 * @since 2021/12/5
 */
public class Solution02 {

    /**
     * 暴力递归 + 剪枝
     * https://leetcode-cn.com/problems/gray-code/solution/wa-pian-89-ge-lei-bian-ma-java-zhong-ji-s3rv2/
     *
     * 每个数在每一位上做改变
     * 没出现过的添加到答案
     *
     * 执行用时：5 ms, 在所有 Java 提交中击败了84.32% 的用户
     * 内存消耗：45.5 MB, 在所有 Java 提交中击败了54.91% 的用户
     */
    public List<Integer> grayCode(int n) {
        int count = (int) Math.pow(2, n);    // 总数量
        boolean[] used = new boolean[count]; // 记录是否出现过
        List<Integer> ans = new ArrayList<>();
        grayCode(0, n, used, ans);   // 从0开始，n位数字，使用记录，答案
        return ans;
    }

    /**
     * 二进制或运算：0|0=0，0|1=1，1|1=1
     *
     * 若 n=3，则初始 num 的二进制值为 000
     * 而 num|dif 表示变换 num 二进制的其中一位，它的值表示变换后的结果
     * 用 used 数组存储变换之后的值，若变换后的值已经存在，则不再继续，作为剪枝
     */
    private void grayCode(int num, int n, boolean[] used, List<Integer> ans) {
        used[num] = true; // 标记出现过了
        ans.add(num);     // 记录答案
        for (int i = 0; i < n; i++) { // 依次变换 num 的其中一位进行尝试
            int dif = 1 << i;
            if (!used[num | dif]) // 变换 num 上的一位，如果没有出现过，则进入递归
                this.grayCode(num | dif, n, used, ans);
        }
    }

    @Test
    public void test() {
        int n = 3;
        List<Integer> ans = grayCode(n);
        System.out.println("ans = " + ans);
    }
}

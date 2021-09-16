package com.sumkor.backtrack._0060_permuteseq;

import org.junit.Test;

/**
 * @author Sumkor
 * @since 2021/9/15
 */
public class Solution02 {

    /**
     * 思路：通过 计算剩余数字个数的阶乘数，一位一位选出第 k 个排列的数位。
     * https://leetcode-cn.com/problems/permutation-sequence/solution/hui-su-jian-zhi-python-dai-ma-java-dai-ma-by-liwei/
     *
     * 容易想到，使用同「力扣」第 46 题： 全排列 的回溯搜索算法，依次得到全排列，输出第 k 个全排列即可。
     * 事实上，我们不必求出所有的全排列。可以利用剪枝的思想，减去大量枝叶，直接来到需要的叶子结点。
     *
     * 基于以下几点考虑：
     * 所求排列 一定在叶子结点处得到，进入每一个分支，可以根据已经选定的数的个数，进而计算还未选定的数的个数，然后计算阶乘，就知道这一个分支的 叶子结点 的个数：
     * 1. 如果 k 大于这一个分支将要产生的叶子结点数，直接跳过这个分支，这个操作叫「剪枝」；
     * 2. 如果 k 小于等于这一个分支将要产生的叶子结点数，那说明所求的全排列一定在这一个分支将要产生的叶子结点里，需要递归求解。
     *
     * 时间复杂度：O(N^2)，最坏肯定是要找到第 N! 个，但是第 1 层你只需要比较 N-1 次，第 2 层比较 N-2 次，以此类推。
     * 空间复杂度：O(N)，nums、used、path 都与 N 等长，factorial 数组就 10 个数，是常数级别的。
     *
     * 执行用时：1 ms, 在所有 Java 提交中击败了98.43% 的用户
     * 内存消耗：35.6 MB, 在所有 Java 提交中击败了88.19% 的用户
     */
    public String getPermutation(int n, int k) {
        this.n = n;
        this.k = k;
        // 计算阶乘数组
        calculateFactorial(n);
        // 查找全排列
        used = new boolean[n + 1];
        StringBuilder path = new StringBuilder();
        dfs(0, path);
        return path.toString();
    }

    /**
     * 记录数字是否使用过
     */
    private boolean[] used;

    /**
     * 阶乘数组
     */
    private int[] factorial;

    private int n;
    private int k;

    /**
     * @param index 表示在这一步之前已经选择了几个数字
     */
    private void dfs(int index, StringBuilder path) {
        if (index == n) {
            return;
        }
        // 根据已选择的数字个数，计算这一分支可以得到多少叶子节点。进而决定是 跳过分支 还是 继续查找。
        // 例如：第 1 次进入这里，由于确定了 1 个数字，则剩余待确定的是 n-1 个数字，共 (n-1)! 个排列
        int cnt = factorial[n - 1 - index];
        for (int i = 1; i <= n; i++) {
            if (used[i]) {
                continue;
            }
            if (cnt < k) {
                k -= cnt;
                continue;
            }
            path.append(i);
            used[i] = true;
            dfs(index + 1, path);
            // 注意 1：不可以回溯（重置变量），算法设计是「一下子来到叶子结点」，没有回头的过程
            // 注意 2：这里要加 return，后面的数没有必要遍历去尝试了
            return;
        }
    }

    /**
     * 计算阶乘数组
     * 注意：0!=1，它表示了没有数可选的时候，即表示到达叶子结点了，排列数只剩下 1 个。
     */
    private void calculateFactorial(int n) {
        factorial = new int[n + 1];
        factorial[0] = 1;
        for (int i = 1; i <= n; i++) {
            factorial[i] = factorial[i - 1] * i;
        }
    }

    // -------------------------------------------------------

    /**
     * 不用回溯，用循环来写，减少一点空间复杂度。
     * https://leetcode-cn.com/problems/permutation-sequence/solution/hui-su-jian-zhi-python-dai-ma-java-dai-ma-by-liwei/578279/
     *
     * 题目中说「给定 n 的范围是 [1,9]」，可以把从 0 到 9 的阶乘计算好，放在一个数组里，可以根据索引直接获得阶乘值。
     *
     * 执行用时：1 ms, 在所有 Java 提交中击败了98.43% 的用户
     * 内存消耗：35.8 MB, 在所有 Java 提交中击败了60.20% 的用户
     */
    public String getPermutation1(int n, int k) {
        final int[] factorial = new int[]{1, 1, 2, 6, 24, 120, 720, 5040, 40320, 362880};
        boolean[] used = new boolean[n + 1];
        StringBuilder permutation = new StringBuilder();
        for (int i = n - 1; i >= 0; i--) {
            int cnt = factorial[i];
            for (int j = 1; j <= n; j++) {
                if (used[j]) {
                    continue;
                }
                if (k > cnt) {
                    k -= cnt;
                    continue;
                }
                used[j] = true;
                permutation.append(j);
                break;
            }
        }
        return permutation.toString();
    }

    // -------------------------------------------------------

    /**
     * 输入：n = 3, k = 1
     * 输出："123"
     *
     * 输入：n = 3, k = 3
     * 输出："213"
     *
     * 输入：n = 4, k = 9
     * 输出："2314"
     */
    @Test
    public void test() {
        String permutation = getPermutation(5, 3);
        System.out.println("permutation = " + permutation);
    }
}

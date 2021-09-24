package com.sumkor.dp._0375_guess;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Sumkor
 * @since 2021/9/24
 */
public class Solution02 {

    /**
     * 暴力法（官方题解）
     * https://leetcode-cn.com/problems/guess-number-higher-or-lower-ii/solution/cai-shu-zi-da-xiao-ii-by-leetcode/
     *
     * 首先，我们需要意识到我们在范围 (1,n) 中猜数字的时候，需要考虑最坏情况下的代价。也就是说要算每次都猜错的情况下的总体最大开销。
     *
     * 在暴力算法中，我们首先在 (1,n) 中任意挑选一个数字，假设它是个错误的猜测（最坏情况），我们需要用最小代价去猜到需要的数字。
     * 那么在一次尝试以后，答案要么在我们猜的数字的左边要么在右边，为了考虑最坏情况，我们需要考虑两者的较大值。
     *
     * 因此，如果我们选择 i 作为第一次尝试，总体最小代价是：
     * cost(1,n) = i + max(cost(1,i−1), cost(i+1,n))
     *
     * 对于左右两段，我们分别考虑在段内选择一个数，并重复上面的过程来求得最小开销。
     * 使用如上方法，我们能求得从 i 开始猜，猜到答案的最小代价。
     *
     * 同样地，我们遍历 (1,n) 中的所有数字并分别作为第一次尝试，求出每一个的代价，并输出最小值即为答案。
     */
    public int getMoneyAmount(int n) {
        return calculate(1, n);
    }

    /**
     * 对暴力法进行剪枝
     *
     * 一个重要的发现是，从段内选择一个数，右边区间都比左边区间大。
     * 所以我们只需要从右边区间获取最大开销即可，因为它的开销肯定比左边区间的要大。
     */
    public int calculate(int low, int high) {
        if (low >= high)
            return 0;
        int minResult = Integer.MAX_VALUE;
        for (int i = (low + high) / 2; i <= high; i++) {
            // 在一次尝试以后，答案要么在我们猜的数字的左边要么在右边，为了考虑最坏情况，我们需要考虑两者的较大值
            int res = i + Math.max(calculate(i + 1, high), calculate(low, i - 1));
            // 遍历每一个 i 作为尝试，求出最小值，即为猜到数字的最小代价
            minResult = Math.min(res, minResult);
        }
        return minResult;
    }

    /**
     * 暴力法
     *
     * 时间复杂度： O(n!)。我们选择一个数作为第一次尝试，然后递归中再选一个数，这样重复 n 次的时间代价为 O(n!)。
     * 空间复杂度： O(n)。 n 层递归的开销。
     */
    public int calculate0(int low, int high) {
        if (low >= high)
            return 0;
        int minResult = Integer.MAX_VALUE;
        for (int i = low; i <= high; i++) {
            int res = i + Math.max(calculate0(i + 1, high), calculate0(low, i - 1));
            minResult = Math.min(res, minResult);
        }
        return minResult;
    }


    @Test
    public void test() {
        int n = 10;
        int moneyAmount = getMoneyAmount(n);
        Assert.assertEquals(16, moneyAmount);
    }
}

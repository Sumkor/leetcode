package com.sumkor.array._0122_stock;

import org.junit.Test;

/**
 * @author Sumkor
 * @since 2021/7/18
 */
public class Solution {

    /**
     * 将数值波动转换为折线图
     * 在所有的上升阶段，都完成一次低价买入高价卖出
     *
     * 执行用时：1 ms, 在所有 Java 提交中击败了99.57% 的用户
     * 内存消耗：38.1 MB, 在所有 Java 提交中击败了74.03% 的用户
     *
     * 时间复杂度：O(n)，其中 n 为数组的长度。我们只需要遍历一次数组即可。
     * 空间复杂度：O(1)。只需要常数空间存放若干变量。
     */
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length <= 1) {
            return 0;
        }
        int maxProfit = 0;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < prices.length; i++) {
            if (prices[i] > min) { // 识别到是上升阶段，记录差值
                maxProfit = maxProfit + prices[i] - min;
            }
            min = prices[i];
        }
        return maxProfit;
    }

    @Test
    public void test() {
//        int[] prices = {7, 1, 5, 3, 6, 4};
//        int[] prices = {7, 6, 4, 3, 1};
//        int[] prices = {2, 4, 1};
        int[] prices = {1, 2, 3, 4, 5};
        int maxProfit = maxProfit(prices);
        System.out.println("maxProfit = " + maxProfit);
    }
}

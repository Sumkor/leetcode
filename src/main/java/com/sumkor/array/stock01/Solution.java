package com.sumkor.array.stock01;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.File;

/**
 * @author Sumkor
 * @since 2021/7/18
 */
public class Solution {

    /**
     * 只需要找到最小值、记录最大差值，不需要找到最大值
     *
     * 执行用时：2 ms, 在所有 Java 提交中击败了98.52% 的用户
     * 内存消耗：51.4 MB, 在所有 Java 提交中击败了54.12% 的用户
     *
     * 时间复杂度：O(n)，只需要遍历一次。
     * 空间复杂度：O(1)，只使用了常数个变量。
     */
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length <= 1) {
            return 0;
        }
        int maxProfit = 0;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < prices.length; i++) {
            if (prices[i] > min) {
                if (maxProfit < prices[i] - min) {
                    maxProfit = prices[i] - min; // 记录最大差值
                }
            } else {
                min = prices[i]; // 找到最小值
            }
        }
        return maxProfit;
    }

    /**
     * 先找最小值，再找最大值，记录最大差值
     *
     * 执行结果：
     * 执行用时：2 ms, 在所有 Java 提交中击败了98.52% 的用户
     * 内存消耗：51.5 MB, 在所有 Java 提交中击败了30.76% 的用户
     */
    public int maxProfit1(int[] prices) {
        if (prices == null || prices.length <= 1) {
            return 0;
        }
        int maxProfit = 0;
        int minIndex = 0;
        int maxIndex = 0;
        for (int i = 0; i < prices.length; i++) {
            int curr = prices[i];
            if (curr < prices[minIndex]) { // 只关心找到最小值
                minIndex = i;
            }
            if (minIndex > maxIndex) { // 最大值只能在最小值后面
                maxIndex = i;
            }
            if (curr > prices[maxIndex]) { // 不仅关心找到最大值，还要找到最大差值
                maxIndex = i;
                if (maxProfit < prices[maxIndex] - prices[minIndex]) {
                    maxProfit = prices[maxIndex] - prices[minIndex];
                }
            }
        }
        return maxProfit;
    }

    /**
     * 遍历两次
     *
     * 执行结果：
     * 超出时间限制
     */
    public int maxProfit0(int[] prices) {
        if (prices == null || prices.length <= 1) {
            return 0;
        }
        int maxProfit = 0;
        for (int i = 0; i < prices.length; i++) {
            for (int j = i; j < prices.length; j++) {
                if (j == i) continue;
                if ((prices[j] - prices[i]) > maxProfit) {
                    maxProfit = prices[j] - prices[i];
                }
            }
        }
        return maxProfit;
    }

    @Test
    public void test() {
//        int[] prices = {7, 1, 5, 3, 6, 4};
//        int[] prices = {7, 6, 4, 3, 1};
        int[] prices = {2, 4, 1};
        int maxProfit = maxProfit(prices);
        System.out.println("maxProfit = " + maxProfit);
    }

    @Test
    public void testLong() throws Exception {
        File file = new File("C:/TOOL/Code/GitHub/leetcode/src/main/java/com/sumkor/array/stock01/input.txt");
        boolean exists = file.exists();
        System.out.println("exists = " + exists);

        String fileToString = FileUtils.readFileToString(file, "UTF8");
//        System.out.println("fileToString = " + fileToString);
        String[] split = fileToString.split(",");
        int[] prices = new int[split.length];
        for (int i = 0; i < split.length; i++) {
            prices[i] = Integer.parseInt(split[i]);
        }
        long start = System.currentTimeMillis();
        int maxProfit = maxProfit(prices);
        System.out.println("ms:" + (System.currentTimeMillis() - start));
        System.out.println("maxProfit = " + maxProfit);
    }
}

package com.sumkor.dp._0375_guess;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Sumkor
 * @since 2021/9/23
 */
public class Solution {

    /**
     * 暴力法（解答错误）
     * 从多个最大花费中，选择最小的一个。即极小化极大问题。
     */
    public int getMoneyAmount(int n) {
        if (n == 1) {
            return 0;
        }
        int maxMoney = Integer.MAX_VALUE;
        for (int i = 1; i < n + 1; i++) {
            int currMaxMoney = getMaxMoney(i, n);
            System.out.println(i + ": currMaxMoney = " + currMaxMoney);
            maxMoney = Math.min(maxMoney, currMaxMoney);
        }
        return maxMoney;
    }

    /**
     * 假设在 [1, n] 范围中，第一个猜的数字是 first 值，计算出最优路径下的最大花费
     *
     * 这里采用二分法，但是在部分场景下，计算结果是错误的。因为二分法并不是最优路径！
     * 推论：最优的 first 值不一定是由二分法得到的，那么选择了 first 后的下一个最优值，也不一定是二分法的结果。
     *
     * 总而言之，二分法只能保证查询的次数最少，不能保证查询的花费最少！！
     */
    private int getMaxMoney(int first, int n) {
        // 在右区间 [first + 1, n] 找最小值
        int rightMin = first;
        int left = first + 1;
        int right = n;
        while (left < right) {
            int mid = left + (right - left) / 2;
            rightMin += mid;
            left = mid + 1;
        }
        // 在左区间 [1, first - 1] 找最小值
        int leftMin = first;
        left = 1;
        right = first - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            leftMin += mid;
            left = mid + 1;
        }
        // 为了考虑最坏情况，需要左右区间的两个结果之间，选择最大值
        return Math.max(rightMin, leftMin);
    }

    /**
     * if n==1:return 0
     * elif n==2:return 1
     * elif n==3:return 2
     * elif n==4:return 4
     * elif n==5:return 6
     * elif n==6:return 8
     * elif n==7:return 10
     * elif n==8:return 12
     * elif n==9:return 14
     * elif n==10:return 16
     * elif n==16:return 34
     * elif n==18:return 42
     * elif n==25:return 64
     * elif n==45:return 144
     * elif n==56:return 198
     * elif n==63:return 226
     * elif n==77:return 282
     * elif n==83:return 310
     * elif n==94:return 365
     * elif n==109:return 454
     * elif n==115:return 494
     * elif n==120:return 529
     * elif n==139:return 630
     * elif n==141:return 640
     * elif n==151:return 698
     * elif n==160:return 743
     * elif n==176:return 823
     * elif n==183:return 858
     * elif n==191:return 898
     * elif n==200:return 952
     */
    @Test
    public void test() {
        int n = 10;
        int moneyAmount = getMoneyAmount(n);
        Assert.assertEquals(16, moneyAmount);

        n = 2;
        moneyAmount = getMoneyAmount(n);
        Assert.assertEquals(1, moneyAmount);

        n = 3;
        moneyAmount = getMoneyAmount(n);
        Assert.assertEquals(2, moneyAmount);

        n = 7;
        moneyAmount = getMoneyAmount(n);
        Assert.assertEquals(10, moneyAmount);

        n = 16;
        moneyAmount = getMoneyAmount(n);
        Assert.assertEquals(34, moneyAmount);
    }

    @Test
    public void getMaxMoney() {
        int money = getMaxMoney(5, 10);
        Assert.assertEquals(22, money);

        money = getMaxMoney(7, 10);
        Assert.assertEquals(16, money);

        money = getMaxMoney(9, 10);
        System.out.println("money = " + money);

        money = getMaxMoney(9, 16);
        System.out.println("money = " + money);
    }
}

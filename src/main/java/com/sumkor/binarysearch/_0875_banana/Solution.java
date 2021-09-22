package com.sumkor.binarysearch._0875_banana;

import org.junit.Test;

/**
 * @author Sumkor
 * @since 2021/9/22
 */
public class Solution {

    /**
     * 二分法（递归）
     *
     * 关于 maxSpeed 的取值：
     *
     * 1. 使用排序，取数组最大值
     * 执行用时：12 ms, 在所有 Java 提交中击败了63.56% 的用户
     * 内存消耗：39.8 MB, 在所有 Java 提交中击败了5.35% 的用户
     *
     * 2. 不使用排序，取题目最大值 10^9
     * 执行用时：6 ms, 在所有 Java 提交中击败了99.88% 的用户
     * 内存消耗：39.6 MB, 在所有 Java 提交中击败了51.34% 的用户
     */
    public int minEatingSpeed(int[] piles, int h) {
        int minSpeed = 1;
        int maxSpeed = 1_000_000_000;
        int ans;
        ans = recur(piles, h, minSpeed, maxSpeed);
        return ans;
    }

    private int recur(int[] piles, int h, int minSpeed, int maxSpeed) {
        if (minSpeed == maxSpeed) {
            return minSpeed;
        }
        int midSpeed = (minSpeed + maxSpeed) / 2;
        if (eatAtSpeed(piles, h, midSpeed)) {
            midSpeed = recur(piles, h, minSpeed, midSpeed); // 这里说明 mid 符合条件，是可能的结果，无法排除
        } else {
            midSpeed = recur(piles, h, midSpeed + 1, maxSpeed); // 这里说明 mid 不符合条件，那么结果肯定比 mid 更大才对，因此 mid 可以被排除掉
        }
        return midSpeed;
    }

    /**
     * 检查以 speed 速度吃香蕉是否合法
     */
    private boolean eatAtSpeed(int[] piles, int h, int speed) {
        int sum = 0;
        for (int pile : piles) {
            // 向上取整
            sum += (pile - 1) / speed + 1;
        }
        return sum <= h;
    }

    /**
     * 比较耗时！
     *
     * 执行用时：43 ms, 在所有 Java 提交中击败了8.09% 的用户
     * 内存消耗：39.6 MB, 在所有 Java 提交中击败了38.17% 的用户
     */
    private boolean eatAtSpeed1(int[] piles, int h, int speed) {
        int sum = 0;
        for (int pile : piles) {
            // 向上取整
            sum += Math.ceil(pile * 1.0 / speed);
        }
        return sum <= h;
    }

    /**
     * 超出时间限制！！
     */
    private boolean eatAtSpeed0(int[] piles, int h, int speed) {
        int count = 0;
        for (int i = 0; i < piles.length; i++) {
            int curr = 0;
            while (curr < piles[i]) {
                curr = curr + speed;
                count++;
            }
        }
        return count <= h;
    }

    @Test
    public void speed() {
//        int[] piles = new int[]{3, 6, 7, 11};
//        int h = 8;
//        int speed = 4;
//        int[] piles = new int[]{30, 11, 23, 4, 20};
//        int h = 5;
//        int speed = 30;
        int[] piles = new int[]{30, 11, 23, 4, 20};
        int h = 6;
        int speed = 23;
        boolean b = eatAtSpeed(piles, h, speed);
        System.out.println("b = " + b);
    }
}

package com.sumkor.binarysearch._0069_sqrtx;

import org.junit.Test;

/**
 * @author Sumkor
 * @since 2021/9/22
 */
public class Solution {

    /**
     * 二分法（基本模板）
     *
     * 在搜索区间 [left, right] 中找到一个 target 值，如果该值不存在，则返回一个比 target 小的最大值。
     *
     * 为什么返回结果是 left - 1 ？
     * 由于对 left 的更新是 left = mid + 1，就是说 while 循环结束时，left 一定不满足 ans < x 了，而 left - 1 可以满足。
     *
     * 时间复杂度：O(logx)，即为二分查找需要的次数。
     * 空间复杂度：O(1)。
     *
     * 执行用时：1 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗：35.5 MB, 在所有 Java 提交中击败了47.58% 的用户
     */
    public int mySqrt(int x) {
        long left = 0;
        long right = x;
        while (left <= right) {
            long mid = (right + left) / 2;
            long ans = mid * mid;
            if (ans == x) {
                return (int) mid;
            } else if (ans < x) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        // End Condition: left = right + 1
        return (int) left - 1;
    }

    /**
     * 二分法。各种调试后写出来的结果，不好理解。
     *
     * 执行用时：1 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗：35.5 MB, 在所有 Java 提交中击败了59.00% 的用户
     */
    public int mySqrt0(int x) {
        if (x == 0) {
            return 0;
        }
        long min = 1;
        long max = x;
        while (min + 1 < max) {
            long mid = (max + min) / 2;
            long ans = mid * mid;
            if (ans == x) {
                return (int) mid;
            } else if (ans < x) {
                min = mid;
            } else {
                max = mid;
            }
        }
        // End Condition: min + 1 == max
        return (int) min;
    }

    /**
     * 二分法，代码来源：
     * https://www.geekxh.com/1.9.%E4%BA%8C%E5%88%86%E6%B3%95%E7%B3%BB%E5%88%97/902.html#_02%E3%80%81%E4%BA%8C%E5%88%86%E6%9F%A5%E6%89%BE
     *
     * 不停缩小 mid 的范围，如果最终平方大于 x 就放回它前面一个值，否则就正常返回，直到两边的边界完全收敛。
     *
     * 执行用时：1 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗：35.5 MB, 在所有 Java 提交中击败了52.22% 的用户
     */
    public int mySqrt1(int x) {
        long min = 0;
        long max = x;
        while (min < max) {
            long mid = (max + min) / 2 + 1; // 略微增大搜索空间
            long ans = mid * mid;
            if (ans == x) {
                return (int) mid;
            } else if (ans < x) {
                min = mid;     // 保留
            } else {
                max = mid - 1; // 过滤
            }
        }
        // End Condition: min == max
        return (int) min;
    }

    /**
     * 输入：x = 4
     * 输出：2
     *
     * 输入：x = 8
     * 输出：2
     */
    @Test
    public void test() {
//        int x = 5;
//        int x = 8;
//        int x = 1;
//        int x = 0;
        int x = 2147395599;

        int pow = (int) Math.pow(x, 0.5);
        System.out.println("pow = " + pow);

        int ans = mySqrt(x);
        System.out.println("ans = " + ans);
    }
}

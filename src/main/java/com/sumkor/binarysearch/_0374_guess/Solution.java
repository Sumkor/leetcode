package com.sumkor.binarysearch._0374_guess;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Sumkor
 * @since 2021/9/23
 */
public class Solution extends GuessGame {

    /**
     * 二分法
     *
     * 1 <= pick <= n
     *
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗：35.4 MB, 在所有 Java 提交中击败了5.13% 的用户
     */
    public int guessNumber(int n) {
        int left = 1;
        int right = n;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (guess(mid) == 0) {
                return mid;
            }
            // pick > num
            else if (guess(mid) == 1) {
                left = mid + 1;
            }
            // pick < num
            else if (guess(mid) == -1) {
                right = mid - 1;
            }
        }
        return n;
    }

    /**
     * 提交结果中内存消耗超过 100% 的解答，采用了模板二的写法，但是实际执行内存消耗差不多。
     *
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗：35.3 MB, 在所有 Java 提交中击败了6.01% 的用户
     */
    public int guessNumber0(int n) {
        int l = 1, r = n;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (guess(mid) <= 0) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }

    @Test
    public void test() {
        int n = 10;
        int pick = 8;
        this.setPick(pick);

        int result = guessNumber(n);
        Assert.assertEquals(result, pick);
    }
}

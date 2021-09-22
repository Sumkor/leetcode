package com.sumkor.binarysearch._0875_banana;

/**
 * @author Sumkor
 * @since 2021/9/22
 */
public class Solution02 {

    /**
     * 官方题解
     * https://leetcode-cn.com/problems/koko-eating-bananas/solution/ai-chi-xiang-jiao-de-ke-ke-by-leetcode/
     *
     * 时间复杂度：O(N*logW)，这里 N 表示数组 piles 的长度，W 是数组的最大值 max(piles)。我们在 [1,max(piles)] 里使用二分查找定位最小速度，而每一次执行判别函数的时间复杂度是 O(N)
     * 空间复杂度：O(1)
     *
     * 执行用时：6 ms, 在所有 Java 提交中击败了99.88% 的用户
     * 内存消耗：39.5 MB, 在所有 Java 提交中击败了71.44% 的用户
     */
    public int minEatingSpeed(int[] piles, int H) {
        int lo = 1;
        int hi = 1_000_000_000;
        while (lo < hi) {
            int mi = (lo + hi) / 2;
            if (!possible(piles, H, mi))
                lo = mi + 1; // 这里说明 mid 不符合条件，那么结果肯定比 mid 更大才对，因此 mid 可以被排除掉
            else
                hi = mi; // 这里说明 mid 符合条件，因此 mid 是可能的结果，无法排除，但是有可能真正的结果比 mid 小
        }
        return lo;
    }

    // Can Koko eat all bananas in H hours with eating speed K?
    public boolean possible(int[] piles, int H, int K) {
        int time = 0;
        for (int p : piles)
            time += (p - 1) / K + 1;
        return time <= H;
    }

}


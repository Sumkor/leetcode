package com.sumkor.dp._0053_maxsubarray;

import org.junit.Test;

/**
 * @author Sumkor
 * @since 2021/12/7
 */
public class Solution02 {

    /**
     * 分治法
     * https://leetcode-cn.com/problems/maximum-subarray/solution/zui-da-zi-xu-he-by-leetcode-solution/
     *
     * 如果我们把 [0,n−1] 分治下去出现的所有子区间的信息都用堆式存储的方式记忆化下来，即建成一颗真正的树之后，
     * 我们就可以在 O(logn) 的时间内求到任意区间内的答案，我们甚至可以修改序列中的值，做一些简单的维护，
     * 之后仍然可以在 O(logn) 的时间内求到任意区间内的答案，对于大规模查询的情况下，这种方法的优势便体现了出来。
     * 这棵树就是上文提及的一种神奇的数据结构——线段树。
     *
     * 时间复杂度：O(n)。
     * 空间复杂度：O(logn)。
     */
    public int maxSubArray(int[] nums) {
        // 获取 [l,r] 内的最大子段和 mSum
        return getInfo(nums, 0, nums.length - 1).mSum;
    }

    /**
     * 对于一个区间 [l,r]，我们可以维护四个量：
     *
     * lSum 表示 [l,r] 内以 l 为左端点的最大子段和
     * rSum 表示 [l,r] 内以 r 为右端点的最大子段和
     * mSum 表示 [l,r] 内的最大子段和
     * iSum 表示 [l,r] 的区间和
     */
    public class Status {
        public int lSum, rSum, mSum, iSum;

        public Status(int lSum, int rSum, int mSum, int iSum) {
            this.lSum = lSum;
            this.rSum = rSum;
            this.mSum = mSum;
            this.iSum = iSum;
        }
    }

    public Status getInfo(int[] a, int l, int r) {
        // 对于长度为 1 的区间 [i,i]，四个量的值都和 nums[i] 相等。
        if (l == r) {
            return new Status(a[l], a[l], a[l], a[l]);
        }
        int m = (l + r) >> 1;
        Status lSub = getInfo(a, l, m);
        Status rSub = getInfo(a, m + 1, r);
        return pushUp(lSub, rSub);
    }

    // 通过 [l,m] 区间的信息和 [m+1,r] 区间的信息合并成区间 [l,r] 的信息
    public Status pushUp(Status l, Status r) {

        // iSum 表示 [l,r] 的区间和
        int iSum = l.iSum + r.iSum;

        // lSum 表示 [l,r] 内以 l 为左端点的最大子段和
        int lSum = Math.max(l.lSum, l.iSum + r.lSum);

        // rSum 表示 [l,r] 内以 r 为右端点的最大子段和
        int rSum = Math.max(r.rSum, r.iSum + l.rSum);

        // mSum 表示 [l,r] 内的最大子段和
        // 考虑 [l,r] 的 mSum 对应的区间是否跨越 m
        // 它可能不跨越 m，也就是说 [l,r] 的 mSum 可能是「左子区间」的 mSum 和「右子区间」的 mSum 中的一个
        // 它也可能跨越 m，可能是「左子区间」的 rSum 和 「右子区间」的 lSum 求和。
        // 三者取大。
        int mSum = Math.max(Math.max(l.mSum, r.mSum), l.rSum + r.lSum);

        return new Status(lSum, rSum, mSum, iSum);
    }

    /**
     * 输入：nums = [-2,1,-3,4,-1,2,1,-5,4]
     * 输出：6
     * 解释：连续子数组 [4,-1,2,1] 的和最大，为 6
     */
    @Test
    public void test() {
//        int[] nums = new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4};
        int[] nums = new int[]{-1, -2, 5, 1};
        int result = maxSubArray(nums);
        System.out.println("result = " + result);
    }
}

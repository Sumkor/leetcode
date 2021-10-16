package com.sumkor.math._0458_poorpigs;

/**
 * @author Sumkor
 * @since 2021/10/16
 */
public class Solution {

    /**
     * 官方题解
     * https://leetcode-cn.com/problems/poor-pigs/solution/ke-lian-de-xiao-zhu-by-leetcode/
     *
     * 找规律：
     *     如果 minutesToTest < minutesToDie，说明即使给小猪喝水都不能测出是否有农药，即「等待的时间不足以判断农药是否发作」，那么经过 minutesToTest 时间以后，每一只猪只有一种状态，即存活；
     *     如果 minutesToTest / minutesToDie = 1，说明只能测一次；
     *     如果 minutesToTest / minutesToDie = 2，说明只能测两次。
     *
     * 只能测一次的时候，当然小猪有可能存活，也有可能在只测一次以后死亡。
     * 只能测两次的时候，当然小猪有可能存活，也有可能在只测一次以后死亡，也有可能在只测两次以后死亡，以此类推。
     *
     * 因此每 一只小猪 的「状态」数量为 states = minutesToTest / minutesToDie + 1，
     * 这里「状态」的意思是：在可以测试的次数的范围内，一只小猪可能测试出现的状态。
     *
     * -----------------------------------
     *
     * 下面我们分析 x 只 2 种状态（只能测试 1 次的情况下）的猪最多可以测试多少个水桶。
     *
     * 在只能测试一次的情况下，1 只猪可以测试 2 个水桶
     * 方案：这是因为让它喝下桶 1 的水，如果存活说明 2 号桶有农药，如果死亡说明 1 号桶有农药。
     *
     * 只能测试一次的情况下，2 只猪可以测试 4 个水桶
     * 方案：1 号猪喝 1、2 号水；
     *      2 号猪喝 2、3 号水。
     * 解答：如果 1 死 2 活，说明 1 号水有毒。
     *      如果 1 活 2 死，说明 3 号水有毒。
     *      如果 1 活 2 活，说明 4 号水有毒。
     *      如果 1 死 2 死，说明 2 号水有毒。
     *
     * 只能测试一次的情况下，3 只猪可以测试 8 个水桶
     * 方案：1 号猪喝 1、2、3、4 号水；
     *      2 号猪喝 3、4、5、6 号水；
     *      3 号猪喝 1、3、5、7 号水。
     *
     * 更一般地，可以归纳出 x 只 2 种状态可以测试 2^x 个水桶。
     *
     * -----------------------------------
     *
     * x 只 s 状态的猪（只能测试 s−1 次的情况下）最多可以测试多少个水桶。
     * 答案是 s^x
     *
     * 下图给出了 x = 1，s = 3 的例子：
     * 方案：在 t0 时刻投喂小猪，如果死亡说明 1 号水有毒。
     *      在 t1 时刻投喂小猪，如果死亡说明 2 号水有毒。
     *      两次测试都没有死亡，说明 3 号水有毒。
     *
     * 因此我们需要找到最小的 x，使得 states^x ≥ buckets，其中 states = minutesToTest / minutesToDie + 1 表示每只猪的状态数。
     * 因此答案为 x ≥ log(states)buckets。
     * 根据换底公式，我们可以得到：x ≥ logbuckets / logstates
     *
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗：35.3 MB, 在所有 Java 提交中击败了33.62% 的用户
     *
     * 时间复杂度：O(1)。
     * 空间复杂度：O(1)。
     */
    public int poorPigs(int buckets, int minutesToDie, int minutesToTest) {
        int states = minutesToTest / minutesToDie + 1;
        return (int) Math.ceil(Math.log(buckets) / Math.log(states));
    }

    /**
     * 进制思路
     * https://leetcode-cn.com/problems/poor-pigs/solution/leetcode-458-njin-zhi-si-xiang-by-xiaok0-819a/
     *
     * 本质上是一个数字进制表示的问题.
     *
     * 先来考虑一种最简单的情况，buckets = 1000, minutesToDie = 15, minutesToTest = 15，
     * 此时每只小猪都只能喝1次液体（再喝时间就不够了），每个小猪喝完若干瓶液体后只能出现两种状态，要么死亡，要么存活.
     *
     * 我们给这 1000 瓶液体分别标上一个唯一编号 0-999，由于 2^10 > 1000 > 2^9，所以每瓶液体都对应着唯一的一个长度为10的二进制串.
     * 我们只需要10只小猪，让每个小猪负责一个二进制位即可.
     * 例如第一只小猪负责二进制串的最低位，那么它就需要喝掉所有二进制最低位为1的液体，如果这只小猪最后死亡，说明有毒液体的编号二进制最低位为1；否则小猪存活，有毒液体的编号二进制最低位为0.
     * 这样一来，一只小猪就可以确定一个二进制位的取值，使用10只小猪就能完全确定有毒液体的编号.
     *
     * 来考虑更一般的情况，buckets = 1000, minutesToDie = 15, minutesToTest = 60，此时每只小猪可以喝4次液体，
     * 在时间限制范围内，小猪可能出现的状态共有5种，分别为：喝完第1次后死亡、喝完第2次后死亡、喝完第3次后死亡、喝完第4次后死亡、喝完4次后依然存活.
     * 现在每只小猪可以表示5种状态了，而不是之前的2种，那么我们就可以将瓶子的编号转换成五进制数考虑.
     *
     * 我们依然给这 1000 瓶液体分别标上一个唯一编号 0-999，由于 5^5 > 1000 > 5^4，所以每瓶液体都对应着唯一的一个长度为5的五进制串.
     * 我们只需要5只小猪，让每个小猪负责一个五进制位即可.
     * 例如第一只小猪负责五进制串的最低位，那么它第一次先喝掉五进制最低位为1的液体，第二次喝掉五进制最低位为2的液体，第三次喝掉五进制最低位为3的液体，第四次喝掉五进制最低位为4的液体.
     * 在这一过程中，如果这只小猪某次喝完后死亡，就可以立马确定有毒液体五进制的最低位取值，如果喝完四次后仍存活，说明有毒液体五进制的最低位为0.
     * 这样一来，一只小猪就可以确定一个五进制位的取值，使用5只小猪就能完全确定有毒液体的编号.
     *
     * -----------------------------------
     *
     * 将问题的解抽象成更一般的数学公式如下：
     *
     *     base = minutesToTest / minutesToDie + 1
     *
     * base 表示对应的是几进制数
     *
     *     base^x ≥ buckets
     *
     * 将第二个式子左右两端同时取对数，即可得到答案：
     *
     *     x ≥ log(base)buckets = logbuckets / logbase
     *
     * 作者：xiaok0707
     * 链接：https://leetcode-cn.com/problems/poor-pigs/solution/leetcode-458-njin-zhi-si-xiang-by-xiaok0-819a/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
}

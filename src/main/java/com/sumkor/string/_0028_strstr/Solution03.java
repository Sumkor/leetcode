package com.sumkor.string._0028_strstr;

/**
 * @author Sumkor
 * @since 2021/8/1
 */
public class Solution03 {

    /**
     * KMP 算法
     * https://leetcode-cn.com/problems/implement-strstr/solution/zhe-ke-neng-shi-quan-wang-zui-xi-de-kmp-8zl57/
     *
     * 主串   bcbcbdbcbe
     * 模式串 bcbcbea
     *
     * 第一次对比，发现主串下标 5 位置的字符不匹配
     *     bcbcbdbcbe
     *     bcbcbea
     *          ↑
     *
     * 对于模式串中已匹配成功的部分：bcbcb
     * 其前缀子串为：b, bc, bcb, bcbc
     * 其后缀子串为：b, cb, bcb, cbcb
     * 可知其最长公共前后缀为 bcb
     *
     * 因此第二次对比，依旧从主串的 5 位置进行对比，模式串移动如下：
     *     bcbcbdbcbe
     *       bcbcbea
     *          ↑
     *
     * 对于模式串中已匹配成功的部分：bcb
     * 可知其可知其最长公共前后缀为 b
     *
     * 因此第二次对比，依旧从主串的 5 位置进行对比，模式串移动如下：
     *     bcbcbdbcbe
     *         bcbcbea
     *          ↑
     *
     * 此时，主串的剩余子串（dbcbe）比模式串的剩余子串（cbcbea）还短，直接退出。
     *
     * 执行用时：4 ms, 在所有 Java 提交中击败了52.79% 的用户
     * 内存消耗：38.3 MB, 在所有 Java 提交中击败了47.11% 的用户
     */
    public int strStr(String haystack, String needle) {
        // 两种特殊情况
        if (needle.length() == 0) {
            return 0;
        }
        if (haystack.length() == 0) {
            return -1;
        }
        // char 数组
        char[] aArray = haystack.toCharArray();
        char[] bArray = needle.toCharArray();
        // 长度
        int aLength = aArray.length;
        int bLength = bArray.length;
        // 返回下标
        return kmp(aArray, aLength, bArray, bLength);

    }

    /**
     * KMP 算法
     */
    public int kmp(char[] aArray, int aLength, char[] bArray, int bLength) {
        // 获取 next 数组
        int[] next = next(bArray, bLength);
        // 定义 j 为模式串上的指针，i 为主串上的指针
        int j = 0;
        for (int i = 0; i < aLength; ++i) {
            // 若字符不匹配，则根据 next 数组移动指针 j，将其移动到【最大公共前后缀】的前缀子串的后一位
            while (j > 0 && aArray[i] != bArray[j]) {
                // i 与 j 位置上的字符不匹配，但是 i-1 与 j-1 的是匹配的，因此查询模式串的 [0, j-1] 范围的最大公共前后缀，获取 j 指针的新位置
                j = next[j - 1] + 1;
                // 超出长度时，可以直接返回不存在（主串的剩余子串 比 模式串的剩余子串 还短，直接退出）
                if (bLength - j > aLength - i) {
                    return -1;
                }
            }
            // 如果相同，则两个指针同时后移一位，比较下个字符
            if (aArray[i] == bArray[j]) {
                ++j;
            }
            // 遍历完整个模式串，返回模式串的起点下标
            if (j == bLength) {
                return i - bLength + 1;
            }
        }
        return -1;
    }

    /**
     * 获取 next 数组
     * https://leetcode-cn.com/problems/implement-strstr/solution/zhe-ke-neng-shi-quan-wang-zui-xi-de-kmp-8zl57/
     *
     * 这里的模式串 bcbcbea，得到 next 数组为：-1,-1,0,1,2,0,0
     * 注：很多教科书的 next 数组表示方式不一致，理解即可
     *
     *        b  c  b  c  b  e  a
     * index: 0  1  2  3  4  5  6 （前缀结尾下标）
     * next: -1 -1  0  1  2  0  0 （最长公共前后缀，其中的最长前缀子串结尾字符的下标）
     *
     *
     * 获取 next 数组
     * https://leetcode-cn.com/problems/implement-strstr/solution/shua-chuan-lc-shuang-bai-po-su-jie-fa-km-tb86/
     */
    public int[] next(char[] array, int length) {
        // 定义 next 数组
        int[] next = new int[length];
        next[0] = -1;
        int j = -1;
        // next 数组的下标 0 位置的值总是 -1，因此这里只需从下标 1 位置开始构造 next 数组
        for (int i = 1; i < length; ++i) {
            // 本次循环开始，已经知道了模式串的 [0,i-1] 范围的最长前后缀，而 next 数组就是用来记录子串的最长公共前后缀的尾坐标
            // 如果匹配不成功，则需要找到 j+1 的前一个字符在 next 数组里的值
            while (j != -1 && array[j + 1] != array[i]) {
                j = next[j];
            }
            // 如果匹配成功，先让 j++
            if (array[j + 1] == array[i]) {
                ++j;
            }
            // 更新 next[i]，结束本次循环，i++
            next[i] = j;
        }
        return next;
    }
}

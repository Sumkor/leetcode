package com.sumkor.string._0028_strstr;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author Sumkor
 * @since 2021/8/1
 */
public class Solution03 {

    /**
     * KMP 算法
     *
     * 【动画模拟】这可能是全网最细的 KMP 讲解！（BF,BM,KMP）
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
     * 发现主串下标 5 位置的字符依旧不匹配
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
     *
     * 时间复杂度：O(n+m)，其中 n 是字符串 haystack 的长度，m 是字符串 needle 的长度。我们至多需要遍历两字符串一次。
     * 空间复杂度：O(m)，其中 m 是字符串 needle 的长度。我们只需要保存字符串 needle 的前缀函数。
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
            // 注意，只有当 i 和 j 位置的字符曾经发生过匹配时（这样才会满足 j > 0）才需要回溯 j 指针
            while (j > 0 && aArray[i] != bArray[j]) {
                // i 与 j 位置上的字符不匹配，但是可知模式串的 [0, j-1] 范围的字符是匹配的，因此查询模式串的 [0, j-1] 范围的最大公共前后缀，以获取 j 指针的新位置
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
     * 例如模式串为 bcbcbea，得到 next 数组为：-1,-1,0,1,2,-1,-1
     * 注：很多教科书的 next 数组表示方式不一致，理解即可。
     *
     *         b  c  b  c  b  e  a
     * index:  0  1  2  3  4  5  6 （前缀结尾下标）
     * next1: -1 -1  0  1  2 -1 -1 （最长公共前后缀，其中的最长前缀子串结尾字符的下标）
     * next2:  0  0  1  2  3  0  0 （最长公共前后缀，的长度）
     *
     * 这里 next1 和 next2 都是正确的 next 数组，本例中是 next1 实现。
     *
     *                        i
     *         b  c  b  c  b  b  a
     *                  j
     */
    public int[] next(char[] array, int length) {
        // 定义 next 数组
        int[] next = new int[length];
        next[0] = -1;
        // j 从头开始遍历 array
        int j = 0;
        // next 数组的下标 0 位置的值总是 -1，因此 i 只需从下标 1 位置开始遍历 array，以构造 next 数组
        for (int i = 1; i < length; ++i) {
            // 注意，只有当 i 和 j 位置的字符曾经发生过匹配时（这样才会满足 j != 0），才会进入以下 while 逻辑
            // 这里判断 i 和 j 位置的字符不相等，需要将指针 j 回溯到 next[j - 1] + 1 的位置再进行对比，一直回溯，直到 j == 0 则跳出循环
            while (j != 0 && array[j] != array[i]) {
                // 此时 i 和 j 位置的字符不相等，但是对于 j - 1 位置的字符，可能存在与之相匹配的！！（建议代入例子来理解，如模式串：bcbcbba，当 i = 5，j = 3）
                // 与之匹配的分别是 i - 1 位置和 next[j - 1] 位置的字符，因此，将 i - 1 位置与 next[j - 1] 位置对齐，下一次可以直接比较 i 位置与 next[j - 1] + 1 位置的字符是否相等
                // 这样可以避免重复比较 next[j - 1] 位置之前的字符
                j = next[j - 1] + 1;
            }
            // 走到这里，分为两种情况：
            // i 和 j 位置的字符相等，则利用 next[i] 记录当前的下标 j，后续会继续比较 i+1 与 j+1 位置的字符是否相等
            // i 和 j 位置的字符不相等，此时 j 只能是 0，即 next[i] 赋值为 -1
            if (array[j] == array[i]) {
                next[i] = j;
                j++;
            } else {
                next[i] = next[0];
            }
        }
        return next;
    }

    /**
     * 获取 next 数组
     *
     *         b  c  b  c  b  e  a
     * index:  0  1  2  3  4  5  6 （前缀结尾下标）
     * next1: -1 -1  0  1  2 -1 -1 （最长公共前后缀，其中的最长前缀子串结尾字符的下标）
     * next2:  0  0  1  2  3  0  0 （最长公共前后缀，的长度）
     *
     * 这里 next1 和 next2 都是正确的 next 数组，本例中是 next2 实现。
     */
    public int[] next2(char[] array, int length) {
        int[] next = new int[length];
        next[0] = 0;
        int j = 0;
        for (int i = 1; i < length; ++i) {
            while (j != 0 && array[j] != array[i]) {
                j = next[j - 1];
            }
            if (array[j] == array[i]) {
                next[i] = j + 1;
                j++;
            } else {
                next[i] = next[0];
            }
        }
        return next;
    }


    /**
     * 关于 next 数组与 pmt 数组
     *
     * next表 在最初始位置补 -1，或者甚至干脆把 pmt 的第一位补一个 -1 当作 next表，这都统统是可以的。
     * 因为最关键的还是说你到时候怎么去使用！毕竟 next表 的定义也是人们给它赋予的！
     * https://www.geekxh.com/1.3.%E5%AD%97%E7%AC%A6%E4%B8%B2%E7%B3%BB%E5%88%97/306.html
     */
    @Test
    public void next() {
//        String needle = "bcbcbea";
//        String needle = "bcbcbba";
//        String needle = "abcdabcc";
        String needle = "xxyxxyxxx";
//        String needle = "abaabcac";
        int[] next = next(needle.toCharArray(), needle.length());
        Arrays.stream(next).forEach(t -> System.out.print(t + " "));

        System.out.println();
        int[] next2 = next2(needle.toCharArray(), needle.length());
        Arrays.stream(next2).forEach(t -> System.out.print(t + " "));
    }

    @Test
    public void test() {
        String haystack = "hello"; String needle = "ll";
        int result = strStr(haystack, needle);
        System.out.println("result = " + result);

        int test = haystack.indexOf(needle);
        System.out.println("test = " + test);
    }
}

package com.sumkor.backtrack._0093_restoreip;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Sumkor
 * @since 2021/9/16
 */
public class Solution03 {

    /**
     * 官方题解
     * https://leetcode-cn.com/problems/restore-ip-addresses/solution/fu-yuan-ipdi-zhi-by-leetcode-solution/
     *
     * 设题目中给出的字符串为 s。我们用递归函数 dfs(segId, segStart) 表示我们正在从 s[segStart] 的位置开始，搜索 IP 地址中的第 segId 段，其中 segId∈{0,1,2,3}。
     * 由于 IP 地址的每一段必须是 [0,255] 中的整数，因此我们从 segStart 开始，从小到大依次枚举当前这一段 IP 地址的结束位置 segEnd。
     * 如果满足要求，就递归地进行下一段搜索，调用递归函数 dfs(segId+1,segEnd+1)。
     *
     * 特别地，由于 IP 地址的每一段不能有前导零，因此如果 s[segStart] 等于字符 0，那么 IP 地址的第 segId 段只能为 0，需要作为特殊情况进行考虑。
     *
     * 在搜索的过程中，如果我们已经得到了全部的 4 段 IP 地址（即 segId=4），并且遍历完了整个字符串（即 segStart=∣s∣，其中 ∣s∣ 表示字符串 s 的长度），
     * 那么就复原出了一种满足题目要求的 IP 地址，我们将其加入答案。在其它的时刻，如果提前遍历完了整个字符串，那么我们需要结束搜索，回溯到上一步。
     *
     * 我们用 SEG_COUNT=4 表示 IP 地址的段数。
     *
     * 时间复杂度：O(3^SEG_COUNT×∣s∣)。
     * 由于 IP 地址的每一段的位数不会超过 3，因此在递归的每一层，我们最多只会深入到下一层的 3 种情况。
     * 由于 SEG_COUNT=4，对应着递归的最大层数，所以递归本身的时间复杂度为 O(3^SEG_COUNT)。
     * 如果我们复原出了一种满足题目要求的 IP 地址，那么需要 O(∣s∣) 的时间将其加入答案数组中，因此总时间复杂度为 O(3^SEG_COUNT×∣s∣)。
     *
     * 空间复杂度：O(SEG_COUNT)。
     * 这里只计入除了用来存储答案数组以外的额外空间复杂度。递归使用的空间与递归的最大深度 SEG_COUNT 成正比。
     * 并且在上面的代码中，我们只额外使用了长度为 SEG_COUNT 的数组 segments 存储已经搜索过的 IP 地址，因此空间复杂度为 O(SEG_COUNT)。
     *
     * 执行用时：1 ms, 在所有 Java 提交中击败了96.00% 的用户
     * 内存消耗：37 MB, 在所有 Java 提交中击败了90.71% 的用户
     */
    public List<String> restoreIpAddresses(String s) {
        segments = new int[SEG_COUNT];
        dfs(s, 0, 0);
        return ans;
    }

    static final int SEG_COUNT = 4;
    List<String> ans = new ArrayList<String>();
    int[] segments = new int[SEG_COUNT];

    /**
     * @param s        入参字符串
     * @param segId    当前所在分段序号
     * @param segStart 入参字符串的下标
     */
    public void dfs(String s, int segId, int segStart) {
        // 如果找到了 4 段 IP 地址并且遍历完了字符串，那么就是一种答案
        if (segId == SEG_COUNT) {
            if (segStart == s.length()) {
                StringBuilder ipAddr = new StringBuilder();
                for (int i = 0; i < SEG_COUNT; ++i) {
                    ipAddr.append(segments[i]);
                    if (i != SEG_COUNT - 1) {
                        ipAddr.append('.');
                    }
                }
                ans.add(ipAddr.toString());
            }
            return;
        }

        // 如果还没有找到 4 段 IP 地址就已经遍历完了字符串，那么提前回溯
        if (segStart == s.length()) {
            return;
        }

        // 由于不能有前导零，如果当前数字为 0，那么这一段 IP 地址只能为 0
        if (s.charAt(segStart) == '0') {
            segments[segId] = 0;
            dfs(s, segId + 1, segStart + 1);
        }

        // 一般情况，枚举每一种可能性并递归
        int addr = 0;
        for (int segEnd = segStart; segEnd < s.length(); ++segEnd) {
            addr = addr * 10 + (s.charAt(segEnd) - '0');
            if (addr > 0 && addr <= 0xFF) {
                segments[segId] = addr;
                dfs(s, segId + 1, segEnd + 1);
            } else {
                break;
            }
        }
    }

    /**
     * 输入：s = "25525511135"
     * 输出：["255.255.11.135","255.255.111.35"]
     *
     * 输入：s = "010010"
     * 输出：["0.10.0.10","0.100.1.0"]
     *
     * 输入：s = "101023"
     * 输出：["1.0.10.23","1.0.102.3","10.1.0.23","10.10.2.3","101.0.2.3"]
     */
    @Test
    public void test() {
//        String s = "25525511135";
//        String s = "010010";
        String s = "101023";
        List<String> strings = restoreIpAddresses(s);
        System.out.println("strings = " + strings);
    }
}

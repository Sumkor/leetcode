package com.sumkor.backtrack._0093_restoreip;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Sumkor
 * @since 2021/9/16
 */
public class Solution02 {

    /**
     * 回溯算法（优化版本）
     *
     * 参考官方题解，将每一次选择路径 path 的数据类型由 LinkedList 改为数组，可以减少回溯导致的耗时
     *
     * 执行用时：1 ms, 在所有 Java 提交中击败了96.00% 的用户
     * 内存消耗：37.1 MB, 在所有 Java 提交中击败了87.60% 的用户
     */
    public List<String> restoreIpAddresses(String s) {
        List<String> ans = new ArrayList<>();
        char[] chars = s.toCharArray();
        int[] path = new int[4];
        dfs(chars, 0, 0, path, ans);
        return ans;
    }

    private void dfs(char[] chars, int index, int seg, int[] path, List<String> ans) {
        // 已经选择了 3 个数字，判断最后的数字能否组成合法ip
        if (seg == 3) {
            // 没有剩余数字，不合法
            if (index >= chars.length) {
                return;
            }
            // 剩余多个数字，以 0 开头，不合法
            if (index != chars.length - 1 && chars[index] == '0') {
                return;
            }
            // 超长，不合法
            if (chars.length - index > 3) {
                return;
            }
            char[] lastPath = Arrays.copyOfRange(chars, index, chars.length);
            int lastPathInt = convert(lastPath);
            if (lastPathInt < 0 || lastPathInt > 255) {
                return;
            }
            // 组装ip
            path[seg] = lastPathInt;
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < path.length; i++) {
                sb.append(path[i]);
                if (i != 3) {
                    sb.append('.');
                }
            }
            ans.add(sb.toString());
            return;
        }
        // 每次只能取 1 到 3 个数字
        for (int i = 1; i <= 3; i++) {
            // 剪枝：当前选择已经过界
            if (index + i > chars.length) {
                continue;
            }
            char[] subPath = Arrays.copyOfRange(chars, index, index + i);
            // 剪枝：不合法的数值
            int subPathInt = convert(subPath);
            if (subPathInt < 0 || subPathInt > 255) {
                continue;
            }
            // 做出选择
            path[seg] = subPathInt;
            // 进入递归
            dfs(chars, index + subPath.length, seg + 1, path, ans);
            // 剪枝：当前尝试已经到头，不必进入下一次循环
            if (subPathInt == 0 || subPathInt == 255) {
                i = 4;
            }
        }
    }

    /**
     * 将字符数组转换为整型
     */
    private int convert(char[] chars) {
        int result = 0;
        for (int i = 0; i < chars.length; i++) {
//           result += (chars[i] - '0') * Math.pow(10, (chars.length - 1 - i)); // 这种计算方式，不会溢出！！
            result = (chars[i] - '0') + result * 10;
        }
        return result;
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
     *
     * "2736786374048"
     * []
     */
    @Test
    public void test() {
//        String s = "25525511135";
//        String s = "2736786374048";
//        String s = "010010";
        String s = "101023";
        List<String> strings = restoreIpAddresses(s);
        System.out.println("strings = " + strings);
    }

    @Test
    public void convertTest() {
//        char[] chars = new char[]{'0', '2', '3'};
        char[] chars = new char[]{'6', '7', '8', '6', '3', '7', '4', '0', '4', '8'};
        int convert = convert(chars);
        System.out.println("convert = " + convert);
    }
}

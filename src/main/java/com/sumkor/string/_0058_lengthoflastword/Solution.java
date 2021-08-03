package com.sumkor.string._0058_lengthoflastword;

import org.junit.Test;

/**
 * @author Sumkor
 * @since 2021/8/3
 */
public class Solution {

    /**
     * 题中的陷阱在于，结尾处仍然可能有空格。
     *
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗：36.7 MB, 在所有 Java 提交中击败了50.85% 的用户
     */
    public int lengthOfLastWord(String s) {
        int count = 0;
        int length = s.length();
        char[] array = s.toCharArray();
        boolean readWord = false;
        for (int i = length - 1; i >= 0; i--) {
            if (array[i] == ' ') {
                // 没有读过单词之前，读到空格，则继续往下读
                if (!readWord) {
                    continue;
                }
                break;
            } else {
                readWord = true;
                count++;
            }
        }
        return count;
    }

    @Test
    public void test() {
//        String s = "Hello World";
//        String s = "  ";
//        String s = "Hello World ";
        String s = "Hello  World  ";
        int lengthOfLastWord = lengthOfLastWord(s);
        System.out.println("lengthOfLastWord = " + lengthOfLastWord);
    }
}

package com.sumkor.linked._0438_findanagrams;

import com.sumkor.FileHelper;
import org.junit.Test;

import java.util.*;

/**
 * @author Sumkor
 * @since 2021/8/9
 */
public class Solution {

    /**
     * 暴力 + 哈希表
     *
     * 执行结果：超出时间限制
     */
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> result = new ArrayList<>();
        int pLength = p.length();
        int sLength = s.length();
        if (pLength > sLength) {
            return result;
        }
        // 由字符串 p 构造哈希表，key=字符，value=次数
        char[] pArray = p.toCharArray();
        HashMap<Character, Integer> pMap = new HashMap<>();
        for (int i = 0; i < pLength; i++) {
            pMap.put(pArray[i], pMap.getOrDefault(pArray[i], 0) + 1);
        }
        // 遍历字符串 s，寻找字母异位子串
        char[] sArray = s.toCharArray();
        for (int i = 0; i < sLength - pLength + 1; i++) {
            boolean success = true;
            char[] subArray = Arrays.copyOfRange(sArray, i, i + pLength);
            HashMap<Character, Integer> subMap = new HashMap<>();
            for (int j = 0; j < pLength; j++) {
                if (!pMap.containsKey(subArray[j])) {
                    success = false;
                    break;
                } else {
                    subMap.put(subArray[j], subMap.getOrDefault(subArray[j], 0) + 1);
                }
            }
            // 每一个字母的次数必须与 p 相同
            if (subMap.size() != pMap.size()) {
                success = false;
            } else {
                for (int j = 0; j < pLength; j++) {
                    if (!pMap.get(pArray[j]).equals(subMap.get(pArray[j]))) {
                        success = false;
                        break;
                    }
                }
            }
            if (success) {
                result.add(i);
            }
        }
        return result;
    }


    /**
     * 输入: s: "cbaebabacd" p: "abc"
     * 输出: [0, 6]
     */
    @Test
    public void test() {
        String s = "cbaebabacd";
        String p = "abc";

        List<Integer> list = findAnagrams(s, p);
        System.out.println("list = " + list);
    }

    /**
     * 输入: s: "abab" p: "ab"
     * 输出: [0, 1, 2]
     */
    @Test
    public void test02() {
        String s = "abab";
        String p = "ab";

        List<Integer> list = findAnagrams(s, p);
        System.out.println("list = " + list);
    }

    /**
     * 输入: "ababababab" "aab"
     * 输出: [0,2,4,6]
     */
    @Test
    public void test03() {
        String s = "ababababab";
        String p = "aab";

        List<Integer> list = findAnagrams(s, p);
        System.out.println("list = " + list);
    }

    @Test
    public void testLong() {
        String s = FileHelper.readFileToString("com\\sumkor\\linked\\_0438_findanagrams\\inputS.txt");
        String p = FileHelper.readFileToString("com\\sumkor\\linked\\_0438_findanagrams\\inputP.txt");

        System.out.println("s.length() = " + s.length());
        System.out.println("p.length() = " + p.length());

        long start = System.currentTimeMillis();
        List<Integer> list = findAnagrams(s, p);
        System.out.println("ms:" + (System.currentTimeMillis() - start));
        System.out.println("list = " + list);
    }
}

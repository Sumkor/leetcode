package com.sumkor;

import org.apache.commons.io.Charsets;
import org.apache.commons.io.IOUtils;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

/**
 * @author Sumkor
 * @since 2021/8/9
 */
public class FileHelper {

    /**
     * 读取文件内容为字符串
     */
    public static String readFileToString(String filePath) {
        InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(filePath);
        assert inputStream != null;
        String result = null;
        try {
            result = IOUtils.toString(inputStream, Charsets.toCharset("UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 读取文件内容为数组
     */
    public static int[] readFileToArray(String filePath) {
        String input = readFileToString(filePath);
        // 转换
        String[] split = input.split(",");
        int[] result = new int[split.length];
        for (int i = 0; i < split.length; i++) {
            result[i] = Integer.parseInt(split[i]);
        }
        return result;
    }

    @Test
    public void readFileToString() {
        String result = FileHelper.readFileToString("com/sumkor/array/_0015_threesum/input.txt");
        System.out.println("result = " + result);
    }

    @Test
    public void readFileToArray() {
        int[] result = FileHelper.readFileToArray("com/sumkor/array/_0015_threesum/input.txt");
        Arrays.stream(result).forEach(t -> System.out.print(t + " "));
    }
}

package longest;

import java.util.Scanner;

/**
 * 最长子字符串的长度（一）
 *
 * 【最长子字符串的长度(一)】给你一个字符串s,字符串s首尾相连成一个环形，请你在环中找出'o'字符出现了偶数次最长子字符串的长度。
 * 输入描述：
 *  输入是一串小写字母组成的字符串
 * 输出描述：
 *  输出是一个整数
 * 备注：
 *  1<=s.length<=5×10^5
 *  s只包含小写英文字母。
 * 示例1:
 * 输入
 *  alolobo
 * 输出
 *  6
 */
public class longestStringOne {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        char[] inputHolder = in.nextLine().toCharArray();

        processer(inputHolder);
    }

    public static void processer(char[] inputHolder) {

    }
}

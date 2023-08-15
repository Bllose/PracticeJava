package string;

import java.util.Scanner;

/**
 * 题目描述：
 *  给定字符串A、B和正整数V，A的长度与B的长度相等， 请计算A中满足如下条件的最大连续子串的长度：
 *      1、该连续子串在A和B中的位置和长度均相同。
 *      2、该连续子串|A[i] – B[i]|之和小于等于V。其中|A[i] – B[i]|表示两个字母ASCII码之差的绝对值。
 *
 * 输入描述：
 *  输入为三行：
 *      第一行为字符串A，仅包含小写字符，1 <= A.length <=1000。
 *      第二行为字符串B，仅包含小写字符，1 <= B.length <=1000。
 *      第三行为正整数V，0<= V <= 10000。
 *
 * 输出描述：
 *  字符串最大连续子串的长度，要求该子串|A[i] – B[i]|之和小于等于V。
 *
 * 案例：
 *  输入：
 *      xxcdefg
 *      cdefghi
 *      5
 *  输出：
 *      2
 */
public class LongestSubString {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int[] String1 = in.nextLine().chars().toArray();
        int[] String2 = in.nextLine().chars().toArray();
        int MAX = Integer.parseInt(in.nextLine());

        processer(String1, String2, MAX);
    }

    public static void processer(int[] one, int[] two, int MAX) {
        int[] holder = new int[one.length];
        for(int i = 0; i < one.length; i ++) {
            holder[i] = Math.abs(one[i] - two[i]);
        }

        int i = 0;
        int j = 0;
        int curSum = holder[0];
        int recorder = 1;
        int longest = 0;
        while(i < one.length && j < one.length) {
            if(curSum > MAX) {
                curSum -= holder[i];
                i ++;
                recorder --;
            } else {
                j ++;
                if(j >= one.length) {
                    break;
                }
                curSum += holder[j];
                recorder ++;
                if(curSum <= MAX) {
                    longest = Math.max(longest, recorder);
                }
            }
        }

        System.out.println(longest);
    }
}

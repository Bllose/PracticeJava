package dp;

import java.util.Scanner;

/**
 * 题目：
 * 给定一个含有N个正整数的数组, 求出有多少个连续区间（包括单个正整数）, 它们的和大于等于x。
 *
 * 输入描述：
 * 第一行两个整数N x（0 < N <= 100000, 0 <= x <= 10000000)
 * 第二行有N个正整数（每个正整数小于等于100)。
 *
 * 输出描述：
 * 输出一个整数，表示所求的个数。
 *
 * 用例1：
 *  输入：
 *      3 7
 *      3 4 7
 *  输出：
 *      4
 */
public class ContinueSum {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int LEN = in.nextInt();
        int TARGET = in.nextInt();
        int[] inputHolder = new int[LEN];
        for(int i = 0 ; i < LEN; i ++) {
            inputHolder[i] = in.nextInt();
        }

        processer(inputHolder, TARGET, LEN);
    }

    public static void processer(int[] input, int TARGET, int LEN) {
        int result = 0;
        for(int i = 0; i < LEN; i ++) {
            int counter = input[i];
            for(int j = i + 1; j < LEN; j ++) {
                counter += input[j];
                if(counter >= TARGET) {
                    result += LEN - j;
                    break;
                }
            }
            if(input[i] >= TARGET) {
                result ++;
            }
        }
        System.out.println(result);
    }
}

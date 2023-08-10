package dp;

import java.util.Scanner;

/**
 * 题目描述：
 *  给定一个二维整数矩阵，要在这个矩阵中选出一个子矩阵，
 *  使得这个子矩阵内所有的数字和尽量大，我们把这个子矩阵称为和最大子矩阵，
 *  子矩阵的选取原则是原矩阵中一块相互连续的矩形区域。
 * 输入描述：
 *  输入的第一行包含2个整数n, m(1 <= n, m <= 10)，表示一个n行m列的矩阵，下面有n行，
 *  每行有m个整数，同一行中，每2个数字之间有1个空格，最后一个数字后面没有空格，所有的数字的在[-1000, 1000]之间。
 * 输出描述：
 *  输出一行一个数字，表示选出的和最大子矩阵内所有的数字和。
 * 用例：
 *  输入：
 *      3 4
 *      -3 5 -1 5
 *      2 4 -2 4
 *      -1 3 -1 3
 *   输出：
 *      20
 */
public class MaxMatrix {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int rows = in.nextInt();
        int cols = in.nextInt();
        
        int[][] inputholder = new int[rows][cols];
        for(int i = 0; i < rows; i ++) {
            for(int j = 0; j < cols; j ++) {
                inputholder[i][j] = in.nextInt();
            }
        }
        
        processer(inputholder, rows, cols);
    }

    /*
    解题思路基础：线性DP
    然后通过确定行范围，将其转化为数组，再套用线性DP进行比较求解
     */
    private static void processer(int[][] matrix, int rows, int cols) {
        int sumMax = Integer.MIN_VALUE;

        for(int i = 0; i < rows; i ++) {                // 遍历每一行上限
            int[] dp = new int[cols];
            for(int j = i ; j < rows; j ++) {           // 遍历每一行下限
                for(int k = 0; k < cols; k ++) {        // 每一列的和的值
                    int sumCol = 0;
                    for(int t = i ; t <= j; t ++) {
                        sumCol += matrix[t][k];
                    }
                    dp[k] = Math.max(k>0?dp[k-1]+sumCol:sumCol, sumCol);
                    sumMax = Math.max(sumMax, dp[k]);
                }
            }
        }

        System.out.println(sumMax);
    }
}

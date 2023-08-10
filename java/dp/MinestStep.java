package dp;

import java.util.Scanner;
import java.util.Vector;

/**
 * 题目描述：
 *  求从坐标零点到坐标点n的最小步数，一次只能沿横坐标轴向左或向右移动 2 或 3。
 *  注意：途径的坐标点可以为负数
 * 输入描述：
 *  坐标点n
 * 输出描述：
 *  输出从坐标零点移动到坐标点n的最小步数
 * 备注：
 *  1 <= n <= 10^9
 * 用例：
 *  输入：
 *      4
 *  输出：
 *      2
 */
public class MinestStep {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int TARGET = in.nextInt();

        processer(TARGET);
    }

    public static void processer(int target) {
        Vector<Integer> holder = new Vector<>(4,2);
        holder.add(0);
        holder.add(2);
        holder.add(1);
        holder.add(1);
        for( int i = 4; i <= target; i ++) {
            holder.add(Math.min(holder.get(i-2) + 1, holder.get(i-3) + 1));
        }

        System.out.println(holder.get(target));
    }
}

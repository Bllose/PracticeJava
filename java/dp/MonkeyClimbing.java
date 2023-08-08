package dp;

import java.util.Scanner;

/**
 * 题目描述:
 *  一天一只顽猴想去从山脚爬到山顶，途中经过一个有个N个台阶的阶梯，但是这猴子有一个习惯：
 *  每一次只能跳1步或跳3步，试问猴子通过这个阶梯有多少种不同的跳跃方式？
 * 输入描述：
 *  输入只有一个整数N（0<N<=50）此阶梯有多少个台阶。
 * 输出描述：
 *  输出有多少种跳跃方式（解决方案数）。
 * 用例：
 *  输入：
 *      50
 *  输出:
 *      122106097
 *
 *  输出:
 *      3
 *  输出：
 *      2
 */
public class MonkeyClimbing {
    /*
    基本思路：
    假如猴子跳到第30层
    那么在此之前只有两种可能性：
        1. 从29层，跳一格
        2. 从27层，跳三格
    所以我们只需要将跳至第29层和27层的方法加在一起，即得到跳至第30层的方法。
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int goal = in.nextInt();

        processer(goal);
    }

    public static void processer(int goal) {
        switch(goal) {
            case 1:
            case 2:
                System.out.println("1");
                return;
            case 3:
                System.out.println("2");
                return;
        }
        int[] holder = new int[goal + 1];
        holder[1] = 1;
        holder[2] = 1;
        holder[3] = 2;
        for(int i = 4; i <= goal; i ++) {
            holder[i] = holder[i - 3] + holder[i - 1];
        }
        System.out.println(holder[goal]);
    }
}

package logical;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 题目描述
 * 小明和朋友玩跳格子游戏，有 n 个连续格子，每个格子有不同的分数，小朋友可以选择以任意格子起跳，但是不能跳连续的格子，也不能回头跳；
 * 给定一个代表每个格子得分的非负整数数组，计算能够得到的最高分数。
 *
 * 输入描述
 * 给定一个数列，如：1 2 3 1
 * 输出描述
 * 输出能够得到的最高分，如：4
 *
 * 备注
 * 1 ≤ nums.length ≤ 100
 * 0 ≤ nums[i] ≤ 1000
 *
 * 用例1
 * 输入
 * 1 2 3 1
 * 输出
 * 4
 * 说明
 * 选择跳第一个格子和第三个格子
 *
 * 用例2
 * 输入
 * 2 7 9 3 1
 * 输出
 * 12
 * 说明
 * 2+9+1=12
 * ————————————————
 * 版权声明：本文为CSDN博主「算法大师」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
 * 原文链接：https://blog.csdn.net/banxia_frontend/article/details/131446639
 */
public class Hopscotch1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 输入数列
        int[] scores = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int n = scores.length;

        // 记录当前位置的最高分数
        int currMax = scores[0];
        // 记录上一个位置的最高分数
        int prevMax = Math.max(scores[0], scores[1]);

        for (int i = 2; i < n; i++) {
            // 计算当前位置的最高分数
            int temp = Math.max(prevMax, currMax + scores[i]);
            // 更新currMax和prevMax
            currMax = prevMax;
            prevMax = temp;
        }

        // 输出最后一个位置的最高分数
        System.out.println(prevMax);
    }
}


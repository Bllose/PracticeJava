package logical;

import java.util.*;

/**
 * 题目描述
 * 篮球(5V5)比赛中，每个球员拥有一个战斗力，每个队伍的所有球员战斗力之和为该队伍的总体战斗力。
 *
 * 现有10个球员准备分为两队进行训练赛，教练希望2个队伍的战斗力差值能够尽可能的小，以达到最佳训练效果。
 *
 * 给出10个球员的战斗力，如果你是教练，你该如何分队，才能达到最佳训练效果?请说出该分队方案下的最小战斗力差值。
 *
 * 输入描述
 * 10个篮球队员的战斗力(整数，范围[1,10000]),战斗力之间用空格分隔，如:10 9 8 7 6 5 4 3 2 1
 *
 * 不需要考虑异常输入的场景。
 *
 * 输出描述
 * 最小的战斗力差值，如:1
 * ————————————————
 * 版权声明：本文为CSDN博主「算法大师」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
 * 原文链接：https://blog.csdn.net/banxia_frontend/article/details/130035571
 */
/*
输入
10 9 8 7 6 5 4 3 2 1
输出
1
说明
1 2 5 9 10分为一队，3 4 6 7 8分为一队，两队战斗力之差最小，输出差值1。备注：球员分队方案不唯一，但最小战斗力差值固定是1
 */
public class BasketballTeam {
    private static int[] power;
    private static boolean[] visited;
    private static int min_diff = Integer.MAX_VALUE;

    public static void dfs(int index, int level, int sum) {
        if (level == 5) { // 分队完成，更新最小战斗力差值
            int diff = Math.abs(sum - (Arrays.stream(power).sum() - sum));
            min_diff = Math.min(min_diff, diff);
            return;
        }

        for (int i = index; i < 10; i++) { // 枚举每个球员
            if (i > index && power[i] == power[i - 1] && !visited[i - 1]) continue; // 去重
            visited[i] = true;
            dfs(i + 1, level + 1, sum + power[i]); // 递归搜索下一个球员
            visited[i] = false;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        power = new int[10];
        visited = new boolean[10];
        for (int i = 0; i < 10; i++) {
            power[i] = sc.nextInt();
        }
        Arrays.sort(power); // 对球员战斗力进行排序

        dfs(0, 0, 0); // 递归搜索所有分队方案

        System.out.println(min_diff); // 输出最小战斗力差值
    }
}

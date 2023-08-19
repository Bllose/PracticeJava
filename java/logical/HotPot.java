package logical;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 题目描述
 * 入职后，导师会请你吃饭，你选择了火锅。
 * 火锅里会在不同时间下很多菜。
 * 不同食材要煮不同的时间，才能变得刚好合适。
 * 你希望吃到最多的刚好合适的菜，但你的手速不够快，用m代表手速，每次下手捞菜后至少要过m秒才能再捞（每次只能捞一个）。
 * 那么用最合理的策略，最多能吃到多少刚好合适的菜？
 *
 * 输入描述
 * 第一行两个整数n，m，其中n代表往锅里下的菜的个数，m代表手速。（1 < n, m < 1000）
 * 接下来有n行，每行有两个数x，y代表第x秒下的菜过y秒才能变得刚好合适。（1 < x, y < 1000）
 *
 * 输出描述
 * 输出一个整数代表用最合理的策略，最多能吃到刚好合适的菜的数量。
 * ————————————————
 * 版权声明：本文为CSDN博主「算法大师」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
 * 原文链接：https://blog.csdn.net/banxia_frontend/article/details/129877961
 */
/*
输入：
2 1
1 2
2 1
输出：
1

 */
public class HotPot {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();

        List<Integer> times = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int start = scanner.nextInt();
            int duration = scanner.nextInt();
            times.add(start + duration);
        }

        int[] nums = new int[getMax(times) + 1];
        for (int t : times) {
            nums[t] = 1;
        }

        List<Integer> dp = new ArrayList<>();

        dfs(1, new ArrayList<>(), nums, dp, m);

        int max = 0;
        for (int count : dp) {
            max = Math.max(max, count);
        }

        System.out.println(max);
    }

    private static void dfs(int t, List<Integer> data, int[] nums, List<Integer> dp, int m) {
        if (t >= nums.length) {
            int sum = 0;
            for (int count : data) {
                sum += count;
            }
            dp.add(sum);
            return;
        }

        if (nums[t] == 1) {
            List<Integer> newData = new ArrayList<>(data);
            newData.add(1);
            dfs(t + m, newData, nums, dp, m);
            dfs(t + 1, data, nums, dp, m);
        } else {
            dfs(t + 1, data, nums, dp, m);
        }
    }

    private static int getMax(List<Integer> list) {
        int max = Integer.MIN_VALUE;
        for (int num : list) {
            max = Math.max(max, num);
        }
        return max;
    }
}

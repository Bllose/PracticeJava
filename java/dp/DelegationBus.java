package dp;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 题目描述：代表团坐车
 *  某组织举行会议，来了多个代表团同时到达，接待处只有一辆汽车，可以同时接待多个代表团，为了提高车辆利用率，请帮接待员计算可以坐满车的接待方案，输出方案数量。
 * 约束：
 *  一个团只能上一辆车，并且代表团人数 (代表团数量小于30，每个代表团人数小于30)小于汽车容量(汽车容量小于100)
 *  需要将车辆坐满
 * 输入描述：
 *  第一行 代表团人数，英文逗号隔开，代表团数量小于30，每个代表团人数小于30
 *  第二行 汽车载客量，汽车容量小于100
 * 输出描述：
 *  坐满汽车的方案数量
 *  如果无解输出0
 * 用例：
 *  输入：
 *      5,4,2,3,2,4,9
 *      10
 *  输出：
 *      4
 *
 * 说明：
 *  解释 以下几种方式都可以坐满车，所以，优先接待输出为4
 *  [2,3,5]
 *  [2,4,4]
 *  [2,3,5]
 *  [2,4,4]
 *
 *  基本思路：
 *      动态规划 dp
 *      每一个团体数量添加到一个容器中，将团体规制到容器的一端。
 *      然后将第二个团体也按照同样的方式进行规制，得到所有可能的排法。
 */
public class DelegationBus {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 读取代表团人数
        int[] groups = Arrays.stream(sc.nextLine().split(","))
                .mapToInt(Integer::parseInt)
                .toArray();

        // 读取汽车载客量
        int capacity =  sc.nextInt();

        // 初始化动态规划数组，dp[i]表示载客量为i时的方案数
        int[] dp = new int[capacity + 1];
        dp[0] = 1; // 载客量为0时，方案数为1（不接待任何代表团）

        // 代表团人数排序
        Arrays.sort(groups);

        // 动态规划转移
        for (int group : groups) {
            int diff = capacity - group; // group和capacity的差值
            for (int j = diff; j >= 0; j--) {
                dp[j + group] += dp[j]; // 转移方程：dp[j + group] += dp[j]，表示加上接待当前代表团后的方案数
            }
        }

        // 输出结果
        System.out.println(dp[capacity]);
    }
}

package dp;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

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
public class DelegationBus1 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int[] holder = Arrays.asList(in.nextLine().split(",")).stream().mapToInt(Integer::parseInt).toArray();
        Arrays.sort(holder);

        int rongliang = in.nextInt();
        int[] recorder = new int[rongliang];
        recorder[0] = 1;

        for(int i = 0 ; i < holder.length; i ++) {
            int cur = holder[i];
            int diff = rongliang - cur;
            for(int j = diff - 1; j > -1 ; j --) {
                recorder[j + cur] += recorder[j];
            }
        }

        System.out.println(recorder[holder.length - 1]);
    }
}

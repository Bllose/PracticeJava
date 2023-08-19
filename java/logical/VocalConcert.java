package logical;

import java.util.*;

/**
 * 题目描述
 * 为了庆祝中国共产党成立100周年，某公园将举行多场文艺表演，很多演出都是同时进行，
 * 一个人只能同时观看一场演出，且不能迟到早退，由于演出分布在不同的演出场地，所以连续观看的演出最少有15分钟的时间间隔，
 * 小明是一个狂热的文艺迷，想观看尽可能多的演出， 现给出演出时间表，请帮小明计算他最多能观看几场演出。
 *
 * 输入描述
 * 第一行为一个数N，表示演出场数，1<=N<=1000，接下来N行，每行有被空格分割的两个整数，
 * 第一个整数T表示演出的开始时间，第二个整数L表示演出的持续时间，T和L的单位为分钟，0<=T<=1440,0<L<=100.
 *
 * 输出描述
 * 输出最多能观看的演出场数。
 * ————————————————
 * 版权声明：本文为CSDN博主「算法大师」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
 * 原文链接：https://blog.csdn.net/banxia_frontend/article/details/129834593
 */
/*
输入：
2
720 120
840 120
输出：
1

输入：
2
0 60
90 60
输出：
2
 */
public class VocalConcert {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        // 输入演出场数
        int n = in.nextInt();

        // 创建一个列表来存储演出时间表
        List<List<Integer>> schedule = new ArrayList<>();

        // 循环读取每个演出的开始时间和持续时间，并将其添加到演出时间表中
        for (int i = 0; i < n; i++) {
            int startTime = in.nextInt();
            int endTime = startTime + in.nextInt();
            schedule.add(Arrays.asList(new Integer[]{startTime, endTime}));
        }

        // 将演出时间表按照结束时间进行排序
        schedule.sort(Comparator.comparingInt(a -> a.get(1)));

        // 获取第一个演出的结束时间和初始化观看的演出场数
        int firstEndTime = schedule.get(0).get(1);
        int numShows = 1;

        // 遍历演出时间表中的每个演出时间段
        for (List<Integer> interval : schedule) {
            int startTime = interval.get(0);
            int endTime = interval.get(1);

            // 如果当前演出的开始时间与前一个演出的结束时间间隔大于等于15分钟，则可以观看该演出
            if (startTime - firstEndTime >= 15) {
                numShows++;
                firstEndTime = endTime;
            }
        }

        // 输出最多能观看的演出场数
        System.out.println(numShows);
    }
}


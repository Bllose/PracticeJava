package greedy;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 题目描述
    某公司组织一场公开招聘活动，假设由于人数和场地的限制，每人每次面试的时长不等，并已经安排给定，用(S1,E1)、 (S2,E2)、 (Sj,Ej)…(Si < Ei，均为非负整数)表示每场面试的开始和结束时间。
    面试采用一对一的方式，即一名面试官同时只能面试一名应试者，一名面试官完成一次面试后可以立即进行下一场面试，且每个面试官的面试人次不超过 m。
    为了支撑招聘活动高效顺利进行，请你计算至少需要多少名面试官。

 输入描述
    输入的第一行为面试官的最多面试人次 m，第二行为当天总的面试场次 n，
    接下来的 n 行为每场面试的起始时间和结束时间，起始时间和结束时间用空格分隔。
    其中， 1 <= n, m <= 500

 输出描述
    输出一个整数，表示至少需要的面试官数量。

 用例：
 输出：
 2
 5
 1 2
 2 3
 3 4
 4 5
 5 6

 输出：
 3
 */
public class interview {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 输入最多面试人次max_interviews
        int max_interviews = scanner.nextInt();
        // 输入总的面试场次num_interviews
        int num_interviews = scanner.nextInt();
        // 输入每场面试的起始时间和结束时间
        int[][] interviews = new int[num_interviews][2];
        for (int i = 0; i < num_interviews; i++) {
            interviews[i][0] = scanner.nextInt();
            interviews[i][1] = scanner.nextInt();
        }

        // 按照结束时间对面试场次进行排序
        Arrays.sort(interviews, (a, b) -> a[1] - b[1]);

        // 使用优先队列来存储每个面试官的面试结束时间
        List<List<Integer>> interviewers = new ArrayList<>();
        interviewers.add(new ArrayList<>());
        for (int i = 0; i < interviews.length; i++) {
            int flag = 1;
            for (int j = 0; j < interviewers.size(); j++) {
                // 如果某个面试官的面试队列为空或者最后一场面试的结束时间早于当前面试的开始时间
                if (interviewers.get(j).isEmpty() || interviewers.get(j).get(interviewers.get(j).size() - 1) <= interviews[i][0]) {
                    interviewers.get(j).add(interviews[i][1]);
                    flag = 0;
                    break;
                }
            }
            // 如果没有符合条件的面试官，则新增一个面试官
            if (flag == 1) {
                interviewers.add(new ArrayList<>(Arrays.asList(interviews[i][1])));
            }
        }

        // 计算至少需要的面试官数量
        int num_interviewers = 0;
        for (int i = 0; i < interviewers.size(); i++) {
            // 如果面试队列的长度能够被max_interviews整除，则需要的面试官数量为队列长度除以max_interviews
            // 否则，需要的面试官数量为队列长度除以max_interviews加一
            num_interviewers += interviewers.get(i).size() / max_interviews;
            if (interviewers.get(i).size() % max_interviews != 0) {
                num_interviewers++;
            }
        }

        // 输出结果
        System.out.println(num_interviewers);
    }
}


package logical;

import java.util.Scanner;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 题目:不开心的小朋友
 * 游乐场里增加了一批摇摇车，非常受小朋友欢迎，但是每辆摇摇车同时只能有一个小朋友使用，如果没有空余的摇摇车需要排队等候，或者直接离开，最后没有玩上的小朋友会非常不开心。
 * 请根据今天小朋友的来去情况，统计不开心的小朋友数量
 * 1.摇摇车数量为N，范围是: 1 <= N < 10;
 * 2.每个小朋友都对应一个编码，编码是不重复的数字，今天小朋友的来去情况，可以使用编码表示为:1 1 2 3 2 3。
 * (若小朋友离去之前有空闲的摇摇车，则代表玩耍后离开;不考虑小朋友多次玩的情况)。小朋友数量≤ 100
 * 3.题目保证所有输入数据无异常且范围满足上述说明
 *
 * 输入描述
 * 第一行: 摇摇车数量
 * 第二行: 小朋友来去情况
 *
 * 输出描述
 * 返回不开心的小朋友数量
 *
 * 用例1：
 * 输入
 *  1
 *  1 2 1 2
 * 输出
 *  0
 * 说明
 * 第一行，1个摇摇车第二行，1号来 2号来(排队) 1号走 2号走(1号走后摇摇车已有空闲，所以玩后离开)
 *
 * 用例2:
 * 输入
 *  1
 *  1 2 2 3 1 3
 * 输出
 *  1
 * 说明
 * 第一行，1个摇摇车第二行，1号来 2号来(排队) 2号走(不开心离开) 3号来(排队)1号走 3号走(1号走后摇摇车已有空闲，所以玩后离)
 * ————————————————
 * 版权声明：本文为CSDN博主「算法大师」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
 * 原文链接：https://blog.csdn.net/banxia_frontend/article/details/131447250
 */
public class Unhappiess {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());
        String[] kids = scanner.nextLine().split(" ");

        int unhappy = 0;
        HashSet<String> playing = new HashSet<>();
        Queue<String> waiting = new LinkedList<>();

        for (String kid : kids) {
            if (playing.contains(kid)) {
                playing.remove(kid);

                if (!waiting.isEmpty()) {
                    playing.add(waiting.poll());
                }
            } else if (waiting.contains(kid)) {
                unhappy++;
                waiting.remove(kid);
            } else if (playing.size() < n) {
                playing.add(kid);
            } else {
                waiting.offer(kid);
            }
        }

        System.out.println(unhappy);

        scanner.close();
    }
}

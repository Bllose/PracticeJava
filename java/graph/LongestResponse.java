package graph;

import java.util.*;

/**
 * 题目描述
 * 某通信网络中有N个网络结点，用1到N进行标识。
 * 网络中的结点互联互通，且结点之间的消息传递有时延，相连结点的时延均为一个时间单位。
 * 现给定网络结点的连接关系link[i]={u，v}，其中u和v表示网络结点。
 * 当指定一个结点向其他结点进行广播，所有被广播结点收到消息后都会在原路径上回复一条响应消息，请计算发送结点至少需要等待几个时间单位才能收到所有被广播结点的响应消息。
 *
 * 注：
 * N的取值范围为[1，100];
 * 连接关系link的长度不超过3000，且1 <= u,v <= N;
 * 网络中任意结点间均是可达的;
 * 输入描述
 *  输入的第一行为两个正整数，分别表示网络结点的个数N，以及时延列表的长度T；
 *  接下来的T行输入，表示结点间的连接关系列表；
 *  最后一行的输入为一个正整数，表示指定的广播结点序号；
 *
 * 输出描述
 *  输出一个整数，表示发送结点接收到所有响应消息至少需要等待的时长。
 * ————————————————
 * 版权声明：本文为CSDN博主「算法大师」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
 * 原文链接：https://blog.csdn.net/banxia_frontend/article/details/129698669
 */
/*
输入：
5 7
1 4
2 1
2 3
2 4
3 4
3 5
4 5
2
输出：
4
说明：
结点2到5的最小时延为2，到剩余结点的最小时延均为1，所以至少要等待2*2=4s。
 */
public class LongestResponse {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 输入网络结点的个数N和时延列表的长度T
        int N = scanner.nextInt();
        int T = scanner.nextInt();

        // 构建连接关系表
        Map<Integer, List<Integer>> table = new HashMap<>();
        for (int i = 0; i < T; i++) {
            int u = scanner.nextInt();
            int v = scanner.nextInt();
            if (!table.containsKey(u)) {
                table.put(u, new ArrayList<>());
            }
            if (!table.containsKey(v)) {
                table.put(v, new ArrayList<>());
            }
            table.get(u).add(v);
            table.get(v).add(u);
        }

        // 输入指定的广播结点序号
        int start = scanner.nextInt();

        // 初始化检查列表
        int[] checkList = new int[N+1];
        checkList[start] = 1;

        // 使用队列进行广度优先搜索
        Queue<Integer> q = new LinkedList<>();
        q.add(start);

        // 初始化层数和时间单位
        int level = 0;

        while (!q.isEmpty()) {
            level++;
            int qSize = q.size();
            for (int i = 0; i < qSize; i++) {
                int cur = q.poll();
                List<Integer> neighbors = table.get(cur);
                for (int nxt : neighbors) {
                    if (checkList[nxt] == 0) {
                        checkList[nxt] = 1;
                        q.add(nxt);
                    }
                }
            }
        }

        // 计算发送结点接收到所有响应消息至少需要等待的时长
        System.out.println((level-1)*2);

        scanner.close();
    }
}


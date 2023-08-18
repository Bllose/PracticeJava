package graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 题目描述
 *  某通信网络中有N个网络结点，用1到N进行标识。网络通过一个有向无环图表示，其中图的边的值表示结点之间的消息传递时延。
 *  现给定相连节点之间的时延列表times[i]={u，v，w}，其中u表示源结点，v表示目的结点，w表示u和v之间的消息传递时延。
 *  请计算给定源结点到目的结点的最小传输时延，如果目的结点不可达，返回**-1****。**
 *
 * 注：
 *  N的取值范围为[1，100];
 *  时延列表times的长度不超过6000，且 1 <= u,v <= N，0 <= w <= 100;
 * 输入描述
 *  输入的第一行为两个正整数，分别表示网络结点的个数N，以及时延列表的长度M，用空格分隔；
 *  接下来的M行为两个结点间的时延列表[u v w];
 *  输入的最后一行为两个正整数，分别表示源结点和目的结点。
 * ————————————————
 * 版权声明：本文为CSDN博主「算法大师」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
 * 原文链接：https://blog.csdn.net/banxia_frontend/article/details/129720107
 *
 * 输出描述
 * 起点到终点得最小时延，不可达则返回-1
 *
 * 输入
 * 3 3
 * 1 2 11
 * 2 3 13
 * 1 3 50
 * 1 3
 *
 * 输出
 * 24
 *
 * 5 7
 * 1 2 5
 * 1 4 18
 * 1 5 42
 * 2 4 6
 * 2 3 7
 * 3 4 9
 * 4 5 11
 * 1 5
 *
 * 题目解析
 * 解题思路：
 *  1.首先，我们通过读取输入，获取网络结点的个数N和时延列表的长度M。
 *  2.接下来，我们读取M行时延列表的数据，并将其存储在二维数组times中。
 *  3.创建一个邻接表表示的有向图graph，其中每个结点都对应一个列表，列表中存储该结点的邻接结点及其权值。
 *  4.初始化源结点到各结点的距离数组dists，将所有结点的距离初始化为无穷大，将源结点的距离初始化为0。
 *  5.初始化需要检查的结点列表needCheck，将源结点加入列表。
 *  6.使用Dijkstra算法进行最短路径计算：
 *      1)选择距离最小的结点进行松弛操作。
 *      2)对该结点的邻接结点进行松弛操作，更新距离数组和需要检查的结点列表。
 *      3)重复上述步骤，直到需要检查的结点列表为空。
 *  7.返回源结点到目的结点的最小传输时延，如果目的结点不可达，则返回-1。
 * ————————————————
 * 版权声明：本文为CSDN博主「算法大师」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
 * 原文链接：https://blog.csdn.net/banxia_frontend/article/details/129720107
 */
/*
5 7
1 2 5
1 4 18
1 5 42
2 4 6
2 3 7
3 4 9
4 5 11
1 5
 */
public class NetDelay {
    public static void main(String[] args) {
        // 创建一个Scanner对象，用于读取输入
        Scanner scanner = new Scanner(System.in);
        // 创建一个List对象，用于存储输入的每一行数据
        List<String> lines = new ArrayList<>();
        // 初始化n和m的值
        int n = 0, m = 0;

        // 循环读取输入的每一行数据
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            lines.add(line);

            // 如果读取的是第一行数据，那么将n和m的值分别赋值为输入的两个整数
            if (lines.size() == 1) {
                String[] nm = line.split(" ");
                n = Integer.parseInt(nm[0]);
                m = Integer.parseInt(nm[1]);
            }

            // 如果已经读取了m行数据，那么就开始处理这些数据
            if (m > 0 && lines.size() == m + 2) {
                // 移除第一行和最后一行数据，并将它们分别赋值给src和dist
                lines.remove(0);
                String[] srcDist = lines.remove(lines.size() - 1).split(" ");
                int src = Integer.parseInt(srcDist[0]);
                int dist = Integer.parseInt(srcDist[1]);

                // 创建一个二维数组times，用于存储每条边的起点、终点和权值
                int[][] times = new int[m][3];
                for (int i = 0; i < m; i++) {
                    String[] time = lines.get(i).split(" ");
                    times[i][0] = Integer.parseInt(time[0]);
                    times[i][1] = Integer.parseInt(time[1]);
                    times[i][2] = Integer.parseInt(time[2]);
                }

                // 调用getMinTransDelay方法，计算源节点src到目标节点dist的最短路径
                System.out.println(getMinTransDelay(n, times, src, dist));

                // 清空lines列表，以便下一次使用
                lines.clear();
            }
        }
    }

    // 计算源节点src到目标节点dist的最短路径
    private static int getMinTransDelay(int n, int[][] times, int src, int dist) {
        // 构建邻接表表示的有向图
        List<List<int[]>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] time : times) {
            int u = time[0];
            int v = time[1];
            int w = time[2];
            graph.get(u).add(new int[]{v, w});
        }

        // 初始化源节点到各节点的距离
        int[] dists = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            dists[i] = Integer.MAX_VALUE;
        }
        dists[src] = 0;

        // 初始化需要检查的节点列表
        List<Integer> needCheck = new ArrayList<>();
        needCheck.add(src);

        // Dijkstra算法
        while (!needCheck.isEmpty()) {
            // 选择距离最小的节点进行松弛操作
            int u = needCheck.get(0);
            for (int i = 1; i < needCheck.size(); i++) {
                int v = needCheck.get(i);
                if (dists[v] < dists[u]) {
                    u = v;
                }
            }
            needCheck.remove((Integer) u);

            // 对u的邻接节点进行松弛操作
            for (int[] next : graph.get(u)) {
                int v = next[0];
                int w = next[1];
                int newDist = dists[u] + w;

                if (newDist >= dists[v]) {
                    continue;
                }

                dists[v] = newDist;
                if (!needCheck.contains(v)) {
                    needCheck.add(v);
                }
            }
        }

        // 返回源节点src到目标节点dist的最短路径
        return dists[dist] == Integer.MAX_VALUE ? -1 : dists[dist];
    }
}

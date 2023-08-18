package graph;

import java.util.*;

/**
 * 题目描述
 * 在一个地图中(地图由n*n个区域组成），有部分区域被感染病菌。 感染区域每天都会把周围（上下左右）的4个区域感染。
 * 请根据给定的地图计算，多少天以后，全部区域都会被感染。 如果初始地图上所有区域全部都被感染，或者没有被感染区域，返回-1
 *
 * 输入描述
 * 一行N*N个数字（只包含0,1，不会有其他数字）表示一个地图，数字间用,分割，0表示未感染区域，1表示已经感染区域
 * 每N个数字表示地图中一行，输入数据共表示N行N列的区域地图。
 *
 * 例如输入1,0,1,0,0,0,1,0,1，表示地图
 * 1,0,1
 * 0,0,0
 * 1,0,1
 *
 * 输出描述
 * 一个整数，表示经过多少天以后，全部区域都被感染 1<=N<200
 * ————————————————
 * 版权声明：本文为CSDN博主「算法大师」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
 * 原文链接：https://blog.csdn.net/banxia_frontend/article/details/129834614
 */
/*
输入
1,0,1,0,0,0,1,0,1
输出
2

输入
1,1,1,1,1,1,1,1,1
输出
-1
 */
public class EpidemicSpreading {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        List<Integer> map = new ArrayList<>();
        int pos = 0;
        String token;
        while ((pos = input.indexOf(",")) != -1) { // 将输入字符串转换为一维数组
            token = input.substring(0, pos);
            map.add(Integer.parseInt(token));
            input = input.substring(pos + 1);
        }
        map.add(Integer.parseInt(input));
        System.out.println(getInfectionDays(map)); // 输出感染天数
    }

    public static int getInfectionDays(List<Integer> map) {
        int n = (int) Math.sqrt(map.size());

        int[][] matrix = new int[n][n]; // 将一维数组转换为二维矩阵

        Queue<int[]> q = new LinkedList<>(); // 用队列存储感染区域

        int healthy = 0; // 记录未感染区域数量

        int[][] offsets = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // 记录四个方向的偏移量

        for (int i = 0; i < n * n; i++) {
            int x = i / n;
            int y = i % n;
            matrix[x][y] = map.get(i); // 将一维数组转换为二维矩阵
            if (matrix[x][y] == 1) {
                q.offer(new int[]{x, y}); // 将感染区域加入队列
            } else {
                healthy++; // 计算未感染区域数量
            }
        }

        if (healthy == 0 || healthy == n * n) { // 判断特殊情况
            return -1;
        }

        int day = 0; // 记录感染天数
        while (!q.isEmpty() && healthy > 0) { // 当队列不为空且还有未感染区域时，进行循环
            int[] tmp = q.poll(); // 取出队首元素
            int x = tmp[0], y = tmp[1]; // 获取队首元素的坐标
            day = matrix[x][y] + 1; // 记录感染天数

            for (int[] offset : offsets) { // 遍历四个方向
                int newX = x + offset[0]; // 新的横坐标
                int newY = y + offset[1]; // 新的纵坐标

                if (newX >= 0 && newX < n && newY >= 0 && newY < n && matrix[newX][newY] == 0) { // 判断边界和未感染区域
                    healthy--; // 未感染区域数量减一
                    matrix[newX][newY] = day; // 标记该区域已感染
                    q.offer(new int[]{newX, newY}); // 将该区域加入队列
                }
            }
        }

        return day - 1; // 返回感染天数
    }
}



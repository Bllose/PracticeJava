package logical;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * 题目描述
 * 给你一个由 ‘0’ (空地)、‘1’ (银矿)、‘2’(金矿) 组成的的地图，矿堆只能由上下左右相邻的金矿或银矿连接形成。超出地图范围可以认为是空地。
 * 假设银矿价值1，金矿价值2 ，请你找出地图中最大价值的矿堆并输出该矿堆的价值。
 *
 * 输入描述
 * 地图元素信息如：
 * 22220
 * 00000
 * 00000
 * 11111
 *
 * 地图范围最大 300*300
 * 0 ≤ 地图元素 ≤ 2
 * 输出描述
 * 矿堆的最大价值
 * ————————————————
 * 版权声明：本文为CSDN博主「算法大师」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
 * 原文链接：https://blog.csdn.net/banxia_frontend/article/details/131058167
 */
/*
输入：
22220
00000
00000
01111
输出：
8

输入：
22220
00020
00010
01111
输出：
15

输入：
20000
00020
00000
00111
输出：
3
 */
public class FindTheBest {
    // 地图矩阵
    static int[][] map;

    // 上下左右，四个方向的偏移量
    static int[][] offsets = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 读入地图信息
        ArrayList<String> lines = new ArrayList<>();
        while (sc.hasNextLine()) {
            String theLine = sc.nextLine();
            if(theLine.length() == 0) {
                break;
            }
            lines.add(theLine);
        }

        // 构建地图矩阵
        int rows = lines.size();
        int cols = lines.get(0).length();
        map = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            String line = lines.get(i);
            for (int j = 0; j < cols; j++) {
                map[i][j] = line.charAt(j) - '0';
            }
        }

        // 记录最大矿堆价值
        int maxVal = 0;

        // 遍历地图矩阵
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                // 如果点(i,j)没有被访问过，且点(i,j)上有矿，则进入深搜
                if (map[i][j] > 0) {
                    LinkedList<int[]> stack = new LinkedList<>();
                    stack.add(new int[]{i, j});

                    int sum = 0;

                    while (!stack.isEmpty()) {
                        int[] pos = stack.removeLast();
                        int x = pos[0], y = pos[1];

                        sum += map[x][y];
                        map[x][y] = 0;

                        // 遍历四个方向
                        for (int[] offset : offsets) {
                            int newX = x + offset[0];
                            int newY = y + offset[1];

                            // 如果新位置在地图范围内，且有矿，则加入栈中
                            if (newX >= 0 && newX < rows && newY >= 0 && newY < cols && map[newX][newY] > 0) {
                                stack.add(new int[]{newX, newY});
                            }
                        }
                    }

                    // 更新最大矿堆价值
                    maxVal = Math.max(maxVal, sum);
                }
            }
        }

        System.out.println(maxVal);
    }
}


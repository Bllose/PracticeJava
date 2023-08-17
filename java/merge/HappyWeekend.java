package merge;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

/**
 题目描述
 小华和小为是很要好的朋友，他们约定周末一起吃饭。
 通过手机交流，他们在地图上选择了多个聚餐地点（由于自然地形等原因，部分聚餐地点不可达），求小华和小为都能到达的聚餐地点有多少个？

 输入描述
    第一行输入m和n，m代表地图的长度，n代表地图的宽度。

 第二行开始具体输入地图信息，地图信息包含：
     0 为通畅的道路
     1 为障碍物（且仅1为障碍物）
     2 为小华或者小为，地图中必定有且仅有2个 （非障碍物）
     3 为被选中的聚餐地点（非障碍物）

 输出描述
    可以被两方都到达的聚餐地点数量，行末无空格。
 ==================================
 输入
 4 4
 2 1 0 3
 0 1 2 1
 0 3 0 0
 0 0 0 0

 输出
 2
 ==========================
 输入
 4 4
 2 1 2 3
 0 1 0 0
 0 1 0 0
 0 1 0 0

 输出
 0
 */
public class HappyWeekend {
    // 定义上下左右四个方向的偏移量
    private static int[][] dirs = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};

    // 深度优先搜索函数
    private static boolean dfs(int currX, int currY, int targetX, int targetY, int[][] map, boolean[][] visited) {
        // 如果当前位置就是目标位置，返回true
        if (currX == targetX && currY == targetY) {
            return true;
        }

        // 遍历四个方向
        for (int[] dir : dirs) {
            int nextX = currX + dir[0], nextY = currY + dir[1];
            // 如果下一个位置超出地图范围，或者是障碍物，或者已经访问过，跳过
            if (nextX < 0 || nextY < 0 || nextX >= map.length || nextY >= map[0].length || map[nextX][nextY] == 1 || visited[nextX][nextY]) {
                continue;
            }

            // 标记下一个位置为已访问
            visited[nextX][nextY] = true;
            // 递归搜索下一个位置
            if (dfs(nextX, nextY, targetX, targetY, map, visited)) {
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        // 输入初始化
        Scanner scanner = new Scanner(System.in);
        int m = scanner.nextInt();
        int n = scanner.nextInt();
        int[][] map = new int[m][n];
        boolean[][] visited;
        List<int[]> persons = new ArrayList<>();
        List<int[]> targets = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                map[i][j] = scanner.nextInt();
                if (map[i][j] == 2) {
                    persons.add(new int[]{i, j});
                } else if (map[i][j] == 3) {
                    targets.add(new int[]{i, j});
                }
            }
        }

        // 获取小华和小为的位置
        int[] xiaohua = persons.get(0);
        int[] xiaowei = persons.get(1);
        int res = 0;
        // 遍历所有聚餐地点
        for (int[] target : targets) {
            // 重置visited数组
            visited = new boolean[m][n];
            // 判断小华是否能到达目标位置
            if (dfs(xiaohua[0], xiaohua[1], target[0], target[1], map, visited)) {
                // 重置visited数组
                visited = new boolean[m][n];
                // 判断小为是否能到达目标位置
                if (dfs(xiaowei[0], xiaowei[1], target[0], target[1], map, visited)) {
                    // 如果两个人都能到达目标位置，结果加1
                    res++;
                }
            }
        }
        System.out.println(res);

        scanner.close();
    }
}


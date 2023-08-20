package logical;

import java.util.Scanner;

/**
 *题目描述
 * 有一个大小是N*M的战场地图，被墙壁 ‘#’ 分隔成大小不同的区域，上下左右四个方向相邻的空地 ‘.’ 属于同一个区域，只有空地上可能存在敌人’E”，
 * 请求出地图上总共有多少区域里的敌人数小于K。
 *
 * 输入描述
 * 第一行输入为N,M,K；
 * N表示地图的行数，M表示地图的列数， K表示目标敌人数量
 * N，M<=100
 * 之后为一个NxM大小的字符数组。
 *
 * 输出描述
 * 敌人数小于K的区域数量
 *
 * 用例
 * 输入
 * 3 5 2
 * ..#EE
 * E.#E.
 * ###..
 * 输出
 * 1
 * 说明
 * 地图被墙壁分为两个区域，左边区域有1个敌人，右边区域有3个敌人，符合条件的区域数量是1
 *
 * 代码思路
 *  1.首先，根据输入描述，我们可以得到地图的行数n、列数m和目标敌人数量k。然后，我们需要读取地图矩阵，将其存储在一个二维字符数组matrix中。
 *  2.接下来，我们需要初始化一个二维布尔数组visited，用于标记地图中的每个位置是否已经被访问过。初始化visited为false。
 *  3.然后，我们定义一个深度优先搜索函数dfs，用于计算以位置(i, j)为起点的区域中敌人的数量。在dfs函数中，我们首先将位置(i, j)标记为已访问，并
 *      根据该位置的值判断是否为敌人，如果是，则将计数器count加1。然后，我们使用一个栈来保存待访问的位置。
 *      在每一次循环中，我们从栈中取出一个位置(pos)，并遍历其上下左右四个相邻位置。
 *      如果相邻位置在地图范围内、未被访问过且不是墙壁，则将其标记为已访问，并根据其值判断是否为敌人，
 *      如果是，则将计数器count加1，并将该位置加入到栈中。
 *      最后，返回计数器count。
 *  4.接下来，我们定义主函数main。在主函数中，我们首先读取地图的行数n、列数m和目标敌人数量k。
 *      然后，根据地图的行数n和列数m初始化visited和matrix数组。
 *      接下来，我们遍历地图中的每个位置，
 *      如果该位置已经被访问过或者是墙壁，则跳过。
 *      否则，调用dfs函数计算以该位置为起点的区域中敌人的数量，
 *      如果该数量小于目标敌人数量k，则将区域数量加1。最后，输出区域数量。
 *
 *  整体思路是，遍历地图中的每个位置，如果该位置未被访问过且不是墙壁，则调用dfs函数计算以该位置为起点的区域中敌人的数量，
 *  如果该数量小于目标敌人数量k，则将区域数量加1。最后，输出区域数量。
 * ————————————————
 * 版权声明：本文为CSDN博主「算法大师」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
 * 原文链接：https://blog.csdn.net/banxia_frontend/article/details/131445865
 */
/*
5 6 3
...##.
.#...#
.#.#..
E.#...
.#.#E#

 */
public class Enemies {
    private static int n, m, k;
    private static char[][] matrix;
    private static int[][] visited;
    private static int enemyCount;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        m = scanner.nextInt();
        k = scanner.nextInt();

        matrix = new char[n][m];
        visited = new int[n][m];

        for (int i = 0; i < n; i++) {
            String row = scanner.next();
            for (int j = 0; j < m; j++) {
                matrix[i][j] = row.charAt(j);
            }
        }

        int ans = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (visited[i][j] != 0 || matrix[i][j] == '#') {
                    continue;
                }
                enemyCount = 0;
                dfs(i, j);
                ans += enemyCount < k ? 1 : 0;
            }
        }

        System.out.println(ans);
    }

    public static void dfs(int i, int j) {
        visited[i][j] = 1;

        if (matrix[i][j] == 'E') {
            enemyCount++;
        }

        int[][] offsets = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        for (int[] offset : offsets) {
            int newX = i + offset[0];
            int newY = j + offset[1];

            if (newX >= 0 && newX < n && newY >= 0 && newY < m && visited[newX][newY] == 0 && matrix[newX][newY] != '#') {
                dfs(newX, newY);
            }
        }
    }
}

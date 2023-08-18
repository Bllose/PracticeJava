package logical;

import java.util.*;

/**
 * 题目描述
 * 贪吃蛇是一个经典游戏，蛇的身体由若干方格连接而成，身体随蛇头移动。蛇头触碰到食物时，蛇的长度会增加一格。
 * 蛇头和身体的任一方格或者游戏版图边界碰撞时，游戏结束。
 *
 * 下面让我们来完成贪吃蛇游戏的模拟。
 * 给定一个N*M的数组arr，代表N*M个方格组成的版图，贪吃蛇每次移动一个方格。
 * 若arr[i][j] == ‘H’，表示该方格为贪吃蛇的起始位置；
 * 若arr[i][j] == ‘F’，表示该方格为食物，
 * 若arr[i][j] == ‘E’，表示该方格为空格。
 * 贪吃蛇初始长度为1，初始移动方向为向左。
 * 为给定一系列贪吃蛇的移动操作，返回操作后蛇的长度，如果在操作执行完之前已经游戏结束，返回游戏结束时蛇的长度。
 * 贪吃蛇移动、吃食物和碰撞处理的细节见下面图示：
 *
 * 图1：截取了贪吃蛇移动的一个中间状态，H表示蛇头，F表示食物，数字为蛇身体各节的编号，蛇为向左移动，此时蛇头和食物已经相邻
 * 图2：蛇头向左移动一格，蛇头和食物重叠，注意此时食物的格子成为了新的蛇头，第1节身体移动到蛇头位置，第2节身体移动到第1节身体位置，以此类推，最后添加第4节身体到原来第3节身体的位置。
 * 图3：蛇头继续向左移动一格，身体的各节按上述规则移动，此时蛇头已经和边界相邻，但还未碰撞。
 * 图4：蛇头继续向左移动一格，此时蛇头已经超过边界，发生碰撞，游戏结束。
 * 图5和图6给出一个蛇头和身体碰撞的例子，蛇为向上移动。
 * 图5时蛇头和第7节身体相邻，但还未碰撞；
 * 图6蛇头向上移动一格，此时蛇头和第8节身体都移动到了原来第7节身体的位置，发生碰撞，游戏结束。
 *
 * 输入描述
 * 输入第一行为空格分隔的字母，代表贪吃蛇的移动操作。
 * 字母取值为U、D、L、R和G，
 * U、D、L、R分别表示贪吃蛇往上、下、左、右和转向，转向时贪吃蛇不移动 ，G表示贪吃蛇按当前的方向移动一格。
 * 用例保证输入的操作正确。
 * 第二行为空格分隔的两个数，指定N和M，为数组的行和列数。
 * 余下N行每行是空格分隔的M个字母。字母取值为H、F和E，H表示贪吃蛇的起始位置，F表示食物，E表示该方格为空。
 *
 * 用例保证有且只有一个H，而F和E会有多个。
 *
 * 输出描述
 *  输出一个数字，为蛇的长度。
 * ————————————————
 * 版权声明：本文为CSDN博主「算法大师」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
 * 原文链接：https://blog.csdn.net/banxia_frontend/article/details/129718597
 */
public class GluttonousSnake {
    static String[][] matrix = new String[1000][1000];
    static int n, m;

    // 贪吃蛇移动函数
    // [i,j]是上一次蛇头位置，snake是蛇的位置信息，direction是蛇头移动方向
    // 返回值：如果游戏结束，返回贪吃蛇长度，否则返回0
    public static int go(int i, int j, Deque<List<Integer>> snake, String direction) {
        // [r,c]是当前蛇头位置
        int r = i, c = j;

        if (direction.equals("U")) {
            r--;
        } else if (direction.equals("D")) {
            r++;
        } else if (direction.equals("L")) {
            c--;
        } else if (direction.equals("R")) {
            c++;
        }

        if (c < 0 || c >= n || r < 0 || r >= m) {
            // 越界，游戏结束，返回贪吃蛇长度
            return snake.size();
        } else {
            if (matrix[r][c].equals("E")) {
                // 如果蛇头去的位置是空地
                matrix[r][c] = "H";
                snake.addFirst(Arrays.asList(r, c));
                List<Integer> tmp = snake.getLast();
                matrix[tmp.get(0)][tmp.get(1)] = "E"; // 原答案写成H应该是错了， 应为移动一步之后，尾端应该就变成空地了
                snake.removeLast();
            } else if (matrix[r][c].equals("F")) {
                // 如果蛇头去的位置是食物
                snake.addFirst(Arrays.asList(r, c));
                matrix[r][c] = "H";
            } else {
                // 吃到自己身体，游戏结束，返回贪吃蛇长度
                return snake.size();
            }
        }

        // 返回0表示继续下一步移动
        return 0;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 读入贪吃蛇移动指令
        List<String> operates = new ArrayList<>();
        String line = scanner.nextLine();
        String operate = "";
        for (int i = 0; i < line.length(); i++) {
            if (line.charAt(i) == ' ') {
                operates.add(operate);
                operate = "";
            } else {
                operate += line.charAt(i);
            }
        }
        operates.add(operate);

        // 读入矩阵信息
        line = scanner.nextLine();
        int pos = 0;
        List<Integer> tmp = new ArrayList<>();
        for (int i = 0; i < line.length(); i++) {
            if (line.charAt(i) == ' ') {
                tmp.add(Integer.parseInt(line.substring(pos, i)));
                pos = i + 1;
            }
        }
        tmp.add(Integer.parseInt(line.substring(pos)));
        n = tmp.get(0);
        m = tmp.get(1);

        for (int i = 0; i < n; i++) {
            line = scanner.nextLine();
            pos = 0;
            for (int j = 0; j < m; j++) {
                int k = line.indexOf(' ', pos);
                if (k == -1) {
                    matrix[i][j] = line.substring(pos);
                } else {
                    matrix[i][j] = line.substring(pos, k);
                }
                pos = k + 1;
            }
        }

        // 找到初始蛇头位置，并使用snake[0]来维护蛇头位置
        Deque<List<Integer>> snake = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (matrix[i][j].equals("H")) {
                    snake.addLast(Arrays.asList(i, j));
                }
            }
        }

        // 蛇头移动方向
        String direction = "L"; // 初始默认向左

        // 处理贪吃蛇移动指令
        for (String op : operates) {
            if (op.equals("G")) {
                // 如果为G，则表示验证direction方向移动一格
                List<Integer> pos1 = snake.getFirst();
                int res = go(pos1.get(0), pos1.get(1), snake, direction); // 具体移动逻辑
                if (res > 0) {
                    // 游戏结束，返回贪吃蛇长度
                    System.out.println(res);
                    return;
                }
            } else {
                direction = op;
            }
        }

        // 指令执行完毕，输出贪吃蛇长度
        System.out.println(snake.size());

        scanner.close();
    }
}


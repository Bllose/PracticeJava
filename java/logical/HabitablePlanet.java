package logical;

import java.util.*;

/**
 *输入描述
 * 输入row * column个网格数据，每个网格值枚举值如下: YES，NO，NA；
 *
 * 样例:
 *
 * YES YES NO
 * NO NO NO
 * NA NO YES
 *
 * 输出描述
 *  可改造区是否能全部变成宜居区，如果可以，则返回改造的太阳日天数，不可以则返回-1。
 *
 * 备注
 *      grid[i][j]只有3种情况，YES、NO、NA
 *      row == grid.length
 *      column == grid[i].length
 *      1 ≤ row, column ≤ 8
 *
 * 用例一
 * 输入
 *  YES YES NO
 *  NO NO NO
 *  YES NO NO
 * 输出
 *  2
 * 说明
 * 经过 2 个太阳日，完成宜居改造。
 *
 * 用例二
 * 输入
 *  YES NO NO NO
 *  NO NO NO NO
 *  NO NO NO NO
 *  NO NO NO NO
 * 输出
 *  6
 * 说明
 *  经过 6 个太阳日，可完成改造
 *
 * 用例三
 * 输入
 *  NO NA
 * 输出
 *  -1
 * 说明
 *  无改造初始条件，无法进行改造
 *
 * 用例四
 * 输入
 *  YES NO NO YES
 *  NO NO YES NO
 *  NO YES NA NA
 *  YES NO NA NO
 * 输出
 *  -1
 * 说明
 *  -1 ，右下角的区域，被周边三个死亡区挡住，无法实现改造
 * ————————————————
 * 版权声明：本文为CSDN博主「算法大师」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
 * 原文链接：https://blog.csdn.net/banxia_frontend/article/details/130876921
 */
public class HabitablePlanet {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<List<String>> grid = new ArrayList<>(); // 网格
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.isEmpty()) {
                break;
            } else {
                List<String> row = new ArrayList<>();
                int start = 0, end = 0;
                while (end != -1) {
                    end = line.indexOf(' ', start);
                    if (end == -1) {
                        row.add(line.substring(start));
                    } else {
                        row.add(line.substring(start, end));
                    }
                    start = end + 1;
                }
                grid.add(row);
            }
        }

        int row_num = grid.size();
        int col_num = grid.get(0).size();

        List<List<Integer>> queue = new ArrayList<>(); // 存储已经改造的位置
        int need = 0; // 需要改造的位置数

        for (int i = 0; i < row_num; i++) {
            for (int j = 0; j < col_num; j++) {
                String val = grid.get(i).get(j);
                if (val.equals("YES")) {
                    queue.add(Arrays.asList(i, j));
                } else if (val.equals("NO")) {
                    need++;
                }
            }
        }

        if (queue.isEmpty()) { // 如果没有已经改造的位置，则无法继续改造
            System.out.println(-1);
            return;
        }
        if (queue.size() == row_num * col_num) { // 如果所有位置都已经改造，则不需要继续改造
            System.out.println(0);
            return;
        }

        int day = 0; // 改造天数
        List<List<Integer>> offsets = Arrays.asList(Arrays.asList(-1, 0), Arrays.asList(1, 0), Arrays.asList(0, -1), Arrays.asList(0, 1)); // 上下左右四个方向

        while (!queue.isEmpty() && need > 0) { // 只要还有需要改造的位置，就继续改造
            List<List<Integer>> new_queue = new ArrayList<>(); // 存储新改造的位置

            for (List<Integer> pos : queue) {
                int x = pos.get(0), y = pos.get(1);
                for (List<Integer> offset : offsets) {
                    int new_x = x + offset.get(0);
                    int new_y = y + offset.get(1);

                    if (new_x >= 0
                            && new_x < row_num
                            && new_y >= 0
                            && new_y < col_num
                            && grid.get(new_x).get(new_y).equals("NO")) { // 如果新位置可以改造，就改造它
                        grid.get(new_x).set(new_y, "YES");
                        new_queue.add(Arrays.asList(new_x, new_y));
                        need--;
                    }
                }
            }

            day++; // 改造天数加一
            queue = new_queue; // 更新已经改造的位置
        }

        if (need == 0) System.out.println(day); // 如果所有位置都已经改造，则返回改造的天数
        else System.out.println(-1); // 否则返回-1
    }
}


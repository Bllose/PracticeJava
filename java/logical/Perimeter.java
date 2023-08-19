package logical;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 题目描述
 * 有一个64×64的矩阵，每个元素的默认值为0，现在向里面填充数字，相同的数字组成一个实心图形，如下图所示是矩阵的局部（空白表示填充0）：
 * 数字1组成了蓝色边框的实心图形，数字2组成了红色边框的实心图形。
 * 单元格的边长规定为1个单位。
 * 请根据输入，计算每个非0值填充出来的实心圆形的周长。
 *
 * 输入描述
 * 第一行输入N，表示N个图形，N > 0 且 N < 64 × 64
 * 矩阵左上角单元格坐标记作(0, 0)，第一个数字表示行号，第二个数字表示列号
 * 接下来是N行，每行第一个数是矩阵单元格填充的数字，后续每两个一组，表示填充该数字的单元格坐标
 * 答题者无需考虑数据格式非法的场景，题目用例不考察数据格式
 * 题目用例保证同一个填充值只会有一行输入数据
 *
 * 输出描述
 * 一共输出N个数值，每个数值表示某一行输入表示图形的周长
 * 输出顺序需和输入的隔行顺序保持一致，即第1个数是输入的第1个图形的周长，第2个数是输入的第2个图形的周长，以此类推。
 * ————————————————
 * 版权声明：本文为CSDN博主「算法大师」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
 * 原文链接：https://blog.csdn.net/banxia_frontend/article/details/129508588
 */
/*
输入：
2
1 1 3 2 2 2 3 2 4 3 2 3 3 3 4 4 1 4 2 4 3 4 4 5 2 5 3
2 3 7 3 8 4 5 4 6 4 7 4 8 5 4 5 5 5 6 5 7 5 8 6 4 6 5 6 6 6 7 6 8 7 4 7 5 7 6 7 7 7 8
输出：
18 20
 */
public class Perimeter {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            // 读取输入的N，表示N个图形
            int N = Integer.parseInt(scanner.nextLine());
            // 创建一个64×64的矩阵，每个元素的默认值为0
            int[][] matrix = new int[64][64];

            // 循环读取N个图形的输入
            for (int i = 0; i < N; i++) {
                // 将每行的输入按空格分割，并转换为整数数组
                Integer[] split = Arrays.stream(scanner.nextLine().split(" "))
                        .map(Integer::parseInt)
                        .toArray(Integer[]::new);

                // 第一个数是填充的数字
                int value = split[0];
                // 后续每两个一组，表示填充该数字的单元格坐标
                for (int j = 1; j < split.length; j += 2) {
                    int row = split[j];
                    int col = split[j + 1];
                    // 将矩阵中对应的单元格填充为指定的数字
                    matrix[row][col] = value;
                }
            }

            scanner.close();

            // 创建一个数组，用于存储每个图形的周长
            int[] perimeters = new int[N];
            // 循环计算每个图形的周长
            for (int i = 1; i <= N; i++) {
                int perimeter = 0;
                // 遍历整个矩阵
                for (int j = 0; j < 64; j++) {
                    for (int k = 0; k < 64; k++) {
                        // 如果当前单元格的值等于当前图形的数字
                        if (matrix[j][k] == i) {
                            // 判断当前单元格的上、下、左、右是否与当前图形的数字不同
                            // 如果是，则周长加1
                            if (j == 0 || matrix[j - 1][k] != i) perimeter++;
                            if (j == 63 || matrix[j + 1][k] != i) perimeter++;
                            if (k == 0 || matrix[j][k - 1] != i) perimeter++;
                            if (k == 63 || matrix[j][k + 1] != i) perimeter++;
                        }
                    }
                }
                // 将当前图形的周长存入数组
                perimeters[i-1] = perimeter;
            }

            // 输出每个图形的周长
            for (int i = 0; i < N; i++) {
                System.out.print(perimeters[i]);
                if (i != N-1) {
                    System.out.print(" ");
                }
            }
        }

    }
}

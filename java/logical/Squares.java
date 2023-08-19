package logical;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

/**
 * 题目描述
 * 输入N个互不相同的二维整数坐标，求这N个坐标可以构成的正方形数量。[内积为零的的两个向量垂直]
 *
 * 输入描述
 * 第一行输入为N，N代表坐标数量，N为正整数。N <= 100
 *
 * 之后的 K 行输入为坐标x y以空格分隔，x，y为整数，-10<=x, y<=10
 *
 * 输出描述
 * 输出可以构成的正方形数量。
 * ————————————————
 * 版权声明：本文为CSDN博主「伏城之外」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
 * 原文链接：https://blog.csdn.net/qfc_128220/article/details/127417851
 */
/*
输入：
3
1 3
2 4
3 1
输出：
0
说明：
3个点不足以构成正方形

输出：
4
0 0
1 2
3 1
2 -1
输出：
1
 */
public class Squares {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = Integer.parseInt(sc.nextLine());

        String[] coordinates = new String[n];
        for (int i = 0; i < n; i++) {
            coordinates[i] = sc.nextLine();
        }

        System.out.println(getResult(n, coordinates));
    }

    public static int getResult(int n, String[] coordinates) {
        int squareCount = 0;

        HashSet<String> set = new HashSet<>(Arrays.asList(coordinates));

        for (int i = 0; i < n; i++) {
            Integer[] arr1 =
                    Arrays.stream(coordinates[i].split(" ")).map(Integer::parseInt).toArray(Integer[]::new);
            int x1 = arr1[0], y1 = arr1[1];

            for (int j = i + 1; j < n; j++) {
                Integer[] arr2 =
                        Arrays.stream(coordinates[j].split(" ")).map(Integer::parseInt).toArray(Integer[]::new);
                int x2 = arr2[0], y2 = arr2[1];

                int x3 = x1 - (y1 - y2), y3 = y1 + (x1 - x2);
                int x4 = x2 - (y1 - y2), y4 = y2 + (x1 - x2);

                if (set.contains(x3 + " " + y3) && set.contains(x4 + " " + y4)) squareCount++;

                int x5 = x1 + (y1 - y2), y5 = y1 - (x1 - x2);
                int x6 = x2 + (y1 - y2), y6 = y2 - (x1 - x2);
                if (set.contains(x5 + " " + y5) && set.contains(x6 + " " + y6)) squareCount++;
            }
        }

        return squareCount / 4;
    }
}
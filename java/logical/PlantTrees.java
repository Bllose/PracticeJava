package logical;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 题目描述: 最佳植树距离
 * 按照环保公司要求，小明需要在沙化严重的地区进行植树防沙工作，初步目标是种植一条直线的树带。
 * 由于有些区域目前不适合种植树木，所以只能在一些可以种植的点来种植树木。
 * 在树苗有限的情况下，要达到最佳效果，就要尽量散开种植，不同树苗之间的最小间距要尽量大。
 * 给你一个适合种情树木的点坐标和一个树苗的数量，请帮小明选择一个最佳的最小种植间距。
 * 例如，适合种植树木的位置分别为1,3,5,6,7,10,13 树苗数量是3，种植位置在1,7,13，树苗之间的间距都是6，
 * 均匀分开，就达到了散开种植的目的，最佳的最小种植间距是6
 *
 * 输入描述
 * 第1行表示适合种树的坐标数量
 * 第2行是适合种树的坐标位置
 * 第3行是树苗的数量
 *
 * 例如：
 *
 * 7
 * 1 5 3 6 10 7 13
 * 3
 *
 * 输出描述
 * 最佳的最小种植间距
 *
 * 代码思路
 * 首先，从输入中获取适合种树的坐标数量n。
 * 然后，从输入中获取适合种树的坐标位置，并将其存储在一个数组positions中。
 * 接着，从输入中获取树苗的数量m。
 * 将坐标位置数组positions进行排序，以便后续计算。
 * 初始化最小种植间距的最小值和最大值，分别为1和positions[n-1] - positions[0]。
 * 初始化答案变量ans为0。
 * 使用一个循环，从最小种植间距的最小值开始，逐步增加种植间距，直到最大值。
 * 在每次循环中，初始化一个计数变量count为1，表示当前种植间距下可以种植的树苗数量。
 * 初始化一个当前位置变量curPos为positions[0]，表示当前种植的位置。
 * 使用一个循环，从第二个位置开始遍历坐标位置数组positions。
 * 如果当前位置与前一个位置的距离大于等于种植间距，说明可以在当前位置种植树苗，将计数变量count加1，并更新当前位置变量curPos为当前位置。
 * 如果当前种植间距下可以种植的树苗数量大于等于目标树苗数量m，更新答案变量ans为当前种植间距，并将最小种植间距的最小值更新为当前种植间距加1，以便继续搜索更大的种植间距。
 * 否则，将最小种植间距的最大值更新为当前种植间距减1，以便继续搜索更小的种植间距。
 * 循环结束后，输出最佳的最小种植间距ans。
 * ————————————————
 * 版权声明：本文为CSDN博主「算法大师」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
 * 原文链接：https://blog.csdn.net/banxia_frontend/article/details/131096963
 * ————————————————
 *
 */
/*
输入：
7
1 5 3 6 10 7 13
3
输出：
6

 */
public class PlantTrees {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 输入适合种树的坐标数量
        int n = scanner.nextInt();

        // 输入适合种树的坐标位置
        int[] positions = new int[n];
        for (int i = 0; i < n; i++) {
            positions[i] = scanner.nextInt();
        }

        // 输入树苗的数量
        int m = scanner.nextInt();

        // 将坐标位置排序
        Arrays.sort(positions);

        // 初始化最小种植间距的最小值和最大值
        int minDistanceMin = 1, minDistanceMax = positions[n - 1] - positions[0];

        int ans = 0;

        //  最佳的最小种植间距
        for (int minDistance = minDistanceMin; minDistance <= minDistanceMax; minDistance++) {
            int count = 1;
            int curPos = positions[0];

            // 计算当前种植间距下可以种植的树苗数量
            for (int i = 1; i < n; i++) {
                if (positions[i] - curPos >= minDistance) {
                    count++;
                    curPos = positions[i];
                }
            }

            // 如果当前种植间距下可以种植的树苗数量大于等于目标树苗数量，更新答案并继续搜索更大的种植间距
            if (count >= m) {
                ans = minDistance;
                minDistanceMin = minDistance + 1;
            } else {
                minDistanceMax = minDistance - 1;
            }
        }

        // 输出最佳的最小种植间距
        System.out.println(ans);
    }
}

package logical;

import java.util.*;

/**
 * 题目描述
 * 学校组织活动，将学生排成一个矩形方阵。
 * 请在矩形方阵中找到最大的位置相连的男生数量。
 * 这个相连位置在一个直线上，方向可以是水平的，垂直的，成对角线的或者呈反对角线的。
 *  注：学生个数不会超过10000
 *
 * 输入描述
 *  输入的第一行为矩阵的行数和列数，接下来的n行为矩阵元素，元素间用”,”分隔。
 *
 * 输出描述
 *  输出一个整数，表示矩阵中最长的位置相连的男生个数。
 * ————————————————
 * 版权声明：本文为CSDN博主「算法大师」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
 * 原文链接：https://blog.csdn.net/banxia_frontend/article/details/129805545
 *
 * 用例
 * 输入
 * 3,4
 * F,M,M,F
 * F,M,M,F
 * F,F,F,M
 * 输出
 * 3
 */
public class StudentsTeam {
    public static void getMaxConnected(List<List<String>> students, int row, int column, List<Integer> res) {
        int len = 1; // 初始化连续的M的个数为1
        int a = 0, b = 0; // 初始化行和列的索引
        int m = students.size(), n = students.get(0).size(); // 获取方阵的行数和列数
        if (column < n) {  // 从左往右搜索
            a = row;
            b = column;
            while (b < n - 1 && students.get(a).get(++b).equals("M")) { // 不越界且下一个元素为M
                len++; // 连续的M的个数加1
            }
            res.add(len); // 把连续的M的个数加入结果数组
            len = 1; // 重新初始化连续的M的个数为1
        }
        if (row < m) {  // 从上往下搜索
            a = row;
            b = column;
            while (a < m - 1 && students.get(++a).get(b).equals("M")) { // 不越界且下一个元素为M
                len++; // 连续的M的个数加1
            }
            res.add(len); // 把连续的M的个数加入结果数组
            len = 1; // 重新初始化连续的M的个数为1
        }
        if (row < m && column < n) {  // 对角线搜索
            a = row;
            b = column;
            while ((a < m - 1 && b < n - 1) && students.get(++a).get(++b).equals("M")) { // 不越界且下一个元素为M
                len++; // 连续的M的个数加1
            }
            res.add(len); // 把连续的M的个数加入结果数组
            len = 1; // 重新初始化连续的M的个数为1
        }
        if (row >= 0 && column < n) {  // 从右往左搜索
            a = row;
            b = column;
            while ((a > 0 && b < n - 1) && students.get(--a).get(++b).equals("M")) { // 不越界且下一个元素为M
                len++; // 连续的M的个数加1
            }
            res.add(len); // 把连续的M的个数加入结果数组
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // 输入
        String input_str = scanner.nextLine();

        int row = Integer.parseInt(input_str.substring(0, input_str.indexOf(",")));
        int column = Integer.parseInt(input_str.substring(input_str.indexOf(",") + 1));
        // 初始化方阵
        List<List<String>> students = new ArrayList<>();
        for (int i = 0; i < row; i++) {
            String student_str = scanner.nextLine();
            List<String> temp = new ArrayList<>();

            while (student_str.contains(",")) { // 当字符串中还有逗号
                int found = student_str.indexOf(",");
                temp.add(student_str.substring(0, found)); // 把逗号前面的字符串加入临时数组
                student_str = student_str.substring(found + 1); // 更新字符串，去掉逗号和前面的字符串
            }
            temp.add(student_str); // 把最后一个字符串加入临时数组
            students.add(temp); // 把临时数组加入方阵
        }

        List<Integer> max_res = new ArrayList<>(); // 初始化结果数组
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                // 遇到M则开始找
                if (students.get(i).get(j).equals("M")) { // 如果当前元素为M
                    getMaxConnected(students, i, j, max_res); // 在四个方向上搜索连续的M
                }
            }
        }
        Collections.sort(max_res); // 对结果数组排序
        System.out.println(max_res.get(max_res.size() - 1)); // 输出最大的连续的M的个数

        scanner.close();
    }
}

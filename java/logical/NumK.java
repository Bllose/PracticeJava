package logical;

import java.util.*;

/**
 * 题目描述
 * 给定参数n，从1到n会有n个整数：1,2,3,…,n,这n个数字共有n!种排列。
 * 按大小顺序升序列出所有排列的情况，并一一标记，
 * 当n=3时,所有排列如下:
 * “123” “132” “213” “231” “312” “321”
 * 给定n和k，返回第k个排列。
 *
 * 输入描述
 * 输入两行，第一行为n，第二行为k，
 * 给定n的范围是[1,9],给定k的范围是[1,n!]。
 *
 * 输出描述
 * 输出排在第k位置的数字。
 * ————————————————
 * 版权声明：本文为CSDN博主「算法大师」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
 * 原文链接：https://blog.csdn.net/banxia_frontend/article/details/130021165
 */
/*
输入
3
3
输出
213
 */
public class NumK {
    public static void main(String[] args) {
        int n, k;
        Vector<String> lines = new Vector<String>();
        Scanner scanner = new Scanner(System.in);

        // 读取 n 和 k
        n = scanner.nextInt();
        k = scanner.nextInt();

        // 如果 n 等于 1，则直接输出 1 并结束程序
        if (n == 1) {
            System.out.println("1");
            return;
        }

        // 初始化 nums 数组，存储 1 到 n 的整数
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = i + 1;
        }

        // 初始化结果列表
        List<String> result = new ArrayList<>();

        // 递归函数，用于生成所有排列
        generatePermutations(nums, "", result, k);

        // 对结果列表进行排序
        Collections.sort(result);

        // 输出第k个排列
        System.out.println(result.get(k - 1));
    }

    public static void generatePermutations(int[] nums, String current, List<String> result, int k) {
        // 如果数字数组为空，将当前结果添加到结果列表中
        if (nums.length == 0) {
            result.add(current);
            return;
        }

        // 遍历当前数字数组
        for (int i = 0; i < nums.length; i++) {
            // 取出一个数字
            int num = nums[i];

            // 创建新的数字数组，删除当前数字
            int[] newNums = new int[nums.length - 1];
            for (int j = 0; j < i; j++) {
                newNums[j] = nums[j];
            }
            for (int j = i + 1; j < nums.length; j++) {
                newNums[j - 1] = nums[j];
            }

            // 递归调用函数，传递更新后的数字数组和结果字符串
            generatePermutations(newNums, current + num, result, k);

            // 如果结果列表长度等于k，直接返回
            if (result.size() == k) {
                return;
            }
        }
    }
}

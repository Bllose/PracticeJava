package logical;

import java.util.*;

/**
 * 题目描述
 * 给定一个由若干整数组成的数组nums，请检查数组是否是由某个子数组重复循环拼接而成，请输出这个最小的子数组。
 *
 * 输入描述
 * 第一行输入数组中元素个数n，1 ≤ n ≤ 100000
 * 第二行输入数组的数字序列nums，以空格分割，0 ≤ nums[i] < 10
 *
 * 输出描述
 * 输出最小的子数组的数字序列，以空格分割；
 *
 * 备注
 * 数组本身是其最大的子数组，循环1次可生成的自身；
 *
 * 用例
 * 输入
 * 9
 * 1 2 1 1 2 1 1 2 1
 * 输出
 * 1 2 1
 * 说明
 * 数组[1,2,1,1,2,1,1,2,1] 可由子数组[1,2,1]重复循环3次拼接而成
 * ————————————————
 * 版权声明：本文为CSDN博主「算法大师」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
 * 原文链接：https://blog.csdn.net/banxia_frontend/article/details/131465563
 */
/*
9
1 2 1 1 2 1 1 2 1
 */
public class MinimalCycal {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = scanner.nextInt();
        }

        int[] pattern = findPattern(nums);
        for (int num : pattern) {
            System.out.print(num + " ");
        }
    }

    public static int[] findPattern(int[] nums) {
        int n = nums.length;
        int[] next = new int[n + 1];
        next[0] = -1;
        int i = 0, j = -1;
        while (i < n) {
            if (j == -1 || nums[i] == nums[j]) {
                i++;
                j++;
                next[i] = j;
            } else {
                j = next[j];
            }
        }

        int patternLength = n - next[n];
        int[] pattern = new int[patternLength];
        for (int k = 0; k < patternLength; k++) {
            pattern[k] = nums[k];
        }

        return pattern;
    }
}

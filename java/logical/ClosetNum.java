package logical;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 题目描述
 * 给定一个数组X和正整数K，请找出使表达式X[i] - x[i +1] … - X[i + K 1]，结果最接近于数组中位数的下标i，如果有多个i满足条件，请返回最大的i。
 * 其中，数组中位数:长度为N的数组，按照元素的值大小升序排列后，下标为N/2元素的值
 * 补充说明:
 * 1.数组X的元素均为正整数;
 * 2.X的长度n取值范围: 2<= n <= 1000;
 * 3.K大于0且小于数组的大小;
 * 4.i的取值范围: 0 <=i < 1000;
 * 5.题目的排序数组X[N]的中位数是X[N/2].
 *
 * 用例
 * 输入:
 * [50,50,2,3],2
 * 输出:
 *  1
 * 说明:
 * 1、中位数为50: [50,50,2,3]升序排序后变成[2,3,50,50]，中位数为下标4/2=2的元素50;
 * 2、计算结果为1: X[50,50,2,3]根据题目计算X[i] - …- X[i + K- 1]得出三个数
 *  0 (X[0]-X[1]= 50 -50) 、
 *  48 (X[1]-X[2] = 50 -2)
 *  -1 (X[2]-X[3]= 2-3) ，
 *
 * 其中48最接近50，因此返回下标1
 * ————————————————
 * 版权声明：本文为CSDN博主「算法大师」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
 * 原文链接：https://blog.csdn.net/banxia_frontend/article/details/131466725
 */
/*
[50,50,2,3],2

[1,2,3,4,5],2

[10,20,30,40,50],3

[100,200,300,400,500],4

[5,10,15,20,25],2

[1,2,3,4,5,6,7,8,9,10],4

[1,3,5,7,9],2
 */
public class ClosetNum {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String[] input_str = in.nextLine().replace("[","").replace("]","").split(",");
        int n = input_str.length - 1;
        int[] nums = new int[n];
        int k = Integer.valueOf(input_str[n]);
        for(int i=0; i<n; i++){
            nums[i] = Integer.valueOf(input_str[i]);
        }

        int[] sorted_nums = Arrays.copyOf(nums, n);
        Arrays.sort(sorted_nums);
        int median = sorted_nums[n/2];  // 计算中位数

        int minDiff = Integer.MAX_VALUE;  // 初始化最小差值为最大整数
        int result = -1;  // 初始化结果为-1
        for(int i=0; i<=n-k; i++){  // 遍历数组
            int count = nums[i];  // 初始化计数器为当前元素
            for(int j=i+1; j<i+k; j++){  // 计算表达式X[i] - x[i +1] ... - X[i + K  1]
                count -= nums[j];
            }
            int diff = Math.abs(count - median);  // 计算当前差值
            if(diff < minDiff){  // 如果当前差值小于最小差值
                minDiff = diff;  // 更新最小差值
                result = i;  // 更新结果为当前下标
            } else if (diff == minDiff) {  // 如果当前差值等于最小差值
                result = Math.max(result, i);  // 更新结果为最大的下标
            }
        }

        System.out.println(result);  // 输出结果
    }
}

package longest;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * 最长子字符串的长度（一）
 *
 * 【最长子字符串的长度(一)】给你一个字符串s,字符串s首尾相连成一个环形，请你在环中找出'o'字符出现了偶数次最长子字符串的长度。
 * 输入描述：
 *  输入是一串小写字母组成的字符串
 * 输出描述：
 *  输出是一个整数
 * 备注：
 *  1<=s.length<=5×10^5
 *  s只包含小写英文字母。
 * 示例1:
 * 输入
 *  alolobo
 * 输出
 *  6
 */

public class longestString {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String[] input = in.nextLine().split("");

        int len = input.length;

        if(len == 1) {
            if(input[0].equals("o")) {
                System.out.println("1");
            } else {
                System.out.println("0");
            }
            return;
        }

        Queue<Integer> q = new LinkedList<>();
        int recorder = 0; // 记录双o队列总长度
        int recorder1 = 0;// 当前o队列长度
        int max = 0; // 记录最长的双o队列长度
        boolean continuing = true;
        boolean theSecondTime = false;
        int index = 0;

        while(continuing || !theSecondTime) {
            String cur = input[index];
            recorder1 ++; // 记录当前o队列长度
            recorder ++; // 记录总长度
            if(!cur.equals("o")) {
                continuing = true;
                if(cur.equals("_")) {
                    theSecondTime = true; // 作为停止循环的判断条件之一
                } else {
                    input[index] = "_"; // 标记已经读过
                }
            } else {
                continuing = false; // 作为停止循环的判断条件之一
                q.add(recorder1); // 记录当前o队列长度
                recorder1 = 0; // 准备记录下一个o队列长度
                if(q.size() > 2) {
                    recorder -= q.poll(); // 将前前次o队列剔除出总长度
                }
            }
            if(max < recorder) {
                max = recorder;
            }
            index ++; // 指针向后挪一格
            if(index >= len) {
                index = 0; // 当遍历到数组末端，则从头开始
            }
        }

        System.out.println(max);
    }
}

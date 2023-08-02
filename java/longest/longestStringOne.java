package longest;

import java.util.ArrayDeque;
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
public class longestStringOne {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        char[] inputHolder = in.nextLine().toCharArray();

        processer(inputHolder);
    }

    /*
    优化内容
    1、通过取余将顺序遍历转为循环遍历
    2、过程中我们仅需要将多出来的o队列长度从总长度中剔除，故只需要将o队列长度算出即可。遍历队列时我们直接保存顺序值即可，无需考虑循环队列是否匹配数组的逻辑位置
     */
    public static void processer(char[] inputHolder) {
        int LEN = inputHolder.length;
        Queue<Integer> point = new ArrayDeque<>();
        point.add(0);

        int max = 0;
        int current = 0;

        for(int i = 0; i < LEN * 2; i ++) {
            current ++; // 统计当前队列长度
            char cur = inputHolder[i%LEN];
            if(cur == 'o') {
                point.add(i);
                point.add(i+1);
            }
            if(point.size()>6) {
                int start = point.poll();
                current -= (point.poll() - start + 1);
            }
            max = current > max?current:max; // 更最长记录做比较，留下最长记录
        }

        System.out.println(max);
    }
}

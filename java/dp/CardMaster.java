package dp;

import java.util.Scanner;

/**
 * 题目描述：
 *  给定一个长度为n的整型数组，表示一个选手在n轮内可选择的牌面分数。选手基于规则选牌，
 *  请计算所有轮结束后其可以获得的最高总分数。
 *  选择规则如下：
 *      1. 在每轮里选手可以选择获取该轮牌面，则其总分数加上该轮牌面分数，为其新的总分数。
 *      2. 选手也可不选择本轮牌面直接跳到下一轮，此时将当前总分数还原为3轮前的总分数，
 *         若当前轮次小于等于3（即在第1、2、3轮选择跳过轮次），则总分数置为0。
 *      3. 选手的初始总分数为0，且必须依次参加每一轮
 * 输入描述：
 *  第一行为一个小写逗号分割的字符串，表示n轮的牌面分数，1<= n <=20。
 *  分数值为整数，-100 <= 分数值 <= 100。不考虑格式问题。
 * 输出描述：
 *  所有轮结束后选手获得的最高总分数。
 */
public class CardMaster {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String[] inputHolder = in.nextLine().split(",");

        processer(inputHolder);
    }

    public static void processer(String[] input) {
        int[] holder = new int[input.length];
        for(int i = 0; i < input.length; i ++) {
            int cur = Integer.parseInt(input[i]);
            if(i < 3) {
                holder[i] = Math.max(i>0 ? cur+holder[i - 1] : cur, 0);
            } else {
                holder[i] = Math.max(cur + holder[i - 1], holder[i - 3]);
            }
        }
        System.out.println(holder[holder.length - 1]);
    }
}

package rearrangement;

import java.util.Scanner;

/*
 * 喊7的次数重排
 *
 * 喊7是一个传统的聚会游戏，N个人围成一圈，按顺时针从1到N编号。
 * 编号为1的人从1开始喊数，下一个人喊的数字为上一个人的数字加1,但是当将要喊出来的数字是7的倍数或者数字本身含有7的话，不能把这个数字直接喊出来，而是要喊"过"。
 * 假定玩这个游戏的N个人都没有失误地在正确的时机喊了"过",当喊到数字K时，可以统计每个人喊"过"的次数。
 *
 * 现给定一个长度为N的数组，存储了打乱顺序的每个人喊"过”的次数，请把它还原成正确的顺序，即数组的第1个元素存储编号的人喊"过"的次数。
 * 输入描述：
 *   输入为一行，为空格分隔的喊"过"的次数，注意K并不提供，K不超过200,而数字的个数即为N。
 * 输出描述：
 *   输出为一行，为顺序正确的喊"过"的次数，也由空格分隔。
 * 示例1:
 * 输入
 *   010
 * 输出
 *   100
 * */
public class SevenEncounterOne {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String[] inputHolder = in.nextLine().split(" ");
        int[] inputIntHolder = new int[inputHolder.length];

        for(int i = 0 ; i < inputHolder.length; i ++) {
            inputIntHolder[i] = Integer.valueOf(inputHolder[i]);
        }

        processor(inputIntHolder);
    }

    public static void processor(int[] input) {
        int sum = 0; // 获取喊7总次数
        for(int i = 0; i < input.length; i ++) {
            sum += input[i];
            input[i] = 0; // 制0，用于记录喊7记录
        }

        for(int i = 1; sum > 0; i ++ ) {
            if(i%7 == 0 || String.valueOf(i).indexOf("7") > 0) {
                input[i%input.length-1] ++;
                sum --;
            }
        }

        for(int cur: input) {
            System.out.print(cur + " ");
        }
    }
}

package rearrangement;

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

import java.util.Arrays;
import java.util.Scanner;

/**
 * 总体思路：
 *  1、输入位数确定窗口大小，输入最大值确定我们的目标。
 *  2、通过窗口平移，遍历喊7过程。每次喊7都对比一下目标。
 *  3、达到目标则直接输出窗口所记录的喊7数组。
 *
 *  该思路核心是窗口平移，但是问题在于未考虑目标的重复性。即最大目标可能出现多次。
 */
public class SevenEncounter {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Integer theMax = 0;
        String inputString = in.nextLine();
        String[] inputArray = inputString.split(" ");
        int len = inputArray.length;
        for(int i = 0 ; i < inputArray.length; i ++){
            int inputNumber = Integer.valueOf(inputArray[i]);
            if( inputNumber > theMax ) {
                theMax = inputNumber;
            }
        }

        Integer[] window = new Integer[len];
        int[] recorder = new int[len];
        Arrays.fill(recorder, 0);
        for(int i = 0 ; i < len; i ++) {
            window[i] = i + 1;
        }

        boolean isFirstTime = true;
        boolean continuing = true;
        while(continuing) {
            if(!isFirstTime) {
                for(int i = 0 ; i < len; i ++) {
                    window[i] = window[i] + len;
                }
            } else {
                isFirstTime = false;
            }

            for(int i = 0 ; i < len; i ++) {
                int currect = window[i];
                if(forSure(currect)) {
                    recorder[i] ++;
                    if( recorder[i] >= theMax) {
                        continuing = false;
                        break;
                    }
                }
            }
        }

        for(int i = 0; i < len; i ++) {
            System.out.print(recorder[i] + " ");
        }
    }

    public static boolean forSure(int currect) {
        if(currect % 7 == 0) {
            return true;
        }

        while(currect > 6) {
            int temp = currect % 10;
            if(temp == 7) {
                return true;
            }
            currect = currect / 10;
        }
        return false;
    }
}

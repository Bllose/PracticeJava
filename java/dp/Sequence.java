package dp;

import java.util.Scanner;

/**
 * 题目描述：
 *  有一个数列a[N] (N=60)，从a[0]开始，每一项都是一个数字。数列中a[n+1]都是a[n]的描述。其中a[0]=1。
 * 规则如下:
 *  a[0]:1
 *  a[1]:11(含义：其前一项a[0]=1是1个1，即“11”。表示a[0]从左到右，连续出现了1次“1”）
 *  a[2]:21(含义：其前一项a[1]=11，从左到右：是由两个1组成，即“21”。表示a[1]从左到右，连续出现了两次“1”)
 *  a[3]:1211(含义：其前一项a[2]=21，从左到右：是由一个2和一个1组成，即“1211”。表示a[2]从左到右，连续出现了1次“2”，然后又连续出现了1次“1”)
 *  a[4]:111221(
 *      含义：其前一项a[3]=1211，从左到右：是由一个1、一个2、两个1组成，即“111221”。
 *      表示a[3]从左到右，连续出现了1次“1”，连续出现了1次“2”，连续出现了两次“1”
 *              )
 *  请输出这个数列的第n项结果（a[n]，0≤n≤59）。
 * 输入描述:
 *  数列的第n项(0≤n≤59)
 *  4
 * 输出描述:
 *  数列的内容
 *  111221
 */
public class Sequence {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int times = in.nextInt();

        processer(times);
    }

    public static void processer(int times) {
        String descripe = "1";
        while(times > 0) {
            char[] holder = descripe.toCharArray();
            descripe = "";
            int recorder = 1;
            for(int i = 0 ; i < holder.length; i ++) {
                if(i + 1 == holder.length) {
                    descripe += recorder + "" + holder[i];
                } else {
                    if (holder[i] != holder[i + 1]) {
                        descripe += recorder + "" + holder[i];
                        recorder = 1;
                    } else {
                        recorder ++;
                    }
                }
            }
            times --;
        }
        System.out.println(descripe);
    }
}

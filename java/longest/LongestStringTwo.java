package longest;

import java.util.Scanner;

/**
 * @since 2022-04
 */
public class LongestStringTwo {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        char[] chrs = input.toCharArray();
        int len = chrs.length;
        int num = 0;
        for (char chr : chrs) {
            if (chr == 'o') {
                num += 1;
            }
        }
        if (num % 2 == 0) {
            System.out.println(len);
        } else {
            //出现了奇数次
            System.out.println(len - 1);
        }
    }
}

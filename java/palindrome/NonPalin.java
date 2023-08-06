package palindrome;

import java.util.Scanner;

/*
【没有回文串】
回文串的定义：正读和反读都一样的字符串
    现在已经存在一个不包含回文串的字符串，字符串的字符都是在英语字母的前N个，且字符串不包含任何长度大于等于2的回文串；
    请找出下一个字典序的不包含回文串的、字符都是在英语字母的前N个、且长度相同的字符串。如果不存在，请输出NO。
输入描述：
    输入包括两行。
    第一行有一个整数：N(1<=Nk=26),表示字符串的每个字符范围都是前N的英语字母。
    第二行输入一个字符串(输入长度<=10000),输入保证这个字符串是合法的并且没有包含回文串。
输出描述：
    输出下一个字典序的不包含回文串的、字符都是在英语字母的前N个、且长度相同的字符串；如果不存在，请输出"NO"。
示例1:
输入
    3
    cba
输出
    NO

 */
public class NonPalin {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int PLACES = Integer.valueOf(in.nextLine());
        char[] inputHolder = in.nextLine().toCharArray();
        int[] input = new int[inputHolder.length];
        for(int i = 0 ; i < inputHolder.length; i ++) {
            input[i] = inputHolder[i];
        }
        int MAX = 'a' + PLACES;
        int[] holder = new int[inputHolder.length];

        String ans = dfs(input, 0, MAX, holder, 0);
    }

    public static String dfs(int[] input, int level, int MAX, int[] holder, int postion) {
        int cur = input[postion];
        String ans = null;

        if(postion == input.length) {
            if(String.valueOf(input).equals(String.valueOf(holder))) {
                return null;
            } else {
                return String.valueOf(holder);
            }
            if(postion > 0){
                if(postion > 1 && input[postion - 1] != cur) {
                        if(postion > 2 && input[postion - 2] != cur) {
                            ans = dfs(input, 0, MAX, holder, postion + 1);
                        }
                    }
                }
                if(ans == null) {
                    ans = dfs(input, level + 1, MAX, holder, postion);
                }
            } else {
                holder[postion] = cur;
                ans = dfs(input, 0, MAX, holder, postion + 1);
            }
        }
    }
}

package logical;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 题目描述
 * 已知火星人使用的运算符为#、$，其与地球人的等价公式如下：
 *
 * x#y = 2*x+3*y+4
 *
 * x$y = 3*x+y+2
 *
 * 其中x、y是无符号整数
 * 地球人公式按C语言规则计算
 * 火星人公式中，$的优先级高于#，相同的运算符，按从左到右的顺序计算
 * 现有一段火星人的字符串报文，请你来翻译并计算结果。
 *
 * 输入描述
 * 火星人字符串表达式（结尾不带回车换行）
 *
 * 输入的字符串说明：字符串为仅由无符号整数和操作符（#、$）组成的计算表达式。
 *
 * 例如：123#4$5#67$78。
 *
 * 用例保证字符串中，操作数与操作符之间没有任何分隔符。
 * 用例保证操作数取值范围为32位无符号整数。
 * 保证输入以及计算结果不会出现整型溢出。
 * 保证输入的字符串为合法的求值报文，例如：123#4$5#67$78
 * 保证不会出现非法的求值报文，例如类似这样字符串：
 * #4$5 //缺少操作数
 *
 * 4$5# //缺少操作数
 *
 * 4#$5 //缺少操作数
 *
 * 4 $5 //有空格
 *
 * 3+4-5*6/7 //有其它操作符
 *
 * 12345678987654321$54321 //32位整数计算溢出
 *
 * 输出描述
 * 根据输入的火星人字符串输出计算结果（结尾不带回车换行）。
 * ————————————————
 * 版权声明：本文为CSDN博主「算法大师」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
 * 原文链接：https://blog.csdn.net/banxia_frontend/article/details/130116171
 */
/*
输入：
7#6$5#12
输出:
226
 */
public class MarsComputing {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();

        String[] nums = s.split("#");
        for(int j = 0; j < nums.length; j ++) {
            String cur = nums[j];
            if(cur.contains("$")) {
                int[] curNums = Arrays.stream(cur.split("\\$")).mapToInt(Integer::parseInt).toArray();
                int x = curNums[0];
                for(int i = 1; i < curNums.length; i++) {
                    x = 3*x+ curNums[i] +2;
                }
                nums[j] = x + "";
            }
        }

//        // 匹配$运算符
//        while (s.contains("$")) {
//            int index = s.indexOf("$");
//            int x = Integer.parseInt(s.substring(index - 1, index));
//            int y = Integer.parseInt(s.substring(index + 1, index + 2));
//            int temp = 3 * x + y + 2;
//            s = s.substring(0, index - 1) + temp + s.substring(index + 2);
//        }
//
//        // 按#分割字符串成数字数组
//        String[] nums = s.split("#");
        int x = Integer.parseInt(nums[0]);
        for (int i = 1; i < nums.length; i++) {
            int y = Integer.parseInt(nums[i]);
            x = 2 * x + 3 * y + 4;
        }

        System.out.println(x);
    }

}

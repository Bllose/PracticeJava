package logical;

import java.util.Scanner;
import java.util.Vector;

/**
 * 题目描述
 * 警察在侦破一个案件时，得到了线人给出的可能犯罪时间，形如 “HH:MM” 表示的时刻。
 * 根据警察和线人的约定，为了隐蔽，该时间是修改过的，
 * 解密规则为：利用当前出现过的数字，构造下一个距离当前时间最近的时刻，则该时间为可能的犯罪时间。
 * 每个出现数字都可以被无限次使用。
 *
 * 输入描述
 *  形如HH:SS字符串，表示原始输入。
 *
 * 输出描述
 *  形如HH:SS的字符串，表示推理处理的犯罪时间。
 *
 * 备注
 *  1.可以保证现任给定的字符串一定是合法的。
 *  例如，“01:35”和“11:08”是合法的，“1:35”和“11:8”是不合法的。
 *  2.最近的时刻可能在第二天。
 */
public class CrimeTime {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String inputStr = scanner.next();
        scanner.close();

        // 提取时间中的数字
        Vector<Integer> num = new Vector<Integer>();
        for (int i = 0; i < inputStr.length(); i++) {
            if (inputStr.charAt(i) != ':') {
                num.add(inputStr.charAt(i) - '0');
            }
        }

        // 转为分钟数
        int totalMinute = (num.get(0) * 10 + num.get(1)) * 60 + (num.get(2) * 10 + num.get(3));
        // 保存最小时间间隔
        int minDiff = 24 * 60 * 60;
        String result = "";

        // 枚举
        for (int index0 = 0; index0 < num.size(); index0++) {
            // 小时首位大于2
            if (num.get(index0) > 2)
                continue;
            for (int index1 = 0; index1 < num.size(); index1++) {
                // 小时次位大于3
                if (num.get(index0) == 2 && num.get(index1) > 3)
                    continue;
                for (int index2 = 0; index2 < num.size(); index2++) {
                    // 分钟位只能是0-5，不可能出现6-9
                    if (num.get(index2) > 5)
                        continue;
                    for (int index3 = 0; index3 < num.size(); index3++) {
                        int newTime = (num.get(index0) * 10 + num.get(index1)) * 60 + (num.get(index2) * 10 + num.get(index3));
                        if (newTime < totalMinute) {
                            // 如果比当前时间还要早，则把它算到下一天去。
                            newTime += 24 * 60 * 60;
                        } else if (newTime == totalMinute) {
                            continue;
                        }
                        // 找出最小的时刻。
                        if (newTime - totalMinute < minDiff) {
                            minDiff = newTime - totalMinute;
                            result = String.valueOf(num.get(index0)) + String.valueOf(num.get(index1)) + ":" +
                                    String.valueOf(num.get(index2)) + String.valueOf(num.get(index3));
                        }
                    }
                }
            }
        }
        System.out.println(result);
    }
}


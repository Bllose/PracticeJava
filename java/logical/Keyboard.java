package logical;

import java.util.*;
import java.io.*;

/**
 * 题目描述
 * [九宫格]按键输入，输出显示内容，有英文和数字两个模式，默认是数字模式，数字模式直接输出数字，
 * 英文模式连续按同一个按键会依次出现这个按键上的字母，如果输入”/”或者其他字符，则循环中断。
 *
 * 字符对应关系如图：
 *
 *
 *
 * 要求输入一串按键，输出屏幕显示。
 *
 * 输入描述
 *  输入范围为数字 0~9 和字符’#’、’/’，输出屏幕显示，例如，
 *  在数字模式下，输入 1234，显示 1234
 *  在英文模式下，输入 1234，显示,adg
 *
 * 输出描述
 *  #用于切换模式，默认是数字模式，执行#后切换为英文模式；
 *  /表示延迟，例如在英文模式下，输入 22/222，显示为 bc；
 *  英文模式下，多次按同一键，例如输入 22222，显示为 b；
 */
public class Keyboard {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input_str = reader.readLine();

        // 九宫格枚举信息
        Map<Character, String> char_map = new HashMap<>();
        char_map.put('0', " ");
        char_map.put('1', ",.");
        char_map.put('2', "abc");
        char_map.put('3', "def");
        char_map.put('4', "ghi");
        char_map.put('5', "jkl");
        char_map.put('6', "mno");
        char_map.put('7', "pqrs");
        char_map.put('8', "tuv");
        char_map.put('9', "wxyz");

        StringBuilder res = new StringBuilder();
        // 默认是数字模式
        int mode = 0;

        for (int i = 0; i < input_str.length(); i++) {
            char c = input_str.charAt(i);
            if (Character.isDigit(c)) { // 如果是数字
                if (mode == 0) { // 如果是数字模式，直接加入结果
                    res.append(c);
                } else if (mode == 1) { // 如果是字母模式
                    int j = i;
                    String tempstr = char_map.get(c);
                    while (j < input_str.length() && input_str.charAt(j) == c) { // 统计连续出现的数字个数
                        j++;
                    }
                    int index = (j - i - 1) % tempstr.length(); // 计算对应的字母下标
                    res.append(tempstr.charAt(index)); // 加入结果
                    i = j - 1; // 跳过已经处理的数字
                }
            } else if (c == '#') { // 如果是切换模式符号
                mode = (mode + 1) % 2; // 切换模式
            } else if (c == '/') { // 如果是延迟符号，不做处理
                // 延迟，不做处理
            } else { // 如果是其他字符，直接退出循环
                break;
            }
        }
        System.out.println(res.toString()); // 输出结果
    }
}

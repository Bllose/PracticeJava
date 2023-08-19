package logical;

import java.util.*;

/**
 * 题目描述
 *  为了提升数据传输的效率，会对传输的报文进行压缩处理。
 *  输入一个压缩后的报文，请返回它解压后的原始报文。
 *  压缩规则：n[str]，表示方括号内部的 str 正好重复 n 次。
 *  注意 n 为正整数（0 < n <= 100），str只包含小写英文字母，不考虑异常情况。
 * 输入描述
 * 输入压缩后的报文：
 * 1）不考虑无效的输入，报文没有额外的空格，方括号总是符合格式要求的；
 * 2）原始报文不包含数字，所有的数字只表示重复的次数 n ，例如不会出现像 5b 或 3[8] 的输入；
 *
 * 输出描述
 * 解压后的原始报文
 *
 * 注：原始报文长度不会超过1000，不考虑异常的情况
 * ————————————————
 * 版权声明：本文为CSDN博主「算法大师」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
 * 原文链接：https://blog.csdn.net/banxia_frontend/article/details/129786100
 */
/*
输入：
3[k]2[mn]
输出：
kkkmnmn

输入：
3[m2[c]]
输出：
mccmccmcc
 */
public class Compressed {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String compressed_string = scanner.next();

        Stack<List<String>> stack = new Stack<>();
        stack.push(Arrays.asList(new String[]{"", "1", ""})); // 使用栈来存储解压后的字符串和重复次数

        String current_str = ""; // 当前字符
        String current_num = ""; // 当前重复次数

        for (char c : compressed_string.toCharArray()) {
            if (Character.isLetter(c)) { // 如果是字母
                current_str += c;
            } else if (Character.isDigit(c)) { // 如果是数字
                current_num += c;
            } else if (c == '[') { // 如果是左括号
                stack.push(new ArrayList<>(Arrays.asList(new String[]{current_str, current_num, ""}))); // 将当前字符和重复次数入栈
                current_str = current_num = ""; // 重置当前字符和重复次数
            } else { // 如果是右括号
                List<String> prev = stack.pop();
                String prev_str = prev.get(0);
                int times = Integer.parseInt(prev.get(1));
                String prev_result = prev.get(2);

                String repeated_str = "";
                for (int i = 0; i < times; i++) {
                    repeated_str += prev_result + current_str;
                }

                stack.peek().set(2, stack.peek().get(2) + prev_str + repeated_str); // 更新栈顶元素的结果
                current_str = ""; // 重置当前字符
            }
        }

        String result = stack.peek().get(2) + current_str; // 返回最终的结果
        System.out.println(result);
    }
}

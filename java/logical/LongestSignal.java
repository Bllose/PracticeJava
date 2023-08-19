package logical;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * 题目描述:最长的完全交替连续方波信号
 * 输入一串方波信号，求取最长的完全连续交替方波信号，并将其输出，如果有相同长度的交替方波信号，输出任一即可，
 * 方波信号高位用1标识，低位用0标识
 * 说明：
 * 1） 一个完整的信号一定以0开始然后以0结尾，即010是一个完整信号，但101，1010，0101不是
 * 2）输入的一串方波信号是由一个或多个完整信号组成
 * 3） 两个相邻信号之间可能有0个或多个低位，如0110010，011000010
 * 4） 同一个信号中可以有连续的高位，如01110101011110001010，前14位是一个具有连续高位的信号
 * 5） 完全连续交替方波是指10交替，如01010是完全连续交替方波，0110不是
 *
 * 输入描述
 * 输入信号字符串（长度 >= 3 且 <= 1024）：
 * 0010101010110000101000010 注：输入总是合法的，不用考虑异常情况
 *
 * 输出描述
 * 输出最长的完全连续交替方波信号串：01010
 * 若不存在完全连续交替方波信号串，输出 -1。
 * ————————————————
 * 版权声明：本文为CSDN博主「算法大师」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
 * 原文链接：https://blog.csdn.net/banxia_frontend/article/details/130918632
 */
/*
输入：
00101010101100001010010
输出：
01010
说明：
输入信号串中有三个信号：
0 010101010110(第一个信号段)
00 01010(第二个信号段)
010(第三个信号段)
第一个信号虽然有交替的方波信号段，但出现了11部分的连续高位，不算完全连续交替方波，
在剩下的连续方波信号串中01010最长
 */
public class LongestSignal {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String signal = scanner.nextLine(); // 输入信号字符串

        process1(signal);
        process2(signal);
    }

    public static void process2(String signal) {
        Map<Integer, String> results = new HashMap<>();

        List<Matcher> matchers = new ArrayList<>();
        matchers.add(Pattern.compile("(?<=0)(01)+0(?=0)").matcher(signal));
        matchers.add(Pattern.compile("^(01)+0(?=0)").matcher(signal));
        matchers.add(Pattern.compile("(?<=0)(01)+0$").matcher(signal));
        matchers.add(Pattern.compile("^(01)+0$").matcher(signal));

        for(Matcher m : matchers){
            getResult(m, results);
        }

        List<Map.Entry<Integer, String>> finalResult = new ArrayList<>(results.entrySet());
        finalResult.sort(Comparator.<Map.Entry<Integer, String>>comparingInt(Map.Entry::getKey).reversed());

        System.out.println(finalResult.get(0).getValue());
    }

    public static void getResult(Matcher m, Map<Integer, String> results) {
        while (m.find()) {
            String result = m.group();
            results.put(result.length(), result);
        }
    }

    public static void process1(String signal) {
        Pattern pattern = Pattern.compile("^(01)+0$"); // 定义正则表达式匹配完全连续交替方波信号

        int maxLength = 0; // 最长完全连续交替方波信号的长度
        String result = "-1"; // 最长完全连续交替方波信号的字符串

        StringBuilder sb = new StringBuilder(); // 用于存储当前处理的信号
        for (char c : signal.toCharArray()) {
            if (c == '0' && sb.length() > 0 && sb.charAt(sb.length() - 1) == '0') { // 当前字符是0，且前一个字符也是0，说明一个完整信号结束
                Matcher matcher = pattern.matcher(sb.toString()); // 对当前信号进行匹配
                if (matcher.find() && sb.length() > maxLength) { // 如果匹配到完全连续交替方波信号，并且长度大于之前的最大长度
                    maxLength = sb.length(); // 更新最大长度
                    result = sb.toString(); // 更新最大长度对应的字符串
                }
                sb.setLength(0); // 清空当前信号
            }
            sb.append(c); // 将当前字符加入当前信号
        }

        Matcher matcher = pattern.matcher(sb.toString()); // 对最后一个信号进行匹配
        if (matcher.find() && sb.length() > maxLength) { // 如果匹配到完全连续交替方波信号，并且长度大于之前的最大长度
            result = sb.toString(); // 更新最大长度对应的字符串
        }

        System.out.println(result); // 输出最长的完全连续交替方波信号串
    }
}


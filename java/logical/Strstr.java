package logical;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 题目描述:增强的strstr
 * C 语言有一个库函数： char *strstr(const char *haystack, const char *needle) ，实现在字符串 haystack 中查找第一次出现字符串 needle 的位置，如果未找到则返回 null。
 *
 * 现要求实现一个strstr的增强函数，可以使用带可选段的字符串来模糊查询，与strstr一样返回首次查找到的字符串位置。
 *
 * 可选段使用“[]”标识，表示该位置是可选段中任意一个字符即可满足匹配条件。比如“a[bc]”表示可以匹配“ab”或“ac”。
 *
 * 注意目标字符串中可选段可能出现多次。
 *
 * 输入描述
 * 与strstr函数一样，输入参数是两个字符串指针，分别是源字符串和目标字符串。
 *
 * 输出描述
 * 与strstr函数不同，返回的是源字符串中，匹配子字符串相对于源字符串地址的偏移（从0开始算），如果没有匹配返回-1。
 *
 * 补充说明：源字符串中必定不包含‘[]’；目标字符串中‘[]’必定成对出现，且不会出现嵌套。
 *
 * 输入的字符串长度在[1,100]之间。
 * ————————————————
 * 版权声明：本文为CSDN博主「算法大师」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
 * 原文链接：https://blog.csdn.net/banxia_frontend/article/details/131609115
 */
/*
输入：
abcd
b[cd]

输出：
1

 */
public class Strstr {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 读取源字符串和目标字符串
        String source = scanner.nextLine();
        String target = scanner.nextLine();

        original(source, target);
        newOne(source, target);
    }

    public static void original(String source, String target) {
        // 将目标字符串中的可选段标记转换为正则表达式的可选字符
        target = target.replaceAll("\\[(.*?)\\]", "[$1]");

        // 编译目标字符串为正则表达式模式
        Pattern pattern = Pattern.compile(target);
        // 创建匹配器，用于在源字符串中查找匹配的子字符串
        Matcher matcher = pattern.matcher(source);

        // 如果找到匹配的子字符串，则输出匹配的子字符串在源字符串中的起始位置
        if (matcher.find()) {
            System.out.println(matcher.start());
        } else {
            // 如果没有找到匹配的子字符串，则输出-1
            System.out.println(-1);
        }
    }

    public static void newOne(String source, String target) {
        // 编译目标字符串为正则表达式模式
        Pattern pattern = Pattern.compile(target);
        // 创建匹配器，用于在源字符串中查找匹配的子字符串
        Matcher matcher = pattern.matcher(source);

        // 如果找到匹配的子字符串，则输出匹配的子字符串在源字符串中的起始位置
        if (matcher.find()) {
            System.out.println(matcher.start());
        } else {
            // 如果没有找到匹配的子字符串，则输出-1
            System.out.println(-1);
        }
    }
}

package string;

import java.util.*;

/**
 * 题目描述
 * 给定一个由多个命令字组成的命令字符串：
 * 1、字符串长度Q小于等于127字节，只包含大小写字母，数字，下划线和偶数个双引号；
 * 2、命令字之间以一个或多个下划线进行分割；
 * 3、可以通过两个双引号””来标识包含下划线的命令字或空命令字(仅包含两个双引号的命令字),双引号不会在命令字内部出现；
 * 请对指定索引的敏感字段进行加密，替换为******(6个*),并删除命令字前后多余的下划线_。
 * 如果无法找到指定索引的命令字，输出字符串ERRORQ。
 * 输入描述
 *  输入为两行，第一行为命令字索引K(从0开始),第二行为命令字符串S。
 * 输出描述
 *  输出处理后的命令字符串，如果无法找到指定索引的命令字，输出字符串ERROR
 */
public class SensitiveField {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int index = Integer.valueOf(in.nextLine());
        String inputString = in.nextLine();

        processer(index, inputString);
    }

    public static void processer(int index, String input) {
        if(index < 0) {
            System.out.println("ERROR");
            return;
        }

        char[] holder = input.toCharArray();
        String commond = "";
        List<String> recordList = new ArrayList<>();

        for(int i = 0; i < holder.length; i ++) {
            char cur = holder[i];
            if( cur == '"' && commond.contains("\"")) {
                // 当前遍历到双引号，且命令对象已经保存了一段双引号命令，说明当前命令已经遍历完成
                commond += "\"";
                recordList.add(commond);
                commond = "";
            } else if (cur == '_' && !commond.contains("\"")) {
                // 当前非双引号命令，且遍历到下划线，则识别为分隔符
                if(commond.length() > 0) {
                    recordList.add(commond);
                    commond = "";
                }
            } else if (i == holder.length - 1) {
                // 遍历到尾部，将最后一个命令写入列表
                commond += cur + "";
                recordList.add(commond);
            } else {
                commond += cur + "";
            }
        }

        if(index > recordList.size()) {
            System.out.println("ERROR");
        } else {
            recordList.set(index, "******");
            StringBuffer sb = new StringBuffer();
            for(String cur: recordList) {
                sb.append("_").append(cur);
            }
            sb.replace(0, 1, "");
            System.out.println(sb);
        }

    }
}

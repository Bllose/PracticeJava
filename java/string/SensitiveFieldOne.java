package string;

import java.util.*;

public class SensitiveFieldOne {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int index = Integer.parseInt(scanner.nextLine()); // 输入命令字索引K
        String input = scanner.nextLine(); // 输入命令字符串S
        char[] charArray = input.toCharArray(); // 将命令字符串转换为字符数组
        String command = ""; // 当前正在解析的命令字
        List<String> commandList = new ArrayList<>(); // 存储解析后的命令字列表

        for (int i = 0; i < charArray.length; i++) {
            char ch = charArray[i];

            if (ch == '"' && command.contains(ch + "")) { // 如果当前字符为双引号且命令字中已经包含了一个双引号
                command += '"'; // 将双引号添加到命令字中
                commandList.add(command); // 将解析完毕的命令字添加到命令字列表中
                command = ""; // 重置命令字
            } else if (!command.contains("\"") && ch == '_') { // 如果命令字不包含双引号且当前字符为下划线
                if (!command.isEmpty()) { // 如果命令字不为空
                    commandList.add(command); // 将解析完毕的命令字添加到命令字列表中
                    command = ""; // 重置命令字
                }
            } else if (i == charArray.length - 1) { // 如果已经到达字符串末尾
                command += ch; // 将当前字符添加到命令字中
                commandList.add(command); // 将解析完毕的命令字添加到命令字列表中
                command = ""; // 重置命令字
            } else {
                command += ch; // 将当前字符添加到命令字中
            }
        }

        if (index < 0 || index > commandList.size() - 1) { // 如果命令字索引超出范围
            System.out.println("ERROR");
        } else {
            commandList.set(index, "******"); // 将指定索引的命令字替换为******
            StringBuilder result = new StringBuilder();

            for (String item : commandList) {
                result.append("_").append(item); // 在命令字之前添加下划线
            }

            result.deleteCharAt(0); // 删除第一个下划线
            System.out.println(result.toString());
        }
    }
}

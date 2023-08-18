package logical;

import java.util.*;

/**
 * 题目描述
 * 有一个考古学家发现一个石碑，但是很可惜，发现时其已经断成多段，原地发现n个断口整齐的石碑碎片。
 * 为了破解石碑内容，考古学家希望有程序能帮忙计算复原后的石碑文字组合数，你能帮忙吗？
 *
 * 输入描述
 *  第一行输入n，n表示石碑碎片的个数。
 *  第二行依次输入石碑碎片上的文字内容s，共有n组。
 *
 * 输出描述
 *  输出石碑文字的组合（按照升序排列），行末无多余空格。
 * ————————————————
 * 版权声明：本文为CSDN博主「算法大师」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
 * 原文链接：https://blog.csdn.net/banxia_frontend/article/details/129699036
 *
 * 输入
 * 3
 * a b c
 *
 * 输出
 * abc
 * acb
 * bac
 * bca
 * cab
 * cba
 */
public class Archaelogist {
    // 深度优先搜索函数
    public static void dfs(String[] charArray, int depth, StringBuilder path, boolean[] used, List<String> result) {
        // 如果碎片都已经被使用过，将当前组合加入结果中
        if (depth == charArray.length) {
            result.add(path.toString());
            return;
        }
        for (int i = 0; i < charArray.length; i++) {
            // 如果碎片已经被使用过，则跳过
            if (used[i]) {
                continue;
            }
            // 如果当前碎片和前一个碎片相同，并且前一个碎片还没有被使用，则跳过
            // 因为遍历是从前往后，如果出现前一格没有被使用的情况只能说明是上一次递归退出来了，而如果上一格跟当前相同， 那么上一次递归时候已经遍历当前字符
            // 如果再次读取，会导致重复字符出现在相同深度位置，所以需要跳过。
            if (i > 0 && charArray[i].equals(charArray[i - 1]) && !used[i - 1]) {
                continue;
            }
            path.append(charArray[i]); // 将当前碎片存入 path 中
            used[i] = true; // 标记当前碎片已被使用
            dfs(charArray, depth + 1, path, used, result); // 递归搜索下一个碎片
            path.setLength(path.length() - 1); // 回溯，将当前碎片从 path 中移除
            used[i] = false; // 标记当前碎片未被使用
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // 处理输入
        int n = scanner.nextInt();
        scanner.nextLine(); // 忽略第一行剩余的换行符
        String inputLine = scanner.nextLine();
        String[] charArray = inputLine.split(" ");
        List<String> result = new ArrayList<>();

        // 先对碎片进行排序
        Arrays.sort(charArray);
        // path 中存储已经使用过的碎片
        StringBuilder path = new StringBuilder();
        // 记录每个碎片是否被使用过
        boolean[] used = new boolean[n];
        dfs(charArray, 0, path, used, result);

        // 输出所有组合
        for (String s : result) {
            System.out.println(s);
        }
    }
}

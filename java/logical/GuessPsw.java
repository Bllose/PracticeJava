package logical;

import java.util.*;

/**
 * 题目描述
 * 小杨申请了一个保密柜，但是他忘记了密码。只记得密码都是数字，而且所有数字都是不重复的。
 * 请你根据他记住的数字范围和密码的最小数字数量，帮他算下有哪些可能的组合，规则如下：
 *  1、输出的组合都是从可选的数字范围中选取的，且不能重复；
 *  2、输出的密码数字要按照从小到大的顺序排列，密码组合需要按照字母顺序，从小到大的顺序排序。
 *  3、输出的每一个组合的数字的数量要大于等于密码最小数字数量；
 *  4、如果可能的组合为空，则返回“None”
 *
 * 输入描述
 *  1、输入的第一行是可能的密码数字列表，数字间以半角逗号分隔
 *  2、输入的第二行是密码最小数字数量
 *
 * 输出描述
 *  可能的密码组合，每种组合显示成一行，每个组合内部的数字以半角逗号分隔，从小到大的顺序排列。
 *  输出的组合间需要按照字典序排序。
 * 比如：
 *  2,3,4放到2,4的前面
 * ————————————————
 * 版权声明：本文为CSDN博主「算法大师」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
 * 原文链接：https://blog.csdn.net/banxia_frontend/article/details/129894608
 *
 * 用例
 * 输入
 * 2,3,4
 * 2
 * 输出
 * 2,3
 * 2,3,4
 * 2,4
 * 3,4
 * 说明
 *  最小密码数量是两个，可能有三种组合：
 * 2,3
 * 2,4
 * 3,4
 *
 * 三个密码有一种：
 * 2,3,4
 */
public class GuessPsw {
    public static void dfs(List<String> nums, int index, int level, List<String> path, List<String> res) {
        if (path.size() >= level) { // 当路径长度达到level时，记录路径
            StringBuilder combination = new StringBuilder();
            for (int i = 0; i < path.size(); i++) {
                if (i > 0) combination.append(","); // 数字之间用逗号分隔
                combination.append(path.get(i));
            }
            res.add(combination.toString());
        }
        if (path.size() == nums.size()) return; // 路径长度达到nums长度时退出

        for (int i = index; i < nums.size(); i++) { // 枚举数字
            path.add(nums.get(i));
            dfs(nums, i + 1, level, path, res); // 递归搜索
            path.remove(path.size() - 1);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String line = sc.nextLine(); // 读入数字列表
        int level = sc.nextInt(); // 读入最小数字数量

        List<String> nums = new ArrayList<>();
        String delimiter = ",";
        StringTokenizer st = new StringTokenizer(line, delimiter);
        while (st.hasMoreTokens()) { // 分割数字列表
            nums.add(st.nextToken());
        }
        Collections.sort(nums); // 排序

        List<String> res = new ArrayList<>();
        List<String> path = new ArrayList<>();
        dfs(nums, 0, level, path, res);

        if (!res.isEmpty()) {
            for (String s : res) {
                System.out.println(s);
            }
        } else {
            System.out.println("None");
        }
    }
}

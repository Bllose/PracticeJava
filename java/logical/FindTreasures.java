package logical;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * 题目描述
 * 一贫如洗的樵夫阿里巴巴在去砍柴的路上，无意中发现了强盗集团的藏宝地，藏宝地有编号从0-N的箱子，
 * 每个箱子上面有一个数字，箱子排列成一个环，编号最大的箱子的下一个是编号为0的箱子。
 * 请输出每个箱了贴的数字之后的第一个比它大的数，如果不存在则输出-1。
 *
 * 输入描述
 * 输入一个数字字串，数字之间使用逗号分隔，例如: 1,2,3,1
 * 1 ≤ 字串中数字个数 ≤ 10000:
 * -100000 ≤ 每个数字值 ≤ 100000
 * 输出描述
 * 下一个大的数列表，以逗号分隔，例如: 2,3,6,-1,6
 * ————————————————
 * 版权声明：本文为CSDN博主「算法大师」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
 * 原文链接：https://blog.csdn.net/banxia_frontend/article/details/131097360
 */
/*
输入：
2,5,2
输出：
5,-1,5

输入：
3,4,5,6,3
输出：
4,5,6,-1.4

 */
public class FindTreasures {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 将输入的数字字符串转为整型数组
        int[] nums = Arrays.stream(in.nextLine().split(",")).mapToInt(Integer::parseInt).toArray();
        LinkedList<int[]> stack = new LinkedList<>();
        int[] res = new int[nums.length];
        // 初始化结果数组为-1
        Arrays.fill(res, -1);

        // 遍历环形数组
        for (int i = 0; i < nums.length * 2; i++) {
            int curIndex = i % nums.length;
            int cur = nums[curIndex];
            while (!stack.isEmpty() && cur > stack.getLast()[0]) {
                int[] top = stack.removeLast();
                res[top[1]] = cur;
            }
            stack.addLast(new int[]{cur, curIndex});
        }

        // 将结果数组转为字符串输出
        StringBuilder result = new StringBuilder();
        for (int v : res) {
            result.append(v).append(",");
        }
        System.out.println(result.substring(0, result.length() - 1));
    }
}

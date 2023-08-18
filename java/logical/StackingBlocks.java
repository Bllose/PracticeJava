package logical;

import java.util.*;

/**
 * 题目描述
 * 有一堆长方体积木，它们的高度和宽度都相同，但长度不一。
 *
 * 小橙想把这堆积木叠成一面墙，墙的每层可以放一个积木，也可以将两个积木拼接起来，要求每层的长度相同。
 *
 * 若必须用完这些积木，叠成的墙最多为多少层？
 *
 * 如下是叠成的一面墙的图示，积木仅按宽和高所在的面进行拼接。
 *
 *
 *
 * 输入描述
 * 输入为一行，为各个积木的长度，数字为正整数，并由空格分隔。积木的数量和长度都不超过5000。
 *
 * 输出描述
 * 输出一个数字，为墙的最大层数，如果无法按要求叠成每层长度一致的墙，则输出-1。
 * ————————————————
 * 版权声明：本文为CSDN博主「算法大师」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
 * 原文链接：https://blog.csdn.net/banxia_frontend/article/details/129393995
 */
/*
输入：
3 6 3 3 3
输出：
3
说明：
以 6 为底的墙，第一层为 6 ，第二层为 3 + 3，第三层 3 + 3。

输入：
9 9 9 5 3 2 2 2 2 2
输出：
5
说明：
5+2+2=9
3+2+2+2=9
9,9,9
共五层
 */
public class StackingBlocks {
    // 深度优先搜索
    public static boolean dfs(List<Integer> nums, int cur, int used, List<Integer> bucket, int k, int score) {
        // 所有积木都已经使用完，返回true
        if (cur < 0) {
            return true;
        }
        // 当前层还未堆满，继续往上堆
        if (used < k) {
            bucket.set(used, nums.get(cur));
            if (dfs(nums, cur - 1, used + 1, bucket, k, score)) {
                return true;
            }
            bucket.set(used, 0);
        }
        // 把当前积木加入已有的每一层中，看是否能够满足条件
        for (int i = 0; i < used; i++) {
            // 如果当前层和上一层积木长度相同，则不需要重复计算
            if (i > 0 && bucket.get(i).equals(bucket.get(i - 1))) {
                continue;
            }
            // 如果当前积木可以放入当前层，则把当前积木放入当前层
            if (bucket.get(i) + nums.get(cur) <= score) {
                bucket.set(i, bucket.get(i) + nums.get(cur));
                if (dfs(nums, cur - 1, used, bucket, k, score)) {
                    return true;
                }
                bucket.set(i, bucket.get(i) - nums.get(cur));
            }
        }
        return false;
    }

    public static void main(String[] args) {
        // 处理输入
        Scanner scanner = new Scanner(System.in);
        String inputStr = scanner.nextLine();
        List<Integer> nums = new ArrayList<>();
        for (String str : inputStr.split(" ")) {
            nums.add(Integer.parseInt(str));
        }
        // 求和并排序
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        Collections.sort(nums);

        int res = -1;
        // i表示可以堆的层数
        for (int i = 2; i <= sum / 2; i++) {
            // 如果所有数字的和除不尽层数，自然肯定不满足条件
            if (sum % i != 0) {
                continue;
            }
            // 每一层的长度为score
            int score = sum / i;
            // 如果最大的积木长度大于当前层的长度，则无法满足条件
            if (nums.get(nums.size() - 1) > score) {
                continue;
            }
            // 建立一个长度为k的桶
            List<Integer> bucket = new ArrayList<>(Collections.nCopies(i, score));
            if (dfs(nums, nums.size() - 1, 0, bucket, i, score)) {
                res = Math.max(res, i);
            }
        }
        System.out.println(res);
    }
}

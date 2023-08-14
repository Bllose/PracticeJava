package merge;

import java.util.*;

/**
 * 区间交集
 * 题目描述
 * 给定一组闭区间，其中部分区间存在交集。
 *  任意两个给定区间的交集，称为公共区间(如:[1,2],[2,3]的公共区间为[2,2]，[3,5],[3,6]的公共区间为[3,5])。
 *  公共区间之间若存在交集，则需要合并(如:[1,3],[3,5]区间存在交集[3,3]，需合并为[1,5])。
 *  按升序排列输出合并后的区间列表。
 *
 * 输入描述
 * 一组区间列表，区间数为 N: 0<=N<=1000;区间元素为 X: -10000<=X<=10000。
 *
 * 输出描述
 * 升序排列的合并区间列表
 *
 * 备注:
 *  1、区间元素均为数字，不考虑字母、符号等异常输入。
 *  2、单个区间认定为无公共区间。
 *
 * 用例1
 *  输入：
 *      4
 *      0 3
 *      1 3
 *      3 5
 *      3 6
 *  输出：
 *      1 5
 */
public class MergeArea {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int total = in.nextInt();
        int[][] holder = new int[total][2];

        for(int i = 0; i < total; i ++) {
            holder[i][0] = in.nextInt();
            holder[i][1] = in.nextInt();
        }

        List<int[]> merge = new ArrayList<>();
        for(int i = 0; i < total; i ++) {
            for(int j = i + 1; j < total; j ++) {
                int l1 = holder[i][0];
                int r1 = holder[i][1];

                int l2 = holder[j][0];
                int r2 = holder[j][1];

                int left = Math.max(l1, l2);
                int right = Math.min(r1, r2);

                if( left <= right ) {
                    merge.add(new int[]{left, right});
                }
            }
        }

        Collections.sort(merge, Comparator.comparing((int[] cur) -> -cur[0]).thenComparing((int[] cur) -> cur[1]));
        Stack<int[]> s = new Stack<>();
        for(int[] cur: merge) {
            s.add(cur);
        }

        while(!s.isEmpty()) {
            if(s.size() == 1) {
                int[] last = s.pop();
                System.out.println(last[0] + " " + last[1]);
                break;
            }
            int[] first = s.pop();
            int[] second = s.pop();

            int l1 = first[0];
            int r1 = first[1];

            int l2 = second[0];
            int r2 = second[1];

            int left = Math.max(l1, l2);
            int right = Math.min(r1, r2);

            if(left < right) {
                s.add(new int[]{left, right});
            } else {
                s.add(second);
                System.out.println(l1 + " " + r1);
            }
        }
    }
}

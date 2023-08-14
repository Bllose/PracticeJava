package search;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

/**
 * 耐心排序 + 二分查找
 * 书籍叠放
 * 题目描述：
 *  书籍的长、宽都是整数对应(l,w)。如果书A的长宽度都比B长宽大时，则允许将B排列放在A上面。
 *  现在有一组规格的书籍，书籍叠放时要求书籍不能做旋转，请计算最多能有多少个规格书籍能叠放在一起。
 * 输入描述：
 *  输入：books = [[20,16],[15,11],[10,10],[9,10]]
 *  说明：总共4本书籍，第一本长度为20宽度为16；第二本书长度为15宽度为11，依次类推，最后一本书长度为9宽度为10.
 * 输出描述：
 *  输出：3
 *  说明: 最多3个规格的书籍可以叠放到一起, 从下到上依次为: [20,16],[15,11],[10,10]
 *
 *  [[1,4],[1,2],[1,3],[1,5],[1,9]]
 *  [[1,1],[2,3],[2,2],[3,1]]
 *  [[20,16],[15,11],[10,10],[9,10],[8,8],[7,7],[6,6],[5,5],[4,4],[3,3],[2,2],[1,1]]
 *  [[20,16],[15,11],[10,10],[9,10],[8,8],[7,14],[6,6],[6,6],[6,6],[6,6],[6,6],[6,6]]
 */
public class StackBooks1 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String inputString = in.nextLine();
        inputString = inputString.substring(1, inputString.length() - 1);

        String[] inputHolder = inputString.split("(?<=]),(?=\\[)");
        int[][] books = new int[inputHolder.length][];

        for(int i = 0; i < inputHolder.length; i ++) {
            String cur = inputHolder[i];
            cur = cur.substring(1, cur.length() - 1);
            books[i] = Arrays.stream(cur.split(",")).mapToInt(Integer::parseInt).toArray();
        }

        Arrays.sort(books,
                Comparator.comparing((int[] cur) -> cur[0]).thenComparing((int[] cur) -> -cur[1]));
        int[] sequence = Arrays.stream(books).mapToInt((int[] book) -> book[1]).toArray();
        int[] lis = new int[sequence.length];
        int len = 0;
        for(int i=0; i < sequence.length; i ++) {
            int idx = Arrays.binarySearch(lis, 0, len, sequence[i]);
            if(idx < 0) {
                idx = -(idx + 1);
                if(idx == len) {
                    lis[idx] = sequence[i];
                    len ++;
                }
            }
        }

        System.out.println(len);
    }
}

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
public class StackBooks {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String input = sc.nextLine();
        String[] bookStrings = input.substring(1, input.length() - 1).split("(?<=]),(?=\\[)");
        Integer[][] books = new Integer[bookStrings.length][];
        for (int i = 0; i < bookStrings.length; i++) {
            String[] bookValues = bookStrings[i].substring(1, bookStrings[i].length() - 1).split(",");
            books[i] = new Integer[bookValues.length];
            for (int j = 0; j < bookValues.length; j++) {
                books[i][j] = Integer.parseInt(bookValues[j]);
            }
        }

        // 按照长度从小到大排序，长度相同时按照宽度从大到小排序
        Arrays.sort(books, Comparator.comparing((Integer[] arr) -> arr[0]).thenComparing((Integer[] arr) -> -arr[1]));

        // 计算最长不下降子序列
        int[] widths = Arrays.stream(books).mapToInt(book -> book[1]).toArray();
        int[] lis = new int[widths.length];
        int len = 0;
        for (int i = 0; i < widths.length; i++) {
            // 这行代码的作用是使用二分搜索算法来查找给定值在现有的 lis 数组中的插入点。在最长递增子序列（LIS）算法中，这是关键步骤之一。
            int idx = Arrays.binarySearch(lis, 0, len, widths[i]);
            if (idx < 0) {
                idx = -(idx + 1);
                lis[idx] = widths[i];
                if (idx == len) {
                    len++;
                }
            }
        }

        // 输出最长不下降子序列的长度
        System.out.println(len);
    }
}

package graph;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 给一个字符串和一个二维字符数组，如果该字符串存在于该数组中，则按字符串的字符顺序输出字符串每个字符所在单元格的位置下标字符串，
 * 如果找不到返回字符串“N”。
 *  1.需要按照字符串的字符组成顺序搜索，且搜索到的位置必须是相邻单元格，其中“相邻单元格”是指那些水平相邻或垂直相邻的单元格。
 *  2.同一个单元格内的字母不允许被重复使用。
 *  3.假定在数组中最多只存在一个可能的匹配。
 *
 * 输入描述
 *  第1行为一个数字N指示二维数组在后续输入所占的行数。
 *  第2行到第N+1行输入为一个二维大写字符数组，每行字符用半角,分割。
 *  第N+2行为待查找的字符串，由大写字符组成。
 *  二维数组的大小为N*N，0<N<=100。
 *  单词长度K，0<K<1000。
 *
 * 输出描述
 *  输出一个位置下标字符串，拼接格式为：
 *  第1个字符行下标+”,”+第1个字符列下标+”,”+第2个字符行下标+”,”+第2个字符列下标… +”,”+第N个字符行下标+”,”+第N个字符列下标。
 *
 * 用例：
 *  输入：
 *      4
 *      A,C,C,F
 *      C,D,E,D
 *      B,E,S,S
 *      F,E,C,A
 *      ACCESS
 *
 *  输出：
 *      0,0,0,1,0,2,1,2,2,2,2,3
 *
 *  说明：
 *      ACCESS分别对应二维数组的[0,0] [0,1] [0,2] [1,2] [2,2] [2,3]下标位置。
 */
public class FindWord {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int rows = in.nextInt();
        in.nextLine();

        String[][] holder = new String[rows][];
        for(int i = 0; i < rows; i ++) {
            holder[i] = Arrays.stream(in.nextLine().split(",")).toArray(String[]::new);
        }
        int[][] visited = new int[rows][holder[0].length]; // 记录当前格子是否被访问过。 0 未访问； 1已访问
        String TARGET = in.nextLine();

        processer(holder, TARGET, visited);
    }

    public static void processer(String[][] input, String target, int[][] visited) {
        String[] t = target.split("");
        String cur = t[0];
        int[] result = new int[target.length() * 2];

        for(int i = 0; i < input.length; i ++) {
            for(int j = 0; j < input[0].length; j ++) {
                if(input[i][j].equals(cur)) {
                    result[0] = i;
                    result[1] = j;
                    visited[i][j] = 1;
                    if(dfs(input, t, 1, result, visited)) {
                        System.out.println(Arrays.toString(result));
                        return;
                    }
                    visited[i][j] = 0; // 本次遍历未能匹配， 还原访问状态
                }
            }
        }
        System.out.println("N");
    }

    public static boolean dfs(String[][] input, String[] target, int cur, int[] result, int[][] visited) {
        if( cur >= target.length ) {
            return true; // 遍历完成
        }
        int index = cur * 2;
        int lastRow = result[index - 2];
        int lastCol = result[index - 1];

        if(next(lastRow + 1, lastCol, input, visited, result, cur, index, target)) {
            return true;
        }
        if(next(lastRow, lastCol + 1, input, visited, result, cur, index, target)) {
            return true;
        }
        if(next(lastRow - 1, lastCol, input, visited, result, cur, index, target)) {
            return true;
        }
        if(next(lastRow, lastCol - 1, input, visited, result, cur, index, target)) {
            return true;
        }

        return false;
    }

    private static boolean next(int row, int col, String[][] input, int[][] visited, int[] result,
                                      int cur, int index, String[] target) {
        if (row < 0 || row > input.length || col < 0 || col > input[0].length) {
            return false;
        }
        if(visited[row][col] == 0) {
            if(input[row][col].equals(target[cur])) {
                result[index] = row;
                result[index + 1] = col;
                visited[row][col] = 1;
                if(dfs(input, target, cur + 1, result, visited)) {
                    return true;
                }
                visited[row][col] = 0;
            }
        }

        return false;
    }
}

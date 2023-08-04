package rearrangement;

import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * 【数字排列】小明负责公司年会，想出一个趣味游戏：
 * 屏幕给出1～9中任意3个不重复的数字，大家以最快时间给出这几个数字可拼成的数字从小到大排列位于第N位置的数字，其中N为给出的数字中最大的(如果不到这
 * 么多个数字则给出最后一个即可)。
 * 注意：
 *  1)2可以当做5来使用，5也可以当做2来使用进行数字拼接，且屏幕不能同时给出2和5;
 *  2)6可以当做9来使用，9也可以当做6来使用进行数字拼接，且屏幕不能同时给出6和9。
 * 如给出：1,4,8,则可以拼成的数字为：
 *  1,4,8,14,18,41,48,81,84,148,184,418,481,814,841
 * 那么最第N(即8)个的数字为81。
 * 输入描述：
 *  输入为以逗号分隔的描述3个int类型整数的字符串。
 * 输出描述：
 *  输出为这几个数字可拼成的数字从小到大排列位于第N(N为输入数字中最大的数字)位置的数字，如果输入的数字不在范围内或者有重复，则输出-1。
 * 示例1:
 * 输入
 *  1,4,8
 * 输出
 *  81
 * </p>
 */
public class Star2Numeric {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String[] inputHolder = in.nextLine().split(",");
        Integer[] input = new Integer[inputHolder.length];
        for(int i = 0 ; i < inputHolder.length; i ++) {
            input[i] = Integer.valueOf(inputHolder[i]);
        }

        processer(input);
    }

    public static void processer(Integer[] input) {
        List<Integer> inputList = new ArrayList<>(Arrays.asList(input));
        int index = Collections.max(inputList); // 数据所处位置
        int originalLen = inputList.size();

        for(int i = 0; i < originalLen; i ++) {
            int cur = inputList.get(i);
            switch(cur) {
                case 2: inputList.add(5); break;
                case 5: inputList.add(2); break;
                case 6: inputList.add(9); break;
                case 9: inputList.add(6); break;
            }
        }

        List<Integer> sorted = inputList.stream().sorted().collect(Collectors.toList());
        int LEN = sorted.size();
        if(index <= LEN) {
            System.out.println(sorted.get(index - 1));
        } else {
            int i = index - LEN;
            int ten = i / (LEN - 1);
            int yushu = i % (LEN - 1);
            for(int j = 0; j < sorted.size() ; j ++) {
                if(yushu > 0) {
                    if(j == ten + 1) {
                        continue;
                    }
                    yushu --;
                } else {
                    System.out.println(sorted.get(ten) + "" + sorted.get(j - 1));
                    break;
                }
            }
        }
    }
}

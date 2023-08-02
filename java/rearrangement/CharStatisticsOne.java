package rearrangement;

import java.util.*;

/**
 * 【字符统计及重排】【重要】
 * 给出一个仅包含字母的字符串，不包含空格，统计字符串中各个字母（区分大小写）出现的次数，
 * 并按照字母出现次数从大到小的顺序输出各个字母及其出现次数。
 * 如果次数相同，按照自然顺序进行排序，且小写字母在大写字母之前。
 * <p>
 * 输入描述：
 * 输入一行，为一个仅包含字母的字符串。
 * 输出描述：
 * 按照字母出现次数从大到小的顺序输出各个字母和字母次数，用英文分号分隔，注意末尾的分号；字母和次数间用英文冒号分隔。
 * 示例1
 * 输入
 * xyxyXX
 * 输出
 * x:2;y:2;X:2;
 * 说明
 * 每个字符出现的个数都是2,故x排在y之前，而小写字母x在x之前
 */
public class CharStatisticsOne {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        char[] inputHolder = in.nextLine().toCharArray();

        processer(inputHolder);
    }

    /*
    优化算法
    1、通过entrySet直接将map转移入List中
    2、通过Collections.sort直接针对List进行自定义排序
    3、利用三元表达式优雅又出大写字母对应字符集数值
     */
    public static void processer(char[] input) {
        Map<Character, Integer> statistic = new HashMap<>();
        for (char cur : input) {
            if (statistic.containsKey(cur)) {
                statistic.put(cur, statistic.get(cur) + 1);
            } else {
                statistic.put(cur, 1);
            }
        }

        List<Map.Entry<Character, Integer>> statisticList = new ArrayList<>(statistic.entrySet());
        Collections.sort(statisticList, (map1, map2) -> {
            if (map1.getValue() != map2.getValue()) {
                return map2.getValue() - map1.getValue();
            } else {
                int num1 = Integer.valueOf(map1.getKey());
                int num2 = Integer.valueOf(map2.getKey());
                num1 = num1 < 91 ? num1 + 100 : num1;
                num2 = num2 < 91 ? num2 + 100 : num2;
                return num1 - num2;
            }
        });

        for (Map.Entry<Character, Integer> outputMap : statisticList) {
            System.out.print(outputMap.getKey() + ":" + outputMap.getValue() + ";");
        }
    }
}

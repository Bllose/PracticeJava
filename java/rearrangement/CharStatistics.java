package rearrangement;

import java.util.*;

/**
 * 【字符统计及重排】【重要】
 * 给出一个仅包含字母的字符串，不包含空格，统计字符串中各个字母（区分大小写）出现的次数，
 * 并按照字母出现次数从大到小的顺序输出各个字母及其出现次数。
 * 如果次数相同，按照自然顺序进行排序，且小写字母在大写字母之前。
 *
 * 输入描述：
 *  输入一行，为一个仅包含字母的字符串。
 * 输出描述：
 *  按照字母出现次数从大到小的顺序输出各个字母和字母次数，用英文分号分隔，注意末尾的分号；字母和次数间用英文冒号分隔。
 * 示例1
 * 输入
 *  xyxyXX
 * 输出
 *  x:2;y:2;X:2;
 * 说明
 *  每个字符出现的个数都是2,故x排在y之前，而小写字母x在x之前
 */
public class CharStatistics {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        char[] input = in.nextLine().toCharArray();
        Map<Character, Integer> recorder = new HashMap<>();

        for(char cur : input) {
            if(recorder.containsKey(cur)) {
                recorder.put(cur, recorder.get(cur) + 1);
            } else {
                recorder.put(cur, 1);
            }
        }

        Map<Integer, List<Character>> statsics = new HashMap<>();
        for(Map.Entry<Character, Integer> cur :recorder.entrySet()) {
            if(statsics.containsKey(cur.getValue())) {
                statsics.get(cur.getValue()).add(cur.getKey());
            } else {
                statsics.put(cur.getValue(), new ArrayList(){{add(cur.getKey());}});
            }
        }

        statsics = sortByKey(statsics, true);
        System.out.println("DONE");
    }

    public static <K extends Comparable<? super K>, V> Map<K, V> sortByKey(Map<K, V> map, boolean isDesc) {
        Map<K, V> result = new LinkedHashMap<>();
        if (isDesc) {
            map.entrySet().stream().sorted(Map.Entry.<K,V>comparingByKey().reversed())
                    .forEachOrdered(e -> result.put(e.getKey(), e.getValue()));
        } else {
            map.entrySet().stream().sorted(Map.Entry.comparingByKey())
                    .forEachOrdered(e -> result.put(e.getKey(), e.getValue()));
        }

        return result;
    }

    public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue( Map<K, V> map, boolean isDesc) {
        Map<K, V> result = new LinkedHashMap<>();
        if( isDesc ) {
            map.entrySet().stream().sorted(Map.Entry.<K,V>comparingByValue().reversed())
                    .forEachOrdered(e -> result.put(e.getKey(), e.getValue()));
        } else {
            map.entrySet().stream().sorted(Map.Entry.comparingByValue())
                    .forEachOrdered(e -> result.put(e.getKey(), e.getValue()));
        }
        return result;
    }
}

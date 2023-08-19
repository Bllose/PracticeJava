package rearrangement;

import java.util.*;

/**
 * 题目描述
 * 实现一个支持优先级的队列，高优先级先出队列；同优先级时先进先出。
 * 如果两个输入数据和优先级都相同，则后一个数据不入队列被丢弃。
 * 队列存储的数据内容是一个整数。
 *
 * 输入描述
 * 一组待存入队列的数据 (包含内容和优先级)
 *
 * 输出描述
 * 队列的数据内容(优先级信息输出时不再体现)
 *
 * 备注
 * 不用考虑输入数据不合法的情况，测试数据不超过100个
 * ————————————————
 * 版权声明：本文为CSDN博主「算法大师」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
 * 原文链接：https://blog.csdn.net/banxia_frontend/article/details/131609615
 */
/*
输入：
(10,1),(20,1),(30,2),(40,3)
输出：
40,30,10,20
说明：
输入样例中，向队列写入了4个数据，每个数据由数据内容和优先级组成。
输入和输出内容都不含空格。
数据40的优先级最高，所以最先输出，其次是30;
10和20优先级相同，所以按输入顺序输出。

输入：
(10,1),(10,1),(30,2),(40,3)
输出：
40,30,10
说明：
输入样例中，向队列写入了4个数据，每个数据由数据内容和优先级组成。
输入和输出内容都不含空格。
数据40的优先级最高，所以最先输出，其次是30;
两个10和10构成重复数据，被丢弃一个。


(10,1),(20,1),(30,2),(40,3)
 */
public class PriorityQuene {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();

        String[] taskStrings = input.substring(1, input.length() - 1).split("\\),\\(");
            int[][] tasks = new int[taskStrings.length][];
        for (int i = 0; i < taskStrings.length; i++) {
            String[] taskValues = taskStrings[i].split(",");
            int[] task = new int[taskValues.length];
            for (int j = 0; j < taskValues.length; j++) {
                task[j] = Integer.parseInt(taskValues[j]);
            }
            tasks[i] = task;
        }

        TreeMap<Integer, LinkedHashSet<Integer>> map = new TreeMap<>(Collections.reverseOrder());

        for (int[] task : tasks) {
            int data = task[0];
            int priority = task[1];

            if (!map.containsKey(priority)) {
                map.put(priority, new LinkedHashSet<>());
            }
            map.get(priority).add(data);
        }

        StringBuilder sb = new StringBuilder();
        for (LinkedHashSet<Integer> set : map.values()) {
            for (int data : set) {
                sb.append(data).append(",");
            }
        }
        sb.deleteCharAt(sb.length() - 1);

        System.out.println(sb.toString());
    }
}

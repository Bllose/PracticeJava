package rearrangement;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

/**
 * 题目描述
 * 某个打印机根据打印队列执行打印任务。打印任务分为九个优先级，分别用数字1-9表示，数字越大优先级越高。打印机每次从队列头部取出第一个任务A，
 * 然后检查队列余下任务中有没有比A优先级更高的任务，如果有比A优先级高的任务，则将任务A放到队列尾部，否则就执行任务A的打印。
 * 请编写一个程序，根据输入的打印队列，输出实际的打印顺序。
 * 输入描述
 * 输入一行，为每个任务的优先级，优先级之间用逗号隔开，优先级取值范围是1~9。
 * 输出描述
 * 输出一行，为每个任务的打印顺序，打印顺序从0开始，用逗号隔开
 * 用例1
 * 输入
 * 9,3,5
 * 输出
 * 0,2,1
 *
 * 用例2
 * 输入
 *  1,2,2
 * 输出
 *  2,0,1
 */
public class PrinterExample {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        String[] priorityListString = input.split(",");
        int[] priorityList = new int[priorityListString.length];
        for (int i = 0; i < priorityListString.length; i++) {
            priorityList[i] = Integer.parseInt(priorityListString[i]);
        }

        List<int[]> taskList = new ArrayList<>();
        for (int i = 0; i < priorityList.length; i++) {
            taskList.add(new int[]{i, priorityList[i]});
        }

        taskList.sort(Comparator.comparingInt(x -> -x[1]));

        int currentTask = 0;
        List<Integer> printOrder = new ArrayList<>();
        while (currentTask < taskList.size()) {
            for (int i = 0; i < taskList.size(); i++) {
                if (taskList.get(i)[0] == currentTask) {
                    printOrder.add(i);
                }
            }
            currentTask++;
        }

        String output = "";
        for (int i = 0; i < printOrder.size(); i++) {
            output += printOrder.get(i);
            if (i < printOrder.size() - 1) {
                output += ",";
            }
        }

        System.out.println(output);
    }
}




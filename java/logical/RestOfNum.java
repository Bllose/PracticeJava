package logical;

import java.util.LinkedList;
import java.util.Scanner;
import java.util.StringJoiner;

/**
 * 题目描述
 * 向一个空栈中依次存入正整数，假设入栈元素 n(1<=n<=2^31-1)按顺序依次为 nx…n4、 n3、n2、 n1,
 * 每当元素入栈时，如果 n1=n2+…+ny(y 的范围[2,x]， 1<=x<=1000)，则 n1~ny 全部元素出栈，重新入栈新元素 m(m=2*n1)。
 *
 * 如：依次向栈存入 6、 1、 2、 3, 当存入 6、 1、 2 时，栈底至栈顶依次为[6、 1、 2]；
 * 当存入 3时， 3=2+1， 3、 2、 1 全部出栈，重新入栈元素 6(6=2*3)，此时栈中有元素 6；
 *
 * 因为 6=6，所以两个 6 全部出栈，存入 12，最终栈中只剩一个元素 12。
 *
 * 输入描述
 * 使用单个空格隔开的正整数的字符串，如”5 6 7 8″， 左边的数字先入栈，输入的正整数个数为 x， 1<=x<=1000。
 *
 * 输出描述
 * 最终栈中存留的元素值，元素值使用空格隔开，如”8 7 6 5″， 栈顶数字在左边。 6 1 2 3
 * ————————————————
 * 版权声明：本文为CSDN博主「算法大师」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
 * 原文链接：https://blog.csdn.net/banxia_frontend/article/details/130096204
 */
/*
5 10 20 50 85 1
1 170
 */
public class RestOfNum {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] inputSequence = scanner.nextLine().split(" ");
        // 创建一个 LinkedList 对象用作数字栈
        LinkedList<Integer> numberStack = new LinkedList<>();

        // 遍历输入的数字序列
        for (String numberString : inputSequence) {
            // 将字符串转换为整数
            int currentNumber = Integer.parseInt(numberString);
            // 初始化部分和为当前数字
            int partialSum = currentNumber;

            // 从栈顶向栈底检查是否满足出栈条件
            for (int index = numberStack.size() - 1; index >= 0; index--) {
                // 从部分和中减去栈中的元素
                partialSum -= numberStack.get(index);

                // 如果满足出栈条件，清除子列表并更新当前数字
                if (partialSum == 0) {
                    // 清除子列表
                    numberStack.subList(index, numberStack.size()).clear();
                    // 更新当前数字
                    currentNumber *= 2;
                    // 更新部分和
                    partialSum = currentNumber;
                } else if (partialSum < 0) {
                    // 如果部分和小于0，跳出循环
                    break;
                }
            }

            // 将当前数字入栈
            numberStack.add(currentNumber);
        }

        // 输出栈中的元素，从栈顶到栈底
        // 创建一个 StringJoiner 对象，用于连接栈中的元素
        StringJoiner outputJoiner = new StringJoiner(" ");
        // 当栈不为空时，依次移除栈顶元素并添加到 StringJoiner 中
        while (!numberStack.isEmpty()) {
            outputJoiner.add(numberStack.removeLast().toString());
        }
        // 输出最终结果
        System.out.println(outputJoiner.toString());
    }
}


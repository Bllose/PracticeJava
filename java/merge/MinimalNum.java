package merge;

import java.util.Scanner;
import java.util.Stack;


/**
 * 题目描述
 * 给一个正整数NUM1，计算出新正整数NUM2，NUM2为NUM1中移除N位数字后的结果，需要使得NUM2的值最小。
 *
 * 输入描述
 * 1.输入的第一行为一个字符串，字符串由0-9字符组成，记录正整数NUM1，NUM1长度小于32。
 * 2.输入的第二行为需要移除的数字的个数，小于NUM1长度。
 *
 * 输出描述
 * 输出一个数字字符串，记录最小值NUM2。
 *
 * 用例：
 * 输入：
 *  2615371 4
 * 输出：
 *  131
 */
public class MinimalNum {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String nums = scanner.nextLine();
        int n = scanner.nextInt();
        String result = removeDigits(nums, n);
        System.out.println(result);
    }

    public static String removeDigits(String nums, int n) {
        StringBuilder ans = new StringBuilder();
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < nums.length(); i++) {
            char item = nums.charAt(i);
            while (n > 0 && !stack.isEmpty() && stack.peek() > item) {
                stack.pop();
                n--;
            }
            stack.push(item);
        }

        while (!stack.isEmpty()) {
            ans.insert(0, stack.pop());
        }

        return ans.toString().replaceFirst("^0+(?!$)", "");
    }
}



package logical;

import java.util.Scanner;
import java.util.Stack;

public class lisp {

    static Stack<Integer> numStack = new Stack<>();   // 数字栈
    static Stack<String> operaStack = new Stack<>();  // 操作符栈

    // 计算表达式(param1 op param2)的值
    public static void calc(int param1, int param2) {
        String op = operaStack.pop();   // 取出操作符
        if (op.equals("add")) {    // 如果是加法
            numStack.push(param1 + param2); // 将计算结果压入数字栈
        } else if (op.equals("sub")) {  // 如果是减法
            numStack.push(param1 - param2);
        } else if (op.equals("mul")) {  // 如果是乘法
            numStack.push(param1 * param2);
        } else {    // 如果是除法
            if (param2 == 0) {  // 如果除数为0
                System.out.println("error");
                System.exit(0);
            } else {
                int res = param1 / param2;  // 计算商
                if (param1 % param2 != 0) { // 如果有余数
                    if (res < 0) {  // 如果商为负数
                        res -= 1;   // 向下取整
                    } else {
                        res += 1;   // 向上取整
                    }
                }
                numStack.push(res); // 将计算结果压入数字栈
            }
        }
    }


    public static void main(String[] args) {
        // 处理输入
        Scanner sc = new Scanner(System.in);
        String exp = sc.nextLine(); // 读入表达式

        int mark = 0;   // 标记数字串的起始位置
        int param1 = 0;    // 参数1
        int param2 = 0;    // 参数2

        for (int i=0; i<exp.length(); i++) {
            String ch = exp.charAt(i) + ""; // 取出当前字符
            if (ch.equals("(")) {   // 如果是左括号
                operaStack.push(exp.substring(i + 1, i + 4));    // 取出操作符并压入操作符栈
                i += 4; // 跳过操作符
                mark = i + 1;   // 标记数字串的起始位置
            } else if (ch.equals(")")) {   // 如果是右括号
                if (mark < i) { // 如果有数字串
                    numStack.push(Integer.parseInt(exp.substring(mark, i))); // 将数字串转为整数并压入数字栈
                    i += 1; // 跳过右括号
                    mark = i + 1;   // 标记数字串的起始位置
                }
                param2 = numStack.pop();    // 取出数字栈顶元素作为参数2
                param1 = numStack.pop();    // 取出数字栈顶元素作为参数1
                calc(param1, param2);   // 计算表达式的值并将结果压入数字栈
            } else {
                if (ch.equals(" ")) {   // 如果是空格
                    if (mark < i) { // 如果有数字串
                        numStack.push(Integer.parseInt(exp.substring(mark, i))); // 将数字串转为整数并压入数字栈
                        mark = i + 1;   // 标记数字串的起始位置
                    }
                }
            }
        }

        while (operaStack.size()!= 0) {    // 如果操作符栈非空
            param2 = numStack.pop();    // 取出数字栈顶元素作为参数2
            param1 = numStack.pop();    // 取出数字栈顶元素作为参数1
            calc(param1, param2);   // 计算表达式的值并将结果压入数字栈
        }

        int ans =numStack.get(0);   // 取出数字栈顶元素作为表达式的值
        System.out.println(ans);
    }
}


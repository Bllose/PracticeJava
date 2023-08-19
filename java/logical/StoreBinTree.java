package logical;

import java.util.LinkedList;
import java.util.Scanner;
import java.util.StringJoiner;
import java.util.Arrays;

/**
 * 题目描述
 * 二叉树也可以用数组来存储，给定一个数组，树的根节点的值存储在下标1，对于存储在下标N的节点，它的左子节点和右子节点分别存储在下标2*N和2*N+1，
 * 并且我们用值-1代表一个节点为空。
 *  给定一个数组存储的二叉树，试求从根节点到最小的叶子节点的路径，路径由节点的值组成。
 *
 * 输入描述
 * 输入一行为数组的内容，数组的每个元素都是正整数，元素间用空格分隔。
 *  注意第一个元素即为根节点的值，即数组的第N个元素对应下标N，下标0在树的表示中没有使用，所以我们省略了。
 *  输入的树最多为7层。
 *
 * 输出描述
 * 输出从根节点到最小叶子节点的路径上，各个节点的值，由空格分隔，用例保证最小叶子节点只有一个。
 * ————————————————
 * 版权声明：本文为CSDN博主「算法大师」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
 * 原文链接：https://blog.csdn.net/banxia_frontend/article/details/130937852
 */
public class StoreBinTree {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // 读入数组
        int[] arr = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int n = arr.length - 1; // 数组长度
        int min = Integer.MAX_VALUE; // 记录最小值
        int minIdx = -1; // 记录最小值的下标
        // 从后往前遍历数组
        for (int i = n; i >= 1; i--) {
            if (arr[i] != -1) { // 当前节点不为空
                // 如果有左子节点或右子节点，说明不是叶子节点，跳过
                if (i * 2 + 1 <= n && arr[i * 2 + 1] != -1) continue;
                if (i * 2 + 2 <= n && arr[i * 2 + 2] != -1) continue;
                // 更新最小值和最小值下标
                if (min > arr[i]) {
                    min = arr[i];
                    minIdx = i;
                }
            }
        }
        // 构造路径
        LinkedList<Integer> path = new LinkedList<>();
        path.addFirst(min);
        while (minIdx != 0) { // 从叶子节点往根节点遍历
            int f = (minIdx - 1) / 2; // 父节点下标
            path.addFirst(arr[f]); // 将父节点加入路径
            minIdx = f; // 更新最小值下标
        }
        // 输出结果
        StringJoiner sj = new StringJoiner(" ");
        for (Integer val : path) sj.add(val + "");
        System.out.println(sj.toString());
    }
}


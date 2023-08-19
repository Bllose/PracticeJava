package logical;

import java.util.Arrays;
import java.util.Scanner;

public class SplitArray {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 输入数组长度
        int length = Integer.parseInt(scanner.nextLine());

        // 输入数字序列
        long[] numbers = Arrays.stream(scanner.nextLine().split(" ")).mapToLong(Long::parseLong).toArray();

        // 计算前缀和
        long[] prefixSum = new long[length];
        prefixSum[0] = numbers[0];
        for (int i = 1; i < length; i++) {
            prefixSum[i] = prefixSum[i-1] + numbers[i];
        }

        // 差值的最大取值
        long maxDifference = 0;

        // 计算差值的最大取值
        for (int i = 0; i < length - 1; i++) {
            long leftSum = prefixSum[i];
            long rightSum = prefixSum[length-1] - prefixSum[i];
            long difference = Math.abs(leftSum - rightSum);
            maxDifference = Math.max(maxDifference, difference);
        }

//        Arrays.stream(numbers).sum();
//        Arrays.stream(Arrays.copyOf(numbers, 1));
        // 输出差值的最大取值
        System.out.println(maxDifference);
    }
}

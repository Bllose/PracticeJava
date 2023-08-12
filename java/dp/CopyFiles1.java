package dp;

import java.util.Scanner;

public class CopyFiles1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();

        int[] fileSizeArray = new int[n];
        for (int i = 0; i < n; i++) {
            fileSizeArray[i] = scanner.nextInt();
        }

        // 计算软盘可以存放的块数
        int blockCount = 1474560 / 512;

        // 动态规划数组，dp[i] 表示容量为 i 的背包可以存储的最大文件大小
        int[] dp = new int[blockCount + 1];

        for (int fileSize : fileSizeArray) {
            // 把文件大小转换成块数
            int weight = (int) Math.ceil(fileSize / 512.0);
            int worth = fileSize;
            for (int j = blockCount; j >= weight; j--) {
                dp[j] = Math.max(dp[j], dp[j - weight] + worth);
            }
        }

        System.out.println(dp[blockCount]);
    }
}


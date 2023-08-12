package dp;

import java.util.Scanner;

/**
 * 题目描述：
 *  但此电脑除了有一个3.5寸软盘驱动器以外，没有任何手段可以将文件持贝出来，而且只有一张软盘可以使用。
 *  因此这一张软盘是唯一可以用来拷贝文件的载体。
 *  科学家想要尽可能多地将计算机中的信息拷贝到软盘中，做到软盘中文件内容总大小最大。
 *  已知该软盘容量为1474560字节。文件占用的软盘空间都是按块分配的，每个块大小为512个字节。一个块只能被一个文件使用。拷贝到软盘中的文件必须是完整的，且不能采取任何压缩技术。
 *
 * 输入描述：
 *  第1行为一个整数N，表示计算机中的文件数量。1 ≤ N ≤ 1000.接下来的第2行到第N+1行(共N行)，每行为一个整数，表示每个文件的大小Si，单位为字节。
 *  0 ≤ i < N,0 ≤ Si
 *
 * 输出描述：
 *  科学家最多能拷贝的文件总大小
 * 备注：
 *  为了充分利用软盘空间，将每个文件在软盘上占用的块记录到本子上。即真正占用软盘空间的只有文件内容本身。
 */
public class CopyFiles {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int quantity = in.nextInt();
        int[] files = new int[quantity];
        for(int i = 0; i < quantity; i ++) {
            files[i] = in.nextInt();
        }

        processer(files);
    }

    /*
    代码思路
    该题可以采用背包问题的思想进行求解。
    首先，根据输入的文件数量和每个文件的大小，将文件大小存储在一个数组中。
    然后，计算软盘可以存放的块数，即将软盘总容量除以每个块的大小。
    接下来，创建一个动态规划数组dp，其中dp[i]表示容量为i的背包可以存储的最大文件大小。
    对于每个文件，将文件大小转换成块数，并将文件大小作为价值存储在worth中。
    然后，从背包容量为blockCount开始向前遍历，对于每个背包容量，计算选择当前文件和不选择当前文件两种情况下的最大值。
    最后，输出dp[blockCount]，即可得到科学家最多能拷贝的文件总大小。
     */
    public static void processer(int[] files) {
        int MAX = 1474560/512;

        int[] blocks = new int[MAX];

        for(int file: files) {
            int size = (int) Math.ceil((double) file / 512);
            int diff = blocks.length - size;
            for(int i = diff - 1; i > -1; i --) {

            }
        }
    }
}

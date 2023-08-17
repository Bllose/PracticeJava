package logical;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

/**
 * 题目描述
 * 给定一个二维数组M行N列，二维数组里的数字代表图片的像素，为了简化问题，仅包含像素1和5两种像素，
 * 每种像素代表一个物体，2个物体相邻的格子为边界，求像素1代表的物体的边界个数。
 * 像素1代表的物体的边界指与像素5相邻的像素1的格子，边界相邻的属于同一个边界，相邻需要考虑8个方向（上，下，左，右，左上，左下，右上，右下）。
 * 其他约束
 * 地图规格约束为：
 * 0<M<100
 * 0<N<100
 *
 * 1）如下图，与像素5的格子相邻的像素1的格子
 * （0,0）、（0,1）、（0,2）、
 * （1,0）、（1,2）、
 * （2,0）、（2,1）、（2,2）、
 * （4,4）、（4,5）、
 * （5,4）为边界，
 *
 * 另（0,0）、（0,1）、（0,2）、
 * （1,0）、（1,2）、
 * （2,0）、（2,1）、（2,2）相邻，为1个边界，
 * （4,4）、（4,5）、（5,4）相邻，为1个边界，所以下图边界个数为2。
 *
 * 2）如下图，与像素5的格子相邻的像素1的格子
 * （0,0）、（0,1）、（0,2）、
 * （1,0）、（1,2）、
 * （2,0）、（2,1）、（2,2）、
 * （3,3）、（3,4）、（3,5）、
 * （4,3）、（4,5）、
 * （5,3）、（5,4）、（5,5）为边界，另这些边界相邻，所以下图边界个数为1。
 *
 * 注：（2,2）、（3,3）相邻。
 *
 * 输入描述
 * 第一行，行数M，列数N 第二行开始，是M行N列的像素的二维数组，仅包含像素1和5
 *
 * 输出描述
 * 像素1代表的物体的边界个数。 如果没有边界输出0（比如只存在像素1，或者只存在像素5）。
 *
 * 用例
 * 输入
 * 6 6
 * 1 1 1 1 1 1
 * 1 5 1 1 1 1
 * 1 1 1 1 1 1
 * 1 1 1 1 1 1
 * 1 1 1 1 1 1
 * 1 1 1 1 1 5
 * 输出
 * 2
 *
 * 输出
 * 6 6
 * 1 1 1 1 1 1
 * 1 5 1 1 1 1
 * 1 1 1 1 1 1
 * 1 1 1 1 1 1
 * 1 1 1 1 5 1
 * 1 1 1 1 1 1
 * 输出
 * 1
 */
public class FindEdge {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 获取输入的行数和列数
        int m = Integer.parseInt(scanner.next());
        int n = Integer.parseInt(scanner.next());

        // 获取像素的二维数组
        String[][] pixels = new String[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                pixels[i][j] = scanner.next();
            }
        }

        // 初始化边界列表
        List<Set<Integer>> boundaries = new ArrayList<>();

        // 遍历二维数组
        for (int x = 0; x < m; x++) {
            for (int y = 0; y < n; y++) {
                // 如果当前像素为5
                if (pixels[x][y].equals("5")) {
                    // 如果边界列表为空，将当前像素添加到新的边界中
                    if (boundaries.isEmpty()) {
                        Set<Integer> boundary = new HashSet<>();
                        boundary.add(x * n + y);
                        boundaries.add(boundary);
                        continue;
                    }

                    // 初始化临时列表
                    List<Set<Integer>> tmp = new ArrayList<>();

                    // 遍历边界列表
                    for (Set<Integer> boundary : boundaries) {
                        // 遍历边界中的点
                        for (int point : boundary) {
                            int pointX = point / n;
                            int pointY = point % n;

                            // 如果当前点与像素5的点在3x3的范围内，将边界添加到临时列表中
                            if (Math.abs(pointX - x) <= 3 && Math.abs(pointY - y) <= 3) {
                                tmp.add(boundary);
                                break;
                            }
                        }
                    }

                    // 如果临时列表只有一个边界，将当前像素添加到该边界中
                    if (tmp.size() == 1) {
                        tmp.get(0).add(x * n + y);
                    }
                    // 如果临时列表有多个边界，将它们合并为一个新的边界，并从边界列表中删除旧边界，将新边界添加到边界列表中
                    else if (tmp.size() > 1) {
                        Set<Integer> newBoundary = new HashSet<>();
                        newBoundary.add(x * n + y);
                        for (Set<Integer> boundary : tmp) {
                            newBoundary.addAll(boundary);
                            boundaries.remove(boundary);
                        }
                        boundaries.add(newBoundary);
                    }
                    // 如果临时列表为空，将当前像素添加到新的边界中
                    else {
                        Set<Integer> boundary = new HashSet<>();
                        boundary.add(x * n + y);
                        boundaries.add(boundary);
                    }
                }
            }
        }

        // 输出边界个数
        System.out.println(boundaries.size());

        scanner.close();
    }
}


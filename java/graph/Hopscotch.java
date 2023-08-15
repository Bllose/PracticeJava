package graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * 题目描述
 * 地上共有N个格子，你需要跳完地上所有的格子，但是格子间是有强依赖关系的，跳完前一个格子后，后续的格子才会被开启，
 * 格子间的依赖关系由多组steps数组给出，steps[0]表示前一个格子，steps[1]表示steps[0]可以开启的格子:
 *      比如[0,1]表示从跳完第0个格子以后第1个格子就开启了，比如[2,1]，[2,3]表示跳完第2个格子后第1个格子和第3个格子就被开启了。
 *  请你计算是否能由给出的steps数组跳完所有的格子，如果可以输出yes，否则输出no。
 *  说明：
 *      1.你可以从一个格子跳到任意一个开启的格子
 *      2.没有前置依赖条件的格子默认就是开启的
 *      3.如果总数是N，则所有的格子编号为[0,1,2,3…N-1]连续的数组
 *
 * 输入描述
 *  输入一个整数N表示总共有多少个格子，接着输入多组二维数组steps表示所有格子之间的依赖关系。
 * 输出描述
 *  如果能按照steps给定的依赖顺序跳完所有的格子输出yes，
 *  否则输出no。
 *
 * 用例：
 *  输入：
 *      3
 *      0 1
 *      0 2
 *  输出：
 *      yes
 *
 *  输入：
 *      2
 *      1 0
 *      0 1
 *  输出：
 *      no
 */
public class Hopscotch {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int total = in.nextInt();
        in.nextLine();

        List<List<Integer>> edges = new ArrayList<>();
        for(int i = 0; i < total; i ++) {
            edges.add(new ArrayList<>());
        }

        int[] nodes = new int[total]; // 0 表示没有先决条件，并且还未被访问; 1表示正在访问; 2表示已经访问过
        while(in.hasNextLine()) {
            String nextLine = in.nextLine();
            if(nextLine.isEmpty()) {
                break;
            }
            int[] curEdge = Arrays.stream(nextLine.split(" ")).mapToInt(Integer::parseInt).toArray();
            edges.get(curEdge[1]).add(curEdge[0]);
            nodes[curEdge[1]] = -1; // -1 表示还未被访问；同时意味着有先决条件
        }

        boolean[] done = {true};
        for(int i = 0; i < total; i ++) {
            if(nodes[i] != 1) {
                dfs(edges, nodes, done, i);
            }
        }

        System.out.println(done[0]?"yes":"no");
    }

    public static void dfs(List<List<Integer>> edges, int[] nodes, boolean[] done, int level) {
        nodes[level] = 1; // 节点访问中
        for(int next: edges.get(level)) {
            if(nodes[next] == 0) {
                dfs(edges, nodes, done, next);
                if(!done[0]) {
                    return;
                }
            } else if(nodes[next] == 1) {
                done[0] = false;
                return;
            }
        }
        nodes[level] = 2; // 节点已访问
    }
}

package logical;

import java.util.Scanner;

/**
 * 题目描述
 * IGMP 协议中响应报文和查询报文，是维系组播通路的两个重要报文，在一条已经建立的组播通路中两个相邻的 HOST 和 ROUTER，
 * ROUTER 会给 HOST 发送查询报文，HOST 收到查询报文后给 ROUTER 回复一个响应报文，以维持相之间的关系，
 * 一旦这关系断裂，那么这条组播通路就异常”了。
 * 现通过某种手段，抓取到了 HOST 和 ROUTER 两者通讯的所有响应报文和查询报文，请分析该组播通路是否“正常”
 * 输入描述
 * 第一行抓到的报文数量C (C≤100) ，后续C行依次输入设备节点D1和D2，表示从D1到D2发送了单向的报文，D1和D2用空格隔开。
 *
 * 输出描述
 * 组播通路是否“正常”，正常输出True， 异常输出False。
 *
 * 用例1
 * 输入
 * 5
 * 1 2
 * 2 3
 * 3 2
 * 1 2
 * 2 1
 * 输出
 * True
 *
 * 用例2
 * 输入
 * 3
 * 1 3
 * 3 2
 * 2 3
 * 输出
 * False
 *
 * 代码思路
 * 该题目要求判断给定的组播通路是否正常。根据题目描述，可以得到以下信息：
 *      1.组播通路是由一条已经建立的通路中的相邻的HOST和ROUTER组成的。
 *      2.ROUTER会向HOST发送查询报文，HOST收到查询报文后会回复一个响应报文。
 *      3.组播通路正常的条件是，对于通路中的每对相邻的HOST和ROUTER，HOST回复的响应报文中包含ROUTER发送的查询报文。
 * 根据以上信息，可以得到以下解题思路：
 *      1.首先，根据输入的报文数量和报文内容，构建一个 Map来表示通路中的节点和它们的相邻节点的关系。
 *      2.对于每对相邻节点，判断对应的 Map中是否包含它们的关系，即判断对于节点A和节点B，节点A的相邻节点中是否包含节点B，
 *      以及节点B的相邻节点中是否包含节点A。
 *      3.如果存在任意一对相邻节点，其中一个节点的相邻节点中不包含另一个节点，则说明组播通路异常，返回False；否则，返回True。
 * ————————————————
 * 版权声明：本文为CSDN博主「算法大师」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
 * 原文链接：https://blog.csdn.net/banxia_frontend/article/details/131387764
 */
public class HostRouter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int num = scanner.nextInt(); // 输入报文数量

        boolean[][] routes = new boolean[101][101]; // 邻接矩阵，初始值为false

        // 输入每一条报文的源节点和目标节点，并将其添加到邻接矩阵中
        for (int i = 0; i < num; i++) {
            int source = scanner.nextInt();
            int destination = scanner.nextInt();
            routes[source][destination] = true;
        }

        // 遍历邻接矩阵，检查每一个节点的邻接节点是否存在对应的反向连接
        for (int i = 1; i <= 100; i++) {
            for (int j = 1; j <= 100; j++) {
                // 如果邻接节点的反向连接不存在，则说明组播通路异常，输出False并结束程序
                if (routes[i][j] && !routes[j][i]) {
                    System.out.println("False");
                    return;
                }
            }
        }

        // 如果所有节点的邻接节点的反向连接都存在，则说明组播通路正常，输出True
        System.out.println("True");
    }
}


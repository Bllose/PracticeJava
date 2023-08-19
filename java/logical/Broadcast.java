package logical;

import java.util.*;

/**
 * 题目: 需要广播的服务器数量
 * 服务器连接方式包括直接相连，间接连接。
 * <p>
 * A和B直接连接，B和C直接连接，则A和C间接连接。
 * <p>
 * 直接连接和间接连接都可以发送广播。
 * <p>
 * 给出一个N*N数组，代表N个服务器，
 * <p>
 * matrix[i][j] == 1，
 * 则代表i和j直接连接；不等于 1 时，代表i和j不直接连接。
 * <p>
 * matrix[i][i] == 1，
 * <p>
 * 即自己和自己直接连接。matrix[i][j] == matrix[j][i]。
 * <p>
 * 计算初始需要给几台服务器广播， 才可以使每个服务器都收到广播。
 * <p>
 * 输入
 * 输入为N行，每行有N个数字，为0或1，由空格分隔，
 * <p>
 * 构成N*N的数组，N的范围为 1 <= N <= 40
 * <p>
 * 输出
 * 输出一个数字，为需要广播的服务器的数量
 * ————————————————
 * 版权声明：本文为CSDN博主「算法大师」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
 * 原文链接：https://blog.csdn.net/banxia_frontend/article/details/130937106/
 */
/*
输入：
1 0 0
0 1 0
0 0 1
输出：
3

输入：
1 1
1 1
输出
1

输入:
1 0 1
0 1 1
1 1 1
输出：
1
 */
public class Broadcast {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            String[] str = in.nextLine().split(" ");
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < str.length; i++) {  // 把第一行加入list
                list.add(Integer.parseInt(str[i]));
            }
            for (int i = 0; i < str.length - 1; i++) {  // 把剩下的行都加入list
                String[] s = in.nextLine().split(" ");
                for (int j = 0; j < s.length; j++) {
                    list.add(Integer.parseInt(s[j]));
                }
            }
            int N = str.length;
            List<List<Integer>> res = new ArrayList<>(); // 存储需要广播的服务器
            for (int i = 0; i < N; i++) {  // 每一行
                if (isContainer(res, i)) {  // 判断某个服务器是否已经存在其连通的服务器集合中
                    continue;
                }
                List<Integer> newList = new ArrayList<>();
                newList.add(i);
                int lastLength = 0;
                while (lastLength != newList.size()) { // 判断当前集合可以联通的服务器
                    for (int k = 0; k < newList.size(); k++) {
                        int x = newList.get(k);
                        for (int j = 0; j < N; j++) {
                            int index = x * (N) + j;  // 找到在对应list的索引
                            if (list.get(index).equals(1)) {
                                if (!newList.contains(j)) {
                                    newList.add(j);
                                }
                            }
                        }
                    }
                    lastLength = newList.size();
                }
                res.add(newList);
            }
            System.out.println(res.size());
        }
    }

    public static Boolean isContainer(List<List<Integer>> res, int x) {
        for (List<Integer> integers : res) {
            if (integers.contains(x)) {
                return true;
            }
        }
        return false;
    }
}


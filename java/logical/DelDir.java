package logical;

import java.util.*;

/**
 * 题目描述
 * 某文件系统中有 N 个目录，每个目录都有一个独一无二的 ID。
 * 每个目录只有一个父目录，但每个父目录下可以有零个或者多个子目录，目录结构呈树状结构。
 * 假设，根目录的 ID 为 0，且根目录没有父目录，其他所有目录的 ID 用唯一的正整数表示，并统一编号。
 * 现给定目录 ID 和其父目录 ID 的对应父子关系表[子目录 ID，父目录 ID]，以及一个待删除的目录 ID，请计算并返回一个 ID 序列，表示因为删除指定目录后剩下的所有目录，返回的ID序列以递增序输出。
 *
 * 注意
 * 1、被删除的目录或文件编号一定在输入的 ID 序列中；
 * 2、当一个目录删除时，它所有的子目录都会被删除。
 *
 * 输入描述
 * 输入的第一行为父子关系表的长度 m；
 * 接下来的 m 行为 m 个父子关系对；
 * 最后一行为待删除的 ID。
 * 序列中的元素以空格分割，参见样例。
 *
 * 输出描述
 * 输出一个序列，表示因为删除指定目录后，剩余的目录 ID。
 * ————————————————
 * 版权声明：本文为CSDN博主「算法大师」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
 * 原文链接：https://blog.csdn.net/banxia_frontend/article/details/129855812
 */
/*
输入：
5
8 6
10 8
6 0
20 8
2 6
8
输出：
2 6
 */
public class DelDir {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<String> lines = new ArrayList<>(); // 用于存储输入的每一行
        int m = 0; // 父子关系表的长度
        while (scanner.hasNextLine()) { // 循环读取输入
            String line = scanner.nextLine(); // 读取一行输入
            lines.add(line); // 将读取的输入加入到列表中
            if (lines.size() == 1) { // 如果列表中只有一行，说明这是父子关系表的长度，将其转为整数类型并赋值给 m
                m = Integer.parseInt(line);
            }
            if (m > 0 && lines.size() == m + 2) { // 如果父子关系表的长度已经读取完，且输入的行数等于 m+2，说明输入结束，开始处理输入
                lines.remove(0); // 删除列表中的第一行，即父子关系表的长度
                int delId = Integer.parseInt(lines.remove(lines.size() - 1)); // 获取待删除的目录 ID
                int[][] parentChildPairs = new int[m][2]; // 创建一个二维数组用于存储父子关系
                for (int i = 0; i < m; i++) { // 遍历列表中的每一行
                    String[] nums = lines.get(i).split(" "); // 将每一行按空格分割成两个数字
                    parentChildPairs[i][0] = Integer.parseInt(nums[0]); // 将第一个数字作为子节点，存储到二维数组中
                    parentChildPairs[i][1] = Integer.parseInt(nums[1]); // 将第二个数字作为父节点，存储到二维数组中
                }
                System.out.println(getRemainTreeEle(parentChildPairs, delId)); // 调用 getRemainTreeEle 方法，输出结果
                lines.clear(); // 处理完一组输入后，清空列表
            }
        }
        scanner.close(); // 关闭 Scanner
    }

    public static String getRemainTreeEle(int[][] parentChildPairs, int delId) {
        Map<Integer, List<Integer>> tree = new HashMap<>(); // 创建一个 Map 用于存储目录树
        for (int i = 0; i < parentChildPairs.length; i++) { // 遍历二维数组
            int child = parentChildPairs[i][0]; // 获取子节点
            int father = parentChildPairs[i][1]; // 获取父节点
            if (tree.containsKey(father)) { // 如果父节点已经在 Map 中存在，将子节点加入到父节点对应的列表中
                tree.get(father).add(child);
            } else { // 如果父节点在 Map 中不存在，创建一个新的列表，将子节点加入到列表中，并将父节点和列表存储到 Map 中
                List<Integer> children = new ArrayList<>();
                children.add(child);
                tree.put(father, children);
            }
        }
        if (delId == 0) { // 如果待删除的目录 ID 为 0，直接返回空字符串
            return "";
        }
        List<Integer> res = new ArrayList<>(); // 创建一个列表用于存储结果
        dfs(tree, 0, delId, res); // 调用 dfs 方法，获取剩余的目录 ID
        Collections.sort(res); // 对结果进行排序
        StringBuilder sb = new StringBuilder(); // 创建一个 StringBuilder 用于拼接字符串
        for (int i = 0; i < res.size(); i++) { // 遍历结果列表
            sb.append(res.get(i)); // 将每个目录 ID 加入到 StringBuilder 中
            if (i != res.size() - 1) { // 如果不是最后一个目录 ID，加入一个空格
                sb.append(" ");
            }
        }
        return sb.toString(); // 返回拼接好的字符串
    }

    public static void dfs(Map<Integer, List<Integer>> tree, int node, int delId, List<Integer> res) {
        List<Integer> children = tree.get(node); // 获取当前节点的子节点列表
        if (children != null) { // 如果子节点列表不为空
            for (int i = 0; i < children.size(); i++) { // 遍历子节点列表
                int child = children.get(i); // 获取子节点
                if (child != delId) { // 如果子节点不是待删除的节点，将其加入到结果列表中，并继续向下遍历
                    res.add(child);
                    dfs(tree, child, delId, res);
                }
            }
        }
    }
}

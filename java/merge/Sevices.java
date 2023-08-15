package merge;

import java.util.*;
import java.util.stream.Collectors;

/**
 题目描述
    某系统中有众多服务，每个服务用字符串（只包含字母和数字，长度<=10）唯一标识，服务间可能有依赖关系，如A依赖B，则当B故障时导致A也故障。
    依赖具有传递性，如A依赖B，B依赖C，当C故障时导致B故障，也导致A故障。
    给出所有依赖关系，以及当前已知故障服务，要求输出所有正常服务。
    依赖关系：服务1-服务2 表示“服务1”依赖“服务2”
    不必考虑输入异常，用例保证：依赖关系列表、故障列表非空，且依赖关系数，故障服务数都不会超过3000，服务标识格式正常。

 输入描述
    半角逗号分隔的依赖关系列表**（换行）**
    半角逗号分隔的故障服务列表

 输出描述
    依赖关系列表中提及的所有服务中可以正常工作的服务列表，用半角逗号分隔，按依赖关系列表中出现的次序排序。

 特别的，没有正常节点输出单独一个半角逗号。

 案例：
    输入：
        a1-a2,a5-a6,a2-a3
        a5,a2
    输出：
        a6,a3
    说明：
        a1依赖a2，a2依赖a3，所以a2故障，导致a1不可用，但不影响a3；a5故障不影响a6。
        所以可用的是a3、a6，在依赖关系列表中a6先出现，所以输出:a6,a3。

    输入：
        a1-a2
        a2
    输出：
        ,
    说明：
        a1依赖a2，a2故障导致a1也故障，没有正常节点，输出一个逗号。
 */
/*
a1-a2,a5-a6,a2-a3
a5,a2

a1-a2
a2

a1-a2,a2-a3,a3-a4,a4-a5
a4
 */
public class Sevices {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String[] holder = in.nextLine().split(",");
        Map<String, List<String>> relation = new HashMap<>();
        Set<String> machines = new HashSet<>();

        for(String rela: holder) {
            String[] cur = rela.split("-");
            if(relation.containsKey(cur[1])) {
                relation.get(cur[1]).add(cur[0]);
            } else {
                relation.put(cur[1], Arrays.asList(cur[0]));
            }
            machines.add(cur[1]);
            machines.add(cur[0]);
        }

        Set<String> malfunctionList = Arrays.stream(in.nextLine().split(",")).collect(Collectors.toSet());
        // todo - 原题想考察拓扑排序， 但是我通过递归解决了，回头一空再琢磨
        System.out.println(processer(malfunctionList, machines, relation));
    }

    public static String processer(Set<String> malfunctionList, Set<String> machines,
                                   Map<String, List<String>> relation) {
        int originSize = machines.size();
        List<String> holder = new ArrayList<>();
        holder.addAll(malfunctionList);

        for(String cur: holder) {
            if(relation.containsKey(cur)) {
                List<String> subServices = relation.get(cur);
                malfunctionList.addAll(subServices);
            }
        }

        for(String mal: malfunctionList) {
            if(machines.contains(mal)) {
                machines.remove(mal);
            }
        }

        if(machines.size() != originSize) {
            return processer(malfunctionList, machines, relation);
        } else {
            if(machines.size() == 0) {
                return ",";
            }
            return String.join(",", machines);
        }
    }
}

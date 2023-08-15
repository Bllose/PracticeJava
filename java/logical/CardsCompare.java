package logical;

import java.util.*;

/**
 * 题目描述：
 *  五张牌，每张牌由牌大小和花色组成，牌大小2~10、J、Q、K、A，牌花色为红桃、黑桃、梅花、方块四种花色之一。
 *  判断牌型:
 *      牌型1，同花顺：同一花色的顺子，如红桃2红桃3红桃4红桃5红桃6。
 *      牌型2，四条：四张相同数字 + 单张，如红桃A黑桃A梅花A方块A + 黑桃K。
 *      牌型3，葫芦：三张相同数字 + 一对，如红桃5黑桃5梅花5 + 方块9梅花9。
 *      牌型4，同花：同一花色，如方块3方块7方块10方块J方块Q。
 *      牌型5，顺子：花色不一样的顺子，如红桃2黑桃3红桃4红桃5方块6。
 *      牌型6，三条：三张相同+两张单。
 *
 * 说明：
 *  （1）五张牌里不会出现牌大小和花色完全相同的牌。
 *  （2）编号小的牌型较大，如同花顺比四条大，依次类推。
 *  （3）包含A的合法的顺子只有10 J Q K A和A 2 3 4 5;类似K A 2 3 4的序列不认为是顺子。
 *
 * 输入描述
 *  输入由5行组成，每行为一张牌大小和花色，牌大小为2~10、J、Q、K、A，花色分别用字符H、S、C、D表示红桃、黑桃、梅花、方块。
 *
 * 输出描述
 *  输出牌型序号，5张牌符合多种牌型时，取最大的牌型序号输出。
 *
 * 用例：
 *  输出：
 *      4 H
 *      5 S
 *      6 C
 *      7 D
 *      8 D
 *  输出：
 *      5
 *  说明：
 *      4 5 6 7 8构成顺子，输出5
 *
 *  输入：
 *      9 S
 *      5 S
 *      6 S
 *      7 S
 *      8 S
 *  输出：
 *      1
 *  说明：
 *      既是顺子又是同花，输出1，同花顺
 *
 */
/*
9 S
5 S
6 S
7 S
4 S
4

9 S
5 S
6 S
7 S
4 H
0

9 S
9 H
9 C
9 D
4 H
2

9 S
10 H
J C
Q D
K H
5

9 S
9 H
9 C
4 D
4 H
3
 */
public class CardsCompare {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String[][] holder = new String[5][2];
        for(int i = 0; i< 5; i ++) {
            holder[i] = in.nextLine().split(" ");
        }

        processer(holder);
    }

    public static void processer(String[][] input) {
        Map<String, Integer> tool = new HashMap<>();
        tool.put("2", 2);
        tool.put("3", 3);
        tool.put("4", 4);
        tool.put("5", 5);
        tool.put("6", 6);
        tool.put("7", 7);
        tool.put("8", 8);
        tool.put("9", 9);
        tool.put("10", 10);
        tool.put("J", 11);
        tool.put("Q", 12);
        tool.put("K", 13);
        tool.put("A", 14);

        Map<String, Integer> counter = new HashMap<>();
        int[] cards = new int[5];
        for(int i = 0; i < 5 ; i ++) {
            String cur = input[i][0];
            cards[i] = tool.get(cur);
            if(counter.containsKey(cur)) {
                counter.put(cur, counter.get(cur) + 1);
            } else {
                counter.put(cur, 1);
            }
        }
        boolean success = true;
        switch(counter.size()) {
            case 2: // 要么是四条，要么是葫芦
                 if(counter.containsValue(2)) {
                     System.out.println("3"); // 葫芦
                 }else {
                     System.out.println("2"); // 四条
                 }
                break;
            case 3:
                if(counter.containsValue(3)) {
                    System.out.println("6"); // 三条
                } else {
                    success = false;
                }
                break;
            case 5:
                Arrays.sort(cards);
                boolean shunzi = false;
                if(cards[0]==2 && cards[1]==3 && cards[2] ==4 && cards[3] == 5 && cards[4] == 14) {
                    shunzi = true;
                } else {
                    int count = 0;
                    for(int i = 0; i < 4; i ++) {
                        if(cards[i] + 1 == cards[i + 1]) {
                            count ++;
                        } else {
                            break;
                        }
                    }
                    if(count == 4) {
                        shunzi = true;
                    }
                }

                Set<String> huase = new HashSet<>();
                for(int i = 0; i < 5; i ++) {
                    huase.add(input[i][1]);
                }
                boolean tongse = false;
                if(huase.size() == 1) {
                    tongse = true;
                }

                if(shunzi && tongse) {
                    System.out.println("1"); // 同花顺
                } else if (shunzi && !tongse) {
                    System.out.println("5"); // 顺子
                } else if (!shunzi && tongse) {
                    System.out.println("4"); // 同花
                } else {
                    success = false;
                }
        }
        if(!success) {
            System.out.println("0"); // 什么都不是
        }
    }
}

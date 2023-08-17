package logical;

import java.util.*;

/**
 题目描述
    有这么一款单人卡牌游戏，牌面由颜色和数字组成，颜色为红、黄、蓝、绿中的一种，数字为0-9中的一个。
 游戏开始时玩家从手牌中选取一张卡牌打出，接下来如果玩家手中有和他上一次打出的手牌颜色或者数字相同的手牌，
 他可以继续将该手牌打出，直至手牌打光或者没有符合条件可以继续打出的手牌。
    现给定一副手牌，请找到最优的出牌策略，使打出的手牌最多。

 输入描述
    输入为两行，第一行是每张手牌的数字，数字由空格分隔，第二张为对应的每张手牌的颜色，用r y b g这4个字母分别代表4种颜色，
 字母也由空格分隔。手牌数量不超过10。

 输出描述
    输出一个数字，即最多能打出的手牌的数量。

 输入
 1 4 3 4 5
 r y b b r

 输出
 3


 */
public class PlayCards {
    // dfs函数：cards表示手牌是否可用，last_num表示上一张打出的牌的数字，last_color表示上一张打出的牌的颜色
    public static int dfs(List<String> numbers, List<String> colors, String last_num, String last_color, List<Integer> cards) {
        int maxdepth = 0;
        for (int i = 0; i < cards.size(); i++) {
            if (cards.get(i) != 0) { // 如果这张牌还没被打出去
                if (numbers.get(i).equals(last_num) || colors.get(i).equals(last_color)) { // 如果这张牌的数字或颜色与上一张打出的牌相同
                    cards.set(i, 0); // 打出这张牌
                    maxdepth = Math.max(dfs(numbers, colors, numbers.get(i), colors.get(i), cards), maxdepth); // 继续搜索
                    cards.set(i, 1); // 恢复手牌
                }
            }
        }
        return maxdepth + 1; // 返回当前搜索深度+1
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input_str = sc.nextLine();
        List<String> numbers = new ArrayList<>();
        Scanner ss = new Scanner(input_str);
        while (ss.hasNext()) {
            String temp_str = ss.next();
            numbers.add(temp_str);
        }
        input_str = sc.nextLine();
        List<String> colors = new ArrayList<>();
        ss = new Scanner(input_str);
        while (ss.hasNext()) {
            String temp_str = ss.next();
            colors.add(temp_str);
        }
        List<Integer> cards = new ArrayList<>(Collections.nCopies(numbers.size(), 1)); // 初始化手牌
        int maxiter = 0;
        for (int i = 0; i < numbers.size(); i++) { // 枚举每一张牌
            cards.set(i, 0); // 打出这张牌
            maxiter = Math.max(dfs(numbers, colors, numbers.get(i), colors.get(i), cards), maxiter); // 进行搜索
            cards.set(i, 1); // 恢复手牌
        }
        System.out.println(maxiter); // 输出最多能打出的牌数
    }
}

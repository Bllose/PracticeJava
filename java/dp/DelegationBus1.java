package dp;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 *
 */
public class DelegationBus1 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int[] holder = Arrays.asList(in.nextLine().split(",")).stream().mapToInt(Integer::parseInt).toArray();
        Arrays.sort(holder);

        int rongliang = in.nextInt();
        int[] recorder = new int[holder.length];

        for(int i = 0 ; i < holder.length; i ++) {
            int cur = holder[i];
            int diff = rongliang - cur;
            for(int j = diff; j > 0 ; j --) {
                holder[j] += holder[j - rongliang];
            }
        }

    }
}

package search;

import java.util.Scanner;

public class FindIt {
    static String[] g = new String[105];
    static String w;
    static int n, m;
    static boolean[][] st = new boolean[105][105];

    public static boolean dfs(int x, int y, int k) {
        if (g[x].charAt(y) != w.charAt(k)) return false;
        if (k == w.length() - 1) return true;

        st[x][y] = true;

        int[] dx = {-1, 0, 1, 0}, dy = {0, 1, 0, -1};
        for (int i = 0; i < 4; i++) {
            int a = x + dx[i], b = y + dy[i];
            if (a >= 0 && a < n && b >= 0 && b < m && !st[a][b]) {
                if (dfs(a, b, k + 1)) return true;
            }
        }

        st[x][y] = false;

        return false;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        w = sc.next();

        for (int i = 0; i < n; i++) {
            g[i] = sc.next();
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (dfs(i, j, 0)) {
                    System.out.println((i + 1) + " " + (j + 1));
                    return;
                }
            }
        }

        System.out.println("NO");
    }
}


import java.util.Scanner;

public class Batman {

    static Scanner in = new Scanner(System.in);

    static int testCases, n, m, k;

    static String a[];

    static StringBuilder ans = new StringBuilder();

    static void solve(int t) {

        ans.append("Case ").append(t).append(": ");

        n = a[0].length();
        m = a[1].length();
        k = a[2].length();

        long dp[][][] = new long[n + 1][m + 1][k + 1];

        for (int i = 0; i != n + 1; i++) {

            for (int j = 0; j != m + 1; ++j) {

                for (int k_ = 0; k_ != k + 1; ++k_) {

                    if (i == 0 || j == 0 || k_ == 0) {

                        dp[i][j][k_] = 0L;
                        continue;

                    }

                    if (a[0].charAt(i - 1) == a[1].charAt(j - 1) && a[1].charAt(j - 1) == a[2].charAt(k_ - 1)) {

                        dp[i][j][k_] = dp[i - 1][j - 1][k_ - 1] + 1L;

                    } else {

                        dp[i][j][k_] = Math.max(Math.max(dp[i - 1][j][k_], dp[i][j - 1][k_]), dp[i][j][k_ - 1]);

                    }

                }

            }

        }

        ans.append(dp[n][m][k]);

        if (t != testCases) {

            ans.append("\n");

        }

    }

    public static void main(String[] amit) {

        testCases = in.nextInt();

        for (int t = 0; t != testCases; ++t) {

            input();
            solve(t + 1);

        }

        System.out.print(ans.toString());

    }

    private static void input() {

        a = new String[3];

        for (int i = 0; i != 3; ++i) {

            a[i] = in.next();

        }

    }

}

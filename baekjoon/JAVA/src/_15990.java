import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _15990 {
    static double[][] dp = new double[4][100001];
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();


        int T = Integer.parseInt(in.readLine());

        dp[1][1] = 1;
        dp[2][2] = 1;
        dp[1][3] = 1;
        dp[2][3] = 1;
        dp[3][3] = 1;

        for (int j=4; j<100001; j++) {
            dp[1][j] = (dp[2][j-1] + dp[3][j-1]) % 1000000009;
            dp[2][j] = (dp[1][j-2] + dp[3][j-2]) % 1000000009;
            dp[3][j] = (dp[1][j-3] + dp[2][j-3]) % 1000000009;
        }

        for (int i=0; i<T; ++i) {
            int n = Integer.parseInt(in.readLine());
            sb.append((int)((dp[1][n] + dp[2][n] + dp[3][n]) % 1000000009) + "\n");
        }

        System.out.println(sb);
    }
}
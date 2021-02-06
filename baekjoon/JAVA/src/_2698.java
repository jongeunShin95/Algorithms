import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _2698 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());

        // 테스트 케이스 수
        int T = Integer.parseInt(st.nextToken());

        // dp 배열 생성
        // [n번째수][인접한비트수][0으로 끝남]
        // [n번째수][인접한비트수][1로 끝남]
        // n은 1부터(0은 사용안함)
        int[][][] dp = new int[101][100][2];

        // 1번째는 그냥 담음, 인접이 0이기 떄문에 각각 1임
        dp[1][0][0] = 1;
        dp[1][0][1] = 1;

        // dp[n][k][0] = dp[n-1][k][0] + dp[n-1][k][1];
        // dp[n][k][1] = dp[n-1][k][0] + dp[n-1][k-1][1];

        for(int k=0; k<100; ++k) {
            for (int n=2; n<=100; ++n) {
                dp[n][k][0] += dp[n-1][k][0] + dp[n-1][k][1];
                if (k==0) dp[n][k][1] += dp[n-1][k][0];
                else dp[n][k][1] += dp[n-1][k][0] + dp[n-1][k-1][1];
            }
        }

        for (int tc=1; tc<=T; ++tc) {
            st = new StringTokenizer(in.readLine());
            int n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            System.out.println(dp[n][k][0] + dp[n][k][1]);
        }
    }
}


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _11060 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());

        // 크기 N 미로
        int N = Integer.parseInt(st.nextToken());

        // n번째까지 오는 최소값
        int[] dp = new int[N+1];

        // 미로 입력받기
        st = new StringTokenizer(in.readLine());

        // 한 칸이면 점프안해도됨
        if (N == 1) {
            System.out.println(0);
            return;
        }

        // 1번째 점프할 수 있는거 먼저 받기
        int num = Integer.parseInt(st.nextToken());

        // 마지막에선 점프안하니까
        for (int i=1; i<N; ++i) {
            if (i != 1 && dp[i] == 0) { // 2번째부터 현재까지 i번째에 올 수 없다면 그 구간에서도 못감
                num = Integer.parseInt(st.nextToken());
                continue;
            }
            // 현재 칸에서 점프할 수 있는만큼 다 하기
            for (int j=1; j<=num; ++j) {
                if (i+j > N) break; // 미로 크기 벗어나면 빠져나감
                if (dp[i+j] != 0) dp[i+j] = Math.min(dp[i+j], dp[i] + 1); // 만약 현재 점프하는 곳에 이미 점프한 값이 있으면 i번째에서 온값과 비교해서 작은 값 저장
                else dp[i+j] = dp[i] + 1; // 그게 아니라면 i번째에서 온거에 +1해줌
            }
            // 다음 미로 칸
            num = Integer.parseInt(st.nextToken());
        }

        // 못간다면 -1로
        if (dp[N] == 0) dp[N] = -1;
        System.out.println(dp[N]);
    }
}
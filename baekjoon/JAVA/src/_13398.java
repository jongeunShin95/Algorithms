import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _13398 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());

        // 개수 n
        int n = Integer.parseInt(st.nextToken());

        // dp 배열(0번째 안씀)
        int[] dp = new int[n+1];
        // n 전의 하나의 수를 하나 삭제했을때최대값
        int[] dp2 = new int[n+1];

        // 최대값 저장용
        int _max = 0;
        int _max2 = 0;

        // 수열 입력 받기
        st = new StringTokenizer(in.readLine());

        // 1번째는 그냥 저장해줌
        dp[1] = Integer.parseInt(st.nextToken());
        dp2[1] = dp[1];
        _max = dp[1];
        _max2 = _max;

        // 2개의 수열 이상이면 2까지도 그냥 저장
        if (n != 1) {
            int num = Integer.parseInt(st.nextToken());
            dp[2] = Math.max(dp[1] + num, num);
            dp2[2] = num; // 1번째 삭제했으니 2번째만 있음
            _max = Math.max(_max, dp[2]);
            _max2 = num;
        }
        // 3번째부터 시작
        for (int i=3; i<=n; ++i) {
            int num = Integer.parseInt(st.nextToken());
            // n-1번째 수열을 삭제안했을 경우
            dp[i] = Math.max(dp[i-1] + num, num);
            // n-1번째 수열을 삭제한 경우
            // n-1을 삭제했을 경우와 그 전에 삭제된 경우를 비교
            dp2[i] = Math.max(dp[i-2] + num, dp2[i-1] + num);
            _max = Math.max(_max, dp[i]);
            _max2 = Math.max(_max2, dp2[i]);
        }
        System.out.println(Math.max(_max, _max2));
    }
}

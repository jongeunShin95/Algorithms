import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _2491 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        // 수열의 길이
        int N = Integer.parseInt(st.nextToken());
        // 오름차순용
        int dp1[] = new int[N+1];
        int _max1 = 0;
        // 내림차순용
        int dp2[] = new int[N+1];
        int _max2 = 0;

        // 첫 번째는 그냥 1로
        dp1[1] = 1;
        dp2[1] = 1;
        _max1 = 1;
        _max2 = 1;

        // 수열 입력 받기
        st = new StringTokenizer(in.readLine());

        // 첫번째 수(n-1용)
        int pre_num = Integer.parseInt(st.nextToken());

        for (int i=2; i<=N; ++i) {
            // 현재 n번째 수
            int num = Integer.parseInt(st.nextToken());

            // 오름차순
            if (pre_num <= num) dp1[i] = dp1[i-1] + 1; // 전꺼보다 같거나 크면
            else dp1[i] = 1; // 그게 아니라면
            _max1 = Math.max(dp1[i], _max1);

            // 내림차순
            if (pre_num >= num) dp2[i] = dp2[i-1] + 1; // 전꺼보다 같거나 작으면
            else dp2[i] = 1;
            _max2 = Math.max(dp2[i], _max2);
            pre_num = num;
        }
        // 최종 맥스값
        _max1 = Math.max(_max1, _max2);

        System.out.println(_max1);
    }
}

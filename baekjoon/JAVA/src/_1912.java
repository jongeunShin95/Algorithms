import java.util.Scanner;

public class _1912 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] inputs = new int[n+1]; // 0번째는 안씀
        int[] dp = new int[n+1]; // 0번째는 안씀
        int _max = 0; // 해당 n번째까지 가장 큰 연속합을 담음


        // max(dp[n-1] + inputs[n], inputs[n])
        for (int i=1; i<=n; ++i) {
            inputs[i] = sc.nextInt();

            if (i == 1) {
                dp[1] = inputs[1];
                _max = dp[1];
                continue;
            }
            dp[i] = Math.max(dp[i-1] + inputs[i], inputs[i]);
            _max = Math.max(dp[i], _max);
        }
        System.out.println(_max);
    }
}

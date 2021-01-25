import java.util.Scanner;

public class _2156 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] inputs = new int[n+1]; // 0번째 배열은 안쓰려고
        int[] dp = new int[n+1]; // 0번째 배열은 안쓰려고

        // dp[n] = max(
        // dp[n-1], n번째꺼 선택안했으면 [n-1]의 최대값이 그대로 최대값이됨.
        // inputs[n] + dp[n-2], n번째꺼 선택했으면 [n-2]까지 최대값 + n번째께 최대값이됨
        // inputs[n] + inputs[n-1] + dp[n-3], n번째 선택하고 n-1도 선택했으면 n-2는 선택이 안되기때문에 n-3까지의 최대값을 더한게 최대값
        // );
        // n선택하고 n-1선택하고 n-2 선택은 안됨.
        // n선택하고 n-1선택안하고 n-2선택안하면 최대값이 될 수가 없음
        for (int i=1; i<=n; ++i) {
            inputs[i] = sc.nextInt();
            if (i == 1) dp[1] = inputs[1]; // 하나뿐
            else if (i == 2) dp[2] = inputs[1] + inputs[2]; // 하나뿐
            else if (i == 3) dp[3] = Math.max(dp[2], Math.max(dp[1] + inputs[3], inputs[2] + inputs[3])); // 세 가지의 경우의 수
            else { // 적어둔 점화식
                dp[i] = Math.max(dp[i - 1], Math.max(inputs[i] + dp[i - 2], inputs[i] + inputs[i - 1] + dp[i - 3]));
            }
        }
        System.out.println(dp[n]);
    }
}

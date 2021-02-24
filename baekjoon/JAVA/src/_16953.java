import java.util.Scanner;

public class _16953 {
    static long A;
    static long B;
    static int cnt;

    public static void dfs(long a, int c) {
        if (a > B) return;
        if (a == B) {
            cnt = c + 1;
            return;
        }
        dfs(a * 10 + 1, c + 1); // 끝에 1
        dfs(a * 2, c + 1); // x 2
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        A = sc.nextInt();
        B = sc.nextInt();

        dfs(A, 0);
        cnt = cnt == 0 ? -1 : cnt;
        System.out.println(cnt);
    }
}

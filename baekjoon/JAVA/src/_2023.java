import java.util.Scanner;

public class _2023 {
    static int N;
    static StringBuilder sb = new StringBuilder();

    public static void search(int c, String s) {

        if (c == N) {
            sb.append(s + "\n");
            return;
        }

        for (int i=1; i<=9; ++i) {
            if (isPrime(Integer.parseInt(s + i))) {
                search(c + 1, s + i);
            }
        }

    }

    // 제곱근까지만 나누어보면 됨
    public static boolean isPrime(int n) {
        if (n == 1) return false;
        int max = (int)Math.sqrt(n);
        boolean flag = true;
        for (int i=2; i<=max; ++i) {
            if (n % i == 0) {
                flag = false;
                break;
            }
        }
        return flag;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();

        search(0, "");
        System.out.println(sb);
    }
}

import java.util.Scanner;

public class _1038 {
    static int N;
    static int cnt = 0; // 0번째는 0
    static boolean flag;
    static String result;

    // c -> 몇 자리 수를 만드는지
    public static void search(int s, int c) {
        if (c == 0) {
            cnt++;
            if (cnt == N) {
                flag = true;
                System.out.println(result);
            }
            return;
        }
        for (int i=s; i<=9; ++i) {
            if (!flag) {
                if (result.length() != 0 && result.charAt(result.length()-1) - '0' <= i) return;
                result += i;
                search(0, c - 1);
                result = result.substring(0, result.length()-1);
            } else {
                return;
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // N입력 받기
        N = sc.nextInt();

        // 0일 때는 0출력하고 끝
        if (N == 0) {
            System.out.println(0);
            return;
        }

        // 1022까지가 0~9까지 다 쓴 숫자라 그 위로는 숫자를 만들 수 없었음.
        if (N >= 1023) {
            System.out.println(-1);
            return;
        }

        int num = 1;

        // 시작 값이 1부터로되어있어서 처음 시작 시 0은 그냥 포함시킴
        while (!flag) {
            result = "";
            search(1, num++);
        }


    }
}

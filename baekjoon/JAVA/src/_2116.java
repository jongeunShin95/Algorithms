import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _2116 {
    static int N; // 주사위 수
    static int[][] d; // 주사위 배열
    static int max; // 최대합

    // m -> 면에 적힌 번호, c -> 선택된 주사위 수, s -> 현재까지의 최대합
    public static void search(int m, int c, int s) {

        if (c == N) {
            max = Math.max(s, max);
            return;
        }

        int n = 0;

        for (int i=1; i<=6; ++i) {
            if (m == d[c][i]) {
                n = i;
                break;
            }
        }

        if (n == 1 || n == 6) {
            int tempS = 0;
            n = n == 1 ? 6 : 1;
            for (int i=1; i<=6; ++i) {
                if (i == 1 || i == 6) continue;
                tempS = Math.max(tempS, d[c][i]);
            }
            search(d[c][n], c + 1, s + tempS);
        } else if (n == 2 || n == 4) {
            int tempS = 0;
            n = n == 2 ? 4 : 2;
            for (int i=1; i<=6; ++i) {
                if (i == 2 || i == 4) continue;
                tempS = Math.max(tempS, d[c][i]);
            }
            search(d[c][n], c + 1, s + tempS);
        } else {
            int tempS = 0;
            n = n == 3 ? 5 : 3;
            for (int i=1; i<=6; ++i) {
                if (i == 3 || i == 5) continue;
                tempS = Math.max(tempS, d[c][i]);
            }
            search(d[c][n], c + 1, s + tempS);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // N 입력 받기
        N = Integer.parseInt(in.readLine());
        // 주사위 초기화
        d = new int[N][7];

        // 주사위 입력 받기
        for (int i=0; i<N; ++i) {
            st = new StringTokenizer(in.readLine());
            for (int j=1; j<=6; ++j) {
                d[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 주사위 마주보는면 1 - 6, 2 - 4, 3 - 5
        int tempS = 0;
        for (int i=1; i<=6; ++i) {
            if (i == 1 || i == 6) continue;
            tempS = Math.max(tempS, d[0][i]);
        }
        search(d[0][1], 1, tempS);
        search(d[0][6], 1, tempS);

        tempS = 0;
        for (int i=1; i<=6; ++i) {
            if (i == 2 || i == 4) continue;
            tempS = Math.max(tempS, d[0][i]);
        }
        search(d[0][2], 1, tempS);
        search(d[0][4], 1, tempS);

        tempS = 0;
        for (int i=1; i<=6; ++i) {
            if (i == 3 || i == 5) continue;
            tempS = Math.max(tempS, d[0][i]);
        }
        search(d[0][3], 1, tempS);
        search(d[0][5], 1, tempS);

        System.out.println(max);
    }
}

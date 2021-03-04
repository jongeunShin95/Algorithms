import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _14620 {
    static int N;
    static int[][] map;
    static int min = Integer.MAX_VALUE;
    static boolean[][] visited;

    // s -> 지금까지 비용, c -> 선택된 중심 좌표 개수
    public static void search(int y, int x, int s, int c) {
        // 만약 지금까지의 합이 이미 min을 넘겼다면 더 할 필요가 없음.
        if (s >= min) return;

        if (c == 3) {
            min = Math.min(min, s);
            return;
        }

        for (int i=y; i<N-1; ++i) {
            for (int j=x; j<N-1; ++j) {
                // 만약 피어야 하는 자리에 이미 꽃이 핀 경우는 다음으로
                if (visited[i][j] ||
                        visited[i-1][j] ||
                        visited[i+1][j] ||
                        visited[i][j-1] ||
                        visited[i][j+1]) continue;

                visited[i][j] = true;
                visited[i-1][j] = true;
                visited[i+1][j] = true;
                visited[i][j-1] = true;
                visited[i][j+1] = true;
                int tempSum = map[i][j] + map[i-1][j] + map[i+1][j] + map[i][j-1] + map[i][j+1];

                // x좌표가 마지막 중점 좌표면 다음 y좌표의 처음부터 시작
                if (j == N-2) search(i + 1, 1, s + tempSum, c + 1);
                else search(i, 1, s + tempSum, c + 1);

                visited[i][j] = false;
                visited[i-1][j] = false;
                visited[i+1][j] = false;
                visited[i][j-1] = false;
                visited[i][j+1] = false;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // N 입력 받기
        N = Integer.parseInt(in.readLine());

        // 맵 초기화
        map = new int[N][N];
        visited = new boolean[N][N];

        // 맵 입력 받기
        for (int i=0; i<N; ++i) {
            st = new StringTokenizer(in.readLine());
            for (int j=0; j<N; ++j) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        search(1, 1, 0, 0);
        System.out.println(min);
    }
}

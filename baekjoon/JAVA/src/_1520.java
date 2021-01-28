import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _1520 {
    static int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1} }; // 상, 우, 하, 좌
    static int M;
    static int N;
    static int[][] map;
    static int[][] dp;

    public static int dfs(int y, int x) {
        if (y == (M - 1) && x == (N - 1)) return 1; // 목적지 도착하면 1
        if (dp[y][x] != -1) return dp[y][x]; // 해당 노드가 방문한 경우라면 그 노드에서 목적지로 가는 경우의 수를 반환
        dp[y][x] = 0; // 방문 하면 일단 0으로

        for (int i=0; i<4; ++i) {
            int dy = y + dir[i][0];
            int dx = x + dir[i][1];

            // map 크기를 벗어나면 다음 방향
            // 또는 다음 노드가 오르막길이면 다음 방향으로
            if (dy < 0 || dx < 0 || dy >= M || dx >= N || map[y][x] <= map[dy][dx]) continue;
            // 해당 노드가 방문한 경우 다음 방향으로
            dp[y][x] += dfs(dy, dx);
        }
        return dp[y][x];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());

        M = Integer.parseInt(st.nextToken()); // 행
        N = Integer.parseInt(st.nextToken()); // 열

        map = new int[M][N];
        dp = new int[M][N];

        for (int i=0; i<M; ++i) {
            st = new StringTokenizer(in.readLine());
            for (int j=0; j<N; ++j) {
                map[i][j] = Integer.parseInt(st.nextToken());
                dp[i][j] = -1;
            }
        }

        int result = dfs(0, 0);
        System.out.println(result);
    }
}
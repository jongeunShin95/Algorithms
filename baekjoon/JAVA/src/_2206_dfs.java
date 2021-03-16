import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 시간초과
public class _2206_dfs {
    static int N, M;
    static char[][] map; // 0 -> false, 1 -> true
    static boolean[][] visited;
    static int min = Integer.MAX_VALUE;
    static int dir[][] = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // 상하좌우
    static int[][] minMap; // 각 위치에 대해 최소값을 가지는 배열
//    static int dir[][] = {{1, 0}, {0, -1}, {0, 1}}; // 하좌우

    // c는 해당 위치까지 온 거리
    // b는 해당 위치까지 오면서 벽을 부순적이 있는지 없는지
    public static void search(int y, int x, int c, boolean b) {

        // 현재 위치까지 올 수 있는 거리보다 이미 거리가 길면 그만
        if (minMap[y][x] != 0 && minMap[y][x] <= c) return;

        // 이미 이동 수가 최소값을 넘어선거면 그만
        if (min <= c) return;

        if (y == N-1 && x == M-1) {
            min = Math.min(min, c);
            return;
        }

        for (int i=0; i<4; ++i) {
            int dy = y + dir[i][0];
            int dx = x + dir[i][1];

            // 범위 벗어나면 다음
            if (dy < 0 || dy >= N || dx < 0 || dx >= M) continue;
            // 이미 방문한거면 다음으로
            if (visited[dy][dx]) continue;
            // 만약 벽인데 이미 전에 벽을 부순적이 있다면 다음으로
            if (map[dy][dx] == '1' && b) continue;
            // 만약 벽인데 전에 벽을 부순적이 없다면
            if (map[dy][dx] == '1' && !b) {
                visited[dy][dx] = true;
                minMap[y][x] = c;
                search(dy, dx, c + 1, true); // 부수고 보내봄
                visited[dy][dx] = false;
            }
            // 그냥 길이면 그냥 보내줌
            if (map[dy][dx] == '0') {
                visited[dy][dx] = true;
                minMap[y][x] = c;
                search(dy, dx, c + 1, b);
                visited[dy][dx] = false;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 입력 및 초기화
        st = new StringTokenizer(in.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new char[N][M];
        visited = new boolean[N][M];
        minMap = new int[N][M];

        for (int y=0; y<N; ++y) {
            String str = in.readLine();
            for (int x=0; x<M; ++x) {
                map[y][x] = str.charAt(x);
            }
        }

//        minMap[0][0] = 1;
        search(0, 0, 1, false);
        min = min == Integer.MAX_VALUE ? -1 : min;
        System.out.println(min);
    }
}

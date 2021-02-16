import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _4963 {
    static int w;
    static int h;
    static int[][] map;
    static boolean[][] visited;
    // 처음 방문한 섬이 이미 방문되어있다면 이미 다른 섬과 같은 섬
    static boolean init;

    static int[][] dir = {
            {-1, -1}, {-1, 0}, {-1, 1},
            {0, -1}, {0, 0}, {0, 1},
            {1, -1}, {1, 0}, {1, 1}
    };
    // 섬의 개수
    static int cnt;

    public static void dfs(int y, int x) {
        // 이미 방문한거면
        // 첫 방문이 true면 다른 섬과 함께있는거라서
        // init은 그대로 false로 섬 개수에 카운트되지 않음.
        if (visited[y][x]) return;

        visited[y][x] = true;
        init = true;

        for (int i=0; i<9; ++i) {
            // 자기 자신
            if (i == 4) continue;
            int dy = y + dir[i][0];
            int dx = x + dir[i][1];

            // 범위 벗어나면
            // 그게 바다라면
            // 다음 방향으로
            if (dy < 0 || dy == h || dx < 0 || dx == w || map[dy][dx] == 0) continue;
            dfs(dy, dx);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        while (true) {
            st = new StringTokenizer(in.readLine());
            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());

            // 0, 0받으면 그만
            if (w == 0 && h == 0) break;

            // 맵 초기화
            map = new int[h][w];
            visited = new boolean[h][w];

            // 섬 개수 초기화
            cnt = 0;

            for (int y=0; y<h; ++y) {
                st = new StringTokenizer(in.readLine());
                for (int x=0; x<w; ++x) {
                    map[y][x] = Integer.parseInt(st.nextToken());
                }
            }

            for (int y=0; y<h; ++y) {
                for (int x=0; x<w; ++x) {
                    if (map[y][x] == 1) {
                        dfs(y, x);
                        if (init) cnt++;
                        init = false;
                    }
                }
            }
            sb.append(cnt + "\n");
        }
        System.out.println(sb);
    }
}

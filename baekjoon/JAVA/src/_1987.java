import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _1987 {
    static int R;
    static int C;
    static char[][] map;
    // 상 하 좌 우
    static int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    // 알파벳 방문(0~25) -> (A~Z)
    static boolean[] alphabetVisited = new boolean[26];
    // 맵 방문
    static boolean[][] visited;
    // 최대값
    static int max;

    public static void dfs(int y, int x, int cnt) {

        // 해당 노드 방문 표시
        visited[y][x] = true;
        // 해당 알파벳 방문 표시
        alphabetVisited[map[y][x] - 65] = true;

        for (int i=0; i<4; ++i) {
            int dy = y + dir[i][0];
            int dx = x + dir[i][1];

            // 범위 벗어나면 다음으로
            if (dy < 0 || dy == R || dx < 0 || dx == C) continue;
            // 해당 노드가 방문한거거나 방문된 알파벳이면 다음 노드로
            if (visited[dy][dx] || alphabetVisited[map[dy][dx] - 65]) continue;
            // 그게 아니라면 다음 방문지로 cnt 올려서
            dfs(dy, dx, cnt + 1);
        }
        // 더 이상 못가니까 cnt는 최대임(마지막까지 온 경우면)
        max = Math.max(max, cnt);
        // 다음을 위해 방문 표시는 복구
        visited[y][x] = false;
        alphabetVisited[map[y][x] - 65] = false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 가로, 세로 입력 받기
        st = new StringTokenizer(in.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        // 맵 초기화
        map = new char[R][C];
        visited = new boolean[R][C];

        // 맵 입력 받기
        for (int y=0; y<R; ++y) {
            st = new StringTokenizer(in.readLine());
            String str = st.nextToken();
            for (int x=0; x<C; ++x) {
                map[y][x] = str.charAt(x);
            }
        }

        dfs(0, 0, 1);

        System.out.println(max);
    }
}

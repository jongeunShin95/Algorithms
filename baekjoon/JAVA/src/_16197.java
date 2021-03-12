import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _16197 {
    static int N, M;
    static char[][] map;
    static boolean[][] visited1;
    static boolean[][] visited2;
    static int min = Integer.MAX_VALUE;
    static int[][] coin = new int[2][2]; // 0->y, 1->x
    static int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // 상 하 좌 우

    public static void search(int y1, int x1, int y2, int x2, int c) {
        // 두 동전이 겹친거라면 하나만 떨어질 수 없기때문에 그만
        if (y1 == y2 && x1 == x2) return;
        if (c > 10) return;

        for (int i=0; i<4; ++i) {
            int dy1 = y1 + dir[i][0];
            int dx1 = x1 + dir[i][1];
            int dy2 = y2 + dir[i][0];
            int dx2 = x2 + dir[i][1];

            // 두 동전 모두 떨어진거면 그만
            if ((dy1 < 0 || dy1 >= N || dx1 < 0 || dx1 >= M) && (dy2 < 0 || dy2 >= N || dx2 < 0 || dx2 >= M)) continue;

            // 위에서 모두 떨어지는 경우는 먼저 예외 처리했으니 이제 걸리면 하나만 떨어지는거임.
            if (dy1 < 0 || dy1 >= N || dx1 < 0 || dx1 >= M) {
                min = Math.min(c+1, min);// 한 칸 이동한 경우기때문에 c+1
                continue;
            }
            if (dy2 < 0 || dy2 >= N || dx2 < 0 || dx2 >= M) {
                min = Math.min(c+1, min);
                continue;
            }

            if (!visited1[dy1][dx1] && map[dy1][dx1] != '#' && !visited2[dy2][dx2] && map[dy2][dx2] != '#') {
                visited1[y1][x1] = true;
                visited2[y2][x2] = true;
                search(y1 + dir[i][0], x1 + dir[i][1], y2 + dir[i][0], x2 + dir[i][1], c + 1);
                visited1[y1][x1] = false;
                visited2[y2][x2] = false;
                continue;// 두 동전 모두 갈 수 있으면 하나만 갈 수 있을 경우는 제외해도 됨.
            }

            if (!visited1[dy1][dx1] && map[dy1][dx1] != '#') {
                visited1[y1][x1] = true;
                search(dy1, dx1, y2, x2, c + 1);
                visited1[y1][x1] = false;
            }

            if (!visited2[dy2][dx2] && map[dy2][dx2] != '#') {
                visited2[y2][x2] = true;
                search(y1, x1, dy2, dx2, c + 1);
                visited2[y2][x2] = false;
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
        visited1 = new boolean[N][M];
        visited2 = new boolean[N][M];
        for (int i=0; i<N; ++i) {
            st = new StringTokenizer(in.readLine());
            map[i] = st.nextToken().toCharArray();
        }
        // 동전 위치 받아놓기
        int cnt = 0;
        for (int i=0; i<N; ++i) {
            for (int j=0; j<M; ++j) {
                if (map[i][j] == 'o') {
                    coin[cnt][0] = i;
                    coin[cnt++][1] = j;
                }
            }
        }

        search(coin[0][0], coin[0][1], coin[1][0], coin[1][1], 0);
        if (min == Integer.MAX_VALUE || min > 10) min = -1;
        System.out.println(min);

    }
}

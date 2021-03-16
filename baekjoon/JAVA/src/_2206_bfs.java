import java.awt.print.Pageable;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class _2206_bfs {
    static int N, M;
    static char[][] map; // 0 -> false, 1 -> true
    // 1은 부순경우, 2는 안부순경우
    static boolean[][][] visited; // 마지막은 2차원으로 전에 벽을 부수고 방문한적이 있는지 없는지, 안부수고 방문한적이 있는지 없는지
    static Queue<Node> q = new LinkedList<>();
    static int dir[][] = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // 상하좌우
    static int result;

    static class Node {
        int y;
        int x;
        int b; // 벽을 부순적이 있는지 없는지
        int d; // 해당 좌표까지의 거리
        Node(int y, int x, int b, int d) {
            this.y = y;
            this.x = x;
            this.b = b;
            this.d = d;
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
        visited = new boolean[N][M][2];

        for (int y=0; y<N; ++y) {
            String str = in.readLine();
            for (int x=0; x<M; ++x) {
                map[y][x] = str.charAt(x);
            }
        }

        q.offer(new Node(0, 0, 0, 1));
        visited[0][0][0] = true;
        visited[0][0][1] = true;

        while (!q.isEmpty()) {
            Node tempNode = q.poll();
            int y = tempNode.y;
            int x = tempNode.x;
            int b = tempNode.b;
            int d = tempNode.d;

            if (y == N-1 && x == M-1) {
                result = d;
                break; // bfs라 가장 먼저 방문한게 최소값이 됨.
            }
            for (int i=0; i<4; ++i) {
                int dy = y + dir[i][0];
                int dx = x + dir[i][1];

                if (dy < 0 || dx < 0 || dy >= N || dx >= M) continue;
                // 현재 좌표가 벽이면서 이미 전에 벽을 부순적이 있으면 다음으로
                // 현재 좌표가 벽이지만 전에 벽을 부순적이 없는 경우
                if (map[dy][dx] == '1') {
                    if (b == 0 && !visited[dy][dx][1]) {
                        q.offer(new Node(dy, dx, 1, d + 1));
                        visited[dy][dx][1] = true;
                    }
                }
                // 그냥 갈 수 있다면 전에 정해진 부숨의 여부를 넣어서 큐에 넣어줌
                if (map[dy][dx] == '0') {
                    if (!visited[dy][dx][b]) {
                        visited[dy][dx][b] = true;
                        q.offer(new Node(dy, dx, b, d + 1));
                    }
                }
            }
        }

        result = result == 0 ? -1 : result;
        System.out.println(result);
    }
}
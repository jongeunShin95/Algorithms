import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _15683 {
    static int[][] map;
    static int[][] cctv;
    static int cctvCnt;
    static int N;
    static int M;
    static int min = 64;

    // 1-상, 2-우, 3-하, 4-좌
    static int[][] dir = {
            {0, 0}, {-1, 0}, {0, 1}, {1, 0}, {0, -1}
    };

    // 배열 복사
    static int[][] copyArr(int[][] arr) {
        int[][] returnArr = new int[N][M];
        for (int i=0; i<N; ++i) {
            for (int j=0; j<M; ++j) {
                returnArr[i][j] = arr[i][j];
            }
        }
        return returnArr;
    }

    // cctv 모든 경우의 수 재귀로
    // 1-상, 2-우, 3-하, 4-좌(vDir => 보는 방향)
    // 7이면 감시지역
    public static void search(int cctvNum, int vDir) {
        if (cctvNum == cctvCnt) {
            int tempMin = 0;
            for (int i=0; i<N; ++i) {
                for (int j=0; j<M; ++j) {
                    if (map[i][j] == 0) tempMin++;
                }
            }
            min = Math.min(tempMin, min);
            return;
        }

        int y = cctv[cctvNum][0];
        int x = cctv[cctvNum][1];

        if (map[y][x] == 1) {
            int dy = y + dir[vDir][0];
            int dx = x + dir[vDir][1];

            // 범위 안벗어나고 벽이 아니면 감시지역으로(7이면 감시지역)
            while (dy>=0&&dy<N&&dx>=0&&dx<M&&map[dy][dx]!=6) {
                if (map[dy][dx] == 0) { // cctv면 그냥 다음 좌표로 가기위해
                    map[dy][dx] = 7;
                }
                dy = dy + dir[vDir][0];
                dx = dx + dir[vDir][1];
            }
        } else if (map[y][x] == 2) {
            // 상일때 하일때 어차피 같음
            if (vDir == 3) vDir = 1;
            if (vDir == 4) vDir = 2;
            int dy = y + dir[vDir][0];
            int dx = x + dir[vDir][1];

            // 위쪽 혹은 오른쪽
            while (dy>=0&&dy<N&&dx>=0&&dx<M&&map[dy][dx]!=6) {
                if (map[dy][dx] == 0) { // cctv면 그냥 다음 좌표로 가기위해
                    map[dy][dx] = 7;
                }
                dy = dy + dir[vDir][0];
                dx = dx + dir[vDir][1];
            }
            dy = y + dir[vDir+2][0];
            dx = x + dir[vDir+2][1];

            // 아래쪽 혹은 왼쪽
            while (dy>=0&&dy<N&&dx>=0&&dx<M&&map[dy][dx]!=6) {
                if (map[dy][dx] == 0) { // cctv면 그냥 다음 좌표로 가기위해
                    map[dy][dx] = 7;
                }
                dy = dy + dir[vDir+2][0];
                dx = dx + dir[vDir+2][1];
            }
        } else if (map[y][x] == 3) {
            int dy = y + dir[vDir][0];
            int dx = x + dir[vDir][1];

            while (dy>=0&&dy<N&&dx>=0&&dx<M&&map[dy][dx]!=6) {
                if (map[dy][dx] == 0) { // cctv면 그냥 다음 좌표로 가기위해
                    map[dy][dx] = 7;
                }
                dy = dy + dir[vDir][0];
                dx = dx + dir[vDir][1];
            }
            if (vDir == 4) vDir = 0;
            dy = y + dir[vDir+1][0];
            dx = x + dir[vDir+1][1];

            while (dy>=0&&dy<N&&dx>=0&&dx<M&&map[dy][dx]!=6) {
                if (map[dy][dx] == 0) { // cctv면 그냥 다음 좌표로 가기위해
                    map[dy][dx] = 7;
                }
                dy = dy + dir[vDir+1][0];
                dx = dx + dir[vDir+1][1];
            }
        } else if (map[y][x] == 4) {
            int tempDir;
            if (vDir == 1) {
                tempDir = 4;
            } else {
                tempDir = vDir - 1;
            }
            int dy = y + dir[tempDir][0];
            int dx = x + dir[tempDir][1];

            while (dy>=0&&dy<N&&dx>=0&&dx<M&&map[dy][dx]!=6) {
                if (map[dy][dx] == 0) { // cctv면 그냥 다음 좌표로 가기위해
                    map[dy][dx] = 7;
                }
                dy = dy + dir[tempDir][0];
                dx = dx + dir[tempDir][1];
            }

            dy = y + dir[vDir][0];
            dx = x + dir[vDir][1];

            while (dy>=0&&dy<N&&dx>=0&&dx<M&&map[dy][dx]!=6) {
                if (map[dy][dx] == 0) { // cctv면 그냥 다음 좌표로 가기위해
                    map[dy][dx] = 7;
                }
                dy = dy + dir[vDir][0];
                dx = dx + dir[vDir][1];
            }
            int tempDir2;
            if (vDir == 4) {
                tempDir2 = 1;
            } else {
                tempDir2 = vDir + 1;
            }
            dy = y + dir[tempDir2][0];
            dx = x + dir[tempDir2][1];

            while (dy>=0&&dy<N&&dx>=0&&dx<M&&map[dy][dx]!=6) {
                if (map[dy][dx] == 0) { // cctv면 그냥 다음 좌표로 가기위해
                    map[dy][dx] = 7;
                }
                dy = dy + dir[tempDir2][0];
                dx = dx + dir[tempDir2][1];
            }
        } else {
            // 5일때는
            // 모든 방향 다함
            for (int i=1; i<=4; ++i) {
                int dy = y + dir[i][0];
                int dx = x + dir[i][1];

                while (dy>=0&&dy<N&&dx>=0&&dx<M&&map[dy][dx]!=6) {
                    if (map[dy][dx] == 0) { // cctv면 그냥 다음 좌표로 가기위해
                        map[dy][dx] = 7;
                    }
                    dy = dy + dir[i][0];
                    dx = dx + dir[i][1];
                }
            }
        }
        int[][] tempArr = copyArr(map);
        // 다음 cctv로
        search(cctvNum+1, 1);
        // 해당 상태 전 으로 복구
        map = copyArr(tempArr);
        search(cctvNum+1, 2);
        map = copyArr(tempArr);
        search(cctvNum+1, 3);
        map = copyArr(tempArr);
        search(cctvNum+1, 4);
        map = copyArr(tempArr);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // N, M
        st = new StringTokenizer(in.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        // 맵 생성
        map = new int[N][M];

        // cctv 좌표 배열 (최대 8개라 일단 선언)
        cctv = new int[8][2];
        // cctv 수
        cctvCnt = 0;

        for (int i=0; i<N; ++i) {
            st = new StringTokenizer(in.readLine());
            for (int j = 0; j < M; ++j) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] >= 1 && map[i][j] <= 5) {
                    cctv[cctvCnt][0] = i;
                    cctv[cctvCnt++][1] = j;
                }
            }
        }

        int[][] tempMap = copyArr(map);
        search(0, 1);
        map = copyArr(tempMap);
        search(0, 2);
        map = copyArr(tempMap);
        search(0, 3);
        map = copyArr(tempMap);
        search(0, 4);

        System.out.println(min);

    }
}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class _2636 {
    // 치즈좌표 클래스
    static class Cheese {
        int y;
        int x;
        Cheese(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
    static int Y, X; // 행, 열
    static int[][] pizza; // 피자 맵
    static boolean[][] visited; // 같은 시간에 녹은 노드를 방문하면 그 부분은 공기로 취급안해야함.
    static boolean[][] visited2; // 벽 찾을 때 사용하는 방문값
    static boolean flag; // 벽 찾으면 나머지 재귀는 종료하기 위해
    static int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // 상, 하, 좌, 우
    static Queue<Cheese> queue = new LinkedList<>(); // 치즈 담는 큐
    static int cheeseCnt; // 치즈 칸 개수
    static int preCheeseCnt; // 이전 치즈 개수
    static int time; // 시간

    // 녹이기
    public static void melt(int y, int x) {
        visited[y][x] = true;
        for (int i=0; i<4; ++i) {
            int dy = y + dir[i][0];
            int dx = x + dir[i][1];

            if (dy < 0 || dy >= Y || dx < 0 || dx >= X) continue; // 범위 벗어나면 다음 방향
            if (visited[dy][dx]) continue; // 이미 방문한거면 다음방향으로(방문했던게 녹은거도 예외처리가능)
            if (pizza[dy][dx] == 1) melt(dy, dx); // 그 부분이 치즈라면 dfs로
            else { // 공기부분이라면 그 공기가 벽까지 가는지 확인
                visited2 = new boolean[Y][X];
                flag = false;
                search(dy, dx, y, x);
            }
        }
    }

    // 벽을 찾는 함수
    // initY, initX는 치즈 부분(녹일 수 있으면 이 좌표를 녹임)
    public static void search(int y, int x, int initY, int initX) {
        if (flag) return; // 벽을 찾은거면 종료
        visited2[y][x] = true;
        for (int i=0; i<4; ++i) {
            int dy = y + dir[i][0];
            int dx = x + dir[i][1];
            if (dy < 0 || dy >= Y || dx < 0 || dx >= X) { // 벽이라면
                pizza[initY][initX] = 0; // 해당 부분 녹이고
                flag = true; // 찾음을 표시하고
                return; // 함수 종료
            }
            if (pizza[dy][dx] == 1) continue; // 치즈로 막히면 다음 방향으로
            if (visited2[dy][dx] || visited[dy][dx]) continue; // 이미 방문한거면 다음 방향으로
            if (flag) return; // 벽을 찾은거면 종료
            search(dy, dx, initY, initX); // 공기부분이라면 다음 공기로
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 행, 열 입력
        st = new StringTokenizer(in.readLine());
        Y = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        // 맵 초기화
        pizza = new int[Y][X];

        // 맵 입력 받기
        for (int y=0; y<Y; ++y) {
            st = new StringTokenizer(in.readLine());
            for (int x=0; x<X; ++x) {
                pizza[y][x] = Integer.parseInt(st.nextToken());
                if (pizza[y][x] == 1) {
                    queue.offer(new Cheese(y, x));
                    cheeseCnt++;
                }
            }
        }

        // 모든 치즈가 녹을 때까지
        while (!queue.isEmpty()) {
            // 방문배열 초기화
            visited = new boolean[Y][X];
            // 일단 모든 치즈 방문하면서 녹이기 시도
            for (int i=0; i<cheeseCnt; ++i) {
                // 해당 노드가 방문이 안되었던거면 덩어리 녹이기 시작
                Cheese temp = queue.poll();
                if (!visited[temp.y][temp.x]) melt(temp.y, temp.x);
                queue.offer(temp);
            }
            preCheeseCnt = cheeseCnt; // 녹은 치즈는 수에서 뺴야되서 임시로 저장함
            for (int i=0; i<preCheeseCnt; ++i) {
                Cheese temp = queue.poll();

                if (pizza[temp.y][temp.x] == 0) cheeseCnt--; // 녹았으면 개수 감소
                else queue.offer(temp); // 그게 아니라면 다시 큐에 넣어줌
            }
            time++; // 시간 증가
        }
        System.out.println(time);
        System.out.println(preCheeseCnt);
    }
}

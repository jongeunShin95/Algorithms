import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class snake {
    int y;
    int x;
    snake(int y, int x) {
        this.y = y;
        this.x = x;
    }
}

public class _3190 {
    // 뱀 - 1, 사과 - 2
    static int[][] map;
    // 뱀 머리 위치, 꼬리 위치
    static int hY;
    static int hX;
    static int tY;
    static int tX;
    static int N;
    static int K;
    // 뱀이 바라보는 방향
    // 1 - 상, 2 - 우, 3 - 하, 4 - 좌
    static int sView = 2;
    static int[][] dir = {{0, 0}, {-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    static boolean flag;
    // 결과값
    static int result;
    // 꼬리용
    static Queue<snake> snakeTail;

    public static void move(int t, String d) {
        // t초 이동
        for (int i=0; i<t; ++i) {
            int dy = hY + dir[sView][0];
            int dx = hX + dir[sView][1];
            // 초 증가
            result++;
            // 벽에 부딪히면 게임 종료 플래그
            // 자기 자신에 부딪히면 게임 종료 플래그
            if (dy < 0 || dy == N || dx < 0 || dx == N || map[dy][dx] == 1) {
                flag = true;
                return;
            }
            snakeTail.offer(new snake(dy, dx));
            // 머리 바꾸기 때문에 값을 미리 받아둠
            int temp = map[dy][dx];
            // 머리 위치 뱀(1)로 변경
            map[dy][dx] = 1;
            // 머리 위치 변경
            hY = dy;
            hX = dx;
            // 머리 부분에 사과가 없으면 길이를 줄여야함
            if (temp != 2) {
                snake tempSnake = snakeTail.poll();
                map[tempSnake.y][tempSnake.x] = 0;
            }
        }
        // 방향 왼쪽으로 변경
        if (d.equals("L")) {
            sView -= 1;
            if (sView == 0) sView = 4;
        } else if (d.equals("D")) { // 오른쪽으로 변경
            sView += 1;
            if (sView == 5) sView = 1;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        // N입력
        st = new StringTokenizer(in.readLine());
        N = Integer.parseInt(st.nextToken());

        // 뱀 꼬리 큐 초기화
        snakeTail = new LinkedList<>();

        // 맵 배열 생성 및 뱀 처음 위치 초기화
        map = new int[N+1][N+1];
        map[0][0] = 1;
        snakeTail.offer(new snake(0, 0));
        hY = 0;
        hX = 0;
        tY = 0;
        tX = 0;

        // 사과 개수
        st = new StringTokenizer(in.readLine());
        K = Integer.parseInt(st.nextToken());

        // 사과 위치
        for (int i=0; i<K; ++i) {
            st = new StringTokenizer(in.readLine());
            map[Integer.parseInt(st.nextToken())-1][Integer.parseInt(st.nextToken())-1] = 2;
        }

        // 뱀 방향 변환 횟수
        st = new StringTokenizer(in.readLine());
        int L = Integer.parseInt(st.nextToken());

        // 몇초 이동중인지위해
        int pre_t = 0;

        // 뱀 변환
        for (int i=0; i<L; ++i) {
            st = new StringTokenizer(in.readLine());
            int t = Integer.parseInt(st.nextToken());
            String d = st.nextToken();

            // 게임 안끝났으면 수행
            // 입력은 받아야되서
            if (!flag) move(t - pre_t, d);
            pre_t = t;
        }
        // 마지막 이동
        // 최대 100크기니 끝에서 끝이더라도 100이 최대
        // 방향은 필요없어서 아무거나
        if (!flag) {
            move(100, "E");
        }
        System.out.println(result);
    }
}

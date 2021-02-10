import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _2564 {
    static int N;
    static int M;
    static int storeCnt;
    // y는 방향, x는 거리
    static int[][] store;
    static int y;
    static int x;
    // 스토어까지 거리 담는 배열
    static int[] distance;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

         // 맵 크기 입력받기
        st = new StringTokenizer(in.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        // 상점 수
        st = new StringTokenizer(in.readLine());
        storeCnt = Integer.parseInt(st.nextToken());

        // 거리 배열 및 상점 배열 생성
        distance = new int[storeCnt];
        store = new int[storeCnt][2];

        // 가게 좌표 입력받기
        for (int i=0; i<storeCnt; ++i) {
            st = new StringTokenizer(in.readLine());
            store[i][0] = Integer.parseInt(st.nextToken());
            store[i][1] = Integer.parseInt(st.nextToken());
        }

        // 내 좌표
        st = new StringTokenizer(in.readLine());
        y = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());

        // 모든 가게 순환
        for (int i=0; i<storeCnt; ++i) {
            // 같은 방향이면 그냥 각 위치 빼준게 최소값
            if (store[i][0] == y) {
                distance[i] = Math.abs(x - store[i][1]);
                continue;
            }

            // 내가 북, 남이면 옆은 서, 동임
            // 그리고 가게 위치가 서로 바라보는방향일 때
            if (y == 1 || y == 2) {
                // 가게가 서쪽일 때
                if (store[i][0] == 3) {
                    distance[i] = x; // 일단 내 거리를 더해줌.(서쪽이라 0부터시작이니)
                    if (y == 1) distance[i] += store[i][1]; // 내가 북쪽으면 가게 거리 그냥 더해줌
                    else distance[i] += M - store[i][1]; // 남쪽이면 반대
                } else if (store[i][0] == 4) { // 가게가 동쪽일 때
                    distance[i] = N - x; // 일단 내 위치에서 동쪽 까지 거리를 더해줌
                    if (y == 1) distance[i] += store[i][1]; // 내가 북쪽이면 그냥 더해줌
                    else distance[i] += M - store[i][1]; // 남쪽이면 반대
                } else if ((y == 1 && store[i][0] == 2) || (y == 2 && store[i][0] == 1)) { // 서로 마주볼때
                    // 시계방향, 반대방향 나누기
                    int temp = x + store[i][1] + M;
                    int temp2 = (N - x) + (N - store[i][1]) + M;
                    // 더 작은거
                    distance[i] = Math.min(temp, temp2);
                }
            } else if (y == 3 || y == 4) { // 내가 서, 동일때
                // 가게가 복쪽일 때
                if (store[i][0] == 1) {
                    distance[i] = x; // 일단 위쪽이니 그냥 내 거리를 더해줌
                    if (y == 3) distance[i] += store[i][1]; // 서쪽 기준으로는 그냥 더해줌
                    else distance[i] += N - store[i][1]; // 동쪽기준으로는 총 거리에서 빼줌
                } else if (store[i][0] == 2) { // 가게가 남쪽일 때
                    distance[i] = M - x; // 밑쪽은 총 거리에서 뺴줌
                    if (y == 3) distance[i] += store[i][1];
                    else distance[i] += N - store[i][1];
                } else if ((y == 3 && store[i][0] == 4) && (y == 4 && store[i][0] == 3)) { // 마주볼 때
                    int temp = x + store[i][1] + N;
                    int temp2 = (M - x) + (M - store[i][1]) + N;
                    distance[i] = Math.min(temp, temp2);
                }
            }
        }

        // 거리 다 더해주기
        int result = 0;
        for (int i=0; i<storeCnt; ++i) result += distance[i];

        System.out.println(result);
    }
}

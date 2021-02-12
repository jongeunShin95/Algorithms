import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Node {
    int y;
    int x;
    Node(int y, int x) {
        this.y = y;
        this.x = x;
    }
}

public class _3190 {
    // 몇년차
    static int result;
    static int N;
    static int M;
    static int[][] arr;
    // 빙하 개수
    static int nodeCnt;
    // 방문용
    static boolean[][] visited;
    static Queue<Node> queue = new LinkedList<>();
    // 상, 하, 좌, 우
    static int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        // N, M 입력 받기
        st = new StringTokenizer(in.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N][M];

        for (int y=0; y<N; ++y) {
            st = new StringTokenizer(in.readLine());
            for (int x=0; x<M; ++x) {
                arr[y][x] = Integer.parseInt(st.nextToken());
                if (arr[y][x] != 0) {
                    queue.offer(new Node(y, x));
                    nodeCnt++;
                }
            }
        }

        // 모든 빙하가 녹으면 그만
        while (!queue.isEmpty()) {
            // 방문노드 초기화
            visited = new boolean[N][M];
            // BFS용 큐
            Queue<Node> bfsQueue = new LinkedList<>();
            // 첫 노드를 넣기 및 방문 표
            bfsQueue.offer(new Node(queue.peek().y, queue.peek().x));
            visited[queue.peek().y][queue.peek().x] = true;
            // 덩어리가 분리되었는지 확인
            // 다 녹은 빙하는 빼야되서 nodeCnt로
            for (int k=0; k<nodeCnt; ++k) {
                Node tempNode = bfsQueue.poll();
                if (tempNode == null) break; // 큐가 비었으면 for문 종료
                for (int i=0; i<4; ++i) {
                    int dy = tempNode.y + dir[i][0];
                    int dx = tempNode.x + dir[i][1];

                    // 범위 벗어나거나 이미 방문한거나 해당 노드가 빙하가 아니라면 다음 방향으로
                    if (dy < 0 || dy >= N || dx < 0 || dx >= M || arr[dy][dx] == 0 || visited[dy][dx]) continue;
                    // 그게 아니라면 큐에 추가
                    bfsQueue.offer(new Node(dy, dx));
                    // 또한 노드는 방문표시
                    visited[dy][dx] = true;
                }
            }
            // 원래 queue에 있던 visited가 모두 방문되었는지 확인
            // 방문 되지 않은게 있다면 빙하 덩어라가 분리되어있는거임
            for (int k=0; k<nodeCnt; ++k) {
                Node tempNode = queue.poll();

                // 방문한거면 다시 집어넣기
                if (visited[tempNode.y][tempNode.x]) queue.offer(tempNode);
                else { // 방문안한게 있다면 년차 출력하고 종료
                    System.out.println(result);
                    return;
                }
            }

            // 방문 배열 초기화
            // 녹이기에서 사용해야함
            visited = new boolean[N][M];

            // for문 안에서 nodeCnt를 줄여버려서 for문에 영향이 있어서 임시로 받아둠
            int tempNodeCnt = nodeCnt;
            // 빙하개수만큼 한번씩 돌기
            for (int k=0; k<tempNodeCnt; ++k) {
                Node tempNode = queue.poll();
                // 해당 노드가 n년차에 녹아 0이 되었는데 옆에 노드는 해당노드가 원래
                // 0으로 인식하여 같은 년차에 --가 한번 더 일어나버려서
                // visited된 0은 --에 포함안시킴
                visited[tempNode.y][tempNode.x] = true;
                for (int i=0; i<4; ++i) {
                    int dy = tempNode.y + dir[i][0];
                    int dx = tempNode.x + dir[i][1];

                    // 범위 벗어나면
                    // 해당 좌표가 빙하라 이미 방문되었다면
                    // 빙하지만 방문되지않았을 경우라면
                    // 다음 방향으로
                    if (dy < 0 || dy >= N || dx < 0 || dx >= M || visited[dy][dx] || arr[dy][dx] != 0) continue;
                    // 만약 0이면 녹이기
                    arr[tempNode.y][tempNode.x]--;
                    // 해당 빙하가 다 녹으면 다음 빙하노드로
                    if (arr[tempNode.y][tempNode.x] == 0) break;
                }
                // 빙하가 다 안녹았으면 queue에 다시 집어넣기
                if (arr[tempNode.y][tempNode.x] != 0) queue.offer(tempNode);
                else nodeCnt--; // 다 녹았으면 빙하 개수 줄이기
            }
            // 년차 늘리기
            result++;

        }
        // 모든 빙하가 녹았다는건 분리된적이 없다는거
        System.out.println(0);
    }
}
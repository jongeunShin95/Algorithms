import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class _1707 {
    static int K;
    static int[] c; // 정점의 색 1은 검정, 2는 빨강
    static boolean[] visited;
    static ArrayList<Integer>[] g; // 각 배열에는 인접한 노드가 저장되어있음.
    static Queue<Integer> q;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        // 입력 및 초기화
        K = Integer.parseInt(in.readLine());

        for (int tc=1; tc<=K; ++tc) {
            st = new StringTokenizer(in.readLine());
            int V = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());

            g = new ArrayList[V + 1];
            for (int i=1; i<=V; ++i) g[i] = new ArrayList<>();
            c = new int[V + 1];
            visited = new boolean[V + 1];
            q = new LinkedList<>();
            boolean exit = false;

            for (int i=0; i<E; ++i) {
                st = new StringTokenizer(in.readLine());
                int A = Integer.parseInt(st.nextToken());
                int B = Integer.parseInt(st.nextToken());
                g[A].add(B);
                g[B].add(A);
            }

            // 각 정점마다 시작정점으로 돌려주는데
            // 이미 방문되어있다면 넘어감
            for (int j=1; j<=V; ++j) {
                if (exit) break;
                if (visited[j]) continue;
                q.offer(j); // 시작노드번호 넣기
                c[j] = 1; // 검은색으로 칠하기
                while (!q.isEmpty()) {
                    if (exit) break;
                    int cN = q.poll();

                    if (visited[cN]) continue;

                    visited[cN] = true;

                    for (int i=0, end=g[cN].size(); i<end; ++i) {
                        if (visited[g[cN].get(i)]) continue;
                        // 만약 해당 노드가 색칠되지 않은 것이라면 색칠해주고 큐에 넣어줌
                        if (c[g[cN].get(i)] == 0) {
                            if (c[cN] == 1) c[g[cN].get(i)] = 2;
                            else if (c[cN] == 2) c[g[cN].get(i)] = 1;
                            q.offer(g[cN].get(i));
                        } else { // 이미 색칠이 되어있다면 지금 노드의 색과 다른지 비교
                            if (c[cN] == c[g[cN].get(i)]) {
                                sb.append("NO\n");
                                exit = true; // 더 이상 볼 필요가 없음
                                break;
                            }
                        }
                    }
                }
            }
            if (!exit) sb.append("YES\n"); // 모든 노드를 확인했는데 같은 색이 없다면 이분 그래프임.
        }
        System.out.println(sb);
    }
}

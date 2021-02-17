import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class _5567 {
    static int N;
    static int M;
    // 1 ~ N은 배열로 각 배열에는 친구관계를 arraylist로
    static ArrayList<Integer>[] relative;
    //  1 -> 2, 1 -> 3, 2 -> 4, 3 -> 4
    // 위 경우와 같이 같은 친구를 또 초대할 수 있으니
    // 이미 초대된 친구는 방문체크
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        // 결과값
        int cnt = 0;

        // N명 입력
        st = new StringTokenizer(in.readLine());
        N = Integer.parseInt(st.nextToken());
        relative = new ArrayList[N+1];
        for (int i=0; i<=N; ++i) relative[i] = new ArrayList<>();
        visited = new boolean[N+1];

        // 관계 리스트 길이 입력
        st = new StringTokenizer(in.readLine());
        M = Integer.parseInt(st.nextToken());

        // 관계 입력 받기
        for (int i=0; i<M; ++i) {
            st = new StringTokenizer(in.readLine());
            int f = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            relative[f].add(s);
            relative[s].add(f);
        }
        visited[1] = true;
        for (int i: relative[1]) {
            // 이미 초대된사람은 이 부분 수행 x
            if (!visited[i]) {
                visited[i] = true;
                cnt++;
            }
            // 선택된 친구의 친구들도 탐색
            for (int j: relative[i]) {
                // 이미 초대된 사람이면 다음으로
                if(visited[j]) continue;
                visited[j] = true;
                cnt++;
            }
        }
        System.out.println(cnt);
    }
}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class _1759 {
    static int L;
    static int C;
    static char[] arr;
    static boolean[] visited;
    static char[] result;
    static int vCnt; // 모음 개수
    static int cCnt; // 자음 개수
    static StringBuilder sb = new StringBuilder();

    // c는 현재까지 선택된 수, s는 시작 번호
    public static void search(int c, int s) {
        if (c == L) {
            vCnt = 0;
            cCnt = 0;
            for (int i=0; i<L; ++i) {
                if (result[i] == 'a'
                        || result[i] == 'e'
                        || result[i] == 'i'
                        || result[i] == 'o'
                        || result[i] == 'u') vCnt++;
                else cCnt++;
            }
            if (vCnt >= 1 && cCnt >= 2) sb.append(new String(result) + "\n");
            return;
        }

        for (int i=s; i<C; ++i) {
            if (visited[i]) continue;

            // 선택된 개수가 하나 이상이고 현재 i번째 문자가 전에 선택된 문자보다 작으면 다음으로
            if (c > 0 && arr[i] < result[c - 1]) continue;
            visited[i] = true;
            result[c] = arr[i];
            search(c + 1, i + 1);
            visited[i] = false;

        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 입력 및 초기화
        st = new StringTokenizer(in.readLine());
        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        arr = new char[C];
        visited = new boolean[C];
        result = new char[L];

        st = new StringTokenizer(in.readLine());
        for (int i=0; i<C; ++i) arr[i] = st.nextToken().charAt(0);
        // 정렬을 하고나면 조합으로 고를 수 있음.
        Arrays.sort(arr);

        search(0, 0);
        System.out.println(sb);
    }
}

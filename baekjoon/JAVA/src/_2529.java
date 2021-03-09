import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _2529 {
    static int k;
    static String[] s; // 부등호 배열
    static char[] set; // 조건에 맞는 숫자 배열
    static boolean[] visited = new boolean[10];
    static double min = Double.MAX_VALUE;
    static double max = 0;
    static String resultMax = "";
    static String resultMin = "";

    // c는 선택된 수
    static public void search(int c) {
        if (c == k + 1) {
            if (max < Double.parseDouble(new String(set))) {
                max = Double.parseDouble(new String(set));
                resultMax = String.valueOf(set);
            }
            if (min > Double.parseDouble(new String(set))) {
                min = Double.parseDouble(new String(set));
                resultMin = String.valueOf(set);
            }
            return;
        }

        for (int i=0; i<=9; ++i) {
            if (visited[i]) continue;

            visited[i] = true;

            if (c != 0) {
                if (s[c-1].equals("<")) {
                    int prevNum = set[c-1] - '0';
                    if (i > prevNum) {
                        set[c] = (char)(i + '0');
                        search(c + 1);
                    }
                } else if (s[c-1].equals(">")) {
                    int prevNum = set[c-1] - '0';
                    if (i < prevNum) {
                        set[c] = (char)(i + '0');
                        search(c + 1);
                    }
                }
            } else {
                set[c] = (char)(i + '0');
                search(c + 1);
            }
            visited[i] = false;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        // 입력 받기 및 초기화
        k = Integer.parseInt(in.readLine());
        set = new char[k+1];
        st = new StringTokenizer(in.readLine());
        s = new String[k];
        for (int i=0; i<k; ++i) s[i] = st.nextToken();

        search(0);

        System.out.println(resultMax);
        System.out.println(resultMin);
    }
}

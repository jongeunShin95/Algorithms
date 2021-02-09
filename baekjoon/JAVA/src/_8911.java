import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _8911 {
    // 거북이 위치
    static int y;
    static int x;
    // 방위 (상, 하, 좌, 우)
    static int[][] dir = {
            {-1, 0}, {1, 0}, {0, -1}, {0, 1}
    };
    // 거북이 방향
    // 1-상, 2-우, 3-하, 4-좌
    static int t;

    // 가장 큰 y축, x축 길이(양의방향)
    static int m_y;
    static int m_x;
    // 가장 큰 y축, x축 길이(음의방향)
    static int m_y2;
    static int m_x2;

    public static void move(char c) {
        if (c == 'F') {
            if (t == 1) {
                y = y + dir[0][0];
                x = x + dir[0][1];
                if (0 > y) m_y2 = Math.min(m_y2, y);
                else if (0 < y) m_y = Math.max(m_y, y);
            } else if (t == 2) {
                y = y + dir[3][0];
                x = x + dir[3][1];
                if (0 > x) m_x2 = Math.min(m_x2, x);
                else if (0 < x) m_x = Math.max(m_x, x);
            } else if (t == 3) {
                y = y + dir[1][0];
                x = x + dir[1][1];
                if (0 > y) m_y2 = Math.min(m_y2, y);
                else if (0 < y) m_y = Math.max(m_y, y);
            } else {
                y = y + dir[2][0];
                x = x + dir[2][1];
                if (0 > x) m_x2 = Math.min(m_x2, x);
                else if (0 < x) m_x = Math.max(m_x, x);
            }
        } else {
            if (t == 1) {
                y = y + dir[1][0];
                x = x + dir[1][1];
                if (0 > y) m_y2 = Math.min(m_y2, y);
                else if (0 < y) m_y = Math.max(m_y, y);
            } else if (t == 2) {
                y = y + dir[2][0];
                x = x + dir[2][1];
                if (0 > x) m_x2 = Math.min(m_x2, x);
                else if (0 < x) m_x = Math.max(m_x, x);
            } else if (t == 3) {
                y = y + dir[0][0];
                x = x + dir[0][1];
                if (0 > y) m_y2 = Math.min(m_y2, y);
                else if (0 < y) m_y = Math.max(m_y, y);
            } else {
                y = y + dir[3][0];
                x = x + dir[3][1];
                if (0 > x) m_x2 = Math.min(m_x2, x);
                else if (0 < x) m_x = Math.max(m_x, x);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        // 테스트 케이스 수
        st = new StringTokenizer(in.readLine());
        int T = Integer.parseInt(st.nextToken());

        // T만큼
        for (int tc=1; tc<=T; ++tc) {
            // 초기값
            y = 0;
            x = 0;
            t = 1;
            m_y = 0;
            m_y2 = 0;
            m_x = 0;
            m_x2 = 0;

            // 컨트롤 프로그램 입력받기
            st = new StringTokenizer(in.readLine());
            String con = st.nextToken();

            for (int i=0; i<con.length(); ++i) {
                char c = con.charAt(i);
                switch (c) {
                    case 'F':
                    case 'B':
                        move(c);
                        break;
                    case 'L':
                        t = (t - 1 == 0) ? 4 : t - 1;
                        break;
                    case 'R':
                        t = (t + 1 == 5) ? 1 : t + 1;
                        break;
                }
            }
            int result_x = m_x - m_x2;
            int result_y = m_y - m_y2;
            sb.append(result_x * result_y + "\n");
        }
        System.out.println(sb);
    }
}

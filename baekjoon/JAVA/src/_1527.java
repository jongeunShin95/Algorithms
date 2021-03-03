import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class _1527 {
    static long A;
    static long B;
    static Queue<Long> q = new LinkedList<>(); // 4, 7로 만든 수를 담는 큐
    static int cnt; // 정답

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 입력 받기
        st = new StringTokenizer(in.readLine());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());

        // 처음 4, 7은 넣어줌
        q.offer(4L);
        q.offer(7L);

        while (!q.isEmpty()) {
            long num = q.poll(); // 하나를 가져옴.

            // 그 수가 B보다 크면 먼저 종료
            if (num > B) break;
            // 그 수가 A와 B 사이면 정답 수 +1함.
            if (num >= A && num <= B) cnt++;
            // 맨 뒷자리에 4, 7을 추가한 수를 큐에 넣어줌.
            q.offer(num*10 + 4); // 여기서 10억인 경우 10 곱하면 int 벗어나서 long으로 해줬음.
            q.offer(num*10 + 7);
        }

        System.out.println(cnt);
    }
}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class _1449 {
    static int N; // 물이 새는 곳 개수
    static int L; // 테이프 길이
    static int cnt; // 테이프 개수

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // R, L 입력 받기
        st = new StringTokenizer(in.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        // 작은거부터 뽑기위해
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        // 위치 입력받기
        st = new StringTokenizer(in.readLine());
        for (int i=0; i<N; ++i) {
            pq.add(Integer.parseInt(st.nextToken()));
        }

        // 하나 남거나 없어지면 테이프 개수 +1해주고 끝
        while (pq.size() > 1) {
            int num1 = pq.poll();
            int num2 = pq.poll();

            while (true) {
                int d = num2 - num1 + 1;
                // 만약 두 구멍을 막고도 테이프가 남는다면 다음 좌표를 가져옴
                if (d <= L) {
                    if (pq.size() == 0) break; // 만약 마지막꺼를 뽑았던거라면 그만
                    num2 = pq.poll(); // 그게 아니라면 새로 하나 더 꺼내서 진행
                } else { // 그게 아니라면 그 좌표는 다시 힙에 넣고 개수증가 후 while문 나가기
                    pq.add(num2);
                    cnt++;
                    break;
                }
            }
        }
        cnt++;
        System.out.println(cnt);
    }
}

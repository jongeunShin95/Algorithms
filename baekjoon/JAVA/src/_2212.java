import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class _2212 {
    static int N; // 센서 수
    static int K; // 집중국 수
    static Set<Integer> s = new HashSet<>(); // 중복 제거용
    static PriorityQueue<Integer> pq = new PriorityQueue<>(); // 집중국 오름차순
    static PriorityQueue<Integer> d = new PriorityQueue<>(); // 거리 오름차순
    static int min; // 최소합

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // N, K 입력받기
        N = Integer.parseInt(in.readLine());
        K = Integer.parseInt(in.readLine());

        // 좌표 입력 받기
        st = new StringTokenizer(in.readLine());
        for (int i=0; i<N; ++i) {
            pq.add(Integer.parseInt(st.nextToken()));
        }

        // 집중국이 같거나 더 많으면 각 센서마다 설치가능해서 0
        if (N <= K) {
            System.out.println(0);
            return;
        }

        // 거리 오름차순
        for (int i=0; i<N-1; ++i) {
            int num1 = pq.poll();
            int num2 = pq.peek();
            d.add(num2 - num1);
        }

        for (int i=K-1; i<N-1; ++i) {
            min += d.poll();
        }

        System.out.println(min);

    }
}

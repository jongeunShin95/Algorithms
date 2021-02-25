import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class _11497 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        // 테스트 케이스 수
        int T = Integer.parseInt(in.readLine());

        for (int tc=0; tc<T; ++tc) {
            // 최소힙
            PriorityQueue<Integer> pq = new PriorityQueue<>();

            // 최대값
            int max = 0;

            // N 입력 받기
            int N = Integer.parseInt(in.readLine());
            st = new StringTokenizer(in.readLine());

            // 정렬된 값들을 담는 배열
            int[] result = new int[N];

            // 입력 받는 것을 일단 오름차순으로
            for (int i=0; i<N; ++i) pq.add(Integer.parseInt(st.nextToken()));

            for (int i=0; i<(N/2); ++i) {
                // 가장 작은 두 값을 꺼내서
                int num1 = pq.poll();
                int num2 = pq.poll();

                // 배열의 양쪽에 배치시켜줌
                result[i] = num1;
                result[N-1-i] = num2;
            }
            // 홀수라면 가운데꺼
            if (N % 2 == 1) result[N/2] = pq.poll();

            // 차이의 최대값 구하기
            for (int i=0; i<N-1; ++i) max = Math.max(max, Math.abs(result[i] - result[i+1]));
            // 마지막이랑 처음도 해주기
            max = Math.max(max, result[N-1] - result[0]);
            sb.append(max + "\n");
        }
        System.out.println(sb);
    }
}
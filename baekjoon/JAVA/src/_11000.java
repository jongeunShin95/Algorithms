import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class _11000 {
    public static class Class implements Comparable<Class> {
        int start; // 수업 시작 시간
        int end; // 수업 끝나는 시간
        Class(int start, int end) {
            this.start = start;
            this.end = end;
        }
        // 시작 시간 오름차순(같으면 끝나는 시간이 더 빠른걸로)
        @Override
        public int compareTo(Class o) {
            return this.start - o.start != 0 ? this.start - o.start : this.end - o.end;
        }
    }

    static int N; // 수업 수
    static PriorityQueue<Class> pq = new PriorityQueue<>(); // 수업을 담는 우선순위 큐
    static PriorityQueue<Integer> pq2 = new PriorityQueue<>(); // 강의실 마다 마지막 수업의 끝나는 시간을 담는 큐

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int cnt = 0;

        // N 입력
        N = Integer.parseInt(in.readLine());

        for (int tc=0; tc<N; ++tc) {
            st = new StringTokenizer(in.readLine());
            pq.add(new Class(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        while (!pq.isEmpty()) {
            Class c = pq.poll();
            // 만약 pq2에 있는 수업에 이어 할 수 있는 수업이 있다면
            if (!pq2.isEmpty() && pq2.peek() <= c.start) {
                pq2.poll(); // 해당 수업 뒤에 오는 것이 마지막이라 해당 수업은 제외시켜줌
            }
            pq2.offer(c.end); // 해당 수업이 마지막 수업이기때문에 넣어줌
        }

        System.out.println(pq2.size());
    }
}

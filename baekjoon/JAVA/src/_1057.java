import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class _1057 {
    static int N; // 참가자 수
    static int A; // 김지민
    static int B; // 임한수
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 입력 받기
        st = new StringTokenizer(in.readLine());
        N = Integer.parseInt(st.nextToken());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());

        // 라운드 수 (1라운드부터 시작)
        int cnt = 1;

        // 한명이 짝수일 때 한명은 (짝수 - 1)이면 붙음
        // 토너먼트 진행마다 짝수는 반, 홀수는 ((자기 수 / 2) + 1)이 됨
        while (true) {
            if (A % 2 == 0) {
                if (A - 1 == B) {
                    break;
                } else {
                    A = A / 2;
                    B = B % 2 == 0 ? B / 2 : (B / 2) + 1;
                    cnt++;
                }
            } else if (B % 2 == 0) {
                if (B - 1 == A) {
                    break;
                } else {
                    B = B / 2;
                    A = A % 2 == 0 ? A / 2 : (A / 2) + 1;
                    cnt++;
                }
            } else {
                A = A % 2 == 0 ? A / 2 : (A / 2) + 1;
                B = B % 2 == 0 ? B / 2 : (B / 2) + 1;
                cnt++;
            }
        }
        System.out.println(cnt);
    }
}
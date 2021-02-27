import sys

# N, K 입력 받기
N, K = map(int, sys.stdin.readline().split())

# 각 물건을 선택했을 때의 각 무게당 최대값을 계속해서 저장하는 dp 배열
dp = [[0] * (K+1) for _ in range(0, N+1)]

for i in range(1, N+1):
    w, v = map(int, sys.stdin.readline().split())
    for j in range(1, K+1):
        if w > j:
            dp[i][j] = dp[i-1][j]
        else:
            dp[i][j] = max(v + dp[i-1][j-w], dp[i-1][j])

print(dp[N][K])
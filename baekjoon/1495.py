N, S, M = map(int, input().split())

arr = []
arr = list(map(int, input().split()))

dp = [[False for _ in range(0, M+1)] for _ in range(0, N+1)]
dp[0][S] = True

for i in range(1, N+1):
    for j in range(0, M+1):
        if dp[i-1][j] is True:
            if j - arr[i-1] >= 0:
                dp[i][j-arr[i-1]] = True
            if j + arr[i-1] <= M:
                dp[i][j+arr[i-1]] = True

_max = -1
for i in range(0, M+1):
    if dp[N][i] == True:
        _max = max(_max, i)

print(_max)

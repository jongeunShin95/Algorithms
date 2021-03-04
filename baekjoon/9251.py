A = list(input())
B = list(input())

A.insert(0, '0')
B.insert(0, '0')

dp = [[0 for _ in range(0, len(A))] for _ in range(0, len(B))]


for i in range(1, len(B)):
    for j in range(1, len(A)):
        if A[j] != B[i]:
            dp[i][j] = max(dp[i-1][j], dp[i][j-1])
        else:
            dp[i][j] = dp[i-1][j-1] + 1

print(dp[len(B)-1][len(A)-1])
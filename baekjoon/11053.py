import sys
N = int(input())

# 0번째는 그냥 0으로 해줌.
# 나중에 개수 - 1로 결과값나옴
dp = [0]
# 입력
nums = list(map(int, sys.stdin.readline().split()))

# N개의 입력에 대해
for i in range(1, N+1):
    num = nums[i-1]
    flag = False

    # 해당 i번째 수보다 큰 수가 나올 때 까지 반복문을 돌림
    # 만약 없다면 지금 수가 가장 큰 수로 dp배열의 뒷부분에 추가됨
    # 즉, num이 들어올 때마다 만들 수 있는 가장 큰 수열을 dp에 저장하는 것임
    for j in range(1, i):
        if len(dp) <= j:
            break
        if dp[j] >= num:
            dp[j] = num
            flag = True
            break

    if flag is False:
        dp.append(num)

print(len(dp) - 1)
import sys
import heapq

heap = []
# 무게, 너비, 높이, 번호 순서
inputs = [[0, 0, 0, 0]]
N = int(sys.stdin.readline())
dp = [0 for _ in range(0, N+1)]

# 최대 높이 및 그 높이의 마지막 inputs 기준의 번호
maxH = 0
maxN = 0

# 최종 결과값 리스트
result = []

for i in range(1, N+1):
    # w 너비, h 높이, W 무게
    w, h, W = map(int, sys.stdin.readline().split())
    templist = [W, w, h, i]
    heapq.heappush(heap, templist)

# 무게 순으로 정렬된 것들을 순서대로 리스트에 넣어줌.
for _ in range(0, N):
    inputs.append(heapq.heappop(heap))

# 1부터 현재 블럭까지 저장된 dp에 현재 블럭을 더한 높이 값이 최대값이 되도록 함.
for i in range(1, N+1):
    for j in range(0, i):
        if inputs[i][1] > inputs[j][1]:
            dp[i] = max(dp[i], dp[j] + inputs[i][2])
            if maxH < dp[i]:
                maxH = dp[i]
                maxN = i

# 현재 높이값과 그 때의 최대값이 같으면 해당 블럭은 포함되어있는것.
for i in range(maxN, 0, -1):
    if maxH == dp[i]:
        result.append(inputs[i][3])
        maxH -= inputs[i][2]

print(len(result))

while len(result) != 0:
    print(result.pop())
# -*- coding: utf-8 -*-
import sys
import heapq

N, M = map(int, sys.stdin.readline().split())

hq = []
arr = [[] for _ in range(N + 1)] # 0 제외, 해당 index를 푼 후 풀 수 있는 문제들을 리스트로
child = [0] * (N + 1) # 먼저 풀어야하는게 있는 개수
result = [] # 결과값 담는 리스트

for _ in range(M):
    a, b = map(int, sys.stdin.readline().split())
    arr[a].append(b)
    child[b] += 1

for i in range(1, N+1):
    if child[i] == 0: # 먼저 풀어야되는게 없으면 우선순위 큐에 넣어줌
        heapq.heappush(hq, i)

while hq:
    num = heapq.heappop(hq)
    result.append(num)
    for i in arr[num]:
        child[i] -= 1
        if child[i] == 0:
            heapq.heappush(hq, i)

for i in result:
    print(i, end=' ')
print()
import sys
import heapq # 최소 힙 모듈

# 최소 힙 배열
heap = []

# N 입력
N = int(input())

# N번만큼 반복
for _ in range(N):
    # num = int(input()) <-- 시간초과남
    num = int(sys.stdin.readline())
    # 만약 입력값이 0이라면
    if num == 0:
        if not heap: #하지만 heap 배열이 비어있다면
            print(0) # 0을 출력
        else: # 비어있지않다면
            print(heapq.heappop(heap)) # 힙에서 하나 꺼내 출력
    else:
        heapq.heappush(heap, num) # 0이 아닌 자연수라면 최소힙에 넣음
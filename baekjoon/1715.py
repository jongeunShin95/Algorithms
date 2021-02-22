# -*- coding: utf-8 -*-
import sys
import heapq

N = int(input())
heap = []
_min = 0

for _ in range(N):
    num = int(sys.stdin.readline())
    heapq.heappush(heap, num)

_min = 0

# 카드덱이 하나면 안섞어도 됨.
while len(heap) != 1:
    # 가장 작은 두 값 최소힙에서 뺴주고
    num1 = heapq.heappop(heap)
    num2 = heapq.heappop(heap)
    # 그 값을 최소값에 더해주고
    _min += num1 + num2
    # 두 덱을 섞은 만큼은 다시 최소힙에 넣어줌
    heapq.heappush(heap, num1 + num2)

print(_min)
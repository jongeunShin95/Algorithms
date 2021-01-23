N, M = map(int, input().split())
d = {}
max_c = 1
min_c = 1000000000

for _ in range(M): # 다리와 무게를 입력받는 부분
    a, b, c = map(int, input().split())
    if not a in d:
        d[a] = {}

    if b in d[a]: # 만약 해당 섬사이에 다리가 존재한다면 무조건 큰 값을 저장
        if d[a][b] > c:
            break
    d[a][b] = c
    if not b in d:
        d[b] = {}
    d[b][a] = c
    max_c = max(max_c, c)
    min_c = min(min_c, c)
result = min_c

start, end = map(int, input().split())

while (max_c >= min_c):
    mid_c = (max_c + min_c) // 2 # 중간값
    queue = []
    visited = [False] * N

    queue.append(start)

    # queue를 이용하여 BFS 구현
    while queue:
        cur = queue.pop(0)
        for key in d[cur]:
            if mid_c <= d[cur][key] and not visited[key-1]:
                visited[key-1] = True
                queue.append(key)
    
    if visited[end-1]: # 중간값을 만족시키는 다리들이 존재하여 원하는 섬까지 간 경우
        result = mid_c # 일단 해당 중간값을 최대로 갱신
        min_c = mid_c + 1 # 최소값을 중간값 + 1로 갱신
    else: # 만약 출발 노드에서 끝 노드까지 못간 경우(즉, 중간값을 만족못시켜 끝까지 못간 경우)
        max_c = mid_c - 1 # 최대값을 중간값 - 1로 갱신

print(result)
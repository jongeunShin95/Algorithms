N, M = map(int, input().split())
d = {}
max_c = 1
min_c = 1000000000

for _ in range(M):
    a, b, c = map(int, input().split())
    if not a in d:
        d[a] = {}

    if b in d[a]:
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
    mid_c = (max_c + min_c) // 2
    queue = []
    visited = [False] * N

    queue.append(start)

    while queue:
        cur = queue.pop(0)
        for key in d[cur]:
            if mid_c <= d[cur][key] and not visited[key-1]:
                visited[key-1] = True
                queue.append(key)
    
    if visited[end-1]:
        result = mid_c
        min_c = mid_c + 1
    else:
        max_c = mid_c - 1

print(result)
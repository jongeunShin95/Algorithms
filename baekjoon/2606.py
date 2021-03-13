N = int(input())
K = int(input())

q = []
nodes = [[] for _ in range(0, N+1)]
visited = [False for _ in range(0, N+1)]
cnt = -1 # 1은 수에 포함안되서 -1부터 시작하기로 함

for _ in range(0, K):
    a, b = map(int, input().split())
    nodes[a].append(b)
    nodes[b].append(a)

q.append(1)

# bfs
while len(q) != 0:
    node = q.pop(0)
    if visited[node] is True:
        continue
    visited[node] = True
    cnt += 1
    for i in range(0, len(nodes[node])):
        n = nodes[node][i]
        if visited[n] is True:
            continue
        q.append(n)

print(cnt)
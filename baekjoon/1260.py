# 입력 및 초기화
N, M, V = map(int, input().split())
e = [[] for _ in range(0, N+1)]
dfs_visited = [False for _ in range(0, N + 1)]
bfs_visited = [False for _ in range(0, N + 1)]
dfs_result = []
bfs_result = []
stack = []
queue = []

# 간선들 입력 및 넣기
for _ in range(0, M):
    a, b = map(int, input().split())
    e[a].append(b)
    e[b].append(a)

# 중복 제거 및 정렬
for i in range(1, N + 1):
    e[i] = list(set(e[i]))
    e[i] = sorted(e[i])

# dfs
stack.append(V)
while len(stack) > 0:
    # 맨 위의 값을 먼저 꺼냄
    node = stack.pop()
    # 이미 방문된 것이라면 다음으로
    if dfs_visited[node]:
        continue
    # 방문 표시
    dfs_visited[node] = True
    # 해당 노드를 결과 리스트에 넣기
    dfs_result.append(node)
    # 해당 정점의 간선들을 역순으로 스택에 넣어줌
    for i in range(len(e[node]), 0, -1):
        stack.append(e[node][i-1])

# bfs
queue.append(V)
while len(queue) > 0:
    # 먼저 들어온 값을 꺼냄
    node = queue.pop(0)
    # 이미 방문된 것이라면 다음으로
    if bfs_visited[node]:
        continue
    # 방문 표시
    bfs_visited[node] = True
    # 해당 노드를 결과 리스트에 넣기
    bfs_result.append(node)
    # 해당 정점의 간선들을 순서대로 큐에 넣어줌
    for i in range(0, len(e[node])):
        queue.append(e[node][i])

def resultPrint(result):
    for i in range(0, len(result)):
        print(result[i], end=' ')
    print()
resultPrint(dfs_result)
resultPrint(bfs_result)
N, K = map(int, input().split())
_min = 100001
queue = []
visited = [False for _ in range(0, 100001)]

queue.append([N, 0])

while len(queue) != 0:
    node = queue.pop(0)
    if visited[node[0]] is True:
        continue

    visited[node[0]] = True

    if node[0] == K:
        _min = min(_min, node[1])
        continue

    # 만약 현재 시간이 최소 값보다 이미 크거나 같다면 다음으로
    if node[1] >= _min:
        continue
    if node[0] - 1 >= 0:
        queue.append([node[0] - 1, node[1] + 1])
    if node[0] + 1 <= 100000:
        queue.append([node[0] + 1, node[1] + 1])
    if node[0] != 0 and node[0] * 2 <= 100000:
        queue.append([node[0] * 2, node[1] + 1])

print(_min)
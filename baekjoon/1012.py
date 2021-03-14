T = int(input()) # 테스트 케이스 수
_dir = [[-1, 0], [1, 0], [0, -1], [0, 1]] # 상, 하, 좌, 우
result = []

for _ in range(0, T):
    M, N, K = map(int, input().split()) # 가로, 세로, 배추 개수
    F = [[0 for _ in range(0, M)] for _ in range(0, N)] # 배추빝
    visited = [[False for _ in range(0, M)] for _ in range(0, N)] # 방문 확인 용
    cnt = 0 # 배추 개수
    q = [] # bfs 용

    # 배추 좌표 값 입력 받기
    for _ in range(0, K):
        x, y = map(int, input().split())
        F[y][x] = 1
    
    # 모든 좌표값에 대한 bfs 적용
    for y in range(0, N):
        for x in range(0, M):
            if visited[y][x] is True or F[y][x] == 0:
                continue

            q.append([y, x])
            cnt += 1

            # bfs
            while len(q) != 0:
                _y, _x = q.pop(0)

                if visited[_y][_x] is True or F[_y][_x] == 0:
                    continue
                visited[_y][_x] = True

                for i in range(0, 4):
                    dy = _y + _dir[i][0]
                    dx = _x + _dir[i][1]

                    if dy < 0 or dx < 0 or dy >= N or dx >= M: # 범위 벗어나면
                        continue

                    if visited[dy][dx] is True or F[dy][dx] == 0:
                        continue

                    q.append([dy, dx])
    
    result.append(cnt)

# 결과값 출력
for i in range(0, len(result)):
    print(result[i])
# 입력 및 초기화
N, M = map(int, input().split())
computers = [[] for _ in range(0, N+1)]
result = []
_max = 0

# 관계 입력
for _ in range(0, M):
    A, B = map(int, input().split())
    computers[B].append(A)

# dfs
# 각 컴퓨터마다 dfs
for i in range(1, N+1):
    visited = [False for _ in range(0, N+1)]
    stack = []
    cnt = 0
    
    stack.append(i)
    while len(stack) != 0:
        c = stack.pop()
        if visited[c] is True:
            continue

        cnt += 1
        visited[c] = True

        for j in computers[c]:
            if visited[j] is True:
                continue
            stack.append(j)

    # 현재 저장된 최대값보다 크다면 결과값 배열을 초기화해주고 새로 넣어줌
    if cnt > _max:
        result = []
        result.append(i)
        _max = cnt
    elif cnt == _max: # 만약 현재 저장된 최대값과 동일한 값이라면 결과값에 넣어줌
        result.append(i)

for i in result:
    print(i, end=' ')
print()
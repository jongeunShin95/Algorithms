N, C = map(int, input().split())
x = []

for i in range(N):
    x.append(int(input()))

x.sort()

max = x[-1] - x[0]
min = 1
result = 0

while (min <= max):
    mid = (max + min) // 2 # 중간값 결정
    cur = x[0]
    cnt = 1 # 첫 번쨰는 무조건 설치이기때문
    for i in range(1, N):
        if (mid + cur <= x[i]):
            cnt += 1
            cur = x[i]
    if cnt >= C: # 설치 가능할 경우 최소값을 증가
        min = mid + 1
        result = mid
    else: # 설치 불가능할 경우 최대값을 감소
        max = mid - 1
            
print(result)
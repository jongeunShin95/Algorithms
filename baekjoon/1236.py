r, c = map(int, input().split())
t = []
r_c = 0
c_c = c

for _  in range(r):
    t.append(input())

for i in range(r):
    if t[i].find('X') == -1:
        r_c += 1

for i in range(c):
    for j in range(r):
        if t[j][i] == 'X':
            c_c -= 1
            break

print(max(r_c, c_c))
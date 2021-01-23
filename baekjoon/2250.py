# 트리 구성하는 하나의 노드
class Node:
    def __init__(self, data, left_node, right_node):
        self.data = data
        self.left_node = left_node
        self.right_node = right_node

# 전역변수용
N = int(input())
# 해당 레벨에 존재하는 위치 값들 다 저장용 [0레벨[] 1레벨[] 2레벨[] 3레벨[]]
# 0레벨은 사용안해서 N+1만큼 레벨 생성
result = [[] for _ in range(N+1)]
location = 1 # 위치용

# 중위 순회용
# 중위 순회하면 트리의 왼쪽부터 오른쪽 순서대로 출력하기 떄문에
# 각 위치값을 알 수 있다.
def in_order(node, level):
    global result
    global location
    
    if node.left_node != -1:
        in_order(tree[node.left_node], level + 1)
    result[level].append(location) # 현재 레벨에 현재 위치값 저장
    location += 1
    if node.right_node != -1:
        in_order(tree[node.right_node], level + 1)


level = 1 # 해당 노드가 몇 번째 레벨인지
root = [0 for i in range(N)] # root 노드 구하기용
max_level = 0 # 최대 너비를 가지는 레벨
max_value = 0 # 최대 너비
tree = {}

for _ in range(N):
    data, left_node, right_node = map(int, input().split())
    tree[data] = Node(data, left_node, right_node)

    # 자식노드로 한번도 언급이 안되면 root노드(root[i] = 0 이면 i+1이 root 노드)
    if left_node != -1:
        root[left_node-1] += 1
    if right_node != -1:
        root[right_node-1] += 1

root_node = -1

for i in range(N):
    if root[i] == 0:
        root_node = i + 1 # 0이면 root 노드
        break

in_order(tree[root_node], level)

# 0번째 레벨은 제외
for i, v in enumerate(result[1:], 1):
    # 해당 레벨에 노드가 존재하지 않으면 종료(최대 레벨이기떄문)
    if len(v) == 0:
        break

    # 해당 레벨에 노드가 하나이면 너비는 1
    if len(v) == 1:
        # 아직 최대 너비가 없을 경우 해당 최대 너비가 그 레벨값을 저장해줌
        if max_value < 1:
            max_value = 1
            max_level = i
            continue
    
    # 해당 레벨에 존재하는 가장 큰 위치값과 가장 작은 위치값을 뺴준거에 +1 해주면 너비값
    # 최대값들을 계속 갱신해줌
    if max_value < v[-1] - v[0] + 1:
        max_value = v[-1] - v[0] + 1
        max_level = i

print(max_level, max_value)
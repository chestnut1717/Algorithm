import sys
input = sys.stdin.readline


# dfs로 구현
def dfs(depth, start):
    if(depth == 2):
        return;
    for friend in adj_list[start]:
        isVisited[friend] = True
        dfs(depth+1, friend)

# 동기의 수
N = int(input())
# 리스트의 길이
M = int(input())

# 인접 리스트(동기의 수만큼
adj_list = [[] for _ in range(N+1)]
# 방문배열
isVisited = [False for _ in range(N+1)]

# 이제 결혼식 동기들을 불러온다!
for _ in range(M):
    a, b = map(int, input().split())
    adj_list[a].append(b)
    adj_list[b].append(a)

dfs(0, 1)

# 이제 방문한 것들 개수 세기
result = 0
for i in range(2, N+1):
    if(isVisited[i] == True):
        result += 1

print(result)

import sys
input = sys.stdin.readline

def dfs(depth):
    if depth == M:
        print(' '.join(map(str, ans_list)))
        return
    
    for n in range(1, N+1):
        if not isVisited[n]:
            isVisited[n] = True
            ans_list.append(n)
            dfs(depth + 1)
            isVisited[n] = False
            ans_list.pop()

N, M = map(int, input().split())

ans_list = []
isVisited = [False] * (N + 1)

dfs(0)

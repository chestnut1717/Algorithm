import sys
input = sys.stdin.readline

def dfs(limit, depth):
    if depth == limit:
        for num in ans_list:
            print(f"{num}", end=' ')
        print('\n', end='')
        return
    
    for n in range(1, N+1):
        if isVisited[n] == True:
            continue
        isVisited[n] = True
        ans_list[depth] = n
        dfs(limit, depth+1)
        isVisited[n] = False

N, M = map(int, input().split())

# 1~ N
numbers = [i for i in range(1, N+1)]
ans_list = [0 for i in range(M)]

# N개의 False
isVisited = [False for _ in range(N+1)]

dfs(M, 0)
import sys
input = sys.stdin.readline

def combination(depth, cnt):
    if depth == M:
        print(" ".join(map(str, numbers)))
        return
    for num in range(cnt, N+1):
        numbers.append(num)
        combination(depth+1, num+1)
        numbers.pop()

# N과 M 입력
N, M = map(int, input().split())

numbers = []

combination(0, 1)
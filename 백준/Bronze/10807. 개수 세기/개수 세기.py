N = int(input())
a = list(map(int, input().split()))
M = int(input())

result = 0
for i in a:
    if i == M:
        result+=1
print(result)
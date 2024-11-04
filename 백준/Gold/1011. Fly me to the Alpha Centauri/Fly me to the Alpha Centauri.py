a = int(input())
for i in range(a):
    x, y = map(int, input().split())
    dist = y - x
    pivot = int(dist ** 0.5)
    if dist == pivot ** 2:
        print(2 * pivot - 1)
    elif pivot**2 < dist <= pivot**2 + pivot:
        print(2 * pivot)
    else:
        print(2 * pivot + 1)
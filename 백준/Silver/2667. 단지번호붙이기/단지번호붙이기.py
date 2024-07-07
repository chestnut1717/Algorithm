def dfs(house, x, y):
    global house_set

    if (x < 0 or x > x_limit) or (y<0 or y > y_limit):
        return False

    if house[y][x] == 0:
        return False
        
    if house[y][x] == 1:
        house[y][x] = 0
        house_set.append([x, y])
        dfs(house, x-1, y)
        dfs(house, x, y-1)
        dfs(house, x+1, y)
        dfs(house, x, y+1)
        return True

    return False

house = [list(map(int, [i for i in input()])) for _ in range(int(input()))]
x_limit = len(house)-1
y_limit = len(house)-1

count = 0
house_count = []

for y in range(y_limit+1):
    for x in range(x_limit+1):
        house_set = list()
        condition = dfs(house, x, y)
        if condition:
            count += 1
            house_count.append(len(house_set))

print(count)
if count > 0:
    for i in sorted(house_count):
        print(i)
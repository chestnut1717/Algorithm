import sys
input = sys.stdin.readline

N, M = map(int, input().split())

nums = [0] + list(map(int, input().split()))
for i in range(N+1):
    nums[i] = nums[i] + nums[i-1]

interval = [tuple(map(int, input().split())) for _ in range(M)]

for i in interval:
    print(nums[i[1]] - nums[i[0] - 1])
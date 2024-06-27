import sys
from queue import PriorityQueue
input = sys.stdin.readline
output = sys.stdout.write

pq = PriorityQueue()
result = []
# 연산의 개수
N = int(input())
for _ in range(N):
    num = int(input())
    # 만약 x가 0이 아니라면 => 추가하는 연산
    temp = 0
    if num != 0:
        pq.put((abs(num), num))
    # 만약 0이라면
    else:
        if(not pq.empty()):
            temp = pq.get()[1]
    
        result.append(temp)
    
            
output('\n'.join(map(str, result)))
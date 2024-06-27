import sys
input = sys.stdin.readline
output = sys.stdout.write

N = int(input())
number_list = list(map(int, input().split()))
stk = []
numStk = []

for i in range(N-1, -1, -1):
    while(len(numStk) != 0 and numStk[-1] <= number_list[i]):
        numStk.pop()
    
    if(len(numStk) == 0):
        stk.append(-1)
    else:
        stk.append(numStk[-1])
    numStk.append(number_list[i])

stk.reverse()
output(" ".join(map(str, stk)))
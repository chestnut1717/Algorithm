N, M = map(int, input().split())

lamps_dict = dict()

for i in range(N):
  row = input()

  if lamps_dict.get(row):
    lamps_dict[row] += 1
  else:
    lamps_dict[row] = 1

K = int(input())


maximum = 0

# M이 K보다 클때
if M > K:

  # 짝수일 때
  if K % 2 == 0:
    for i in range(0, K+1, 2):
      for key in lamps_dict.keys():
        zero_count = key.count('0')

        if zero_count == (i) and maximum < lamps_dict[key]:
          maximum = lamps_dict[key]
  # 홀수일 때
  else: 
    for i in range(1, K+1, 2):
      for key in lamps_dict.keys():
        zero_count = key.count('0')
        # print(zero_count, lamps_dict[key])
        if zero_count == (i) and maximum < lamps_dict[key]:
          maximum = lamps_dict[key]

else:
  # 짝수일 때
  if K % 2 == 0:
    for i in range(0, M+1, 2):
      for key in lamps_dict.keys():
        zero_count = key.count('0')

        if zero_count == (i) and maximum < lamps_dict[key]:
          maximum = lamps_dict[key]
  # 홀수일 때
  else: 
    for i in range(1, M+1, 2):
      for key in lamps_dict.keys():
        zero_count = key.count('0')
        # print(zero_count, lamps_dict[key])
        if zero_count == (i) and maximum < lamps_dict[key]:
          maximum = lamps_dict[key]

print(maximum)
a, b, c = map(int, input().split())

# 같은 눈 3개
if a == b == c :
    print(10000+1000*a)
elif a == b:
    print(1000+a*100)
elif b == c:
    print(1000+b*100)
elif c == a:
    print(1000+c*100)
else:
    maximum = max(a, b, c)
    print(maximum * 100)
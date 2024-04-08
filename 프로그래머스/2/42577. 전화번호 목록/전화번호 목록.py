def solution(phone_book):
    answer = True
    phone_book.sort()
    
    i = 0
    while(i < len(phone_book)-1):
        prefix = phone_book[i]
        next = phone_book[i+1]
        if prefix == next[:len(prefix)]:
            answer = False
            break
        i += 1
    return answer


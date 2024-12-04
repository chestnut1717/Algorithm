def solution(kk, tangerine):
    answer = 0
    
    mapDict = {}
    for num in tangerine:
        if num in mapDict.keys():
            mapDict[num] += 1
        else:
            mapDict[num] = 1
    
    newDict = {k: v for k, v in sorted(mapDict.items(), key=lambda item: item[1], reverse=True)}
    
    for k, v in newDict.items():
        kk -= v
        answer+= 1
        if kk <= 0:
            break
        
    
    return answer
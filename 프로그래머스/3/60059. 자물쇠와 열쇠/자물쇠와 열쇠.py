def solution(key, lock):
    answer = False
    
    n = len(key)
    m = len(lock)
    tripled_lock = [[0] * (3*m) for _ in range(3*m)]
        
    # tripled lock의 가운데에 채우기
    for idx in range(m):
        for jdx in range(m):
            tripled_lock[idx + m][jdx + m] = lock[idx][jdx]
    
    # 4방향 돌리기
    for _ in range(4):
        key = rotation(key)

        # 범위 check
        ## 범위는 x : m ~ 2m-1 / y : m ~ 2m-1
        for i in range(2*m):
            for j in range(2*m):
                start_x = i
                start_y = j
        
                # 자물쇠에 키 찍기
                for idx in range(n):
                    for jdx in range(n):

                        tripled_lock[start_x + idx][start_y + jdx] += key[idx][jdx]

                # 키 확인
                if check(tripled_lock):
                    return True
                
                # 키 원상복구
                for idx in range(n):
                    for jdx in range(n):
                        tripled_lock[start_x + idx][start_y + jdx] -= key[idx][jdx]

    return False

def check(matrix):
    m = len(matrix) // 3
    for i in range(m):
        for j in range(m):
            if matrix[m+i][m+j] == 0 or matrix[m+i][m+j] == 2:
                return False
    return True


def rotation(matrix):
    length = len(matrix)
    new_matrix = [[0] * len(matrix) for _ in range(length)]
    
    # rotation by 90 degree
    for i in range(length):
        for j in range(length):
            new_matrix[j][length-i-1] = matrix[i][j]
    
    return new_matrix
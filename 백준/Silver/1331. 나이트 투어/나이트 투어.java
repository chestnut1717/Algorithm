// 나이트투어 핵심
// 1. 36개를 진행하면서
// 1.1 다음 말의 위치가 가능한 경우여야 한다.
// 1.2 한번이라도 방문하면 중간 종료시킨다.
// 1.3 36개 모두 돈 다음 => 마지막 값이 첫번째 값이

import java.io.*;
import java.util.*;

public class Main
{
    
    static final int[] dy = {-1, -2, -2, -1, 1, 2, 2, 1};
    static final int[] dx = {2, 1, -1, -2, -2, -1, 1, 2};
    static final int N = 36;
    static int[] rows = new int[N];
    static int[] cols = new int[N];
    static boolean[][] board = new boolean[6][6];
    
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        // 우선 위치 입력
        for(int i = 0; i < N; i++) {
            String input = br.readLine();
            cols[i] = input.charAt(0) - 'A';
            rows[i] = 5 - (input.charAt(1) - '1');
        }
        
        boolean result = check();
        
        if(result) {
            System.out.println("Valid");
        } else {
            System.out.println("Invalid");
        }

        
    }
    static boolean check() {
        int y = rows[0];
        int x = cols[0];
        board[y][x] = true;
        
        for(int i = 1; i < N; i++) {
            boolean flag = false;
            for(int j = 0; j < 8; j++) {
                int ny = y + dy[j];
                int nx = x + dx[j];

                if (isValid(ny, nx) && rows[i] == ny && cols[i] == nx && !board[ny][nx]) {
                    board[ny][nx] = true;
                    flag = true;
                    y = ny;
                    x = nx;
                    break;
                }
            }
            
            // 8번 모두 돌았는데도 불구하고 없다면 이건 거짓이다
            if (flag==false) {
                return false;
            }
        }
        // 마지막까지 끝냈다면, 마지막것에서 첫번째로 갈 수 있는지?
        for(int i = 0; i < 8; i++) {
            int ny = rows[35] + dy[i];
            int nx = cols[35] + dx[i];
            
            if(ny == rows[0] && nx == cols[0]) return true;
        }
        
        
        return false;
    }
    static boolean isValid(int y, int x) {
        return 0 <= y && y < 6 && 0 <= x && x < 6;
    }
}

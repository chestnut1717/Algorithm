import java.io.*;
import java.util.*;

public class Main
{
    static int[] dy = {0, 1, 0, -1};
    static int[] dx = {1, 0, -1, 0};
    static int R, C, N;
    static int[][] arr;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		// 이제 격자판 입력
		arr = new int[R][C];
		
		
		for(int i = 0; i < R; i++) {
		    char[] charArr = br.readLine().toCharArray();
		    for(int j = 0; j < C; j++) {
		        if (charArr[j] == '.') {
		            arr[i][j] = -1; // 폭탄이 아닌 것은 -1
		        } else {
		            arr[i][j] = 1; // 처음 1초는 스킵하므로 그냥 1 넣자.
		        }
		    }
		    
		}
		
        // 이제 진행하자.
        // 반복문을 돌면서
        // 1. 각각의 칸에 대해
        // 1.1 만약 폭탄이 없으면 폭탄을 설치하고
        // 1.2 만약 폭탄이 있으면 숫자를 더한다.
        // 1.2.1 더한 이후 해당 폭탄의 숫자가 3이라면, 연쇄폭발한다.
        int cnt = 1;
        while(cnt < N) {
            // 폭탄 설치
            for(int i = 0; i < R; i++) {
                for(int j = 0; j < C; j++) {
                    arr[i][j]++;
                }
            }
            
            // 폭탄 터트리기!
            for(int i = 0; i < R; i++) {
                for(int j = 0; j < C; j++) {
                    if(arr[i][j] == 3) {
                        bomb(i, j);
                    }
                }
            }
            cnt++;
        }
        
        
        for(int i = 0; i < R; i++) {
            for(int j = 0; j < C; j++) {
                if(arr[i][j] == -1) sb.append('.');
                else sb.append('O');
                
            }
            sb.append('\n');
        }
        
        bw.write(sb.toString());
        bw.close();
		
	}
	
	static void bomb(int y, int x) {
	    arr[y][x] = -1;
	    for(int i = 0; i < 4; i++) {
	        int ny = y + dy[i];
	        int nx = x + dx[i];
	        // 우선 3초가 지난 bomb를 함께 터트리면 안된다(나중에 터트려야 함 - 어차피 터짐)
	        if(0 <= ny && ny < R && 0 <= nx && nx < C && arr[ny][nx] != 3) arr[ny][nx] = -1;
	    }
	}
	
}
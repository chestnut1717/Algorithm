import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        
        int N = Integer.parseInt(st.nextToken()); // 행과
        int M = Integer.parseInt(st.nextToken()); // 열
        int R = Integer.parseInt(st.nextToken()); // 돌릴 횟수
        
        int[][] map = new int[N][M];
        // 배열 입력
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        // R번 수행
        for(int i = 0; i < R; i++) {
            // 
            for(int j = 0; j < Math.min(N, M)/2; j++) {
                
                int temp = map[j][j];
                
                // 왼쪽
                for(int k = j; k<M-j-1; k++) {
                    map[j][k] = map[j][k+1];
                }
                // 위쪽
                for(int k = j; k<N-1-j; k++) {
                    map[k][M-j-1] = map[k+1][M-j-1];
                }
                // 오른쪽
                for (int k = M-j-1; k > j; k--) {
                    map[N-1-j][k] = map[N-1-j][k-1];
                    
                }
                // 아래쪽
                for(int k = N-j-1; k > j; k--) {
                    map[k][j] = map[k-1][j];
                }
                map[j+1][j] = temp;
            }
        }
        for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				sb.append(map[i][j]).append(" ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
    }
}
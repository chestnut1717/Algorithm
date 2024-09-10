import java.util.*;
import java.io.*;

public class Main
{
    static int N, M;
    static int K;
    static int[][] map;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		// 맵 초기화
		map = new int[N+1][M+1];
		
		for(int i = 1; i <= N; i++) {
		    st = new StringTokenizer(br.readLine());
		    for(int j = 1; j <= M; j++) {
		        map[i][j] = Integer.parseInt(st.nextToken()) + map[i][j-1];
		    }
		}
		
		// 그리고 다음과 같은 누적합 공식을 쓴다.
		K = Integer.parseInt(br.readLine());
		
		for(int i = 0; i < K; i++) {
		    st = new StringTokenizer(br.readLine());
		    int x1 = Integer.parseInt(st.nextToken());
		    int y1 = Integer.parseInt(st.nextToken());
		    int x2 = Integer.parseInt(st.nextToken());
		    int y2 = Integer.parseInt(st.nextToken());
		    
		    int sum = 0; // sum은 100 * 1024 * 1024 (1억 안팎이므로 int처리 가능)
		    for(int n = x1; n <= x2; n++) {
		        sum += (map[n][y2] - map[n][y1-1]);
		    }
		    sb.append(sum).append('\n');
		    
		}
		
		
		bw.write(sb.toString());
		bw.close();
	}
}
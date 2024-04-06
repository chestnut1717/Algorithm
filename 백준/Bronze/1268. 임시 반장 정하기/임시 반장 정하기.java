import java.io.*;
import java.util.*;

public class Main
{
    static final int G = 5;
    static int N; // 학생 수
    static int[][] map;
    static boolean[][] sameBan;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		
		map = new int[N][G];
		for(int i = 0; i < N; i++) {
		    st = new StringTokenizer(br.readLine());
		    for(int j = 0; j < G; j++) {
		        map[i][j] = Integer.parseInt(st.nextToken());
		      //  System.out.printf("%d ", map[i][j]);
		    }
		  //  System.out.println();
		}
		
		// 이제 N*N 정답행렬에서 기록함
		sameBan = new boolean[N][N];
		
		
		for(int i = 0; i < N; i++) {
		    // 한 학년마다 같은 반이 되었는지 탐색
		    for(int g = 0; g < G; g++) {
                int ban = map[i][g]; //i번째 학생의 g학년일때 반
                for(int j = 0; j < N; j++) {
                    if(i == j) continue; // 같은 것은 무시

                    // i와 j 둘다 true해줘야 한다.
                    if(ban == map[j][g]) {
                        sameBan[i][j] = true;
                        sameBan[j][i] = true;
                    }
                }
		    }

		}
		
		int max = Integer.MIN_VALUE;
		int number = -1;
		for(int i = 0; i < N; i++) {
		    int tmpCnt = 0;
		    for(int j = 0; j < N; j++) {
		        if(sameBan[i][j]) tmpCnt++;
		    }
		    if(tmpCnt > max) {
		        max = tmpCnt;
		        number = i;
		    } 
		}
		System.out.println(number+1);

	}
}

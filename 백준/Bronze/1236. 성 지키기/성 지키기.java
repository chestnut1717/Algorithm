import java.util.*;
import java.io.*;

// 시간복잡도 : O(NM);
// 세부사항 : 2500(입력) + 2500 * 2(탐색) = 7500
public class Main
{
    static int N; // 열
    static int M; // 행
    static char[][] map;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		map = new char[N][M];
		for(int i = 0; i < N; i++) {
		    String row = br.readLine();
		    for(int j = 0; j < M; j++) {
		        map[i][j] = row.charAt(j);
		    }
		}
		
		// 답 :  Max(빈행개수, 빈열개수)
		// 빈 행 탐색
		int emptyRow = 0;
		for(int i = 0; i < N; i++) {
		    boolean isExist = false; // 경비원 유무 체크
		    for(int j = 0; j < M; j++) {
		        if(map[i][j] == 'X') {
		            isExist = true;
		            break;
		        }
		    }
		    // 경비원이 없다면 더함
		    if(!isExist) {
		        emptyRow++;
		    }
		}
		
		int emptyCol = 0;
		for(int j = 0; j < M; j++) {
		    boolean isExist = false; // 경비원 유무 체크
		    for(int i = 0; i < N; i++) {
		        if(map[i][j] == 'X') {
		            isExist = true;
		            break;
		        }
		    }
		    // 경비원이 없다면 더함
		    if(!isExist) {
		        emptyCol++;
		    }
		}
		
		System.out.println(Math.max(emptyRow, emptyCol));
	}
}

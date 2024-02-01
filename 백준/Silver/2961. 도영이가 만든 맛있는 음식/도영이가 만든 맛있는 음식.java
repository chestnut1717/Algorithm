/**
 * @since jdk1.8
 * @see <a href="https://www.acmicpc.net/problem/2961">
 * - 문제 : 백준 1961-도영이가 만든 맛있는 음식
 * - 소요시간 : 40분 
 * - 난이도 : 중
 * - 메모리 : 11500 KB / 시간 : 76 ms
 * - 아이디어 : 부분수열을 활용해서 풀 수 있는 문제
 *   - 1. 부분수열 중 반드시 아무것도 고르지 않는 경우를 제외해야 한다.
 *   - 2. 아무것도 고르지 못하는 경우를 활용하기위해 size 매개변수 활용
 */

 import java.util.*;
import java.io.*;

public class Main {
    static int N; // 음식 들어오는 경우의 수
    static int[] sour; // 신 맛을 저장하는 배열
    static int[] bitter; // 짠 맛을 저장하는 배열
    static int taste = Integer.MAX_VALUE; // 최솟값을 구해야 하므로 Integer의 최댓값으로 초기화
    
	public static void main(String[] args) throws Exception{
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;
	    N = Integer.parseInt(br.readLine()); // 재료의 개수 입력;
	    
        // 배열 초기화
	    sour = new int[N];
	    bitter = new int[N];
	    
	    // 음식의 신맛 ,쓴맛 입력
	    for(int i = 0; i < N; i++) {
	        st = new StringTokenizer(br.readLine());
	        sour[i] = Integer.parseInt(st.nextToken());
	        bitter[i] = Integer.parseInt(st.nextToken());
	    }
	    search(0, 1, 0, 0);

        // 정답 출력
	    System.out.println(taste);
	    
	    
	    
	}
	
	// 아무것도 고르지 않는 경우를 제외하는 모든 부분수열 계산하는 코드
	static void search(int cnt, int tmp_s, int tmp_b, int size) {
	    // 종료조건
	    if (cnt == N) {
	        if (size > 0) { // SIZE가 0 이상인 경우에만 taste를 갱신한다.
	           int tmp_taste = Math.abs(tmp_s - tmp_b);
        	   taste = Math.min(taste, tmp_taste); 
	        }
	        return;
	    }
        
        // 다른 음식을 조합하는 경우
	    search(cnt+1, tmp_s * sour[cnt], tmp_b + bitter[cnt], size+1);
        // 조합하지 않는 경우
	    search(cnt+1, tmp_s, tmp_b, size);

	    
	}
}

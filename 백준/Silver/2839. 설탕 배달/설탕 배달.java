import java.util.*;
import java.io.*;
/**
 * @since jdk1.8
 * - 문제 : BJ 2839 설탕 배달
 * - 소요시간 : 10분
 * - 난이도 : 하
 * - 아이디어 : 그리디 - 5의 배수가 나올 때까지 3으로 빼주고 => 5의 배수가 나오면 5로 빼주면 최적의 해를 구할 수 있음
 */
public class Main {
    static int N;
    static int cnt;
	public static void main(String[] args) throws Exception {
	    // 조건 : 5의 배수가 될 때까지 3으로 빼주고 => 5로 나눔!
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    N = Integer.parseInt(br.readLine());
	    
	    while(N > 0) {
	        if(N % 5 == 0) { // N이 5의 배수일 때
	            N -= 5;
	        } else { // N이 5의 배수가 아니면 5의 배수로 만들어 줄 때까지 뺌
	            N -= 3;
	        }
	        cnt++; //연산을 할 때마다(설탕을 배달할 때마다) 답 더해줌
	    }
		
		if (N != 0) {
		    System.out.println(-1); // 뺀 값이 0으로 딱 떨어지지 않을 때(배송 불가한 경우)
		} else {
		    System.out.println(cnt);
		}
	}
}

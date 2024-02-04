import java.util.*;
import java.io.*;

public class Main {
    static int N; // O의 길이
    static int S; // 문자열의 길이
    static char[] charArr; // I, O담는 것
    static int result;// 정답
    
	public static void main(String[] args) throws Exception{
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    N = Integer.parseInt(br.readLine());
	    S = Integer.parseInt(br.readLine());

	    char[] charArr = br.readLine().toCharArray(); // 문자열 입력을 문자로
	    
	    // 총 반복 횟수, start를 기점으로
	    int idx = 0; // start 위치
	    int length = 0; // 현재까지 추적한 IOIOI에서 O의 개수
	    boolean flag = false; // 한번 IOIOI형태가 나왔을 때, 지속적으로 만들 수 있는지 여부
	    while(idx < S-2) {
	        if(charArr[idx] == 'I' && charArr[idx+1] == 'O' && charArr[idx+2] == 'I') {
	            if (flag) {
	                result++;
	                idx+=2;
	            } else {
    	            idx+=2;
    	            length++;
    	            if(length == N) { // N과 같아질 경우
    	                result++;
    	                length = 0; // 초기화
    	                flag = true;
    	            }
	            }
	        } else {
	            idx++; // 그렇지 않으면 1칸만 증가시킴
	            length = 0; // length는 계속 초기화시킴(IOI나오다가 다른 경우 나올 수 있으므로)
	            flag = false; // 초기화
	        }
	    }

	    System.out.println(result);
	}
}

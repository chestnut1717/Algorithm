import java.util.*;
import java.io.*;

class Solution
{
	public static void main(String[] args) throws Exception{
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    int T = Integer.parseInt(br.readLine());
		for(int test_case = 1; test_case <= T; test_case++) {
		    // 우선 입력을 받은 다음, map을 초기화하자.
		    String num = br.readLine();
		    int N = Integer.parseInt(num);
		    int cnt = 1; // 배수를 구하기 위한 count
		    
		    int mainBit = 0;
		    int comp = (1 << 10) - 1;
		    
		    
		    // mainBit가 1111111111이 나올 때까지
		    // 그리고 cnt는 최대 45만 되고, N <= 10^6이므로 충분히 int내에서 처리 가능)
		    while(true){
		        char[] charArr = Integer.toString(N * cnt).toCharArray();
		        for(char c: charArr) {
		            int n = c - '0';
		            mainBit = mainBit | (1 << n);
		        }
		        if((mainBit & comp) == comp) {
		            break;
		        }
		        cnt++;
		    }
		    
		    
		    System.out.printf("#%d %d\n",test_case, N * cnt);
		    
		}
	}
}

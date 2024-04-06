import java.io.*;
import java.util.*;

public class Main
{
    static int x, y;
    static int[] sum;
	public static void main(String[] args) throws Exception{
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st = new StringTokenizer(br.readLine());
	    
	    x = Integer.parseInt(st.nextToken());
	    y = Integer.parseInt(st.nextToken());
	    
	    sum = new int[y+1];
	    fillSumArray();
	    System.out.println(sum[y] - sum[x-1]);
	}
	
	static void fillSumArray() {
	    int idx = 0; // 현재까지 sumarr채운 기준
	    int limit = y; // 어디까지 채울 것인지 한계
	    int num = 1; // for문의 한계!
	    while(true) {
	        // num번 반복(num = 1 =>1번, 2 => 2번)
	        for(int i = 0; i < num; i++) {
	            sum[idx+1] = sum[idx] + num;
	            idx++;
	            if(idx == limit) return;
	        }
	        num++;
	    }
	}
}

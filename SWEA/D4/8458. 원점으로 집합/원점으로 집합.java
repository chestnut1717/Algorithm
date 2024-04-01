import java.util.*;
import java.io.*;

public class Solution {
    static int T;
    static int N;
    static long[] dist;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		T = Integer.parseInt(br.readLine());
		
		for(int test_case = 1; test_case<= T; test_case++) {
		    N = Integer.parseInt(br.readLine());
            
            // 우선 맨하탄 거리를 담는 배열 생성
		    dist = new long[N];
		    long max = -1;
		    for(int i = 0; i < N; i++) {
		        st = new StringTokenizer(br.readLine());
		        dist[i] = Math.abs(Integer.parseInt(st.nextToken())) + Math.abs(Integer.parseInt(st.nextToken()));
		        max = Math.max(dist[i], max);
		    }
		    
		    int result = -1;
		    boolean flag = true;
		    // 하나라도 짝수거나 홀수가 아니면
		    for(int i = 1; i < N; i++) {
		        if(dist[i] % 2 != dist[i-1] % 2) {
		            flag = false;
		        }
		    }
		    
		    // 만약 모두 홀수거나 짝수라면, 구한다!
		    int idx = 0;
            if(flag) {
                long sum = 0;
                while(true) {
                    sum+= idx;
                    if(sum >= max && (sum - max) % 2 == 0) break;
                    idx++;
                }
                result = idx;
            } else {
                result = -1;
            }
		    
		    sb.append("#").append(test_case).append(" ").append(result).append('\n');
		    
		    
		    
		    
		}
		bw.write(sb.toString());
		bw.close();
	}
	
}
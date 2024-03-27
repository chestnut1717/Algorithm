import java.util.*;
import java.io.*;


class Solution {
	public static void main(String args[]) throws Exception {


		/*
		   여러 개의 테스트 케이스가 주어지므로, 각각을 처리합니다.
		*/
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();
        
        int T = Integer.parseInt(br.readLine());
		for(int test_case = 1; test_case <= T; test_case++)
		{
		    int N = Integer.parseInt(br.readLine());
		    int[] arr = new int[N];
		    st = new StringTokenizer(br.readLine());
		    for(int i = 0; i < N; i++) {
		        arr[i] = Integer.parseInt(st.nextToken());
		    }
		    
		    // LIS를 구하자.
		    // lis배열의 정의 : lis[i] = arr[i]가 LIS배열의 맨 마지막으로 들어가 있는 것 LIS들 중 최대 길이
		    int[] lis = new int[N];
		    int result = Integer.MIN_VALUE; // lis에서 최대값을 찾아내기 위함
		    for(int i = 0; i < N; i++) {
		        lis[i] = 1; //초기 길이는 1로 세팅
		        for(int j = 0; j < i; j++) {
		            // 증가 형태를 띄면서 지금까지 저장된 i번째의 최장 배열의 최대길이가 갱신 가능할 때
		            if(arr[j] < arr[i] && lis[j]+1 > lis[i]) {
		                lis[i] = lis[j]+1;
		            }
		        }
		        result = Math.max(lis[i], result);
		       
		        
		    }

		    
		    sb.append("#").append(test_case).append(" ").append(result).append('\n');

		}
		bw.write(sb.toString());
		bw.close();
	}
}
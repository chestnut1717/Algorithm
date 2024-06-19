import java.util.*;
import java.io.*;

public class Main
{
    static int T;
    static int N;
    static int[] arr;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		T = Integer.parseInt(br.readLine());
		
		// 각각의 테스트케이스마다 채우기
		for(int t = 0; t < T; t++) {
		    N = Integer.parseInt(br.readLine());
		    
		    arr = new int[N];
		    StringTokenizer st = new StringTokenizer(br.readLine());
		    for(int i = 0; i < N; i++) {
		        arr[i] = Integer.parseInt(st.nextToken());
		    }
		    
		    // 뒤에서부터 탐색하면서, 팔 수 있는 경우면 팔고, pivot고정시키고
		    // 팔수 없으면 새로운 pivot으로 넘어간다.
		    int pivot = N-1;
		    long result = 0;
		    for(int i = N-2; i >= 0; i-- ){
		        // 만약 앞에 값이 뒤에 값보다 클 경우! 무시해야 한다.
		        if(arr[i] >= arr[pivot]) {
		            pivot = i;
		        } else {
		            result += (arr[pivot] - arr[i]);
		        }
		    }
		    
		    sb.append(result).append('\n');
		    
		}
		
		bw.write(sb.toString());
		bw.close();
		
	}
}
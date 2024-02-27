
import java.util.*;
import java.io.*;
public class Main {
    static int N;
    static int[][] arr;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		arr = new int[N+1][3];
		
		for(int i = 1; i <= N; i++) {
		    st = new StringTokenizer(br.readLine());
		    arr[i][0] = Integer.parseInt(st.nextToken());
		    arr[i][1] = Integer.parseInt(st.nextToken());
		    arr[i][2] = Integer.parseInt(st.nextToken());
		    
		}
		
		// 입력 끝
		for(int i = 2; i <= N; i++) {
		    arr[i][0] = arr[i][0] + Math.min(arr[i-1][1], arr[i-1][2]);
		    arr[i][1] = arr[i][1] + Math.min(arr[i-1][0], arr[i-1][2]);
		    arr[i][2] = arr[i][2] + Math.min(arr[i-1][0], arr[i-1][1]);
		}
		
		int min = Integer.MAX_VALUE;
		for(int i = 0; i < 3; i++){
		    if(min > arr[N][i]) min = arr[N][i];
		}
		System.out.println(min);
	}
}

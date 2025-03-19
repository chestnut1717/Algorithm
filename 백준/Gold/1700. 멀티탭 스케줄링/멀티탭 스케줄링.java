import java.util.*;
import java.io.*;

public class Main
{
    static int N, K;
    static int[] arr;
    static int[] visited;
    static int result;
    static List<Integer> list = new ArrayList<>();
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		// K개 입력
		arr = new int[104];
		visited = new int[104];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < K; i++) {
		    arr[i] = Integer.parseInt(st.nextToken());


		}
		
		for(int i = 0; i < K; i++) {
		    
		    if(visited[arr[i]] == 0) {
		        // 다 차지 않았다면
		        if(list.size() == N) {
		            // 현재 컨센트에 있는 것들 중에 가장 마지막에 있는 것을 제거
		            int last_idx = 0;
		            int last = 0;
		            for(int v: list) {
                        int tmp_idx = 100000;
		                for(int j = i+1; j <K; j++) {
		                    if(v == arr[j]) {
		                        tmp_idx = j; // 찾았으면 갱신
		                        break;
		                    }
		                }
		                if(last_idx < tmp_idx) {
		                    last = v;
		                    last_idx = tmp_idx;
		                }
		            }
		            
		            // 그리고 제거
		            list.remove(Integer.valueOf(last));
		            visited[last] = 0;
		            result++;
		            
		        }
		        list.add(arr[i]);
		        visited[arr[i]] = 1;
		    }
		}

				


		System.out.println(result);

	}
}
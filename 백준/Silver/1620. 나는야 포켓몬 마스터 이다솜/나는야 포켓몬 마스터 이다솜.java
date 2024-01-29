import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Map;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main

{   
    static int N;
    static int M;
    static Map<String, Integer> mp = new HashMap<>();
    static String[] arr;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new String[N+1]; // 번호가 1번째부터 시작하므로
		
		// 도감에 넣기
		for(int i = 1; i <= N; i++) {
		    String tmp = br.readLine();
		    arr[i] = tmp;
		    mp.put(tmp, i); // map에 넣음

		}
		
		
		// 문제 맞추기
		for(int i = 0; i < M; i++) {
		    String key = br.readLine();
	        Integer result = mp.get(key);  // get()함수가 null반환 가능하므로 int대신 Integer 객체 씀
	        
		    if (result == null) {
		        System.out.println(arr[Integer.parseInt(key)]);
		    } else {
		        System.out.println(result);
		    }


		}
	}
}

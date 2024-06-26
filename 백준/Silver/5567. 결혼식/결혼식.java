import java.util.*;
import java.io.*;

public class Main
{
    static int N; // 동기의 수
    static int M; // 리스트의 길이
    static List<List<Integer>> list = new ArrayList<>(); // 인접 리스트
    static boolean[] isVisited;
    static int result; // 정답
	public static void main(String[] args) throws Exception{
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;
	    N = Integer.parseInt(br.readLine());
	    M = Integer.parseInt(br.readLine());
	    
	    // 인접리스트 동기의 수대로 초기화
	    // 번호가 1번부터 n번까지 시작되므로 N+1개
	    for(int i = 0; i < N+1; i++) {
	        list.add(new ArrayList<>());
	    }
	    
	    // 방문 여부 배열
	    isVisited = new boolean[N+1];
	    
	    // 이제 친구관계 간선 입력
	    for(int i = 0; i < M; i++) {
	        st = new StringTokenizer(br.readLine());
	        int a = Integer.parseInt(st.nextToken());
	        int b = Integer.parseInt(st.nextToken());
	        
	        list.get(a).add(b);
	        list.get(b).add(a);
	    }
	    
        // 상근이의 학번은 1이므로 상근이의 친구들부터 탐색
        isVisited[1]=true;
        for(int friend: list.get(1)) {
            
            // 만약 아직 세지 않은 친구라면 => 더해주기
            if(!isVisited[friend]) {
                result++;
                isVisited[friend] = true;
            }
            

            for(int ff: list.get(friend)) {
                if(isVisited[ff]) continue; // 이미 방문처리 친구들 무시
                isVisited[ff] = true;
                result++;
            }
        }
        
        System.out.println(result);
	    
		
	}
}
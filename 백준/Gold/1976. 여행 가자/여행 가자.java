import java.util.*;
import java.io.*;

public class Main {
    static int N, M;
    static boolean visited[];
    static ArrayList<ArrayList<Integer>> map = new ArrayList<>();
    static ArrayList<ArrayList<Integer>> componentList = new ArrayList<>();
    static ArrayList<Integer> component;
    
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		
		// 인접 리스트 만들기
		for(int i = 0; i < N+1; i++) {
		    map.add(new ArrayList<>());
		}
		
		
		// 그래프 입력
		for(int i = 1; i <= N; i++) {
		    st = new StringTokenizer(br.readLine());
		    for(int j = 1; j <= N; j++) {
		        int num = Integer.parseInt(st.nextToken());
                if(num == 1) {
                    map.get(i).add(j);
                    map.get(j).add(i);
                }

		    }
		}
		
		visited = new boolean[N+1];
        // 해당 인접 리스트에서 connected component 찾아서, 발견하면 넣기
        
        for(int i = 1; i <= N; i++) {
            if(visited[i]) continue; // 이미 방문하였으면
            component = new ArrayList<>();
            visited[i] = true;
            component.add(i);
            dfs(i);

            // 하나의 component가 모였으면, 저장
            componentList.add(component);
        }
        
        // 그다음 M개의 방문 도시 입력
        List<Integer> city = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < M; i++) {
            city.add(Integer.parseInt(st.nextToken()));
        }
        
        boolean flag = false;
        // componentList의 sub list가 city를 포함하면, 00 그렇지 않으면 continue;
        for(List<Integer> tmpList: componentList) {
            if(tmpList.containsAll(city)) {
                flag = true;
                break;
            }
        }
        
        
        if(flag) System.out.println("YES");
        else System.out.println("NO");

	}
	
	
	// 방문 가능한 것은 모두 더함
	static void dfs(int start) {
	    for(int next: map.get(start)) {
	        if(visited[next]) continue;
	        visited[next] = true;
	        component.add(next);
	        dfs(next);
	        
	    }
	    return;
	}
}

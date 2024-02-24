import java.util.*;
import java.io.*;

// 1. 문제셜계 : 30분
// 2. 입려받기 : 10분
// 3. 최적화 : 50분
// 4. 수정 : 10분 => StringBuilder랑 BufferedWriter 실수로 빼먹음
public class Main
{   
    static int N, M;// 역의 개수, 공사 횟수
    static Node[] subway;
    static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		

		int[] tmpArr = new int[N];
		subway = new Node[1000001]; // 역의 고유번호 최댓값 생성
		
		// 0. 지하철 입력
		int max = Integer.MIN_VALUE;
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++){
		    tmpArr[i] = Integer.parseInt(st.nextToken());
		    max = Math.max(max, tmpArr[i]);
		}
		
		// 1. 지하철 배열에 추가
		// 첫번째 지하철 입력
		int prev = tmpArr[N-1];
		int now = tmpArr[0];
		int next = tmpArr[1];
		subway[now] = new Node(prev, next);
		
		// 지하철을 넣어준다
		for(int i = 1; i < N-1; i++) {
		    prev = tmpArr[i-1];
		    now = tmpArr[i];
		    next = tmpArr[i+1];
		    subway[now] = new Node(prev, next);
		}
		
		// 마지막 지하철도 그냥 하드코딩
		prev = tmpArr[N-2];
		now = tmpArr[N-1];
		next = tmpArr[0];
		subway[now] = new Node(prev, next);
		
		
		// 2. 커맨드 입력
		for(int i = 0; i < M; i++) {
		    st = new StringTokenizer(br.readLine());
		    String cmd = st.nextToken();
		    
		    if (cmd.charAt(0) == 'B') {
		        int from = Integer.parseInt(st.nextToken());
		        int to = Integer.parseInt(st.nextToken());
		        if (cmd.equals("BN")) BN(from, to);
		        else if (cmd.equals("BP")) BP(from, to);
		    } else {
		        int from = Integer.parseInt(st.nextToken());
		        if (cmd.equals("CN")) CN(from);
		        else if (cmd.equals("CP")) CP(from);
		    }
		
		}
		
		bw.write(sb.toString());
		bw.close();
		
		
	}
	

    // from 바로 다음에 to 설치, 
    static void BN(int from, int to){
        // 원래 다음 것 출력
        Node fromNode = subway[from];
        int original = fromNode.next;
        sb.append(original).append('\n');
        
        // 새로운 것 삽입
        subway[to] = new Node(from, original);
        fromNode.next = to;
        subway[original].prev = to;
        return;
    }
    
    // from 바로 이전에 to 설치
    static void BP(int from, int to){
        Node fromNode = subway[from]; // 3
        int original = fromNode.prev; // 7
        sb.append(original).append('\n');
        
        // 새로운 것 삽입
        subway[to] = new Node(original, from);
        fromNode.prev = to;
        subway[original].next = to;
        return;
    }
    
    // from 다음 역 폐쇄
    static void CN(int from){
        int target = subway[from].next; // 삭제 대상
        int newLink = subway[target].next; // from노드와 새로 이어줄 대상
        subway[newLink].prev = from;
        subway[from].next = newLink;
        subway[target] = null; // 데이터 삭제
        
        sb.append(target).append('\n');
        return;
    }
    static void CP(int from){
        
        int target = subway[from].prev; // 삭제 대상 11
        int newLink = subway[target].prev; // from노드와 새로 이어줄 대상 2

        subway[newLink].next = from;
        subway[from].prev = newLink;
        subway[target] = null; // 데이터 삭제
        
        sb.append(target).append('\n');
        return;
    }
}
class Node{
    int prev, next;
    public Node(int prev, int next) {
        this.prev = prev;
        this.next = next;
    }
}

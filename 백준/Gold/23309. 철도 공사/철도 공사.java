import java.util.*;
import java.io.*;

/**
 * @since jdk1.8
 * @see <a href="https://www.acmicpc.net/problem/1620">
 * - 문제 : BJ 10972 다음 순열
 * - 소요시간 : 1시간 40분
 *   1. 문제설계 : 30분
 *   2. 입려받기 : 10분
 *   3. 최적화 : 50분
 *   4. 수정 : 10분 => StringBuilder랑 BufferedWriter 실수로 빼먹음
 * - 난이도 : 중
 * - 메모리 : 395624 KB / 시간 : 1856 ms
 * 
 * - 아이디어 : 배열을 활용하여 O(M)의 시간복잡도로 해결해야 하는 문제
 * - subway배열, prev와 next를 담은 custom Node클래스를 활용해 문제 전개 
 * - subway배열의 인덱스는 현재 node의 번호를 나타내므로 Node에 별도로 담을 필요 없음(메모리 절약)
 * - N보다 지하철 번호가 클 수 있으므로 tmpArr을 활용해서 미리 임시로 담아둔다음 for-each를 통해서 다시 담아줌
 * - 그리고 명령어가 들어오면 cmd에 따라 현재 노드와 인접해 있는 노드들의 새로운 데이터를 추가, 삭제해주고prev, next를 변경해주는 연산을 통해 O(1)의 시간복잡도 지킬 수 있음
 * - 주의할 점 : 역의 고유 번호는 1,000,000이하 자연수 => 실제로 N은 2이지만 숫자는 1, 1000000이 들어올 수 있음
 *   => 사전에 지하철 관리하는 배열 미리 1,000,000+a로 초기화
 */

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
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++){
		    tmpArr[i] = Integer.parseInt(st.nextToken());
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
		
		// 메모리 회수
		tmpArr = null;
		
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
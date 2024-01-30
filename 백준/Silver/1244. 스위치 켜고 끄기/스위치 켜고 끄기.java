import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {
    static int N; // 스위치 개수
    static int[] status; // 스위치 상태
    static int M; // 학생 수
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine()); // 스위치 개수 입력
        status = new int[N+1]; // 스위치 상대 개수 초기화; (순번이 1부터이므로 N+1)
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 1; i < N + 1; i++) {
            status[i] = Integer.parseInt(st.nextToken());
        }
        
        M = Integer.parseInt(br.readLine()); // 학생 수 입력;
        // 각 학생의 성별, 받은 수 입력;
        for(int i = 0; i < M; i++) {
            StringTokenizer st2 = new StringTokenizer(br.readLine());
            int gender = Integer.parseInt(st2.nextToken()); // 성별
            int number = Integer.parseInt(st2.nextToken()); // 받은 숫자
            
            // 남자인 경우
            if (gender == 1) {
                for(int j = number; j < N + 1; j+=number) { // 배수마다 스위치 바꿈
                    status[j] = (status[j] == 1) ? 0 : 1; // 삼항연산자로 변경
                }
            // 여자인 경우
            } else {
                int startIdx = number; // 대칭 검사를 하기 위한 출발 인덱스
                int endIdx = number; // 대칭 검사를 하기 위한 끝 인덱스
                   
                // while문을 통해 startIdx, endIdx가 탐색 범위를 벗어나 탐색을 멈출때까지
                while(startIdx >= 1 && endIdx <= N) {
                    // 대칭되는 수가 같거나, 시작점이면 => 스위치 변경
                    if (status[startIdx] == status[endIdx]) {
                        if (startIdx == endIdx) {
                            status[startIdx] = (status[startIdx] == 1) ? 0 : 1; // 시작점일 때는 한번만 변경
                        } else {
                            status[startIdx] = (status[startIdx] == 1) ? 0 : 1;
                            status[endIdx] = (status[endIdx] == 1) ? 0 : 1;
                        }
                        startIdx--; // startIdx감소
                        endIdx++; // endIdx 감소
                    } else {
                        break;
                    }
                }
            }
            
            
        }
        
        // 정답 출력(20개씩 출력)
        for(int i = 1; i < N+1; i++) {
            System.out.printf("%d ", status[i]);
            if(i % 20 == 0) {
                System.out.println(); // 20번째마다 개행
            }
        }

     
    }
    
    
}
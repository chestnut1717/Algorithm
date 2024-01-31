import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    static int N;
    static int M;
    static int[][] arr;
    static int x1, y1, x2, y2;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        arr = new int[N+1][N+1];

        // 배열 입력
        for(int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= N; j++) {
                arr[i][j] = arr[i][j-1] + Integer.parseInt(st.nextToken()); // 행 기준으로 더함
                
            }
        }
        
        // x1, y2, x2, y2 입력받기(행, 열)
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            x1 = Integer.parseInt(st.nextToken());
            y1 = Integer.parseInt(st.nextToken());
            x2 = Integer.parseInt(st.nextToken());
            y2 = Integer.parseInt(st.nextToken());
            
            int height = x2-x1+1;
            int sum = 0;
            for(int j = x1; j < x1+height; j++) {
                sum += arr[j][y2] - arr[j][y1-1];
            }
            sb.append(sum).append("\n");
        }
        
        System.out.println(sb.toString());

        
        
        
    }
}
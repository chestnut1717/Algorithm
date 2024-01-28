import java.util.Scanner;

class Main {
    static int[] arr = new int[104];
    static int A, B, C;
    static int result;
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        A = sc.nextInt(); B = sc.nextInt(); C = sc.nextInt();
        for(int i = 0; i < 3; i++) {
            int start = sc.nextInt();
            int end = sc.nextInt();
            
            for(int j = start + 1; j <= end; j++) {
                arr[j]++;
            }
        }
        
        for(int i = 0; i < arr.length; i++) {
            switch (arr[i]){
                case 1:
                    result += arr[i] * A;
                    break;
                case 2:
                    result += arr[i] * B;
                    break;
                case 3:
                    result += arr[i] * C;
                    break;
                default:
                    continue;
            }
        }
        
        System.out.println(result);
    }
}
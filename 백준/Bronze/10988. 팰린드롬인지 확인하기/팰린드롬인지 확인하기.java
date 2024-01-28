import java.util.Scanner;

class Main {
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        char[] arr = sc.nextLine().toCharArray();
        boolean flag = checkPal(arr);
        System.out.println((flag) ? 1 : 0);
    }
    
    static boolean checkPal(char[] arr) {
        for(int i = 0; i < arr.length / 2; i++) {
            if (arr[i] != arr[arr.length - i - 1]) {
                return false;
            }
        }
        return true;
    }
}
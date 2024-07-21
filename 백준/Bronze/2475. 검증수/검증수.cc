#include <iostream>
#include <cmath>
#define N 5

using namespace std;

int arr[N];
int main()
{
    ios::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);
    
    
    for(int i = 0; i < N; i++) {
        cin >> arr[i];
    }
    
    int num = 0;
    for(int i = 0; i < N; i++) {
        num += pow(arr[i], 2);
    }
    
    cout << num % 10;

    return 0;
}
#include <iostream>
#include <cmath>

using namespace std;


int main()
{
    ios::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    
    int tmp;
    int num = 0;
    for(int i = 0; i < 5; i++) {
        cin >> tmp;
        num += pow(tmp, 2);
    }
    
    cout << num % 10;

    return 0;
}
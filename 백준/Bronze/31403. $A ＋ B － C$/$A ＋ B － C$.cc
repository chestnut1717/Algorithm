#include <bits/stdc++.h>

using namespace std;

string a, b, c;

int main() {
    cin >> a >> b >> c;
    
    // 첫 줄에는 출력
    cout << stoi(a) + stoi(b) - stoi(c) << endl;
    
    cout << stoi(a + b) - stoi(c) << endl;
    
    return 0;
}

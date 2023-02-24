#include <bits/stdc++.h>

using num = int64_t;
using namespace std;
#define rep(i, a, b) for(int i = a; i < (b); ++i)
#define REPI(t, n) for (num t = 0; t < n; ++t)
#define all(x) begin(x), end(x)
#define sz(x) (int)(x).size()
using ll = long long;
using pii = pair<int, int>;
using vi = vector<int>;
#ifdef TESTING
#define DEBUG(...) __VA_ARGS__
#else
#define DEBUG(...)
#endif

int main() {
    num T;
    cin >> T;
    while (T--) {
        num N, K;
        cin >> N >> K;
        num mnVal = N*(N+1)/2;
        if (K < mnVal) {
            cout << ((mnVal-K)*1000000) << "\n";
        } else {
            cout << ((K-mnVal) % N) << "\n";
        }
    }
}

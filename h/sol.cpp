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
    num N, K;
    cin >> N >> K;
    vector<num> a(N);
    num M = 0;
    REPI(i, N) {
        cin >> a[i];
        M += a[i];
    }
    
    vector<num> goods(K);
    REPI(i, K) cin >> goods[i];
    
    constexpr num INF = 1000000001;
    vector<vector<num>> dp(N+1, vector<num>(M+1, INF));
    dp[0][0] = 0;
    rep(done, 1, N+1) {
        REPI(s, M+1) {
            for (num g : goods) {
                if (g > s) continue;
                dp[done][s] = min(dp[done][s], dp[done-1][s-g]+abs(a[done-1]-g));
            }
        }
    }
    if (dp[N][M] == INF) cout << "-1\n";
    else cout << dp[N][M] << "\n";
}

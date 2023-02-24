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
    vector<pair<num,num>> intervals(N);
    REPI(i, N) cin >> intervals[i].first >> intervals[i].second;
    sort(intervals.begin(), intervals.end(), [&](pair<num,num> pr1, pair<num,num> pr2) { return pr1.second < pr2.second; });
    num ans = 0;
    constexpr num INF = 1000000001;
    num mnSoFar = -INF;
    REPI(i, N) {
        num curSt = intervals[i].first;
        num curNd = intervals[i].second;
        if (mnSoFar < curSt) {
            if (mnSoFar == -INF) {
                ++ans;
                mnSoFar = curNd;
            } else {
                num distToInterval = (curSt-mnSoFar);
                num cyclesNeeded = (distToInterval+K-1)/K;
                num nextAfterCurSt = mnSoFar+cyclesNeeded*K;
                assert(nextAfterCurSt >= curSt);
                ans += cyclesNeeded;
                mnSoFar = min(curNd, nextAfterCurSt);
            }
        }
    }
    cout << ans << "\n";
}

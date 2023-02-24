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
    num N, W, T;
    cin >> N >> W >> T;

    vector<vector<vector<pair<num,num>>>> warps(N, vector<vector<pair<num,num>>>(N));
    REPI(i, W) {
        num r1, c1, r2, c2;
        cin >> r1 >> c1 >> r2 >> c2;
        --r1, --c1, --r2, --c2;
        warps[r1][c1].push_back({r2, c2});
    }

    vector<vector<vector<pair<num,pair<num,num>>>>> queries(N, vector<vector<pair<num,pair<num,num>>>>(N));
    REPI(i, T) {
        num rs, cs, re, ce;
        cin >> rs >> cs >> re >> ce;
        --rs, --cs, --re, --ce;
        queries[rs][cs].push_back({i, {re, ce}});
    }
    vector<num> answers(T);
    REPI(rs, N) {
        REPI(cs, N) {
            if (sz(queries[rs][cs]) == 0) continue;
            
            constexpr num INF = 1000000000;
            vector<vector<num>> dsts(N, vector<num>(N, INF));
            vector<vector<pair<num,num>>> prev(N,vector<pair<num,num>>(N));
            queue<pair<num,num>> bfsQ;
            dsts[rs][cs] = 0;
            bfsQ.push({rs, cs});
            while (!bfsQ.empty()) {
                num curR, curC;
                tie(curR, curC) = bfsQ.front();
                bfsQ.pop();

                auto processNeighbor = [&](num x, num y) {
                                           num newDst = dsts[curR][curC]+1;
                                           if (newDst < dsts[x][y]) {
                                               dsts[x][y] = newDst;
                                               bfsQ.push({x, y});
                                               prev[x][y] = {curR, curC};
                                           }
                                       };

                if (curR > 0) processNeighbor(curR-1, curC);
                if (curR+1 < N) processNeighbor(curR+1, curC);
                if (curC > 0) processNeighbor(curR, curC-1);
                if (curC+1 < N) processNeighbor(curR, curC+1);
                processNeighbor(N-1-curC, curR);
                for (auto [nextR, nextC] : warps[curR][curC]) processNeighbor(nextR, nextC);
            }

            for (auto pr : queries[rs][cs]) {
                num qIdx = pr.first;
                num re, ce;
                tie(re, ce) = pr.second;
                answers[qIdx] = dsts[re][ce];
            }
        }
    }
    REPI(i, T) cout << answers[i] << "\n";
}

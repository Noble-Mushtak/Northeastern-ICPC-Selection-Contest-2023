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

struct edge {
    num w, u, v;
};

bool operator<(edge e1, edge e2) {
    return e1.w > e2.w;
}

int main() {
    num N;
    cin >> N;
    vector<set<pair<num,num>>> neighbors(N);
    priority_queue<edge> edgeQ;
    REPI(i, N-1) {
        num a, b, w;
        cin >> a >> b >> w;
        --a, --b;
        neighbors[a].insert({w, b});
        neighbors[b].insert({w, a});
    }
    auto addEdge = [&](num v) {
                       if (sz(neighbors[v]) == 1) {
                           auto it = neighbors[v].begin();
                           edgeQ.push({it->first, v, it->second});
                       }
                   };
    REPI(i, N) addEdge(i);
    REPI(i, N-2) {
        if (i) cout << " ";
        assert(!edgeQ.empty());
        edge curEdge = edgeQ.top();
        cout << curEdge.w;
        edgeQ.pop();
        auto it = neighbors[curEdge.v].find({curEdge.w, curEdge.u});
        assert(it != neighbors[curEdge.v].end());
        neighbors[curEdge.v].erase(it);
        addEdge(curEdge.v);
    }
    cout << "\n";
}

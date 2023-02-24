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
    string S;
    cin >> S;
    vector<num> digs(4);
    REPI(i, 4) digs[i] = (S[i]-'a');
    num numer = 0, denom = 0;
    REPI(c0, 26) {
        REPI(c1, 26) {
            REPI(c2, 26) {
                REPI(c3, 26) {
                    vector<num> curDigs = {c0,c1,c2,c3};
                    bool goodCode = true;
                    REPI(i, 4) {
                        if (digs[i] == curDigs[i]) {
                            goodCode = false;
                            break;
                        }
                    }
                    if (goodCode) {
                        ++denom;
                        bool noConsecEqual = true;
                        REPI(i, 3) {
                            if (curDigs[i] == curDigs[i+1]) {
                                noConsecEqual = false;
                                break;
                            }
                        }
                        if (noConsecEqual) ++numer;
                    }
                }
            }
        }
    }
    assert(denom == 390625); //25^4
    numer *= 4*4*4*4;
    assert(numer >= 10000000);
    cout << "0." << numer << "\n";
}

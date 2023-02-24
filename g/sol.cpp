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

ll euclid(ll a, ll b, ll &x, ll &y) {
	if (!b) return x = 1, y = 0, a;
	ll d = euclid(b, a % b, y, x);
	return y -= a/b * x, d;
}
 
num invert(num a, num MOD) {
    ll x, y, g = euclid(a, MOD, x, y);
    assert(g == 1);
    if (x < 0) return x+MOD;
    return x;
}

int main() {
    num T, MOD;
    cin >> MOD >> T;
    
    constexpr num MAXN = 4000;
    vector<num> fact(MAXN+1);
    fact[0] = 1;
    REPI(i, MAXN) fact[i+1] = (fact[i]*(i+1)) % MOD;
    vector<num> factInv(MAXN+1);
    factInv[MAXN] = invert(fact[MAXN], MOD);
    for (num i = MAXN-1; i >= 0; --i) {
        factInv[i] = (factInv[i+1]*(i+1)) % MOD;
    }
    
    auto choose = [&](num n, num k) {
                      assert(n >= 0);
                      if (k < 0) return (num)0;
                      if (k > n) return (num)0;
                      num tmp = (fact[n]*factInv[k]) % MOD;
                      return (tmp*factInv[n-k]) % MOD;
                  };
    auto solve = [&](num U, num D) {
                     if (D > U) return (num)0;
                     num ans = choose(U+D, D);
                     ans -= choose(U+D, D-1);
                     if (ans < 0) ans += MOD;
                     return ans;
                 };

    vector<pair<num,num>> ans(MOD, {-1,-1});
    REPI(U, 2001) {
        REPI(D, 2001) {
            pair<num,num> &x = ans[solve(U, D)];
            if (x.first == -1) x = {U,D};
        }
    }
    while (T--) {
        num rem;
        cin >> rem;
        if (ans[rem].first == -1) cout << "-1\n";
        else cout << ans[rem].first << " " << ans[rem].second << "\n";
    }
}

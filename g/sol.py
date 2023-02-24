"""INPUT TEMPLATE - I copy paste this part to the top of every solution"""
import sys

toks = (tok for tok in sys.stdin.read().split())
"""END OF TEMPLATE"""

MOD = int(next(toks))
T = int(next(toks))

MAXN = 4000
fact = [1]
for i in range(1, MAXN+1):
    fact.append((fact[-1]*i) % MOD)

fact_inv = [0 for _ in range(MAXN+1)]
fact_inv[MAXN] = pow(fact[MAXN], -1, MOD)
for i in range(MAXN-1, -1, -1):
    fact_inv[i] = (fact_inv[i+1]*(i+1)) % MOD

def choose(A, B):
    tmp = (fact[A]*fact_inv[B]) % MOD
    return (tmp*fact_inv[A-B]) % MOD

def solve(U, D):
    if D > U:
        return 0
    if D == 0:
        return 1
    ans = choose(U+D, D)-choose(U+D, D-1)
    if ans < 0:
        ans += MOD
    return ans
    
answers = [None for _ in range(MOD)]
for U in range(0, 2001):
    for D in range(0, 2001):
        res = solve(U, D)
        if answers[res] == None:
            answers[res] = (U, D)

for _ in range(T):
    M = int(next(toks))
    if answers[M] == None:
        print(-1)
    else:
        U, D = answers[M]
        print(U, D)

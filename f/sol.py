"""INPUT TEMPLATE - I copy paste this part to the top of every solution"""
import sys

toks = (tok for tok in sys.stdin.read().split())
"""END OF TEMPLATE"""

import random
from math import gcd

def addf(f1, f2):
    n1, d1 = f1
    n2, d2 = f2
    new_n = n1*d2+d1*n2
    new_d = d1*d2
    return (new_n, new_d)

def mulf(f1, f2):
    n1, d1 = f1
    n2, d2 = f2
    new_n = n1*n2
    new_d = d1*d2
    return (new_n, new_d)

def negf(f):
    n, d = f
    return -n, d

def subf(f1, f2):
    return addf(f1, negf(f2))

def divf(f1, f2):
    n2, d2 = f2
    return mulf(f1, (d2, n2))

def dist2(p):
    return addf(mulf(p[0], p[0]), mulf(p[1], p[1]))

def mul_point(p, r):
    return (mulf(p[0], r), mulf(p[1], r))

def div_point(p, r):
    return (divf(p[0], r), divf(p[1], r))

def addps(p1, p2):
    return (addf(p1[0], p2[0]), addf(p1[1], p2[1]))

def subps(p1, p2):
    return (subf(p1[0], p2[0]), subf(p1[1], p2[1]))

def cross(p1, p2):
    return subf(mulf(p1[0], p2[1]), mulf(p1[1], p2[0]))

def perp(p):
    return (negf(p[1]), p[0])

def cc_center(p1, p2, p3):
    b = subps(p3, p1)
    c = subps(p2, p1)
    return addps(p1, div_point(div_point(perp(subps(mul_point(b, dist2(c)), mul_point(c, dist2(b)))), cross(b, c)), (2, 1)))

def mec(pts):
    random.shuffle(pts)
    o = pts[0]
    r = (0, 1)
    for i in range(len(pts)):
        if dist2(subps(o, pts[i])) > r:
            o = pts[i]
            r = (0, 1)
            for j in range(i):
                if dist2(subps(o, pts[j])) > r:
                    o = div_point(addps(pts[i], pts[j]), (2, 1))
                    r = dist2(subps(o, pts[i]))
                    for k in range(j):
                        if dist2(subps(o, pts[k])) > r:
                            o = cc_center(pts[i], pts[j], pts[k])
                            r = dist2(subps(o, pts[i]))
    return (o, r)

N = int(next(toks))
M = int(next(toks))
pts = []
for _ in range(N):
    x = int(next(toks))
    y = int(next(toks))
    pts.append(((x, 1), (y, 1)))

center, radius = mec(pts[:M])

goods = []
for i in range(M, N):
    res = subf(dist2(subps(center, pts[i])), radius)
    if res[0] <= 0:
        goods.append(i+1)

print(len(goods))
for i in range(len(goods)):
    if i > 0:
        print(" ", end="")
    print(goods[i], end="")
print()

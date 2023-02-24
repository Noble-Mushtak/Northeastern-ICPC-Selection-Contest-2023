For this editorial, we use 0-based indexing, so the heights of the buildings are `a[0], ..., a[N-1]`.

Let `M` be the sum of all the heights of the buildings in Tildon. Notice that, since we must have 0 bricks left at the end of all modifications, we must change the heights of all the buildings in Tildon such that all the building heights are good but the sum of all the heights is still `M`.

Then, create a `(N+1) * (M+1)` dynamic programming table where for any `0 <= i <= N, 0 <= m <= M`, `dp[i][m]` represents the minimum number of Tildon dollars to change the heights of the first `i` buildings so that they are all good and the sum of the new heights of the first `i` buildings is now `m`.

Initially, all DP values are set to infinity. Then, we update the DP values by looping from `i=1` to `N` and using the recurrence `dp[i][m] = min(dp[i-1][m-g]+abs(a[i-1]-g))`, where the minimum is taken over all good heights `g` such that `g <= m`. The `abs(a[i-1]-g)` part of this recurrence represents the cost to change the height of the `i`th building to `g`, since decreasing or increasing the height of any building costs 1 Tildon dollar.

Finally, the final answer is simply `dp[N][M]`, or `-1` if `dp[N][M]` is still infinity at the end of the algorithm.
The grid can be thought of as a directed graph, where each cell `(r, c)` has edges to `(r-1, c), (r+1, c), (r, c-1), (r, c+1)` (for moving in the up, down, left, and right directions), `(N-1-c, r)` (for rotating the grid with the magic wand), and any cells which are the end of a warp that starts at `(r, c)`. In total, the graph has `V=N^2 <= 4900` vertices and `E=5*N^2+W=27500` edges.

Therefore, each query can be solved using a simple BFS through the graph. However, doing a BFS takes `O(V+E)` and doing `V+E\approx 32400` operations for `T <= 100000` queries is too slow (this gets us to around `3.24 * 10^9` operations and generally, in competitive programming, we use the rule of thumb of `10^8` operations per second).

Instead, we can batch each query into groups by their starting point, and then we do a BFS from each starting point in order to answer the queries in every group using just one BFS. This way, we do one BFS for every point in the graph, so we are doing `V+E\approx 32400` operations for `V <= 4900` vertices in the graph, which gets our solution to around `1.6 * 10^8` operations, which is doable in the time limit of 6 seconds.
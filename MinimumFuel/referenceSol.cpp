#include <bits/stdc++.h>
using namespace std;
#define add push_back
#define INF 1000000000
typedef pair<int,int> ii;

vector<ii> adj[10];
int dist[10][55], cost[10];
int N, M, C, A, B, W;

struct State
{
    int v, c, d;
    State() {}
    State(int a, int b, int _c) { v = a; c = b; d = _c; }
    bool operator>(const State &s) const { return d > s.d; }
};

int dijkstra()
{
    for (int i = 0; i < N; i++) for (int j = 0; j <= C; j++) dist[i][j] = INF;
    priority_queue< State,vector<State>,greater<State> > pq;
    pq.push(State(0,C,0));
    while (!pq.empty())
    {
        State s = pq.top(); pq.pop();
        if (dist[s.v][s.c] < s.d) continue;
        dist[s.v][s.c] = s.d;
        for (auto p : adj[s.v])
        {
            int v = p.first, w = p.second;
            for (int i = C-s.c; i >= 0 && s.c+i-w >= 0; i--)
                if (dist[v][s.c+i-w] > s.d+i*cost[s.v])
                {
                    dist[v][s.c+i-w] = s.d+i*cost[s.v];
                    pq.push(State(v,s.c+i-w,s.d+i*cost[s.v]));
                }
        }
    }

    int ret = INF;
    for (int i = 0; i <= C; i++) ret = min(ret, dist[N-1][i]);
    return ret;
}
int main()
{
    ios_base::sync_with_stdio(false);
    while (scanf("%d %d %d", &N, &M, &C) && N)
    {
        for (int i = 0; i < N; i++) adj[i].clear();
        for (int i = 0; i < M; i++)
        {
            scanf("%d %d %d", &A, &B, &W); A--; B--;
            adj[A].add(ii(B,W));
            adj[B].add(ii(A,W));
        }
        for (int i = 0; i < N; i++) scanf("%d", cost+i);
        int ret = dijkstra();
        printf("%d\n", ret==INF?-1:ret);
    }
}
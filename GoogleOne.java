import java.util.*;

public class GoogleOne {

    private static class T{
        public int idx, val;
        public List<T> next;
        public T(int idx, int val){
            this.idx = idx;
            this.val = val;
            this.next = new LinkedList<>();
        }
    }

    private static class R {
        public int count, paths;
        public R(){
            count = paths = 0;
        }
    }

    private R dfs(T[] nodes, int curr, int val, int parent){
        R res = new R();
        for(T nextNode: nodes[curr].next){
            if(nextNode.idx == parent) continue;
            R r = dfs(nodes, nextNode.idx, val, curr);
            res.count += r.count;
            res.paths += res.count*r.count;
        }
        if(nodes[curr].val == val){
            res.paths += res.count;
            res.count += 1;
        }else if(nodes[curr].val > val){
            res.count = 0;
        }
        return res;
    }

    private int sol(final int N, int [][]edges, int []A){
        T[] nodes = new T[N+1];
        for(int i=0; i<=N; i++){
            nodes[i] = new T(i, A[i]);
        }
        for(int []edge: edges){
            int s = edge[0], d = edge[1];
            nodes[s].next.add(nodes[d]);
            nodes[d].next.add(nodes[s]);
        }
        Set<Integer> set = new HashSet<>();
        for(int a: A){
            set.add(a);
        }
        nodes[0].next.add(nodes[1]);
        int res = 0;
        for(int a: set) {
            R r = dfs(nodes, 0, a, -1);
            res += r.paths;
        }
        return res;
    }

    public static void main(String[] args) {
        GoogleOne googleOne = new GoogleOne();
//        System.out.println(googleOne.sol(6, ));
    }
}

package FirstThreeTwo.ProcessRestrictedFriendRequests;

public class Solution {

    private static class DisjointSet{
        public int rank, val;
        public DisjointSet parent;
        private DisjointSet(int val){
            this.val = val;
            this.rank = 1;
            this.parent = this;
        }
    }

    DisjointSet[] estranged, ldf;

    private void makeSets(int val){
        ldf[val] = new DisjointSet(val);
        return;
    }

    private DisjointSet findRep(int val){
        return findRep(ldf[val]);
    }

    private DisjointSet findRep(DisjointSet curr){
        DisjointSet node = curr;
        while(node.parent != node){
            node = node.parent;
        }
        curr.parent = node; // path compression
        return node;
    }

    private void unionSet(int val1, int val2){
        DisjointSet set1 = findRep(val1);
        DisjointSet set2 = findRep(val2);

        if(set1.val == set2.val){
            return;
        }
        if(set1.rank >= set2.rank){
            if(set1.rank == set2.rank){
                set1.rank++;
            }
            set2.parent = set1;
        }else{
            set1.parent = set2;
        }
    }


    public boolean[] friendRequests(int n, int[][] restrictions, int[][] requests) {
        ldf = new DisjointSet[n];
        for(int i=0; i<n; i++){
            makeSets(i);
        }
        boolean res[] = new boolean[requests.length];
        for(int i=0; i<requests.length; i++){
            int[] request = requests[i];
            int src = findRep(request[0]).val, dest = findRep(request[1]).val;
            if(src != dest){
                boolean canViolateRestriction = false;
                for(int restriction[]: restrictions){
                    int rSrc = findRep(restriction[0]).val, rDest = findRep(restriction[1]).val;
                    if((rSrc == src && rDest == dest) || (rSrc == dest && rDest == src)){
                        canViolateRestriction = true;
                        break;
                    }
                }
                if(canViolateRestriction){
                    res[i] = false;
                }else{
                    res[i] = true;
                    unionSet(request[0], request[1]);
                }
            }else{
                res[i] = true;
            }
        }

        return res;
    }
}

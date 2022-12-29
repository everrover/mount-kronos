package NinetySix;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * https://leetcode.com/problems/add-edges-to-make-degrees-of-all-nodes-even/
 * As the code suggests
 */

public class AddEdgesToMakeDegreesOfAllNodesEven {
  public boolean isPossible(int n, List<List<Integer>> edges) {
    Set<Integer>[]nm = new HashSet[n+1];
    List<Integer> oddBalls = new ArrayList<>();
    for(int i=1; i<=n; i++) nm[i] = new HashSet<>();
    for(List<Integer> edge: edges){ // create adjacency-set
      nm[edge.get(1)].add(edge.get(0));
      nm[edge.get(0)].add(edge.get(1));
    }
    for(int i=1; i<=n; i++){
      if(nm[i].size()%2 == 1){
        oddBalls.add(i);
      }
    }
    if(oddBalls.size() == 4){
      // at least one combination should be present where the four nodes can connect together amongst themselves
      int a = oddBalls.get(0), b = oddBalls.get(1), c = oddBalls.get(2), d = oddBalls.get(3);
      return (!nm[a].contains(b) && !nm[c].contains(d)) || (!nm[a].contains(d) && !nm[c].contains(b)) || (!nm[a].contains(c) && !nm[b].contains(d));
      // it worked, but a case seems missing: #incorrect:: only two edges allowed
      // two of these odd-degree pairs connecting to two different or the same even-edged node(s)
    }else if(oddBalls.size() == 2){
      // two odd-degree nodes can connect to the same node(with even-degree) and it would result in even-degree node
      // since: all remaining nodes are with even degree
      // degree of connected node = 2n+2 = 2(n+1)
      int a = oddBalls.get(0), b = oddBalls.get(1);
      for(int i=1; i<=n; i++){
        if(!nm[a].contains(i) && !nm[b].contains(i)) return true;
      }
      return false;
    }else{
      // any other combo can't exist and will always violate a condition
      return oddBalls.size() == 0;
    }
  }
}

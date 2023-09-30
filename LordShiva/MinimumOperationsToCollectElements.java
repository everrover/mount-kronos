class MinimumOperationsToCollectElements {
  /**
  https://leetcode.com/problems/minimum-operations-to-collect-elements/
  
  Tags:: #easy #set
  **/
  public int minOperations(List<Integer> nums, int k) {
    int steps = 0;
    Set<Integer> s = new HashSet<>();
    int i=nums.size()-1;
    for(; i>=0; i--){
      steps++;
      if(nums.get(i) >= 1 && nums.get(i) <= k){
        s.add(nums.get(i));
      }
      if(s.size() == k){
        break;
      }
    }
    return steps;
  }
}

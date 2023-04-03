package NinetySix;

/**
 * https://leetcode.com/problems/find-the-student-that-will-replace-the-chalk/
 * The cycle will repeat till sum(arr)*i > k; i-> number of cycles.
 * Removed cycle using prefixSum, k%prefixSum. Iterated once more, for final simulation.
 */
public class FindStudentThatWillReplaceChalk {
  public int chalkReplacer(int[] chalk, int k) {
    long []prefix = new long[chalk.length];
    prefix[0] = chalk[0];
    for(int i=1; i<chalk.length; i++){
      prefix[i] = prefix[i-1]+chalk[i];
    }
    if(prefix[chalk.length-1] <= k){
      k%=prefix[chalk.length-1];
    }
    for(int i=0; i<chalk.length; i++){ // could have used binary search here to save some computes
      if(prefix[i]>k) return i;
    }
    return 0;
  }
}

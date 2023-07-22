public class ProbabilityOfTwoBoxesHavingSameNumberOfDistinctBalls {

  /**
   * https://leetcode.com/problems/probability-of-a-two-boxes-having-the-same-number-of-distinct-balls/
   * 
   * TC: O(n*2^n) SC: O(n)
   * #dynamic-programming #memoization #combinatorics #backtracking #tricky #hard
   */
  private int n, sum;
  private int []balls;
  private double factorial[];
  private double perm(int []arr, int total){
    double res = factorial[total];
    for(int a: arr) res /= factorial[a];
    return res;
  }
  private double[] generateFact(int n){
    double []factorial = new double[n+1];
    factorial[0] = 1;
    for(int i=1; i<=n; i++){
      factorial[i] = factorial[i-1]*i;
    }
    return factorial;
  }
  public double getProbability(int[] balls) {
    n = balls.length; sum = 0;
    this.balls = balls;
    for(int ball: balls) sum+=ball;
    factorial = generateFact(sum);
    double den = perm(balls, sum);
    double num = validScenarios(0, new int[n], new int[n], 0, 0);
    return num/den;
  }

  private int count(int []part){
    int res = 0;
    for(int p: part) if(p>0) res++;
    return res;
  }

  private double validScenarios(int idx, int []partOfA, int []partOfB, int sumA, int sumB){
    if(sumA > sum/2 || sumB > sum/2) return 0;
    if(idx == n) return (count(partOfA) == count(partOfB))?(perm(partOfA, sum/2)*perm(partOfB, sum/2)):0;
    double scenarioCnt = 0;
    for(int c=0; c<=balls[idx]; c++){
      partOfA[idx] = c;
      partOfB[idx] = balls[idx]-c;
      scenarioCnt += validScenarios(idx+1, partOfA, partOfB, sumA+c, sumB+balls[idx]-c);
    }
    return scenarioCnt;
  }
}

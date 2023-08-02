package SuperMario;

public class MinPossibleIntegerAfterAtMostKAdjSwapsOnDigits {
  // code for segment tree class for finding min in a range
  private static class SegmentTreeFirstSmallest {
    int st[];


    SegmentTreeFirstSmallest(int arr[], int n) {
      int x = (int) (Math.ceil(Math.log(n) / Math.log(2)));
      int max_size = 2 * (int) Math.pow(2, x) - 1;
      st = new int[max_size];
      constructSTUtil(arr, 0, n - 1, 0);
    }

    int getMid(int s, int e) {
      return s + (e - s) / 2;
    }

    int constructSTUtil(int arr[], int ss, int se, int si) {
      if (ss == se) {
        st[si] = ss;
        return st[si];
      }
      int mid = getMid(ss, se);
      int first = constructSTUtil(arr, ss, mid, si * 2 + 1);
      int second = constructSTUtil(arr, mid + 1, se, si * 2 + 2);
      if(st[first] < st[second]) st[si] = first;
      else st[si] = second;
      return st[si];
    }

    int query(int ss, int se, int qs, int qe, int si) {
      if (qs <= ss && qe >= se)
        return st[si];
      if (se < qs || ss > qe)
        return Integer.MAX_VALUE;
      int mid = getMid(ss, se);
      return Math.min(query(ss, mid, qs, qe, 2 * si + 1),
          query(mid + 1, se, qs, qe, 2 * si + 2));
    }

    void updateValueUtil(int ss, int se, int i, int diff, int si) {
      if (i < ss || i > se)
        return;
      st[si] = Math.min(st[si], diff);
      if (se != ss) {
        int mid = getMid(ss, se);
        updateValueUtil(ss, mid, i, diff, 2 * si + 1);
        updateValueUtil(mid + 1, se, i, diff, 2 * si + 2);
      }
    }
  }


  public String minInteger(String num, int k) {
    int nums[] = new int[num.length()];
    for(int i=0; i<nums.length; i++) {
      nums[i] = Integer.parseInt(String.valueOf(num.charAt(i)));
    }
    SegmentTree st = new SegmentTree(nums);
    StringBuilder res = new StringBuilder();
    for(int i=0; i<nums.length; i++){
      int min = st.query(0, nums.length-1, i, Math.min(i+k, nums.length-1), 0);
      int pos = -1;
      res.append(min);
      st.update(0, nums.length-1, 0, pos, Integer.MAX_VALUE);
      k -= pos-i;
    }
    return res.toString();
  }
}

package NinetySix;

/**
 * https://leetcode.com/problems/find-the-original-array-of-prefix-xor/
 * pref[i] = arr[i]*pref[i-1] => pref[i]^pref[i]^arr[i] = arr[i]^pref[i-1]^pref[i]^arr[i] => arr[i] = pref[i-1]^pref[i]
 */
public class FindOrginalArrayOfPrefixXor {
  public int[] findArray(int[] pref) {
    int prefix = pref[0];
    for(int i=1; i<pref.length; i++){
      pref[i] = pref[i] ^ prefix;
      prefix = pref[i] ^ prefix; // original prefix would be altered in next step, hence storing it
    }
    return pref;
  }
}

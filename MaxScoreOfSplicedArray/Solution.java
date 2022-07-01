package MaxScoreOfSplicedArray;

public class Solution {
    public int maximumsSplicedArray(int[] nums1, int[] nums2) {
        int res = 0, s1 = 0, s2 = 0;
        final int n = nums1.length;
        int pp[] = new int[n];
        pp[0] = nums1[0]-nums2[0];
        for(int i=0; i<n; i++){
            pp[i] = nums1[i]-nums2[i];
            s1 += nums1[i];
            s2 += nums2[i];
        }
        int mp=0, mn=0;
        for(int i=0; i<n; i++){
            int mpt = pp[i]+mp;
            if(mpt>0){
                res = Math.max(res, Math.max(s1-mpt,s2+mpt));
                mp = mpt;
            }else{
                mp = 0;
            }
            int mnt = pp[i]+mn;
            if(mnt<0){
                res = Math.max(res, Math.max(s1-mnt,s2+mnt));
                mn = mnt;
            }else{
                mn = 0;
            }
        }
        res = Math.max(res, Math.max(s1,s2));
        return res;
    }
}

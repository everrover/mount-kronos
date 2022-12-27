package NinetySix;

/**
 * 2516. Take K of Each Character From Left and Right
 * https://leetcode.com/problems/take-k-of-each-character-from-left-and-right/
 */
public class TakeKOfEachCharacterFromLeftAndRight {
  public int takeCharacters(String s, int k) {
    if(k == 0) return 0;
    char []chs = s.toCharArray();
    int l = -1, r = chs.length-1, res = 100001;
    int ax, bx, cx, ay, by, cy;
    ax = bx = cx = ay = by = cy = 0;
    while(r>=0){ // largest possible window counts
      if(chs[r] == 'a') ay++;
      else if(chs[r] == 'b') by++;
      else if(chs[r] == 'c') cy++;
      r--;
    }
    if((ax+ay)<k || (bx+by)<k || (cx+cy)<k) return -1;
    r = 0;
    while(l<chs.length && r<chs.length){
      r = Math.max(r, l+1);
      while(r<=chs.length){ // shrink window of [r...len) till condition isn't violated
        if((ax+ay)<k || (bx+by)<k || (cx+cy)<k) break;
        res = Math.min(res, l+1+chs.length-r); // `res` is smallest amongst all valid windows
        if(r == chs.length) break;
        if(chs[r] == 'a') ay--;
        else if(chs[r] == 'b') by--;
        else if(chs[r] == 'c') cy--;
        r++;
      }

      l++; // increase window of [0...l] by 1
      if(l>=chs.length) break;
      if(chs[l] == 'a') ax++;
      else if(chs[l] == 'b') bx++;
      else if(chs[l] == 'c') cx++;
    }
    return res;
  }

  public int takeCharactersV2(String s, int k) {
    if(k == 0) return 0;
    char []chs = s.toCharArray();
    int l = -1, r = chs.length-1, res = 100001;
    int a, b, c;
    a = b = c = 0;
    while(r>=0){ // largest possible window counts
      if(chs[r] == 'a') a++;
      else if(chs[r] == 'b') b++;
      else if(chs[r] == 'c') c++;
      r--;
    }
    if(a<k || b<k || c<k) return -1;
    r = 0;
    while(l<chs.length && r<chs.length){
      r = Math.max(r, l+1);
      while(r<=chs.length){ // shrink window of [r...len) till condition isn't violated
        if(a<k || b<k || c<k) break;
        res = Math.min(res, l+1+chs.length-r);
        if(r == chs.length) break;
        if(chs[r] == 'a') a--;
        else if(chs[r] == 'b') b--;
        else if(chs[r] == 'c') c--;
        r++;
      }

      l++; // increase window of [0...l] by 1
      if(l>=chs.length) break;
      if(chs[l] == 'a') a++;
      else if(chs[l] == 'b') b++;
      else if(chs[l] == 'c') c++;
    }
    return res;
  }
}

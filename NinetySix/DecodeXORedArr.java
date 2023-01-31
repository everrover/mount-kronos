package NinetySix;

/**
 * https://leetcode.com/problems/decode-xored-array/
 * enc[i] = dec[i-1] ^ enc[i-1]
 * => dec[i] = enc[i-1] ^ dec[i-1], dec[0] = first
 */

public class DecodeXORedArr {
  public int[] decode(int[] encoded, int first) {
    int []decoded = new int[encoded.length+1];
    decoded[0] = first;
    for(int i=0; i<encoded.length; i++){
      decoded[i+1] = decoded[i] ^ encoded[i];
    }
    return decoded;
  }
}

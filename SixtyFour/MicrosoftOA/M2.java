package SixtyFour.MicrosoftOA;

public class M2 {


    String repeat(String a, int count){
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<count;i++) sb.append(a);
        return sb.toString();
    }
    String generate(int a, int b, int c, String aa, String bb, String cc) {
        if (a < b)
            return generate(b, a, c, bb, aa, cc);
        if (b < c)
            return generate(a, c, b, aa, cc, bb);
        if (b == 0)
            return repeat(aa, Math.min(2, a));
        int use_a = Math.min(2, a), use_b = ((a - use_a) >= b ? 1 : 0);
        return repeat(aa, use_a) + repeat(bb, use_b) +
                generate(a - use_a, b - use_b, c, aa, bb, cc);
    }
    public String longestDiverseString(int a, int b, int c) {
        return generate(a, b, c, "a", "b", "c");
    }

    public String longestDiverseStringv2(int a, int b, int c) {
        return generate(new StringBuilder(), a, b, c, 'a', 'b', 'c');
    }
    String generate(StringBuilder strb, int a, int b, int c, char sa, char sb, char sc) {
        if (a < b)
            return generate(strb, b, a, c, sb, sa, sc);
        else if (b < c)
            return generate(strb, a, c, b, sa, sc, sb);
        else if (b == 0){
            for(int i=0;i<Math.min(2, a);i++) strb.append(sa);
            return strb.toString();
        }
        int useA = Math.min(2, a), useB = (a - useA) >= b ? 1 : 0;
        for(int i=0;i<useA;i++) strb.append(sa);
        for(int i=0;i<useB;i++) strb.append(sb);
        return generate(strb, a - useA, b - useB, c, sa, sb, sc);
    }

    public static void main(String[] args) {
        System.out.println(new M2().longestDiverseString(6,1,1));
        System.out.println(new M2().longestDiverseString(1,3,1));
        System.out.println(new M2().longestDiverseString(0,1,8));
        System.out.println(new M2().longestDiverseString(100,100,100));
        System.out.println(new M2().longestDiverseStringv2(6,1,1));
        System.out.println(new M2().longestDiverseStringv2(1,3,1));
        System.out.println(new M2().longestDiverseStringv2(0,1,8));
        System.out.println(new M2().longestDiverseStringv2(100,100,100));
    }
}

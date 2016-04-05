package gcj;
import general.Functions;
import general.GCJProblem;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;

/* @author fazuniga */
public class Problem {
    public static String PolynesiaGlotSingle(List<String> s) {
        BigInteger C, V, L;
        
        C = new BigInteger(s.get(0));
        V = new BigInteger(s.get(1));
        L = new BigInteger(s.get(2));
        
        // String msg = (Words(C, V, L).remainder(BigInteger.valueOf(1000000007))).toString();
        String msg = (Results(C, V, L)).toString();
        
        return msg;
    }
    
    public static BigInteger Words(BigInteger C, BigInteger V, BigInteger L) {
        if (L.equals(BigInteger.valueOf(1))) {
            return V.pow(2);
        } else if (L.equals(BigInteger.valueOf(2))) {
            return V.multiply(V.add(C));
        } else {
            return V.multiply(Words(C, V, L.subtract(BigInteger.valueOf(1)))).
                    add(C.multiply(V).multiply(Words(C, V, L.subtract(BigInteger.valueOf(2)))));
        }
    }
    
    public static BigInteger Results(BigInteger C, BigInteger V, BigInteger L) {
        BigInteger sum = BigInteger.valueOf(0);
        
        for (BigInteger j = BigInteger.valueOf(0); j.compareTo(N(L)) < 0; j = j.add(BigInteger.ONE))
        {
            sum = sum.add(Coefficients(j, L).multiply(C.pow(j.intValue())).multiply(V.pow((L.subtract(BigInteger.valueOf(j.longValue()))).intValue())));
        }
        
        return sum.remainder(BigInteger.valueOf(1000000007));
    }
    
    public static BigInteger Coefficients(BigInteger j, BigInteger L) {
        System.out.println("## [j, L]: [" + j + ", " + L + "]");
        
        BigInteger N = N(L);
        if (L.compareTo(N) < 0) {
            return BigInteger.valueOf(0);
        } else {
            if (j.equals(BigInteger.valueOf(0))) { return BigInteger.valueOf(1); }
            else if (j.equals(BigInteger.valueOf(1))) { return L.subtract(BigInteger.valueOf(1)); }
            else {
                BigInteger m = BigInteger.ZERO;
            
                if (j.equals(N.subtract(BigInteger.ONE))) {
                    if (Functions.IsEven(L)) { m = BigInteger.ONE; }
                    else if (Functions.IsOdd(L)) { m = j.add(BigInteger.ONE); }
                } else {
                // j > 1 => sum(f(c^[j-1], m), m = 1, …, L-2)
                /* BigInteger s = BigInteger.ZERO;
                for (BigInteger i = BigInteger.valueOf(1);
                        i.compareTo(L.subtract(BigInteger.valueOf(2))) <= 0;
                        i = i.add(BigInteger.ONE)) {
                    BigInteger aux = Coefficients(j.subtract(BigInteger.valueOf(1)), i);
                    s = s.add(aux);
                } */
                m = Coefficients(j, L.subtract(BigInteger.ONE))
                        .add(Coefficients(j.subtract(BigInteger.ONE), L.subtract(BigInteger.valueOf(2))));
                }
                return m;
            }
        }
    }
    
    public static BigInteger N(BigInteger L) {
        if (L.mod(BigInteger.valueOf(2)).equals(BigInteger.ZERO)) {
            return L.divide(BigInteger.valueOf(2)).add(BigInteger.valueOf(1));
        } else {
            return (L.add(BigInteger.ONE)).divide(BigInteger.valueOf(2));
        }
    }
    
    public static void PolynesiaGlot(GCJProblem cjp) throws IOException
    {
        for (int iT = 0; iT < cjp.getT(); iT++)
        {
            List<String> s = Arrays.asList(cjp.getCases().get(iT).get(0).split(" "));
            String msg = ""; PolynesiaGlotSingle(s);
            
            int c = 1;
            int v = 1;
            int l = 500;
            BigInteger C = BigInteger.valueOf(c);
            BigInteger V = BigInteger.valueOf(v);
            BigInteger L = BigInteger.valueOf(l);
            BigInteger ss = Results(C, V, L);
            
            System.out.println("Case #" + (iT+1) + ": " + msg);
            cjp.getPw().println("Case #" + (iT+1) + ": " + msg);
            cjp.getPw().flush();
        }
    }
}
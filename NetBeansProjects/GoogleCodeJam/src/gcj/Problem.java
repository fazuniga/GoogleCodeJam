package gcj;
import general.GCJProblem;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* @author fazuniga */
public class Problem {
    public static String PolynesiaGlotSingle(List<String> s) {
        BigInteger C, V, L;
        
        C = new BigInteger(s.get(0));
        V = new BigInteger(s.get(1));
        L = new BigInteger(s.get(2));
        
        String msg = (Words(C, V, L).remainder(BigInteger.valueOf(1000000007))).toString();
        
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
    
    public static void PolynesiaGlot(GCJProblem cjp) throws IOException
    {
        for (int iT = 0; iT < cjp.getT(); iT++)
        {
            List<String> s = Arrays.asList(cjp.getCases().get(iT).get(0).split(" "));
            String msg = PolynesiaGlotSingle(s);
            
            System.out.println("Case #" + (iT+1) + ": " + msg);
            cjp.getPw().println("Case #" + (iT+1) + ": " + msg);
            cjp.getPw().flush();
        }
    }
}
package gcj;
import general.Functions;
import general.GCJProblem;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/* @author fazuniga */
public class Problem {
    
    public static String Single(List<String> params) {
        
        List<Integer> p = PancakesToInt(params);
        String msg = String.valueOf(SolvePancakes(p));
        return msg;
    }
    
    public static int SolvePancakes(List<Integer> p) {
        int N = p.size();
        int sum = suma(p);
        
        System.out.println("entra: " + p);
        
        if (sum == N) { return 0; }
        else if (sum == 0) {
            return 1;
        } else {
            if (p.get(0) == 1) {
                System.out.println("p pre: " + p);
                Boolean ok = false;
                for (int i = 1; i < N; i++) {
                    List<Integer> sl = p.subList(i+1, N);
                    
                    System.out.println("i: " + i + " - p(i): " + p.get(i) + " - ss: " + sl + " - sls: " + sl.size());
                    
                    if (p.get(i) == 0 && suma(sl) != sl.size()) {
                        p.set(i, 1);
                        ok = true;
                    } else if (p.get(i) == 1 && ok) {
                        break;
                    }
                }
                System.out.println("p out: " + p);
                return 2 + SolvePancakes(p);
            }
            else {
                for (int i = 0; i < N; i++) {
                    if (p.get(i) == 1) {
                        break;
                    } else {
                        p.set(i, 1);
                    }
                }
                return 1 + SolvePancakes(p);
            }
        }
    }
    
    public static int suma(List<Integer> p) {
        return p.stream().mapToInt(Integer::intValue).sum();
    }
    
    public static List<Integer> PancakesToInt(List<String> p) {

        List<Integer> n = new ArrayList<>();
        for (int i = 0; i < p.size(); i++) {
            if (p.get(i).equals("+")) { n.add(1); }
            else { n.add(0); }
        }
        
        return n;
    }
    
    public static List<String> IntToPancakes(List<Integer> p) {
        
        List<String> n = new ArrayList<>();
        
        for (int i = 0; i < p.size(); i++) {
            if (p.get(i) == 1) { n.add("+"); }
            else { n.add("-"); }
        }
        
        return n;
    }
    
    public static List<Integer> FlipPancakes(List<String> p) {
        return PancakesToInt(Functions.ReverseStringList(p));
    }
    
    public static void Main(GCJProblem cjp) {
        for (int iT = 0; iT < cjp.getT(); iT++)
        {
            List<String> params = Arrays.asList(cjp.getCases().get(iT).get(0).split(""));
            String msg = Single(params);
            
            String out = "Case #" + (iT+1) + ": " + msg;
            System.out.println(out);
            cjp.getPw().println(out);
            cjp.getPw().flush();
        }
    }
}
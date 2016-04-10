package gcj;
import general.GCJProblem;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* @author fazuniga */
public class Problem {
    
    public static String Single(List<String> params) {
        List<Integer> p = PancakesToInt(params);
        return String.valueOf(SolvePancakes(p));
    }
    
    public static int SolvePancakes(List<Integer> p) {
        int N = p.size();
        int sum = suma(p);
        
        if (sum == N) { return 0; }
        else if (sum == 0) {
            return 1;
        } else {
            if (p.get(0) == 1) {
                Boolean cero_found = false;
                for (int i = 0; i < N; i++) {
                    int num = p.get(i);
                    
                    if (num == 0) {
                        p.set(i, 1);
                        cero_found = true; 
                    }
                    if (num == 1 && cero_found) { break; }
                }
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
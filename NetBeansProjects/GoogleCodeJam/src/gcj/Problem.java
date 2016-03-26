package gcj;
import general.Functions;
import general.GCJProblem;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/* @author fazuniga */
public class Problem {
    public static String CodysJamSingle(int N, List<String> s) {
        List<Integer> prices = Functions.ConvertStringToIntList(s);
        // Collections.reverse(prices);
       
        Map<Integer, Long> count = prices.stream().collect(
                Collectors.groupingBy(Function.identity(), Collectors.counting()));
        List<Integer> finales = new ArrayList<>(N);
        
        for (Integer p : prices) {
            int candidato = (int) (p / 0.75);
            
            if (prices.contains(candidato) && count.get(candidato) > 0) {
                finales.add(p);
                count.put(candidato, count.get(candidato) - 1);
            }
            
            // System.out.println(count);
        }
        
        Collections.sort(finales);
        
        String msg = "";
        if (finales.size() == N) {
            for (int i = 0; i < finales.size(); i++) {
                if (i < finales.size() - 1) { msg += finales.get(i) + " "; }
                else { msg += finales.get(i); }
            }
        }
        
        return msg;
    }
    
    public static void CodysJam(GCJProblem cjp) throws IOException {
        
        for (int iT = 0; iT < cjp.getT(); iT++)
        {
            if (iT+1 == 19) {
                int x = 0;
            }
            
            int N = Integer.parseInt(cjp.getCases().get(iT).get(0));
            List<String> s = Arrays.asList(cjp.getCases().get(iT).get(1).split(" "));
            String msg = CodysJamSingle(N, s);
            
            System.out.println("Case #" + (iT+1) + ": " + msg);
            cjp.getPw().println("Case #" + (iT+1) + ": " + msg);
            cjp.getPw().flush();
        }
    }
}
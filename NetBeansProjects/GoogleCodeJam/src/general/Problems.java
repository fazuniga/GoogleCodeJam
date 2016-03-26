package general;
import static general.Functions.Promedio;
import static general.Functions.Divisors;
import static general.Functions.Euclidean;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.Math;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;
import static general.Functions.Suma;
import java.util.function.Function;

/* @author fazuniga */
public class Problems
{   
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
    
    public static String ReverseWordsSingle(List<String> s) {
        // Definir variables específicas del problema 
        String msg = "";
        List<String> ss = Functions.ReverseStringList(s);

        for (int ii = 0; ii < ss.size(); ii++) {
            if (ii < ss.size() - 1) { msg += ss.get(ii) + " "; }
            else { msg += ss.get(ii); }
        }
        
        return msg;
    }
    public static void ReverseWords(GCJProblem cjp) throws IOException
    {
        for (int iT = 0; iT < cjp.getT(); iT++)
        {
            List<String> s = Arrays.asList(cjp.getCases().get(iT).get(0).split(" "));
            String msg = ReverseWordsSingle(s);
            
            System.out.println("Case #" + (iT+1) + ": " + msg);
            cjp.getPw().println("Case #" + (iT+1) + ": " + msg);
            cjp.getPw().flush();
        }
    }
    
    public static double[] minimumWork(double[] x, double[] y, double[] r, double[] m)
    {
        int N = x.length;
        // double[] ret = new double[N];
        double[] f = new double[2*N];
        double GAP = 1e-9;
        Boolean Overlap = true;
        int YMIN = -100;
        int YMAX = 100;

        while (Overlap)
        {
            for (int i = 0; i < N; i++)
            {
                for (int j = i + 1; j < N; j++)
                {
                    double d = Euclidean(x[i], y[i], x[j], y[j]);
                    if (d >= r[i] + r[j] + GAP) { Overlap = false; }
                    else { Overlap = true; }

                    if (Overlap)
                    {
                        double fy = y[j];
                        if (fy >= YMAX) { fy = YMAX; }
                        if (fy <= YMIN) { fy = YMIN; }
                        double fx = x[i] + Math.sqrt(Math.pow(r[i] + r[j] + GAP, 2) - Math.pow(y[j] - y[i], 2));

                        f[2 * (i + 1) - 1] = x[i];
                        f[2 * (i + 1)] = y[i];
                        f[2 * (j + 1) - 1] = fx;
                        f[2 * (j + 1)] = fy; 
                    }
                }
            }
        }

        return f;
    }

    /*
    public int[] Lychrel(int i)
    {
        int count = 0, countMax = 50;
        Boolean stop = false, EsPalindromo = false;
        int[] salida = new int[2];
        salida[0] = i;

        BigInteger iRev, iDer;
        Boolean ok_1, ok_2;
        iDer = BigInteger.valueOf(i);

        while (!EsPalindromo && !stop)
        {
            ok_2 = BigInteger.TryParse(Reversa(iDer.toString()), out iRev);
            BigInteger s = iDer + iRev;
            if (IsPalindrome(s)) { EsPalindromo = true; count++; }
            else {
                count++;
                iDer = s;
            }

            if (count == countMax) { stop = true; count = 1000; }
        }
        salida[1] = count;
        return salida;
    }

    public void CuentaLychrel()
    {
        Dictionary<int, int> Dict = new Dictionary<int, int>();
        for (int i = 1; i <= 10000; i++)
        {
            int[] s = Lychrel(i);
            Dict.add(s[0], s[1]);
        }
        System.out.println("Lychrel: " + Dict.Where(a => a.Value == 1000).ToArray().Count());
    }

    public void Problema34()
    {
        List<Integer> Numeros = new List<Integer>();
        int i = 3, iMax = 100000;

        while (i <= iMax)
        {
            char[] j = i.toString().ToCharArray();
            BigInteger s_2 = 0, s_, s__;

            foreach (char c in j)
            {
                Boolean ok_s__ = BigInteger.TryParse(c.toString(), out s__);
                Boolean ok_s = BigInteger.TryParse(Factorial(s__).toString(), out s_);
                s_2 += s_;
            }
            if (i == s_2)
            {
                Numeros.add(i);
                System.out.println("  i : " + i);
            }
            i++;
        }
        System.out.println("Suma : " + Numeros.Sum());
    }
    
    public void Problema35()
    {
        List<Integer> Primos = GetPrimesUpTo(1000000);

        // Primos sin números pares en la cadena de texto
        Primos = Primos.Where(x => x.toString().Contains('0') == false && x.toString().Contains('4') == false
            && x.toString().Contains('6') == false && x.toString().Contains('8') == false).ToList();

        System.out.println("Primos ok!");
        List<Integer> CircularPrimes = new List<Integer>();

        int cont = 0;
        foreach (int c in Primos)
        {
            cont++;
            if (IsCircularPrime(c, Primos))
            {
                CircularPrimes.add(c);
                System.out.println("Primo Circular : " + c + " - " + cont + "/" + Primos.Count);
            }
        }

        System.out.println("Cuenta: " + CircularPrimes.Count());
    }*/

    public static void Problema008()
    {
        String BigNumber = "7316717653133062491922511967442657474235534919493496983520312774506326239578318016984801869478851843858615607891129494954595017379583319528532088055111254069874715852386305071569329096329522744304355766896648950445244523161731856403098711121722383113622298934233803081353362766142828064444866452387493035890729629049156044077239071381051585930796086670172427121883998797908792274921901699720888093776657273330010533678812202354218097512545405947522435258490771167055601360483958644670632441572215539753697817977846174064955149290862569321978468622482839722413756570560574902614079729686524145351004748216637048440319989000889524345065854122758866688116427171479924442928230863465674813919123162824586178664583591245665294765456828489128831426076900422421902267105562632111110937054421750694165896040807198403850962455444362981230987879927244284909188845801561660979191338754992005240636899125607176060588611646710940507754100225698315520005593572972571636269561882670428252483600823257530420752963450";

        int chunks = 5;
        int max = 0;
        int prod = 1;
        
        long start = System.currentTimeMillis();
        
        for (int c = 0; c < BigNumber.length() - (chunks - 1); c++)
        {
            prod = 1;
            for (int i = 0; i < chunks; i++) {
                prod *= Integer.parseInt(String.valueOf(BigNumber.toCharArray()[c + i]));
            }
            if (prod > max) { max = prod; }
        }
        long end = System.currentTimeMillis();
        
        System.out.println("MAX PROD: " + max);
        System.out.println("Time: " + (end - start));
    }

    public static void Problema011()
    {
        String a = "08 02 22 97 38 15 00 40 00 75 04 05 07 78 52 12 50 77 91 08" + "|";
        a += "49 49 99 40 17 81 18 57 60 87 17 40 98 43 69 48 04 56 62 00" + "|";
        a += "81 49 31 73 55 79 14 29 93 71 40 67 53 88 30 03 49 13 36 65" + "|";
        a += "52 70 95 23 04 60 11 42 69 24 68 56 01 32 56 71 37 02 36 91" + "|";
        a += "22 31 16 71 51 67 63 89 41 92 36 54 22 40 40 28 66 33 13 80" + "|";
        a += "24 47 32 60 99 03 45 02 44 75 33 53 78 36 84 20 35 17 12 50" + "|";
        a += "32 98 81 28 64 23 67 10 26 38 40 67 59 54 70 66 18 38 64 70" + "|";
        a += "67 26 20 68 02 62 12 20 95 63 94 39 63 08 40 91 66 49 94 21" + "|";
        a += "24 55 58 05 66 73 99 26 97 17 78 78 96 83 14 88 34 89 63 72" + "|";
        a += "21 36 23 09 75 00 76 44 20 45 35 14 00 61 33 97 34 31 33 95" + "|";
        a += "78 17 53 28 22 75 31 67 15 94 03 80 04 62 16 14 09 53 56 92" + "|";
        a += "16 39 05 42 96 35 31 47 55 58 88 24 00 17 54 24 36 29 85 57" + "|";
        a += "86 56 00 48 35 71 89 07 05 44 44 37 44 60 21 58 51 54 17 58" + "|";
        a += "19 80 81 68 05 94 47 69 28 73 92 13 86 52 17 77 04 89 55 40" + "|";
        a += "04 52 08 83 97 35 99 16 07 97 57 32 16 26 26 79 33 27 98 66" + "|";
        a += "88 36 68 87 57 62 20 72 03 46 33 67 46 55 12 32 63 93 53 69" + "|";
        a += "04 42 16 73 38 25 39 11 24 94 72 18 08 46 29 32 40 62 76 36" + "|";
        a += "20 69 36 41 72 30 23 88 34 62 99 69 82 67 59 85 74 04 36 16" + "|";
        a += "20 73 35 29 78 31 90 01 74 31 49 71 48 86 81 16 23 57 05 54" + "|";
        a += "01 70 54 71 83 51 54 69 16 92 33 48 61 43 52 01 89 19 67 48";

        String[] b = a.split("\\|", -1);
        int N = b.length;
        int chunks = 4;
        int[][] M = new int[N][N];

        for (int i = 0; i < N; i++)
        {
            String[] d = b[i].split(" ");
            for (int j = 0; j < d.length; j++)
            {
                M[i][j] = Integer.parseInt(d[j]);
            }
        }

        // POR FILAS
        int prod = 1;
        List<Integer> prods = new ArrayList<>();

        for (int r = 0; r < N; r++)
        {
            for (int c = r; c <= (N - chunks); c++)
            {
                for (int x = 0; x < chunks; x++) {  prod *= M[r][c + x];  }
                prods.add(prod);
                prod = 1;
            }
        }

        // POR COLUMNAS
        for (int c = 0; c < N; c++)
        {
            for (int r = c; r <= (N - chunks); r++)
            {
                for (int x = 0; x < chunks; x++) {  prod *= M[r + x][c];  }
                prods.add(prod);
                prod = 1;
            }
        }

        // DIAGONAL 1 -> \ (arriba hacia abajo)
        for (int r = 0; r <= (N - chunks); r++) 
        {
            for (int c = 0; c <= (N - chunks); c++)
            {
                for (int x = 0; x < chunks; x++) {  prod *= M[r + x][c + x]; }
                prods.add(prod);
                prod = 1;
            }
        }

        // DIAGONAL 2 -> / (arriba hacia abajo)
        for (int r = 0; r <= (N - chunks); r++)
        {
            for (int c = (N - 1); c >= (chunks - 1); c--)
            {
                for (int x = 0; x < chunks; x++) {  prod *= M[r + x][c - x]; }
                prods.add(prod);
                prod = 1;
            }
        }
        
        System.out.println("Maximo : " + Collections.max(prods));
    }

    /*
    public void Problema13()
    {
        List<String> N = new ArrayList<>();
        N.add("37107287533902102798797998220837590246510135740250");
        N.add("46376937677490009712648124896970078050417018260538");
        N.add("74324986199524741059474233309513058123726617309629");
        N.add("91942213363574161572522430563301811072406154908250");
        N.add("23067588207539346171171980310421047513778063246676");
        N.add("89261670696623633820136378418383684178734361726757");
        N.add("28112879812849979408065481931592621691275889832738");
        N.add("44274228917432520321923589422876796487670272189318");
        N.add("47451445736001306439091167216856844588711603153276");
        N.add("70386486105843025439939619828917593665686757934951");
        N.add("62176457141856560629502157223196586755079324193331");
        N.add("64906352462741904929101432445813822663347944758178");
        N.add("92575867718337217661963751590579239728245598838407");
        N.add("58203565325359399008402633568948830189458628227828");
        N.add("80181199384826282014278194139940567587151170094390");
        N.add("35398664372827112653829987240784473053190104293586");
        N.add("86515506006295864861532075273371959191420517255829");
        N.add("71693888707715466499115593487603532921714970056938");
        N.add("54370070576826684624621495650076471787294438377604");
        N.add("53282654108756828443191190634694037855217779295145");
        N.add("36123272525000296071075082563815656710885258350721");
        N.add("45876576172410976447339110607218265236877223636045");
        N.add("17423706905851860660448207621209813287860733969412");
        N.add("81142660418086830619328460811191061556940512689692");
        N.add("51934325451728388641918047049293215058642563049483");
        N.add("62467221648435076201727918039944693004732956340691");
        N.add("15732444386908125794514089057706229429197107928209");
        N.add("55037687525678773091862540744969844508330393682126");
        N.add("18336384825330154686196124348767681297534375946515");
        N.add("80386287592878490201521685554828717201219257766954");
        N.add("78182833757993103614740356856449095527097864797581");
        N.add("16726320100436897842553539920931837441497806860984");
        N.add("48403098129077791799088218795327364475675590848030");
        N.add("87086987551392711854517078544161852424320693150332");
        N.add("59959406895756536782107074926966537676326235447210");
        N.add("69793950679652694742597709739166693763042633987085");
        N.add("41052684708299085211399427365734116182760315001271");
        N.add("65378607361501080857009149939512557028198746004375");
        N.add("35829035317434717326932123578154982629742552737307");
        N.add("94953759765105305946966067683156574377167401875275");
        N.add("88902802571733229619176668713819931811048770190271");
        N.add("25267680276078003013678680992525463401061632866526");
        N.add("36270218540497705585629946580636237993140746255962");
        N.add("24074486908231174977792365466257246923322810917141");
        N.add("91430288197103288597806669760892938638285025333403");
        N.add("34413065578016127815921815005561868836468420090470");
        N.add("23053081172816430487623791969842487255036638784583");
        N.add("11487696932154902810424020138335124462181441773470");
        N.add("63783299490636259666498587618221225225512486764533");
        N.add("67720186971698544312419572409913959008952310058822");
        N.add("95548255300263520781532296796249481641953868218774");
        N.add("76085327132285723110424803456124867697064507995236");
        N.add("37774242535411291684276865538926205024910326572967");
        N.add("23701913275725675285653248258265463092207058596522");
        N.add("29798860272258331913126375147341994889534765745501");
        N.add("18495701454879288984856827726077713721403798879715");
        N.add("38298203783031473527721580348144513491373226651381");
        N.add("34829543829199918180278916522431027392251122869539");
        N.add("40957953066405232632538044100059654939159879593635");
        N.add("29746152185502371307642255121183693803580388584903");
        N.add("41698116222072977186158236678424689157993532961922");
        N.add("62467957194401269043877107275048102390895523597457");
        N.add("23189706772547915061505504953922979530901129967519");
        N.add("86188088225875314529584099251203829009407770775672");
        N.add("11306739708304724483816533873502340845647058077308");
        N.add("82959174767140363198008187129011875491310547126581");
        N.add("97623331044818386269515456334926366572897563400500");
        N.add("42846280183517070527831839425882145521227251250327");
        N.add("55121603546981200581762165212827652751691296897789");
        N.add("32238195734329339946437501907836945765883352399886");
        N.add("75506164965184775180738168837861091527357929701337");
        N.add("62177842752192623401942399639168044983993173312731");
        N.add("32924185707147349566916674687634660915035914677504");
        N.add("99518671430235219628894890102423325116913619626622");
        N.add("73267460800591547471830798392868535206946944540724");
        N.add("76841822524674417161514036427982273348055556214818");
        N.add("97142617910342598647204516893989422179826088076852");
        N.add("87783646182799346313767754307809363333018982642090");
        N.add("10848802521674670883215120185883543223812876952786");
        N.add("71329612474782464538636993009049310363619763878039");
        N.add("62184073572399794223406235393808339651327408011116");
        N.add("66627891981488087797941876876144230030984490851411");
        N.add("60661826293682836764744779239180335110989069790714");
        N.add("85786944089552990653640447425576083659976645795096");
        N.add("66024396409905389607120198219976047599490197230297");
        N.add("64913982680032973156037120041377903785566085089252");
        N.add("16730939319872750275468906903707539413042652315011");
        N.add("94809377245048795150954100921645863754710598436791");
        N.add("78639167021187492431995700641917969777599028300699");
        N.add("15368713711936614952811305876380278410754449733078");
        N.add("40789923115535562561142322423255033685442488917353");
        N.add("44889911501440648020369068063960672322193204149535");
        N.add("41503128880339536053299340368006977710650566631954");
        N.add("81234880673210146739058568557934581403627822703280");
        N.add("82616570773948327592232845941706525094512325230608");
        N.add("22918802058777319719839450180888072429661980811197");
        N.add("77158542502016545090413245809786882778948721859617");
        N.add("72107838435069186155435662884062257473692284509516");
        N.add("20849603980134001723930671666823555245252804609722");
        N.add("53503534226472524250874054075591789781264330331690");

        String[] M = new String[N.size()];
        M = N.toArray(M);
        Integer[][] Ent = new Integer[M.length][M[0].length()];

        for (int r = 0; r < M.length; r++)
        {
            for(int c = 0; c < M[r].length(); c++)
            {
                Ent[r][c] = Integer.parseInt(M[r][c]);
            }
        }

        Dictionary<int, int> Sumas = new Dictionary<int, int>();
        for (int c = 0; c < M[0].length(); c++)
        {
            int suma = 0;
            for (int r = 0; r < M.length; r++)
            {
                suma += Ent[r][c];
            }
            Sumas.add(c, suma);
        }

        // ARMAR LA SUMA ACUMULATIVA
        int f = Sumas.Count - 1;
        int s, rem;
        while (f > 0) {
            s = Sumas[f];
            rem = s / 10;
            Sumas[f] = Sumas[f] % 10;
            Sumas[f - 1] = Sumas[f - 1] + rem;
            f--;
        }

        String y = "";
        BigInteger output;
        Boolean ok_output;
        for (int i = 0; i < Sumas.Count; i++)
        {
            y += Sumas[i].toString();
        }
        ok_output = BigInteger.TryParse(y, out output);

        System.out.println("salida : " + output.toString().SubString(0, 10));
    }*/

    public static void Problema014()
    {
        Map<Integer, Integer> Collatz = new HashMap<>();
        int count;
        long f;

        //      1 - 1000000: 524 (Key => 837799)
        for (int i = 1; i <= 1000000; i++)
        {
            count = 0;
            f = i;
            do {
                if (f % 2 != 0) { f = 3 * f + 1; }
                else {  f = f / 2; }
                count++;
            } while (f != 1);

            Collatz.put(i, count);
            System.out.println(" - i : " + i + " -> " + count);
        }

        System.out.println("Maximum : " + Collections.max(Collatz.keySet()));
    }
/*
    public void Problema16()
    {
        BigInteger sum = 0, prod = 1;

        for (int i = 1; i <= 1000; i++) {  prod *= 2;  }

        // Algoritmo para sumar dígitos
        while (prod != 0)
        {
            sum += prod % 10; // SACA EL RESTO
            prod /= 10;       // ASIGNA EL PROD DIVIDIDO POR 10 A PROD
        }

        System.out.println("Sum :" + sum);
    }

    public void Problema19()
    {        
        Date startDate = new Date(1901, 1, 1);
        Date endDate = new Date(2000, 12, 31);
        int count = 0;
        for (Date date = startDate; date <= endDate; date = date.addDays(1))
        {
            if (date.DayOfWeek == DayOfWeek.Sunday && date.Day == 1) {
                count++;
                date = date.addDays(6);
            }
        }
        System.out.println("Count : " + count);
    }*/

    public static void Problema025()
    {
        BigInteger s = BigInteger.valueOf(0);
        int count = 2;
        BigInteger[] fibs = { BigInteger.valueOf(1), BigInteger.valueOf(1) };
        int MaxSize = 1000;

        while (s.toString().length() != MaxSize)
        {
            count++;
            s = fibs[0].add(fibs[1]);
            fibs[0] = fibs[1];
            fibs[1] = s;
        }
        System.out.println("First : " + count);
    }

    public static void Problema017()
    {
        Map<Integer, String> numbers = new HashMap<Integer, String>();
        String And = "and";
        String Hundred = "hundred";
        String Thousand = "onethousand";
        numbers.put(1, "one");
        numbers.put(2, "two");
        numbers.put(3, "three");
        numbers.put(4, "four");
        numbers.put(5, "five");
        numbers.put(6, "six");
        numbers.put(7, "seven");
        numbers.put(8, "eight");
        numbers.put(9, "nine");
        numbers.put(10, "ten");
        numbers.put(11, "eleven");
        numbers.put(12, "twelve");
        numbers.put(13, "thirteen");
        numbers.put(14, "fourteen");
        numbers.put(15, "fifteen");
        numbers.put(16, "sixteen");
        numbers.put(17, "seventeen");
        numbers.put(18, "eighteen");
        numbers.put(19, "nineteen");

        Map<Integer, String> Tens = new HashMap<Integer, String>();
        Tens.put(2, "twenty");
        Tens.put(3, "thirty");
        Tens.put(4, "forty");
        Tens.put(5, "fifty");
        Tens.put(6, "sixty");
        Tens.put(7, "seventy");
        Tens.put(8, "eighty");
        Tens.put(9, "ninety");

        int c = 0;            
        String dig = "";
        String dec = "";
        String cent = "";

        for (int i = 1; i < 1000; i++)
        {
            if (i % 100 == 0) {
                for (int j = 0; j < numbers.keySet().size(); j++)
                {
                    Object key = numbers.keySet().toArray()[j];
                    if ((Integer)key == i / 100)
                    {
                        cent = numbers.get(key);
                        break;
                    }
                }
                
                dig = cent + Hundred;
                c += dig.length();
                dig = "";
            }                

            if (i % 100 >= 1 && i % 100 <= 19)
            {
                if (i >= 100) {
                    if (i % 100 == 0) { dig = cent + Hundred; }
                    if (i % 100 != 0) { dig = cent + Hundred + And; }
                }
                
                for (int j = 0; j < numbers.keySet().size(); j++)
                {
                    Object key = numbers.keySet().toArray()[j];
                    if ((Integer)key == i % 100)
                    {
                        dig += numbers.get(key);
                        break;
                    }
                }

                c += dig.length();
                dig = "";
            }
            else if (i % 100 >= 20 && i % 100 <= 99) {
                if (i >= 100)
                {
                    if (i % 100 == 0) { dig = cent + Hundred; }
                    if (i % 100 != 0) { dig = cent + Hundred + And; }
                }

                if (i % 10 != 0)
                {
                    for (int j = 0; j < numbers.keySet().size(); j++)
                    {
                        Object key = numbers.keySet().toArray()[j];
                        if ((Integer)key == i % 10)
                        {
                            dig += numbers.get(key);
                            break;
                        }
                    }
                    
                    c += dig.length();
                    dig = "";
                }
                else {
                    for (int j = 0; j < numbers.keySet().size(); j++)
                    {
                        Object key = Tens.keySet().toArray()[j];
                        if ((Integer)key == (i % 100) / 10)
                        {
                            dec =  numbers.get(key);
                            break;
                        }
                    }
                    
                    dig += dec;
                    c += dig.length();
                    dig = "";
                }
            }
        }

        c += Thousand.length();
        System.out.println("Total : " + c);
    }

    public static void Problema020()
    {
        BigInteger x = Functions.Factorial(BigInteger.valueOf(100));
        // System.out.println("Number : " + x);
        String s = x.toString();
        // System.out.println("Sum    : " + s.Aggregate(0, (a, b) => a += Integer.parseInt(b.toString())));
    }

    public static void Problema021(int Maximum)
    {
        int i0 = 1, i1 = Maximum;
        List<Integer> amicables = new ArrayList<>();
        for (int i = i0; i <= i1; i++)
        {
            int sumDiv1 = Suma(Divisors(i));
            int sumDiv2 = Suma(Divisors(sumDiv1));

            if (i == sumDiv2 && sumDiv1 != sumDiv2)
            {
                amicables.add(i);
                amicables.add(sumDiv1);
            }
        }
        
        System.out.println("Sum : " + Suma(amicables.stream().distinct().collect(Collectors.toList())));
    }
    
    /*
    public void Problema22()
    {
        String IN_FILE = "names.txt";
        String path = "C:/Users/Felipe/Dropbox/Google/CodeJam2013/";
        String fileName = path + IN_FILE;
        FileReader sr;
        Map<Integer, Integer> scores = new HashMap<Integer, Integer>();
        int i = 1;
        
        File f = new File(fileName);

        if (f.exists())
        {
            try
            {
                sr = new FileReader(fileName, Charset.defaultCharset());
                String[] names_raw_1 = sr.ReadLine().Split(',', '\\');
                List<String> names_raw_2 = new List<String>();

                foreach (String s in names_raw_1)
                {
                    names_raw_2.add(s.TrimEnd('"').TrimStart('"'));
                }

                names_raw_2.Sort();

                foreach (String s in names_raw_2) 
                {
                    scores.add(i, i * GetNameScore(s));
                    i++;
                }
            }
            catch (Exception e)
            {
                System.out.println("Error : " + e.GetBaseException());
            }

            System.out.println("Score : " + scores.Values.Sum());
        }
    }

    public int GetNameScore(String s)
    {
        int score = s.ToCharArray().Aggregate(0, (a, b) => a += (char)b - 64);
        return score;
    }

    public void Problema230()
    {
        String A, B;
        A = "1415926535897932384626433832795028841971693993751058209749445923078164062862089986280348253421170679";
        B = "8214808651328230664709384460955058223172535940812848111745028410270193852110555964462294895493038196";

        Map<Integer, BigInteger> Fibs_L = new HashMap<Integer, BigInteger>();
        int s = 0;
        String EvenPattern = "BABAB", OddPattern = "ABBABBABABBABBABABBAB";

        int i0 = 0, iMax = 17;
        for (int i = i0; i <= 5*iMax; i++)
        {
            BigInteger ff = Fibonacci(BigInteger.valueOf(i+1));
            Fibs_L.put(i+1, ff.multiply(BigInteger.valueOf(A.length())));
        }

        for (int i = i0; i <= iMax; i++)
        {
            BigInteger c = BigInteger.valueOf((127 + 19 * i) * Math.pow(7, i));

            var Key = Fibs_L.Where(x => x.Value >= c).First().Key;

            String Fibs_S = "";
            String[] f = { "A", "B" };

            Fibs_S = f.Aggregate("", (current, next) => current += next).toString();

            var indice = c % 100 == 0 ? BigInteger.Divide(c, (BigInteger)100) : BigInteger.Divide(c, (BigInteger)100) + 1;

            // var posAB = (Key % 2 == 0) ? (indice - 1) % EvenPattern.length : (indice - 1) % OddPattern.length;
            // var AB = (Key % 2 == 0) ? EvenPattern[(int)posAB] : OddPattern[(int)posAB];
            var AB = Fibs_S.SubString((int)(indice - 1), 1);
            var Position = c % 100 == 0 ? (int)(100) : (int)(c % 100);
            var Final = (AB.CompareTo("A") == 0) ? Int32.Parse(A[Position - 1].toString()) : Int32.Parse(B[Position - 1].toString());  // 0-based

            s += Final;

            System.out.println("i = " + i + " - c: " + c + " [Key = " + Key + "]");
            System.out.println("  -> Position : " + Position + " - AB: " + AB + " - Final: " + Final);
            System.out.println("  -> Sum      : " + s);
        }

        System.out.println("Sum : " + s);
    }

    public static void MagicTrick(GCJProblem cjp) throws IOException
    {
        // Definir sólo variables específicas del problema
        int gridRows = 4;
        int answerOne, answerTwo;

        String[] RowCardsOne = new String[gridRows];
        String[] RowCardsTwo = new String[gridRows];

        for (int iT = 0; iT < cjp.getT(); iT++)
        {
            answerOne = Integer.parseInt(cjp.getCases().get(iT).get(0));
            RowCardsOne = cjp.getCases().get(iT).get(answerOne).split(" ");

            // System.out.println("Answer 1: " + answerOne);
            // System.out.println("--- Elements in RowCardsOne ---");
            // foreach (var x in RowCardsOne) { System.out.println(x); };

            // Answer Two
            answerTwo = Integer.parseInt(cjp.getCases().get(iT).get(gridRows + 1));
            RowCardsTwo = cjp.getCases().get(iT).get(gridRows + 1 + answerTwo).split(" ");

            // System.out.println("Answer 2: " + answerTwo);
            // System.out.println("--- Elements in RowCardsTwo ---");
            // foreach (var x in RowCardsTwo) { System.out.println(x); };

            // String[] results = intersection(RowCardsOne, RowCardsTwo);
            // ( RowCardsOne.Intersect(RowCardsTwo).ToList();

            // OUTPUT
            String answer = (results.Count == 1) ? results[0] : (results.Count > 1) ? "Bad magician!" : "Volunteer cheated!";
            cjp.getPw().write("Case #" + (iT+1) + ": " + answer);
            System.out.println("Case #" + (iT+1) + ": " + answer);
        }
        // cjp.CloseWriter();
    } */

    public static void CookieClicker()
    {
        String path = "C:/Users/Felipe/Dropbox/Google/CodeJam2014/CookieClicker/";
        String IN_FILE = "B-large-practice.in"; // "B-small-attempt1.in";
        String OUT_FILE = "OUT_" + IN_FILE.split(".")[0] + ".txt";
        String fileName = path + IN_FILE;
        String fileOut = path + OUT_FILE;
        int T;
        BufferedReader sr;
        FileWriter sw;

        // PROBLEM SPECIFICS
        double C, F, X;
        double r = 2;
        double time = 0;
        
        File fff = new File(fileName);

        if (fff.exists())
        {
            try
            {
                sr = new BufferedReader(new FileReader(fff));
                sw = new FileWriter(fileOut);
                // NUMERO DE CASOS                    
                T = Integer.parseInt(sr.readLine());

                // LEO LAS LINEAS DEL ARCHIVO                    
                for (int iT = 0; iT < T; iT++)
                {
                    // C, F, X
                    String[] Values = sr.readLine().split(" ");
                    C = Double.parseDouble(Values[0]);
                    F = Double.parseDouble(Values[1]);
                    X = Double.parseDouble(Values[2]);

                    if (C >= X) { time = X / r; }
                    else {
                        int nFarms = 0;
                        double CurrentTime = X / r, NextTime = 0, DifT;
                        time = 0;

                        int nFarmsMax = 10000000; //(int) (100 * Math.Ceiling(C / r));
                        List<Double> TimesN = new ArrayList<>(nFarmsMax);
                        for (int n = 0; n <= nFarmsMax; n++)
                        {
                            TimesN.add(GetTimeNewFarm(C, F, r, n));
                        }

                        do
                        {
                            if (nFarms > 0) { CurrentTime = NextTime; }
                            for (int f = 0; f <= nFarms; f++) { time += TimesN.get(f); }
                            time += GetTimeNewFarm(X, F, r, nFarms + 1);
                            nFarms++;
                            NextTime = time;
                            time = 0;

                            DifT = Math.abs((NextTime - CurrentTime) / CurrentTime);

                            System.out.println("nFarms: " + nFarms + " - NextTime: " + NextTime);
                        } while (NextTime < CurrentTime || DifT < 1e-4);

                        time = CurrentTime;
                    }

                    // OUTPUT
                    String answer = String.format(Double.valueOf(time).toString().replace(",", "."), "%1$.7f");
                    sw.write("Case #" + (iT+1) + ": " + answer + "\n");
                    sw.flush();
                    System.out.println("Case #" + (iT+1) + ": " + answer + "\n");
                }
                sw.close();
            }
            catch (IOException e)
            {
                System.out.println(e.getMessage());
                // throw new EndOfStreamException("NO PUDE LEER EL ARCHIVO");
            }
        }
    }
    public static double GetTimeNewFarm(double C, double F, double r, int UpToN)
    {
        return (C / (r + UpToN * F));
    }
/*
    public static void MinesMaster(GCJProblem cjp)
    {
        String path = "C:/Users/Felipe/Dropbox/Google/CodeJam2014/MinesMaster/";
        String IN_FILE = "C-small-practice.in";
        String OUT_FILE = "OUT_" + IN_FILE.split(".")[0] + ".txt";
        String fileName = path + IN_FILE;
        String fileOut = path + OUT_FILE;
        int T;
        BufferedReader sr;
        FileWriter sw;

        // PROBLEM SPECIFICS
        int C, R, M;
        String Mine = "*", Hidden = ".", Click = "c";

        File fff = new File(fileName);
        
        if (fff.exists())
        {
            try
            {
                sr = new BufferedReader(new FileReader(fff));
                sw = new FileWriter(fileOut);
                // NUMERO DE CASOS
                cjp.setT(Integer.parseInt(sr.readLine()));
                // T = Int32.Parse(sr.ReadLine());

                // LEO LAS LINEAS DEL ARCHIVO                    
                for (int iT = 0; iT < cjp.getT(); iT++)
                {
                    // R, C, M
                    String[] Values = sr.readLine().split(" ");
                    R = Integer.parseInt(Values[0]);
                    C = Integer.parseInt(Values[1]);
                    M = Integer.parseInt(Values[2]);

                    int Win = 0;
                    BigInteger Diferencia = BigInteger.valueOf(R * C - M);
                    Boolean EsPar = Diferencia.mod(BigInteger.valueOf(2)).equals(BigInteger.ZERO);
                    double RaizDif = Math.sqrt(Diferencia);
                    Boolean EsCuadrado = RaizDif == (int)RaizDif;

                    if (Diferencia == 1) { Win = 1; }
                    else {
                        if (R == 1 && C == 1) {  Win = 1;  }
                        if ((R == 1 || C == 1) && (Diferencia >= 2)) { Win = 1; }
                        if (R > 1 && C > 1 && (EsPar || EsCuadrado)) { Win = 1; }    // Espacios libres par o cuadrado
                    }

                    String lineOut = "";
                    if (Win == 0) { 
                        lineOut = "Impossible";
                        cjp.sw.WriteLine("Case #{0}: ", (iT + 1).toString());
                        cjp.sw.WriteLine(lineOut);

                        System.out.println("Case #{0}: ", (iT + 1).toString());
                        System.out.println(lineOut);
                    }
                    else
                    {

                        if (R == 1 && C == 1)
                        {
                            lineOut = Click;
                            sw.WriteLine("Case #{0}: ", (iT + 1).toString());
                            sw.WriteLine(lineOut);

                            System.out.println("Case #{0}: ", (iT + 1).toString());
                            System.out.println(lineOut);
                        }

                        if ((R == 1 || C == 1) && (Diferencia >= 2))
                        {
                            if (R == 1)
                            {
                                for (int iCol = 0; iCol < C; iCol++)
                                {
                                    if (iCol < M) {  lineOut += Mine;  }
                                    else {
                                        if (iCol == C - 1) { lineOut += Click; }
                                        else { lineOut += Hidden; }
                                    }
                                }

                                sw.WriteLine("Case #{0}: ", (iT + 1).toString());
                                sw.WriteLine(lineOut);

                                System.out.println("Case #{0}: ", (iT + 1).toString());
                                System.out.println(lineOut);
                            }

                            if (C == 1)
                            {
                                for (int iRow = 0; iRow < R; iRow++)
                                {
                                    if (iRow < M) { lineOut += Mine + Environment.NewLine; }
                                    else {
                                        if (iRow == R - 1) { lineOut += Click; }
                                        else { lineOut += Hidden + Environment.NewLine; }
                                    }
                                }

                                sw.WriteLine("Case #{0}: ", (iT + 1).toString());
                                sw.WriteLine(lineOut);

                                System.out.println("Case #{0}: ", (iT + 1).toString());
                                System.out.println(lineOut);
                            }                                
                        }

                        if (R > 1 && C > 1 && (EsPar || EsCuadrado))
                        {
                            // HEADER
                            lineOut = "";
                            sw.WriteLine("Case #{0}: ", (iT + 1).toString());
                            System.out.println("Case #{0}: ", (iT + 1).toString());

                            int iMine = 0;
                            String[,] Lines = new String[R, C];
                            // Inicializar todo en mina
                            for (int iRow = 0; iRow < R; iRow++) {
                                for (int iCol = 0; iCol < C; iCol++) {  Lines[iRow, iCol] = Mine;  }
                            }

                            int Lado = 0;
                            List<Integer> Divisores = new List<Integer>();
                            List<Boolean> Condiciones = new List<Boolean>();

                            if (EsCuadrado) { Lado = (int)Math.Sqrt((double)Diferencia); }
                            else
                            {
                                Divisores = Functions.Divisors((int)Diferencia).Where(x => x > 1).ToList();
                                Condiciones = Divisores.Select(x => x <= Math.Max(R, C)).ToList();

                                List<Integer> idxs = new List<Integer>();
                                for (int r = 0; r < Condiciones.Count; r++)
                                {
                                    if (Condiciones[r] == false) { idxs.add(r); idxs.add(Condiciones.Count - 1 - r); }
                                }

                                List<Integer> DivElim = new List<Integer>();
                                for (int r = 0; r < idxs.Count; r++) { DivElim.add(Divisores[idxs[r]]); }
                                foreach(int d in DivElim) { Divisores.Remove(d); }
                            }

                            if (EsCuadrado)
                            {
                                for (int j = 0; j < Lado && j < R; j++)
                                {
                                    for (int k = 0; k < Lado && k < C; k++)
                                    {
                                        Lines[j, k] = Hidden;
                                    }
                                }
                                Lines[0, 0] = Click;
                            }
                            else
                            {
                                if (Divisores.Count == 0)
                                {
                                    List<Integer> Divisores_ = new List<Integer>();
                                    List<Boolean> Condiciones_ = new List<Boolean>();

                                    Divisores_ = Functions.Divisors((int)M).Where(x => x > 1).ToList();
                                    Condiciones_ = Divisores_.Select(x => x <= Math.Max(R, C)).ToList();

                                    List<Integer> idxs = new List<Integer>();
                                    for (int r = 0; r < Condiciones_.Count; r++)
                                    {
                                        if (Condiciones_[r] == false) { idxs.add(r); idxs.add(Condiciones_.Count - 1 - r); }
                                    }

                                    List<Integer> DivElim = new List<Integer>();
                                    for (int r = 0; r < idxs.Count; r++) { DivElim.add(Divisores_[idxs[r]]); }
                                    foreach (int d in DivElim) { Divisores_.Remove(d); }

                                    int minD_ = Divisores_.Min();
                                    int maxD_ = Divisores_.Max();

                                    if (R < C)
                                    {
                                        for (int r = R - 1; r > 0 && r > minD_ - 1; r--)
                                        {
                                            for (int c = C - 1; c > 0 && c > maxD_ - 1; c--)
                                            {
                                                Lines[r, c] = Mine;
                                            }
                                        }
                                    }
                                    if (R > C)
                                    {

                                    }

                                    for (int r = 0; r < R; r++)
                                    {
                                        for (int c = 0; c < C; c++)
                                        {
                                            if (!(r == R - 1 && c == C - 1)) { Lines[r, c] = Hidden; }
                                        }
                                    }

                                    Lines[0, 0] = Click;
                                }
                                else
                                {
                                    int minD = Divisores.Min();
                                    int maxD = Divisores.Max();

                                    if (R < C)
                                    {
                                        for (int j = 0; j < minD && j < R; j++)
                                        {
                                            for (int k = 0; k < maxD && k < C; k++)
                                            {
                                                Lines[j, k] = Hidden;
                                            }
                                        }
                                    }
                                    if (R > C)
                                    {
                                        for (int k = 0; k < minD && k < C; k++)
                                        {
                                            for (int j = 0; j < maxD && j < R; j++)
                                            {
                                                Lines[j, k] = Hidden;
                                            }
                                        }
                                    }
                                }
                                Lines[0, 0] = Click;
                            }

                            // Imprimir el arreglo de Strings
                            lineOut = PrintLines(Lines, R, C);

                            sw.WriteLine(lineOut);
                            System.out.println(lineOut);
                        }
                    }     

                }
                sw.Close();
            }
            catch (EndOfStreamException e)
            {
                System.out.println(e.GetBaseException());
            }
        }
    }*/
    
    public static String PrintLines(String[][] Lines, int R, int C)
    {
        // Imprimir el arreglo de Strings
        String lineOut = "";
        for (int iRow = 0; iRow < R; iRow++)
        {
            for (int iCol = 0; iCol < C; iCol++)
            {
                lineOut += Lines[iRow][iCol];
                if (iCol == C - 1) { lineOut += "\n"; }
            }
        }

        return lineOut;
    }

    /* GCJ2014 - QR - DeceitfulWar */
    /*
    public static void DeceitfulWar(GCJProblem cjp)
    {
        // Definir variables específicas del problema

        for (int iT = 0; iT < cjp.getT(); iT++)
        {
            // N
            int N = Integer.parseInt(cjp.getCases().get(iT).get(0));

            List<Double> NaomiW = new ArrayList<Double>(N);
            List<Double> KenW = new ArrayList<Double>(N);

            NaomiW = cjp.getCases().get(IT).get(1).split(" ").Select(s => Double.Parse(s, CultureInfo.InvariantCulture)).ToList();
            KenW = cjp.Cases[iT][2].Split(' ').Select(s => Double.Parse(s, CultureInfo.InvariantCulture)).ToList();

            List<double> NaomiDW = new List<double>(NaomiW);
            List<double> KenDW = new List<double>(KenW);

            int PointsNaomiWar = PlayWar(NaomiW, KenW, N);
            int PointsNaomiDWar;
            if (PointsNaomiWar == N) { PointsNaomiDWar = N; }
            else { PointsNaomiDWar = PlayDWar(NaomiDW, KenDW, N); }

            System.out.println("Case #{0}: " + PointsNaomiDWar + " " + PointsNaomiWar, (iT + 1).toString());
            cjp.sw.WriteLine("Case #{0}: " + PointsNaomiDWar + " " + PointsNaomiWar, (iT + 1).toString());
        }
        cjp.CloseWriter();
    }
    public static int PlayWar(List<double> Naomi, List<double> Ken, int N)
    {
        int PointsNaomi = 0;
        double NaomiStick;

        if (Naomi.Min() > Ken.Max()) { PointsNaomi = N; }
        else
        {
            for (int Turn = 0; Turn < N; Turn++)
            {
                NaomiStick = Naomi.Max();
                PointsNaomi += (WarWinner(NaomiStick, Ken) == "N") ? 1 : 0;

                Naomi.Remove(NaomiStick);
                Ken.Remove(ChosenByKen(NaomiStick, Ken));
            }
        }
        return PointsNaomi;
    }
    public static int PlayDWar(List<double> Naomi, List<double> Ken, int N)
    {
        int PointsNaomi = 0, Mult = 1;
        double NaomiStick, NaomiSays, Dif = 1e-3, KenMin;
        List<double> KenCandidates = new List<double>();

        for (int Turn = 0; Turn < N; Turn++)
        {
            NaomiStick = Naomi.Min();

            KenMin = Ken.Min();
            if (NaomiStick < KenMin) { Mult = -1; }
            else { Mult = 1; }

            NaomiSays = Ken.Max() + Mult * Dif;
            PointsNaomi += (WarWinner(NaomiSays, Ken) == "N") ? 1 : 0;

            Naomi.Remove(NaomiStick);
            Ken.Remove(ChosenByKen(NaomiSays, Ken));
        }
        return PointsNaomi;
    }
    public static String WarWinner(double NaomiStick, List<double> Ken)
    {
        var KenCandidates = Ken.Where(x => x > NaomiStick).ToList();
        return (KenCandidates.Count == 0) ? "N" : "K";
    }
    public static double ChosenByKen(double NaomiStick, List<double> Ken)
    {
        var KenCandidates = Ken.Where(x => x > NaomiStick).ToList();
        return (KenCandidates.Count > 0) ? KenCandidates.Min() : Ken.Min();
    }
    */
    /* GCJ2014 - QR - DeceitfulWar */

    /* GCJ2014 - 1A - ProperShuffle */
    public static void ProperShuffle(GCJProblem cjp) throws IOException
    {
        // Definir variables específicas del problema
        int N;
        String Method = "";

        int Test = 10000;
        List<Integer> x = new ArrayList<>();
        List<Integer> y = new ArrayList<>();
        for (int t = 0; t < Test; t++)
        {
            int[] X = GoodMethod(1000);
            int[] Y = BadMethod(1000);
            x.add(CuentaMayoresQuePosicion(X));
            y.add(CuentaMayoresQuePosicion(Y));
        }

        double x_ = Promedio(x), y_ = Promedio(y), Goal = (x_ + y_) / 2;

        for (int iT = 0; iT < cjp.getT(); iT++)
        {
            N = Integer.parseInt(cjp.getCases().get(iT).get(0));
            
            String[] params = cjp.getCases().get(iT).get(1).split(" ");
            int[] Enteros = new int[params.length];
            for(int i = 0; i < params.length; i++) {
                Enteros[i] = Integer.parseInt(params[i]);
            }

            int n = CuentaMayoresQuePosicion(Enteros);

            Method = (n >= Goal) ? "BAD" : "GOOD";

            System.out.println("Case #" + (iT+1) + ": " + Method);
            cjp.getPw().write("Case #" + (iT+1) + ": " + Method);
            cjp.getPw().flush();
        }
        cjp.CloseWriter();
    }
    
    public static int[] GoodMethod(int N)
    {
        int[] A = new int[N];
        for (int i = 0; i < N; i++) { A[i] = i; }
        Random r = new Random();
        int p;
        for (int i = 0; i < N; i++) { p = r.nextInt(N-1-i) + i; int tmp = A[i]; A[i] = A[p]; A[p] = tmp; }
        return A;
    }
    public static int[] BadMethod(int N)
    {
        int[] A = new int[N];
        for (int i = 0; i < N; i++) { A[i] = i; }
        Random r = new Random();
        int p;
        for (int i = 0; i < N; i++) { p = r.nextInt(N-1); int tmp = A[i]; A[i] = A[p]; A[p] = tmp; }
        return A;
    }
    public static int CuentaMayoresQuePosicion(int[] A)
    {
        int n = 0;
        for (int i = 0; i < A.length; i++) { if (A[i] >= i) { n++; } }
        return n;
    }
    /* GCJ2014 - 1A - ProperShuffle */

    /* GCJ2014 - 1A - Charging Chaos */
    /*
    public static void ChargingChaos(GCJProblem cjp)
    {
        // Definir variables específicas del problema
        int N, L, nMoves = 0;
        String answer = "";

        for (int iT = 0; iT < cjp.getT(); iT++)
        {
            String[] params = cjp.getCases().get(iT).get(0).split(" ");
            
            N = Integer.parseInt(params[0]);
            L = Integer.parseInt(params[1]);
            
            var InitialFlows = cjp.Cases[iT][1].Split(' ').ToList();
            var RequiredFlow = cjp.Cases[iT][2].Split(' ').ToList();

            InitialFlows.Sort();
            RequiredFlow.Sort();

            List<String> Flows = InitialFlows;

            if (!Enumerable.SequenceEqual(Flows, RequiredFlow))
            {
                // Switch
                var P = CreatePermutations(L);
                P.Sort();

                for (int s = 0; s < P.Count; s++)
                {
                    String SwitchCombination = P[s];
                    nMoves = SwitchCombination.ToCharArray().Select(t => int.Parse(t.toString())).Sum();

                    var Indices = SwitchCombination.ToCharArray().Select((v, i) => new { v, i })
                                .Where(x => x.v == '1')
                                .Select(x => x.i).ToList();

                    int ok = 0;
                    foreach (int idx in Indices)
                    {
                        Flows = Switch(Flows, idx);
                        Flows.Sort();
                        if (Enumerable.SequenceEqual(Flows, RequiredFlow)) { ok = 1; }
                    }

                    Flows = InitialFlows;
                }
                answer = (nMoves >= L) ? "NOT POSSIBLE" : nMoves.toString();

                System.out.println("Case #{0}: " + answer, (iT + 1).toString());
                cjp.sw.WriteLine("Case #{0}: " + answer, (iT + 1).toString());
            }
            else
            {
                System.out.println("Case #{0}: " + nMoves, (iT + 1).toString());
                cjp.sw.WriteLine("Case #{0}: " + nMoves, (iT + 1).toString());
            }
        }
        cjp.CloseWriter();
    }
    public static List<String> Switch(List<String> Flows, int l)
    {
        List<String> F = new List<String>();
        foreach (String f in Flows) { F.add(SwitchFlow(f, l)); }
        return F;
    }
    public static String SwitchFlow(String Flow, int l)
    {
        String F = "";
        for (int i = 0; i < Flow.length; i++) { if (i == l) { F += SwitchChar(Flow[i]); } else { F += Flow[i]; } }
        return F;
    }
    public static char SwitchChar(char f)
    {
        return f == '0' ? '1' : '0';
    }
    public static List<String> OrderString(List<String> y)
    {
        List<String> ny = new List<String>();
        foreach (String sy in y) { ny.add(String.Concat(sy.OrderBy(r => r))); }
        return ny;
    }
    public static List<String> PermuteN(String prefix, int len)
    {
        List<String> s = new List<String>();
        if (len == 0) { s.add(prefix); }
        else
        {
            var x = PermuteN(prefix + "0", len - 1);
            var y = PermuteN(prefix + "1", len - 1);
            foreach (String sx in x) { s.add(sx); }
            foreach (String sy in y) { s.add(sy); }
        }
        return s;
    }
    public static List<String> CreatePermutations(int n)
    {
        return PermuteN("", n);
    }*/
    /* GCJ2014 - 1A - Charging Chaos */
}

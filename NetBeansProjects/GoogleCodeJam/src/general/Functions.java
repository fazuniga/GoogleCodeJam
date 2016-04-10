package general;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;
import java.util.stream.*;

/* @author fazuniga */
public class Functions
{
    public static double Euclidean(double a, double b, double c, double d)
    {
        return Math.sqrt(Math.pow(c - a, 2) + Math.pow(d - b, 2));
    }

    public static List<Integer> Divisors(int n)
    {
        List<Integer> d = new ArrayList<>();
        d.add(1);
        int divisor = 2;
        do {
            if (n % divisor == 0) { d.add(divisor); }
            divisor++;
        } while (divisor < n);

        return d;
    }
    public static List<Long> DivisorsL(long n)
    {
        List<Long> d = new ArrayList<>();
        d.add((long)1);
        long divisor = 2;
        do {
            if (n % divisor == 0) { d.add(divisor); }
            divisor++;
        } while (divisor <= n);

        return d;
    }
    public static List<Long> DivisorsLimit(long n, int limit)
    {
        List<Long> d = new ArrayList<>();
        d.add((long)1);
        long divisor = 2;
        do {
            if (n % divisor == 0) { d.add(divisor); }
            divisor++;
            
            if (d.size() == limit) { break; }
        } while (divisor <= n);

        return d;
    }
    
    public static Boolean IsEven(BigInteger x) {
        return x.mod(BigInteger.valueOf(2)).equals(BigInteger.ZERO);
    }
    
    public static Boolean IsOdd(BigInteger x) {
        return !IsEven(x);
    }

    /*
    public static Boolean IsSquare(BigInteger num)
    {
        BigInteger result;
        result = pow(num, (BigInteger) 1 / 2);
        Boolean isSquare = result % 1 == 0;
        return isSquare;
    }
    
    public static BigInteger pow(BigInteger base, BigInteger exponent)
    {
        BigInteger result = BigInteger.ONE;
        while (exponent.signum() > 0) {
          if (exponent.testBit(0)) result = result.multiply(base);
          base = base.multiply(base);
          exponent = exponent.shiftRight(1);
        }
        return result;
    } */

    public Boolean IsPalindrome(BigInteger numberToCheck)
    {
        String a_ = numberToCheck.toString();
        String b_ = Reversa(a_);
        Boolean ok = a_.equals(b_);
        return ok;
    }

    public static BigInteger RaizC(BigInteger N)
    {
        BigInteger rootN = N;
        int count = 0;
        int bitlength = 1; // There is a bug in finding bit length hence we start with 1 not 0
        while (rootN.divide(BigInteger.valueOf(2)) != BigInteger.ZERO)
        {
            rootN = rootN.divide(BigInteger.valueOf(2));
            bitlength++;
        }
        bitlength = (bitlength + 1) / 2;
        rootN = N.shiftRight(bitlength);
        BigInteger o = BigInteger.valueOf(1);

        BigInteger lastRoot = BigInteger.ZERO;
        do
        {
            if (lastRoot.compareTo(rootN) > 0) // lastRoot > rootN
            {
                if (count++ > 1000)                   // Work around for the bug where it gets into an infinite loop
                {
                    return rootN;
                }
            }
            lastRoot = rootN;
            try
            {
                rootN = N.divide(rootN.add(o)).add(rootN).shiftRight(1);
                // rootN = (BigInteger.divide(N, rootN + o) + rootN) >> 1;
            }
            catch (Exception e)
            {
                System.out.println("Error!!: " + e.getMessage());
            }
        }
        while (!((rootN.modPow(lastRoot, BigInteger.ZERO)).toString().equals("0")));
        return rootN;
    } // SqRtN

    public List<Integer> PrimeFactors(long num)
    {
        List<Integer> factors = new ArrayList<>();
        Boolean alreadyCounted = false;
        while (num % 2 == 0) {
            if (alreadyCounted == false) {
                factors.add(2);
                alreadyCounted = true;
            }
            num = num / 2;
        }

        int divisor = 3;
        alreadyCounted = false;
        while (divisor <= num)
        {
            if (num % divisor == 0) {
                if (alreadyCounted == false) {
                    factors.add(divisor);
                    alreadyCounted = true;
                }
                num /= divisor;
            }
            else {
                alreadyCounted = false;
                divisor += 2;
            }
        }
        return factors;
    }

    public static String Reversa(String a_)
    {
        return new StringBuilder(a_).reverse().toString();    
    }
    public static List<String> ReverseStringList(List<String> s)
    {
        Iterator<String> i = s.stream().collect(Collectors.toCollection(LinkedList::new)).descendingIterator();

        List<String> ss = new ArrayList<>();
        do { ss.add(i.next()); } while (i.hasNext());
        
        return ss;
    }
    public static List<Integer> ReverseIntegerList(List<Integer> s)
    {
        Iterator<Integer> i = s.stream().collect(Collectors.toCollection(LinkedList::new)).descendingIterator();

        List<Integer> ss = new ArrayList<>();
        do { ss.add(i.next()); } while (i.hasNext());
        
        return ss;
    }
    public static <T> T[] reverse(T[] array) {
        T[] copy = array.clone();
        Collections.reverse(Arrays.asList(copy));
        return copy;
    }

    public static BigInteger Factorial(BigInteger n)
    {
        if (n == BigInteger.valueOf(0) || n == BigInteger.valueOf(1)) { return BigInteger.valueOf(1); }
        else { return n.multiply(Factorial(n.subtract(BigInteger.valueOf(1)))); }
    }
    
    public static int ShiftRight(int i)
    {
        int cMax = Integer.toString(i).length();
        String y = "";
        for (int c = 0; c < cMax - 1; c++)
        {
            y += Integer.toString(i).toCharArray()[c];
        }

        y = Integer.toString(i).toCharArray()[cMax - 1] + y;
        return Integer.parseInt(y);
    }

    public static Boolean IsCircularPrime(int i, List<Integer> Primos)
    {
        List<Integer> Shift = new ArrayList<>();
        for (int c = 1; c <= Integer.toString(i).length(); c++)
        {
            i = ShiftRight(i);
            Shift.add(i);
        }
        Boolean Contiene = ContainsAllItems(Primos, Shift);
        return Contiene;
    }

    public static Boolean ContainsAllItems(List<Integer> a, List<Integer> b)
    {
        return b.containsAll(a);
    }

    public static List<Integer> GetPrimesUpTo(int x)
    {
        List<Integer> primes = new ArrayList<>();
        
        for (int i = 0; i < x; i++) {
            if (i == 0 || i == 1 || i == 2) { primes.add(i); }
            else {
                if (isPrime(i)) { primes.add(i); }
            }
        }
        
        return primes;
    }

    public static Boolean isPrime(int num) {
        if (num > 2 && num % 2 == 0) {
            return false;
        }
        int top = (int)Math.sqrt(num) + 1;
        for(int i = 3; i < top; i+=2){
            if(num % i == 0) { return false; }
        }
        return true; 
    }
    
    public static Boolean isPrimeL(long num) {
        if (num > 2 && num % 2 == 0) {
            return false;
        }
        int top = (int)Math.sqrt(num) + 1;
        for(int i = 3; i < top; i+=2){
            if(num % i == 0) { return false; }
        }
        return true; 
    }
    public static Boolean isPrimeBig(BigInteger num) {
        if (num.compareTo(BigInteger.valueOf(2)) > 0 &&
            num.remainder(BigInteger.valueOf(2)).equals(BigInteger.ZERO)) {
            return false;
        }
        
        BigInteger top = sqrt(num).add(BigInteger.ONE);
        
        for(BigInteger i = BigInteger.valueOf(3); i.compareTo(top) < 0; i = i.add(BigInteger.valueOf(2))){
            if(num.remainder(i).equals(BigInteger.ZERO)) { return false; }
        }
        return true; 
    }
    public static Boolean isPrimeBigAlt(BigInteger num, int contMax) {
        if (num.compareTo(BigInteger.valueOf(2)) > 0 &&
            num.remainder(BigInteger.valueOf(2)).equals(BigInteger.ZERO)) {
            return false;
        }
        
        BigInteger top = sqrt(num).add(BigInteger.ONE);
        
        for(BigInteger i = BigInteger.valueOf(3); i.compareTo(top) < 0 && i.compareTo(BigInteger.valueOf(contMax)) < 0; i = i.add(BigInteger.valueOf(2))){
            if(num.remainder(i).equals(BigInteger.ZERO)) { return false; }
        }
        return true; 
    }

    public static int Suma(List<Integer> n) {
        return n.stream().collect(Collectors.summingInt(x -> x));
    }
    public static int Suma(Set<Integer> n) {
        int s = 0;
        return n.stream().map((i) -> i).reduce(s, Integer::sum);
    }
    public static double Promedio(List<Integer> n) {
        return n.stream().collect(Collectors.averagingDouble(x -> x));
    }

    public BigInteger Fibonacci(BigInteger n)
    {
        BigInteger s;
        int count = 3;
        BigInteger[] fibs = { BigInteger.valueOf(1), BigInteger.valueOf(1) };

        while (n.compareTo(BigInteger.valueOf(count)) < 0)
        {
            count++;
            s = fibs[0].add(fibs[1]);
            fibs[0] = fibs[1];
            fibs[1] = s;
        }
        return fibs[1];
    }
    
    public static BigInteger sqrt(BigInteger n) {
        BigInteger a = BigInteger.ONE;
        BigInteger b = new BigInteger(n.shiftRight(5).add(new BigInteger("8")).toString());
        
        while(b.compareTo(a) >= 0)
        {
            BigInteger mid = new BigInteger(a.add(b).shiftRight(1).toString());
            if(mid.multiply(mid).compareTo(n) > 0) b = mid.subtract(BigInteger.ONE);
            else a = mid.add(BigInteger.ONE);
        }
        return a.subtract(BigInteger.ONE);
    }
    
    public static void QuickSort(int[] arr, int low, int high) {
        if (arr == null || arr.length == 0)
                return;

        if (low >= high)
                return;

        // pick the pivot
        int middle = low + (high - low) / 2;
        int pivot = arr[middle];

        // make left < pivot and right > pivot
        int i = low, j = high;
        while (i <= j) {
            while (arr[i] < pivot) { i++; }
            while (arr[j] > pivot) { j--; }

            if (i <= j) {
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                i++;
                j--;
            }
        }

        // recursively sort two sub parts
        if (low < j)  { QuickSort(arr, low, j);  }
        if (high > i) { QuickSort(arr, i, high); }
    }

    public static <T> List<T> union(List<T> list1, List<T> list2) {
        Set<T> set = new HashSet<>();

        set.addAll(list1);
        set.addAll(list2);

        return new ArrayList<>(set);
    }

    public static <T> List<T> intersection(List<T> list1, List<T> list2) {
        List<T> list = new ArrayList<>();

        for (T t : list1) {
            if(list2.contains(t)) {
                list.add(t);
            }
        }

        return list;
    }
    
    public static List<Integer> DistinctElements(List<Integer> n) {
        return n.stream().distinct().collect(Collectors.toList());
    }
    public static List<Integer> DistinctElements(Set<Integer> n) {
        return n.stream().distinct().collect(Collectors.toList());
    }
    
    public static List<Integer> ConvertStringToIntList(List<String> s) {
        List<Integer> pp = new ArrayList<>();
        for (String p : s) { pp.add(Integer.parseInt(p)); }
        return pp;
    }
    
    public static Boolean DoubleIsInt(double d) {
        if ((d == Math.floor(d)) && !Double.isInfinite(d)) {
            return true;
        } else {
            return false;
        }
    }
}
public class recursion {
    static long fib(long n)
    {
        if (n <= 1)
            return n;
        return fib(n-1) + fib(n-2);
    }

    public static void main (String args[])
    {
        long n = 3;
        System.out.println(fib(n));
    }

}

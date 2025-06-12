public class FibonacciRecursion {
    public static int fibonacci(int n){
        if(n<=1){
            return n;
        }
        return fibonacci(n-1)+fibonacci(n-2);
    }
    public static void main(String[] args) {
        int n=7;
        System.out.println("fibonacci series upto"+n+"terms:");
        for(int i=0;i<n;i++){
            System.out.println(fibonacci(i)+" ");
        }
    }
}

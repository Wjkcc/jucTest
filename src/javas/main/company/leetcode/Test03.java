package company.leetcode;

public class Test03
{
    public static void main(String[] args)
    {
        int n = 3;
        int a[] = {1,1,1,2,2,3};
        System.out.println(dieSimulator(n,a));
    }
    public static int dieSimulator(int n, int[] rollMax) {
        int num=0;
        for(int i:rollMax) {
            int  d = n - i;
            int add = 1;
           while(n-1 > 0){
               if(d > 0) {
                   add = add*5;
               }
               else {
                   add = add*6;
               }
               d--;
               n--;
           }
           num+=add;



        }
        return num;

    }
}

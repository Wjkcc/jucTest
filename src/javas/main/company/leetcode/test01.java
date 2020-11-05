package company.leetcode;

public class test01
{
    public static void main(String[] args)
    {
                int a[][] = {{0,0,0,0},{1,0,1,0},{0,1,1,0},{0,0,0,0}};
        System.out.println(numEnclaves(a));
    }
    public static int numEnclaves(int array[][]) {
        int i = array.length;
        int j = array[0].length;
        int num = 0;
        // 边界中不为一的数量
        for (int a = 0;a<i;a++) {
            for(int b = 0;b<j;b++) {
                if (array[a][b] == 1) {
                    int add = es(array,a,b);
                    if(add == 0){
                        num++;
                        System.out.println(a+","+b);
                    }
                }

            }
        }
        return num;
    }

    public static int es(int a[][],int i,int j) {
        int add = 0;
        if (i<0||i>a.length)
        {
            throw new RuntimeException("out of index");
        }
        if (j<0||i>a[0].length) {
            throw new RuntimeException("out of index");
        }
        if(a[i][j] == 0) {
            return 0;
        }
        if(i == 0 || j == 0 || i == j-1 || j == i-1){
                return 1;

        }
        // 顺时针旋转
        else {
            if (es(a,i,j-1) == 1) {
                return 1;
            }
            if (es(a,i-1,j) == 1) {
                return 1;
            }
            if (es(a,i,j+1) == 1) {
                return 1;
            }
            if (es(a,i+1,j) == 1) {
                return 1;
            }
        }
        return 0;
    }
}

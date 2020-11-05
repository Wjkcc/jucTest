package company.leetcode;

public class test02
{
    static int add = 0;
    public static void main(String[] args)
    {

    }
    public static int numEnclaves(int array[][]) {
        int i = array.length;
        int j  =array[0].length;
        // 边界上的1
        for (int k = 0; k < i; k++)
        {
            if (array[k][0] == 1) {
                inversion(array,k,0,1);
                continue;
            }
            if (array[k][j-1] == 1) {
                inversion(array,k,j-1,3);
            }
        }
        for (int k = 1; k < j-1; k++)
        {
            if (array[0][k] == 1) {
                inversion(array,0,k,2);
                continue;
            }
            if (array[i-1][k] == 1) {
                inversion(array,i-1,k,4);
            }
        }
        return add;
    }

    /**
     * 按方位入侵置零
     * @param a 二维数组
     * @param x 横坐标
     * @param y 纵坐标
     * @param location 方位 1 上 2 右边 3 下 4 左 顺时针
     * @return
     */
    public static int inversion(int a[][],int x,int y,int location) {
        add++;
        a[x][y] = 0;
        switch(location) {
            case 1:
                if (y + 1 <= a.length-1 && a[x][y+1] == 1) {
                    return inversion(a,x,y+1,1);
                }
                return 0;
            case 2:
                if (x-1 >= 0 && a[x-1][y] == 1) {
                    return inversion(a,x-1,y,2);
                }
                return 0;
            case 3:
                if (y-1 >= 0 && a[x][y-1] == 1) {
                    return inversion(a,x,y-1,3);
                }
                return 0;
            case 4:
                if (x+1 <= a[0].length && a[x+1][y] == 1) {
                    return inversion(a,x+1,y,4);
                }
                return 0;
            default:
                return 0;
        }
    }
}

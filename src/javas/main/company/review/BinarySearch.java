package javas.main.company.review;

/**
 * @description:
 * @author: wjk
 * @date: 2020/9/11 9:10
 **/
public class BinarySearch {
    public static void main(String[] args) {
        int a[] = {1,1,2,2,3,4,5,6,7,8,9};
        int i = binarySearch(a, 8);
        System.out.println(i);
    }
    public static int binarySearch(int[] a, int search) {
        if (a.length == 0) {
            throw new RuntimeException(" array is null");
        }
        int begin = 0;
        int top = a.length-1;
        int mid = 0;
        while(begin <= top) {
            mid = begin + (top - begin) / 2;
            if(a[mid] == search) {
                return mid;
            }
            if (a[mid] < search) {
                begin = mid;
                continue;
            }
            if (a[mid] > search) {
                top = mid;
            }
        }
        return -1;

    }
}

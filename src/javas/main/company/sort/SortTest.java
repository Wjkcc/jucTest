package javas.main.company.sort;

import java.util.Arrays;

/**
 * @description:
 * @author: wjk
 * @date: 2020/9/12 15:41
 **/
public class SortTest {
    public static void main(String[] args) {
        sortPrintTemplate(3);

    }
    public static void sortPrintTemplate(int type) {
        int[] test = {7,6,8,9};
        switch(type){
            case 1:
                optionalSort(test);
            case 2:
                swapSort(test);
            case 3:
                quickSort(test);
            default:
                break;
        }
        arrayPrint(test);
    }
    /**
     * 数组间数字交换
     * @param array
     * @param i
     * @param j
     */
    public static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    /**
     * 选择排序
     * 每次循环第一个数和后面的数依次比较，直到最后一个数
     * @param array
     */
    public static void optionalSort(int[] array) {
        int l = array.length;
        for (int i = 0; i < l -1; i++) {
            for (int j = i+1; j < l; j++) {
                if (array[j] < array[i]) {
                    swap(array,i,j);
                }
            }
        }
    }
    public static void arrayPrint(int[] array) {
        for (int i:array
             ) {
            System.out.print(i + ",");
        }
    }

    /**
     * 选择排序
     * 每次循环，当前位和下一位比较，下一位和再和它后面一位比较，直到最后一位。
     * @param array
     */
    public static void swapSort(int[] array) {
        for (int i = 0; i < array.length-1; i++) {
            for (int j = 1; j < array.length-i; j++) {
                if (array[j] < array[j-1]) {
                    swap(array,j,j-1);
                }
            }
        }
    }

    /**
     * 快速排序，以最左边的数作为基准值，先从右边进行遍历，如果比基准值小，左边的index值变为右边值，
     * 然后左边遍历，找到比基准值大的，右边的index值变为左边值，直到左边右边重合，结束，然后递归左右两边
     * @param array
     */
    public static void quickSort(int[] array) {
        quickSort(array, 0, array.length-1);
    }

    private static void quickSort(int[] array, int left, int right) {
        if (left >= right) {
            System.out.println(1);
            return;
        }
        // 左边index
        int l = left;
        // 基准值
        int key = array[left];
        int r = right;
        while (l < r) {
            // 先对右边进行遍历
            while(r > l && array[r] > key) {
                r--;
            }
            // 找到右边比基准小的，最左边index值改为右边值
            array[l] = array[r];
            // 进行左边遍历
            while (l < r && array[l] < key) {
                l++;
            }
            // 找到左边比基准值大的，和右边的index交换
            array[r] = array[l];
        }
        // 左边index值变为基准值
        array[l] = key;
        // 递归操作,l的左边和右边
        quickSort(array,left,l-1);
        quickSort(array,r+1,right);

    }
}

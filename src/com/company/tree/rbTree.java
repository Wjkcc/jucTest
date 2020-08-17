package com.company.tree;

/**
 * @description:
 * @author: wjk
 * @date: 2020/7/10 11:50
 **/
public class rbTree {
    public static void main(String[] args) {
        int array[] = {2,2,3,4,12,14,18,19,24,44};
        System.out.println(binarySearch(array,8));
    }

    /**
     *二分查找
     * 时间复杂度logn
     * @param array
     * @param value
     * @return
     */
    public static int binarySearch(int[] array, int value) {
        int low = 0;
        int high = array.length - 1;
        int mid;
        while(low < high) {
             mid = low + (high - low)/2;
            if (array[mid] > value) {
                high = mid - 1;
            }else if (array[mid] == value) {
                return mid;
            }else {
                low = mid + 1;
            }
        }
        return -1;
    }
}

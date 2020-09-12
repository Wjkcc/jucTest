package javas.main.company.review;

import java.util.Arrays;
import java.util.Objects;
import java.util.Random;

/**
 * @description:
 * @author: wjk
 * @date: 2020/9/11 9:56
 **/
public class QuickSort {
    public static void main(String[] args) {
        int a[] = {2,3,11,23,18,92,32,11};
        quickSort1(a,0,a.length-1);
        print(a);
    }
    public static void quickSort(int a[],int left,int right) {

       if (a.length == 0) {
           throw new RuntimeException("array is empty");
       }
        if (right >= left) {
            return;
        }
        int index = new Random().nextInt(((right+left)/2));
        int key = a[index];
        int l = left;
        int r = right;
       while (l<index||r>index) {
           while(l<index && a[l] < key) {
               l++;
           }
           a[index] = a[l];
           while(index < r&& a[r] > key) {
               r++;
           }
           a[l] = a[r];
       }


    }

    public static void swap(int a[] ,int i,int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
    public static void quickSort1(int a[],int left,int right) {
        q1(a,left,right);
    }
    public static void q1(int a[],int left,int right) {
        if (left >= right) {
            return;
        }
        int l = left;
        int r = right;
        int key = a[left];
        while(l < r) {
           while(r > l && a[r] >= key) {
               r--;
           }
           a[l] = a[r];
           while(l < r && a[l] <= key){
               l++;
           }
           a[r] = a[l];
        }
        a[l] = key;
        quickSort1(a,left,l-1);
        quickSort1(a,r+1,right);
    }
    public static void print(int a[]) {
        for(int i : a) {
            System.out.print(i+",");
        }
    }
}

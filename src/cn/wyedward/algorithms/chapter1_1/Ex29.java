package cn.wyedward.algorithms.chapter1_1;

import java.util.Arrays;

public class Ex29 {
    /**
     * 返回数组中小于key的元素数量
     * @param key
     * @param a
     * @return
     */
    public static int rank(int key, int[] a){
        int lo = 0;
        int hi = a.length - 1;
        while(lo <= hi){
            int mid = lo + (hi - lo)/2;
            if(a[mid] == key){
                while(mid >= 0 && a[mid] == key){
                    mid--;
                }
                return ++mid;
            }else if(a[mid] < key){
                lo = mid + 1;
            }else if(a[mid] > key){
                hi = mid - 1;
            }
        }
        return -1;
    }

    /**
     * 返回数组中等于key的元素的数量
     * @param key
     * @param a
     * @return
     */
    public static int count(int key, int[] a){
        int num = 1;
        int pos = rank(key, a);
        while (pos < a.length - 1 && a[pos] == a[++pos]){
            num++;
        }
        return num;
    }
    public static void main(String[] args) {
        int[] a = new int[] {84, 48, 68, 10, 18, 98, 12, 23, 54, 57, 33, 16, 77, 11, 29, 11, 29, 77, 77};
        Arrays.sort(a);
        System.out.println(rank(29, a));
        System.out.println(count(48, a));
    }
}

import java.io.*;

import java.util.Scanner;

public class BinarySearch
{
    static int binarysearch(int arr[], int left, int right, int target)
    {
        if(left > right)
        {
            return -1;
        }
        int mid = left + (right -left) / 2;

        if(arr[mid]==target)
        {
            return mid;
        }
        if( arr[mid] < target)
        {
            return binarysearch(arr, mid+1, right, target);
        }
        else
        {
            return binarysearch(arr, left, mid-1, target);
        }

    }
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter the no. of elements: ");
        int n= scan.nextInt();
        int arr[] = new int[n];
        System.out.println("Enter the elements: ");
        for (int i=0; i<arr.length; i++)
        {
            arr[i]= scan.nextInt();
        }
        System.out.println("Enter the targeted value: ");
        int target= scan.nextInt();
int found = binarysearch(arr,0,n-1,target);
        System.out.println("Found element: "+found);
    }

}

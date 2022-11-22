package ir.ac.kntu;

import java.io.*;

// copied from gfg and changed it.

// So for sorting with prices we need names to change their place too.
// while numbers are being sort, names which are in same place as numbers will swap too.
class BubbleSort
{
    // An optimized version of Bubble Sort
    static void bubbleSort(int[] prices, String[] goodsNames)
    {
        int n = prices.length;
        int i, j, temp;
        boolean swapped;
        for (i = 0; i < n - 1; i++)
        {
            swapped = false;
            for (j = 0; j < n - i - 1; j++)
            {
                if (prices[j] > prices[j + 1])
                {
                    // swap arr[j] and arr[j+1]
                    temp = prices[j];
                    prices[j] = prices[j + 1];
                    prices[j + 1] = temp;

                    String tempo = goodsNames[j];
                    goodsNames[j] = goodsNames[j+1];
                    goodsNames[j+1] = tempo;
                    swapped = true;
                }
            }

            // IF no two elements were
            // swapped by inner loop, then break
            if (!swapped)
                break;
        }
    }

    // Function to print an array
    static void printArray(int[] prices,String[] goodsNames)
    {
        int size = prices.length;

        for (int price : prices){
            System.out.print(price + " ");
        }
        System.out.println();
        for (String i:goodsNames){
            System.out.print(i+"  ");
        }
    }


    public static void main(String args[])
    {
        int arr[] = { 64, 34, 25, 12, 22, 11, 90 };
        String[] goodsNames = {"a","b","c","d","e","f","g"};
        int n = arr.length;
        bubbleSort(arr, goodsNames);
        System.out.println("Sorted array: ");
        printArray(arr, goodsNames);
    }
}


// This code is contributed
// by Nikita Tiwari.
//enhanced by Amirreza Mehrzadian :)
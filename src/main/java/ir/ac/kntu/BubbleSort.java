package ir.ac.kntu;


import java.io.*;

import java.util.ArrayList;

// copied from gfg and changed it.

// So for sorting with prices we need names to change their place too.
// while numbers are being sort, names which are in same place as numbers will swap too.
class BubbleSort
{
    // An optimized version of Bubble Sort
    static void bubbleSort(ArrayList<Integer> prices, ArrayList<String> goodsNames)
    {
        int n = prices.size();
        int i, j, temp;
        boolean swapped;
        for (i = 0; i < n - 1; i++)
        {
            swapped = false;
            for (j = 0; j < n - i - 1; j++)
            {
                if (prices.get(j) > prices.get(j+1))
                {
                    // swap arr[j] and arr[j+1]
                    temp = prices.get(j);
                    prices.set(j,prices.get(j+1));
                    prices.set(j+1,temp);

                    String tempo = goodsNames.get(j);
                    goodsNames.set(j,goodsNames.get(j+1));
                    goodsNames.set(j+1,tempo);
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
    static void printSortedByPrice(ArrayList<Integer> prices, ArrayList<String> goodsNames)
    {
        bubbleSort(prices,goodsNames);
        System.out.println();
        for (String i:goodsNames){
            System.out.print(i+"  ");
        }
        System.out.println();
    }

    public static void bubbleSortForDates(ArrayList<String> dates, ArrayList<String> goodsNames) {
        int n = dates.size();
        int i, j;
        String temp;
        boolean swapped;
        for (i = 0; i < n - 1; i++) {
            swapped = false;
            for (j = 0; j < n - i - 1; j++) {
                if (dates.get(j).compareTo(dates.get(j + 1)) < 0) {
                    temp = dates.get(j);
                    dates.set(j, dates.get(j + 1));
                    dates.set(j + 1, temp);

                    String tempo = goodsNames.get(j);
                    goodsNames.set(j, goodsNames.get(j + 1));
                    goodsNames.set(j + 1, tempo);
                    swapped = true;
                }
            }
            if (!swapped)
                break;
        }
    }

    public static void printSortedByDate(ArrayList<String> dates, ArrayList<String> goodsNames)
    {
        bubbleSortForDates(dates,goodsNames);
        System.out.println();
        for (String i:goodsNames){
            System.out.print(i+"  ");
        }
        System.out.println();
    }

}


// This code is contributed
// by Nikita Tiwari.
//enhanced by Amirreza Mehrzadian :)
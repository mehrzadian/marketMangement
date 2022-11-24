package ir.ac.kntu;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

public class HandleUserRequests {

    public static void sortByPrice2(ArrayList<String> stuffs){
        ArrayList<String> goodsNames=new ArrayList<>();
        ArrayList<Integer> prices = new ArrayList<>();

        for (int i=0;i<stuffs.size();i+=6){
            goodsNames.add(stuffs.get(i+1));
            prices.add(Integer.parseInt(stuffs.get(i+2)));
        }
        BubbleSort.printSortedByPrice(prices,goodsNames);
    }

    public static void sortByDate3(ArrayList<String> stuffs){
        ArrayList<String> goodsNames=new ArrayList<>();
        ArrayList<String> dates = new ArrayList<>();

        for (int i=0;i<stuffs.size();i+=6){
            goodsNames.add(stuffs.get(i+1));
            dates.add(stuffs.get(i+5));
        }
        BubbleSort.printSortedByDate(dates,goodsNames);
    }

    public static void displayFromSpecificKind1(ArrayList<String> stuffs,String kind){
        kind = kind.toLowerCase();
        System.out.println(stuffs);
        ArrayList<String> fromSpecificKind= new ArrayList<>();
        for (int i=0;i<stuffs.size();i+=6){
            if (stuffs.get(i).equals(kind)){
                fromSpecificKind.add(stuffs.get(i+1));
            }
        }
        System.out.println("We have these stuffs from "+kind.toUpperCase()+" category:");
        for (int i=0;i<fromSpecificKind.size();i++){
            System.out.println(i+1+". "+fromSpecificKind.get(i));
        }
        if (fromSpecificKind.size()==0){
            System.out.println("We don't have any "+kind.toUpperCase()+"!");
        }
    }

    public static void sortByQuantity4(ArrayList<String> stuffs){
        ArrayList<String> goodsNames = new ArrayList<>();
        ArrayList<Integer> quantities = new ArrayList<>();
        for (int i=0;i<stuffs.size();i+=6){
            goodsNames.add(stuffs.get(i+1));
            quantities.add(Integer.parseInt(stuffs.get(i+3)));
        }
        BubbleSort.printSortedByPrice(quantities,goodsNames);
    }

    public static void printIfExpired5(ArrayList<String>stuffs) throws ParseException {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd");
        LocalDate now = LocalDate.now();
        String nowStr =String.valueOf(now);
        System.out.println(dtf.format(now));
        ArrayList<String>goodsNames= new ArrayList<>();
        ArrayList<String> expirationDates = new ArrayList<>();
        for (int i=0;i<stuffs.size();i+=6){
            expirationDates.add(stuffs.get(i+5));
            goodsNames.add(stuffs.get(i+1));
        }
        for (int i=0;i<expirationDates.size();i++){
            String date2 = expirationDates.get(i);

            Date d1 = sdformat.parse(nowStr);
            Date d2 = sdformat.parse(date2);
            if (d2.compareTo(d1)<0){
                System.out.print(goodsNames.get(i)+"   ");
            }
        }
        System.out.println();

    }

    public static void sumOfAll6(ArrayList<String>stuffs){
        int sum=0;
        for (int i=0;i<stuffs.size();i+=6){
            sum+=Integer.parseInt(stuffs.get(i+2))*Integer.parseInt(stuffs.get(i+3));
        }
        System.out.println(sum);
    }

    public static void printAllStuffs7(ArrayList<String> stuffs) {
        for (int i = 0; i < stuffs.size(); i += 6) {
            System.out.println(stuffs.get(i + 1));
            System.out.println("    Price : " + stuffs.get(i + 2));
            System.out.println("    Quantity : " + stuffs.get(i + 3));
            System.out.println("    Production Date : " + stuffs.get(i + 4));
            System.out.println("    Expiration Date : " + stuffs.get(i + 5));
        }
    }
}

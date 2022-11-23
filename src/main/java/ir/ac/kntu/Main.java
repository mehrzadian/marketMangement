package ir.ac.kntu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {

        System.out.println("If this is a shopping list enter 1 otherwise enter 0");
        Scanner scanner = new Scanner(System.in);
        int mode = Integer.parseInt(scanner.nextLine());
        String recipe = gettingRecipe(mode);
        menu();
        int whatToDisplay = Integer.parseInt(scanner.nextLine());
        ArrayList<String> stuffs = stuffs(goods(recipe));
        whatToDesplay(scanner, whatToDisplay, stuffs);
        System.out.println(recipe);


    }

    private static void whatToDesplay(Scanner scanner, int whatToDisplay, ArrayList<String> stuffs) {
        if (whatToDisplay == 1) {
            String kind = scanner.nextLine();
            displayFromSpecificKind1(stuffs, kind);
        } else if (whatToDisplay ==2){
            sortByPrice2(stuffs);
        } else if (whatToDisplay ==3){
            sortByDate3(stuffs);
        } else if (whatToDisplay == 4) {
            sortByQuantity4(stuffs);
        } else if (whatToDisplay == 5){
            printIfExpired5(stuffs);
        } else if (whatToDisplay == 6){
            sumOfAll6(stuffs);
        } else if (whatToDisplay == 7){
            printAllStuffs7(stuffs);
        } else{
            System.out.println(" Unaproppriate number!");
        }
    }

    public static String gettingRecipe(int mode) {
        if (mode == 0){
            System.out.println("Enter your selling recipe");
        } else {
            System.out.println("Enter your shopping recipe");
        }
        JsonGenerator jsonGenerator = new JsonGenerator();
        return jsonGenerator.generateBuyRecipe();


    }

    public static void menu(){
        System.out.println("What do you want to see? (enter thr number)\n\n" +
                "1. Displaying the products of a particular bracelet\n" +
                "2. Sort products by price\n" +
                "3. Sort products by expiration date\n" +
                "4. Sort items by number\n" +
                "5. Show expired products\n" +
                "6. Display the amount of the total amount of the invoice\n" +
                "7. Display all goods in the invoice");
    }

    public static String[] goods(String recipe){
        return recipe.split(",],");
    }

    public static ArrayList<String> stuffs(String[] goods){
        ArrayList<String> stuffs = new ArrayList<>();
        for (String i:goods){
            //last index of list is "}", so we delete it.
            if (i.length()<5){
                break;
            }
            String kind = i.split(":\\[")[0];
            i=i.split(":\\[")[1];
            kind= kind.replaceAll("\"","").replaceAll("\\{","");
            // for example "cheese":{"price":"192306","quantity":"59","productionDate":"2022-2-21","expirationDate":"2022-3-6"
            for(String good: i.split("\\},")){
                //extracting name of good
                String name=good.split(":\\{")[0].replaceAll("\"","");
                String description = good.split(":\\{")[1];
                stuffs.add(kind);
                stuffs.add(name);
                addDescriptions(stuffs, description);
            }
        }
        return stuffs;
    }

    public static void addDescriptions(ArrayList<String> stuffs, String description) {
        stuffs.add(extractPrice(description));
        stuffs.add(extractQuantity(description));
        stuffs.add(extractProductionDate(description));
        stuffs.add(extractExpirationDate(description));
    }

    public static String extractPrice(String description){
        String price;
        //price:158599
        String temp1 = description.split(",")[0].replaceAll("\"","");
        //158599
        price = temp1.split(":")[1];

        return price;
    }

    public static String extractQuantity(String description){
        String quantity;
        //quantity:162
        String temp1 = description.split(",")[1].replaceAll("\"","");
        //162
        quantity = temp1.split(":")[1];
        return quantity;
    }

    public static String extractProductionDate(String description){
        String production;
        //productionDate:2022-4-5
        String temp1 = description.split(",")[2].replaceAll("\"","");
        //2022-4-5
        production =temp1.split(":")[1];

        return production;
    }

    public static String extractExpirationDate(String description){
        String expiration;
        //productionDate:2022-4-5
        String temp1 = description.split(",")[3].replaceAll("\"","");
        //2022-4-5
        expiration =temp1.split(":")[1].replaceAll("}","");

        return expiration;
    }

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

    public static void printIfExpired5(ArrayList<String>stuffs){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate now = LocalDate.now();
        String nowStr =String.valueOf(now);
        System.out.println(dtf.format(now));
        ArrayList<String>goodsNames= new ArrayList<>();
        ArrayList<String> expirationDates = new ArrayList<>();
        ArrayList<String> expired=new ArrayList<>();
        for (int i=0;i<stuffs.size();i+=6){
            expirationDates.add(stuffs.get(i+5));
            goodsNames.add(stuffs.get(i+1));
        }
        for (int i=0;i<expirationDates.size();i+=6){
            if (expirationDates.get(i).compareTo(nowStr)<0){
                System.out.print(goodsNames.get(i));
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

    public static void printAllStuffs7(ArrayList<String> stuffs){
        for (int i=0;i<stuffs.size();i+=6){
            System.out.println(stuffs.get(i+1));
            System.out.println("    Price : " + stuffs.get(i+2));
            System.out.println("    Quantity : " + stuffs.get(i+3));
            System.out.println("    Production Date : " + stuffs.get(i+4));
            System.out.println("    Expiration Date : " + stuffs.get(i+5));
        }
    }
}


package ir.ac.kntu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        System.out.println("If this is a shopping list enter 1 otherwise enter 0");
        Scanner scanner = new Scanner(System.in);
        int mode = Integer.parseInt(scanner.nextLine());
        String recipe = gettingRecipe(mode);
        menu();
        int whatToDisplay = Integer.parseInt(scanner.nextLine());
        ArrayList<String> stuffs = stuffs(goods(recipe));
        if (whatToDisplay == 1) {
            String kind = scanner.nextLine();
            displayFromSpecificKind1(stuffs, kind);
        } else if (whatToDisplay==2){

        }
        System.out.println(recipe);
//        for( String i:goods(recipe)){
//            System.out.println(i);
//        }
//        String[]x=goods(recipe);
//        System.out.println(stuffs(x).size());
//        System.out.println(stuffs(x));


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

    public static void displayAllGoods7(ArrayList<String> stuffs){

    }

    public static void displayFromSpecificKind1(ArrayList<String> stuffs,String kind){
        System.out.println(stuffs);
        ArrayList<String> fromSpecificKind= new ArrayList<>();
        for (int i=0;i<stuffs.size();i+=6){
            if (stuffs.get(i).equals(kind)){
                fromSpecificKind.add(stuffs.get(i+1));
            }
        }
        System.out.println(fromSpecificKind);
        System.out.println("We have these stuffs from "+kind.toUpperCase()+" category:");
        for (int i=0;i<fromSpecificKind.size();i++){
            System.out.println(i+1+". "+fromSpecificKind.get(i));
        }
        if (fromSpecificKind.size()==0){
            System.out.println("We don't have any "+kind.toUpperCase()+".");
        }
    }
}
//\{"price":"\d+"

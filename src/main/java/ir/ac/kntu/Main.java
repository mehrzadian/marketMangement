package ir.ac.kntu;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {


        System.out.println("If this is a shopping list enter 1 otherwise enter 0");
        Scanner scanner = new Scanner(System.in);
        int mode = scanner.nextInt();
        String recipe = gettingRecipe(mode);
        menu();
        int whatToDisplay = scanner.nextInt();
        JsonGenerator jsonGenerator = new JsonGenerator();
        System.out.println(recipe);
        for( String i:goods(recipe)){
            System.out.println(i);
        }
        System.out.println(stuffs(goods(recipe))+"hi");
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

    public static ArrayList<ArrayList<String>> stuffs(String[] goods){

        ArrayList<ArrayList<String>> stuffs = new ArrayList<>();
        String regex = ":";
        Pattern pattern = Pattern.compile(regex);
        for (String listOfProducts : goods){
            Matcher matcher = pattern.matcher(listOfProducts);
            System.out.println(matcher.groupCount());
            for (int i=0;i<matcher.groupCount();i++) {
                ArrayList<String> stuff = new ArrayList<>();
                String regexForKind = "\\w+\":\\[";
                Pattern patternForKind = Pattern.compile(regexForKind);
                String[] kind = patternForKind.split("\"");
                stuff.add(kind[0]);

                stuffs.add(stuff);
            }
        }
        return stuffs;

    }
}
//\{"price":"\d+"

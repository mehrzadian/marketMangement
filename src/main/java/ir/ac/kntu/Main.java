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
        int mode = scanner.nextInt();
        String recipe = gettingRecipe(mode);
        menu();
        int whatToDisplay = scanner.nextInt();
        JsonGenerator jsonGenerator = new JsonGenerator();
        System.out.println(recipe);
//        Pattern re = Pattern.compile("(?:,|\\{)?([^:]*):(\"[^\"]*\"|\\{[^}]*\\}|[^},]*)",Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
//        Matcher m = re.matcher(recipe);
//        System.out.println(m);
        for( String i:goods(recipe)){
            System.out.println(i);
        }
        String[]x=goods(recipe);
        stuffs(x);
        extractPrice("\"price\":\"158599\",\"quantity\":\"106\",\"productionDate\":\"2022-13-17\",\"expirationDate\":\"2023-2-2\"}");
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
        HashMap<String,String> hash= new HashMap<>();
        for (String i:goods){
            //last index of list is "}", so we delete it.
            if (i.length()<5){
                break;
            }
            String kind = i.split(":\\[")[0];
            i=i.split(":\\[")[1];
            kind= kind.replaceAll("\"","");
            kind = kind.replaceAll("\\{","");
            System.out.println(kind);
            hash.put(kind,"");
            // for example "cheese":{"price":"192306","quantity":"59","productionDate":"2022-2-21","expirationDate":"2022-3-6"
            for(String good: i.split("\\},")){
                //extracting name of good
                System.out.println(good);
                String name=good.split(":\\{")[0];
                name = name.replaceAll("\"","");
                System.out.println(name);

            }
//            HashMap<String,String> kindName=new HashMap<>();
//            kindName.put(i.find("\\w+\":\\["),i.("\\w+\":\\{"));
        }
        return new ArrayList<>();


    }
    public static int extractPrice(String description){
        int price;
        //price:158599
        String temp1 = description.split(",")[0].replaceAll("\"","");
        //158599
        String temp2 = temp1.split(":")[1];
        price=Integer.parseInt(temp2);
        System.out.println(price);
        return price;
    }
}
//\{"price":"\d+"

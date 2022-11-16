package ir.ac.kntu;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

//        System.out.println(jsonGenerator.generateBuyRecipe());
        System.out.println("If this is a shopping list enter 1 otherwise enter 0");
        Scanner scanner = new Scanner(System.in);
        int mode = scanner.nextInt();
        String recipe = gettingRecipe(mode);
        menu();
        int whatToDisplay = scanner.nextInt();

    }

    private static String gettingRecipe(int mode) {
        if (mode == 0){
            System.out.println("Enter your selling recipe");
        } else {
            System.out.println("Enter your shopping recipe");
        }
        JsonGenerator jsonGenerator = new JsonGenerator();
        return jsonGenerator.generateBuyRecipe();


    }

    private static void menu(){
        System.out.println("What do you want to see? (enter thr number)\n\n" +
                "1. Displaying the products of a particular bracelet\n" +
                "2. Sort products by price\n" +
                "3. Sort products by expiration date\n" +
                "4. Sort items by number\n" +
                "5. Show expired products\n" +
                "6. Display the amount of the total amount of the invoice\n" +
                "7. Display all goods in the invoice");
    }

}

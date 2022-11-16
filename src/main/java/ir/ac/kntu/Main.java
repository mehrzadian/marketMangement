package ir.ac.kntu;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

//        System.out.println(jsonGenerator.generateBuyRecipe());
        System.out.println("If this is a shopping list enter 1 otherwise enter 0");
        Scanner scanner = new Scanner(System.in);
        int mode = scanner.nextInt();
        String recipe = gettingRecipe(mode);

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
}

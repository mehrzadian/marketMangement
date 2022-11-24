package ir.ac.kntu;

import java.util.ArrayList;
import java.util.Scanner;

import static ir.ac.kntu.HandleUserRequests.*;

public class Menu {

    public static void whatToDesplay(Scanner scanner, int whatToDisplay, ArrayList<String> stuffs) {
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


}

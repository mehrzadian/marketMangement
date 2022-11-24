package ir.ac.kntu;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;

import static ir.ac.kntu.JsonExtractor.goods;
import static ir.ac.kntu.JsonExtractor.stuffs;
import static ir.ac.kntu.Menu.*;

public class Main {
    public static void main(String[] args) throws ParseException {

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




}


package ir.ac.kntu;

import java.util.ArrayList;

public class JsonExtractor {
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

}

package ir.ac.kntu;

import java.util.ArrayList;
import java.util.Random;

public class JsonGenerator {
    private StringBuilder json;
    private Random rand;
    static final String[] typeList = {"junkFood", "drink", "diary", "sanitary", "protein"};
    static final String[] proteinGoodsList = {"beef", "chicken", "turkey", "sausage", "fish", "egg"};
    static final String[] diaryGoodsList = {"milk", "cheese", "yogurt", "cream", "dough", "Curd"};
    static final String[] drinkGoodsList = {"coke", "hype", "delester", "hotChocolate", "coffee", "tea"};
    static final String[] sanitaryGoodsList = {"handWash", "shampoo", "tissue", "faceWash", "bodyWash", "washingPowder"};
    static final String[] junkFoodGoodsList = {"chips", "pofak", "tokhme", "popCorn", "lavashak", "chocolate"};

    public JsonGenerator() {
        json = new StringBuilder();
        rand = new Random();
    }

    public String generateBuyRecipe() {
        generateRandomTypes();
        return json.toString();
    }


    private void generateRandomTypes() {
        json.append("{");
        ArrayList<Integer> typesIndex = generateNonDuplicateRandomNumbers(5);
        for (Integer i : typesIndex) {
            generateRandomGoods(typeList[i]);
        }
        json.append("}");
    }

    private void generateRandomGoods(String type) {
        String[] goodsList;
        json.append("\"").append(type).append("\":[");
        switch (type) {
            case "junkFood":
                goodsList = junkFoodGoodsList;
                break;
            case "drink":
                goodsList = drinkGoodsList;
                break;
            case "diary":
                goodsList = diaryGoodsList;
                break;
            case "sanitary":
                goodsList = sanitaryGoodsList;
                break;
            case "protein":
                goodsList = proteinGoodsList;
                break;
            default:
                return;
        }
        ArrayList<Integer> goodsIndex = generateNonDuplicateRandomNumbers(6);
        for (Integer i : goodsIndex) {
            generateRandomGoodsDetails(goodsList[i], type);
        }
        json.append("],");
    }

    private void generateRandomGoodsDetails(String item, String type) {
        String price = generateRandomPrice(type);
        String pDate = generateRandomPDate();
        String eDate = generateRandomEDate(pDate, type);
        json.append("\"").append(item).append("\":{\"price\":\"").append(price).append("\",\"quantity\":\"").append(rand.nextInt(150) + 15).append("\",\"productionDate\":\"").append(pDate).append("\",\"expirationDate\":\"").append(eDate).append("\"},");
    }

    private String generateRandomPrice(String type) {
        return switch (type) {
            case "junkFood" -> String.valueOf(rand.nextInt(55000) + 20000);
            case "drink" -> String.valueOf(rand.nextInt(40000) + 25000);
            case "diary" -> String.valueOf(rand.nextInt(150000) + 50000);
            case "sanitary" -> String.valueOf(rand.nextInt(200000) + 60000);
            case "protein" -> String.valueOf(rand.nextInt(300000) + 100000);
            default -> "error";
        };
    }

    private String generateRandomPDate() {
        return "2022" + "-" + (rand.nextInt(13) + 1) + "-" + (rand.nextInt(31) + 1);
    }

    private static String generateRandomEDate(String pDate, String type) {
        String[] split = pDate.split("-");
        int year = Integer.parseInt(split[0]);
        int month = Integer.parseInt(split[1]);
        int day = Integer.parseInt(split[2]);
        switch (type) {
            case "junkFood":
                month += 6;
                if (month > 12) {
                    month -= 12;
                    year += 1;
                }
                break;
            case "drink":
                month += 8;
                if (month > 12) {
                    month -= 12;
                    year += 1;
                }
                break;
            case "diary":
                day += 15;
                if (day > 30) {
                    day -= 30;
                    month += 1;
                    if (month > 12) {
                        month -= 12;
                        year += 1;
                    }
                }
                break;
            case "sanitary":
                year += 2;
                break;
            case "protein":
                day += 2;
                if (day > 30) {
                    day -= 30;
                    month += 1;
                    if (month > 12) {
                        month -= 12;
                        year += 1;
                    }
                }
                break;
            default:
                return "error";
        }
        return year + "-" + month + "-" + day;
    }

    private ArrayList<Integer> generateNonDuplicateRandomNumbers(int max) {
        ArrayList<Integer> generated = new ArrayList<>();
        while (generated.size() < rand.nextInt(max + 1) + 1) {
            Integer next = rand.nextInt(max);
            if (!generated.contains(next)) {
                generated.add(next);
            }
        }
        return generated;
    }
}

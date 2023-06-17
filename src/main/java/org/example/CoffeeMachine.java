package org.example;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class CoffeeMachine {

    private int moneyBox;
    private int earnedAmount;
    private HashMap<String, Integer> ingredients = new HashMap<>();
    private HashMap<String, Coffee> coffeeMenu = new HashMap<>();
    private HashMap<String, Integer> coffeeSold = new HashMap<>();
    private HashMap<String, Integer> ingredientsConsumed = new HashMap<>();


    public CoffeeMachine(){

        moneyBox = 0;

        earnedAmount = 0;

        ingredients.put("water",0);
        ingredients.put("milk",0);
        ingredients.put("coffee beans",0);

        coffeeMenu.put("espresso",new Coffee(4,250,0,16));
        coffeeMenu.put("latte",new Coffee(7,350,75,20));
        coffeeMenu.put("cappuccino",new Coffee(6,200,100,12));

        coffeeSold.put("espresso",0);
        coffeeSold.put("latte",0);
        coffeeSold.put("cappuccino",0);

        ingredientsConsumed.put("water",0);
        ingredientsConsumed.put("milk",0);
        ingredientsConsumed.put("coffee beans",0);

    }

    class Coffee {
        private int price;
        private int water;
        private int milk;
        private int coffeeBeans;

        public Coffee(int price, int water, int milk, int coffeeBeans) {
            this.price = price;
            this.water = water;
            this.milk = milk;
            this.coffeeBeans = coffeeBeans;
        }

        public int getPrice() {
            return price;
        }

        public int getWater() {
            return water;
        }

        public int getMilk() {
            return milk;
        }

        public int getCoffeeBeans() {
            return coffeeBeans;
        }
    }
    
    public void coffeeBuying(String type){
        Coffee coffee = coffeeMenu.get(type);
        
        if(coffee != null && checkIngredients(coffee)){
            remainingIngredients(coffee);
            moneyBox += coffee.getPrice();
            coffeeSold.put(type, coffeeSold.get(type)+1);
            earnedAmount += coffee.getPrice();
            System.out.println("enjoy your"+type+"coffee !");
        }else{
            System.out.println("Ingrediants to make coffee is insufficient or you entered wrong coffee type");
        }
    }

    private void remainingIngredients(Coffee coffee) {
        ingredients.put("water", ingredients.get("water") - coffee.getWater());
        ingredients.put("milk", ingredients.get("milk") - coffee.getMilk());
        ingredients.put("coffee beans", ingredients.get("coffee beans") - coffee.getCoffeeBeans());
        ingredientsConsumed.put("water", ingredientsConsumed.get("water") + coffee.getWater());
        ingredientsConsumed.put("milk", ingredientsConsumed.get("milk") + coffee.getMilk());
        ingredientsConsumed.put("coffee beans", ingredientsConsumed.get("coffee beans") + coffee.getCoffeeBeans());
    }

    private boolean checkIngredients(Coffee coffee) {
        return ingredients.get("water") >= coffee.getWater() &&
                ingredients.get("milk") >= coffee.getMilk() &&
                ingredients.get("coffee beans") >= coffee.getCoffeeBeans();
    }

    public void fillIngredients(int water, int milk, int coffeeBeans) {
        ingredients.put("water", ingredients.get("water") + water);
        ingredients.put("milk", ingredients.get("milk") + milk);
        ingredients.put("coffee beans", ingredients.get("coffee beans") + coffeeBeans);
    }

    public void takeMoney() {
        System.out.println("Money collected: $" + moneyBox);
        moneyBox = 0;
    }

    public void showIngredients() {
        System.out.println("Ingredients available:");
        for (Map.Entry<String, Integer> entry : ingredients.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue() + " ml/g");
        }
    }

    public void showAnalytics() {
        System.out.println("Analytics:");
        for (Map.Entry<String, Integer> entry : coffeeSold.entrySet()) {
            System.out.println("Coffee: " + entry.getKey() + ", Sold Count: " + entry.getValue());
        }
        System.out.println("Earned Amount: $" + earnedAmount);
        System.out.println("Consumed Ingredients:");
        for (Map.Entry<String, Integer> entry : ingredientsConsumed.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue() + " ml/g");
        }
    }


    public static void main(String[] args) {

        CoffeeMachine Machine = new CoffeeMachine();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Buy a coffee");
            System.out.println("2. Fill ingredients");
            System.out.println("3. Take money");
            System.out.println("4. Show ingredients");
            System.out.println("5. Show analytics");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter the type of coffee (espresso, latte, cappuccino): ");
                    String coffeeType = scanner.nextLine();
                    Machine.coffeeBuying(coffeeType);
                    break;
                case 2:
                    System.out.print("Enter the amount of water to fill (ml): ");
                    int water = scanner.nextInt();
                    System.out.print("Enter the amount of milk to fill (ml): ");
                    int milk = scanner.nextInt();
                    System.out.print("Enter the amount of coffee beans to fill (g): ");
                    int coffeeBeans = scanner.nextInt();
                    Machine.fillIngredients(water, milk, coffeeBeans);
                    break;
                case 3:
                    Machine.takeMoney();
                    break;
                case 4:
                    Machine.showIngredients();
                    break;
                case 5:
                    Machine.showAnalytics();
                    break;
                case 6:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }

            System.out.println();
        }
    }

    }

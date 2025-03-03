import java.util.Scanner;

public class SliceOHeaven {
    private String storeName = "Slice-o-Heaven";
    private String storeAddress = "123 Pizza Street, Cheese City";
    private String storeEmail = "order@sliceoheaven.com";
    private String storePhone = "1-800-PIZZA";
    
    private static final String DEF_ORDER_ID = "DEF-SOH-099";
    private static final String DEF_PIZZA_INGREDIENTS = "Mozzarella Cheese";
    private static final double DEF_ORDER_TOTAL = 15.00;
    
    private static final String[] STORE_MENU = {"Margherita", "Pepperoni", "Vegetarian"};
    private static final String[] PIZZA_INGREDIENTS = {
        "Tomato, Mozzarella, Basil",
        "Tomato, Mozzarella, Pepperoni",
        "Tomato, Mozzarella, Vegetables"
    };
    private static final double[] PIZZA_PRICES = {10.99, 12.99, 11.99};
    private static final String[] SIDES_MENU = {"Garlic Bread", "French Fries", "Chicken Wings"};
    private static final String[] DRINKS_MENU = {"Coke", "Sprite", "Iced Tea"};
    
    private String orderID;
    private double orderTotal;
    private String pizzaIngredients;
    private String selectedPizza;
    private String selectedSide;
    private String selectedDrink;

    public SliceOHeaven() {
        this.orderID = DEF_ORDER_ID;
        this.pizzaIngredients = DEF_PIZZA_INGREDIENTS;
        this.orderTotal = DEF_ORDER_TOTAL;
        this.selectedSide = SIDES_MENU[0];
        this.selectedDrink = DRINKS_MENU[0];
    }

    public SliceOHeaven(String orderID, String pizzaIngredients, double orderTotal) {
        this.orderID = orderID;
        this.pizzaIngredients = pizzaIngredients;
        this.orderTotal = orderTotal;
        this.selectedSide = SIDES_MENU[0];
        this.selectedDrink = DRINKS_MENU[0];
    }

    public String getOrderID() { return orderID; }
    public void setOrderID(String orderID) { this.orderID = orderID; }

    public double getOrderTotal() { return orderTotal; }
    public void setOrderTotal(double orderTotal) { this.orderTotal = orderTotal; }

    public String getPizzaIngredients() { return pizzaIngredients; }
    public void setPizzaIngredients(String pizzaIngredients) { this.pizzaIngredients = pizzaIngredients; }

    public void takeOrder() {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Welcome to " + storeName);
        System.out.println("Our Pizza Menu:");
        
        for(int i = 0; i < STORE_MENU.length; i++) {
            System.out.println((i+1) + ". " + STORE_MENU[i] + " - $" + PIZZA_PRICES[i]);
        }
        
        System.out.print("\nChoose your pizza (enter number): ");
        int pizzaChoice = scanner.nextInt() - 1;
        selectedPizza = STORE_MENU[pizzaChoice];
        this.pizzaIngredients = PIZZA_INGREDIENTS[pizzaChoice];
        
        System.out.println("\nSide options:");
        for(int i = 0; i < SIDES_MENU.length; i++) {
            System.out.println((i+1) + ". " + SIDES_MENU[i]);
        }
        System.out.print("Choose your side (enter number): ");
        int sideChoice = scanner.nextInt() - 1;
        selectedSide = SIDES_MENU[sideChoice];
        
        System.out.println("\nDrink options:");
        for(int i = 0; i < DRINKS_MENU.length; i++) {
            System.out.println((i+1) + ". " + DRINKS_MENU[i]);
        }
        System.out.print("Choose your drink (enter number): ");
        int drinkChoice = scanner.nextInt() - 1;
        selectedDrink = DRINKS_MENU[drinkChoice];
        
        orderTotal = PIZZA_PRICES[pizzaChoice] + 3.0 + 3.0;
        orderID = "ORDER" + (int)(Math.random() * 1000);
        
        scanner.close();
    }

    public void makePizza() {
        System.out.println("\nMaking your " + selectedPizza);
        System.out.println("Ingredients: " + pizzaIngredients);
        System.out.println("Estimated time: 10 minutes...");
    }

    private void printReceipt() {
        System.out.println("\nYour Order Details:");
        System.out.println("========================");
        System.out.println("Order ID: " + orderID);
        System.out.println("Pizza: " + selectedPizza + " - $" + PIZZA_PRICES[getPizzaIndex(selectedPizza)]);
        System.out.println("Side: " + selectedSide + " - $3.00");
        System.out.println("Drink: " + selectedDrink + " - $3.00");
        System.out.println("------------------------");
        System.out.println("Total: $" + String.format("%.2f", orderTotal));
        System.out.println("========================");
        System.out.println("\nStore Address: " + storeAddress);
        System.out.println("Contact: " + storePhone);
        System.out.println("Email: " + storeEmail);
    }

    public void processOrder() {
        takeOrder();
        makePizza();
        printReceipt();
    }

    private int getPizzaIndex(String pizzaName) {
        for(int i = 0; i < STORE_MENU.length; i++) {
            if(STORE_MENU[i].equals(pizzaName)) return i;
        }
        return 0;
    }

    public void processCardPayment(String cardNumber, String expiryDate, int cvv) {
        int cardLength = cardNumber.length();
        if (cardLength == 14) {
            System.out.println("Card accepted");
        } else {
            System.out.println("Invalid card");
            return;
        }

        int firstCardDigit = Integer.parseInt(cardNumber.substring(0, 1));
        String blacklistedNumber = "12345678901234";
        if (cardNumber.equals(blacklistedNumber)) {
            System.out.println("Card is blacklisted. Please use another card");
            return;
        }

        int lastFourDigits = Integer.parseInt(cardNumber.substring(cardNumber.length() - 4));
        String cardNumberToDisplay = cardNumber.charAt(0) + "********" + cardNumber.substring(cardNumber.length() - 4);
        System.out.println("Card Number to Display: " + cardNumberToDisplay);
    }

    public void specialOfTheDay(String pizzaOfTheDay, String sideOfTheDay, double specialPrice) {
        System.out.println("\nSpecial of the Day:");
        System.out.println("Pizza: " + pizzaOfTheDay);
        System.out.println("Side: " + sideOfTheDay);
        System.out.println("Special Price: $" + specialPrice);
    }

    public static void main(String[] args) {
        SliceOHeaven defaultOrder = new SliceOHeaven();
        defaultOrder.processOrder();

        SliceOHeaven customOrder = new SliceOHeaven("CUSTOM-123", "Special Ingredients", 25.99);
        customOrder.processOrder();

        SliceOHeaven paymentExample = new SliceOHeaven();
        paymentExample.processCardPayment("12345678901234", "12/25", 123);

        SliceOHeaven specialExample = new SliceOHeaven();
        specialExample.specialOfTheDay("Hawaiian", "Garlic Bread", 9.99);
    }
}
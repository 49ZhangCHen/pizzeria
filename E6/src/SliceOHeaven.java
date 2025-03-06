import java.util.Scanner;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

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
    private static final String[] SIDES_MENU = {"Calzone", "Garlic Bread", "None"};
    private static final String[] DRINKS_MENU = {"Cold Coffee", "Cocoa Drink", "Coke", "None"};
    
    private String orderID;
    private double orderTotal;
    private String pizzaIngredients;
    private String selectedPizza;
    private String selectedSide;
    private String selectedDrink;
    private String pizzaSize;
    private boolean extraCheese;
    private boolean wantDiscount;

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
        
        System.out.print("Enter three ingredients for your pizza (use spaces to separate ingredients): ");
        String ing1 = scanner.next();
        String ing2 = scanner.next();
        String ing3 = scanner.next();
        this.pizzaIngredients = ing1 + ", " + ing2 + ", " + ing3;
        
        System.out.print("Enter size of pizza (Small, Medium, Large): ");
        this.pizzaSize = scanner.next();
        
        System.out.print("Do you want extra cheese (Y/N): ");
        this.extraCheese = scanner.next().equalsIgnoreCase("Y");
        
        System.out.print("Enter one side dish (Calzone, Garlic Bread, None): ");
        this.selectedSide = scanner.next();
        
        System.out.print("Enter drinks (Cold Coffee, Cocoa Drink, Coke, None): ");
        this.selectedDrink = scanner.next();
        
        System.out.print("Would you like the chance to pay only half for your order? (Y/N): ");
        this.wantDiscount = scanner.next().equalsIgnoreCase("Y");
        
        if (wantDiscount) {
            isItYourBirthday();
        } else {
            makeCardPayment();
        }
        
        scanner.close();
    }

    public void isItYourBirthday() {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter your birthdate (YYYY-MM-DD): ");
        String birthdateStr = scanner.next();
        LocalDate birthdate = LocalDate.parse(birthdateStr, DateTimeFormatter.ISO_LOCAL_DATE);
        LocalDate today = LocalDate.now();
        
        Period age = Period.between(birthdate, today);
        boolean isBirthday = birthdate.getMonth() == today.getMonth() && birthdate.getDayOfMonth() == today.getDayOfMonth();
        
        if (age.getYears() < 18 && isBirthday) {
            System.out.println("Congratulations! You pay only half the price for your order.");
            this.orderTotal *= 0.5;
        } else {
            System.out.println("Too bad! You do not meet the conditions to get our 50% discount.");
        }
        
        scanner.close();
    }

    public void makeCardPayment() {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter your card number: ");
        long cardNumber = scanner.nextLong();
        
        System.out.print("Enter the card's expiry date (YYYY-MM): ");
        String expiryDate = scanner.next();
        
        System.out.print("Enter the card's CVV: ");
        int cvv = scanner.nextInt();
        
        processCardPayment(cardNumber, expiryDate, cvv);
        
        scanner.close();
    }

    public void makePizza() {
        System.out.println("\nMaking your " + selectedPizza);
        System.out.println("Ingredients: " + pizzaIngredients);
        System.out.println("Size: " + pizzaSize);
        System.out.println("Extra Cheese: " + (extraCheese ? "Yes" : "No"));
        System.out.println("Estimated time: 10 minutes...");
    }

    private void printReceipt() {
        System.out.println("\nYour Order Details:");
        System.out.println("========================");
        System.out.println("Order ID: " + orderID);
        System.out.println("Pizza: " + selectedPizza + " - $" + PIZZA_PRICES[getPizzaIndex(selectedPizza)]);
        System.out.println("Size: " + pizzaSize);
        System.out.println("Extra Cheese: " + (extraCheese ? "Yes" : "No"));
        System.out.println("Side: " + selectedSide);
        System.out.println("Drink: " + selectedDrink);
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

    public void processCardPayment(long cardNumber, String expiryDate, int cvv) {
        String cardNumberStr = Long.toString(cardNumber);
        int cardLength = cardNumberStr.length();
        if (cardLength == 14) {
            System.out.println("Card accepted");
        } else {
            System.out.println("Invalid card");
            return;
        }

        int firstCardDigit = Integer.parseInt(cardNumberStr.substring(0, 1));
        long blacklistedNumber = 12345678901234L;
        if (cardNumber == blacklistedNumber) {
            System.out.println("Card is blacklisted. Please use another card");
            return;
        }

        int lastFourDigits = Integer.parseInt(cardNumberStr.substring(cardNumberStr.length() - 4));
        String cardNumberToDisplay = cardNumberStr.charAt(0) + "********" + cardNumberStr.substring(cardNumberStr.length() - 4);
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
        paymentExample.processCardPayment(12345678901234L, "12/25", 123);

        SliceOHeaven specialExample = new SliceOHeaven();
        specialExample.specialOfTheDay("Hawaiian", "Garlic Bread", 9.99);
    }
}

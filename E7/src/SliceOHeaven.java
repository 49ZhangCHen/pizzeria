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

    private static final String[] INGREDIENTS = {
        "Mushroom", "Paprika", "Sun-dried tomatoes", "Chicken", "Pineapple"
    };
    private static final String[] SIZES = {"Large", "Medium", "Small"};
    private static final String[] SIDES = {
        "Calzone", "Garlic bread", "Chicken puff", "Muffin", "Nothing for me"
    };
    private static final String[] DRINKS = {
        "Coca Cola", "Cold coffee", "Cocoa Drink", "No drinks for me"
    };

    private static final long BLACKLISTED_NUMBER = 12345678901234L;

    private String orderID;
    private double orderTotal;
    private String pizzaIngredients;
    private String pizzaSize;
    private boolean extraCheese;
    private String sideDish;
    private String drink;
    private boolean wantDiscount;

    public SliceOHeaven() {
        this.orderID = DEF_ORDER_ID;
        this.pizzaIngredients = DEF_PIZZA_INGREDIENTS;
        this.orderTotal = DEF_ORDER_TOTAL;
        this.pizzaSize = SIZES[1]; // Default to Medium
        this.sideDish = SIDES[0]; // Default to Calzone
        this.drink = DRINKS[0]; // Default to Coca Cola
    }

    public void takeOrder() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to " + storeName);

        // Ingredients selection
        int ingChoice1, ingChoice2, ingChoice3;
        do {
            System.out.println("Please pick any three of the following ingredients:");
            for (int i = 0; i < INGREDIENTS.length; i++) {
                System.out.println((i + 1) + ". " + INGREDIENTS[i]);
            }
            System.out.print("Enter any three choices (1, 2, 3,…) separated by spaces: ");
            ingChoice1 = scanner.nextInt();
            ingChoice2 = scanner.nextInt();
            ingChoice3 = scanner.nextInt();

            if (ingChoice1 < 1 || ingChoice1 > 5 || ingChoice2 < 1 || ingChoice2 > 5 || ingChoice3 < 1 || ingChoice3 > 5) {
                System.out.println("Invalid choice(s). Please pick only from the given list.");
            }
        } while (ingChoice1 < 1 || ingChoice1 > 5 || ingChoice2 < 1 || ingChoice2 > 5 || ingChoice3 < 1 || ingChoice3 > 5);

        String ing1 = INGREDIENTS[ingChoice1 - 1];
        String ing2 = INGREDIENTS[ingChoice2 - 1];
        String ing3 = INGREDIENTS[ingChoice3 - 1];
        this.pizzaIngredients = ing1 + ", " + ing2 + ", " + ing3;

        // Pizza size selection
        int sizeChoice;
        do {
            System.out.println("What size should your pizza be?");
            for (int i = 0; i < SIZES.length; i++) {
                System.out.println((i + 1) + ". " + SIZES[i]);
            }
            System.out.print("Enter only one choice (1, 2, or 3): ");
            sizeChoice = scanner.nextInt();

            if (sizeChoice < 1 || sizeChoice > 3) {
                System.out.println("Invalid choice. Please enter a valid choice.");
            }
        } while (sizeChoice < 1 || sizeChoice > 3);
        this.pizzaSize = SIZES[sizeChoice - 1];

        // Extra cheese
        System.out.print("Do you want extra cheese (Y/N): ");
        this.extraCheese = scanner.next().equalsIgnoreCase("Y");

        // Side dish selection
        int sideDishChoice;
        do {
            System.out.println("Following are the side dishes that go well with your pizza:");
            for (int i = 0; i < SIDES.length; i++) {
                System.out.println((i + 1) + ". " + SIDES[i]);
            }
            System.out.print("What would you like? Pick one (1, 2, 3,…): ");
            sideDishChoice = scanner.nextInt();

            if (sideDishChoice < 1 || sideDishChoice > 5) {
                System.out.println("Invalid choice. Please enter a valid choice.");
            }
        } while (sideDishChoice < 1 || sideDishChoice > 5);
        this.sideDish = SIDES[sideDishChoice - 1];

        // Drink selection
        int drinkChoice;
        do {
            System.out.println("Choose from one of the drinks below. We recommend Coca Cola:");
            for (int i = 0; i < DRINKS.length; i++) {
                System.out.println((i + 1) + ". " + DRINKS[i]);
            }
            System.out.print("Enter your choice: ");
            drinkChoice = scanner.nextInt();

            if (drinkChoice < 1 || drinkChoice > 4) {
                System.out.println("Invalid choice. Please enter a valid choice.");
            }
        } while (drinkChoice < 1 || drinkChoice > 4);
        this.drink = DRINKS[drinkChoice - 1];

        // Discount
        System.out.print("Would you like the chance to pay only half for your order? (Y/N): ");
        this.wantDiscount = scanner.next().equalsIgnoreCase("Y");

        if (wantDiscount) {
            isItYourBirthday();
        } else {
            makeCardPayment();
        }
    }

    public void isItYourBirthday() {
        Scanner scanner = new Scanner(System.in);
        LocalDate birthdate;
        while (true) {
            System.out.print("Enter your birthdate (YYYY-MM-DD): ");
            String birthdateStr = scanner.next();
            birthdate = LocalDate.parse(birthdateStr, DateTimeFormatter.ISO_LOCAL_DATE);
            LocalDate today = LocalDate.now();
            Period age = Period.between(birthdate, today);

            if (age.getYears() < 5 || age.getYears() > 120) {
                System.out.println("Invalid date. You are either too young or too dead to order. Please enter a valid date.");
            } else {
                break;
            }
        }

        LocalDate today = LocalDate.now();
        boolean isBirthday = birthdate.getMonth() == today.getMonth() && birthdate.getDayOfMonth() == today.getDayOfMonth();

        if (isBirthday && Period.between(birthdate, today).getYears() < 18) {
            System.out.println("Congratulations! You pay only half the price for your order.");
            this.orderTotal *= 0.5;
        } else {
            System.out.println("Too bad! You do not meet the conditions to get our 50% discount.");
        }
    }

    public void makeCardPayment() {
        Scanner scanner = new Scanner(System.in);

        long cardNumber;
        while (true) {
            System.out.print("Enter your card number: ");
            cardNumber = scanner.nextLong();
            if (Long.toString(cardNumber).length() == 14 && cardNumber != BLACKLISTED_NUMBER) {
                break;
            }
            System.out.println("Invalid card number. Please enter a valid 14-digit card number.");
        }

        String expiryDate;
        while (true) {
            System.out.print("Enter the card's expiry date (YYYY-MM): ");
            expiryDate = scanner.next();
            LocalDate expiry = LocalDate.parse(expiryDate + "-01", DateTimeFormatter.ISO_LOCAL_DATE);
            if (expiry.isAfter(LocalDate.now())) {
                break;
            }
            System.out.println("Invalid expiry date. Please enter a future date.");
        }

        System.out.print("Enter the card's CVV: ");
        int cvv = scanner.nextInt();

        processCardPayment(cardNumber, expiryDate, cvv);
    }

    public void processCardPayment(long cardNumber, String expiryDate, int cvv) {
        String cardNumberStr = Long.toString(cardNumber);
        String cardNumberToDisplay = cardNumberStr.charAt(0) + "********" + cardNumberStr.substring(cardNumberStr.length() - 4);
        System.out.println("Card Number to Display: " + cardNumberToDisplay);
        System.out.println("Card accepted.");
    }

    @Override
    public String toString() {
        return "\nYour Order Details:\n" +
               "========================\n" +
               "Order ID: " + orderID + "\n" +
               "Pizza: " + pizzaIngredients + " - " + pizzaSize + "\n" +
               "Extra Cheese: " + (extraCheese ? "Yes" : "No") + "\n" +
               "Side: " + sideDish + "\n" +
               "Drink: " + drink + "\n" +
               "------------------------\n" +
               "Total: $" + String.format("%.2f", orderTotal) + "\n" +
               "========================\n" +
               "\nStore Address: " + storeAddress + "\n" +
               "Contact: " + storePhone + "\n" +
               "Email: " + storeEmail;
    }

    public static void main(String[] args) {
        SliceOHeaven order = new SliceOHeaven();
        order.takeOrder();
        System.out.println(order); // Calls toString() method
    }
}
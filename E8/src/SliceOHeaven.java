import java.util.Scanner;

public class SliceOHeaven {
    private static final double PIZZA_BASE_PRICE = 10.0;
    private String[] pizzasOrdered = new String[10];
    private String[] pizzasSizesOrdered = new String[10];
    private String[] sideDishesOrdered = new String[20];
    private String[] drinksOrdered = new String[20];
    private double totalOrderPrice = 0.0;
    private int orderCount = 0;

    enum PizzaSelection {
        PEPPERONI("Pepperoni", "Lots of pepperoni and extra cheese", 18),
        HAWAIIAN("Hawaiian", "Pineapple, ham, and extra cheese", 22),
        VEGGIE("Veggie", "Green pepper, onion, tomatoes, mushroom, and black olives", 25),
        BBQ_CHICKEN("BBQ Chicken", "Chicken in BBQ sauce, bacon, onion, green pepper, and cheddar cheese", 35),
        EXTRAVAGANZA("Extravaganza", "Pepperoni, ham, Italian sausage, beef, onions, green pepper, mushrooms, black olives, and extra cheese", 45);

        private final String pizzaName;
        private final String pizzaToppings;
        private final double price;

        PizzaSelection(String pizzaName, String pizzaToppings, double price) {
            this.pizzaName = pizzaName;
            this.pizzaToppings = pizzaToppings;
            this.price = price;
        }

        public String getPizzaName() {
            return pizzaName;
        }

        public String getPizzaToppings() {
            return pizzaToppings;
        }

        public double getPrice() {
            return price;
        }

        @Override
        public String toString() {
            return pizzaName + " Pizza with " + pizzaToppings + ", for €" + price;
        }
    }

    enum PizzaToppings {
        HAM("Ham", 2),
        PEPPERONI("Pepperoni", 2),
        BEEF("Beef", 2),
        CHICKEN("Chicken", 2),
        SAUSAGE("Sausage", 2),
        PINEAPPLE("Pineapple", 1),
        ONION("Onion", 0.5),
        TOMATOES("Tomatoes", 0.4),
        GREEN_PEPPER("Green Pepper", 0.5),
        BLACK_OLIVES("Black Olives", 0.5),
        SPINACH("Spinach", 0.5),
        CHEDDAR_CHEESE("Cheddar Cheese", 0.8),
        MOZZARELLA_CHEESE("Mozzarella Cheese", 0.8),
        FETA_CHEESE("Feta Cheese", 1),
        PARMESAN_CHEESE("Parmesan Cheese", 1);

        private final String topping;
        private final double toppingPrice;

        PizzaToppings(String topping, double toppingPrice) {
            this.topping = topping;
            this.toppingPrice = toppingPrice;
        }

        public String getTopping() {
            return topping;
        }

        public double getToppingPrice() {
            return toppingPrice;
        }

        @Override
        public String toString() {
            return topping + " - €" + toppingPrice;
        }
    }

    enum PizzaSize {
        LARGE("Large", 10),
        MEDIUM("Medium", 5),
        SMALL("Small", 0);

        private final String pizzaSize;
        private final double addToPizzaPrice;

        PizzaSize(String pizzaSize, double addToPizzaPrice) {
            this.pizzaSize = pizzaSize;
            this.addToPizzaPrice = addToPizzaPrice;
        }

        public String getPizzaSize() {
            return pizzaSize;
        }

        public double getAddToPizzaPrice() {
            return addToPizzaPrice;
        }

        @Override
        public String toString() {
            return pizzaSize + " - €" + addToPizzaPrice;
        }
    }

    enum SideDish {
        CALZONE("Calzone", 15),
        CHICKEN_PUFF("Chicken Puff", 20),
        MUFFIN("Muffin", 12),
        NOTHING("No side dish", 0);

        private final String sideDishName;
        private final double addToPizzaPrice;

        SideDish(String sideDishName, double addToPizzaPrice) {
            this.sideDishName = sideDishName;
            this.addToPizzaPrice = addToPizzaPrice;
        }

        public String getSideDishName() {
            return sideDishName;
        }

        public double getAddToPizzaPrice() {
            return addToPizzaPrice;
        }

        @Override
        public String toString() {
            return sideDishName + " - €" + addToPizzaPrice;
        }
    }

    enum Drinks {
        COCA_COLA("Coca Cola", 8),
        COCOA_DRINK("Cocoa Drink", 10),
        NOTHING("No drinks", 0);

        private final String drinkName;
        private final double addToPizzaPrice;

        Drinks(String drinkName, double addToPizzaPrice) {
            this.drinkName = drinkName;
            this.addToPizzaPrice = addToPizzaPrice;
        }

        public String getDrinkName() {
            return drinkName;
        }

        public double getAddToPizzaPrice() {
            return addToPizzaPrice;
        }

        @Override
        public String toString() {
            return drinkName + " - €" + addToPizzaPrice;
        }
    }

    public void takeOrder() {
        Scanner scanner = new Scanner(System.in);
        boolean moreOrders = true;

        while (moreOrders) {
            System.out.println("Welcome to Slice-o-Heaven Pizzeria. Here’s what we serve:");
            for (int i = 0; i < PizzaSelection.values().length; i++) {
                System.out.println((i + 1) + ". " + PizzaSelection.values()[i]);
            }
            System.out.println("6. Custom Pizza with a maximum of 10 toppings that you choose");
            System.out.print("Please enter your choice (1 - 6): ");
            int choice = scanner.nextInt();

            if (choice >= 1 && choice <= 5) {
                PizzaSelection selectedPizza = PizzaSelection.values()[choice - 1];
                pizzasOrdered[orderCount] = selectedPizza.toString();
                totalOrderPrice += selectedPizza.getPrice();
            } else if (choice == 6) {
                System.out.println("Choose up to 10 toppings:");
                for (int i = 0; i < PizzaToppings.values().length; i++) {
                    System.out.println((i + 1) + ". " + PizzaToppings.values()[i]);
                }
                System.out.print("Enter your choices (1-" + PizzaToppings.values().length + ") separated by spaces: ");
                scanner.nextLine(); // Consume newline
                String[] choices = scanner.nextLine().split(" ");
                StringBuilder customPizza = new StringBuilder("Custom Pizza with ");
                double customPrice = PIZZA_BASE_PRICE;

                for (String c : choices) {
                    int toppingChoice = Integer.parseInt(c) - 1;
                    customPizza.append(PizzaToppings.values()[toppingChoice].getTopping()).append(", ");
                    customPrice += PizzaToppings.values()[toppingChoice].getToppingPrice();
                }

                customPizza.append("for €").append(customPrice);
                pizzasOrdered[orderCount] = customPizza.toString();
                totalOrderPrice += customPrice;
            }

            System.out.println("Choose a size:");
            for (int i = 0; i < PizzaSize.values().length; i++) {
                System.out.println((i + 1) + ". " + PizzaSize.values()[i]);
            }
            int sizeChoice = scanner.nextInt();
            PizzaSize selectedSize = PizzaSize.values()[sizeChoice - 1];
            pizzasSizesOrdered[orderCount] = selectedSize.getPizzaSize();
            totalOrderPrice += selectedSize.getAddToPizzaPrice();

            System.out.println("Choose a side dish:");
            for (int i = 0; i < SideDish.values().length; i++) {
                System.out.println((i + 1) + ". " + SideDish.values()[i]);
            }
            int sideChoice = scanner.nextInt();
            SideDish selectedSide = SideDish.values()[sideChoice - 1];
            sideDishesOrdered[orderCount] = selectedSide.getSideDishName();
            totalOrderPrice += selectedSide.getAddToPizzaPrice();

            System.out.println("Choose a drink:");
            for (int i = 0; i < Drinks.values().length; i++) {
                System.out.println((i + 1) + ". " + Drinks.values()[i]);
            }
            int drinkChoice = scanner.nextInt();
            Drinks selectedDrink = Drinks.values()[drinkChoice - 1];
            drinksOrdered[orderCount] = selectedDrink.getDrinkName();
            totalOrderPrice += selectedDrink.getAddToPizzaPrice();

            orderCount++;

            System.out.print("Do you want to order more? (Y/N): ");
            moreOrders = scanner.next().equalsIgnoreCase("Y");
        }

        System.out.println(toString());
    }

    @Override
    public String toString() {
        StringBuilder orderDetails = new StringBuilder("Thank you for dining with Slice-o-Heaven Pizzeria. Your order details are as follows:\n");

        for (int i = 0; i < orderCount; i++) {
            orderDetails.append(i + 1).append(". ").append(pizzasOrdered[i]).append("\n");
            orderDetails.append(pizzasSizesOrdered[i]).append(": €").append(PizzaSize.valueOf(pizzasSizesOrdered[i]).getAddToPizzaPrice()).append("\n");
            orderDetails.append(sideDishesOrdered[i]).append(": €").append(SideDish.valueOf(sideDishesOrdered[i]).getAddToPizzaPrice()).append("\n");
            orderDetails.append(drinksOrdered[i]).append(": €").append(Drinks.valueOf(drinksOrdered[i]).getAddToPizzaPrice()).append("\n");
        }

        orderDetails.append("ORDER TOTAL: €").append(totalOrderPrice);
        return orderDetails.toString();
    }

    public static void main(String[] args) {
        SliceOHeaven pizzeria = new SliceOHeaven();
        pizzeria.takeOrder();
    }
}
public class App {
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
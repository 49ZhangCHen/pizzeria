import java.util.Scanner;

public class SliceOHeaven {
    // 店铺基本信息
    private String storeName = "Slice-o-Heaven";
    private String storeAddress = "123 Pizza Street, Cheese City";
    private String storeEmail = "order@sliceoheaven.com";
    private String storePhone = "1-800-PIZZA";
    
    // 菜单相关
    private String[] storeMenu = {"Margherita", "Pepperoni", "Vegetarian"};
    private String[] pizzaIngredients = {"Tomato, Mozzarella, Basil", 
                                      "Tomato, Mozzarella, Pepperoni", 
                                      "Tomato, Mozzarella, Vegetables"};
    private double[] pizzaPrice = {10.99, 12.99, 11.99};
    private String[] sides = {"Garlic Bread", "French Fries", "Chicken Wings"};
    private String[] drinks = {"Coke", "Sprite", "Iced Tea"};
    
    // 订单相关
    private String orderID;
    private double orderTotal;
    private String selectedPizza;
    private String selectedSide;
    private String selectedDrink;

    // 接收订单
    public void takeOrder() {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("★ Welcome to " + storeName + " ★");
        System.out.println("🍕 Our Menu:");
        
        // 显示披萨菜单
        for(int i = 0; i < storeMenu.length; i++) {
            System.out.println((i+1) + ". " + storeMenu[i] + " - $" + pizzaPrice[i]);
        }
        
        // 选择披萨
        System.out.print("\n请选择披萨（输入编号）: ");
        int pizzaChoice = scanner.nextInt() - 1;
        selectedPizza = storeMenu[pizzaChoice];
        
        // 选择配菜
        System.out.println("\n🍟 配菜选项:");
        for(int i = 0; i < sides.length; i++) {
            System.out.println((i+1) + ". " + sides[i]);
        }
        System.out.print("请选择配菜（输入编号）: ");
        int sideChoice = scanner.nextInt() - 1;
        selectedSide = sides[sideChoice];
        
        // 选择饮料
        System.out.println("\n🥤 饮料选项:");
        for(int i = 0; i < drinks.length; i++) {
            System.out.println((i+1) + ". " + drinks[i]);
        }
        System.out.print("请选择饮料（输入编号）: ");
        int drinkChoice = scanner.nextInt() - 1;
        selectedDrink = drinks[drinkChoice];
        
        // 计算总价（假设配菜和饮料各3美元）
        orderTotal = pizzaPrice[pizzaChoice] + 3.0 + 3.0;
        
        // 生成简单订单ID
        orderID = "ORDER" + (int)(Math.random() * 1000);
        
        scanner.close();
    }

    // 制作披萨
    public void makePizza() {
        System.out.println("\n👨🍳 正在制作你的 " + selectedPizza);
        System.out.println("配料包含：" + pizzaIngredients[getPizzaIndex(selectedPizza)]);
        System.out.println("预计需要10分钟...");
    }

    // 打印收据
    public void printReceipt() {
        System.out.println("\n🧾 你的订单明细：");
        System.out.println("══════════════════════════════");
        System.out.println("订单号: " + orderID);
        System.out.println("披萨: " + selectedPizza + " - $" + pizzaPrice[getPizzaIndex(selectedPizza)]);
        System.out.println("配菜: " + selectedSide + " - $3.00");
        System.out.println("饮料: " + selectedDrink + " - $3.00");
        System.out.println("──────────────────────────────");
        System.out.println("总价: $" + String.format("%.2f", orderTotal));
        System.out.println("══════════════════════════════");
        System.out.println("\n🏠 店铺信息：" + storeAddress);
        System.out.println("☎️ 联系电话：" + storePhone);
        System.out.println("📧 电子邮箱：" + storeEmail);
    }

    // 辅助方法：获取披萨索引
    private int getPizzaIndex(String pizzaName) {
        for(int i = 0; i < storeMenu.length; i++) {
            if(storeMenu[i].equals(pizzaName)) {
                return i;
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        SliceOHeaven pizzeria = new SliceOHeaven();
        pizzeria.takeOrder();
        pizzeria.makePizza();
        pizzeria.printReceipt();
    }
}
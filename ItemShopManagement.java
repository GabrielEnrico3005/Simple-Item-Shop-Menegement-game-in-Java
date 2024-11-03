import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

// Abstract class or Interface for items
abstract class Item {
    private String name;
    private String rarity;
    private int level;
    private int price;

    public Item(String name, String rarity, int level, int price) {
        this.name = name;
        this.rarity = rarity;
        this.level = level;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getRarity() {
        return rarity;
    }

    public int getLevel() {
        return level;
    }

    public int getPrice() {
        return price;
    }

    // Abstract method to be implemented by subclasses
    public abstract void displayInfo();
}

// Weapon class extending Item
class Weapon extends Item {
    private int attackPoint;

    public Weapon(String name, String rarity, int level, int price, int attackPoint) {
        super(name, rarity, level, price);
        this.attackPoint = attackPoint;
    }

    public int getAttackPoint() {
        return attackPoint;
    }

    // Implementation of the abstract method
    @Override
    public void displayInfo() {
        System.out.println("Weapon: " + getName());
        System.out.println("Rarity: " + getRarity());
        System.out.println("Level: " + getLevel());
        System.out.println("Price: " + getPrice());
        System.out.println("Attack Point: " + getAttackPoint());
        System.out.println("--------------------");
    }
}

// Armor class extending Item
class Armor extends Item {
    private int defensePoint;

    public Armor(String name, String rarity, int level, int price, int defensePoint) {
        super(name, rarity, level, price);
        this.defensePoint = defensePoint;
    }

    public int getDefensePoint() {
        return defensePoint;
    }

    // Implementation of the abstract method
    @Override
    public void displayInfo() {
        System.out.println("Armor: " + getName());
        System.out.println("Rarity: " + getRarity());
        System.out.println("Level: " + getLevel());
        System.out.println("Price: " + getPrice());
        System.out.println("Defense Point: " + getDefensePoint());
        System.out.println("--------------------");
    }
}

public class ItemShopManagement {
    public static void main(String[] args) {
        ArrayList<Item> itemList = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Menu:");
            System.out.println("1. Add new Item");
            System.out.println("2. View all added items");
            System.out.println("3. Delete Item");
            System.out.println("4. Exit");
            System.out.print("Enter your choice (1-4): ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    addItem(itemList, scanner);
                    break;
                case 2:
                    viewAllItems(itemList);
                    break;
                case 3:
                    deleteItem(itemList, scanner);
                    break;
                case 4:
                    System.out.println("Thank you for using the program!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 4.");
            }
        }
    }

    private static void addItem(ArrayList<Item> itemList, Scanner scanner) {
        System.out.println("Choose category:");
        System.out.println("1. Weapon");
        System.out.println("2. Armor");

        int categoryChoice = scanner.nextInt();

        switch (categoryChoice) {
            case 1:
                addWeapon(itemList, scanner);
                break;
            case 2:
                addArmor(itemList, scanner);
                break;
            default:
                System.out.println("Invalid choice. Please enter 1 or 2.");
        }
    }

    private static void addWeapon(ArrayList<Item> itemList, Scanner scanner) {
        System.out.print("Enter Weapon name (4-25 characters): ");
        scanner.nextLine(); // Consume the newline character
        String name = scanner.nextLine();
        while (name.length() < 4 || name.length() > 25) {
            System.out.print("Invalid length. Enter Weapon name (4-25 characters): ");
            name = scanner.nextLine();
        }

        System.out.print("Enter Weapon Rarity (Common, Rare, Epic, Legendary): ");
        String rarity = scanner.next();
        while (!isValidRarity(rarity)) {
            System.out.print("Invalid rarity. Enter Weapon Rarity (Common, Rare, Epic, Legendary): ");
            rarity = scanner.next();
        }

        System.out.print("Enter Weapon Level (1-1000): ");
        int level = getValidIntInput(scanner, 1, 1000);

        System.out.print("Enter Weapon Price (1000-9999999): ");
        int price = getValidIntInput(scanner, 1000, 9999999);

        System.out.print("Enter Attack Point: ");
        int attackPoint = getValidIntInput(scanner, 0, Integer.MAX_VALUE);

        Weapon weapon = new Weapon(name, rarity, level, price, attackPoint);
        itemList.add(weapon);

        System.out.println("Weapon added successfully!");
    }

    private static void addArmor(ArrayList<Item> itemList, Scanner scanner) {
        System.out.print("Enter Armor name (4-25 characters): ");
        scanner.nextLine(); // Consume the newline character
        String name = scanner.nextLine();
        while (name.length() < 4 || name.length() > 25) {
            System.out.print("Invalid length. Enter Armor name (4-25 characters): ");
            name = scanner.nextLine();
        }

        System.out.print("Enter Armor Rarity (Common, Rare, Epic, Legendary): ");
        String rarity = scanner.next();
        while (!isValidRarity(rarity)) {
            System.out.print("Invalid rarity. Enter Armor Rarity (Common, Rare, Epic, Legendary): ");
            rarity = scanner.next();
        }

        System.out.print("Enter Armor Level (1-1000): ");
        int level = getValidIntInput(scanner, 1, 1000);

        System.out.print("Enter Armor Price (1000-9999999): ");
        int price = getValidIntInput(scanner, 1000, 9999999);

        System.out.print("Enter Defense Point: ");
        int defensePoint = getValidIntInput(scanner, 0, Integer.MAX_VALUE);

        Armor armor = new Armor(name, rarity, level, price, defensePoint);
        itemList.add(armor);

        System.out.println("Armor added successfully!");
    }

    private static void viewAllItems(ArrayList<Item> itemList) {
        if (itemList.isEmpty()) {
            System.out.println("No items in the list.");
        } else {
            for (Item item : itemList) {
                item.displayInfo();
            }
        }
    }

    private static void deleteItem(ArrayList<Item> itemList, Scanner scanner) {
        viewAllItems(itemList);

        if (!itemList.isEmpty()) {
            System.out.print("Choose an item to delete (1-" + itemList.size() + "): ");
            int choice = getValidIntInput(scanner, 1, itemList.size());

            Item deletedItem = itemList.remove(choice - 1);
            System.out.println("Item deleted successfully:");
            deletedItem.displayInfo();
        }
    }

    private static boolean isValidRarity(String rarity) {
        return rarity.equals("Common") || rarity.equals("Rare") || rarity.equals("Epic") || rarity.equals("Legendary");
    }

    private static int getValidIntInput(Scanner scanner, int min, int max) {
        int input = 0;
        boolean isValid = false;

        while (!isValid) {
            try {
                input = scanner.nextInt();
                if (input >= min && input <= max) {
                    isValid = true;
                } else {
                    System.out.print("Invalid input. Enter a number between " + min + " and " + max + ": ");
                }
            } catch (InputMismatchException e) {
                System.out.print("Invalid input. Enter a valid number: ");
                scanner.next(); // Consume the invalid input
            }
        }

        return input;
    }
}

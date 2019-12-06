//package machine;
import java.util.Scanner;

public class CoffeeMachine {
    public static void main(String[] args) {
        CoffeeMachine machine = new CoffeeMachine(400, 540, 120, 9, 550);
        machine.work();
    }

    enum State {
        CHOOSING_ACTION,
        BUYING,
        FILLING,
        TAKING,
        CHECKING_SUPPLIES,
        OFF;
    }

    private int waterLeft;
    private int milkLeft;
    private int beansLeft;
    private int cupsLeft;
    private int moneyLeft;
    private State state;

    private Scanner scanner = new Scanner(System.in);

    private CoffeeMachine(int water, int milk, int beans, int cups, int money){
        this.waterLeft = water;
        this.milkLeft = milk;
        this.beansLeft = beans;
        this.cupsLeft = cups;
        this.moneyLeft = money;
        this.state = State.CHOOSING_ACTION;
    }

    private void work(){
        do {
            System.out.println("Write action (buy, fill, take, remaining, exit):");
            processAction(scanner.nextLine());
        } while (state != State.OFF);
    }

    private void processAction(String action){
        switch (action){
            case "buy":
                state = State.BUYING;
                System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:");
                chooseOption(scanner.next());
                System.out.println();
                state = State.CHOOSING_ACTION;
                break;
            case "fill":
                this.state = State.FILLING;
                System.out.println();
                addSupplies();
                System.out.println();
                state = State.CHOOSING_ACTION;
                break;
            case "take":
                this.state = State.TAKING;
                getMoney();
                System.out.println();
                state = State.CHOOSING_ACTION;
                break;
            case "remaining":
                this.state = State.CHECKING_SUPPLIES;
                printState();
                System.out.println();
                state = State.CHOOSING_ACTION;
                break;
            case "exit":
                this.state = State.OFF;
                break;
        }
    }

    private void printState(){
        System.out.println("The coffee machine has:");
        System.out.println(waterLeft + " of water");
        System.out.println(milkLeft + " of milk");
        System.out.println(beansLeft + " of coffee beans");
        System.out.println(cupsLeft + " of disposable cups");
        System.out.println(moneyLeft + " of money");
    }

    private void addSupplies(){
        System.out.println("Write how many ml of water do you want to add:");
        int waterAdded = scanner.nextInt();
        System.out.println("Write how many ml of milk do you want to add:");
        int milkAdded = scanner.nextInt();
        System.out.println("Write how many grams of coffee beans do you want to add: ");
        int beansAdded = scanner.nextInt();
        System.out.println("Write how many disposable cups of coffee do you want to add:");
        int cupsAdded = scanner.nextInt();

        waterLeft += waterAdded;
        milkLeft += milkAdded;
        beansLeft += beansAdded;
        cupsLeft += cupsAdded;
    }

    private void getMoney(){
        moneyLeft -= moneyLeft;
        System.out.println("I gave you $" + moneyLeft);
    }

    private void chooseOption(String option){
        switch (option){
            case "1":
                makeCoffee(250, 0, 16, 4);
                break;
            case "2":
                makeCoffee(350, 75, 20, 7);
                break;
            case "3":
                makeCoffee(200, 100, 12, 6);
                break;
            case "back":
                this.state = State.CHOOSING_ACTION;
                break;

        }
    }

    private void makeCoffee(int water, int milk, int beans, int price){
        if (waterLeft >= water && beansLeft >= beans && milkLeft >= milk && cupsLeft >= 1) {
            System.out.println("I have enough resources, making you a coffee!");
            waterLeft -= water;
            milkLeft -= milk;
            beansLeft -= beans;
            moneyLeft += price;
            cupsLeft--;
        } else {
            if (waterLeft < water) {
                System.out.println("Sorry, not enough water!");
            } else if (milkLeft < milk) {
                System.out.println("Sorry, not enough milk!");
            } else if (beansLeft < beans) {
                System.out.println("Sorry, not enough coffee beans!");
            } else {
                System.out.println("Sorry, not enough disposable cups!");
            }
        }
    }

}

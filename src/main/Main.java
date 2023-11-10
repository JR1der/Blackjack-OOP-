package main;

// import of packages and classes from these packages

import blackjack_req.Deck;
import blackjack_req.Hand;
import blackjack_req.Table;

import java.util.Scanner; // scanner to input


public class Main {

    /**
     * Main func
     *
     * Has choice menu in it with switch
     * If you pick 1 starts the game from choosing the table
     * If you pick 2 shows current balance
     * If you pick 3 exits the program and finishes the game
     *
     * @author Ivan Popovych
     */
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int userChoice;
        System.out.println("Type in your balance:");
        double balance = scan.nextDouble(); // input of start balance
        if (balance <= 0) { // output if the value is incorrect
            System.out.println("You don't have money to play, so go earn some and return back");
            return;
        }
        boolean exitRequested = false; // boolean to check if you had pressed the exit button (3)
        while (!exitRequested) {
            userChoice = Menu(); // user choice variable that collects inout from the user
            switch (userChoice) { // switch menu
                case 1:
                    balance = Game(balance);
                    break;
                case 2:
                    System.out.println("Your current balance is: " + balance + '$'); // player balance check
                    break;
                case 3:
                    System.out.println("Exiting the program");
                    exitRequested = true;
                default:
                    System.out.println("...");
            }
        }


    }

    /**
     * Main menu
     *
     * @return choice of the user in menu
     */
    public static int Menu() {
        Scanner scan = new Scanner(System.in);
        int choice; // init of choice variable
        System.out.println("Choose what you want to do:");
        System.out.println("___________________________");
        System.out.println("1 - Choose the table");
        System.out.println("2 - Show current balance");
        System.out.println("3 - Exit");
        System.out.println("___________________________");
        System.out.println("You picked:");
        choice = scan.nextInt();
        return choice; // returns choice in the main
    }

    /**
     * Game function
     *
     * @param balance of the player on the start of the game
     *
     * @return balance of the player after playing this session
     */
    public static double Game(double balance) {
        Scanner scan = new Scanner(System.in);

        System.out.println("Choose your table");
        House house = new House();

        // If balance of the player is lower than the minimal bet on the table exits from it

        if (balance < house.tables[0].mnBid) {
            System.out.println("Earn some money and return back");
            System.exit(0);
        }

        // Print of list of the tables

        for (int i = 0; i < 3; i++) {
            System.out.println(i + 1 + " table has limit of bids " + house.tables[i].mnBid + '$' + " - " + house.tables[i].mxBid + '$');
        }
        int choice = 0;

        /*
        Doing a choice code

        If the balance is lower than the table minimal bet throws an error

        If picks the right one enters in bet menu
         */

        while (true) {
            System.out.println("You picked:");
            choice = scan.nextInt();
            if (balance >= house.tables[choice - 1].mnBid) {

                System.out.println("Your table is: " + choice);
                break;
            }
            if (choice > 3 || choice < 1) {
                System.out.println("There is no such table");
            }
            if (balance < house.tables[choice - 1].mnBid) {
                System.out.println("Min bid of the table is too high for you, pick another table");
            }
        }

        /*
        The loop is working until you have money, and until you have enough money to place a bet on the chosen table

        If instead of the bet you write -1 exits to the main menu
         */

        while (balance > 0 && balance >= house.tables[choice - 1].mnBid) {
            double bet;
            boolean ex = false;
            System.out.println("Your balance is: " + balance + "$ , enter your bet, (-1) to go back to menu");
            while (true) {
                bet = scan.nextDouble();
                if (bet == -1) {
                    return balance;
                }
                if (bet > balance) { // if your balance is lower that the bet you want to do
                    System.out.println("You don't have that amount of money, enter proper amount");
                } else if (bet < house.tables[choice - 1].mnBid) { // if the bet is too low for this table
                    System.out.println("The bet is too low for this table, enter proper amount");
                } else if (bet > house.tables[choice - 1].mxBid) { // if the bet is to high for this table
                    System.out.println("The bet is too high for this table, enter proper amount");
                } else { // if you placed the correct bet
                    System.out.println("Your bet is: " + bet + '$');
                    break;
                }
            }

            // After doing the bet decreases balance

            balance -= bet;

            // Creating deck and shuffling

            Deck deck = new Deck();
            deck.createDeck();
            deck.shuffleDeck();

            // Creating dealer hand and player hand

            Hand player = new Hand();
            Hand dealer = new Hand();

            // Adds cards to the dillers deck and players deck

            dealer.addCardHand(deck.giveCards());
            dealer.addCardHand(deck.giveCards());

            // Prints your cards and top card of the dealer

            player.addCardHand(deck.giveCards());
            player.addCardHand(deck.giveCards());
            System.out.println("Your cards value: (" + player.getValueHand() + "):");
            player.coutCard(0);
            System.out.println("Dealers card value: ( ? ):");
            dealer.coutCard(1);

            // if on the start of the round you already have 21 than it's a blackjack and you win

            if (player.getValueHand() == 21) {
                System.out.println("VICTORY!!! You have BLACKJACK!!!");
                balance += 2.5 * bet; // returns you your bet and adds 150% of the bet to your balance
            } else { // else starts picking or holding option
                int in = 1; // init of the choice (pick, hold) variable
                while (player.getValueHand() < 21) { // loop is working until you reach 21 or more

                    System.out.println("1 - Pick a new card"); // output of choices
                    System.out.println("2 - Hold");
                    in = scan.nextInt(); // takes input from the user
                    if (in > 2 && in < 1) System.out.println("Invalid choice...");
                    else if (in == 1) {
                        player.addCardHand(deck.giveCards()); // gives new card and outputs your all your cards
                        System.out.println("Your cards value: (" + player.getValueHand() + "):");
                        player.coutCard(0);
                        System.out.println("Dealers card value: ( ? ):");
                        dealer.coutCard(1);
                    } else {
                        break; // hold == stop of the round and shows the results
                    }
                }

                /*
                since the dealer can pick cards only until he has 17,
                as in the rules the loop is working until
                the value of the cards is 17
                 */

                while (dealer.getValueHand() < 17) {
                    dealer.addCardHand(deck.giveCards());
                }
                int playerValue = player.getValueHand(); // value of players cards
                int dealerValue = dealer.getValueHand(); // value of dealers cards
                if (dealerValue == playerValue) {
                    System.out.println("DRAW! You have the same value of cards as dealer"); // output if draw
                    balance += bet; // returns the bet to your balance
                } else if (dealerValue > playerValue && dealerValue <= 21) {
                    System.out.println("LOSS! You have lost to the dealer, he had: " + dealer.getValueHand()); // output if lost
                } else if (playerValue <= 21) {
                    System.out.println("VICTORY! You won: " + bet + "$ and the dealer had: " + dealer.getValueHand()); // output if won
                    balance += bet * 2; // adds to your balance your bet + 100% of your bet
                } else if (dealerValue <= 21 && playerValue > 21) {
                    System.out.println("LOSS! You have too much points, he had: " + dealer.getValueHand()); // outpu if you lost because of big amount of points
                } else if (dealerValue > 21 && playerValue > 21) {
                    balance += bet; // return the bet to your balance
                    System.out.println("DRAW! You and the dealer picked too much cards and the dealer had had: " + dealer.getValueHand()); // output if draw when two have too much points
                }
            }
            if (balance < house.tables[choice - 1].mnBid) { // output when your balance is lower than minimal bet of the table
                System.out.println("You don't have money to play on this table, so you need to change it...");
            }
        }
        if (balance <= 0) { // output and exit if your balance is 0 or lower
            System.out.println("You lost everything and don't have money to play on every table");
            System.exit(0);
        }
        return balance; // returns the balance after end of the Game func, so it will change the balance that was before
    }

}
package main;

import blackjack_req.Deck;
import blackjack_req.Hand;
import blackjack_req.Table;

import java.util.Scanner;


public class Main {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int userChoice;
        System.out.println("Type in your balance:");
        double balance = scan.nextDouble();
        if (balance <= 0) {
            System.out.println("You don't have money to play, so go earn some and return back");
            return;
        }
        boolean exitRequested = false;
        while (!exitRequested) {
            userChoice = Menu();
            switch (userChoice) {
                case 1:
                    balance = Game(balance);
                    break;
                case 2:
                    System.out.println("Your current balance is: " + balance + '$');
                    break;
                case 3:
                    System.out.println("Exiting the program");
                    exitRequested = true;
                default:
                    System.out.println("...");
            }
        }


    }

    public static int Menu() {
        Scanner scan = new Scanner(System.in);
        int choice;
        System.out.println("Choose what you want to do:");
        System.out.println("___________________________");
        System.out.println("1 - Choose the table");
        System.out.println("2 - Show current balance");
        System.out.println("3 - Exit");
        System.out.println("___________________________");
        System.out.println("You picked:");
        choice = scan.nextInt();
        return choice;
    }

    public static double Game(double balance) {
        Scanner scan = new Scanner(System.in);

        System.out.println("Choose your table");
        House house = new House();
        if (balance < house.tables[0].mnBid) {
            System.out.println("Earn some money and return back");
            System.exit(0);
        }
        for (int i = 0; i < 3; i++) {
            System.out.println(i + 1 + " table has limit of bids " + house.tables[i].mnBid + '$' + " - " + house.tables[i].mxBid + '$');
        }
        int choice = 0;
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
        while (balance > 0 && balance >= house.tables[choice - 1].mnBid) {
            double bet;
            boolean ex = false;
            System.out.println("Your balance is: " + balance + "$ , enter your bet, (-1) to go back to menu");
            while (true) {
                bet = scan.nextDouble();
                if (bet == -1) {
                    return balance;
                }
                if (bet > balance) {
                    System.out.println("You don't have that amount of money, enter proper amount");
                } else if (bet < house.tables[choice - 1].mnBid) {
                    System.out.println("The bet is too low for this table, enter proper amount");
                } else if (bet > house.tables[choice - 1].mxBid) {
                    System.out.println("The bet is too high for this table, enter proper amount");
                } else {
                    System.out.println("Your bet is: " + bet + '$');
                    break;
                }
            }
            balance -= bet;
            Deck deck = new Deck();
            deck.createDeck();
            deck.shuffleDeck();

            Hand player = new Hand();
            Hand dealer = new Hand();

            dealer.addCardHand(deck.giveCards());
            dealer.addCardHand(deck.giveCards());

            player.addCardHand(deck.giveCards());
            player.addCardHand(deck.giveCards());
            System.out.println("Your cards value: (" + player.getValueHand() + "):");
            player.coutCard(0);
            System.out.println("Dealers card value: ( ? ):");
            dealer.coutCard(1);

            if (player.getValueHand() == 21) {
                System.out.println("VICTORY!!! You have BLACKJACK!!!");
                balance += 2.5 * bet;
            } else {
                int in = 1;
                while (player.getValueHand() < 21) {

                    System.out.println("1 - Pick a new card");
                    System.out.println("2 - Hold");
                    in = scan.nextInt();
                    if (in > 2 && in < 1) System.out.println("Invalid choice...");
                    else if (in == 1) {
                        player.addCardHand(deck.giveCards());
                        System.out.println("Your cards value: (" + player.getValueHand() + "):");
                        player.coutCard(0);
                        System.out.println("Dealers card value: ( ? ):");
                        dealer.coutCard(1);
                    } else {
                        break;
                    }
                }
                while (dealer.getValueHand() < 17) {
                    dealer.addCardHand(deck.giveCards());
                }
                int playerValue = player.getValueHand();
                int dealerValue = dealer.getValueHand();
                if (dealerValue == playerValue) {
                    System.out.println("DRAW! You have the same value of cards as dealer");
                    balance += bet;
                } else if (dealerValue > playerValue && dealerValue <= 21) {
                    System.out.println("LOSS! You have lost to the dealer, he had: " + dealer.getValueHand());
                } else if (playerValue <= 21) {
                    System.out.println("VICTORY! You won: " + bet + "$ and the dealer had: " + dealer.getValueHand());
                    balance += bet * 2;
                } else if (dealerValue <= 21 && playerValue > 21) {
                    System.out.println("LOSS! You have too much points, he had: " + dealer.getValueHand());
                } else if (dealerValue > 21 && playerValue > 21) {
                    balance += bet;
                    System.out.println("DRAW! You and the dealer picked too much cards and the dealer had had: " + dealer.getValueHand());
                }
            }
            if (balance < house.tables[choice - 1].mnBid) {
                System.out.println("You don't have money to play on this table, so you need to change it...");
            }
        }
        if (balance <= 0) {
            System.out.println("You lost everything and don't have money to play on every table");
            System.exit(0);
        }
        return balance;
    }

}
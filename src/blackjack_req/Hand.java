package blackjack_req;

import java.util.ArrayList;

public class Hand {
    public ArrayList<Card> hand;

    public Hand() {
        this.hand = new ArrayList<Card>();
    }

    public void addCardHand(Card card) {
        this.hand.add(card);
    }

    public void clearHand() {
        this.hand.clear();
    }

    public void coutCard(int a) {
        if (a == 1) {
            System.out.print(this.hand.get(0).get_value());
            System.out.println(this.hand.get(0).get_suit());
        } else {
            for (Card card : this.hand) {
                System.out.print(card.get_value());
                System.out.println(card.get_suit());
            }
        }
    }

    public int getValueHand() {
        int sum = 0, ace = 0;
        for (Card card : this.hand) {
            switch (card.get_value()) {
                case "2":
                    sum += 2;
                    break;
                case "3":
                    sum += 3;
                    break;
                case "4":
                    sum += 4;
                    break;
                case "5":
                    sum += 5;
                    break;
                case "6":
                    sum += 6;
                    break;
                case "7":
                    sum += 7;
                    break;
                case "8":
                    sum += 8;
                    break;
                case "9":
                    sum += 9;
                    break;
                case "10":
                    sum += 10;
                    break;
                case "K":
                    sum += 10;
                    break;
                case "Q":
                    sum += 10;
                    break;
                case "J":
                    sum += 10;
                    break;
                case "A":
                    ace++;
                    break;
            }
        }
        if (sum == 21) {
            return sum;
        }
        for (int i = 0; i < ace; i++) {
            if (sum > 10) {
                sum++;
            } else sum += 11;
        }
        return sum;
    }
}

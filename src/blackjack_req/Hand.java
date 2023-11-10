package blackjack_req;



import java.util.ArrayList;

/**
 *
 * Class hand
 *
 * Class hand created to store cards which player or dealer has in hands
 */
public class Hand {
    public ArrayList<Card> hand; // creates an array to store the cards in hand

    public Hand() {
        this.hand = new ArrayList<Card>();
    }

    public void addCardHand(Card card) {
        this.hand.add(card); // adds card to the array
    }

    public void clearHand() {
        this.hand.clear(); // clears card from the array
    }

    public void coutCard(int a) {
        if (a == 1) { // if calling this func with a parameter 1 outputs only 1st card of the array (output for the dealer)
            System.out.print(this.hand.get(0).get_value());
            System.out.println(this.hand.get(0).get_suit());
        } else { // outputs all the cards (output for player)
            for (Card card : this.hand) {
                System.out.print(card.get_value());
                System.out.println(card.get_suit());
            }
        }
    }

    public int getValueHand() {
        int sum = 0, ace = 0; // init variables of number of ace cards and overall sum
        for (Card card : this.hand) {
            switch (card.get_value()) { // if card has some value, adds it's value to the sum
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
                case "A": // if the card has is the ace, adds 1 to the number of aces
                    ace++;
                    break;
            }
        }
        if (sum == 21) { // if sum == 21 return sum
            return sum;
        }
        for (int i = 0; i < ace; i++) { // loop to count values of aces
            if (sum > 10) { // if the value of cards is bigger then 10 adds only one if you have an ace in your hand
                sum++;
            } else sum += 11; // otherwise adds 11
        }
        return sum; // return sum
    }
}

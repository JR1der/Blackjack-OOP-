package blackjack_req;

import java.util.Random;
import java.util.ArrayList;

public class Deck {
    private ArrayList<Card> cards = new ArrayList<Card>();

    public void createDeck() {
        for (Value value : Value.values()) {
            for (Suit suit : Suit.values()) {
                this.cards.add(new Card(suit, value));
            }
        }
    }

    public void shuffleDeck() {
        ArrayList<Card> t = new ArrayList<Card>();
        Random random = new Random();

        for (int i = 0; i < this.cards.size(); i++) {
            int index = random.nextInt(this.cards.size());
            t.add(this.cards.get(index));
            this.cards.remove(index);
        }
        this.cards = t;

    }

    public Card giveCards() {
        Random random = new Random();
        int index = random.nextInt(this.cards.size());
        Card t = this.cards.get(index);
        this.cards.remove(index);
        return t;
    }
}

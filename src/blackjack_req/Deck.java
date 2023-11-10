package blackjack_req;

/**
 *
 * Class deck
 *
 * This class creates an array of cards
 * Also it adds 52 cards to a deck
 * When calling shuffleDeck function shuffles the deck using random
 * Gives cards to a player or a dealer
 */


// import of random to shuffle deck
import java.util.Random;
import java.util.ArrayList;

public class Deck {
    private ArrayList<Card> cards = new ArrayList<Card>(); // creates new array of cards

    public void createDeck() {
        for (Value value : Value.values()) {
            for (Suit suit : Suit.values()) {
                this.cards.add(new Card(suit, value)); // cycles through all values and suits and creates all 52 cards
            }
        }
    }

    public void shuffleDeck() {
        ArrayList<Card> t = new ArrayList<Card>(); // creates a temporary array to store shuffled cards
        Random random = new Random();

        for (int i = 0; i < this.cards.size(); i++) {
            int index = random.nextInt(this.cards.size()); // init index of a random card from the array
            t.add(this.cards.get(index)); // adds this card to a temporary array
            this.cards.remove(index); // removes this card from our main array
        }
        this.cards = t; // our main array gains data from temporary one

    }

    public Card giveCards() {
        Random random = new Random();
        int index = random.nextInt(this.cards.size()); // init index of a random card from the array
        Card t = this.cards.get(index); // takes a random card
        this.cards.remove(index); // removes this card from array
        return t; // return the card
    }
}

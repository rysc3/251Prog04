import java.util.Collection;
import java.util.Collections;
import java.util.List;

public final class Deck {
    private final List<Card> cards;

    public Deck(List<Card> cards) {
        this.cards = cards;
        shuffleDeck();
    }

    public static class EmptyDeckException extends Exception {}

    /**
     * This function does the following:
     * - Checks if cards is empty
     *   - If it is then throw a new EmptyDeckException
     *   - If not then return and remove the first card in cards
     * @return The top card from the deck
     * @throws EmptyDeckException
     * TODO: Implement this
     */
    public Card drawCard() throws EmptyDeckException {
        return null;
    }

    public void shuffleDeck() {
        Collections.shuffle(this.cards);
    }

    public void addCards(Collection<Card> cards) {
        this.cards.addAll(cards);
        shuffleDeck();
    }
}

import java.util.ArrayList;

public class Player {
    private final String name;
    private final Game game;
    private final Hand hand;

    public Player(String name, Game game) {
        this.name = name;
        this.game = game;
        this.hand = new Hand(new ArrayList<>());
    }

    /**
     * This function does the following:
     * - Attempts to draw num number of cards
     * - If a EmptyDeckException is caught then the play area
     *   must be shuffled into the deck. Note this a function of game class
     * - Adds each drawn card to hand
     * @param num Number of cards to be drawn
     * TODO: Implement this
     */
    public void drawCards(int num) {

    }

    /**
     * Performs IO to figure out what moves the user
     * wants to make. It does this as follows:
     * - Loops until the user has successfully played a card
     * - Prints out "Play area:\n"
     * - Prints out the top card
     * - Checks to see if the hand has any matches against the top card
     *   - If it does not then print: "Your hand had no matches, a card was drawn."
     *   - Then draw 1 card
     * - Then prints "Hand:\n"
     * - Then prints out the hand
     * - If the hand still has no matches then print: "Your hand still has no matches your turn is being passed"
     *   and ends the turn
     * - Otherwise it asks the user: "Which card would you like to play?" using the game::interact function
     * - The code loops until the user successfully answers this question, the three criteria are:
     *   - A valid int, if not print:
     *     "$cardNumStr is not a valid integer, please try again."
     *     where cardNumStr is the user input
     *   - A valid match, if not print:
     *     "Card $cardNumStr cannot currently be played, please try again."
     *     where cardNumStr is the user input
     *   - A valid index, if not print:
     *     "$cardNumStr is not a valid index, please try again."
     * TODO: Implement this
     */
    public void takeTurn() {

    }

    public boolean emptyHand() {
        return hand.numCardsRemaining() == 0;
    }

    @Override
    public String toString() {
        return name;
    }
}

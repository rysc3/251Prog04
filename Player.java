
/*
 * @author Ryan Scherbarth
 * cs251
 * 3/29/23
 */
import java.util.ArrayList;

public class Player {
  private final String name;
  private final Game game;
  public final Hand hand;

  public Player(String name, Game game) {
    this.name = name;
    this.game = game;
    this.hand = new Hand(new ArrayList<>());
  }

  /**
   * This function does the following:
   * - Attempts to draw num number of cards
   * - If a EmptyDeckException is caught then the play area
   * must be shuffled into the deck. Note this a function of game class
   * - Adds each drawn card to hand
   *
   * @param num Number of cards to be drawn
   *            D_TODO: Implement this
   */
  public void drawCards(int num) {
    try {
      for (int i = 0; i < num; i++) {
        Card currentCard = game.getDeck().drawCard();
        hand.addCard(currentCard);
      }
    } catch (Deck.EmptyDeckException e) { // Deck throws empty deck
      System.out.println("Shuffling play area into the deck again.");
      game.shufflePlayAreaIntoDeck(); // reshuffle
    }
  }

  /**
   * Performs IO to figure out what moves the user
   * wants to make. It does this as follows:
   * - Loops until the user has successfully played a card
   * - Prints out "Play area:\n"
   * - Prints out the top card
   * - Checks to see if the hand has any matches against the top card
   * - If it does not then print: "Your hand had no matches, a card was drawn."
   * - Then draw 1 card
   * - Then prints "Hand:\n"
   * - Then prints out the hand
   * - If the hand still has no matches then print: "Your hand still has no
   * matches your turn is being passed"
   * and ends the turn
   * - Otherwise it asks the user: "Which card would you like to play?" using the
   * game::interact function
   * - The code loops until the user successfully answers this question, the three
   * criteria are:
   * - A valid int, if not print:
   * "$cardNumStr is not a valid integer, please try again."
   * where cardNumStr is the user input
   * - A valid match, if not print:
   * "Card $cardNumStr cannot currently be played, please try again."
   * where cardNumStr is the user input
   * - A valid index, if not print:
   * "$cardNumStr is not a valid index, please try again."
   * D_TODO: Implement this
   */
  public void takeTurn() {
    boolean valid = false;

    while (!valid) {

      // Loop through to handle cases where you don't start with a valid card to be played.
      if (hand.noMatches(game.getTopCard())) {
        System.out.println("Your hand had no matches, a card was drawn.");  // Case: no match on first turn
        drawCards(1);

        // Check if hand still has no matches
        if (hand.noMatches(game.getTopCard())) {
          System.out.println("Your hand still has no matches, your turn is being passed");  // Case: no match after second draw
          drawCards(1);
          valid = true; // end the turn
        }else{  // Case: no match to start, but card that was drawn was a match
          System.out.println(game.printInfo(this));
          String inputStr = game.interact("Which card would you like to play?");
          try{
            int inputInt = Integer.parseInt(inputStr);
            if(inputInt < 0 || inputInt > hand.numCardsRemaining()){
              game.interact(inputStr + " is not a valid index, please try again.");
            }else{
              hand.playCard(game, inputInt);
              valid = true;   // end turn
            }
          }
          catch(Card.CannotPlayCardException j){
            game.interact("Card " + inputStr + " cannot currently be played.");
          }
        }
      }else{
        // Case: user has a valid card to be played right away
        String inputStr = game.interact("Which card would you like to play?");
        try {
          int inputInt = Integer.parseInt(inputStr);
          if(inputInt < 0 || inputInt > hand.numCardsRemaining()){
            game.interact(inputStr + " is not a valid index, please try again.");
          }else{
            hand.playCard(game, inputInt);
            valid = true;
          }
        }
        catch(Card.CannotPlayCardException c){
          // game.interact("Card " + inputStr + " cannot currently be played, please try again.");
          System.out.println("Card " + inputStr + " cannot currently be played.");
        }
      }
    }
  }

  // * another helper method, prints some space inbewteen each
  // * play so its more clear and easier to tell when its a new
  // * turn.
  // */
  public static void giveSpace() {
    for (int i = 0; i < 5; i++) {
      System.out.println("\n");
    }
  }

  public boolean emptyHand() {
    return hand.numCardsRemaining() == 0;
  }

  @Override
  public String toString() {
    return name;
  }
}

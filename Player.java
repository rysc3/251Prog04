/*
 * @author Ryan Scherbarth
 * cs251
 * 3/29/23
 */
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
   * must be shuffled into the deck. Note this a function of game class
   * - Adds each drawn card to hand
   *
   * @param num Number of cards to be drawn
   *            D_TODO: Implement this
   */
  public void drawCards(int num) {
    try{
      for(int i=0; i<num; i++){
        Card currentCard = game.getDeck().drawCard();
        hand.addCard(currentCard);
      }
    }
    catch(Deck.EmptyDeckException e){   // Deck throws empty deck
      game.shufflePlayAreaIntoDeck();   // reshuffle
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
   * TODO: Implement this
   */
  public void takeTurn() {
    /*
     * create int numCards and set it to numCardsRemaining in the current hand
     * if the number of cards remaining (the player draws or plays a card) then
     * the turn is over.
     */
    int numCards = hand.numCardsRemaining();
    int invalidTries = 0;

    while(hand.numCardsRemaining() == numCards){
      // Reprint the info if user put invalid input too many times
      if(invalidTries == 5){
        System.out.println("Play area:\n");
        game.getTopCard().prettyPrint();  // print deck card pretty
        System.out.println("Hand:\n");
        hand.toString();  // Prints all cards in hand pretty
        invalidTries = 0;
      }

      if(emptyHand() || hand.noMatches(game.getTopCard())){   // User doesn't have valid cards to play
        System.out.println("Your hand still has no matches your turn is being passed");
        /*
         * user has no valid cards, draw a card, if the next card is also not valid, skip his turn
         * if the card is valid, print out info again so he can play.
         */
        if(hand.noMatches(game.getTopCard())){
          System.out.println("Your hand still has no matches your turn is being passed");
        }else{
          // User's first turn wasn't playable, but their drawn card is playable

        }

      }else{  // If hand starts off with a match

      }
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

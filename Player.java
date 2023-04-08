
/*
 * @author Ryan Scherbarth
 * cs251
 * 3/29/23
 */
import java.util.ArrayList;
import java.util.Scanner;

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
    try {
      for (int i = 0; i < num; i++) {
        Card currentCard = game.getDeck().drawCard();
        hand.addCard(currentCard);
      }
    } catch (Deck.EmptyDeckException e) { // Deck throws empty deck
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
  // TODO figure out how to use giveSpace after each turn I don't wanna do this
  // til last
  public void takeTurn() {
    // just keeps looping through tryTurn until we get a valid answer
    while(!tryTurn()){
      tryTurn();
    }
  }

  public boolean tryTurn() {
    int numCards = hand.numCardsRemaining();
    int invalidTries = 0;

    while (hand.numCardsRemaining() == numCards) {
      // Reprint the info if user put invalid input too many times
      if (invalidTries == 5) {
        giveSpace();
        printGameInfo(game, hand);
        invalidTries = 0;
      }

      if (emptyHand() || hand.noMatches(game.getTopCard())) { // User doesn't have valid cards to play to begin turn
        game.interact("Your hand still has no matches your turn is being passed");
        if (hand.noMatches(game.getTopCard())) { // After drawing one, there is still no valid card. Player is skipped
          game.interact("Your hand still has no matches your turn is being passed");
        } else { // no valid card to start, but drawn card was playable
          hand.toString(); // print out your hand
          game.interact("Which card would you like to play?");
          numCards++;
          // begin the loop for valid user input
          Scanner userInput = new Scanner(System.in);
          while (numCards == hand.numCardsRemaining()) {
            if (!userInput.hasNextInt()) {
              String garbage = userInput.nextLine(); // ignore whatever they put so we can collect new stuff
              game.interact(garbage + " is not a valid integer, please try again.");
            } else {
              int input = userInput.nextInt();
              // invalid index
              if (input < 0 || input > hand.numCardsRemaining()) {
                game.interact(input + " is not a valid index, please try again.");
              } else {
                // invalid match
                if (playCard(game, input)) {
                  userInput.close(); // close scanner
                  return true; // turn was valid
                }
              }
            }
          }
          userInput.close(); // close scanner
        }
      } else { // If hand starts off with a match
        // handle match scenario here
      }
    }
    return false; // turn was not valid
  }

  /*
   * Prints out all the game info at once, used to call after too many bad tries,
   * or just idk a lot of times so it should be its own function
   */
  public static void printGameInfo(Game game, Hand hand) {
    System.out.println("Play area:\n");
    game.getTopCard().prettyPrint(); // print deck card pretty
    System.out.println("Hand:\n");
    System.out.println(hand.toString()); // Prints all cards in hand pretty
  }

  public boolean tryTurn(Game game, int numCards) {
    return false;
  }

  /*
   * method to be used to play the card and catch the error if invalid
   * assums we have a valid game and int to pass through, so you
   * need to check that the userInput scanner object is given an int
   * before passing to playCard.
   */
  public boolean playCard(Game game, int input) {
    try {
      hand.playCard(game, input);
    } catch (Card.CannotPlayCardException e) { // catch error yell at user
      StringBuilder str = new StringBuilder();
      str.append("Card ");
      str.append(input);
      str.append(" cannot currently be played, please try again.");
      game.interact(str.toString());
      return false;
    }
    return true;
  }

  /*
   * another helper method, prints some space inbewteen each
   * play so its more clear and easier to tell when its a new
   * turn.
   */
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

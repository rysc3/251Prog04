/*
 * @author Ryan Scherbarth
 * cs251L
 * 4/8/23
 */
import java.util.Arrays;
import java.util.List;

public final class Hand {
  private final List<Card> cards;

  public Hand(List<Card> cards) {
    this.cards = cards;
  }

  public void addCard(Card card) {
    cards.add(card);
  }

  public int numCardsRemaining() {
    return cards.size();
  }

  /**
   * This function does the following:
   * - Gets the current card using index
   * - Plays the current card
   * - Removes the current card from cards
   *
   * @param game  State of the game
   * @param index Index of desired card to play in cards
   * @throws Card.CannotPlayCardException
   *                                      D_TODO: Implement this
   */
  public void playCard(Game game, int index) throws Card.CannotPlayCardException {
    Card playedCard = cards.get(index);   // get current card
    if(playedCard.match(game.getTopCard())){  // if valid card is given
      game.playCard(playedCard);  // play card
      cards.remove(index);  // remove from hand
    }else{
      throw new Card.CannotPlayCardException();
    }
  }

  /**
   * This function checks to see if your hand has any
   * matches to the given card
   *
   * @param topCard Card currently in play
   * @return true if match is found and false otherwise
   *         D_TODO: Implement this
   */
  public boolean noMatches(Card topCard) {
    for(Card c : cards){
      /*
       * Actual card matching takes place in Card,
       * this just calls card.
       * 
       * We loop through each card in our hand to check if one is 
       * playable, if so return true
       */
      if(c.match(topCard)){ return true; }
    }
    return false;
  }

  /**
   * Prints out your current hand's cards horizontally.
   * This is accomplished by calling the Card::prettyPrint
   * function on all cards in your hands. The prettyPrint function
   * transforms each card into a List<String> where each element of
   * the list represents a line of output for that card. You must then
   * Append all the first lines together seperated by a space
   * then all the second lines seperated by spaces
   * etc.
   * You must then put an index label under each card that
   * is centered between each card.
   * Then put a new line below
   * Then return the String you constructed
   * For example if your hand consisted of a red reverse and a blue skip
   * then your output would look like:
   * /-------\ /-------\
   * | R |Rev| | B | S |
   * \-------/ \-------/
   * 0 1
   *
   * @return
   *         D_TODO: Implement this
   */
  @Override
  public String toString() {
    StringBuilder str = new StringBuilder();
    for(Card c : this.cards){   // For each card in the list of cards (the hand)
      str.append(c.prettyPrint());
    }
    System.out.println("\n");
    // we are now on the next line after the cards have all been printed:
    for(int i=0; i<cards.size(); i++){
      int remaining = cards.size();
      str.append("\n");   // new line
      str.append("    " + i);
      if(remaining > 0){
        str.append("      ");
      }
      remaining --;
    }
    return str.toString();
  }

  // Code you can use to test your implementation
  public static void main(String[] args) {
    Hand hand = new Hand(Arrays.asList(new Reverse(Card.Color.RED),
        new Skip(Card.Color.BLUE)));
    String handStr = hand.toString();
    System.out.println(handStr);
  }
}

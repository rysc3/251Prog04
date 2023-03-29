/*
 * @author Ryan Scherbarth
 * cs251
 * 3/29/23
 */
public class DrawN extends Card {
  private final int n;

  public DrawN(Color cardColor, int n) {
    super(cardColor);
    this.n = n;
  }

  public int getN() {
    return n;
  }

  /**
   * Makes the next player draw n cards
   *
   * @param game Current game state
   *             TODO: Implement this
   */
  @Override
  public void doAction(Game game) {
    /*
     * if N == 1, we can check if it matches, and play the card if true
     * if N == 4; their turn is just skipped after drawing cards
     */
    // Need to finish player & hand so we can take the cards and add to current
    // Player's hand
    if(this.getN() == 4){
      game.getTopCard();
    }else{

    }
  }

  /**
   * Checks if other has the same value as this
   *
   * @param other Other card to match against
   * @return true if other is an instanceof DrawN and our n equals their n, false
   *         otherwise
   *         D_TODO: Implement this
   */
  @Override
  public boolean matchValue(Card other) {
    // Check card color
    // Check card value
    if(this.getCardColor() == other.getCardColor() || this.matchValue(other)){
      return true;
    }else{
      return false;   // false if neither are matches
    }
  }


  @Override
  public String strRep() {
    return "D+" + n;
  }
}

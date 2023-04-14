public class NumberCard extends Card {
  private int number;

  public NumberCard(Color cardColor, int number) {
    super(cardColor);
    this.number = number;
  }

  @Override
  public void doAction(Game game) {
    // Implement the action for number cards
  }

  @Override
  public boolean matchValue(Card other) {
    // Implement the value matching logic for number cards
		return false;
  }

  @Override
  public String strRep() {
    return Integer.toString(number);
  }
}

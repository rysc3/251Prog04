
/*
 * @author Ryan Scherbarth
 * cs251
 * 3/29/23
 */
import java.util.*;

public final class Game {
  private final Scanner io;
  private final UnusIterator<Player> players;
  private final int numPlayers;
  private final Deck deck;
  private final Deque<Card> playArea;

  /**
   * Constructs all the data necessary to run a game.
   * This includes the following:
   * - Create a scanner of System.in and saves it into io
   * - Creates a deck using the createDeck function and saves it into deck.
   * - Creates a list of players with length given by numPlayers
   * - Has each player draw 5 cards
   * - Creates a UnusIterator with the aforementioned player list
   * - Assigns the parameter numPlayers to the instance variable numPlayers
   * - Initializes playArea with a new ArrayDeque
   *
   * @param numPlayers
   *                   TODO: Implement this
   */
  public Game(int numPlayers) {
    this.io = new Scanner(System.in);
    this.deck = createDeck();
    List<Player> playerList = new ArrayList<>();
    /*
     * Creates a player, named the index in which they're put in.
     * Creates the player list, one by one creates the player, draw
     * 5 cards for the player, and add them to the playerlist.
     */
    for (int i = 0; i < numPlayers; i++) {
      Player player = new Player(Integer.toString(i), this);
      player.drawCards(5);
      playerList.add(player);
    }
    this.players = new UnusIterator<>(playerList); // Create the unusIterator
    this.numPlayers = numPlayers;
    this.playArea = new ArrayDeque<>();
  }

  /**
   * The main game loop function.
   * Does the following:
   * - Loops until the curPlayer's hand is empty
   * - When this is the case the curPlayer has won the game.
   * - It then prints: "$curPlayer won!"
   * - The current player is received from the UnusIterator
   * - The player then takes their turn
   * - The UnusIterator is then moved to the next player
   * TODO: Implement this
   */
  public void start() {
    // // How to find the current player?
    // while($curPlayer.numCardsRemaining() > 0){

    // }
  }

  public String interact(String toUser) {
    System.out.println(toUser);
    return io.nextLine();
  }

  public UnusIterator<Player> getPlayers() {
    return players;
  }

  public int getNumPlayers() {
    return numPlayers;
  }

  public int getNumberOfCardsInPlay() {
    return playArea.size();
  }

  public Deck getDeck() {
    return deck;
  }

  public Card getTopCard() {
    if (playArea.isEmpty()) {
      return new None();
    }

    return playArea.getFirst();
  }

  public void playCard(Card card) {
    playArea.addFirst(card);
  }

  public void shufflePlayAreaIntoDeck() {
    deck.addCards(playArea);
    deck.shuffleDeck();
  }

  /**
   * Creates the standard 108 card Unus deck.
   * The deck contains the following cards:
   * - 19 red cards
   * - 1 zero
   * - 2 of every number
   * - 19 blue cards
   * - 1 zero
   * - 2 of every number
   * - 19 green cards
   * - 1 zeroef
   * - 2 of every number
   * - 19 yellow cards
   * - 1 zero
   * - 2 of every number
   * - 8 skip cards - two of each color
   * - 8 reverse cards - two of each color
   * - 8 draw 2 cards - two of each color
   * - 4 wild cards
   * - 4 wild draw 4 cards
   *
   * @return A standard Unus deck of 108 cards
   *         TODO: Implement this
   */
  private Deck createDeck() {
    List<Card> deck = new ArrayList<Card>();

    for(int i=1; i<20; i++){
      // 2 draw 2 of each color
      if(i < 3){
        deck.add(new Draw2Card(Card.Color.RED));
        deck.add(new Draw2Card(Card.Color.BLUE));
        deck.add(new Draw2Card(Card.Color.GREEN));
        deck.add(new Draw2Card(Card.Color.YELLOW));
      }

      // 4 of each wild card
      if(i < 5){
        deck.add(new WildCard());
        deck.add(new WildDraw4Card());
      }

      // only for 8 copies
      if(i < 9){
        // skip
        deck.add(new SkipCard(Card.Color.RED));
        deck.add(new SkipCard(Card.Color.BLUE));
        deck.add(new SkipCard(Card.Color.GREEN));
        deck.add(new SkipCard(Card.Color.YELLOW));

        // reverse
        deck.add(new ReverseCard(Card.Color.RED));
        deck.add(new ReverseCard(Card.Color.BLUE));
        deck.add(new ReverseCard(Card.Color.GREEN));
        deck.add(new ReverseCard(Card.Color.YELLOW));
      }

      // Number cards
      deck.add(new NumberCard(Card.Color.RED, i));
      deck.add(new NumberCard(Card.Color.BLUE, i));
      deck.add(new NumberCard(Card.Color.GREEN, i));
      deck.add(new NumberCard(Card.Color.YELLOW, i));
    }
    System.out.println("DEBUG: after");
    for(int i=0; i<108; i++){
      System.out.println(i);
      System.out.println(deck.get(i));
    }

    Deck ret = new Deck(deck);  // conv. from card list to deck before returning
    return ret;
  }

}

import java.util.ArrayList;
import java.util.List;

public final class Skip extends Card {

    public Skip(Color cardColor) {
        super(cardColor);
    }

    /**
     * Skip can skip any player except for the player who played it.
     * This function accomplishes the following:
     * - Prompts the user who they would like to skip with the following message:
     *   "Who would you like to skip? (n)ext or (s)pecific user?"
     * - If the answer is "n" then the next player is skipped
     * - If the answer is "s" then a specific player is skipped
     *   - The user must then be prompted with the following prompt:
     *     "Please choose from the following numbers: $playerNumbers"
     *     where playerNumbers are all the indices of players other than the current player seperated by spaces
     *   - You must loop until they give a valid index, if they fail output the following message:
     *     "$playerNumber is not valid. $playersToChoose"
     *     where playerNumber is the number they input
     *   - If they give an index that is not a number then output the following message and loop again:
     *     "$n not an int, please try again."
     *     where n is the index they input
     * - You must loop until they give you a valid command, if they fail output the following message:
     *   "$answer is not a recognized command, please try again."
     * @param game
     * TODO: Implement this
     */
    @Override
    public void doAction(Game game) {

    }

    @Override
    public boolean matchValue(Card other) {
        return other instanceof Skip;
    }

    @Override
    public String strRep() {
        return "S";
    }
}

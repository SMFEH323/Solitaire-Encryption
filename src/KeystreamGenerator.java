/**
 * Generates keystream values.
 * 
 * @author Sayf Elhawary
 */
public class KeystreamGenerator {

	private SolitaireDeck deck_;

	/**
	 * Creates a new deck of the desired size and keys the deck using the desired
	 * passphrase.
	 * 
	 * @param deckSize
	 *          The desired size for the deck.
	 * @param passPhrase
	 *          passphrase.
	 */
	public KeystreamGenerator ( int deckSize, String passPhrase ) {
		if ( deckSize < 1 ) {
			throw new IllegalArgumentException("Deck size should be at least zero ant maximum 26. Please try again.");
		}
		deck_ = new SolitaireDeck(deckSize);
		char[] chars = passPhrase.toCharArray();
		for ( char c : chars ) {
			if ( Character.isLetter(c) ) {
				deck_.swapJokerA();
				deck_.swapJokerB();
				deck_.tripleCut();
				deck_.countCut(deck_.getBottomCard().getValue());
				int letterNum = getCharNum(c);
				deck_.countCut(letterNum);
			}
		}
	}

	/**
	 * Converts the desired letter to a number.
	 * 
	 * @param character
	 *          The desired character.
	 * @return the desired letter's number.
	 */
	private int getCharNum ( char character ) {
		char[] letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
		for ( int i = 0 ; i < letters.length ; i++ ) {
			if ( letters[i] == Character.toUpperCase(character) ) {
				return i + 1;
			}
		}
		return 0;
	}

	/**
	 * Generates and gets the next keystream value.
	 * 
	 * @return the next keystream value.
	 */
	public int nextKeystreamValue () {
		for ( ; true ; ) {
			deck_.swapJokerA();
			deck_.swapJokerB();
			deck_.tripleCut();
			deck_.countCut(deck_.getBottomCard().getValue());
			SolitaireCard keystreamCard =
			    deck_.getNthCard(deck_.getTopCard().getValue());
			if ( !keystreamCard.isJoker() ) {
				return keystreamCard.getValue();
			}
		}
	}

	/**
	 * Gets a string representation of the deck.
	 * 
	 * @return a string representation of the deck.
	 */
	protected String getDeckString () {
		return deck_.toString();
	}

}

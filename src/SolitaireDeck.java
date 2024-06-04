/**
 * The deck of cards used to generate keystream values.
 * 
 * @author Sayf Elhawary
 */
public class SolitaireDeck {

	private DoubleListNode head_; // The head of the circular doubly-linked list.

	private int deckSize_; // The deck size.

	/**
	 * Creates a Solitaire Deck according to the desired deck size.
	 * 
	 * @param deckSize
	 *          The desired size for the deck.
	 */
	public SolitaireDeck ( int deckSize ) {
		if ( deckSize < 1 ) {
			throw new IllegalArgumentException("Deck size should be at least one. Please try again.");
		}
		deckSize_ = deckSize;
		head_ = new DoubleListNode(new SolitaireCard(1));
		DoubleListNode current = head_;
		DoubleListNode jokerA =
		    new DoubleListNode(new SolitaireCard(deckSize + 1,'A'));
		DoubleListNode jokerB =
		    new DoubleListNode(new SolitaireCard(deckSize + 1,'B'),jokerA,head_);
		DoubleListNode next = current.getNext();
		for ( int nextPos = 2 ; nextPos <= deckSize ; nextPos++ ) {
			next = new DoubleListNode(new SolitaireCard(nextPos));
			current.setNext(next);
			next.setPrev(current);
			current = next;
		}
		current.setNext(jokerA);
		jokerA.setPrev(current);
		jokerA.setNext(jokerB);
		head_.setPrev(jokerB);
		assert checkStructure() : "The deck is not consistent.";
		assert checkContents() : "The deck contains duplicates or has missing cards";
	}

	/**
	 * Creates a Solitaire Deck according to the desired deck size and string.
	 * 
	 * @param deckSize
	 *          The desired size for the deck.
	 * @param deckString
	 *          The desired string for the deck.
	 */
	protected SolitaireDeck ( int deckSize, String deckString ) {
		if ( deckSize < 1 || !deckString.contains("A")
		    || !deckString.contains("B") ) {
			throw new IllegalArgumentException("Deck size should be at least one and the desired string is not applicable for creating a deck. Please try again.");
		}
		deckSize_ = deckSize;
		String[] cards = deckString.split(" +");
		head_ = new DoubleListNode(new SolitaireCard(cards[0]));
		DoubleListNode current = head_;
		DoubleListNode next = current.getNext();
		for ( int i = 1 ; i < cards.length ; i++ ) {
			next = new DoubleListNode(new SolitaireCard(cards[i]));
			current.setNext(next);
			next.setPrev(current);
			current = next;
		}
		current.setNext(head_);
		head_.setPrev(current);
		assert checkStructure() : "The deck is not consistent.";
		assert checkContents() : "The deck contains duplicates or has missing cards";
	}

	/**
	 * Gets the deck size.
	 * 
	 * @return the deck size.
	 */
	public int getDeckSize () {
		return deckSize_;
	}

	/**
	 * Gets the top card in the deck.
	 * 
	 * @return the top card in the deck.
	 */
	public SolitaireCard getTopCard () {
		return head_.getCard();
	}

	/**
	 * Gets the bottom card in the deck.
	 * 
	 * @return the bottom card in the deck.
	 */
	public SolitaireCard getBottomCard () {
		return head_.getPrev().getCard();
	}

	/**
	 * Gets the nth solitaire card from the top of the deck.
	 * 
	 * @param n
	 *          The desired position for the SolitaireCard. n >= 0 and n <
	 *          deckSize.
	 * @return the nth SolitaireCard.
	 */
	public SolitaireCard getNthCard ( int n ) {
		if ( n < 0 || n >= deckSize_ + 2 ) {
			throw new IllegalArgumentException("The desired nth Card is out of bounds. Please try again.");
		}
		DoubleListNode current = head_;
		for ( int curPos = 0 ; curPos < n ; curPos++ ) {
			current = current.getNext();
		}
		return current.getCard();
	}

	/**
	 * Swaps Joker A with the card that comes after it in the deck. Joker A cannot
	 * be the top card.
	 */
	public void swapJokerA () {
		DoubleListNode jokerA = head_;
		for ( ; !jokerA.getCard().isJokerA() ; ) {
			jokerA = jokerA.getNext();
		}
		DoubleListNode beforeJA = jokerA.getPrev();
		DoubleListNode afterJA = jokerA.getNext();
		DoubleListNode after2JA = afterJA.getNext();
		if ( head_.getCard().isJokerA() ) {
			head_ = afterJA;
		}
		beforeJA.setNext(afterJA);
		afterJA.setPrev(beforeJA);
		afterJA.setNext(jokerA);
		jokerA.setPrev(afterJA);
		jokerA.setNext(after2JA);
		after2JA.setPrev(jokerA);
		assert checkStructure() : "The deck is not consistent.";
		assert checkContents() : "The deck contains duplicates or has missing cards";
	}

	/**
	 * Swaps Joker B with the 2 cards that come after it in the deck. Joker B
	 * cannot be the top card.
	 */
	public void swapJokerB () {
		DoubleListNode jokerB = head_;
		for ( ; !jokerB.getCard().isJokerB() ; ) {
			jokerB = jokerB.getNext();
		}
		DoubleListNode beforeJB = jokerB.getPrev();
		DoubleListNode afterJB = jokerB.getNext();
		DoubleListNode after2JB = afterJB.getNext();
		DoubleListNode after3JB = after2JB.getNext();
		if ( head_.getCard().isJokerB() ) {
			head_ = afterJB;
		}
		beforeJB.setNext(afterJB);
		afterJB.setPrev(beforeJB);
		after2JB.setNext(jokerB);
		jokerB.setPrev(after2JB);
		jokerB.setNext(after3JB);
		after3JB.setPrev(jokerB);
		assert checkStructure() : "The deck is not consistent.";
		assert checkContents() : "The deck contains duplicates or has missing cards";
	}

	/**
	 * Exchanges the cards above the first joker with the cards below the second
	 * joker.
	 */
	public void tripleCut () {
		DoubleListNode topDeck = head_;
		DoubleListNode bottomDeck = head_.getPrev();
		DoubleListNode joker1 = topDeck;
		DoubleListNode joker2 = bottomDeck;
		for ( ; !joker1.getCard().isJoker() ; ) {
			joker1 = joker1.getNext();
		}
		for ( ; !joker2.getCard().isJoker() ; ) {
			joker2 = joker2.getPrev();
		}
		DoubleListNode beforeJ1 = joker1.getPrev();
		DoubleListNode afterJ2 = joker2.getNext();
		if ( bottomDeck == joker2 && topDeck != joker1 ) {
			head_ = joker1;
		} else if ( bottomDeck != joker2 && topDeck != joker1 ) {
			joker2.setNext(topDeck);
			topDeck.setPrev(joker2);
			beforeJ1.setNext(afterJ2);
			afterJ2.setPrev(beforeJ1);
			bottomDeck.setNext(joker1);
			joker1.setPrev(bottomDeck);
			head_ = afterJ2;
		} else if ( bottomDeck != joker2 && topDeck == joker1 ) {
			head_ = afterJ2;
		} else {

		}
		assert checkStructure() : "The deck is not consistent.";
		assert checkContents() : "The deck contains duplicates or has missing cards";
	}

	/**
	 * Removes the bottom card from the deck, counts down a specified number of
	 * cards from the top of the deck, puts the cards that were counted at the
	 * bottom of the deck, and adds the original bottom card back to the bottom of
	 * the deck.
	 * 
	 * @param n
	 *          The number of counts. n >= 1 and n <= deckSize_ + 2.
	 */
	public void countCut ( int n ) {
		if ( n < 1 || n > deckSize_ + 2 ) {
			throw new IllegalArgumentException("n is out of bounds. Please try again.");
		}
		DoubleListNode bottomDeck = head_.getPrev();
		DoubleListNode beforeBottomDeck = bottomDeck.getPrev();
		DoubleListNode current = head_;
		beforeBottomDeck.setNext(head_);
		head_.setPrev(beforeBottomDeck);
		for ( int curPos = 1 ; curPos < n ; curPos++ ) {
			current = current.getNext();
		}
		head_ = current.getNext();
		beforeBottomDeck = head_.getPrev();
		beforeBottomDeck.setNext(bottomDeck);
		bottomDeck.setPrev(beforeBottomDeck);
		bottomDeck.setNext(head_);
		head_.setPrev(bottomDeck);
		assert checkStructure() : "The deck is not consistent.";
		assert checkContents() : "The deck contains duplicates or has missing cards";
	}

	/**
	 * Gets a string representation of the deck.
	 * 
	 * @return a string representation of the deck.
	 */
	public String toString () {
		String str = "";
		DoubleListNode current = head_;
		for ( int curPos = 0 ; curPos < deckSize_ + 2 ; curPos++ ) {
			if ( curPos == deckSize_ + 1 ) {
				str += current.getCard().toString();
			} else {
				str += current.getCard().toString() + " ";
			}
			current = current.getNext();
		}
		return str;
	}

	/**
	 * Checks the structure of the deck.
	 * 
	 * @return true if the deck is consistent, false otherwise.
	 */
	private boolean checkStructure () {
		DoubleListNode current = head_;
		DoubleListNode prev = current.getPrev();
		DoubleListNode next = current.getNext();
		for ( int i = 0 ; i < deckSize_ + 2 ; i++ ) {
			if ( prev.getNext() != current || next.getPrev() != current ) {
				return false;
			} else {
				prev = current;
				current = next;
				next = next.getNext();
			}

		}
		return true;
	}

	/**
	 * Checks the contents of the deck.
	 * 
	 * @returns true if the deck contains cards 1..deckSize plus joker A and joker
	 *          B, false otherwise.
	 */
	private boolean checkContents () {
		boolean[] contents = new boolean[deckSize_ + 2];
		DoubleListNode current = head_;
		for ( int curPos = 0 ; curPos < deckSize_ + 2 ; curPos++ ) {
			if ( current.getCard().isJokerA() ) {
				if ( contents[0] == true ) {
					return false;
				} else {
					contents[0] = true;
				}
			} else {
				if ( contents[current.getCard().getValue()] == true ) {
					return false;
				} else {
					contents[current.getCard().getValue()] = true;
				}
			}
			current = current.getNext();
		}

		for ( int i = 0 ; i < contents.length ; i++ ) {
			if ( contents[i] == false ) {
				return false;
			}
		}
		return true;
	}

}
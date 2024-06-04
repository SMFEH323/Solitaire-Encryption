/**
 * Tester for SolitaireDeck
 * 
 * @author Sayf Elhawary
 */
public class SolitaireDeckTester {
	public static void main ( String[] args ) {
		boolean assertsEnabled = false;
		assert assertsEnabled = true;
		System.out.println("assertions are on: " + assertsEnabled);
		System.out.println();

		{
			// name: SolitaireDeck
			// starting state: n/a
			// input: n/a
			// expected result: 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21A
			// 21B
			System.out.println("SolitaireDeck");
			System.out.println();
			String str = "1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21A 21B";
			try {
				SolitaireDeck deck = new SolitaireDeck(20);
				if ( deck.toString().equals(str) ) {
					System.out.println("PASSED");
				} else {
					System.out.println("FAILED");
				}
			} catch ( Exception e ) {
				System.out.println("FAILED");
			}
			System.out.println();
		}

		{
			// name: SolitaireDeck
			// starting state: n/a
			// input: str - 1 21B 2 10 11 12 13 14 15 16 17 18 19 20 7 5 9 21A 8 3 6 4
			// expected result: true
			System.out.println("SolitaireDeck");
			System.out.println();
			String str = "1 21B 2 10 11 12 13 14 15 16 17 18 19 20 7 5 9 21A 8 3 6 4";
			try {
				SolitaireDeck deck = new SolitaireDeck(20,str);
				if ( deck.toString().equals(str) ) {
					System.out.println("PASSED");
				} else {
					System.out.println("FAILED");
				}
			} catch ( Exception e ) {
				System.out.println("FAILED");
			}
			System.out.println();
		}

		{
			// name: swapJokerA - Joker A is last
			// starting state: deck - 1 23 25 27B 2 22 10 11 12 13 14 15 16 17 18 19
			// 20 21 7 5 9 4 26 8 3 24 6 27A
			// input: n/a
			// expected result: 1 27A 23 25 27B 2 22 10 11 12 13 14 15 16 17 18 19 20
			// 21 7 5 9 4 26 8 3 24 6
			System.out.println("swapJokerA - Joker A is not first");
			System.out.println();
			String strBefore =
			    "1 23 25 27B 2 22 10 11 12 13 14 15 16 17 18 19 20 21 7 5 9 4 26 8 3 24 6 27A";
			String strAfter =
			    "1 27A 23 25 27B 2 22 10 11 12 13 14 15 16 17 18 19 20 21 7 5 9 4 26 8 3 24 6";
			SolitaireDeck deck = new SolitaireDeck(26,strBefore);
			try {
				deck.swapJokerA();
				if ( deck.toString().equals(strAfter) ) {
					System.out.println("PASSED");
				} else {
					System.out.println("FAILED");
				}
			} catch ( Exception e ) {
				System.out.println("FAILED");
			}
			System.out.println();
		}

		{
			// name: swapJokerA - Joker A is first
			// starting state: deck - 27A 1 23 25 27B 2 22 10 11 12 13 14 15 16 17 18
			// 19 20
			// 21 7 5 9 26 8 3 24 6 4
			// input: n/a
			// expected result: 1 27A 23 25 27B 2 22 10 11 12 13 14 15 16 17 18 19 20
			// 21 7 5
			// 9 26 8 3 24 6 4
			System.out.println("swapJokerA - Joker A is first");
			System.out.println();
			String strBefore =
			    "27A 1 23 25 27B 2 22 10 11 12 13 14 15 16 17 18 19 20 21 7 5 9 26 8 3 24 6 4";
			String strAfter =
			    "1 27A 23 25 27B 2 22 10 11 12 13 14 15 16 17 18 19 20 21 7 5 9 26 8 3 24 6 4";
			SolitaireDeck deck = new SolitaireDeck(26,strBefore);
			try {
				deck.swapJokerA();
				if ( deck.toString().equals(strAfter) ) {
					System.out.println("PASSED");
				} else {
					System.out.println("FAILED");
				}
			} catch ( Exception e ) {
				System.out.println("FAILED");
			}
			System.out.println();
		}

		{
			// name: swapJokerB - Joker B is not first
			// starting state: deck - 1 23 25 27B 2 22 10 11 12 13 14 15 16 17 18 19
			// 20 21 7 5 9 26 27A 8 3 24 6 4
			// input: n/a
			// expected result: 1 23 25 2 22 27B 10 11 12 13 14 15 16 17 18 19 20 21 7
			// 5 9 26 27A 8 3 24 6 4
			System.out.println("swapJokerB - Joker B is not first");
			System.out.println();
			String strBefore =
			    "1 23 25 27B 2 22 10 11 12 13 14 15 16 17 18 19 20 21 7 5 9 26 27A 8 3 24 6 4";
			String strAfter =
			    "1 23 25 2 22 27B 10 11 12 13 14 15 16 17 18 19 20 21 7 5 9 26 27A 8 3 24 6 4";
			SolitaireDeck deck = new SolitaireDeck(26,strBefore);
			try {
				deck.swapJokerB();
				if ( deck.toString().equals(strAfter) ) {
					System.out.println("PASSED");
				} else {
					System.out.println("FAILED");
				}
			} catch ( Exception e ) {
				System.out.println("FAILED");
			}
			System.out.println();
		}

		{
			// name: swapJokerB - Joker B is first
			// starting state: deck - 27B 1 23 25 27A 2 22 10 11 12 13 14 15 16 17 18
			// 19 20
			// 21 7 5 9 26 8 3 24 6 4
			// input: n/a
			// expected result: 1 23 27B 25 27A 2 22 10 11 12 13 14 15 16 17 18 19 20
			// 21 7 5
			// 9 26 8 3 24 6 4
			System.out.println("swapJokerB - Joker B is first");
			System.out.println();
			String strBefore =
			    "27B 1 23 25 27A 2 22 10 11 12 13 14 15 16 17 18 19 20 21 7 5 9 26 8 3 24 6 4";
			String strAfter =
			    "1 23 27B 25 27A 2 22 10 11 12 13 14 15 16 17 18 19 20 21 7 5 9 26 8 3 24 6 4";
			SolitaireDeck deck = new SolitaireDeck(26,strBefore);
			try {
				deck.swapJokerB();
				if ( deck.toString().equals(strAfter) ) {
					System.out.println("PASSED");
				} else {
					System.out.println("FAILED");
				}
			} catch ( Exception e ) {
				System.out.println("FAILED");
			}
			System.out.println();
		}

		{
			// name: tripleCut - Bottom and Top Cards are not joker
			// starting state: deck - 1 23 25 2 22 27B 10 11 12 13 14 15 16 17 18 19
			// 20 21 7 5 9 26 27A 8 3 24 6 4
			// input: n/a
			// expected result: 8 3 24 6 4 27B 10 11 12 13 14 15 16 17 18 19 20 21 7 5
			// 9 26 27A 1 23 25 2 22
			System.out.println("tripleCut - Bottom and Top Cards are not joker");
			System.out.println();
			String strBefore =
			    "1 23 25 2 22 27B 10 11 12 13 14 15 16 17 18 19 20 21 7 5 9 26 27A 8 3 24 6 4";
			String strAfter =
			    "8 3 24 6 4 27B 10 11 12 13 14 15 16 17 18 19 20 21 7 5 9 26 27A 1 23 25 2 22";
			SolitaireDeck deck = new SolitaireDeck(26,strBefore);
			try {
				deck.tripleCut();
				if ( deck.toString().equals(strAfter) ) {
					System.out.println("PASSED");
				} else {
					System.out.println("FAILED");
				}
			} catch ( Exception e ) {
				System.out.println("FAILED");
			}
			System.out.println();
		}

		{
			// name: tripleCut - Bottom and Top Cards are joker
			// starting state: deck - 27B 23 25 2 22 1 10 11 12 13 14 15 16 17 18 19
			// 20 21 7 5 9 26 4 8 3 24 6 27A
			// input: n/a
			// expected result: 27B 23 25 2 22 1 10 11 12 13 14 15 16 17 18 19
			// 20 21 7 5 9 26 4 8 3 24 6 27A
			System.out.println("tripleCut - Bottom and Top Cards are joker");
			System.out.println();
			String strBefore =
			    "27B 23 25 2 22 1 10 11 12 13 14 15 16 17 18 19 20 21 7 5 9 26 4 8 3 24 6 27A";
			String strAfter = strBefore;
			SolitaireDeck deck = new SolitaireDeck(26,strBefore);
			try {
				deck.tripleCut();
				if ( deck.toString().equals(strAfter) ) {
					System.out.println("PASSED");
				} else {
					System.out.println("FAILED");
				}
			} catch ( Exception e ) {
				System.out.println("FAILED");
			}
			System.out.println();
		}

		{
			// name: tripleCut - Bottom Card is joker
			// starting state: deck - 1 27B 25 2 22 23 10 11 12 13 14 15 16 17 18 19
			// 20 21 7 5 9 26 4 8 3 24 6 27A
			// input: n/a
			// expected result: 27B 25 2 22 23 10 11 12 13 14 15 16 17 18 19 20 21 7 5
			// 9 26 4 8 3 24 6 27A 1
			System.out.println("tripleCut - Bottom Card is joker");
			System.out.println();
			String strBefore =
			    "1 27B 25 2 22 23 10 11 12 13 14 15 16 17 18 19 20 21 7 5 9 26 4 8 3 24 6 27A";
			String strAfter =
			    "27B 25 2 22 23 10 11 12 13 14 15 16 17 18 19 20 21 7 5 9 26 4 8 3 24 6 27A 1";
			SolitaireDeck deck = new SolitaireDeck(26,strBefore);
			try {
				deck.tripleCut();
				if ( deck.toString().equals(strAfter) ) {
					System.out.println("PASSED");
				} else {
					System.out.println("FAILED");
				}
			} catch ( Exception e ) {
				System.out.println("FAILED");
			}
			System.out.println();
		}

		{
			// name: tripleCut - Top Card is joker
			// starting state: deck - 27B 1 25 2 22 23 10 11 12 13 14 15 16 17 18 19
			// 20 21 7 5 9 26 4 8 3 24 27A 6
			// input: n/a
			// expected result: 6 27B 1 25 2 22 23 10 11 12 13 14 15 16 17 18 19 20 21
			// 7 5 9 26 4 8 3 24 27A
			System.out.println("tripleCut - Top Card is joker");
			System.out.println();
			String strBefore =
			    "27B 1 25 2 22 23 10 11 12 13 14 15 16 17 18 19 20 21 7 5 9 26 4 8 3 24 27A 6";
			String strAfter =
			    "6 27B 1 25 2 22 23 10 11 12 13 14 15 16 17 18 19 20 21 7 5 9 26 4 8 3 24 27A";
			SolitaireDeck deck = new SolitaireDeck(26,strBefore);
			try {
				deck.tripleCut();
				if ( deck.toString().equals(strAfter) ) {
					System.out.println("PASSED");
				} else {
					System.out.println("FAILED");
				}
			} catch ( Exception e ) {
				System.out.println("FAILED");
			}
			System.out.println();
		}

		{
			// name: countCut
			// starting state: deck - 8 3 24 6 4 27B 10 11 12 13 14 15 16 17 18 19 20
			// 21 7 5
			// 9 26 27A 1 23 25 2 22
			// input: n - 22
			// expected result: 27A 1 23 25 2 8 3 24 6 4 27B 10 11 12 13 14 15 16 17
			// 18 19
			// 20 21 7 5 9 26 22
			System.out.println("countCut");
			System.out.println();
			String strBefore =
			    "8 3 24 6 4 27B 10 11 12 13 14 15 16 17 18 19 20 21 7 5 9 26 27A 1 23 25 2 22";
			String strAfter =
			    "27A 1 23 25 2 8 3 24 6 4 27B 10 11 12 13 14 15 16 17 18 19 20 21 7 5 9 26 22";
			SolitaireDeck deck = new SolitaireDeck(26,strBefore);
			int n = 22;
			try {
				deck.countCut(n);
				if ( deck.toString().equals(strAfter) ) {
					System.out.println("PASSED");
				} else {
					System.out.println("FAILED");
				}
			} catch ( Exception e ) {
				System.out.println("FAILED");
			}
			System.out.println();
		}

	}

}

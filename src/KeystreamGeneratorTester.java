/**
 * Tester for KeystreamGenerator.
 * 
 * @author Sayf Elhawary
 */
public class KeystreamGeneratorTester {
	public static void main ( String[] args ) {
		boolean assertsEnabled = false;
		assert assertsEnabled = true;
		System.out.println("assertions are on: " + assertsEnabled);
		System.out.println();

		{
			// name: KeystreamGenerator
			// starting state: n/a
			// input: deckSize - 26
			// passPhrase - SECRET KEY
			// expected result: true
			System.out.println("KeystreamGenerator");
			System.out.println();
			int deckSize = 26;
			String passPhrase = "SECRET KEY";
			String result =
			    "1 23 25 27B 2 22 10 11 12 13 14 15 16 17 18 19 20 21 7 5 9 27A 26 8 3 24 6 4";
			try {
				KeystreamGenerator keySG = new KeystreamGenerator(deckSize,passPhrase);
				if ( keySG.getDeckString().equals(result) ) {
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
			// name: nextKeystreamValue
			// starting state: KeystreamGenerator
			// input: deckSize - 26
			// passPhrase - SECRET KEY
			// expected result: 22 22 25 9 1 7 19 22 8 23 12 21 2 3 4 25 19 21 9 8 3
			// 23 17 18 25 24
			System.out.println("nextKeystreamValue");
			System.out.println();
			int deckSize = 26;
			String passPhrase = "SECRET KEY";
			String values = "";
			String str =
			    "22 22 25 9 1 7 19 22 8 23 12 21 2 3 4 25 19 21 9 8 3 23 17 18 25 24";
			KeystreamGenerator keySG = new KeystreamGenerator(deckSize,passPhrase);
			try {
				for ( int i = 0 ; i < deckSize ; i++ ) {
					int keySV = keySG.nextKeystreamValue();
					if ( i == deckSize - 1 ) {
						values += keySV;
					} else {
						values += keySV + " ";
					}
				}
				if ( values.equals(str) ) {
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

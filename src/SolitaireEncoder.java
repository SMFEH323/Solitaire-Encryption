import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Handles the actual encryption and decryption processes.
 * 
 * @author Sayf Elhawary
 */
public class SolitaireEncoder {

	/**
	 * Takes a message, the passphrase, and the deck size and gets the encrypted
	 * message.
	 * 
	 * @param message
	 *          message
	 * @param deckSize
	 *          The desired size for the deck.
	 * @param passPhrase
	 *          passphrase.
	 * @return the encrypted message.
	 */
	public static String encrypt ( String message, int deckSize,
	                               String passPhrase ) {
		if ( deckSize < 1 ) {
			throw new IllegalArgumentException("Deck size should be at least one. Please try again.");
		}
		KeystreamGenerator keystream = new KeystreamGenerator(deckSize,passPhrase);
		char[] chars = message.toCharArray();
		String encryptedMessage = "";
		for ( char c : chars ) {
			if ( !Character.isSpaceChar(c) ) {
				int letterNum = getCharNum(c);
				int nextKeySV = keystream.nextKeystreamValue();
				int sum = letterNum + nextKeySV;
				if ( sum > deckSize ) {
					sum -= deckSize;
				}
				char encryptedChar = getNumChar(sum);
				encryptedMessage += encryptedChar;
			}
		}

		return encryptedMessage;
	}

	/**
	 * Takes the desired input file name, the output file name, the passphrase,
	 * the deck size and encrypts the input file into the output file.
	 * 
	 * @param infile
	 *          The input file
	 * @param outfile
	 *          The output file
	 * @param deckSize
	 *          The desired size for the deck.
	 * @param passPhrase
	 *          passphrase.
	 * @throws IOException
	 */
	public static void encryptFile ( String infile, String outfile, int deckSize,
	                                 String passPhrase )
	    throws IOException {
		if ( deckSize < 1 ) {
			throw new IllegalArgumentException("Deck size should be at least one. Please try again.");
		}
		FileReader in = new FileReader(infile);
		PrintWriter out = new PrintWriter(new FileWriter(outfile));
		String encryptedMessage = "";
		for ( int read = in.read() ; read != -1 ; read = in.read() ) {
			encryptedMessage += (char) read;
		}
		in.close();
		out.write(encrypt(encryptedMessage,68,passPhrase));
		out.flush();
		out.close();
	}

	/**
	 * Takes a message, the passphrase, and the deck size and gets the decrypted
	 * message.
	 * 
	 * @param message
	 *          message
	 * @param deckSize
	 *          The desired size for the deck.
	 * @param passPhrase
	 *          passphrase.
	 * @return the decrypted message.
	 */
	public static String decrypt ( String message, int deckSize,
	                               String passPhrase ) {
		if ( deckSize < 1 ) {
			throw new IllegalArgumentException("Deck size should be at least one. Please try again.");
		}
		KeystreamGenerator keystream = new KeystreamGenerator(deckSize,passPhrase);
		char[] chars = message.toCharArray();
		String decryptedMessage = "";
		for ( char c : chars ) {
			if ( !Character.isSpaceChar(c) ) {
				int letterNum = getCharNum(c);
				int nextKeySV = keystream.nextKeystreamValue();
				int result = letterNum - nextKeySV;
				if ( result <= 0 ) {
					result += deckSize;
				}
				char decryptedChar = getNumChar(result);
				decryptedMessage += decryptedChar;
			}
		}

		return decryptedMessage;
	}

	/**
	 * Takes the desired input file name, the output file name, the passphrase,
	 * the deck size and decrypts the input file into the output file.
	 * 
	 * @param infile
	 *          The input file
	 * @param outfile
	 *          The output file
	 * @param deckSize
	 *          The desired size for the deck.
	 * @param passPhrase
	 *          passphrase.
	 * @throws IOException
	 */
	public static void decryptFile ( String infile, String outfile, int deckSize,
	                                 String passPhrase )
	    throws IOException {
		if ( deckSize < 1 ) {
			throw new IllegalArgumentException("Deck size should be at least one. Please try again.");
		}
		FileReader in = new FileReader(infile);
		PrintWriter out = new PrintWriter(new FileWriter(outfile));
		String decryptedMessage = "";
		for ( int read = in.read() ; read != -1 ; read = in.read() ) {
			decryptedMessage += (char) read;
		}
		in.close();
		out.write(decrypt(decryptedMessage,68,passPhrase));
		out.flush();
		out.close();
	}

	/**
	 * Converts the desired letter to a number.
	 * 
	 * @param character
	 *          The desired character.
	 * @return the desired letter's number.
	 */
	private static int getCharNum ( char character ) {
		char[] chars =
		    "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!,;.?-\'\":@#$%^&*()_+=`~/\\[]{}<>|"
		        .toCharArray();
		for ( int i = 0 ; i < chars.length ; i++ ) {
			if ( chars[i] == Character.toUpperCase(character) ) {
				return i + 1;
			}
		}
		return 0;
	}

	/**
	 * Converts the desired number to a letter.
	 * 
	 * @param num
	 *          The desired number.
	 * @return the desired number's letter.
	 */
	private static char getNumChar ( int num ) {
		char[] chars =
		    "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!,;.?-\'\":@#$%^&*()_+=`~/\\[]{}<>|"
		        .toCharArray();
		return chars[num - 1];
	}
}

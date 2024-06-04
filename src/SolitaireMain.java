import java.io.IOException;
import java.util.Scanner;

public class SolitaireMain {

	public static void main ( String[] args ) {
		Scanner scan = new Scanner(System.in);
		boolean success = false;
		try {
			if ( args.length == 4 ) {
				if ( args[0].equals("e") ) {
					SolitaireEncoder.encryptFile(args[2],args[3],68,args[1]);
					success = true;
				} else if ( args[0].equals("d") ) {
					SolitaireEncoder.decryptFile(args[2],args[3],68,args[1]);
					success = true;
				}
			}
		} catch ( IOException e ) {
			System.out.println("IO Error. Please try again.");
			System.out.println();
		}
		System.out.print("enter passphrase: ");
		String passPhrase = scan.nextLine();
		for ( ; !success ; ) {
			try {
				System.out
				.print("encrypt, decrypt, encrypt file, decrypt file, or quit? [e/d/ef/df/q] ");
				String choice = scan.nextLine().toLowerCase();
				if ( choice.equals("e") ) {
					System.out.print("enter message: ");
					String message = scan.nextLine();
					String encryptedMessage =
							SolitaireEncoder.encrypt(message,68,passPhrase);
					System.out.println();
					System.out.println("encrypted: " + encryptedMessage);
					System.out.println();
				} else if ( choice.equals("d") ) {
					System.out.print("enter encrypted message: ");
					String encryptedMessage = scan.nextLine();
					String decryptedMessage =
							SolitaireEncoder.decrypt(encryptedMessage,68,passPhrase);
					System.out.println();
					System.out.println("decrypted: " + decryptedMessage);
					System.out.println();
				} else if ( choice.equals("ef") ) {
					System.out.print("enter file to encrypt: ");
					String infile = scan.nextLine();
					System.out.print("enter file to save in: ");
					String outfile = scan.nextLine();
					SolitaireEncoder.encryptFile(infile,outfile,68,passPhrase);
					System.out.println();
				} else if ( choice.equals("df") ) {
					System.out.print("enter file to decrypt: ");
					String infile = scan.nextLine();
					System.out.print("enter file to save in: ");
					String outfile = scan.nextLine();
					SolitaireEncoder.decryptFile(infile,outfile,68,passPhrase);
					System.out.println();
				} else if ( choice.equals("q") ) {
					scan.close();
					break;
				} else {
					System.out.println();
					System.out.println("Invalid choice. Please try again.");
				}
			} catch ( IOException e ) {
				System.out.println("IO Error. Please try again.");
				System.out.println();
			}
		}

	}

}

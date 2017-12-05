import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

/**
 *  Driver class
 *  <p>
 *  The invert indexing is done in the driver class
 *  <p>
 *  Author: Yi Zhang
 */

public class Q1Driver {

	public static void main(String[] args) {
		File rawFile = new File("ds17s-Asg4-data.txt");
		try {
			Q1AddressBook ab = new Q1AddressBook(rawFile);
			System.out.println("Please enter the information you want to search:");
			Scanner keyboard=new Scanner(System.in);
			String input=keyboard.nextLine();
			System.out.println("Here is the search result:");
			ab.search(input);
			}
	    catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

}

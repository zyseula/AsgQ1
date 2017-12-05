import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.text.Normalizer;
import java.util.Scanner;

/**
 *  The {@code AddressBook} class is a ADT to store contacts information.
 *  <p>
 *  This implementation uses three arrays to store all related information.
 *  <p>
 *  Author: Yi Zhang
 */

public class Q1AddressBook {
	private String [] nameArray;
	private String [] emailArray;
	private String [] orgArray;
	private String [] countryArray;
	private String[] rawArray;
	private Q1BoyerMoore compartor = new Q1BoyerMoore();
	public int number = 0;
	
	/**
	 * Default Constructor
	 */
	public Q1AddressBook(){
		
	}
	
	/**
     * Construct the obj with the target file
     * Sort the list by using the selection sort
     * @param file data file
     * @throws UnsupportedEncodingException throw exception if the encoding method is not supported
     */
	public Q1AddressBook(File file) throws UnsupportedEncodingException{
		Scanner inputStream = null;
		try{
			inputStream = new Scanner(new FileInputStream(file));//ds17s-asg2-data
		}
		catch(FileNotFoundException e){
			System.out.println("File was not found or could not be opened.");
			System.exit(0);
		}
		
		int index = 0;
		while (inputStream.hasNextLine()) {
		    String line = inputStream.nextLine(); 
		    number++;
		}
		inputStream.close();
		
		nameArray = new String[number];
		emailArray = new String[number];
		orgArray = new String[number];
		countryArray = new String[number];
		rawArray = new String[number];
		
		Scanner inputStream_2 = null;
		try{
			inputStream_2 = new Scanner(file);//ds17s-asg2-data
		}
		catch(FileNotFoundException e){
			System.out.println("File was not found or could not be opened.");
			System.exit(0);
		}
		
		while (inputStream_2.hasNextLine()) {
			String temp_String = inputStream_2.nextLine().trim();
			temp_String = Normalizer.normalize(temp_String, Normalizer.Form.NFD);
			temp_String = temp_String.replaceAll("[^\\p{ASCII}]", "");
			rawArray[index] = temp_String;
			index++;
		}
		
		inputStream_2.close();
		SelectionSort(rawArray);
		for(int i = 0 ;i<number;i++){
			String [] temp = rawArray[i].split(":");

			nameArray[i] = temp.length != 4 ? "":temp[0];
			emailArray[i] = temp.length != 4 ? "":temp[1];
			orgArray[i] = temp.length != 4 ? "":temp[2];
			countryArray[i] = temp.length != 4 ? "":temp[3];
		}
	}
	
	/**
     * Selection sort.
     * @param name rayArray
     */
	private void SelectionSort(String[] name){
		
		for(int i=0;i<name.length;i++){
			for(int j=i+1;j<name.length;j++){
				int firstQ_1 = name[i].indexOf(":");
				int firstQ_2 = name[j].indexOf(":");
				if(name[i].substring(0, firstQ_1).compareToIgnoreCase(name[j].substring(0, firstQ_2))>0){
					String temp = name[i];
					name[i] = name[j];
					name[j] = temp;
				}
			}	
		}
	}
	
	/**
     * Search the list by using the Boyer-Moore with provided query.
     * If found, print the result
     * @param entry target query
     * @see BoyerMoore
     */
	public void search(String entry){
		int count=0;
		for(int i = 0;i<number;i++){
			String temp = this.getALLAt(i);
			compartor = new Q1BoyerMoore(entry);
			if( compartor.search(temp) != -1){
				System.out.println(this.getALLAt(i));
				count++;
			}	
		}
		 if(count==0)
			 System.out.println("Not Found!");
		 else
			 System.out.println(count+ " records found!");
	}
	
	/**
     * Return whole entry at i
     * @param index index number
     * @return whole entry
     */
	public String getALLAt(int index){
		if(index > number && index <0)
			return "";
		else
			return nameArray[index]+" : "+emailArray[index]+" : "+orgArray[index]+" : "+
				countryArray[index];
	}
	
	/**
     * Return name at i
     * @param index index number
     * @return name
     */
	public String getNameAt(int index){
		if(index > number && index <0)
			return "";
		else
			return nameArray[index];
	}
	
	/**
     * Return email at i
     * @param index positional index
     * @return email
     */
	public String getEmailAt(int index){
		if(index > number && index <0)
			return "";
		else
			return emailArray[index];
	}
	
	/**
     * Return organization at i
     * @param index positional index
     * @return organization
     */
	public String getOrgAt(int index){
		if(index > number && index <0)
			return "";
		else
			return orgArray[index];
	}
	
	/**
     * Return country at i
     * @param index positional index
     * @return country
     */
	public String getCountryAt(int index){
		if(index > number && index <0)
			return "";
		else
			return countryArray[index];
	}

}

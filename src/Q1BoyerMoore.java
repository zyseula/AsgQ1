/**
 *  The {@code BoyerMoore} class is provide a service to search an query in {@link AddressBook} by using BoyerMoore algorithm.
 *  <p>
 *  This class Initializes the charTable with 256 slots. 
 *  Pattern should be initialized with the service object
 *  <p>
 *  Author: Yi Zhang
 */
public class Q1BoyerMoore {
	private final int length =256;  
    private int[] charTable;    

    private String pattern;     
    
    /**
     * Default Constructor
     */
    public Q1BoyerMoore(){
    	
    }
    
    /**
     * Constructor
     * @param pattern string
     */
    public Q1BoyerMoore(String pattern) {
        this.pattern = pattern.toLowerCase();

        // position of rightmost occurrence of c in the pattern
        charTable = new int[length];
        for (int i = 0; i < length; i++)
            charTable[i] = -1;
        for (int j = 0; j < pattern.length(); j++)
            charTable[pattern.charAt(j)] = j;
    }

    /**
     * Pattern search. Return the index of the matching position and print if found 
     * @param txt pattern string
     * @return -1 if not found
     */
    public int search(String txt) {
    	txt = txt.toLowerCase();
        int m = pattern.length();
        int n = txt.length();
        int skip;
        for (int i = 0; i <= n - m; i += skip) {
            skip = 0;
            for (int j = m-1; j >= 0; j--) {
                if (pattern.charAt(j) != txt.charAt(i+j)) {
                    skip = Math.max(1, j - charTable[txt.charAt(i+j)]);
                    break;
                }
            }
            if (skip == 0) 
            {
            	return i;
            }
        }
        return -1;                      
    }

}

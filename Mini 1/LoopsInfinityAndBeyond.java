package mini1;

import java.util.Scanner;
/**
 * Utility class with a bunch of static methods for loop practice.
 * @author aditi.n
 */

public class LoopsInfinityAndBeyond {
	
	private LoopsInfinityAndBeyond() { }
	
	/**
	 * Returns a new string in which every character in the given string that
	 * is not already repeated consecutively is doubled.
	 * <p> 
	 * For example:
	 * <pre>{@code
	 * "attribute1" -> "aattrriibbuuttee11"
	 * "AAA Bonds" -> "AAA  BBoonnddss"
	 * }</pre>
	 * 
	 * @param text given starting string
	 * @return string with characters doubled
	 */
	public static String doubleChars(String text)
	{
		String newText = "";
		for (int i = 0; i < text.length(); i++) {
			if (i==0) {
				if (text.length()==1) {
					newText = newText + text.charAt(i) + text.charAt(i);
				}
				else if (text.charAt(i)!=text.charAt(i+1)||text.length()==1) {
					newText = newText + text.charAt(i) + text.charAt(i);
				}
				else {
					newText = newText + text.charAt(i);
				}
			}
			else if (i!=0 && i!=text.length()-1) {	
				if (text.charAt(i)!=text.charAt(i-1)&&text.charAt(i)!=text.charAt(i+1)) {
					newText = newText + text.charAt(i) + text.charAt(i);
				}
				else {
					newText = newText + text.charAt(i);
				}
			}
			else if (i==text.length()-1) {
				if (text.charAt(i)!=text.charAt(i-1)) {
					newText = newText + text.charAt(i) + text.charAt(i);
				}
				else {
					newText = newText + text.charAt(i);
				}
			}
			
		}
	return newText;
	}
	
	/**
	 * Returns a year with the highest value, given a string containing pairs
	 * of years and values (doubles). If there are no pairs, the method returns
	 * -1. In the case of a tie, the first year with the highest value is
	 * returned. Assumes the given string follows the correct format.
	 * <p>
	 * For example, given the following string, the year 1995 is returned.
	 * <pre>
	 * 1990 75.6 1991 110.6 1995 143.6 1997 62.3
	 * </pre>
	 * 
	 * @param data given string containing year-value pairs
	 * @return first year associated with the highest value, or -1 if no pair
	 *         given
	 */
	public static int maxYear(String data) {
		int maxYear =0;
		Scanner s = new Scanner(data);
		
		if(s.hasNextDouble()) {
			int YearNum = s.nextInt();
			double PairNum = s.nextDouble();

		while (s.hasNextDouble()) {
			int num = s.nextInt();
			double num1 = s.nextDouble();
			
			if((num1)>(PairNum)) {
				PairNum = num1;
				YearNum = num;
				maxYear = num;
			}
			else {
				maxYear = YearNum;
			}
		}
		return maxYear;
	}
	else {
		return -1;
		}
	}
	
	/**
	 * Returns the number of iterations required until <code>n</code> is equal to
	1,
	 * where each iteration updates <code>n</code> in the following way:
	 * 
	 * <pre>
	 *     if n is even
	 *         divide it in half
	 *     else
	 *         multiply n by three and add 1
	 * </pre>
	 * 
	 * For example, given <code>n == 6</code>, the successive values of
	 * <code>n</code> would be 3, 10, 5, 16, 8, 4, 2, 1, so the method returns 8.
	If
	 * <code>n</code> is less than 1, the method returns -1.
	 * <p>
	 * <em>(Remark:</em> How do we know this won't be an infinite loop for some
	 * values of <code>n</code>? In general, we don't: in fact this is a well-
	known
	 * open problem in mathematics. However, it has been checked for numbers up 
	to
	 * 10 billion, which covers the range of possible values of the Java
	 * <code>int</code> type.)
	 * 
	 * @param n given starting number
	 * @return number of iterations required to reach <code>n == 1</code>, or -1 
	if
	 *         <code>n</code> is not positive
	 */
	public static int collatzCounter(int n)
	{
		int tracker=0;
		
		if (n>=1) {
			
			while (n!=1) {
				if (n%2==0) {
				n = n/2;
				tracker++;
		}
		else {
			n= (n*3) +1;
			tracker++;
			}
		}
			return tracker;
			}
		else {
			return -1;
		}
	
	}
	/**
	 * Returns a new string in which every word in the given string is doubled. A
	 * word is defined as a contiguous group of non-space (i.e., ' ') characters
	 * that starts with an alphabetic letter and are surrounded by spaces and/or
	 * the start or end of the given string. Assumes the given string does not
	 * contain more than one consecutive white-space.
	 * <p> 
	 * For example:
	 * <pre>{@code
	 * "the time time" -> "the the time time time time"
	 * "The answer is 42." -> "The The answer answer is is 42."
	 * "A. runtime = 10ms" -> "A. A. runtime runtime = 10ms"
	 * }</pre>
	 * 
	 * @param text given starting string
	 * @return new string in which words are doubled
	 */
	public static String doubleWords(String text)
	{
		String w = "";
		String newText = "";
		Scanner s = new Scanner(text);
		
		while (s.hasNext()) {
			w = s.next();
			
			if (isWord(w)==true) {
				newText = newText + w + " " + w;
			}
			else {
				newText = newText + w;
			}
			if (s.hasNext()) {
				newText = newText + " ";
			}
		}
	return newText;
	}
	
	private static boolean isWord(String text)
	{
		boolean word = true;
		for (int i =0; i < text.length(); ++i) {
			if (!(text.charAt(i) >= 'A' && text.charAt(i) <= 'Z') && !(text.charAt(i) >= 'a' && text.charAt(i) <= 'z') && (text.charAt(i)!= '.')) {
				word = false;
            }
		}
	  return word;
	}
	/**
	 * Returns true if string t can be obtained from string s by removing exactly
	 * one vowel character. The vowels are the letters 'a', 'e', 'i', 'o'
	 * and 'u'. Vowels can be matched in either upper or lower case, for example,
	 * 'A' is treated the same as 'a'. If s contains no vowels the method returns
	 * false.
	 * <p>
	 * For example:
	 * <pre>{@code
	 * "banana" and "banna" returns true
	 * "Apple" and "ppl" returns false
	 * "Apple" and "pple" returns true
	 * }</pre>
	 * 
	 * @param s longer string
	 * @param t shorter string
	 * @return true if removing one vowel character from s produces the string t
	 */
	public static boolean oneVowelRemoved(String s, String t)
	{
		for (int i =0; i<s.length();++i) {
			if (isVowel(s.charAt(i)) == true){
				if (t.length()>0) {
				for (int j = 0; j<t.length();++j) {
					if (s.charAt(i)!=t.charAt(j) && t.length()==s.length()-1) {
						return true;
						}
					}
				}
				else {
					return true;
					}
				}
			}
		 return false;
	}
		
	private static boolean isVowel(char c)
	{
	  return "aeiouAEIOU".indexOf(c) >= 0;
	}
	/**
	 * Returns a new string in which a UFO pattern in the given string is
	 * shifted one character to the right. The UFO pattern starts with a
	 * {@code '<'}, has one or more {@code '='} and ends with a {@code '>'}. The 
	pattern may wrap
	 * around from the end to the beginning of the string, for example
	 * {@code ">  <="}. Any other characters from the given string remain in 
	place.
	 * If the UFO moves over top of another character, that character is
	 * removed. If there are multiple UFO patterns, only the one that starts
	 * farthest to the left is moved.
	 * <p> 
	 * For example:
	 * <pre>{@code
	 * " <=>  *   . <=> " ->
	 * "  <=> *   . <=> "
	 * 
	 * "   <=>*   .     " ->
	 * "    <=>   .     "
	 * 
	 * ">.   x     .  <=" ->
	 * "=>   x     .   <"
	 * 
	 * " <= <===>   .   " ->
	 * " <=  <===>  .   "
	 * }</pre>
	 * 
	 * @param space given string
	 * @return a new string with a UFO pattern moved one to the right
	 */
	public static String ufo(String space)
	{
		boolean moved =false;
		String space2 ="";
		int counter = 0;
		
		if (space.charAt(space.length()-1)=='=' || space.charAt(space.length()-1) =='<' || space.charAt(space.length()-1)=='>') {
			for (int i = 0; i < space.length();i++) {
				if (space.charAt(i)=='<') {
					counter = counter + 1;
				}
			}
			if (counter == 1) {
				space2 = space2 + space.charAt(space.length()-1);
				moved = true;
			}
			else {
				space2 = space2 + " ";
			}
		}
		else {
			space2 = space2 + " ";
		}
		for (int i = 0; i<space.length(); i++) {
			if (space.charAt(i)== '.'||space.charAt(i)== '*'||space.charAt(i)== 'x' || space.charAt(i)== '=') {	
				if (space.charAt(i-1)== '>') {
					space2 = space2 + "";
				}
				else {
					space2 = space2 + space.charAt(i);
				}
			}
			else if (moved && i == space.length()-2){
				String temp = "" + space.charAt(i);
				space2 = space2 + " " + temp;
			}
			else{
				space2 = space2 + space.charAt(i);
			}
		}
		if (space2.length()>space.length()) {
			int spaceDiff = (space2.length())-(space.length());
			space2 = space2.substring(0, space2.length() - spaceDiff);
		}
		for (int i = 0; i< space2.length(); i++) {
			if (space2.charAt(i) == '*' || space2.charAt(i) == '.'|| space2.charAt(i) == 'x') {
				if (space.charAt(i-1) == '*' || space.charAt(i-1) == '.'|| space.charAt(i-1) == 'x') {
					String sub = space2.substring(0, i-1);
					String sub2 = space2.substring(i, space2.length());
					space2 = sub + sub2 + " ";
					break;
				}
			}
		}
		for (int i = 0; i< space2.length()-3; i++) {
			if (space2.charAt(i+1) == '<' && space2.charAt(i+2) == '=' &&space2.charAt(i+3) == ' ') {	
				String sub = space2.substring(0, i);
				String sub2 = space2.substring(i+1, i + 4);
				String sub3 = space2.substring(i+4, space2.length());
				space2 = sub + sub2 + " " + sub3;
				break;
			}
		}
		return space2;
	}
		
	/**
	 * Prints a pattern of <code>2*n</code> rows containing slashes, dashes and 
	backslashes
	 * as illustrated below.
	 * <p>
	 * When {@code n <= 0 }, prints nothing.
	 * <p> 
	 * <code>n = 1</code>
	 * <pre>
	 * \/
	 * /\
	 * </pre>
	 * <p> 
	 * <code>n = 2</code>
	 * <pre>
	 * \--/
	 * -\/
	 * -/\
	 * /--\
	 * </pre>
	 * <p> 
	 * <code>n = 3</code>
	 * <pre>
	 * \----/
	 * -\--/
	 * --\/
	 * --/\
	 * -/--\
	 * /----\
	 * </pre>
	 * 
	 * @param n number of rows in the output
	 */
	public static void printX(int n)
	{
		if (n>0) {
			for (int i = 0; i<n;i++) {
				System.out.println("-".repeat(i) + "\\"+ "-".repeat(n-i-1) + "-".repeat(n-i-1) + "/");
				}
			for (int i = n; i>0;i--) {
				System.out.println("-".repeat(i-1) + "/"+ "-".repeat(n-i) + "-".repeat(n-i) + "\\");
				}
			}
			
		}
		
	}

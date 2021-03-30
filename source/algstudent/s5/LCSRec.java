package algstudent.s5;

import java.util.Random;

public class LCSRec {
	
	/**
	 * Generates a random sequence
	 * @param n sequence size
	 * @return random sequence, sting of characters
	 */
	public String genRandomSeq(int n){
		char[] dna_elements = {'A', 'C', 'G', 'T'};
		String result = "";
		Random r = new Random();
		for (int i=0; i<n; i++)
			result += dna_elements[r.nextInt(4)];
		return result;
	}
	
	/**
	 * Find a MSC directly by a recursive approach 
	 */
	public String findLongestSubseq(String s1, String s2){
		// TODO: find directly a MSC (whitout a table) of two input sequences using recursion
		if(s1.length() == 0 || s2.length() == 0) {
			return "";
		}
	
		String l1 = findLongestSubseq(s1.substring(0,s1.length()-1), s2);
		String l2 = findLongestSubseq(s1, s2.substring(0,s2.length()-1));
		String l3 = findLongestSubseq(s1.substring(0,s1.length()-1), s2.substring(0,s2.length()-1));
		
		int longest = 0;
		if(s1.charAt(s1.length()-1) == s2.charAt(s2.length()-1)) {
			longest = longest(l1, l2, l3, 1);
		}
		else{
			longest = longest(l1, l2, l3, 0);
		}
		
		String res = null;
		
		
				
		if(longest == 1) {
			res = l1;
		}
		if(longest == 2) {
			res = l2;
		}
		if(longest == 3) {
			res = l3;
			if(s2.charAt(s2.length()-1) == s1.charAt(s1.length()-1)) {
				res = l3 + s2.charAt(s2.length()-1);
			}
		}
		return res;
	}
	
	/**
	 * Get the index for the longest of three strings
	 * @param l1 e.g. input L1=MSC(S1’, S2). S1’ S1 without its last char
	 * @param l2 e.g. input L1=MSC(S1, S2'). S2' S2 without its last char
	 * @param l3 e.g. input L3=MSC(S1’, S2’) or L3+1 when both current chars are equal
	 * @return index of the longest string
	 */
	@SuppressWarnings("unused")
	private int longest(String l1, String l2, String l3, int n) {
		// TODO (optional): from three different sequences (e.g. subsequences) gets index for the longest
			if(l1.length() >= l2.length() && l1.length() >= l3.length()+n) {
				return 1;
			}
			
			if(l2.length() >= l1.length() && l2.length() >= l3.length()+n) {
				return 2;
			}
			
			if(l3.length()+n >= l2.length() && l3.length()+n >= l1.length()) {
				return 3;
			}
		return -1;
	}

}

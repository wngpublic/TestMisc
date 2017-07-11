import java.util.*;
public class SStrings {
	public boolean debug = true;
	public static void p(String f, Object ...a) {
		System.out.printf(f, a);
	}
	public String longestCommonSubsequence(String s1, String s2) {
		int sz1 = s1.length();
		int sz2 = s2.length();
		int [][] a = new int[sz1][sz2];
		
		/*
		 * for each character, compare with every char of other string.
		 * if cur char == other string char, take the prev diagonal + 1.
		 * else take max of either prev val in row or prev val in col.
		 * the a[x][y] will be max common subsequence. 
		 * to backtrack, start at a[x][y] and each diagonal increment
		 * is a matching char.
		 */
		for(int i = 0; i < sz1; i++) {
			for(int j = 0; j < sz2; j++) {
				char c1 = s1.charAt(i);
				char c2 = s2.charAt(j);
				int prvi = (i == 0) ? 0 : i - 1;
				int prvj = (j == 0) ? 0 : j - 1;
				if(c1 == c2) {
					a[i][j] = 1;
					if(i != 0 || j != 0) {
						a[i][j] += a[prvi][prvj];
					}
				} else {
					a[i][j] = (a[prvi][j] > a[i][prvj]) ? 
							a[prvi][j] : a[i][prvj];
				}
			}
		}
		
		if(debug) {
			// print row of column vals
			p("   ");
			for(int i = 0; i < sz2; i++) {
				p("%02s ", s2.charAt(i));
			}
			p("\n");

			for(int i = 0; i < sz1; i++) {
				// print col of row val
				p("%02s ", s1.charAt(i));
				// print the accumulated frequency
				for(int j = 0; j < sz2; j++) {
					p("%02d ", a[i][j]);
				}
				p("\n");
			}
		}
		
		StringBuilder sb = new StringBuilder();
		{
			boolean stop = false;
			for(int i = sz1-1, j = sz2-1; !stop && i > 0 && j > 0; ) {
				if(a[i][j] == (a[i-1][j-1] + 1)) {
					sb.append(s1.charAt(i));
					i--;
					j--;
				}
				else if(a[i][j] == a[i-1][j]) {
					i--;
				}
				else {
					j--;
				}
			}
		}
		String s = sb.reverse().toString();
		return s;
	}
	public String longestCommonSubstring(String s1, String s2) {
		int sz1 = s1.length();
		int sz2 = s2.length();
		int [][] a = new int[sz1][sz2];
		int maxi = 0, maxj = 0, max = 0;
		for(int i = 0; i < sz1; i++) {
			for(int j = 0; j < sz2; j++) {
				char c1 = s1.charAt(i);
				char c2 = s2.charAt(j);
				if(c1 == c2) {
					a[i][j] = (i == 0 || j == 0) ? 1 : a[i-1][j-1] + 1;
					if(a[i][j] > max) {
						max = a[i][j];
						maxi = i;
						maxj = j;
					}
				} else {
					a[i][j] = 0;
				}
			}
		}
		
		if(debug) {
			// print row of column vals
			p("   ");
			for(int i = 0; i < sz2; i++) {
				p("%02s ", s2.charAt(i));
			}
			p("\n");

			for(int i = 0; i < sz1; i++) {
				// print col of row val
				p("%02s ", s1.charAt(i));
				// print the accumulated frequency
				for(int j = 0; j < sz2; j++) {
					p("%02d ", a[i][j]);
				}
				p("\n");
			}
		}
				
		StringBuilder sb = new StringBuilder();
		{
			boolean stop = false;
			for(int i = maxi, j = maxj; !stop && i >= 0 && j >= 0; i--, j--) {
				if(a[i][j] == 0) {
					stop = true;
				} else {
					sb.append(s1.charAt(i));
				}
			}
		}
		String s = sb.reverse().toString();
		return s;
	}
	/**
	 * 0 8 4 12 2 10 6 14 1 9 5 13 3 11 7 15
	 * 
	 * 0 8
	 * 0 4 12
	 * 0 4 10
	 * 0 2 10
	 * 0 2 6
	 * 0 2 6 14
	 * 0 1
	 * 0 1 9
	 * 0 2 6 9
	 * 0 2 5 13
	 * 0 2 6 9 13
	 * 0 2 6 9 11
	 * 0 2 5 7
	 * 0 2 6 7
	 * 0 2 6 9 11 15
	 * 
	 * @param s1
	 * @return
	 */
	public String longestIncreasingSubsequence(String s) {
		int max = 0;
		StringBuilder sb = new StringBuilder();
		int [] ai = new int[s.length()];
		int idx1 = 0;
		int idx2 = 0;
		for(int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if(i == 0) {
				ai[i] = 1;
			}
			else {
				
			}
		}
		String sret = sb.toString();
		return sret;
	}
	public String longestPalindrome(String s1) {
		return null;
	}
	public int levenshteinDistance(String s1, String s2) {
		return -1;
	}
	public int findString(String needle, String haystack) {
		return -1;
	}
}

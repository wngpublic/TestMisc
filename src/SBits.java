import java.util.*;
public class SBits {
	static class Triple {
		public String bits;
		public String unsigned;
		public String signed;
	}
	public String int2bin(int v) {
		// easy way is Integer.toString(v, 2); // base 2
		StringBuilder sb = new StringBuilder();
		boolean isLeading0 = true;
		for(int i = 31; i >= 0; i--) {
			byte b = (byte)((v >> i) & 1);
			if(b != 0) {
				sb.append('1');
				isLeading0 = false;
			}
			else if(b == 0 && !isLeading0) {
				sb.append('0');
			}
			//sb.append((b == 0) ? '0' : '1');
		}
		String s = sb.toString();
		return s;
	}
	public Integer bin2int(String s) {
		int v = 0;
		if(s.length() > 32) {
			return null;
		}
		for(int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if(c != '1' && c != '0') {
				return null;
			}
			int b = (c == '1') ? 1 : 0;
			v = (v << 1) | b;
		}
		return v;
	}
	public String signed2unsigned(int i) {
		if((i >> 31) == 0) {
			
		}
		return null;
	}
	public String unsigned2signed(int i) {
		return null;
	}
	public String int2string(int i) {
		StringBuilder sb = new StringBuilder();
		int v = i;
		while(v != 0) {
			int m = v % 10;
			sb.append(b10i2s(m));
			v = (v - m) / 10;
		}
		String s = sb.reverse().toString();
		return s;
	}
	public String b10i2s(int i) {
		if(i == 0) return "0";		
		if(i == 1) return "1";		
		if(i == 2) return "2";	
		if(i == 3) return "3";	
		if(i == 4) return "4";	
		if(i == 5) return "5";	
		if(i == 6) return "6";	
		if(i == 7) return "7";	
		if(i == 8) return "8";	
		if(i == 9) return "9";	
		return null;
	}
	public int b10s2i(char c) {
		if(c == '0') return 0;
		if(c == '1') return 1;
		if(c == '2') return 2;
		if(c == '3') return 3;
		if(c == '4') return 4;
		if(c == '5') return 5;
		if(c == '6') return 6;
		if(c == '7') return 7;
		if(c == '8') return 8;
		if(c == '9') return 9;
		return -1;
	}
	public int string2int(String s) {
		return -1;
	}
	public void printTwosComplement(int v) {
		/*
		 * Bits 		Unsigned	Twos complement
		 * 0111 1111 	127  		127 
		 * 0111 1110 	126  		126 
		 * 0000 0010 	2  			2 
		 * 0000 0001 	1  			1 
		 * 0000 0000 	0  			0 
		 * 1111 1111 	255  		−1 
		 * 1111 1110 	254  		−2 
		 * 1000 0010 	130  		−126 
		 * 1000 0001 	129  		−127 
		 * 1000 0000 	128  		−128 
		 */
		int max = 0x7fffffff;
		
	}
	public void printTwosComplementNegative(int v) {
		int max = 0xffffffff;
		int twoc = v ^ -1;
		
	}
	public List<Integer> getTwosComplementRange(int i) {
		return null;
	}
	public List<SBits.Triple> getTwosComplementRange(int min, int max) {
		List<SBits.Triple> l = new ArrayList<>();
		for(int i = min; i <= max; i++) {
			SBits.Triple triple = new SBits.Triple();
		}
		return null;
	}
	public void printGetTwosComplementRange(int min, int max) {
		List<SBits.Triple> l = new ArrayList<>();
	}
}

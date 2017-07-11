import java.util.*;
import java.util.zip.CRC32;

public class Utils<E> {
	public static boolean debug = false;
    final static Random r_ = new Random(System.currentTimeMillis());

    public final static String charsetBio = "acgtu";
    public final static String charsetSymbols = "!@#$%^&*(){}[],.?<>";
    public final static String charsetDigits = "0123456789";
    public final static String charsetAlphaLC = "abcdefghijklmnopqrstuvwxyz";
    public final static String charsetAlphaUC = "ABCDEFGHJIKLMNOPQRSTUVWXYZ";
    public final static String charsetAlphaLCNum = charsetDigits + charsetAlphaLC;
    public final static String charsetAlphaNum = charsetDigits + charsetAlphaLC + charsetAlphaUC;

    public static void p(String s, Object ...a) {
    	System.out.printf(s, a);
    }
    public static int getInt(int min, int max) {
        int diff = max - min;
        int v = r_.nextInt(diff) + min;
        return v;
    }
    public static int getInt(int max) {
    	return getInt(0, max);
    }
    public static int getInt() {
        return getInt(Integer.MIN_VALUE, Integer.MAX_VALUE);
    }
    public static List<Integer> getInts(int min, int max, int numElements, boolean isUnique) {
        final int sizeRange = max - min;
        final List<Integer> l = new ArrayList<>();
        if(isUnique && sizeRange < numElements) {
        	return null;
        }
        else if(sizeRange == numElements) {
        	for(int i = min; i < max; i++) {
        		l.add(i);
        	}
        	return l;
        }
        final Set<Integer> set = new HashSet<>();
        for(int i = 0; i < numElements; i++) {
        	int v = getInt(min, max);
        	while(set.contains(v)) {
        		v = getInt(min, max);
        	}
            l.add(v);
        }
        return l;
    }
    public static char getChar(String charset) {
        int i = getInt(0, charset.length());
        return charset.charAt(i);
    }
    public static char getChar() {
        return getChar(charsetAlphaNum);
    }
    public static String getString(String charset, int sz) {
        int szCharset = charset.length();
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < sz; i++) {
            int j = getInt(0, szCharset);
            char c = charset.charAt(j);
            sb.append(c);
        }
        final String s = sb.toString();
        return s;
    }
    public static String getString(int sz) {
        return getString(charsetAlphaNum, sz);
    }
    public static void swap(int i, int j, char [] a) {
        char t = a[i];
        a[i] = a[j];
        a[j] = t;
    }
    public static void swap(int i, int j, int [] a) {
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }
    public void swap(int i, int j, List<E> l) {
        E t = l.get(i);
        l.set(i, l.get(j));
        l.set(j, t);
    }
    public static void shuffle(char [] a) {
        for(int i = 0; i < a.length; i++) {
        	int dst = getInt(a.length);
        	swap(i, dst, a);
        }
    }
    public static void shuffle(int [] a) {
        for(int i = 0; i < a.length; i++) {
        	int dst = getInt(a.length);
        	swap(i, dst, a);
        }
    }
    public void shuffle(List<E> l) {
        for(int i = 0; i < l.size(); i++) {
        	int dst = getInt(l.size());
        	swap(i, dst, l);
        }
    }
    public E getRandom(List<E> l) {
    	int sz = l.size();
    	int i = getInt(sz);
    	return l.get(i);
    }
    public static int getIntGaussian(int min, int max) {
    	/*
    	 * gaussian		1		-5:5
    	 * 				10		-50:50
    	 */
    	int median = (min + max) / 2;
    	int intrange = max - min + 1;
    	double range = (intrange) / 10.0;
    	return getIntGaussian(range, median, min, max);
    }
    public static int getIntGaussian(double range, int median, int min, int max) {
    	for(int i = 0; i < 100; i++) {
        	int v = (int)(r_.nextGaussian() * range) + median;
        	if(min <= v && v <= max)
        		return v;
    	}
    	return median;
    }
    public static List<Integer>
    getIntGaussianList(int size, int min, int max) {
    	int median = (min + max) / 2;
    	int intrange = max - min + 1;
    	double range = (intrange) / 10.0;
    	List<Integer> list = new ArrayList<>();
    	for(int i = 0; i < size; i++)
    		list.add(getIntGaussian(range, median, min, max));
    	return list;
    }
    public static List<Integer>
    getListRandomNormalProgressive(int size, int min, int max, boolean isIncreasing) {
    	int intrange = max - min + 1;
    	List<Integer> list = new ArrayList<>();
    	int stepsize = (int)Math.ceil(intrange/(size*1.0));
    	int step = min;
    	int distributionRange = (int)Math.ceil(intrange/3.0);
    	double range = (distributionRange) / 10.0;
    	for(int i = 0; i < size; i++) {
    		int v = getIntGaussian(range, step, min, max);
    		list.add(v);
    		step += stepsize;
    	}
    	return list;
    }
    public static List<Integer>
    getListRandomProgressive(int size, int min, int max, boolean isIncreasing) {
    	int intrange = max - min + 1;
    	List<Integer> list = new ArrayList<>();
    	int window = (intrange < size) ? (size - intrange) : (intrange - size);
    	int mincur = min;
    	int maxcur = min + window;
    	for(int i = 0; i < size; i++) {
    		int v = getInt(mincur, maxcur);
    		if(debug) {
        		p("min:%2d max:%2d mincur:%2d maxcur:%2d v:%2d\n",
            			min, max, mincur, maxcur, v);
    		}
    		list.add(v);
    		mincur = ((mincur+1) <= max) ? (mincur+1) : mincur;
    		maxcur = ((maxcur+1) <= max) ? (maxcur+1) : maxcur;
    	}
    	if(!isIncreasing) {
    		Collections.reverse(list);
    	}
    	return list;
    }
    /**
     * sublist
     * 
     * @param list
     * @param min inclusive
     * @param max exclusive
     * @return new list
     */
    public static List<Integer>
    sublist(List<Integer> list, int min, int max) {
    	List<Integer> newlist = new ArrayList<>();
    	for(int i = min; i < max; i++) {
    		newlist.add(list.get(i));
    	}
    	return newlist;
    }
    public static int getHash(String s) {
    	CRC32 crc = new CRC32();
    	crc.update(s.getBytes());
    	long v = crc.getValue();
    	return (int)v;
    }
    public static List<Integer> 
    getListInt(int size, int min, int max) {
    	List<Integer> list = new ArrayList<>();
    	int diff = max - min + 1;
    	for(int i = 0; i < size; i++) {
    		int v = r_.nextInt(diff) + min;
    		list.add(v);
    	}
    	return list;
    }
    public static List<Integer> 
    getListInt(int size, int min, int max, boolean unique) {
    	if(!unique)
    		return getListInt(size, min, max);
    	List<Integer> list = new ArrayList<>();
    	Set<Integer> set = new HashSet<>();
    	int diff = max - min + 1;
    	if(size > diff) {
    		return list;
    	}
    	else if(size == diff) {
        	for(int i = min; i <= max; i++) {
        		list.add(i);
        	}
    	}
    	else {
        	for(int i = 0; i < size; i++) {
        		int v = r_.nextInt(diff) + min;
    			int ctr = 0;
        		while(set.contains(v)) {
        			v = r_.nextInt(diff) + min;
        			if(ctr++ > 1000)
        				return list;
        		}
        		list.add(v);
        		set.add(v);
        	}
    	}
    	return list;
    }
    public static List<List<Integer>> 
    getMatrixListInt(int numRows, int numCols, int min, int max) {
    	List<List<Integer>> list = new ArrayList<>();
    	for(int i = 0; i < numRows; i++) {
    		List<Integer> row = getListInt(numCols, min, max);
    		list.add(row);
    	}
    	return list;
    }
    public static int []
    getArrayInt(int size, int min, int max) {
    	int [] a = new int[size];
    	getArrayInt(a, min, max);
    	return a;
    }
    private static void getArrayInt(int []a, int min, int max) {
    	int diff = max - min + 1;
    	for(int i = 0; i < a.length; i++) {
    		int v = r_.nextInt(diff) + min;
    		a[i] = v;
    	}
    }
    public static int [][]
    getMatrixArrayInt(int numRows, int numCols, int min, int max) {
    	int [][] a = new int[numRows][numCols];
    	for(int i = 0; i < numRows; i++)
    		getArrayInt(a[i], min, max);
    	return a;
    }
    public static int [][]
    matrixListToArrayInt(List<List<Integer>> list) {
    	int [][] a = new int[list.size()][list.get(0).size()];
    	for(int i = 0; i < list.size(); i++) {
    		List<Integer> row = list.get(i);
    		listToArrayInt(a[i], row);
    	}
    	return a;
    }
    public static int []
    listToArrayInt(List<Integer> list) {
    	int [] a = new int[list.size()];
    	listToArrayInt(a, list);
    	return a;
    }
    private static void listToArrayInt(int [] a, List<Integer> list) {
    	for(int i = 0; i < list.size(); i++)
    		a[i] = list.get(i);
    }
    public static List<Integer>
    arrayToListInt(int [] a) {
    	List<Integer> list = new ArrayList<>();
    	for(int i = 0; i < a.length; i++)
    		list.add(a[i]);
    	return list;
    }
    public static List<List<Integer>>
    matrixToListInt(int [][] a) {
    	List<List<Integer>> list = new ArrayList<>();
    	for(int i = 0; i < a.length; i++) {
    		List<Integer> row = arrayToListInt(a[i]);
    		list.add(row);
    	}
    	return list;
    }
    public static List<List<Integer>>
    copyMatrixListInt(List<List<Integer>> lli) {
    	List<List<Integer>> list = new ArrayList<>();
    	for(List<Integer> li: lli) {
    		List<Integer> row = new ArrayList<>(li);
    		list.add(row);
    	}
    	return list;
    }
    public static int [][]
    copyMatrixArrayInt(int [][] ai) {
    	int [][] a = new int[ai.length][ai[0].length];
    	for(int i = 0; i < ai.length; i++) 
    		for(int j = 0; j < ai[i].length; j++) 
    			a[i][j] = ai[i][j];
    	return a;
    }
    public static int [][]
    initMatrixInt(int numRow, int numCol, int val) {
    	int [][] a = new int[numRow][numCol];
    	for(int i = 0; i < numRow; i++)
    		for(int j = 0; j < numCol; j++)
    			a[i][j] = val;
    	return a;
    }
    public static int []
    initArrayInt(int size, int val) {
    	int [] a = new int[size];
    	for(int i = 0; i < size; i++) {
    		a[i] = val;
    	}
    	return a;
    }
    public static void printMatrixInt(int [][] a) {
    	p("PRINT MAXTRIX\n");
    	int numRow = a.length;
    	int numCol = a[0].length;
    	{
    		// print idx col
			p("   |");
			for(int i = 0; i < numCol; i++)
				p("%3d ", i);
			p("\n");
			
			p("   +");
			for(int i = 0; i < numCol; i++)
				p("----", i);
			p("\n");
    	}
    	for(int i = 0; i < numRow; i++) {
    		// print idx row
    		p("%3d|", i);
    		for(int j = 0; j < numCol; j++) 
				p("%3d ", a[i][j]);
    		p("\n");
    	}
    }
    public static void printMatrixListInt(List<List<Integer>> list) {
    	p("PRINT MAXTRIX\n");
    	int numRow = list.size();
    	int numCol = list.get(0).size();
    	{
    		// print idx col
			p("   |");
			for(int i = 0; i < numCol; i++)
				p("%3d ", i);
			p("\n");
			
			p("   +");
			for(int i = 0; i < numCol; i++)
				p("----", i);
			p("\n");
    	}
    	for(int i = 0; i < numRow; i++) {
    		// print idx row
    		List<Integer> row = list.get(i);
    		p("%3d|", i);
    		for(int j = 0; j < numCol; j++) 
				p("%3d ", row.get(j));
    		p("\n");
    	}
    }
	public static List<Integer> makeList(Integer ...i) {
		return Arrays.asList(i);
	}
	public static int min(List<Integer> list) {
		int min = list.get(0);
		for(int i = 1; i < list.size(); i++)
			if(min > list.get(i))
				min = list.get(i);
		return min;
	}
	public static String aryToString(char [] a) {
		return new String(a);
	}
	public static int factorial(int n) {
		if(n == 0)
			return 0;
		int v = 1;
		for(int i = 1; i <= n; i++) {
			v *= i;
		}
		return v;
	}
}

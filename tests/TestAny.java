import java.lang.reflect.Method;
import java.util.*;

public class TestAny {
	public static boolean debug = false;
	public static void p(String f, Object ...o) {
		System.out.printf(f, o);
	}
	public static void pl(Object o) {
		System.out.println(o);
	}
	public void test(int tc) {
		int idxStart = 4;
		try {
			if(debug)
				printTestCases();
			Method [] methods = TestAny.class.getDeclaredMethods();
			if(idxStart <= tc && tc < methods.length)
				methods[tc].invoke(this);
			else 
				printTestCases();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void printTestCases() {
		Method [] methods = TestAny.class.getDeclaredMethods();
		int idxStart = 4;
		for(int i = idxStart; i < methods.length; i++) {
			p("%02d: %s\n", i, methods[i].getName());
		}
	}
	public void testLongestIncreasingSubsequence() {
		SLists.Misc algos = new SLists.Misc();
		int size = 20;
		int min = 10;
		int max = 50;
		List<Integer> list = Utils.getListRandomProgressive(size, min, max, true);
		List<Integer> path = algos.longestIncreasingSubsequence(list);
		p("LIS ORIGINAL\n");
		System.out.println(list);
		p("LONGEST INCREASING SUBSEQUENCE\n");
		System.out.println(path);
	}
	public void testLongestIncreasingSubsequenceSimple() {
		SLists.Misc algos = new SLists.Misc();
		int size = 20;
		int min = 10;
		int max = 50;
		List<Integer> list = Utils.getListRandomProgressive(size, min, max, true);
		List<Integer> path = algos.longestIncreasingSubsequenceSimple(list);
		p("LIS ORIGINAL\n");
		System.out.println(list);
		p("LONGEST INCREASING SUBSEQUENCE\n");
		System.out.println(path);
	}
	public void testLongestIncreasingSubsequenceSimpleFixedList() {
		SLists.Misc algos = new SLists.Misc();
		List<Integer> list = Arrays.asList(18, 20, 31, 14, 25, 21, 18, 32, 19, 26, 29, 30, 37, 30, 41, 26, 40, 33, 28, 40);
		List<Integer> path = algos.longestIncreasingSubsequenceSimple(list);
		p("LIS ORIGINAL\n");
		System.out.println(list);
		p("LONGEST INCREASING SUBSEQUENCE\n");
		System.out.println(path);
	}
	public void testShortestIncreasingSubsequenceFocused() {
		SLists.Misc algos = new SLists.Misc();
		List<Integer> list = Arrays.asList();
		List<Integer> path = algos.shortestIncreasingSubsequence(list);
		p("SHORTEST INCREASING SUBSEQUENCE ORIGINAL\n");
		System.out.println(list);
		p("SHORTEST INCREASING SUBSEQUENCE\n");
		System.out.println(path);
	}
	public void testLongestIncreasingSubequenceDP() {
		SLists.Misc algos = new SLists.Misc();
		int size = 20;
		int min = 10;
		int max = 50;
		List<Integer> list = Utils.getListRandomProgressive(size, min, max, true);
		List<Integer> path = algos.longestIncreasingSubequenceDP(list);
		p("LIS ORIGINAL\n");
		System.out.println(list);
		p("LONGEST INCREASING SUBSEQUENCE\n");
		System.out.println(path);
	}
	public void testLongestIncreasingSubsequenceNLOGN() {
		SLists.Misc algos = new SLists.Misc();
		int size = 20;
		int min = 10;
		int max = 50;
		List<Integer> list = Utils.getListRandomProgressive(size, min, max, true);
		List<Integer> path = algos.longestIncreasingSubsequenceNLOGN(list);
		p("LIS ORIGINAL\n");
		System.out.println(list);
		p("LONGEST INCREASING SUBSEQUENCE\n");
		System.out.println(path);
	}
	public void testShortestIncreasingSubsequence() {
		SLists.Misc algos = new SLists.Misc();
		int size = 20;
		int min = 10;
		int max = 50;
		List<Integer> list = Utils.getListRandomProgressive(size, min, max, true);
		List<Integer> path = algos.shortestIncreasingSubsequence(list);
		p("SIS ORIGINAL\n");
		System.out.println(list);
		p("SHORTEST INCREASING SUBSEQUENCE\n");
		System.out.println(path);
	}
	public void testLongestIncreasingSubsequenceFocusedString() {
		SLists.Misc algos = new SLists.Misc();
		int size = 20;
		int min = 10;
		int max = 50;
		List<Integer> list = Utils.getListRandomProgressive(size, min, max, true);
		List<Integer> path = algos.longestIncreasingSubsequence(list);
		p("LIS ORIGINAL\n");
		System.out.println(list);
		p("LONGEST INCREASING SUBSEQUENCE\n");
		System.out.println(path);
	}
	public void dungeonHealthMatrix() {
		
	}
	public void testBinarySearchIterative() {
		SLists.Misc algos = new SLists.Misc();
		int size = 21;
		List<Integer> list = new ArrayList<>();
		for(int i = 0; i < size; i++)
			list.add(i);
		p("BINARY SEARCH ITERATIVE ORIG\n");
		System.out.println(list);
		for(int i = 0; i < size; i++) {
			int idx = algos.binarySearchIterative(list, i);
			p("IDX of %2d:%2d\n", i, idx);
		}
	}
	public void testBinarySearchIterativeFindClosestUnder() {
		SLists.Misc algos = new SLists.Misc();
		p("testBinarySearchIterativeFindClosestUnder\n");
		int size = 11;
		{
			int max = 0;
			List<Integer> list = new ArrayList<>();
			for(int i = 0; i < size; i++) {
				max = i*2;
				list.add(max);
			}
			p("TEST EVEN\n");
			System.out.println(list);
			for(int i = 0; i < max; i++) {
				int idx = algos.binarySearchIterativeFindClosestUnder(list, i);
				p("IDX of %2d:%2d\n", i, idx);
			}
		}
		p("\n");
		{
			int max = 0;
			List<Integer> list = new ArrayList<>();
			for(int i = 0; i < size; i++) {
				max = i*2+1;
				list.add(max);
			}
			p("TEST ODD\n");
			System.out.println(list);
			for(int i = 0; i < max; i++) {
				int idx = algos.binarySearchIterativeFindClosestUnder(list, i);
				p("IDX of %2d:%2d\n", i, idx);
			}
		}
	}
	public void testTypedefPairII() {
		SLists.PairII p = new SLists.PairII(10, 20);
		p("PAIRII v1:%2d v2:%2d\n", p.v1, p.v2);
	}
	public void testSubListCopyAndAdd() {
		List<Integer> list = new ArrayList<>();
		for(int i = 0; i < 10; i++)
			list.add(i);
		//List<Integer> l1 = list.subList(5, 8); // this is VIEW ONLY
		List<Integer> l1 = Utils.sublist(list, 5, 8);
		p("testSubListCopyAndAdd\n");
		p("orig list\n");
		System.out.println(list);
		p("subarray 5-8\n");
		System.out.println(l1);
		l1.add(20);
		list.add(10);
		p("sublist add 20 and orig list add 10\n");
		p("orig list\n");
		System.out.println(list);
		p("subarray 5-8\n");
		System.out.println(l1);
	}
	public void testNumericKeypadCombinations() {
		p("numericKeypadCombinations 2\n");
		SLists.Misc algos = new SLists.Misc();
		int num = 3;
		int total = algos.numericKeypadCombinations(num);
		p("total = %d\n", total);
	}
	public void testBinPacking() {
		p("bin packing\n");
		SLists.Misc algos = new SLists.Misc();
		int capacity = 30;
		List<Integer> list = Utils.getListInt(20, 2, 15);
		Collections.sort(list);
		int numBins = algos.binPackingNumBins(list, capacity);
		p("LIST: ");
		pl(list);
		p("NUM BINS: %d\n", numBins);
	}
	public void testBinPackingFocus() {
		p("bin packing focused test\n");
		SLists.Misc algos = new SLists.Misc();
		int capacity = 10;
		List<Integer> list = Arrays.asList(1,1,1,1,2,2,3,3,3,4,5,5,5,6,7,8);
		Collections.sort(list);
		int numBins = algos.binPackingNumBins(list, capacity);
		p("LIST: ");
		pl(list);
		p("NUM BINS: %d\n", numBins);
	}
	public void testExactFit() {
		p("exact fit of capacity, given list\n");
		SLists.Misc a = new SLists.Misc();
		int capacity = 15;
		List<Integer> list = Utils.getListInt(10, 1, 9, false);
		Collections.sort(list);
		a.exactFit(list, capacity);
		p("capacity:%2d; inputlist: ", capacity);
		System.out.println(list);
	}
	public void testKnapsack() {
		p("knapsack problem\n");
		List<SLists.PairII> listPairs = new ArrayList<>();
		int size = 10;
		int capacity = 20;
		boolean isWeight = true;
		{
			int weightMin = 1;
			int weightMax = 7;
			int valMin = 1;
			int valMax = 10;
			List<Integer> listW = Utils.getListInt(size, weightMin, weightMax);
			List<Integer> listV = Utils.getListInt(size, valMin, valMax);
			for(int i = 0; i < size; i++) {
				SLists.PairII pair = new SLists.PairII(listW.get(i), listV.get(i));
				listPairs.add(pair);
			}
		}
		SLists.Misc algo = new SLists.Misc();
		p("CAP:%2d \n", capacity);
		for(int i = 0; i < listPairs.size(); i++) {
			if(i != 0 && i % 10 == 0)
				p("\n");
			SLists.PairII p = listPairs.get(i);
			p("(%2d,%2d) ", p.v1, p.v2);
		}
		p("\n");
		List<SLists.PairII> list = algo.knapsack(listPairs, capacity, isWeight);
		assert (list != null) : "ERR:LIST RESULT IS NULL\n";
	}
	public void testKnapsackFocused() {
		p("knapsack focused problem\n");
		/*
		 * 1,  2, 3, 3, 4, 4, 5, 5, 6, 7
		 * 6, 10, 5, 9, 7, 8, 4, 3, 4, 4
		 * 
		 * weight    00 01 02 03 04 05 06 07 08 09 10 
		 * item val
		 * 1      6   0  6  6  6  6  6  6  6  6  6  6  
		 * 2     10   0  6 10 10 10 10 10 10 10 10 10
		 * 3      5   0  6 10 10 16 
		 *     
		 *  for i = 0 to n
		 *  	for w = 0 to max
		 *  		if w[i] > w
		 *  			m[i,w] = m[i-1,w]
		 *  		else
		 *  			m[i,w] = max(m[i-1,w], v[i]+m[i-1,w-w[i]])
		 *  
		 *  w	01,03,04,07
		 *  v	03,10,17,25
		 *  
		 *  	w|	1	2	3	4	5	6	7	8	9	10	11	12
		 *  idx	n|
		 *  -----+-------------------------------------------------
		 *  0	1|	3	6	9	12	15	18	21	24	27	30	33	36
		 *  1	3|	3	6	10	13	16	19	22	25	28	31	34	37
		 *  	 |	scratch			@5 15 < ((10+w[0,5-3]) == 10+6)
		 *  	 |						@6 18 < ((10+w[0,6-3]) == 10+9)
		 *  	 |							@7 21 < ((10+w[0,7-3]) == 10+12)
		 *  2	4|	3	6	
		 *  	 |	scratch
		 *  3	7|	3	6		
		 * 
		 */
		List<SLists.PairII> listPairs = new ArrayList<>();
		int size = 10;
		int capacity = 20;
		boolean isWeight = true;
		{
			List<Integer> listW = Arrays.asList(1,2,3,3,4,4,5,5,6,7);
			List<Integer> listV = Arrays.asList(6,10,5,9,7,8,4,3,4,4);
			for(int i = 0; i < size; i++) {
				SLists.PairII pair = new SLists.PairII(listW.get(i), listV.get(i));
				listPairs.add(pair);
			}
		}
		SLists.Misc algo = new SLists.Misc();
		List<SLists.PairII> list = algo.knapsack(listPairs, capacity, isWeight);
		p("CAP:%2d \n", capacity);
		for(int i = 0; i < listPairs.size(); i++) {
			if(i != 0 && i % 10 == 0)
				p("\n");
			SLists.PairII p = listPairs.get(i);
			p("(%2d,%2d) ", p.v1, p.v2);
		}
		p("\n");
	}
	public void testEditDistance() {
		String src = "abcdeg";
		String dst = "zbcdeg";
		SLists.Misc algos = new SLists.Misc();
		int distance;

		src = "abcdef";
		dst = "abcdef";
		distance = algos.editDistance(src, dst);
		p("edit distance\n");
		p("src:%s dst:%s distance:%d\n\n", src, dst, distance);

		src = "aaaaaa";
		dst = "aaaaa";
		distance = algos.editDistance(src, dst);
		p("src:%s dst:%s distance:%d\n\n", src, dst, distance);

		src = "baaaaa";
		dst = "aaaaa";
		distance = algos.editDistance(src, dst);
		p("src:%s dst:%s distance:%d\n\n", src, dst, distance);

		src = "abcdeg";
		dst = "zbcdeg";
		distance = algos.editDistance(src, dst);
		p("src:%s dst:%s distance:%d\n\n", src, dst, distance);

		src = "abcdeg";
		dst = "zbcdeh";
		distance = algos.editDistance(src, dst);
		p("src:%s dst:%s distance:%d\n\n", src, dst, distance);

		src = "abcdef";
		dst = "aabcdef";
		distance = algos.editDistance(src, dst);
		p("src:%s dst:%s distance:%d\n\n", src, dst, distance);

		src = "abcdeg";
		dst = "zzbcdeg";
		distance = algos.editDistance(src, dst);
		p("src:%s dst:%s distance:%d\n\n", src, dst, distance);

		src = "abcdeg";
		dst = "zzbcdeh";
		distance = algos.editDistance(src, dst);
		p("src:%s dst:%s distance:%d\n\n", src, dst, distance);
	}
	public void testCombination() {
		p("testCombination\n");
	}
	public void testCombinationFocused() {
		p("testCombinationFocused\n");
		int size = 6;
		String str = Utils.getString(size);
		char [] asrc = str.toCharArray();
		int choose = 3;
		p("String:%s size:%d choose:%d\n", str, size, choose);
		SLists.Misc misc = new SLists.Misc();
		List<String> list = misc.combination(asrc, choose);
		int expNumComb = Utils.factorial(size) / 
				(Utils.factorial(choose) * Utils.factorial(size-choose));
		p("expected number of comb: %d actual:%d\n", expNumComb, list.size());
	}
	public void testPermutation() {
		p("testPermutation\n");
	}
	public void testPermutationFocused() {
		p("testPermutationFocused\n");
		int size = 5;
		String str = Utils.getString(size);
		char [] asrc = str.toCharArray();
		int choose = 3;
		p("String:%s size:%d choose:%d\n", str, size, choose);
		SLists.Misc misc = new SLists.Misc();
		List<String> list = misc.permutation(asrc, choose);
		int expNumPerm = Utils.factorial(size) / Utils.factorial(size-choose);
		p("expected number of perm: %d actual:%d\n", expNumPerm, list.size());
	}
	public void testRegex() {
		
	}
	public void testRegexFocused1() {
		String pat = "abcd";
		String txt = "abcd";
		SLists.Misc misc = new SLists.Misc();
		int res = 0;
		
		res = misc.regex(pat, txt);
		p("00 pat:%10s txt:%10s result:%d\n", pat, txt, res);
		assert(res == 1) : "expected 1\n";
		
		pat = "abcd";
		txt = "abcde";
		res = misc.regex(pat, txt);
		p("01 pat:%10s txt:%10s result:%d\n", pat, txt, res);
		assert(res == 0) : "expected 0\n";
	
		pat = "abcde";
		txt = "abcd";
		res = misc.regex(pat, txt);
		p("02 pat:%10s txt:%10s result:%d\n", pat, txt, res);
		assert(res == 0) : "expected 0\n";
		
		pat = "a..d";
		txt = "abcd";
		res = misc.regex(pat, txt);
		p("03 pat:%10s txt:%10s result:%d\n", pat, txt, res);
		assert(res == 1) : "expected 1\n";

		pat = "a.*d";
		txt = "abcdd";
		res = misc.regex(pat, txt);
		p("04 pat:%10s txt:%10s result:%d\n", pat, txt, res);
		assert(res == 1) : "expected 1\n";

		pat = "b.*d";
		txt = "abcdd";
		res = misc.regex(pat, txt);
		p("05 pat:%10s txt:%10s result:%d\n", pat, txt, res);
		assert(res == 0) : "expected 0\n";

		pat = "a.*";
		txt = "abcdd";
		res = misc.regex(pat, txt);
		p("06 pat:%10s txt:%10s result:%d\n", pat, txt, res);
		assert(res == 1) : "expected 1\n";

		pat = "abc.*d";
		txt = "abcdd";
		res = misc.regex(pat, txt);
		p("07 pat:%10s txt:%10s result:%d\n", pat, txt, res);
		assert(res == 1) : "expected 1\n";

		pat = "abc.*dd";
		txt = "abcdd";
		res = misc.regex(pat, txt);
		p("08 pat:%10s txt:%10s result:%d\n", pat, txt, res);
		assert(res == 1) : "expected 1\n";

		pat = "abcdd.*";
		txt = "abcdd";
		res = misc.regex(pat, txt);
		p("09 pat:%10s txt:%10s result:%d\n", pat, txt, res);
		assert(res == 1) : "expected 1\n";

		pat = "ac.*";
		txt = "abcdd";
		res = misc.regex(pat, txt);
		p("10 pat:%10s txt:%10s result:%d\n", pat, txt, res);
		assert(res == 0) : "expected 0\n";

		pat = "abc.*d";
		txt = "abcdc";
		res = misc.regex(pat, txt);
		p("11 pat:%10s txt:%10s result:%d\n", pat, txt, res);
		assert(res == 0) : "expected 0\n";

		pat = "abcz*d";
		txt = "abcdd";
		res = misc.regex(pat, txt);
		p("12 pat:%10s txt:%10s result:%d\n", pat, txt, res);
		assert(res == 1) : "expected 1\n";

		pat = "abczz*d";
		txt = "abcdd";
		res = misc.regex(pat, txt);
		p("13 pat:%10s txt:%10s result:%d\n", pat, txt, res);
		assert(res == 0) : "expected 0\n";
	}
	public void testRegexFocused2() {
		SLists.Misc misc = new SLists.Misc();

		String pat;
		String txt;
		pat = "a.*d";
		txt = "abcdd";

		int res = misc.regex(pat, txt);
		p("pat:%10s\ntxt:%10s result:%d\n\n", pat, txt, res);
	}
	public void testGraphG1() {
		SGraphs.G g = new SGraphs.G();
		{
			Map<Integer, List<Integer>> m = new HashMap<>();
			m.put(1, Arrays.asList(3,4));
			m.put(2, Arrays.asList(3,4,5));
			m.put(3, Arrays.asList(4,5));
			m.put(4, Arrays.asList(5,6));
			m.put(5, Arrays.asList(6));
			m.put(6, Arrays.asList(7));
			m.put(7, Arrays.asList());
			for(Map.Entry<Integer, List<Integer>> kv: m.entrySet()) {
				g.addVertex(kv.getKey());
			}
			for(Map.Entry<Integer, List<Integer>> kv: m.entrySet()) {
				Integer k = kv.getKey();
				List<Integer> list = kv.getValue();
				for(Integer e: list)
					g.addEdge(k, e, true);
			}
			p("testGraphG1 DIRECTED\n");
			g.print();
			p("\n");
		}
		g.reset();
		{
			Map<Integer, List<Integer>> m = new HashMap<>();
			m.put(1, Arrays.asList(3,4));
			m.put(2, Arrays.asList(3,4,5));
			m.put(3, Arrays.asList(4,5));
			m.put(4, Arrays.asList(5,6));
			m.put(5, Arrays.asList(6));
			m.put(6, Arrays.asList(7));
			m.put(7, Arrays.asList());
			for(Map.Entry<Integer, List<Integer>> kv: m.entrySet()) {
				g.addVertex(kv.getKey());
			}
			for(Map.Entry<Integer, List<Integer>> kv: m.entrySet()) {
				Integer k = kv.getKey();
				List<Integer> list = kv.getValue();
				for(Integer e: list)
					g.addEdge(k, e, false);
			}
			p("testGraphG1 UNDIRECTED\n");
			g.print();
		}
	}
	public void testSubsetSum() {
		
	}
	public void testSubsetSumFocused() {
		SLists.Misc misc = new SLists.Misc();
		List<Integer> l = Arrays.asList(2,3,5,7,8,10,11);
		int sum = 15;
		List<List<Integer>> ll = misc.subsetSum(l, sum);
		p("SUM: %d; INPUT LIST: ", sum);
		pl(l);
		p("OUTPUT LIST\n");
		for(List<Integer> list: ll) {
			pl(list);
		}
	}
	public void testSubarraySumFocused() {
		SLists.Misc misc = new SLists.Misc();
		List<Integer> l;
		int sum = 125;
		{
			p("testSubarraySum for sum %2d\n", sum);
			l = Arrays.asList(2,3,5,7,8,10,11);
			List<Integer> lres = misc.subarraySum(l, sum);
			p("INPUT: ");
			pl(l);
			pl(lres);
		}
		{
			sum = 25;
			p("testSubarraySum for sum %2d\n", sum);
			l = Arrays.asList(3,5,7,8,10,11);
			List<Integer> lres = misc.subarraySum(l, sum);
			p("INPUT: ");
			pl(l);
			pl(lres);
		}
		{
			sum = 25;
			p("testSubarraySum for sum %2d\n", sum);
			l = Arrays.asList(3,5,-6,7,8,10,11);
			List<Integer> lres = misc.subarraySum(l, sum);
			p("INPUT: ");
			pl(l);
			pl(lres);
		}
		{
			sum = 25;
			p("testSubarraySum for sum %2d\n", sum);
			l = Arrays.asList(3,5,-2,-6,7,8,8,8,11);
			List<Integer> lres = misc.subarraySum(l, sum);
			p("INPUT: ");
			pl(l);
			pl(lres);
		}
	}
	public void testSublistMaxFocused() {
		SLists.Misc misc = new SLists.Misc();
		List<Integer> l;
		List<Integer> lres;
		{
			l = Arrays.asList(3,-1,4,-5,6,7,-3,10);
			p("testSublistMax input: ");
			pl(l);
			lres = misc.sublistMax(l);
			p("out: ");
			pl(lres);
		}
		{
			l = Arrays.asList(3,-1,4,-5,6,10,7,-3,-8);
			p("testSublistMax input: ");
			pl(l);
			lres = misc.sublistMax(l);
			p("out: ");
			pl(lres);
		}
		{
			//   3  -1   4  -9   6  10   7  -3  -8   // vals
			//   3   2   6  -3   3  13  20  17   9   // sumsofar
			//   3   3   6   6   6  13  20  20  20   // max
			//   3   2   6  -3   6  16  23  20  12   // new sumsofar
			l = Arrays.asList(3,-1,4,-9,6,10,7,-3,-8);
			p("testSublistMax input: ");
			pl(l);
			lres = misc.sublistMax(l);
			p("out: ");
			pl(lres);
		}
		{
			l = Arrays.asList(-9,-8,-1,-9,-6,-10,-7,-8);
			p("testSublistMax input: ");
			pl(l);
			lres = misc.sublistMax(l);
			p("out: ");
			pl(lres);
		}
		{
			//   3  -5  80 -90   6  10  80  -3  -8   // vals
			//   3  -2  78 -12  -6   4  84  81  73   // sumsofar
			//   3   3  78  78  78  78  84  84  84   // max
			//   3  -2  80 -10   6  16  96  93  85   // new sumsofar
			l = Arrays.asList(3,-5,80,-90,6,10,80,-3,-8);
			p("testSublistMax input: ");
			pl(l);
			lres = misc.sublistMax(l);
			p("out: ");
			pl(lres);
		}
	}
	public void testSublistMaxFocused1() {
		SLists.Misc misc = new SLists.Misc();
		List<Integer> l;
		List<Integer> lres;
		{
			l = Arrays.asList(-9,-8,-1,-9,-6,-10,-7,-8);
			p("testSublistMax input: ");
			pl(l);
			lres = misc.sublistMax(l);
			p("out: ");
			pl(lres);
		}
	}
	public void testGraphLongestPathFocused() {
		SGraphs.GAlgos algos = new SGraphs.GAlgos();
		//algos.longestPath(g, src, dst)
	}
    /**
    * Convert unordered pairs into longest diameter tree.
    * Given is int [][] a, which is 
    * 
    *   a[0][0] = 1, a[0][1] = 2
    *   a[1][0] = 3, a[1][2] = 1
    *   a[2][0] = 4, a[2][2] = 3
    *   a[3][0] = 6, a[3][2] = 2
    *   a[4][0] = 3, a[4][2] = 5
    *   a[5][0] = 5, a[5][2] = 9
    *   a[6][0] = 5, a[6][2] = 7
    *   a[7][0] = 8, a[7][2] = 7
    *
    *           1----2----6
    *           |
    *           3----4
    *           |
    *           5----7----8
    *           |
    *           9
    *           
    */
    public void testDiameterTreeUnorderedPairs() {
        SLists.Misc misc = new SLists.Misc();
        int [][] a = {{1,2},{3,1},{4,3},{6,2},{3,5},{5,7},{5,9},{8,7}};
        Utils.printMatrixInt(a);
        misc.diameterTreeUnorderedPairs(a);
    }
}

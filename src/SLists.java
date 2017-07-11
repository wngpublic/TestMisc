import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class SLists {
	public static boolean debug = true;
	public static int ctrdebug_ = 0;
	public static void p(String f, Object ...o) {
		System.out.printf(f, o);
	}
	public static void pl(Object o) {
		System.out.println(o);
	}
	public static class Pair<T extends Comparable<T>, U> 
	implements Comparable<Pair<T,U>> {
		public T v1;
		public U v2;
		public Pair(T t, U u) {
			v1 = t;
			v2 = u;
		}
		@Override
		public int compareTo(Pair<T, U> p) {
			return v1.compareTo(p.v1);
		}
	}
	public static class PairII extends Pair<Integer, Integer> {
		public PairII(Integer v1, Integer v2) {
			super(v1, v2);
		}
	}
	public static class LinkedList<E> {
		
	}
    public static class BNode {
        public int value;
        public BNode(int v) {
            value = v;
        }
        public Set<BNode> set = new HashSet<>();
        public boolean add(BNode n) {
            if(set.contains(n)) {
                return false;
            }
            set.add(n);
            return true;
        }
        public Set<BNode> getSet() {
            return set;
        }
        public BNode get(int i) {
            for(BNode n: set) {
                if(n.get() == i) {
                    return n;
                }
            }
            return null;
        }
        public int get() {
            return value;
        }
        public void print() {
            p("V:%2d ", value);
            for(BNode n: set) {
                p("Node:%2d ", n.get());
            }
            p("\n");
        }
    }
	public static class Misc {
		/**
		 * binarySearchIterative
		 * 
		 * return the index if v is found, else return -1
		 * 
		 * @param list
		 * @param searchval
		 * @return
		 * 
		 * if v > searchval
		 *     hi = mid-1
		 * if v < searchval
		 *     lo = mid+1
		 * 
		 * look for val 3
		 * eg	0 1 2 3 4 5 6 7	   min max mid val@mid
		 * 		1 2 3 4 5 6 7 8	   0   7   3   4	      hi = mid-1
		 *                         0   2   1   2          lo = mid+1          
		 *                         2   2   2   3
		 * look for 7
		 * eg	0 1 2 3 4 5 6 7	   min max mid val@mid
		 * 		1 2 3 4 5 6 7 8	   0   7   3   4          lo = mid+1
		 *                         3   7   5   6          lo = mid+1          
		 *                         6   7   6   7          
		 * look for 8
		 * eg	0 1 2 3 4 5 6 7	   min max mid val@mid
		 * 		1 2 3 4 5 6 7 8	   0   7   3   4          lo = mid+1
		 *                         4   7   5   6          lo = mid+1
		 *                         6   7   6   7          lo = mid+1
		 *                         7   7   7   8          
		 * look for 8
		 * eg	0 1 2 3 4 5 6 7	8  min max mid val@mid
		 * 		1 2 3 4 5 6 7 8	9  0   8   4   5          lo = mid+1
		 *                         5   8   6   7          lo = mid+1
		 *                         6   8   7   8
		 * look for 4
		 * eg	0 1 2 3 4 5 6 7	8  min max mid val@mid
		 * 		1 2 3 4 5 6 7 8	9  0   8   4   5          hi = mid-1
		 *                         0   3   1   2          lo = mid+1
		 *                         2   3   2   3          lo = mid+1
		 *                         3   3   3   4
		 */
		public int binarySearchIterative(List<Integer> list, int searchval) {
			int idxmin = 0;
			int idxmax = list.size()-1;
			int idxmid = (idxmax+idxmin)/2;
			while(idxmin <= idxmax) {
				int v = list.get(idxmid);
				if		(v > searchval) {
					idxmax = idxmid-1;
				}
				else if	(v < searchval) {
					idxmin = idxmid+1;
				}
				else {
					return idxmid;
				}
				idxmid = (idxmin+idxmax)/2;
			}
			return -1;
		}
		/**
		 * binarySearchIterativeFindClosestUnder
		 * Use iterative binary search to find idx where:
		 *     list.get(idx) < searchval && searchval <= list.get(idx+1)
		 * There may or may not be value in list == searchval.
		 * 
		 * @param list
		 * @param searchval
		 * @return
		 */
		public int binarySearchIterativeFindClosestUnder(
			List<Integer> list, int searchval) {
			int idxmin = 0;
			int idxmax = list.size()-1;
			int idxmid = (idxmax+idxmin)/2;
			if(searchval <= list.get(0))
				return -1;
			while(idxmin <= idxmax) {
				int v = list.get(idxmid);
				if	(searchval > v) {
					idxmin = idxmid+1;
				}
				else if	(searchval <= v) {
					if(list.get(idxmid-1) < searchval) {
						return (idxmid-1);
					}
					idxmax = idxmid-1;
				}
				idxmid = (idxmin+idxmax)/2;
			}
			return -1;
		}
		public List<Integer> longestIncreasingSubsequence(List<Integer> listin) {
			List<Integer> path = new ArrayList<>();
			return path;
		}
		/**
		 * This is NlogN. for each input value, check if it's bigger than
		 * last element of result list. if it is, then add to result list. if it
		 * is smaller than last element of result list, then do binary search
		 * on result list to find closest number under current. set the
		 * current value to that index position in the result list (replace, not add).
		 * 
		 * @param listin
		 * @return
		 */
		public List<Integer> longestIncreasingSubsequenceSimple(List<Integer> listin) {
			List<Integer> pathCur = new ArrayList<>();
			List<Integer> path = new ArrayList<>();
			ctrdebug_ = 0;
			longestIncreasingSubsequenceSimple(listin, 0, pathCur, path);
			int lenOnly = longestIncreasingSubsequenceSimpleLengthOnly(listin);
			if(lenOnly == path.size()) {
				p("LIS len algos size %d\n", lenOnly);
			}
			else {
				p("ERROR: LIS len algos size (%d) != path algos size (%d)\n", 
					lenOnly, path.size());
			}
			return path;
		}
		private int longestIncreasingSubsequenceSimpleLengthOnly(List<Integer> lin) {
			List<Integer> lval = new ArrayList<>();
			for(int i = 0; i < lin.size(); i++) {
				int vcur = lin.get(i);
				if(lval.size() == 0) {
					lval.add(vcur);
				}
				else if(lval.get(0) > vcur) {
					lval.set(0, vcur);
				}
				else if(lval.get(lval.size()-1) < vcur) {
					lval.add(vcur);
				}
				else if(lval.get(lval.size()-1) == vcur){
					continue;
				}
				else {
					int idx = binarySearchIterativeFindClosestUnder(lval, vcur);
					idx = (idx == -1) ? 0 : idx;
					lval.set(idx, vcur);
				}
			}
			p("PRINT LVAL TABLE\n");
			System.out.println(lval);
			return lval.size();
		}
		private void longestIncreasingSubsequenceSimple(
			List<Integer> listin,
			int i,
			List<Integer> pathCur,
			List<Integer> path
		) {
			if(i >= listin.size()) {
				return;
			}
			ctrdebug_++;
			if(i == (listin.size()-1)) {
				if(pathCur.size() > path.size()) {
					path.clear();
					path.addAll(pathCur);
				}
				return;
			}
			int vcur = listin.get(i);
			if(pathCur.size() == 0) {
				pathCur.add(vcur);
			}
			else {
				int vprv = pathCur.get(pathCur.size()-1);
				if		(vprv < vcur) {
					pathCur.add(vcur);
				}
				else if	(vprv > vcur) {
					// binary search lowest point in pathCur
					// then spawn off new list and add vcur.
					// if new list size == pathCur size, then replace pathCur
					// if new list size < pathCur size, then do recursive on both lists
					int idx = binarySearchIterativeFindClosestUnder(pathCur, vcur);
					List<Integer> pathNew = (idx != -1) ?
						Utils.sublist(pathCur, 0, idx+1) :
						new ArrayList<>();
					pathNew.add(vcur);
					if((idx != -1) && (idx+1+1) >= pathCur.size()) {
						// new list size is equivalent or bigger than old list
						pathCur.clear();
						pathCur.addAll(pathNew);
					}
					else {
						// new list size is smaller, so run both simultaneously
						longestIncreasingSubsequenceSimple(listin, i+1, pathNew, path);
					}
				}
			}
			longestIncreasingSubsequenceSimple(listin, i+1, pathCur, path);
		}
		/**
		 * longestIncreasingSubequenceDP
		 * 
		 *                 idx    00 01 02 03 04 05 06 07 08 09 10 11 12 13 14 15
		 *                   v    24 20 19 25 23 30 35 18 19 31 20 50 40 21 32 45
		 *                        --------------------------------------------
		 *                 list1  00
		 *                           01
		 *                              02 03
		 *                              02    04 05 06
		 *                              02    04 05          09    11
		 *                              02    04 05          09       12
		 *                              02    04 05          09             14 15   <-- 2nd
		 *                 list3        02                               13 14 15
		 *                              
		 *                 list2                       07 08 09 10 11
		 *                                             07 08 09 10    12
		 *                                             07 08 09 10       13 14 15   <-- winner
		 *                 prv1    -  -  - 02
		 *                                    02 04 05       05
		 *                 prv2                         - 
		 *                 
		 * listin
		 * dp
		 * prv
		 * 
		 * for i = 0; i < n; i++
		 *     dp[i] = 1
		 *     prv[i] = 1
		 *     
		 * 
		 * 
		 * 
		 * @param listin
		 * @return
		 */
		public List<Integer> longestIncreasingSubequenceDP(List<Integer> list) {
			List<Integer> path = new ArrayList<>();
			return path;
		}
		public List<Integer> longestIncreasingSubsequenceNLOGN(List<Integer> list) {
			List<Integer> path = new ArrayList<>();
			return path;
		}
		/**
		 * shortestIncreasingSubsequence
		 * 
		 *  list	2  3  1  1  4
		 *  steps	0  1        2
		 *  
		 *  list	2  8  3  4  6 10
		 *  		0  1  1  2  3  2
		 *  
		 *  list	2  8  1  1  
		 *  
		 *  
		 * @param list
		 * @return
		 */
		public List<Integer> shortestIncreasingSubsequence(List<Integer> list) {
			List<Integer> path = new ArrayList<>();
			for(int i = 0; i < list.size(); i++) {
				
			}
			return path;
		}
		/**
		 * knapsack takes in a list of pairs, which has v1 and v2, and a max arg, 
		 * and isMaxOfArg1, which asks for max of either v1 or v2.
		 * 
		 * index	0  1  2  3  4  5  
		 * weight	2  3  4  5  6  7  
		 * value	4  3  7  2  4  5 
		 * 
		 * max weight is 18, find max value
		 * 
		 * index	1  2  3  4  5  6  7  8  9 10 11 12 13 14 15 16 17 18
		 * value       4  3  8  7 11 11 
		 *                              11  // from 8-1
		 *                              15  // from 8-2 = 6 + 2
		 *                                 15 // from 9-1
		 *                                 15 // from 9-2
		 *                                 // from 9-3
		 * 
		 * -+-+--------------------------------
		 * w|v|01 02 03 04 05 06 07 08 09 10 11  
		 * -+-+--------------------------------
		 * 1|1| 1  2  3  4  5  6  7  8  9 10 11
		 * 2|3| 1  3  4  6  7  9 10 12 13 15 16
		 * 3|5| 1  3  5  6  8 10 11 13 15 16 18
		 * 4|8| 1  3  5  8  9 11 13 16 17 20 21
		 * 
		 *  w|  1   2   3   4
		 *  v|  1   3   5   8
		 * --+----------------
		 * 01|
		 * 02|
		 * 03|
		 * 04|
		 * 05|
		 * 06|
		 * 07|
		 * 08|
		 * 09|
		 * 10|
		 * 11|
		 * 
		 * knapsack several ways:
		 * 
		 * 1. given n, W, and pairs of (w1,v1), (w2,v2), ..., (wn,vn)
 		 * 		for w = 0 to W
 		 * 			m[0, w] = 0
 		 * 		for i = 1 to n
 		 * 			for w = 0 to W
 		 * 				if wi > w
 		 * 					m[i,w] = m[i-1,w]
 		 * 				else
 		 * 					m[i,w] = max(m[i-1,w], vi+m[i-1,w-wi])
 		 * 		return m[n,W]
 		 * 
 		 * 2. bottom up
 		 * 		for i = 0 to n
 		 * 			for j = 0 to W
 		 * 				if i == 0 || j == 0
 		 * 					m[i][j] = 0
 		 * 				else if a[i-1].w <= j
 		 * 					m[i][j] = max(a[i-1].v + m[i-1][w-a[i-1].w], m[i-1][j])
 		 * 				else
 		 * 					m[i][j] = m[i-1][j]
 		 * 		return m[n][W]
 		 * 
 		 * 2. recursion naive
 		 * 		int knapsack(list<pair> list, int i, int w)
 		 * 			if i == 0 || w == 0
 		 * 				return 0
 		 * 			if list.get(i).w > w
 		 * 				return knapsack(list, i-1, w)
 		 * 			int case1 = knapsack(list, i-1, w)
 		 * 			int case2 = knapsack(list, i-1, w-list.get(i).w) + list.get(i).v
 		 * 			return max(case1, case2)
 		 * 
 		 * 	3. brute force
 		 * 		find all combinations with weight <= max and return the highest
 		 * 		value.
 		 * 
 		 * recomputation of subproblems can be avoided by constructing m[][] bottom up.
		 * 
		 * @param listin
		 * @param max
		 * @param isMaxOfArg1
		 * @return
		 */
		public List<PairII> knapsack(List<PairII> listin, int max, boolean isMaxOfArg1) {
			Collections.sort(listin);
			List<PairII> list = new ArrayList<>();
			int size = listin.size();
			
			// a is DP table of value, not weight. the column index is implicitly weight
			int [][] a = new int[size][max];
			// for each pair of weight,value
			// v1 is weight, v2 is value
			for(int i = 0; i < size; i++) {
				PairII p = listin.get(i);
				for(int j = 0; j < max; j++) {
					// if pair weight > current weight index, ignore it
					if(i == 0 || j == 0)
						a[i][j] = 0;
					else if(p.v1 > j) 
						a[i][j] = a[i-1][j];
					else {
						int idxDiff = j - p.v1;
						int valDiff = a[i-1][idxDiff];
						int curValPlusDiffVal = p.v2 + valDiff;
						if(curValPlusDiffVal > a[i-1][j]) 
							a[i][j] = curValPlusDiffVal;
						else 
							a[i][j] = a[i-1][j];
					}
				}
			}
			if(debug) {
				// print the row of columns, with header offset
				p(" w  v|");
				for(int i = 0; i < max; i++)
					p("%2d ", i);
				p("\n");
				p("-----+");
				for(int i = 0; i < max; i++)
					p("---", i);
				p("\n");
				for(int i = 0; i < size; i++) {
					PairII p = listin.get(i);
					p("%2d %2d|", p.v1, p.v2);
					for(int j = 0; j < max; j++) {
						p("%2d ", a[i][j]);
					}
					p("\n");
				}
				p("\n");
			}
			return list;
		}
		public List<PairII> knapsackNoRepetition(
			List<PairII> listin, int max, boolean isMaxOfArg1) {
			Collections.sort(listin);
			List<PairII> list = new ArrayList<>();
			int size = listin.size();
			return list;
		}
		/**
		 * cuttingStock 
		 * 
		 * @param listin
		 * @return
		 */
		public List<PairII> cuttingStock(List<PairII> listin) {
			List<PairII> list = new ArrayList<>();
			return list;
		}
		/**
		 * numericKeypadCombinations
		 * 
		 *     1    2    3
		 *         abc  def
		 *         
		 *     4    5    6
		 *    ghi  jkl  mno    
		 *    
		 *     7    8    9
		 *    pqrs tuv  wxyz
		 *    
		 *          0
		 *     
		 * Given n, and only moving u,d,l,r, find number of combinations for n length.
		 * 
		 * eg 
		 * for 0, there are 10 combinations (0,1,2,..,9)
		 * for 1, there are 36 (00,08,11,12,22,21,etc)
		 * 
		 * @param n
		 * @return number of combinations
		 */
		public int numericKeypadCombinations(int n) {
			List<Integer> list = new ArrayList<>();
			int total = 0;
			for(int i = 0; i <= 9; i++) {
				list.clear();
				total += numericKeypadCombinations(list, i, n);
			}
			return total;
		}
		private int numericKeypadCombinations(List<Integer> list, int curnumpad, int max) {
			list.add(curnumpad);
			if(list.size() == max) {
				System.out.println(list);
				list.remove(list.size()-1);
				return 1;
			}
			int total = numericKeypadCombinations(list, curnumpad, max);
			if(curnumpad == 0) {
				total += numericKeypadCombinations(list, 8, max);
			}
			else if(curnumpad == 1) {
				total += numericKeypadCombinations(list, 2, max);
				total += numericKeypadCombinations(list, 4, max);
			}
			else if(curnumpad == 2) {
				total += numericKeypadCombinations(list, 1, max);
				total += numericKeypadCombinations(list, 3, max);
				total += numericKeypadCombinations(list, 5, max);
			}
			else if(curnumpad == 3) {
				total += numericKeypadCombinations(list, 2, max);
				total += numericKeypadCombinations(list, 6, max);
			}
			else if(curnumpad == 4) {
				total += numericKeypadCombinations(list, 1, max);
				total += numericKeypadCombinations(list, 5, max);
				total += numericKeypadCombinations(list, 7, max);
			}
			else if(curnumpad == 5) {
				total += numericKeypadCombinations(list, 2, max);
				total += numericKeypadCombinations(list, 4, max);
				total += numericKeypadCombinations(list, 6, max);
				total += numericKeypadCombinations(list, 8, max);
			}
			else if(curnumpad == 6) {
				total += numericKeypadCombinations(list, 3, max);
				total += numericKeypadCombinations(list, 5, max);
				total += numericKeypadCombinations(list, 9, max);
			}
			else if(curnumpad == 7) {
				total += numericKeypadCombinations(list, 4, max);
				total += numericKeypadCombinations(list, 8, max);
			}
			else if(curnumpad == 8) {
				total += numericKeypadCombinations(list, 5, max);
				total += numericKeypadCombinations(list, 7, max);
				total += numericKeypadCombinations(list, 9, max);
				total += numericKeypadCombinations(list, 0, max);
			}
			else if(curnumpad == 9) {
				total += numericKeypadCombinations(list, 6, max);
				total += numericKeypadCombinations(list, 8, max);
			}
			list.remove(list.size()-1);
			return total;
		}
		/**
		 * numWaysPathMatrixCombination number of ways to get from topleft to bottomright in
		 * a matrix. can only go right or down.
		 * 
		 *   | 0  1  2  3  4  5
		 * --+-----------------
		 *  0| 1  1  1  1  1  1
		 *  1| 1  2  3  4  5  6
		 *  2| 1  3  6 10 15 21
		 *  3| 1  4 10 20 35 56
		 *  4| 1  5 15 35 70 ..
		 *  5| 1  6 21 56 .. ..
		 *  
		 * @param a
		 * @param src
		 * @param dst
		 * @return
		 */
		public int numWaysPathMatrixCombination(int [][] a) {
			int numRow = a.length;
			int numCol = a[0].length;
			int [][] anum = new int[numRow][numCol];
			for(int i = 0; i < a.length; i++) {
				for(int j = 0; j < a[0].length; j++) {
					if(i == 0) {
						anum[i][j] = 1;
					} 
					else if(j == 0) {
						anum[i][j] = 1;
					}
					else {
						anum[i][j] = anum[i][j-1] + anum[i-1][j];
					}
				}
			}
			return anum[numRow-1][numCol-1];
		}
		/**
		 * bin packing: 
		 * 
		 * 
		 * 
		 * given n number of weights and each bin capacity c, assign items into bin such that
		 * total number of bins used is minimized. capacity is the same, weights are different.
		 * 
		 * best fit for one bin may not be best fit for all bins, so greedy probably
		 * wont work.
		 * 
		 * there are offline and online versions. offline is where all weights
		 * are presented at once. online is where items are coming in after
		 * some bins are used.
		 * 
		 * if offline, there is next fit, first fit, best fit. best fit is
		 * NlogN using self balancing trees. best fit can also be next item
		 * in the tightest spot.
		 * 
		 * original list:	2 2 3 3 3 5 5 6 6 
		 * capacity:		8
		 * 
		 * 					total
		 * ----------------------------------------------
		 * 	2	2	3		7
		 * 		3	3		8	2,3,5,5,6,6 remain
		 * 		5			7
		 * 		6			8	2,2,3,3,3,5,5,6 remain
		 * 	3	3			6
		 * 		5			8	2,2,3,3,5,6,6 remain
		 * 	5				5
		 * 	6				6
		 * 
		 * remain list 1:	2,3,5,5,6,6 (from first iteration)
		 * 2	3			5
		 * 3	5			8	2,5,6,6 remain
		 * 5				5
		 * 6				6
		 * 
		 * remain list 2:	2,2,3,3,3,5,5,6 (from first iteration)
		 * 2	2	3		7
		 * 		3	3		8	2,3,5,5,6 remain
		 * 3	3			6
		 * 		5			8	2,2,3,3,5,6 remain
		 * 5				5
		 * 6				6
		 * 
		 * remain list 3:	2,2,3,3,5,6,6 (from first iteration)
		 * 2	2	3		7
		 * 		3	3		8	2,5,6,6 remain
		 * 		5			7
		 * 		6			8	2,3,3,5,6 remain
		 * 3	5			8	2,2,3,6,6 remain
		 * 5				5
		 * 6				5
		 * 
		 * remain list 4:	2,5,6,6 (from remain list 1)
		 * 2	5			7
		 * 		6			8	6,6 remain
		 * 5
		 * 6
		 * 
		 * remain list 5:	2,3,5,5,6 (from remain list 2)
		 * 2	3
		 * 		5
		 * 		6			8	3,5,5 remain
		 * 3	5			8	2,5,6 remain
		 * 5
		 * 6
		 * 
		 * remain list 6:	2,2,3,3,5,6 (from remain list 2)
		 */
		public int binPackingNumBins(List<Integer> listWeights, int binCapacity) {
			return 0;
		}
		/**
		 * exactFit: given a list of items, get all the ways that can produce
		 * the exact combination capacity.
		 * 
		 * use a tree. given unlimited 1 2 3 4, find all combinations that lead to 7;
		 * you can go from bottom up, or top to bottom. lets do both.
		 * 
		 * 	bottom up
		 * 	rule is you must go higher but not lower
		 * 									sum
		 * 	1	1	1	1	1	1	1		7
		 * 						2			7
		 * 					2				6
		 * 					3				7
		 * 				2	2				7
		 * 				3					6
		 * 				4					7
		 * 			2	2					6
		 * 				3					7
		 * 			3						5
		 * 			4						6
		 * 		2	2	2					7
		 * 			3						6
		 * 		3							4
		 * 		4							5
		 * 	2	2	2						6
		 * 		3							5
		 * 		4							6
		 * 	3	3							6
		 * 		4							7
		 * 	4								4
		 * 
		 * 	top to bottom
		 * 	the rule is each branch, you must go lower but not higher
		 * 
		 * 	val								sum
		 * 	9								9
		 * 		8	1						9
		 * 		7	2						9
		 * 		7	1	1					9
		 * 		6	3						9
		 * 		6	2	1					9	overlapping subproblem
		 * 		6	1	1	1				9	overlapping subproblem
		 * 		5	4						9	overlapping subproblem
		 * 		5	3	1					9	overlapping subproblem
		 * 		5	2	1	1				9	overlapping subproblem
		 * 		5	1	1	1	1			9	overlapping subproblem
		 * 		4	4	1					9	overlapping subproblem
		 * 		4	3	1					9	overlapping subproblem
		 * 
		 * 	top to bottom for 9?
		 * 	the rule is each branch, you must go lower but not higher
		 * 
		 * @param list
		 * @param capacity
		 */
		public void exactFit(List<Integer> list, int capacity) {
			List<Integer> list1 = new ArrayList<>(list);
			Collections.sort(list1);
			exactFitBottomUp(list1, capacity);
		}
		public void exactFitBottomUp(List<Integer> list, int capacity) {
			List<Integer> path = new ArrayList<>();
			ctrdebug_ = 0;
			exactFitBottomUpRecursive(list, path, 0, 0, capacity);
		}
		private void exactFitBottomUpRecursive(
			List<Integer> list, 
			List<Integer> path,
			int idx,
			int curcap,
			int capacity) {
			if(curcap > capacity) {
				return;
			}
			ctrdebug_++;
			if(curcap == capacity) {
				p("MATCH: ");
				System.out.println(path);
				return;
			}
			for(int i = idx; i < list.size(); i++) {
				int val = list.get(i);
				path.add(val);
				p("idx%2d val:%2d curcap:%2d; ", i, val, curcap);
				System.out.println(path);
				exactFitBottomUpRecursive(list, path, i+1, curcap+val, capacity);
				path.remove(path.size()-1);
			}
		}
		/**	
		 * 
		 * column is weight
		 * row is element type
		 * given element type and everything prior to it, what is the number of
		 * ways to obtain that weight? express that in columns.
		 * 
		 *    1  2  3  4  5  6  7  max
		 * 1  1  0  0  0  0  0  0   1
		 * 1  2  1  0  0  0  0  0   2
		 * 2  2  2  2  1  0  0  0   4
		 * 2  2  3  4  3  2  1  0   6
		 * 3  2  3  4  4  4  3      9
		 * 3  2                    12
		 * 4  2                    16
		 * 4  2                    20
		 * 5  2                    25
		 * @param list
		 * @param path
		 * @param capacity
		 */
		private void exactFitBottomUpIterative(
			List<Integer> list, 
			List<Integer> path,
			int capacity
		) {
			int curcap = 0;
			int [][] a = new int[list.size()][capacity];
			a = Utils.initMatrixInt(list.size(), capacity, 0);
			for(int i = 0; i < list.size(); i++) {
				for(int j = 0; j < capacity; j++) {
					
				}
				int val = list.get(i);
				if((curcap+val) > capacity) {
					continue;
				}
				path.add(val);
				if((curcap+val) == capacity) {
					System.out.println(path);
				}
				else {
					
				}
				path.remove(path.size()-1);
			}
		}
		private void exactFitTopDown(List<Integer> list, int capacity) {
			
		}
		/**
		 * bestFitUnder: given a list of items, get the set of lists that
		 * is closest to, or equal to, the given capacity.
		 * @param list
		 * @param capacity
		 */
		public void bestFitUnder(List<Integer> list, int capacity)
		{
		}
		/**
		 * Given an array of strings of variable length, cut them to equal size
		 * such that there is minimal waste.
		 */
		public void cuttingStrings() {
			
		}
		/**
		 * Given an array of strings of variable length, and a set of sizes,
		 * cut them to the set of sizes such that there is minimal waste.
		 */
		public void cuttingStringsVariableSets() {
			
		}
		/**
		 * Given unlimited supply of strings of size X, need to fulfill
		 * an array of variable sizes and quantity. Find minimal number of
		 * strings of size X to fulfill request, with minimal waste.
		 * 
		 * eg:
		 * unlimited input strings are size 5600.
		 * array of variable sizes and quantity for each size:
		 * length	qty
		 * 1380		22
		 * 1520		25
		 * 1560		12
		 * 
		 * Find number of strings of size 5600 needed to fulfill this array.
		 */
		public void cuttingStringUnlimitedResourceToFixedSet() {
			
		}
		public void kdTree() {
			
		}
		/**
		 * Given pairs of numbers, first num < second num. What is longest
		 * chain of pairs where p1.secondnum < p2.firstnum?
		 * 
		 * 1. brute force. first sort
		 * 			5,24	15,28	27,35	28,38	39,60	45,48	70,90
		 * 
		 * 			5,24	15,28					39,60			70,90
		 * 							27,35			39,60			70,90
		 * 									28,38	39,60			70,90
		 * 											39,60			70,90
		 * 													45,48	70,90
		 * 															70,90
		 * 		above has overlapping problems, so use DP bottom up
		 * 	
		 * 2. DP similar to longest increasing subsequence
		 * 			5,24	15,28	27,35	28,38	39,60	45,48	70,90
		 * 	5,24				
		 * 	15,28	
		 * 	27,35	
		 * 	28,38	
		 * 	39,60	
		 * 	45,48	
		 * 	70,90	
		 * 
		 * (),(),(),(),(),()
		 * 
		 * 	for(i = 0; i < n; i++)
		 * 		for(j = 0; j < i; j++)
		 * 			if(in[i].a > in[j].b && len[i] < len[j]+1)
		 * 				len[i] = len[j]+1
		 *  for(i = 0; i < n; i++)
		 *  	if(max < len[i])
		 *  		max = len[i]
		 *  return max
		 *  
		 */
		public void maxChainLengthOfPairs() {
			
		}
		/**
		 * edit distance tracks the number of steps to change String src to String dst.
		 * 
		 * Look at this as a tree of changes, which can be done recursively.
		 * 
		 * Another way is iterative full matrix, using levenshtein/wagner-fischer
		 * @param src
		 * @param dst
		 * @return
		 */
		public int editDistanceDP(char [] asrc, char [] adst, List<String> list) {
			int [][] a = new int[asrc.length][adst.length];
			boolean firstRowMatched = false;
			boolean firstColMatched = false;
			// rowMatched and colMatched are used such that
			// if the character matched once already on the first row,
			// dont match it again if u see it again. eg
			//   0 1 2 3 4 5
			//   b c a a a a 
			// a 1 2 2 3 4 5 // the a is counted once already
			// 
			// else the simple logic is this:
			// for i each char of A
			//    for j each char of B
			//        if(i == 0 || j == 0)
			//            a[i][j] = (A[i] == B[i]) ? 0 : 1
			//        else if(A[i] == B[i])
			//            a[i][j] = a[i-1][j-1]
			//        else
			//            a[i][j] = 1 + min(a[i-1][j],a[i][j-1])
		
			for(int i = 0; i < asrc.length; i++) {
				for(int j = 0; j < adst.length; j++) {
					if(i == 0 && j == 0) {
						if(asrc[i] == adst[j]) {
							firstRowMatched = true;
							firstColMatched = true;
							a[i][j] = 0;
						} else {
							a[i][j] = 1;
						}
					}
					else if(i == 0) {
						if(!firstRowMatched && asrc[i] == adst[j]) {
							a[i][j] = a[i][j-1];
							firstRowMatched = true;
						} else {
							a[i][j] = a[i][j-1]+1;
						}
					}
					else if(j == 0) {
						if(!firstColMatched && asrc[i] == adst[j]) {
							a[i][j] = a[i-1][j];
							firstColMatched = true;
						} else {
							a[i][j] = a[i-1][j]+1;
						}
					}
					else if(asrc[i] == adst[j]) {
						a[i][j] = a[i-1][j-1];
					}
					else {
						a[i][j] = min(a[i-1][j], a[i][j-1], a[i-1][j-1]) + 1;
					}
				}
			}
			debug = false;
			if(debug) {
				// print adst column
				p("  |");
				for(int j = 0; j < adst.length; j++) {
					p("%s ", adst[j]);
				}
				p("\n");
				p("  +");
				for(int j = 0; j < adst.length; j++) {
					p("---");
				}
				p("\n");
				for(int i = 0; i < asrc.length; i++) {
					p("%2s|", asrc[i]);
					for(int j = 0; j < adst.length; j++) {
						p("%d ", a[i][j]);
					}
					p("\n");
				}
			}
			return a[asrc.length-1][adst.length-1];
		}
		/**
		 * edit distance tracks the number of steps to change String src to String dst.
		 * 
		 * Look at this as a tree of changes, which can be done recursively.
		 * 
		 * Another way is iterative full matrix, using levenshtein/wagner-fischer
		 * @param src
		 * @param dst
		 * @return
		 */
		public int editDistanceRecursive(
			char [] asrc, char [] adst, int isrc, int idst, List<String> list) {
			if(isrc == asrc.length && idst == adst.length) 
				return 0;
			if(isrc == asrc.length) 
				return (adst.length-idst-1);
			if(idst == adst.length) 
				return (asrc.length-isrc-1);
			int cost = (asrc[isrc] == adst[idst]) ? 0 : 1;
			int min1 = editDistanceRecursive(asrc, adst, isrc+1, idst, list) + 1;
			int min2 = editDistanceRecursive(asrc, adst, isrc, idst+1, list) + 1;
			int min3 = editDistanceRecursive(asrc, adst, isrc+1, idst+1, list) + cost;
			int min = min(makeList(min1, min2, min3)); 
			return min;
		}
		private List<Integer> makeList(Integer ...i) {
			return Utils.makeList(i);
		}
		private int min(Integer ...i) {
			List<Integer> list = makeList(i);
			return Utils.min(list);
		}
		private int min(List<Integer> list) {
			return Utils.min(list);
		}
		public int editDistance(String src, String dst) {
			List<String> list = new ArrayList<>();
			char [] asrc = src.toCharArray();
			char [] adst = dst.toCharArray();
			int distance = editDistanceDP(asrc, adst, list);
			int distanceRecursive = editDistanceRecursive(asrc, adst, 0, 0, list);
			p("distanceDP:%d distanceRecursive:%d\n", distance, distanceRecursive);
			assert (distance != distanceRecursive) : 
				String.format("%d != %d\n", distance, distanceRecursive);
			return distance;
		}
		/**
		 * rabinKarp rolling hash
		 * function RabinKarp(string s[1..n], string pattern[1..m])
		 *   hpattern := hash(pattern[1..m]);
		 *   for i from 1 to n-m+1
		 *     hs := hash(s[i..i+m-1])
		 *     if hs = hpattern
		 *       if s[i..i+m-1] = pattern[1..m]
		 *         return i
		 *   return not found
		 *   
		 * @param pat
		 * @param txt
		 */
		public void rabinKarp(String pat, String txt) {
			
		}
		public List<String> permutation(char [] a, int choose) {
			boolean isIterative = false;
			List<String> list = new ArrayList<>();
			char [] adst = new char[choose];
			int [] av = new int[a.length];
			for(int i = 0; i < av.length; i++)
				av[i] = 0;
			if(isIterative)
				permutationIterative(a, choose, list);
			else
				permutationRecursive(a, choose, adst, 0, 0, av, list);
			pl(list);
			return list;
		}
		private void permutationIterative(char [] a, int choose, List<String> list) {
			
		}
		private void permutationRecursive(
			char [] a, int choose,
			char [] adst, int isrc, int idst, 
			int [] av,
			List<String> list
		) {
			if(idst == choose) {
				list.add(new String(adst));
				return;
			}
			for(int i = 0; i < a.length; i++) {
				if(av[i] == 1)
					continue;
				av[i] = 1;
				adst[idst] = a[i];
				permutationRecursive(a, choose, adst, i+1, idst+1, av, list);
				av[i] = 0;
			}
		}
		public void permutationRepetition(char [] a, int choose) {
			
		}
		public void permutationMultiset(char [] a, int choose) {
			
		}
		public List<String> combination(char [] a, int choose) {
			boolean isIterative = false;
			char [] adst = new char[choose];
			List<String> list = new ArrayList<>();
			if(isIterative)
				combinationIterative(a, choose, list);
			else
				combinationRecursive(a, choose, adst, 0, 0, list);
			pl(list);
			return list;
		}
		private void combinationIterative(
			char [] a, int choose, List<String> list) {
			char [] adst = new char[choose];
			int [] chain = new int[a.length];
			int idst = 0;
			int isrc = 0;
			for(int i = 0; i < a.length; i++)
				chain[i] = 0;
			for(int i = 0; i < a.length; i++) {
				adst[idst] = a[isrc];
				chain[idst] = isrc;
				idst++;
				isrc++;
				if(idst == choose) {
					list.add(new String(adst));
				}
				else if(isrc >= a.length) {
					
				}
			}
		}
		public Integer quickSelect(List<Integer> l, int k) {
			return null;
		}
		/**
		 * subsetSum is NP combination problem. 
		 * 
		 * It can be reduced to O(sN), where s is 1:sum value, N is list using DP.
		 * 
		 * FIND ALL LIST = 8
		 * N	2	3	4	4	5	6
		 * SUM	2	5	9	13	18	24
		 * S	1 	2	3	4	5	6	7	8
		 * IDX	-1	0	1	2	3	1
		 * 
		 * 	naive(int [] set, int n, int sum)
		 * 		if(sum == 0)
		 * 			return true
		 * 		if(n == 0 && sum != 0)
		 * 			return false
		 * 		if(set[n-1] > sum)
		 * 			return naive(set, n-1 sum)
		 * 		return naive(set, n-1, sum) || naive(set, n-1, sum-set[n-1])
		 * 	O(NP)
		 * 
		 * 	dp(int [] set, int n, int sum)
		 * 		boolean [][] subset = new boolean[sum+1][n+1]
		 * 		for(int i = 0; i <= n; i++)
		 * 			subset[0][i] = true
		 * 		for(int i = 1; i <= sum; i++)
		 * 			subset[i][0] = false
		 * 		for(int i = 1; i <= sum; i++)
		 * 			for(int j = 1; j <= n; j++)
		 * 				subset[i][j] = subset[i][j-1]
		 * 				if(i >= set[j-1])
		 * 					subset[i][j] = subset[i][j] || subset[i-set[j-1]][j-1]
		 * 		return subset[sum][n]
		 * 	O(sum*n)
		 * 	
		 * 
		 */
		public List<List<Integer>> subsetSum(List<Integer> l, int sum) {
			return subsetSumOfficial(l, sum);
		}
		/**
		 * FIND ALL LIST = 15
		 * 	N																		c	m	V	r	r
		 * 																			u	i		m	m	
		 * 																			r	n		a	i
		 * 																						x	n
		 * -------------------------------------------------------------------------------------------
		 * DP	0	1	2	3	4	5	6	7	8	9	10	11	12	13	14	15						
		 * -------------------------------------------------------------------------------------------
		 * 	2	1		1															2	2	2	2	2
		 * 	3	1			1	0	1												5	5	3	5	3
		 * 	5	1					2	0	1	1	0	1							10	10	5	10	5
		 * 	7	1							2	1	1	2	0	2	0	1	1		17	15	7	15	7
		 * 	8	1								2	1	3	1	2	2	1	3		25	15	8	15	8
		 * 	10	1										4	1	3	3	1	5		35	15	10	15	10
		 * 	11	1											2	3	4	2	5		46	15	11	15	11
		 * 																							
		 * final DP		1	0	1	1	0	2	0	2	2	1	4	2	3	4	2	5
		 *
		 *
		 *	actual	2	3	10		=	15	
		 *			5	10			=	15																
		 *			7	8			=	15
		 *			2	5	8		=	15
		 *			3	5	7		=	15
		 */
		private List<List<Integer>> subsetSumOfficial(List<Integer> l, int sum) {
			List<List<List<Integer>>> lll = new ArrayList<>();
			int [] a = new int[sum+1];
			a[0] = 1;
			int currentSum = 0;
			for(int i = 0; i <= sum; i++) {
				lll.add(new ArrayList<>());
			}
			for(int i = 0; i < l.size(); i++) {
				int v = l.get(i);
				currentSum += v;
				int min = Math.min(sum, currentSum);
				for(int j = min; j >= v; j--) {
					int numPossibilities = a[j-v];
					a[j] = a[j] + numPossibilities;

					if(numPossibilities == 0)
						continue;
					List<List<Integer>> ll = lll.get(j);
					if((j-v) == 0) {
						List<Integer> list = new ArrayList<>();
						list.add(v);
						ll.add(list);
					} else {
						List<List<Integer>> llDiff = lll.get(j-v);
						for(int k = 0; k < numPossibilities; k++) {
							if(llDiff != null && llDiff.size() != 0) {
								List<Integer> list = new ArrayList<>(llDiff.get(k));
								list.add(v);
								ll.add(list);
							}
						}
					}
				}
			}
			if(debug) {
				p("SUBSETSUM ARRAY: \n");
				for(int i = 0; i < a.length; i++) {
					p("%2d ", i);
				}
				p("\n");
				for(int i = 0; i < a.length; i++) {
					p("%2d ", a[i]);
				}
				p("\n");
				p("\n");
				p("PRINT LISTS FOR EACH SUM\n");
				for(int i = 0; i < lll.size(); i++) {
					p("%2d SUM\n", i);
					List<List<Integer>> ll = lll.get(i);
					for(int j = 0; j < ll.size(); j++) {
						List<Integer> list = ll.get(j);
						p("\t");
						pl(list);
					}
				}
			}

			return lll.get(sum);
		}
		/**
		 * sublistMax.
		 * return the sublist that has max value. maybe more than 1, 
		 * but just return last.
		 * 
		 * idea is keep running value of sum and keep
		 * track of highest sum seen so far. if running sum
		 * is positive, then it's contributing to max sum.
		 * but if it's negative, then cut off from there.
		 * what if all numbers are negative? running sum goes
		 * negative. in that case, still use running sum, but
		 * the idxBeg == idxEnd, where idx is largest negative val.
		 * 
		 * Use kadane for O(n)
		 * 
		 * @param l
		 * @param sum
		 * @return
		 */
		public List<Integer> sublistMax(List<Integer> l) {
			List<Integer> list = new ArrayList<>();
			int idxBeg = 0;
			int idxEnd = 0;
			int sumSoFar = 0;
			int sumMax = Integer.MIN_VALUE;
			
			for(int i = 0; i < l.size(); i++) {
				int v = l.get(i);
				sumSoFar += v;
				if(v > sumSoFar) {
					idxBeg = i;
					sumSoFar = v;
				}
				if(sumSoFar > sumMax) {
					sumMax = sumSoFar;
					idxEnd = i;
				}
				p("v:%3d sumsofar: %3d summax:%3d\n", v, sumSoFar, sumMax);
			}
			p("summax:%3d; ", sumMax);
			sumMax = 0;
			for(int i = idxBeg; i <= idxEnd; i++) {
				int v = l.get(i);
				list.add(v);
				sumMax += v;
			}
			p("summax:%3d\n", sumMax);
			return list;
		}
		/**
		 * sublistMaxWindow.
		 * find max subarray that has at most window number of elements.
		 * 
		 * @param l
		 * @param window
		 * @return
		 */
		public List<Integer> sublistMaxWindow(List<Integer> l, int window) {
			List<Integer> list = new ArrayList<>();
			int idxBeg = -1;
			int idxEnd = -1;
			int sumSoFar = 0;
			int sumMax = 0;
			return list;
		}
		/**
		 * sublistSum.
		 * return the set of sublists that equal sum. 
		 * Similar to subarraySum.
		 * 
		 * @param l
		 * @param sum
		 * @return
		 */
		public List<List<Integer>> sublistSum(List<Integer> l, int sum) {
			List<List<Integer>> ll = new ArrayList<>();
			return ll;
		}
		/**
		 * subarraySum.
		 * return the last set of subarray that equals sum. 
		 * 
		 * as soon as totalsumsofar > sum, start decrementing from back
		 * using binary search from 0:curIdx-1. This is nlogn.
		 * 
		 * Alternatively, as soon as totalSumSoFar >= sum, calculate diff.
		 * If diff exists in map of diffVal:idx, then construct list and
		 * return. This is O(n)
		 * 
		 * @param l
		 * @param sum
		 * @return
		 */
		public List<Integer> subarraySum(List<Integer> l, int sum) {
			int [] asum = Utils.initArrayInt(l.size(), 0);
			int sumSoFar = 0;
			int idxBeg = -1;
			int idxEnd = -1;
			Map<Integer, Integer> map = new HashMap<>();
			for(int i = 0; i < l.size(); i++) {
				int v = l.get(i);
				sumSoFar += v;
				asum[i] = sumSoFar;
				map.put(sumSoFar, i);
				if(sumSoFar >= sum) {
					idxEnd = i;
					int diff = sumSoFar - sum;
					if(diff == 0) {
						idxBeg = 0;
						break;
					}
					if(map.containsKey(diff)) {
						idxBeg = map.get(diff) + 1;
						break;
					}
				}
			}
			
			List<Integer> list = new ArrayList<>();
			if(idxBeg != -1 && idxEnd != -1) {
				for(int i = idxBeg; i <= idxEnd; i++)
					list.add(l.get(i));
			}
			return list;
		}
		private void combinationRecursive(
			char [] a, int choose, 
			char [] adst, int isrc, int idst,
			List<String> list) {
			if(idst == choose) {
				list.add(new String(adst));
				return;
			}
			for(int i = isrc; i < a.length; i++) {
				adst[idst] = a[i];
				combinationRecursive(a, choose, adst, i+1, idst+1, list);
			}
		}
		public void combinationRepetition(char [] a, int choose) {
			
		}
		/**
		 * dungeonHealth. Get from topleft to bottomright. determine init min health
		 * needed get to dst. can only go right or down. if health ever goes
		 * below 0, then die.
		 *  
		 * example 1
		 * matrix a[][] health values
		 * 
		 *   | 00  01  02  03  04  
		 * --+-------------------
		 * 00|  3  -4  -3   1  -2 
		 * 01| -5  -2   3  -1   3 
		 * 02| -3   6  -2  -6  -4 
		 * 03|  4  -3  -2  -1  -6  
		 * 04| -1   4  -3  -3   8
		 * 
		 * DP table construction top down of health
		 * 
		 *   | 00  01  02  03  04  
		 * --+-------------------
		 * 00|  3  -1  -4  -3  -5
		 * 01| -2  -3   0  -1   2
		 * 02| -5   3   1  -5  -2
		 * 03| -1   0  -1  -2  -8
		 * 04| -2   4   1  -2   6
		 * 
		 * DP table derived from above, minimizing init health
		 * 
		 *   | 00  01  02  03  04  
		 * --+-------------------
		 * 00|  0   1   4   3   5      
		 * 01|  2   3   0   1   0       
		 * 02|  5   0   0   5   2      
		 * 03|  1   0   1   2   8      
		 * 04|  2   0   0   2   0      
		 * 
		 * DP table construction bottom up and invert it
		 * 
		 *   |04 03 02 01 00 
		 * --+---------------
		 * 04| 0  2  0  0  2   0->2->0->0
		 * 03| 8  2  1  0  1            0
		 * 02| 2  5  0  0  5            0
		 * 01| 0  1  0  3  2            3
		 * 00| 5  3  4  1  0            1->0
		 * 
		 * example 2
		 * matrix a[][] health values
		 * 
		 *   | 00  01  02  03  04  
		 * --+-------------------
		 * 00| -1  -4  -3  -1  -2 
		 * 01| -5  -2  -3  -1  -3 
		 * 02| -3  -6  -2  -6  -4 
		 * 03| -4  -3  -2  -1  -9  
		 * 04| -1  -4  -3  -7  -7
		 * 
		 * DP table construction top down of health
		 * 
		 *   | 00  01  02  03  04  
		 * --+-------------------
		 * 00| -1  -5  -8  -9 -11
		 * 01| -6  -7 -10 -10 -13
		 * 02| -9 -13 -12 -16 -17
		 * 03|-13 -16 -14 -15 -24
		 * 04|-14 -18 -17 -22 -29
		 * 
		 * DP table derived from above, minimizing init health
		 * 
		 *   | 00  01  02  03  04  
		 * --+-------------------
		 * 00|  1   5   8   9  11
		 * 01|  6   7  10  10  13
		 * 02|  9  13  12  16  17
		 * 03| 13  16  14  15  24
		 * 04| 14  18  17  22  29
		 * 
		 * path: 29,22,15,14,12,10,7,5,1
		 * path: (4,4),(4,3),(3,3),(3,2),(2,2),(1,2),(1,1),(0,1),(0,0)
		 * rev:  (0,0),(0,1),(1,1),(1,2),(2,2),(3,2),(3,3),(4,3),(4,4)
		 * 
		 * 
		 * populating backwards, same answer, but 29 is @(0,0), not @(4,4)
		 * 
		 *   | 00  01  02  03  04  
		 * --+-------------------
		 * 00| 29  28  25  23  25
		 * 01| 22  24  22  22  23
		 * 02| 17  25  19  21  20
		 * 03| 24  20  17  15  16
		 * 04| 22  21  17  14   7
		 * 
		 * @param a
		 * @return
		 */
		public int dungeonHealth(int [][] a) {
			return 0;
		}
		public int regex(String pat, String txt) {
			return regex(pat, txt, 0, 0);
		}
		/**
		 * supports
		 * . = wild card
		 * * = 0 or more of previous char
		 * 
		 * @param pat
		 * @param txt
		 * @param idxp
		 * @param idxt
		 * @return
		 */
		public int regex(String pat, String txt, int idxp, int idxt) {
			if(idxt == txt.length()) {
				if(	(idxp == pat.length()) || 
					((idxp+1) < pat.length() && pat.charAt(idxp+1) == '*'))
					return 1;
				return 0;
			}
			if(idxp >= pat.length() || idxt >= txt.length()) {
				return 0;
			}
			if((idxp+1) < pat.length() && pat.charAt(idxp+1) == '*') {
				if((idxp+2) < pat.length() && pat.charAt(idxp+2) == txt.charAt(idxt)) {
					int res = regex(pat, txt, idxp+2, idxt);
					if(res != 0) {
						return res;
					}
				}
				if(pat.charAt(idxp) == '.' || pat.charAt(idxp) == txt.charAt(idxt)) { 
					return regex(pat, txt, idxp, idxt+1);
				}
				return regex(pat, txt, idxp, idxt+1);
			}
			if(pat.charAt(idxp) == '.' || pat.charAt(idxp) == txt.charAt(idxt)) {
				return regex(pat, txt, idxp+1, idxt+1);
			}
			return 0;
		}
		public int regexPike(String pat, String txt, int idxp, int idxt) {
			return 0;
		}
		/**
		 * rod cutting problem is repeating subproblems.
		 * 
		 * 	int cutRod(int [] price, int n)
		 * 		if(n <= 0)
		 * 			return 0
		 * 		int max = price[0]
		 * 		for(int i = 0; i < n; i++)
		 * 			max = Math.max(max, price[i] + cutRod(price, n-i-1));
		 * 		return max
		 * 	O(n^n)
		 * 
		 * 	int cutRod(int [] price, int n)
		 * 		int [] val = new int[n+1]
		 * 		val[0] = 0
		 * 		for(int i = 1; i <= n; i++)
		 * 			int max = price[0]
		 * 			for(int j = 0; j < i; j++)
		 * 				max = Math.max(max, price[j] + val[i-j-1])
		 * 			val[i] = max
		 * 		return val[n]
		 * O(n^2)
		 * 
		 * @param listPrice
		 * @param size
		 * @return
		 */
		public List<Integer> rodCutting(List<Integer> listPrice, int size) {
			List<Integer> l = new ArrayList<>();
			return l;
		}
		/**
		 * 
		 * 	partition(list, left, right, pivotIndex)
		 * 		pivotVal = list(pivotIndex)
		 * 		swap list(pivotIndex) and list(right)
		 * 		storeIndex = left
		 * 		for i from left to right-1
		 * 			if list[i] < pivotValue
		 * 				swap list[storeIndex] and list[i]
		 * 				increment storeIndex
		 * 		swap list(right) and list(storeIndex)
		 * 	return storeIndex
		 * 
		 * 
		 * O(n^2) 	worst case
		 * O(n)		best case
		 * O(n)		avg case
		 * 
		 * @param a
		 * @param num
		 * @return
		 */
		int quickSelect(int [] a, int num) {
			return 0;
		}
		public List<Pair> needlemanWunsch(String a, String b) {
			List<Pair> l = new ArrayList<>();
			return l;
		}
        /**
        * Convert unordered pairs into longest diameter tree.
        * Given is int [][] a, which is 
        * 
        *   a[0][0] = 1, a[0][1] = 2
        *   a[1][0] = 3, a[1][2] = 1
        *   a[2][0] = 4, a[2][2] = 3
        *   a[2][0] = 6, a[2][2] = 2
        *   a[2][0] = 3, a[2][2] = 5
        *
        *           1----2----6
        *           |
        *           3----4
        *           |
        *           5
        */
        public void diameterTreeUnorderedPairs(int [][] a) {
            Map<Integer, BNode> m = new HashMap<>();
            BNode r = null;
            for(int i = 0; i < a.length; i++) {
                int v0 = a[i][0];
                int v1 = a[i][1];
                BNode n0 = m.get(v0);
                BNode n1 = m.get(v1);
                if(n0 == null) {
                    n0 = new BNode(v0);
                    m.put(v0, n0);
                }
                if(n1 == null) {
                    n1 = new BNode(v1);
                    m.put(v1,  n1);
                }
                n0.add(n1);
                n1.add(n0);
                if(r == null)
                    r = n0;
            }
            AtomicInteger max = new AtomicInteger(0);
            diameterTree(r, null, m, max);
            p("MAX DIAM is %d\n", max.get());
        }
        private int 
        diameterTree(
            BNode n, 
            BNode p, 
            Map<Integer, BNode> m,
            AtomicInteger max) 
        {
            if(n == null)
                return 0;
            BNode l = null;
            BNode r = null;
            for(BNode nodeChild: n.getSet()) {
                if(nodeChild == p)
                    continue;
                if(l == null)
                    l = nodeChild;
                else
                    r = nodeChild;
            }
            int lenL = diameterTree(l, n, m, max);
            int lenR = diameterTree(r, n, m, max);
            int tmpDiameter = 1 + lenL + lenR;
            if(max.get() < tmpDiameter)
                max.set(tmpDiameter);
            int maxLength = (lenL > lenR) ? (lenL + 1) : (lenR + 1);
            return maxLength;
        }
	}
}

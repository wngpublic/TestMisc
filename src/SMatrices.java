import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class SMatrices {
	public static void p(String f, Object ...a) {
		System.out.printf(f, a);
	}
	public static class CompressedColumns {
		List<Integer> rows = new ArrayList<>();
		List<Integer> cols = new ArrayList<>();
		List<Integer> data = new ArrayList<>();

		public void printMatrix() {
			
		}
	}
	public static class CompressedRows {
		List<Integer> rows = new ArrayList<>();
		List<Integer> cols = new ArrayList<>();
		List<Integer> data = new ArrayList<>();

		public void printMatrix() {
			
		}
	}
	public static class MapOfMap {
		Map<Integer, Map<Integer, Integer>> mm = new HashMap<>();
		public void add(int row, int col, int val) {
			Map<Integer, Integer> m = mm.get(row);
			if(m == null) 
				m = new HashMap<Integer, Integer>();
			m.put(col, val);
		}
		public void delRow(int row) {
			
		}
		public void addRow(int row) {
			Set<Integer> setRowsVisited = new HashSet<>();
			for(Map.Entry<Integer, Map<Integer, Integer>> kv: mm.entrySet()) {
				Integer r = kv.getKey();
				if(r < row || setRowsVisited.contains(r)) {
					continue;
				}
				setRowsVisited.add(r);
				int j = r;
				Map<Integer, Integer> t = kv.getValue();
				Map<Integer, Integer> m = mm.get(++j);
				mm.remove(j-1);
				if(m == null) {
					setRowsVisited.add(j);
					mm.put(j, t);
				}
				else {
					while(m != null) {
						setRowsVisited.add(j);
						mm.put(j, t);
						t = m;
						m = mm.get(++j);
					}
				}
			}
		}
		public void addCol(int col) {
			
		}
		public void delCol(int col) {
			
		}
		public void printMatrix() {
			
		}
	}
	public static class Matrix {
		int [][] a = null;
		public Matrix(int x, int y) {
			a = new int[x][y];
			for(int i = 0; i < x; i++)
				for(int j = 0; j < y; j++)
					a[i][j] = 0;
		}
		public void printMatrix() {
			
		}
	}
	public static class Coord implements Comparable<Coord> {
		public int x = -1;
		public int y = -1;
		public Coord(int x, int y) {
			this.x = x;
			this.y = y;
		}
		@Override
		public int compareTo(Coord c) {
			if(x == c.x && y == c.y)
				return 0;
			if(x != c.x)
				return -1;
			return 1;
		}
		public boolean equals(Coord c) {
			return (compareTo(c) == 0);
		}
	}
	public static class Pair implements Comparable<Pair> {
		public int x = -1;
		public int y = -1;
		public Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}
		@Override
		public int compareTo(Pair p) {
			if(x == p.x && y == p.y)
				return 0;
			if(x != p.x)
				return -1;
			return 1;
		}
		public boolean equals(Pair p) {
			return (compareTo(p) == 0);
		}
	}
	public static class MatrixAlgos {
		public List<Coord> getMinCostPath(int [][] a) {
			List<Coord> listPathMinCost = new ArrayList<>();
			List<Coord> listPathCurrent = new ArrayList<>();
			int numRow = a.length;
			int numCol = a[0].length;
			int [][] aCost = Utils.initMatrixInt(numRow, numCol, Integer.MAX_VALUE);
			int [][] aVisit = Utils.initMatrixInt(numRow, numCol, -1);
			AtomicInteger minCost = new AtomicInteger(Integer.MAX_VALUE);
			int prevCost = 0;
			getMinCostPath(a, 0, 0, prevCost, minCost, aCost, aVisit, listPathCurrent, listPathMinCost);
			return listPathMinCost;
		}
		private void getMinCostPath(
			int [][]a,
			int i,
			int j,
			int prevCost,
			AtomicInteger minCost,
			int [][] aCost,
			int [][] aVisit,
			List<Coord> listPathCurrent,
			List<Coord> listPathMinCost
		) {
			if(i >= a.length || j >= a[0].length)
				return;

			if(aVisit[i][j] != -1)
				return;
			
			// if at end, check if total cost is less than best so far
			int curCost = prevCost + a[i][j];
			if((i+1) == a.length && (j+1) == a[0].length) {
				if(curCost >= minCost.get()) {
					return;
				}
				minCost.set(curCost);
				listPathMinCost.clear();
				for(Coord c: listPathCurrent)
					listPathMinCost.add(c);
				listPathMinCost.add(new Coord(i, j));
				return;
			}
			
			// do not continue if another path leading here costs less
			if(curCost > aCost[i][j]) 
				return;

			aCost[i][j] = curCost;
			aVisit[i][j] = 1;
			listPathCurrent.add(new Coord(i, j));

			// right
			getMinCostPath(a, i, j+1, curCost, minCost, aCost, aVisit, listPathCurrent, listPathMinCost);
			
			// down
			getMinCostPath(a, i+1, j, curCost, minCost, aCost, aVisit, listPathCurrent, listPathMinCost);
			
			// diagonal
			getMinCostPath(a, i+1, j+1, curCost, minCost, aCost, aVisit, listPathCurrent, listPathMinCost);
			
			aVisit[i][j] = -1;
			listPathCurrent.remove(listPathCurrent.size() - 1);
		}
		public List<Coord> getMaxCostPath(int [][] a) {
			List<Coord> listPathMaxCost = new ArrayList<>();
			List<Coord> listPathCurrent = new ArrayList<>();
			int numRow = a.length;
			int numCol = a[0].length;
			int [][] aCost = Utils.initMatrixInt(numRow, numCol, Integer.MIN_VALUE);
			int [][] aVisit = Utils.initMatrixInt(numRow, numCol, -1);
			AtomicInteger maxCost = new AtomicInteger(Integer.MIN_VALUE);
			int prevCost = 0;
			getMaxCostPath(a, 0, 0, prevCost, maxCost, aCost, aVisit, listPathCurrent, listPathMaxCost);
			return listPathMaxCost;
		}
		private void getMaxCostPath(
				int [][]a,
				int i,
				int j,
				int prevCost,
				AtomicInteger maxCost,
				int [][] aCost,
				int [][] aVisit,
				List<Coord> listPathCurrent,
				List<Coord> listPathMaxCost
			) {
				if(i >= a.length || j >= a[0].length)
					return;

				if(aVisit[i][j] != -1)
					return;
				
				// if at end, check if total cost is less than best so far
				int curCost = prevCost + a[i][j];
				if((i+1) == a.length && (j+1) == a[0].length) {
					if(curCost <= maxCost.get()) {
						return;
					}
					maxCost.set(curCost);
					listPathMaxCost.clear();
					for(Coord c: listPathCurrent)
						listPathMaxCost.add(c);
					listPathMaxCost.add(new Coord(i, j));
					return;
				}
				
				// do not continue if another path leading here costs less
				if(curCost > aCost[i][j]) 
					return;

				aCost[i][j] = curCost;
				aVisit[i][j] = 1;
				listPathCurrent.add(new Coord(i, j));

				// right
				getMaxCostPath(a, i, j+1, curCost, maxCost, aCost, aVisit, listPathCurrent, listPathMaxCost);
				
				// down
				getMaxCostPath(a, i+1, j, curCost, maxCost, aCost, aVisit, listPathCurrent, listPathMaxCost);
				
				// diagonal
				getMaxCostPath(a, i+1, j+1, curCost, maxCost, aCost, aVisit, listPathCurrent, listPathMaxCost);
				
				aVisit[i][j] = -1;
				listPathCurrent.remove(listPathCurrent.size() - 1);
			}
		public List<Coord> getMinCostPathDungeon(int [][] a) {
			int numRow = a.length;
			int numCol = a[0].length;
			
			List<Coord> listPathMinCostDungeon = new ArrayList<>();
			int [][] a1 = Utils.initMatrixInt(numRow, numCol, 0);
			a1[numRow-1][numCol-1] = Math.max(1-a[numRow-1][numCol-1], 1);
			for(int i = numRow-1; i >= 0; i--) {
				for(int j = numCol-1; j >= 0; j--) {
					
				}
			}
			p("MIN HEALTH is %d\n", a1[0][0]);
			return listPathMinCostDungeon;
		}
		public List<Coord> getMinCostPath(int [][] a, Coord src, Coord dst) {
			List<Coord> listPathMinCost = new ArrayList<>();
			List<Coord> listPathCurrent = new ArrayList<>();
			int numRow = a.length;
			int numCol = a[0].length;
			int [][] aCost = Utils.initMatrixInt(numRow, numCol, Integer.MAX_VALUE);
			int [][] aVisit = Utils.initMatrixInt(numRow, numCol, -1);
			AtomicInteger minCost = new AtomicInteger(Integer.MAX_VALUE);
			int prevCost = 0;
			getMinCostPath(a, src.x, src.y, dst, prevCost, minCost, aCost, aVisit, listPathCurrent, listPathMinCost);
			return listPathMinCost;
		}
		private void getMinCostPath(
			int [][]a,
			int i,
			int j,
			Coord dst,
			int prevCost,
			AtomicInteger minCost,
			int [][] aCost,
			int [][] aVisit,
			List<Coord> listPathCurrent,
			List<Coord> listPathMinCost
		) {
			if(i >= a.length || i < 0 || j >= a[0].length || j < 0)
				return;

			if(aVisit[i][j] != -1)
				return;
			
			// if at end, check if total cost is less than best so far
			int curCost = prevCost + a[i][j];
			if(i == dst.x && j == dst.y) {
				if(curCost >= minCost.get()) {
					return;
				}
				minCost.set(curCost);
				listPathMinCost.clear();
				for(Coord c: listPathCurrent)
					listPathMinCost.add(c);
				listPathMinCost.add(new Coord(i, j));
				return;
			}
			
			// do not continue if another path leading here costs less
			if(curCost > aCost[i][j]) 
				return;

			aCost[i][j] = curCost;
			aVisit[i][j] = 1;
			listPathCurrent.add(new Coord(i, j));

			// left
			getMinCostPath(a, i, j-1, dst, curCost, minCost, aCost, aVisit, listPathCurrent, listPathMinCost);

			// right
			getMinCostPath(a, i, j+1, dst, curCost, minCost, aCost, aVisit, listPathCurrent, listPathMinCost);
			
			// up
			getMinCostPath(a, i-1, j, dst, curCost, minCost, aCost, aVisit, listPathCurrent, listPathMinCost);

			// down
			getMinCostPath(a, i+1, j, dst, curCost, minCost, aCost, aVisit, listPathCurrent, listPathMinCost);
			
			aVisit[i][j] = -1;
			listPathCurrent.remove(listPathCurrent.size() - 1);
		}
		public List<Coord> getMaxCostPath(int [][] a, Coord src, Coord dst) {
			List<Coord> listPathMaxCost = new ArrayList<>();
			return listPathMaxCost;
		}
	}
}

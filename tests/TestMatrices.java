import java.lang.reflect.Method;
import java.util.List;

public class TestMatrices {
	public static void p(String f, Object ...o) {
		System.out.printf(f, o);
	}
	public void test(int tc) {
		try {
			Method [] methods = TestMatrices.class.getDeclaredMethods();
			if(0 <= tc && tc < methods.length)
				methods[tc].invoke(this);
			else 
				printTestCases();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void printTestCases() {
		int ctr = 0;
		for(Method method: TestMatrices.class.getDeclaredMethods()) {
			p("%02d:%s\n", ctr++, method.getName());
		}
	}
	public void testMinCostPathRepeat() {
		for(int i = 0; i < 3; i++)
			testMinCostPath();
	}
	public void testMinCostPath() {
		int numRows = 8;
		int numCols = 8;
		int min = 0;
		int max = 5;
		p("MIN COST PATH\n");
		int [][] randMatrix = Utils.getMatrixArrayInt(numRows, numCols, min, max);
		Utils.printMatrixInt(randMatrix);
		SMatrices.MatrixAlgos algos = new SMatrices.MatrixAlgos();
		List<SMatrices.Coord> list = algos.getMinCostPath(randMatrix);
		p("PRINT PATH\n");
		for(SMatrices.Coord c: list) {
			p("(%d,%d) ", c.x, c.y);
		}
		p("\n");
		p("\n");
	}
	public void testMinCostPathSrcDstRepeat() {
		for(int i = 0; i < 3; i++)
			testMinCostPathSrcDst();
	}
	public void testMinCostPathSrcDst() {
		int numRows = 8;
		int numCols = 8;
		int min = 0;
		int max = 5;
		p("MIN COST PATH SRC DST\n");
		int [][] randMatrix = Utils.getMatrixArrayInt(numRows, numCols, min, max);
		Utils.printMatrixInt(randMatrix);
		SMatrices.MatrixAlgos algos = new SMatrices.MatrixAlgos();
		SMatrices.Coord src = new SMatrices.Coord(7, 0);
		SMatrices.Coord dst = new SMatrices.Coord(0, 7);
		List<SMatrices.Coord> list = algos.getMinCostPath(randMatrix, src, dst);
		p("PRINT PATH\n");
		for(SMatrices.Coord c: list) {
			p("(%d,%d) ", c.x, c.y);
		}
		p("\n");
		p("\n");
	}
	public void testDungeon() {
		/*
		 * 
		 * -2  -3   3
		 * -6 -10   1
		 * 10  30  -5
		 * 
		 * min cost is 8 in this case: -2,-6,10,30,-5.
		 * Work backwards:
		 * 
		 *  8
		 *  6     
		 *  5   5   5
		 * 
		 * what if work forward?
		 * -2,-3,3,1,-5 = -6 min, -6 actual gain
		 * -2,-6,10,30,-5 = -8 min, 27 actual gain
		 * 
		 * this can be worked backward or forward.
		 * 
		 * 	int m = dungeon.length;
		 * 	int n = dungeon[0].length;
		 * 	//init dp table
		 * 	int[][] h = new int[m][n];
		 * 	h[m - 1][n - 1] = Math.max(1 - dungeon[m - 1][n - 1], 1);
		 * 
		 * 	//init last row
		 * 	for (int i = m - 2; i >= 0; i--) {
		 * 		h[i][n - 1] = Math.max(h[i + 1][n - 1] - dungeon[i][n - 1], 1);
		 * 	}
		 * 
		 * 	//init last column
		 * 	for (int j = n - 2; j >= 0; j--) {
		 * 		h[m - 1][j] = Math.max(h[m - 1][j + 1] - dungeon[m - 1][j], 1);
		 * 	}
		 * 
		 * 	//calculate dp table
		 * 	for (int i = m - 2; i >= 0; i--) {
		 * 	for (int i = m - 2; i >= 0; i--) {
		 * 			int down = Math.max(h[i + 1][j] - dungeon[i][j], 1);
		 * 			int right = Math.max(h[i][j + 1] - dungeon[i][j], 1);
		 * 			h[i][j] = Math.min(right, down);
		 * 		}
		 * 	}
		 * 	return h[0][0];
		 * 
		 */
		int [][] a = {
				{-2, -3, 3},
				{-6,-10, 1},
				{10, 30,-5}};
		Utils.printMatrixInt(a);
		SMatrices.MatrixAlgos algos = new SMatrices.MatrixAlgos();
		List<SMatrices.Coord> path = algos.getMinCostPathDungeon(a);
		p("PRINT PATH\n");
		for(SMatrices.Coord c: path) {
			p("(%d,%d) ", c.x, c.y);
		}
		p("\n");
		p("\n");
	}
}

import java.util.*;
public class ScratchTest {
	public static void p(String f, Object ...a) {		System.out.printf(f, a);	}
	public void test(int tcnum) {
		t00();
	}
	public static class Player {
		public char name = '\0';
		public boolean isWin = false;
	}
	/*
	 *   	  | O | X
			---------
			X | O |
			---------
	  		  | O | X

			O wins

	 */
	Random r = new Random();
	Player px = new Player();
	Player po = new Player();
	public static class Pair {
		public int x = -1;
		public int y = -1;
		public Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	public void t00() {
		for(int i = 0; i < 1000; i++) {
			ttt();
		}
	}
	public void ttt() {
		px.name = 'x';
		po.name = '0';
		Player winner = null;
		boolean isPlaying = true;
		char [][]a = new char[3][3];
		boolean isX = true;
		int ctr = 0;
		int max = 9;
		List<Pair> list = new LinkedList<>();
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				list.add(new Pair(i, j));
			}
		}
		while(isPlaying) {
			placeMarker(a, ((isX) ? px : po), list) ;
			isX = !isX;
			//printGrid(a);
			winner = evaluateGrid(a);
			if(winner != null) {
				isPlaying = false;
				//printGrid(a);
				p("%s wins\n", winner.name);
			}
			if(++ctr >= max) {
				p("no winner\n");
				break;
			}
		}
	}
	/*
	 * returns valid winner if game ends, else return null.
	 */
	public Player evaluateGrid(char [][] a) {
		// check rows
		for(int i = 0; i < 3; i++) {
			char c = a[i][0];
			if(c == '\0') {
				continue;
			}
			if(a[i][1] == c && a[i][2] == c) {
				return (c == 'x') ? px : po;
			}
		}
		
		// check cols
		for(int i = 0; i < 3; i++) {
			char c = a[0][i];
			if(c == '\0') {
				continue;
			}
			if(a[1][i] == c && a[2][i] == c) {
				return (c == 'x') ? px : po;
			}
		}
		// check diagonals cross left up to right down
		{
			char c = a[0][0];
			if(c != '\0') {
				if(a[1][1] == c && a[2][2] == c) {
					return (c == 'x') ? px : po;
				}
			}
		}
		
		// check diagonals cross right up to left down
		{
			char c = a[0][2];
			if(c != '\0') {
				if(a[1][1] == c && a[2][0] == c) {
					return (c == 'x') ? px : po;
				}
			}
		}
		return null;
	}
	/*
	 * pick a random unpopulated space, 0-8
	 */
	public void placeMarker(char [][]a, Player p, List<Pair> list) {
		int idx = r.nextInt(list.size());
		Pair pair = list.get(idx);
		list.remove(idx);
		a[pair.x][pair.y] = p.name;
	}
	public void printGrid(char [][]a) {
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				if(a[i][j] != '\0') {
					p(" %s ", a[i][j]);
				} else {
					p("   ");
				}
				if(j != 2) {
					p("|");
				}
			}
			p("\n");
			if(i != 2) {
				p(" ---------\n");
			}
		}
		p("\n\n\n");
	}
}

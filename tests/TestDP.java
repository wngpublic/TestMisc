import java.util.*;

public class TestDP extends SDynamic {
	public void test() {
		t00();
	}
	public void t00() {
		p("findMaxSubArray\n");
		List<Integer> l = Arrays.asList(12, 16, 11, 15, -5, -12, -1, -7, 12, -9, 10, 5, 9, 0);
		System.out.println(l);
		List<Integer> lresults = findMaxSubArray(l);
		System.out.println(lresults);
		p("maxval: %d\n", findMaxSubArrayValue(l));

		l = Arrays.asList(-1, 14, -18, 0, -19, 2, -18, 2, 2, 7);
		System.out.println(l);
		lresults = findMaxSubArray(l);
		System.out.println(lresults);
		p("maxval: %d\n", findMaxSubArrayValue(l));

		l = Arrays.asList(-5, -14, -2, -1, -6, -14, 7, 12, -9, -8);
		System.out.println(l);
		lresults = findMaxSubArray(l);
		System.out.println(lresults);
		p("maxval: %d\n", findMaxSubArrayValue(l));
	}
	public void t01() {
		Utils<Integer> u = new Utils<>();
		int sizelist = 10;
		p("findMaxSubArray\n");
		int numcases = 5;
		for(int i = 0; i < numcases; i++) {
			List<Integer> l = u.getInts(-19, 19, sizelist, true);
			System.out.println(l);
			List<Integer> lresults = findMaxSubArray(l);
			System.out.println(lresults);
		}
	}
}

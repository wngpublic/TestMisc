import java.util.*;

/**
 * test class to process APIs, eg java.util.*;
 * @author wng
 *
 */
public class TestAPIs {
	public void test(int tcnum) {
		t00();
	}
	public void p(String f, Object ...a) {
		System.out.printf(f, a);
	}
	public void t00() {
		Utils<Integer> u = new Utils<>();
		PriorityQueue<Integer> q = new PriorityQueue<>();
		List<Integer> l = Arrays.asList(0,1,2,3,4,5,6,7,8,9);
		u.shuffle(l);
		p("SHUFFLE\n");
		System.out.println(l);
		for(Integer i: l) {
			q.add(i);
		}
		Object [] a2 = q.toArray();
		p("PRINT PRIORITY QUEUE\n");
		for(Object o: a2) {
			Integer i = (Integer)o;
			p("%d ", i);
		}
		p("\n");
	}
}

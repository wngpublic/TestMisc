import java.util.*;

public class TestUtils {

	public void test() {
		t01();
	}
	public static void p(String f, Object ...a) {
		System.out.printf(f, a);
	}
	public static void pl(String f, Object ...a) {
		p(f + "\n", a);
	}
	public void t00() {
		final Utils<String> u = new Utils<String>();
		List<String> l = Arrays.asList("aaa","bbb","ccc","ddd","eee","fff","ggg","hhh","iii");
		System.out.println(l);
		System.out.println("NOW SHUFFLE");
		u.shuffle(l);
		System.out.println(l);
	}
	public void t01() {
		final Utils<Integer> u = new Utils<Integer>();
		List<Integer> l = Arrays.asList(0,1,2,3,4,5,6,7,8,9);
		System.out.println(l);
		System.out.println("NOW SHUFFLE");
		u.shuffle(l);
		System.out.println(l);
	}
}

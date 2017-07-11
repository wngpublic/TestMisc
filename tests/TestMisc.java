import java.lang.reflect.Method;
import java.util.*;

public class TestMisc {
	public static void p(String f, Object ...o) {
		System.out.printf(f, o);
	}
	public void test(int tc) {
		try {
			Method [] methods = TestMisc.class.getDeclaredMethods();
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
		for(Method method: TestMisc.class.getDeclaredMethods()) {
			p("%02d:%s\n", ctr++, method.getName());
		}
	}
	public void testTreeMapBoundaries() {
		/**
		 * testTreeMapBoundaries. Set up a TreeMap with neg and pos keys.
		 * Then check floor and ceiling methods for TreeMap.
		 */
		Utils<String> u = new Utils<>();
		TreeMap<Integer, String> map = new TreeMap<>();
		int numCases = 20;
		List<String> list = new ArrayList<>();
		for(int i = 0; i < numCases; i++) {
			String s = u.getString(10);
			list.add(s);
		}
		int hashlo = 0;
		int hashhi = 0;
		for(String s: list) {
			int hash = u.getHash(s);
			map.put(hash, s);
			if(hash < hashlo)
				hashlo = hash;
			if(hash > hashhi)
				hashhi = hash;
		}
		Integer kfloor1 = map.floorKey(hashlo + 1);
		Integer kfloor2 = map.floorKey(hashlo - 1);
		Integer kceil1 = map.ceilingKey(hashhi - 1);
		Integer kceil2 = map.ceilingKey(hashhi + 1);
		String sfloor1 = (kfloor1 == null) ? null : kfloor1.toString();
		String sfloor2 = (kfloor2 == null) ? null : kfloor2.toString();
		String sceil1 = (kceil1 == null) ? null : kceil1.toString();
		String sceil2 = (kceil2 == null) ? null : kceil2.toString();
		for(Map.Entry<Integer, String> kv: map.entrySet()) {
			Integer k = kv.getKey();
			p("hash:%d\n", k);
		}
		p("hashlo:%d hashhi:%d\n", hashlo, hashhi);
		p("floor+1:%s floor-1:%s ceil+1:%s ceil-1:%s\n",
				sfloor1, sfloor2, sceil1, sceil2);
	}
}

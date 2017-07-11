import java.util.*;

public class SSort {
	public static void p(String f, Object ...a) {
		System.out.printf(f, a);
	}
	static class HashAndList {
		Map<Integer,Integer> m = new HashMap<>();
		LinkedList<Integer> ll = new LinkedList<>();

		public void add(Integer i) {
			
		}
	}
	static class LRU {
		class Node implements Comparable<Node> {
			Integer i;
			Node p;
			Node n;
			@Override
			public int compareTo(Node n) {
				if(i < n.i) {
					return -1;
				}
				else if(i == n.i) {
					return 0;
				}
				return 1;
			}
		}
		class LL {
			Node h = null;
			Node t = null;
			Map<Integer, Node> m = new HashMap<>();
			void add(Node n) {
				if(h == null) {
					h = n;
					t = n;
				} else {
					n.n = h;
					h.p = n;
				}
				m.put(n.i, n);
			}
			Node get(Node n) {
				return m.get(n.i);
			}
			Node get(int i) {
				return m.get(i);
			}
			void remove(int i) {
				Node n = get(i);
				remove(n);
			}
			private void remove(Node n) {
				if(n == null) {
					return;
				}
				if(n == h && h == t) {
					h = null;
					t = null;
				}
				else if(n == h) {
					h = h.n;
				}
				else if(n == t) {
					t = t.p;
				}
				else {
					n.p.n = n.n;
					n.n.p = n.p;
				}
			}
			void print() {
				Node tmp = h;
				while(tmp != null) {
					p("%2d ", tmp.i);
				}
				p("\n");
			}
		}
		int sz;
		LinkedList<Integer> ll = new LinkedList<>();
		Set<Integer> set = new HashSet<>();
		public LRU(int sz) {
			this.sz = sz;
		}
		public int capacity() {
			return sz;
		}
		public void add(int v) {
			if(ll.size() >= sz) {
				Integer i = ll.poll();	// remove from front, which is oldest
				set.remove(i);
			}
			ll.add(v);		// add to back, which is youngest
			set.add(v);
		}
		public Integer get(int val) {
			if(!set.contains(val)) {
				return null;  
			}
			for(int i = 0; i < ll.size(); i++) {
				Integer v = ll.get(i);
				if(v.intValue() == val) {
					ll.remove(i);
					ll.add(v);
					return v;
				}
			}
			return null;
		}
		public void print() {
			p("PRINT LRU\n");
			for(int i = 0; i < ll.size(); i++) {
				p("%2d = %d\n", i, ll.get(i));
			}
		}
	}
}

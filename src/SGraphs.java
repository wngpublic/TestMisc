import java.util.*;
public class SGraphs {
	public static void p(String f, Object ...o) {
		System.out.printf(f, o);
	}
	public static void pl(Object o) {
		System.out.println(o);
	}
	public static class V {
		public int id = 0;
		public String name = null;
		public V(int id) {
			this(id, null);
		}
		public V(int id, String name) {
			this.id = id;
			this.name = name;
		}
		public int hashCode() {
			return id;
		}
	}
	public static class E {
		public V dst = null;
		public int w = 1;
		public E(V dst) {
			this(dst, 1);
		}
		public E(V dst, int w) {
			this.dst = dst;
			this.w = w;
		}
		public int hashCode() {
			return dst.id;
		}
	}
	public static class Pair implements Comparable<Pair> {
		public Integer i = null;
		public Integer j = null;
		public Pair(int i, int j) {
			this.i = i;
			this.j = j;
		}
		public Pair(int i) {
			this.i = i;
		}
		public int compareTo(Pair p) {
			return 1;
		}
	}
	
	public Map<Integer, Set<Pair>> loadGraphFromFile(String filename) {
		List<Integer> listv = new ArrayList<>();
		List<List<Integer>> liste = new ArrayList<>();
		List<List<Integer>> listw = new ArrayList<>();
		return loadGraphFromList(listv, liste, listw);
	}
	
	public Map<Integer, Set<Pair>> loadGraphFromList(
		List<Integer> listv,
		List<List<Integer>> liste
	) {
		if(listv.size() != liste.size())
			return null;
		List<List<Integer>> listw = new ArrayList<>();
		for(int i = 0; i < listv.size(); i++) {
			List<Integer> list = liste.get(i);
			List<Integer> listweight = new ArrayList<>();
			for(int j = 0; j < list.size(); j++) {
				listweight.add(null);
			}
			listw.add(listweight);
		}
		return loadGraphFromList(listv, liste, listw);
	}
	
	public Map<Integer, Set<Pair>> loadGraphFromList(
		List<Integer> listv,
		List<List<Integer>> liste,
		List<List<Integer>> listw
	) {
		if(listv.size() != liste.size() || listv.size() != listw.size())
			return null;
		Map<Integer, Set<Pair>> map = new HashMap<>();
		for(int i = 0; i < listv.size(); i++) {
			Integer k = listv.get(i);
			map.put(k, new HashSet<Pair>());
		}
		for(int i = 0; i < listv.size(); i++) {
			Integer k = listv.get(i);
			Set<Pair> set = map.get(k);
			if(set == null)
				return null;
			List<Integer> list = liste.get(i);
			List<Integer> listweight = listw.get(i);
			for(int j = 0; j < list.size(); j++) {
				set.add(new Pair(list.get(j), listweight.get(j)));
			}
		}
		return map;
	}
	
	public G loadMapToGraphG(Map<Integer, Set<Pair>> map) {
		G g = new G(map);
		return g;
	}

	public GNodes loadMapToGraphGNodes(Map<Integer, Set<Pair>> map) {
		GNodes g = new GNodes();
		return g;
	}
	
	/**
	 * G is graph with V and E instead of Node.
	 */
	public static class G {
		public Map<Integer, V> mapid_ = new HashMap<>();
		public Map<V, Set<E>> map_ = new HashMap<>();
		public boolean isWeighted = true;
		public G() {
		}
		public G(Map<Integer, Set<Pair>> map) {
			Set<Integer> vset = map.keySet();
			for(Integer id: vset)
				addVertex(id);
			for(Map.Entry<Integer, Set<Pair>> kv: map.entrySet()) {
				Integer k = kv.getKey();
				Set<Pair> setpair = kv.getValue();
				for(Pair p: setpair)
					if(p.j == null)
						addEdge(k, p.i, true);
					else
						addEdge(k, p.i, p.j, true);
			}
		}
		public int numVertices() {
			return mapid_.size();
		}
		public Set<E> getEdges(int src) {
			V v = mapid_.get(src);
			return getEdges(v);
		}
		public Set<E> getEdges(V v) {
			if(v == null)
				return null;
			return map_.get(v);
		}
		public Set<V> getVertices() {
			return map_.keySet();
		}
		public V getVertex(int id) {
			return mapid_.get(id);
		}
		public void addVertex(int id) {
			V v = new V(id);
			if(map_.containsKey(v))
				return;
			Set<E> hashset = new HashSet<>();
			map_.put(v, hashset);
			mapid_.put(id, v);
		}
		public void reset() {
			mapid_.clear();
			map_.clear();
			isWeighted = true;
		}
		public void addEdge(int src, int dst, boolean isDirected) {
			isWeighted = false;
			addEdge(src, dst, 1, isDirected);
		}
		public void addEdge(int src, int dst, int w, boolean isDirected) {
			V vsrc = mapid_.get(src);
			V vdst = mapid_.get(dst);
			if(vsrc == null || vdst == null) 
				return;
			addEdge(vsrc, vdst, w, isDirected);
		}
		public void addEdge(V src, V dst, int w, boolean isDirected) {
			if(!isDirected) {
				Set<E> set = map_.get(dst);
				if(set.contains(src))
					return;
				E e = new E(src, w);
				set.add(e);
			}
			{
				Set<E> set = map_.get(src);
				if(set.contains(dst))
					return;
				E e = new E(dst, w);
				set.add(e);
			}
 		}
		public void print() {
			p("PRINT G\n");
			for(Map.Entry<V, Set<E>> kv: map_.entrySet()) {
				V v = kv.getKey();
				Set<E> edges = kv.getValue();
				if(isWeighted) {
					p("V:%2d; EDGE(dst,wt):", v.id);
					for(E e: edges) {
						p("(%d,%d) ", e.dst.id, e.w);
					}
				} else {
					p("V:%2d; EDGE:", v.id);
					for(E e: edges) {
						p("%2d ", e.dst.id);
					}
				}
				p("\n");
			}
		}
	}
	
	public static class Node {
		V v = null;
		Set<E> edst = new HashSet<>();
		public Node(int id) {
			this(id, null);
		}
		public Node(int id, String name) {
			v = new V(id, name);
		}
		public boolean addEdge() {
			return true;
		}
	}
	/**
	 * GNodes is graph with Nodes, where Node has list of edges.
	 */
	public static class GNodes {
		public GNodes() {
			
		}
		public GNodes(Map<Integer, Set<Pair>> map) {
			
		}
	}

	public static class GAlgos {
		List<V> orderPre = new ArrayList<>();
		List<V> orderPost = new ArrayList<>();
		List<V> orderIn = new ArrayList<>();
		List<V> orderTopology = new ArrayList<>();
		boolean isDAG_ = true;
		boolean isProcessed = false;
		public void reset() {
			orderPre.clear();
			orderPost.clear();
			orderIn.clear();
			orderTopology.clear();
			isDAG_ = false;
			isProcessed = false;
		}
		public List<V> topology(G g) {
			List<V> list = new ArrayList<>();
			return list;
		}
		public List<V> dfs(G g, int src, int dst) {
			List<V> list = new ArrayList<>();
			return list;
		}
		public List<V> bfs(G g, int src, int dst) {
			List<V> list = new ArrayList<>();
			return list;
		}
		public void traverse(G g) {
			reset();
			List<V> listRoots = getRoots(g);
			boolean [] visited = new boolean[g.numVertices()];
			Set<Integer> set = new HashSet<>();
			boolean isDAG = true;
			for(int i = 0; i < visited.length; i++)
				visited[i] = false;
			for(V v: listRoots) {
				if(!traverse(g, v, visited, set)) {
					isDAG = false;
					orderPre.clear();
					orderPost.clear();
					orderIn.clear();
					break;
				}
			}
			orderTopology = new ArrayList<>(orderPre);
			isDAG_ = isDAG;
			isProcessed = true;
		}
		public boolean isDAG(G g) {
			if(!isProcessed)
				traverse(g);
			return isDAG_;
		}
		private boolean traverse(G g, V v, boolean [] visited, Set<Integer> set) {
			if(v == null)
				return true;
			if(set.contains(v.id))
				return false;
			visited[v.id] = true;
			set.add(v.id);
			orderPre.add(v);
			Set<E> setE = g.getEdges(v);
			for(E e: setE) {
				V vdst = e.dst;
				if(!traverse(g, vdst, visited, set))
					return false;
			}
			set.remove(v.id);
			orderPost.add(v);
			return true;
		}
		public List<V> getRoots(G g) {
			List<V> list = new ArrayList<>();
			Set<V> setV = g.getVertices();
			boolean [] hasIncoming = new boolean[setV.size()];
			for(int i = 0; i < hasIncoming.length; i++)
				hasIncoming[i] = false;
			for(V v: setV) {
				Set<E> setE = g.getEdges(v);
				for(E e: setE) {
					V vdst = e.dst;
					hasIncoming[vdst.id] = true;
				}
			}
			for(int i = 0; i < hasIncoming.length; i++)
				if(!hasIncoming[i])
					list.add(g.getVertex(i));
			return list;
		}
		public List<V> preorder(G g) {
			if(!isProcessed)
				traverse(g);
			return orderPre;
		}
		public List<V> inorder(G g) {
			if(!isProcessed)
				traverse(g);
			return orderIn;
		}
		public List<V> postorder(G g) {
			if(!isProcessed)
				traverse(g);
			return orderPost;
		}
		public List<V> mostExpensivePath(G g, int src, int dst) {
			List<V> list = new ArrayList<>();
			return list;
		}
		/**
		 * longestPath is NP for no DAG, and linear for DAG.
		 * 
		 * a longest path is the same as shortest path in -G by changing every
		 * weight to its negation. If shortest paths can be found in -G,
		 * then longest paths can also be found in G.
		 * 
		 * Use topological sort of given DAG, and add +1 for each step.
		 * 
		 * O(n)
		 */
		public List<V> longestPath(G g, int src, int dst) {
			List<V> list = new ArrayList<>();
			return list;
		}
		public List<V> leastExpensivePath(G g, int src, int dst) {
			List<V> list = new ArrayList<>();
			return list;
		}
		public List<V> bridges(G g) {
			List<V> list = new ArrayList<>();
			return list;
		}
	}
}

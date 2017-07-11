import java.util.*;
import java.util.zip.CRC32;
import java.lang.*;

public class SSearch {
	public static void p(String f, Object ...o) {
		System.out.printf(f, o);
	}
	public static class RingObject {
		public final int tid;
		public final int id;
		public final String name;
		public String v1;
		public String v2;
		public RingObject(int tid, int id, String name, String v1, String v2) {
			this.tid = tid;
			this.id = id;
			this.name = name;
			this.v1 = v1;
			this.v2 = v2;
		}
	}
	
	public static class RingNode {
		public int id;
		public String name;
		public TreeMap<Integer, RingObject> m = new TreeMap<>();
		public RingNode(int id, String name) {
			this.id = id;
			this.name = name;
		}
		public void put(Integer k, RingObject v) {
			m.put(k, v);
		}
		public RingObject get(Integer k) {
			return m.get(k);
		}
		public RingObject remove(Integer k) {
			return m.remove(k);
		}
		public Set<Integer> getKeys(int lo, int hi) {
			NavigableMap<Integer, RingObject> mrange = 
					m.subMap(lo, true, hi, true);
			Set<Integer> set = mrange.keySet();
			return set;
		}
		public void printSummary() {
			p("RingNode base:%10d has %d items\n", id, m.size());
		}
	}
	
	public static class RingHash {
		/**
		 * wrap around ring hash. 
		 * 
		 * A node stores items. 
		 * 
		 * A ring has physical nodes. Each physical node has virtual nodes 
		 * distributed around ring. When adding a new node, also add
		 * virtual nodes around ring. The partition is automatic when
		 * adding a new node. The new node takes new sub ranges for
		 * hash. The old values need to be remapped to new sub ranges.
		 * This means given a hash, it needs to find the floor value of
		 * hash that maps to a node (virtual or physical). Then one can
		 * get an object from that node, or put an object into that node.
		 * The floor value implies a tree map of ring, such that given
		 * hash h, ring finds floor of h, which maps to a node.
		 */
		
		@SuppressWarnings("rawtypes")
		Utils u = new Utils();
		int idtransaction = 0;
		int idobject = 0;
		int numVirtualNodes = 5;
		int numNodes = 0;
		int numItems = 0;
		int keyhi = 0;
		int keylo = 0;
		final int initialRingSize = 5;
		final int numRingSlots = 4096;
		final int szName = 10;
		TreeMap<Integer, RingNode> ring = new TreeMap<>();
		List<RingNode> listPhysicalNodes = new ArrayList<>();
		Map<Integer, RingObject> mapItems = new HashMap<>();
		
		public RingHash() {
			for(int i = 0; i < initialRingSize; i++) {
				addRingNode();
			}
		}
		
		public int transferNodeItems(RingNode node, int hash) {
			// get all entries from old floor to old ceil and transfer
			int hashwrap = hash;
			if(hash < keylo) {
				hashwrap = keyhi;
			}
			else if(hash > keyhi) {
				hashwrap = keylo;
			}

			int numTransferred = 0;
			Integer oldFloor = ring.floorKey(hashwrap);
			Integer oldCeil = ring.ceilingKey(hashwrap);
			
			RingNode nodeOld = ring.get(oldFloor);
			p("TRANSFER hash:%x wrap:%x floor:%x ceil:%x oldnode:%s to newnode:%s\n",
					hash, hashwrap, oldFloor, oldCeil, nodeOld.name, node.name);
			Set<Integer> keys = nodeOld.getKeys(hashwrap, oldCeil);
			List<Integer> listKeys = new ArrayList<>();
			for(Integer k: keys) {
				listKeys.add(k);
				RingObject o = nodeOld.get(k);
				node.put(k, o);
				numTransferred++;
			}
			for(Integer k: listKeys) {
				nodeOld.remove(k);
			}
			return numTransferred;
		}

		public void addRingNode() {
			String name = Utils.getString(szName);
			RingNode node = new RingNode(ring.size(), name);
			listPhysicalNodes.add(node);
			numNodes++;

			// map new node to all hash areas in ring.
			// then remap old range values to new node.
			int numTransferred = 0;
			for(int i = 0; i < numVirtualNodes; i++) {
				String s = String.format("%s_%06d", name, i);
				int hash = getHash(s);
				
				if(numItems != 0) {
					numTransferred += transferNodeItems(node, hash);
				}
				ring.put(hash, node);
				if(hash < keylo)
					keylo = hash;
				if(hash > keyhi)
					keyhi = hash;
			}
			if(numItems != 0) {
				double pct = numTransferred / (double)numItems;
				p("Add Node: items transferred to new node:%10d percent:%.3f\n", 
						numTransferred, pct);
			}
		}
		
		public RingNode getRingNode(int hash) throws Exception {
			int hashwrap = hash;
			if(hash < keylo) {
				hashwrap = keyhi;
			}
			else if(hash > keyhi) {
				hashwrap = keylo;
			}
			Integer floorKey = ring.floorKey(hashwrap);
			if(floorKey == null) {
				floorKey = 0;
				String msg = String.format("KEY is null for %x\n", hash);
				throw new Exception(msg);
			}
			RingNode node = ring.get(floorKey);
			return node;
		}
		
		public void addRingNodeRandomItems(int numItems) throws Exception {
			for(int i = 0; i < numItems; i++) {
				addRingNodeItem(newRandomRingObject());
			}
		}
		
		public RingObject getRingObject(String name) throws Exception {
			int hash = getHash(name);
			return getRingObject(hash);
		}
		
		public RingObject getRingObject(int hash) throws Exception {
			RingNode node = getRingNode(hash);
			if(node == null) {
				return null;
			}
			return node.get(hash);
		}
		
		public int checkItemsRetrievable()  {
			int numRetrieved = 0;
			for(Map.Entry<Integer, RingObject> kv: mapItems.entrySet()) {
				Integer k = kv.getKey();
				try {
					RingObject o = getRingObject(k);
					if(o != null) {
						numRetrieved++;
					}
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
			return numRetrieved;
		}
		
		public void checkAndPrintItemsRetrievable() throws Exception {
			int numRetrieved = 0;
			for(Map.Entry<Integer, RingObject> kv: mapItems.entrySet()) {
				Integer k = kv.getKey();
				try {
					RingObject o = getRingObject(k);
					if(o != null) {
						numRetrieved++;
					} else {
						RingNode nodeExpected = getRingNode(k);
						String nameExpected = (nodeExpected == null) ? null : nodeExpected.name;
						boolean found = false;
						for(RingNode node: listPhysicalNodes) {
							if(node.get(k) != null) {
								p("ERR: found key %15d in node %s, expected in node %s\n",
										k, nameExpected, node.name);
								found = true;
							}
						}
						if(!found) {
							p("ERR: key %15d NOT FOUND ANYWHERE\n", k);
						}
					}
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
			p("CHECK ITEMS: EXP:%d ACT:%d\n", numItems, numRetrieved);
			if(numItems != numRetrieved) {
				throw new Exception("CHECK ITEMS RETRIEVABLE MISMATCH\n");
			}
		}
		
		public void addRingNodeItem(RingObject o) throws Exception {
			int hash = getHash(o.name);
			RingNode node = getRingNode(hash);
			if(node == null) {
				throw new Exception("Ring Node is null\n");
			}
			node.put(hash, o);
			numItems++;
		}
		
		public int getHash(String s) {
			CRC32 crc = new CRC32();
			crc.update(s.getBytes());
			int hash = (int)crc.getValue();
			return hash;
		}
		
		public RingObject newRandomRingObject() {
			int tid = idtransaction++;
			int id = idobject++;
			String name = Utils.getString(20);
			String v1 = Utils.getString(Utils.getInt(10, 20));
			String v2 = Utils.getString(Utils.getInt(10, 20));
			RingObject o = new RingObject(tid, id, name, v1, v2);
			int hash = getHash(name);
			mapItems.put(hash, o);
			return o;
		}	
		
		public void print() {
			p("----------------------------------------------------\n");
			p("NUM PHY NODES:%5d NUM VIR NODES:%5d NUM ITEMS:%d\n", 
					listPhysicalNodes.size(), ring.size(), numItems);
			int ctr = 0;
			// print physical nodes
			p("PHYSICAL NODES\n");
			for(RingNode n: listPhysicalNodes) {
				String name = n.name;
				if(numItems == 0) {
					p("%5d	NODE NAME: %15s\n", ctr, name);
				} else {
					int numLocalItems = n.m.size();
					double pct = numLocalItems / (double)numItems;
					p("%5d	NODE NAME: %15s NUM ITEMS:%10d PCT:%.3f\n", 
							ctr, name, numLocalItems, pct);
				}
				ctr++;
			}

			// print virtual nodes
			ctr = 0;
			p("VIRTUAL NODES\n");
			for(Map.Entry<Integer, RingNode> kv: ring.entrySet()) {
				Integer k = kv.getKey();
				RingNode v = kv.getValue();
				p("%5d	VIRT NODE: %15s HASH:%10x\n", ctr, v.name, k);
				ctr++;
			}
		}
	}
}


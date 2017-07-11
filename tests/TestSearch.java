public class TestSearch extends SSearch {
	public void test(int tcnum) {
		try {
			testRingHash();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void testRingHash() throws Exception {
		RingHash ring = new RingHash();
		ring.addRingNode();
		p("\nINIT RING\n");
		ring.print();
		p("\nADD ITEMS\n");
		ring.addRingNodeRandomItems(1000);
		ring.print();
		ring.checkAndPrintItemsRetrievable();
		p("\nADD NODE\n");
		ring.addRingNode();
		ring.print();
		ring.checkAndPrintItemsRetrievable();
		p("\nADD ITEMS\n");
		ring.addRingNodeRandomItems(1000);
		ring.print();
		ring.checkAndPrintItemsRetrievable();
		p("\nADD NODE\n");
		ring.addRingNode();
		ring.print();
		ring.checkAndPrintItemsRetrievable();
		p("\nADD ITEMS\n");
		ring.addRingNodeRandomItems(1000);
		ring.print();
		ring.checkAndPrintItemsRetrievable();
	}
}

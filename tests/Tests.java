public class Tests {
	public static void p(String f, Object ...a) {
		System.out.printf(f, a);
	}
	public void test(String [] a) {
		if(a.length == 0) {
			p("Main testcasenum\n");
			printCategories();
		} else {
			processArgs(a);
		}
	}
	public void printCategories() {
		p("00: Concurrency\n");
		p("01: Shapes\n");
		p("02: Graphs\n");
		p("03: IO\n");
		p("04: Matrices\n");
		p("05: NIO\n");
		p("06: Search\n");
		p("07: Scratch\n");
		p("08: Sort\n");
		p("09: Strings\n");
		p("10: Trees\n");
		p("11: Utils\n");
		p("12: Misc\n");
		p("13: APIs\n");
		p("14: DP\n");
		p("15: Bits\n");
		p("16: Any\n");
	}
	public void processArgs(String [] a) {
		for(int i = 0; i < a.length; i++) {
			//p("%2d = %s\n", i, a[i]);
		}
		int tcNum = Integer.parseInt(a[0]);
		int tcSubNum = (a.length >= 2) ? Integer.parseInt(a[1]) : -1;
		switch(tcNum) {
		case 0: t00(tcSubNum); break;
		case 1: t01(tcSubNum); break;
		case 2: t02(tcSubNum); break;
		case 3: t03(tcSubNum); break;
		case 4: t04(tcSubNum); break;
		case 5: t05(tcSubNum); break;
		case 6: t06(tcSubNum); break;
		case 7: t07(tcSubNum); break;
		case 8: t08(tcSubNum); break;
		case 9: t09(tcSubNum); break;
		case 10: t10(tcSubNum); break;
		case 11: t01(tcSubNum); break;
		case 12: t12(tcSubNum); break;
		case 13: t13(tcSubNum); break;
		case 14: t14(tcSubNum); break;
		case 15: t15(tcSubNum); break;
		case 16: t16(tcSubNum); break;
		case 17: t17(tcSubNum); break;
		case 18: t18(tcSubNum); break;
		case 19: t19(tcSubNum); break;
		case 20: t20(tcSubNum); break;
		default:
			printCategories();
			break;
		}
	}
	public void t00(int tcnum) {		(new TestConcurrency()).test(tcnum);	}
	public void t01(int tcnum) {		(new TestShapes()).test();	}
	public void t02(int tcnum) {		(new TestGraphs()).test(tcnum);	}
	public void t03(int tcnum) {		(new TestIO()).test(tcnum);	}
	public void t04(int tcnum) {		(new TestMatrices()).test(tcnum);	}
	public void t05(int tcnum) {		(new TestNIO()).test(tcnum);	}
	public void t06(int tcnum) {		(new TestSearch()).test(tcnum);	}
	public void t07(int tcnum) {		(new ScratchTest()).test(tcnum);	}
	public void t08(int tcnum) {		(new TestSort()).test(tcnum);	}
	public void t09(int tcnum) {		(new TestStrings()).test(tcnum);	}
	public void t10(int tcnum) {		(new TestTrees()).test(tcnum);	}
	public void t11(int tcnum) {		(new TestUtils()).test();	}
	public void t12(int tcnum) {		(new TestMisc()).test(tcnum);	}
	public void t13(int tcnum) {		(new TestAPIs()).test(tcnum);	}
	public void t14(int tcnum) {		(new TestDP()).test(); 		}
	public void t15(int tcnum) {		(new TestBits()).test();	}
	public void t16(int tcnum) {		(new TestAny()).test(tcnum);	}
	public void t17(int tcnum) {	}
	public void t18(int tcnum) {	}
	public void t19(int tcnum) {	}
	public void t20(int tcnum) {	}
	public void t21(int tcnum) {	}
	public void t22(int tcnum) {	}
	public void t23(int tcnum) {	}
	public void t24(int tcnum) {	}
	public void t25(int tcnum) {	}
}
